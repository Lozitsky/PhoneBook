package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.EditDialogController;
import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;

public class AddCommand extends AbstractControllerCommand {

    private MainController controller;

    public AddCommand(MainController controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public boolean execute() {
        EditDialogController editDialogController = controller.getEditDialogController();
        editDialogController.setPerson(new Person());
        Person person = editDialogController.getPerson();
        controller.showDialog();
        if (person.getFullName().equals("") || person.getPhone().equals("")) {
            return false;
        }

        controller.getAddressBook().add(person);

        System.out.print("add ");
        return true;
    }
}
