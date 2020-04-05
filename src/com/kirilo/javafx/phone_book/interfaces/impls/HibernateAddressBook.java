package com.kirilo.javafx.phone_book.interfaces.impls;

import com.kirilo.javafx.phone_book.interfaces.AddressBook;
import com.kirilo.javafx.phone_book.objects.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateAddressBook implements AddressBook {
    private static final String FULL_NAME_LIKE_1_$_S_OR_PHONE_LIKE_1_$_S = "select p from Person p where p.fullName like '%%%1$s%%' or phone like '%%%1$s%%'";
    private static final String DELETE_FROM_PERSON_WHERE_ID_IN = "DELETE FROM Person p WHERE p.id IN(%s)";
    private final ObservableList<Person> personList;
    EntityManager entityManager;

    public HibernateAddressBook() {
        EntityManagerFactory modelPersonEntity = Persistence.createEntityManagerFactory("PersonDB");
        entityManager = modelPersonEntity.createEntityManager();
        personList = FXCollections.observableArrayList();
    }

    @Override
    public boolean add(Person person) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(person);
            transaction.commit();

            return personList.add(person);
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean delete(Person person) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(person);
            transaction.commit();

            return personList.remove(person);
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean delete(ObservableList<Person> persons) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();

            transaction.begin();
            final StringBuilder builder = new StringBuilder();
            persons.forEach(person -> builder.append(person.getId()).append(","));
            builder.setLength(builder.length() - 1);
            entityManager.createQuery(String.format(DELETE_FROM_PERSON_WHERE_ID_IN, builder.toString()))
                    .executeUpdate();
            transaction.commit();

            return personList.removeAll(persons);
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    //    https://stackoverflow.com/questions/1607532/when-to-use-entitymanager-find-vs-entitymanager-getreference-with-jpa
    @Override
    public boolean update(Person person) {
/*      final Query query = entityManager.createQuery("update Person p set id = :id, fullName = :fullName, phone = :phone where id = :id");
        int rows = 0;
        rows = query.setParameter("id", person.getId())
                .setParameter("fullName", person.getFullName())
                .setParameter("phone", person.getPhone())
                .executeUpdate();*/
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Person reference = entityManager.getReference(Person.class, person.getId());
/*        reference.setFullName(person.getFullName());
        reference.setPhone(person.getPhone());*/
            transaction.commit();

            return true;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public ObservableList<Person> findAll() {
        final List<Person> people = entityManager.createQuery("from Person ", Person.class).getResultList();
        personList.addAll(people);
        return personList;
    }

    //    https://stackoverflow.com/questions/21456494/spring-jpa-query-with-like
    @Override
    public ObservableList<Person> find(String text) {
        personList.clear();
        final List<Person> people = entityManager.createQuery(String.format(FULL_NAME_LIKE_1_$_S_OR_PHONE_LIKE_1_$_S, text)).getResultList();
        personList.addAll(people);
        return personList;
    }
}
