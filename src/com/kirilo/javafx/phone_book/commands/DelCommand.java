package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.model.Person;
import javafx.collections.ObservableList;

import static com.kirilo.javafx.phone_book.utils.DialogManager.isSelected;

public class DelCommand extends AbstractControllerCommand {
    private MainController controller;

    private ObservableList<Person> selectedPersons;

    public DelCommand(MainController controller) {
        super(controller);
        this.controller = controller;
        selectedPersons = controller.getSelectedPersons();

    }

    @Override
    public boolean execute() {
        if (!isSelected(selectedPersons, "error", "select_person")) {
            return false;
        }

        return selectedPersons.size() > 1 ? controller.getAddressBook().delete(selectedPersons) : controller.getAddressBook().delete(selectedPersons.get(0));
    }
}
