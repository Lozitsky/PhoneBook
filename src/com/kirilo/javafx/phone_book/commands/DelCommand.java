package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.ObservableList;

import java.util.ResourceBundle;

import static com.kirilo.javafx.phone_book.utils.DialogManager.isSelected;

public class DelCommand extends AbstractControllerCommand {
    private MainController controller;
    private ResourceBundle resources;
    private ObservableList<Person> selectedPersons;

    public DelCommand(MainController controller) {
        super(controller);
        this.controller = controller;
        selectedPersons = controller.getSelectedPersons();
        resources = controller.getResources();
    }

    @Override
    public boolean execute() {
        if (!isSelected(selectedPersons, resources.getString("error"), resources.getString("select_person"))) {
            return false;
        }
//        System.out.println(selectedPersons);
        controller.getAddressBook().getPersonList().removeAll(selectedPersons);
//        System.out.print("delete ");
        return true;
    }
}
