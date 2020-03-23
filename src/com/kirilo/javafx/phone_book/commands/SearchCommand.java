package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchCommand extends AbstractControllerCommand {
    private MainController controller;
    private ObservableList<Person> backupList;

    public SearchCommand(MainController controller) {
        super(controller);
        this.controller = controller;
        backupList = FXCollections.observableArrayList();
        backupList.addAll(controller.getAddressBook().getPersonList());
    }

    @Override
    public boolean execute() {
        controller.getAddressBook().getPersonList().clear();
        for (Person person : backupList) {
            String searchingString = controller.getTextField().getText().toLowerCase();
            if (person.getFullName().toLowerCase().contains(searchingString) || person.getPhone().toLowerCase().contains(searchingString)) {
                controller.getAddressBook().add(person);
            }
        }
        return false;
    }
}
