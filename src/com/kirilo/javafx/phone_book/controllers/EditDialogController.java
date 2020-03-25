package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.commands.Command;
import com.kirilo.javafx.phone_book.commands.SaveCommand;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDialogController implements Controller, Initializable {
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
    private ResourceBundle resources;

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
        executeCommand(new SaveCommand(mainController));
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public ResourceBundle getResources() {
        return resources;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.resources = resources;
    }
}
