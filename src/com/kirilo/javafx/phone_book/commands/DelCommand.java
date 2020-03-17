package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.ObservableList;

public class DelCommand extends AbstractControllerCommand {
    private MainController controller;

    public DelCommand(MainController controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public boolean execute() {
        ObservableList<Person> selectedPersons = controller.getSelectedPersons();
        controller.getAddressBook().getPersonList().removeAll(selectedPersons);
        System.out.print("delete ");
        return true;
    }
}
