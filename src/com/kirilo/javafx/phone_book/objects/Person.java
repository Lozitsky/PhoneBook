package com.kirilo.javafx.phone_book.objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private StringProperty fullName;
    private StringProperty phone;
    private Integer id;

    public Person(String fullName, String phone) {
        this.fullName = new SimpleStringProperty(fullName);
        this.phone = new SimpleStringProperty(phone);
    }

    public Person() {
        fullName = new SimpleStringProperty("");
        phone = new SimpleStringProperty("");
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public StringProperty getFullNameProperty() {
        return fullName;
    }

    public StringProperty getPhoneProperty() {
        return phone;
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName.get() + '\'' +
                ", phone='" + phone.get() + '\'' +
                '}';
    }
}
