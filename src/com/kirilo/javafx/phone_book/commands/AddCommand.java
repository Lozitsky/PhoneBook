package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.EditDialogController;
import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.scene.control.TableView;

import java.util.ResourceBundle;

public class AddCommand extends AbstractControllerCommand {

    private MainController controller;
    private EditDialogController editDialogController;
    private ResourceBundle resources;

    public AddCommand(MainController controller) {
        super(controller);
        this.controller = controller;
        editDialogController = controller.getEditDialogController();
        resources = controller.getResources();
    }

    @Override
    public boolean execute() {
        editDialogController.setPerson(new Person());
        Person person = editDialogController.getPerson();

        controller.showDialog();

        if (person == null || person.getFullName().trim().length() == 0 || person.getPhone().trim().length() == 0) {
            return false;
        }

        controller.getAddressBook().add(person);
        int row = controller.getFilteredList().size() - 1;

        TableView<Person> tableView = controller.getTableView();
//        tableView.requestFocus();
//        tableView.getSelectionModel().select(row);
        tableView.scrollTo(row);
        return true;
    }
}
