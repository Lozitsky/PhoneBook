package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.EditDialogController;
import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.model.Person;

public class AddCommand extends AbstractControllerCommand {

    private MainController controller;
    private EditDialogController editDialogController;

    public AddCommand(MainController controller) {
        super(controller);
        this.controller = controller;
        editDialogController = controller.getEditDialogController();
    }

    @Override
    public boolean execute() {
        editDialogController.setPerson(null);
        Person person = editDialogController.getPerson();

        controller.showDialog();

        return person != null && person.getFullName().trim().length() != 0 && person.getPhone().trim().length() != 0;
    }
}
