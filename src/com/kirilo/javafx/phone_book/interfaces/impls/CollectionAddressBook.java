package com.kirilo.javafx.phone_book.interfaces.impls;

import com.kirilo.javafx.phone_book.interfaces.AddressBook;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAddressBook implements AddressBook {
    private ObservableList<Person> personList;

    public CollectionAddressBook() {
//        this.personList = new ArrayList<>();
        personList = FXCollections.observableArrayList();
        fillTestData();
    }

    public ObservableList<Person> getPersonList() {
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

    private void fillTestData() {
        personList.add(new Person("Hans Muster", "0971234561"));
        personList.add(new Person("Ruth Mueller", "0971234562"));
        personList.add(new Person("Heinz Kurz", "0971234563"));
        personList.add(new Person("Cornelia Meier", "0971234564"));
        personList.add(new Person("Werner Meyer", "0971234565"));
        personList.add(new Person("Lydia Kunz", "0971234566"));
        personList.add(new Person("Anna Best", "0971234567"));
        personList.add(new Person("Stefan Meier", "0971234568"));
        personList.add(new Person("Martin Mueller", "0971234569"));
    }
}
