package com.kirilo.javafx.phone_book.utils;

import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

public class DataUtil {

    public static void fillTestData(ObservableList<Person> personList) {
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

    public static boolean changeToFilterField(TextField textField, FilteredList<Person> filteredList) {
//        TextField field = new TextField(textField.getText());
        textField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(person -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String newValueLoverCase = newValue.toLowerCase();
                    return person.getFullName().toLowerCase().contains(newValueLoverCase) ||
                            person.getPhone().toLowerCase().contains(newValueLoverCase);
                })
        );
        return textField.getLength() > 0;
    }
}
