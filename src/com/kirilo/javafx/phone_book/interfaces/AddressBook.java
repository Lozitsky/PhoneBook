package com.kirilo.javafx.phone_book.interfaces;

import com.kirilo.javafx.phone_book.objects.Person;

public interface AddressBook {
    void add(Person person);

    void delete(Person person);

    void update(Person person);
}
