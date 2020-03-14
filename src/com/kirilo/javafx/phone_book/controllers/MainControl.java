package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.commands.AddTableViewCommand;
import com.kirilo.javafx.phone_book.commands.Command;
import com.kirilo.javafx.phone_book.commands.DelTableViewCommand;
import com.kirilo.javafx.phone_book.commands.EditTableViewCommand;
import com.kirilo.javafx.phone_book.interfaces.impls.CollectionAddressBook;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainControl implements Control {
    CollectionAddressBook addressBook;

    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDel;
    @FXML
    private Label labelCounts;
    @FXML
    private TextField textField;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> columnFullName;
    @FXML
    private TableColumn<Person, String> columnPhone;

    public void showDialog(ActionEvent actionEvent) {
        try {
//            chgName.setText("clicked!");
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("../fxml/edit.fxml"));
            stage.setTitle("Editing note");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
/*        columnFullName.setCellValueFactory(new PropertyValueFactory<Person, String>("fullName"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));*/
        columnFullName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        columnPhone.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        addressBook = new CollectionAddressBook();
        tableView.setItems(addressBook.getPersonList());
        updateCountLabel();
    }

    private void updateCountLabel() {
        labelCounts.setText("Counts of notes: " + addressBook.getPersonList().size());
    }

    public void addPerson(ActionEvent actionEvent) {
        executeCommand(new AddTableViewCommand(tableView));
        showDialog(actionEvent);
    }

    public void editPerson(ActionEvent actionEvent) {
        executeCommand(new EditTableViewCommand(tableView));
        showDialog(actionEvent);
    }

    public void delPerson(ActionEvent actionEvent) {
        executeCommand(new DelTableViewCommand(tableView));
    }

    @Override
    public void executeCommand(Command command) {
        Person person = tableView.getSelectionModel().getSelectedItem();
        if (command.execute()) {
            System.out.println(person);
        }
    }
}
