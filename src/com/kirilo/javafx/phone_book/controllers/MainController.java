package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.commands.*;
import com.kirilo.javafx.phone_book.interfaces.impls.CollectionAddressBook;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController implements Controller {
    private CollectionAddressBook addressBook;
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader;
    private EditDialogController editDialogController;
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
    private CancelCommand cancelCommand;
    private Stage editStage;
    private Stage mainStage;
    private AddCommand addCommand;
    private EditCommand editCommand;
    private DelCommand delCommand;

    public Stage getEditStage() {
        return editStage;
    }

    public CollectionAddressBook getAddressBook() {
        return addressBook;
    }

    public EditDialogController getEditDialogController() {
        return editDialogController;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void showDialog() {
        if (editStage == null) {
            editStage = new Stage();
            editStage.setTitle("Editing note");
            editStage.setMinHeight(150);
            editStage.setMinWidth(300);
            editStage.setResizable(false);
            editStage.setScene(new Scene(fxmlEdit));
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(mainStage);
        }
        editStage.showAndWait();
    }

    public Parent getFxmlEdit() {
        return fxmlEdit;
    }

    public CancelCommand getCancelCommand() {
        return cancelCommand;
    }

    @FXML
    private void initialize() {
        columnFullName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        columnPhone.setCellValueFactory(param -> param.getValue().getPhoneProperty());

        addressBook = new CollectionAddressBook();
        ObservableList<Person> personList = addressBook.getPersonList();
        personList.addListener((ListChangeListener<? super Person>) change -> updateCountLabel());

        addressBook.fillTestData();
        tableView.setItems(personList);
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)) {
                executeCommand(editCommand);
            }
        });
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        try {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlEdit = fxmlLoader.<Parent>load();
            editDialogController = fxmlLoader.<EditDialogController>getController();
            editDialogController.setMainController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addCommand = new AddCommand(this);
        editCommand = new EditCommand(this);
        cancelCommand = new CancelCommand(this);
        delCommand = new DelCommand(this);
    }

    private void updateCountLabel() {
        labelCounts.setText("Counts of notes: " + addressBook.getPersonList().size());
    }

    public void addPerson(ActionEvent actionEvent) {
        executeCommand(addCommand);
    }

    public void editPerson(ActionEvent actionEvent) {
        executeCommand(editCommand);
    }

    public void delPerson(ActionEvent actionEvent) {
        executeCommand(delCommand);
    }

    @Override
    public void executeCommand(Command command) {
        Person selectedPerson = getSelectedPerson();
        if (command.execute()) {
            System.out.println(selectedPerson);
        }
    }

    public Person getSelectedPerson() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public ObservableList<Person> getSelectedPersons() {
        return tableView.getSelectionModel().getSelectedItems();
    }
}
