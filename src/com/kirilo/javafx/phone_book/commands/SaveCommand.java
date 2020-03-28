package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.EditDialogController;
import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;

import static com.kirilo.javafx.phone_book.utils.DialogManager.checkValues;
import static com.kirilo.javafx.phone_book.utils.DialogManager.isSelected;

public class SaveCommand extends AbstractControllerCommand {

    private MainController controller;
    private EditDialogController editDialogController;

    public SaveCommand(MainController control) {
        super(control);
        this.controller = control;
        editDialogController = control.getEditDialogController();
    }

    @Override
    public boolean execute() {
        Person selectedPerson = editDialogController.getPerson();
        String fullName = editDialogController.getFieldName().getText();
        String phone = editDialogController.getFieldPhone().getText();

        if (!isSelected(selectedPerson, "error", "select_person")) {
            return false;
        }

        if (!checkValues("error", "fill_field", fullName, phone)) {
            return false;
        }

        selectedPerson.setFullName(fullName);
        selectedPerson.setPhone(phone);

        controller.getFxmlEdit().getScene().getWindow().hide();

        return true;
    }
}
