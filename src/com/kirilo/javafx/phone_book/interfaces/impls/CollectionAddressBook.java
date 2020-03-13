package com.kirilo.javafx.phone_book.interfaces.impls;

import com.kirilo.javafx.phone_book.interfaces.AddressBook;
import com.kirilo.javafx.phone_book.objects.Person;

import java.util.ArrayList;
import java.util.List;

public class CollectionAddressBook implements AddressBook {
    List<Person> personList = new ArrayList<>();

    public List<Person> getPersonList() {
        return personList;
    }

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    @Override
    public void update(Person person) {

    }
}
