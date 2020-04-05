package com.kirilo.javafx.phone_book.objects.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Access(AccessType.PROPERTY )
@Table(name = "person", schema = "main", catalog = "")
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

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    @Transient
    public StringProperty getFullNameProperty() {
        return fullName;
    }

    @Transient
    public StringProperty getPhoneProperty() {
        return phone;
    }

    @Basic
    @Column(name = "full_name", nullable = false, length = -1)
    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    @Basic
    @Column(name = "phone", nullable = false, length = -1)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(fullName, person.fullName) &&
                Objects.equals(phone, person.phone) &&
                Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, phone, id);
    }
}
