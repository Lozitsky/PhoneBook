package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.commands.Command;
import com.kirilo.javafx.phone_book.commands.SaveCommand;
import com.kirilo.javafx.phone_book.objects.model.Person;
import com.kirilo.javafx.phone_book.utils.ObservableResourceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditDialogController implements Controller, Initializable {
    private static final String LOCALE_BUNDLE = "com/kirilo.javafx.phone_book.bundles.Locale";

    private static ObservableResourceFactory RESOURCE_FACTORY;
    @FXML
    private Button buttonOk;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldPhone;
    @FXML
    private Button buttonCancel;
    @FXML
    private Label labelName;
    @FXML
    private Label labelPhone;

    private Person person;

    private MainController mainController;
    private boolean isAdd;

    public boolean isAdd() {
        return isAdd;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if (person == null) {
//            return;
            isAdd = true;
            person = new Person();
        } else {
            isAdd = false;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RESOURCE_FACTORY = ObservableResourceFactory.getInstance(ResourceBundle.getBundle(LOCALE_BUNDLE, new Locale("uk")));

        RESOURCE_FACTORY.setText(buttonCancel, "cancel");
        RESOURCE_FACTORY.setText(buttonOk, "ok");
        RESOURCE_FACTORY.setText(labelName, "full_name");
        RESOURCE_FACTORY.setText(labelPhone, "phone");
    }
}
