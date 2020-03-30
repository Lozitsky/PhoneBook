package com.kirilo.javafx.phone_book.interfaces.impls;

import com.kirilo.javafx.phone_book.db.SQLiteConnection;
import com.kirilo.javafx.phone_book.interfaces.AddressBook;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// https://stackoverflow.com/questions/42494178/java-sql-sqlexception-database-connection-closed
public class DBAddressBook implements AddressBook {
    public static final String SELECT_FROM_PERSON = "select * from person";
    public static final String INSERT_INTO_PERSON = "INSERT INTO person VALUES(null, ?, ?)";
    public static final Logger LOGGER = Logger.getLogger(DBAddressBook.class.getName());
    public static final String DELETE_FROM_PERSON_WHERE_ID = "DELETE FROM person WHERE id=%d";
    public static final String DELETE_FROM_PERSON_WHERE_ID_IN = "DELETE FROM person WHERE id IN(%s)";
    public static final String SELECT_FROM_PERSON_WHERE_FULL_NAME_LIKE_OR_PHONE_LIKE = "SELECT * FROM person WHERE full_name LIKE ? OR phone LIKE ?";
    private static Connection connection;
    private ObservableList<Person> personList;

    public DBAddressBook() {
        personList = FXCollections.observableArrayList();
    }

    @Override
    public boolean add(Person person) {
        initConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_PERSON);
            statement.setString(1, person.getFullName());
            statement.setString(2, person.getPhone());
            int update = statement.executeUpdate();
            if (update > 0) {
                final int id = statement.getGeneratedKeys().getInt(1);
                person.setId(id);
                return personList.add(person);
            }
        } catch (Exception e) {
            final Throwable[] suppressed = e.getSuppressed();
            for (Throwable throwable : suppressed) {
                System.out.println("Suppressed");
                throwable.printStackTrace();
            }
            LOGGER.log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Person person) {
        initConnection();
        try (Statement statement = connection.createStatement()) {
            final int update = statement.executeUpdate(String.format(DELETE_FROM_PERSON_WHERE_ID, person.getId()));

            if (update > 0) {
                return personList.remove(person);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return false;
    }

    //    https://stackoverflow.com/questions/45750152/select-multiple-rows-using-id-in-sqlite
    @Override
    public boolean delete(ObservableList<Person> persons) {
        initConnection();
        try (Statement statement = connection.createStatement()) {

            final StringBuilder builder = new StringBuilder();
            persons.forEach(person -> builder.append(person.getId()).append(","));
            final String expression = builder.toString();

            final int update = statement.executeUpdate(String.format(DELETE_FROM_PERSON_WHERE_ID_IN,
                    expression.substring(0, expression.length() - 1)));

            if (update > 0) {
                return personList.removeAll(persons);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Person person) {
        initConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET full_name=?, phone=? WHERE id=?")) {
            preparedStatement.setString(1, person.getFullName());
            preparedStatement.setString(2, person.getPhone());
            preparedStatement.setInt(3, person.getId());

            final int update = preparedStatement.executeUpdate();

            if (update > 0) {
                System.out.println(update);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public ObservableList<Person> findAll() {
        initConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_FROM_PERSON)
        ) {
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setFullName(resultSet.getString("full_name"));
                person.setPhone(resultSet.getString("phone"));
                System.out.println(person);
                personList.add(person);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return personList;
    }

    @Override
    public ObservableList<Person> find(String text) {
        personList.clear();
        initConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_PERSON_WHERE_FULL_NAME_LIKE_OR_PHONE_LIKE)) {
            final String format = String.format("%%%s%%", text);
            preparedStatement.setString(1, format);
            preparedStatement.setString(2, format);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setFullName(resultSet.getString("full_name"));
                person.setPhone(resultSet.getString("phone"));
                personList.add(person);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return personList;
    }

    private void initConnection() {
        connection = SQLiteConnection.getConnection();
    }
}
