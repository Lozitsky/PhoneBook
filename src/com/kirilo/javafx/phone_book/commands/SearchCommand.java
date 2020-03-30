package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.ObservableList;

public class SearchCommand extends AbstractControllerCommand {
    private MainController controller;

    public SearchCommand(MainController controller) {
        super(controller);
        this.controller = controller;
        resetFilter();
    }

    private void resetFilter() {
        controller.getFilteredList().setPredicate(person -> true);
    }

    @Override
    public boolean execute() {
        String text = controller.getTextField().getText();
        final ObservableList<Person> people = controller.getAddressBook().find(text);
        if (people == null || people.isEmpty()) {
            return false;
        }
        return controller.getFilteredList().retainAll(people);
    }
}
