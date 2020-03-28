package com.kirilo.javafx.phone_book.interfaces.impls;

import com.kirilo.javafx.phone_book.interfaces.AddressBook;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAddressBook implements AddressBook {
    private ObservableList<Person> personList;

    public CollectionAddressBook() {
        personList = FXCollections.observableArrayList();
    }

    @Override
    public boolean add(Person person) {
        personList.add(person);
        return true;
    }

    @Override
    public boolean delete(Person person) {
        personList.remove(person);
        return true;
    }

    @Override
    public boolean delete(ObservableList<Person> persons) {
        return personList.removeAll(persons);
    }

    @Override
    public boolean update(Person person) {

        return true;
    }

    @Override
    public ObservableList<Person> findAll() {
        return personList;
    }

    @Override
    public ObservableList<Person> find(String text) {
        return null;
    }

}
