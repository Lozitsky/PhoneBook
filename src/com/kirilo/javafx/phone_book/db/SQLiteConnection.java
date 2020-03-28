package com.kirilo.javafx.phone_book.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {
    public static final String SQLITE_JDBC = "org.sqlite.JDBC";
    private static Connection connection;
    public static final String URL = "jdbc:sqlite:db\\test.db";

    public static Connection getConnection() {
        try {
            Driver driver = (Driver) Class.forName(SQLITE_JDBC).newInstance();
            if (driver == null) {
                System.out.println("!!!!");
            }
            if (connection == null) {
                connection = DriverManager.getConnection(URL);
            }
            return connection;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
