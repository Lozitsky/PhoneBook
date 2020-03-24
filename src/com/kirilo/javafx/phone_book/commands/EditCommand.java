package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.EditDialogController;
import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;

import java.util.ResourceBundle;

import static com.kirilo.javafx.phone_book.utils.DialogManager.checkValues;
import static com.kirilo.javafx.phone_book.utils.DialogManager.isSelected;

public class EditCommand extends AbstractControllerCommand {

    private MainController controller;
    private ResourceBundle resources;
    private EditDialogController editDialogController;

    public EditCommand(MainController controller) {
        super(controller);
        this.controller = controller;
        editDialogController = controller.getEditDialogController();
        resources = controller.getResources();
    }

    @Override
    public boolean execute() {
        Person selectedPerson = controller.getSelectedPerson();
        if (!isSelected(selectedPerson, resources.getString("error"), resources.getString("select_person"))) {
            return false;
        }

        if (!checkValues(selectedPerson, resources.getString("error"), resources.getString("fill_field"))) {
            return false;
        }

        editDialogController.setPerson(selectedPerson);
        controller.showDialog();

        System.out.print("edit ");
        return true;
    }
}
