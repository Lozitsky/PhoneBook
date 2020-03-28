package com.kirilo.javafx.phone_book.interfaces;

import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.ObservableList;

public interface AddressBook {
    boolean add(Person person);

    boolean delete(Person person);

    boolean delete(ObservableList<Person> persons);

    boolean update(Person person);

    ObservableList<Person> findAll();

    ObservableList<Person> find(String text);

}
