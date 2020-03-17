package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.EditDialogController;
import com.kirilo.javafx.phone_book.objects.Person;

public class SaveCommand extends AbstractControllerCommand {

    private EditDialogController control;

    public SaveCommand(EditDialogController control) {
        super(control);
        this.control = control;
    }

    @Override
    public boolean execute() {
        Person selectedPerson = control.getPerson();
        String fullName = control.getFieldName().getText();
        String phone = control.getFieldPhone().getText();

        if (fullName.equals("") || phone.equals("")) {
            return false;
        }

        selectedPerson.setFullName(fullName);
        selectedPerson.setPhone(phone);
        return true;
    }
}
