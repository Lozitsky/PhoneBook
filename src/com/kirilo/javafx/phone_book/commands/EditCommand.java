package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.EditDialogController;
import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;

import java.util.Optional;

public class EditCommand extends AbstractControllerCommand {

    private MainController controller;

    public EditCommand(MainController controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public boolean execute() {
        EditDialogController editDialogController = controller.getEditDialogController();
        Person selectedPerson = controller.getSelectedPerson();
        if (selectedPerson == null ) {
            return false;
        }
        try {
            editDialogController.setPerson(Optional.of(selectedPerson).orElseThrow(Exception::new));
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.showDialog();

        System.out.print("edit ");
        return true;
    }
}
