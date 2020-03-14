package com.kirilo.javafx.phone_book.objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    /*private String fullName;
    private String phone;*/
    private StringProperty fullName;
    private StringProperty phone;

    public Person(String fullName, String phone) {
/*        this.fullName = fullName;
        this.phone = phone;*/
        this.fullName = new SimpleStringProperty(fullName);
        this.phone = new SimpleStringProperty(phone);
    }

    public Person() {
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
//        this.fullName = fullName;
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
