package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.commands.CancelCommand;
import com.kirilo.javafx.phone_book.commands.Command;
import com.kirilo.javafx.phone_book.commands.SaveCommand;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditDialogController implements Controller {
    @FXML
    private Button buttonOk;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldPhone;
    @FXML
    private Button buttonCancel;
    private Person person;
    private MainController mainController;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if (person == null) {
            return;
        }
        this.person = person;
        fieldName.setText(person.getFullName());
        fieldPhone.setText(person.getPhone());
    }

    public TextField getFieldName() {
        return fieldName;
    }

    public TextField getFieldPhone() {
        return fieldPhone;
    }

    public void actionClose(ActionEvent actionEvent) {
        executeCommand(mainController.getCancelCommand());
    }

    @Override
    public void executeCommand(Command command) {
        if (command.execute()) {
        }
    }

    public void actionSave(ActionEvent actionEvent) {
        executeCommand(new SaveCommand(this));
        executeCommand(mainController.getCancelCommand());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}