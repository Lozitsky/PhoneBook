package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.EditDialogController;
import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.scene.control.TableView;

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
        int row = 0;
        final boolean isExecute;
        if (editDialogController.isAdd()) {
            isExecute = controller.getAddressBook().add(selectedPerson);
//            row = controller.getFilteredList().size() - 1;
        } else {
            isExecute = controller.getAddressBook().update(selectedPerson);
        }
        row = controller.getFilteredList().lastIndexOf(selectedPerson);
        System.out.println(row);

        TableView<Person> tableView = controller.getTableView();
//        tableView.requestFocus();
//        tableView.getSelectionModel().select(row);
        tableView.scrollTo(row);

        controller.getFxmlEdit().getScene().getWindow().hide();

        return isExecute;
    }
}
