package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.commands.CancelCommand;
import com.kirilo.javafx.phone_book.commands.Command;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditDialogControl implements Control {
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldPhone;
    @FXML
    public Button buttonOk;
    @FXML
    private Button buttonCancel;

    public void actionClose(ActionEvent actionEvent) {
        executeCommand(new CancelCommand((Node) actionEvent.getSource()));
    }



    @Override
    public void executeCommand(Command command) {
        if (command.execute()) {
        }
    }
}
