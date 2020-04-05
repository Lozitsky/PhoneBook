package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.EditDialogController;
import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.model.Person;

import static com.kirilo.javafx.phone_book.utils.DialogManager.checkValues;
import static com.kirilo.javafx.phone_book.utils.DialogManager.isSelected;

public class EditCommand extends AbstractControllerCommand {

    private MainController controller;
    private EditDialogController editDialogController;

    public EditCommand(MainController controller) {
        super(controller);
        this.controller = controller;
        editDialogController = controller.getEditDialogController();
    }

    @Override
    public boolean execute() {
        Person selectedPerson = controller.getSelectedPerson();
        if (
                !isSelected(selectedPerson, "error", "select_person") ||
                !checkValues(selectedPerson, "error", "fill_field")
        ) {
            return false;
        }

        editDialogController.setPerson(selectedPerson);
        controller.showDialog();

        return true;
    }
}
