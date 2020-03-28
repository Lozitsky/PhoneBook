package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.commands.*;
import com.kirilo.javafx.phone_book.interfaces.AddressBook;
import com.kirilo.javafx.phone_book.interfaces.impls.CollectionAddressBook;
import com.kirilo.javafx.phone_book.objects.Person;
import com.kirilo.javafx.phone_book.utils.DataUtil;
import com.kirilo.javafx.phone_book.utils.ObservableResourceFactory;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

//https://stackoverflow.com/questions/55696100/javafx-change-node-generated-by-fxml-to-another-node
public class MainController implements Controller, Initializable {
    private static final String LOCALE = "com/kirilo.javafx.phone_book.bundles.Locale";
    private static final Locale UK = new Locale("uk");
    private static final String EDIT_RESOURCE = "../fxml/edit.fxml";
    private ObservableResourceFactory RESOURCE_FACTORY;
    private AddressBook addressBook;
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader;
    private EditDialogController editDialogController;
    @FXML
    private ComboBox<Locale> comboLocales;
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
    private Stage editStage;
    private Stage mainStage;
    private CancelCommand cancelCommand;
    private Command addCommand;
    private Command editCommand;
    private Command delCommand;
    private Command searchCommand;
    private FilteredList<Person> filteredList;

    public TableView<Person> getTableView() {
        return tableView;
    }

    public TextField getTextField() {

        return textField;
    }

    public AddressBook getAddressBook() {
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
            editStage.titleProperty().bind(RESOURCE_FACTORY.getStringBinding("editing_note"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RESOURCE_FACTORY =
                ObservableResourceFactory.getInstance(ResourceBundle.getBundle(LOCALE, UK));

        columnFullName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        columnFullName.textProperty().bind(RESOURCE_FACTORY.getStringBinding("full_name"));
        columnPhone.setCellValueFactory(param -> param.getValue().getPhoneProperty());
        columnPhone.textProperty().bind(RESOURCE_FACTORY.getStringBinding("phone"));

        fillLangComboBox();

        initListeners();

        initLoader();

        initCommands();
    }

    private void fillLangComboBox() {
        comboLocales.getItems().addAll(UK, Locale.ENGLISH, new Locale("ru"));
        comboLocales.setValue(UK);
        comboLocales.setCellFactory(param -> new LocaleCell());
        comboLocales.setButtonCell(new LocaleCell());
        comboLocales.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RESOURCE_FACTORY.setResource(ResourceBundle.getBundle(LOCALE, newValue));
            }
        });
    }

    private ObservableList<Person> getSortedLists(ObservableList<Person> observableList) {
        SortedList<Person> sortedList = new SortedList<>(observableList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        return sortedList;
    }

    //    not used. Another way is used that extends the TextField class and created a static factory method.
    private void setClearButtonField(CustomTextField customTextField) {
        try {
            Method method = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            method.setAccessible(true);
            method.invoke(null, customTextField, customTextField.rightProperty());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public FilteredList<Person> getFilteredList() {
        return filteredList;
    }

    private void initListeners() {
        addressBook = new CollectionAddressBook();
        ObservableList<Person> personList = addressBook.findAll();

        filteredList = new FilteredList(personList, person -> true);
        filteredList.addListener((ListChangeListener<? super Person>) changingList -> updateCountLabel());

        DataUtil.fillTestData(personList);

        tableView.setItems(getSortedLists(filteredList));

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)) {
                executeCommand(editCommand);
            }
        });
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

//        filtering without button
//        https://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
//        DataUtil.getFilteringField(textField, filteredList);
    }

    private void initLoader() {
        fxmlLoader = new FXMLLoader();

        fxmlLoader.setResources(RESOURCE_FACTORY.getResource());

        try (InputStream inputStream = getClass().getResourceAsStream(EDIT_RESOURCE)) {
            fxmlEdit = fxmlLoader.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editDialogController = fxmlLoader.getController();
        editDialogController.setMainController(this);
    }

    private void initCommands() {
        RESOURCE_FACTORY.setText(buttonAdd, "add");
        RESOURCE_FACTORY.setText(buttonDel, "del");
        RESOURCE_FACTORY.setText(buttonEdit, "edit");
        RESOURCE_FACTORY.setText(buttonSearch, "search");

        addCommand = new AddCommand(this);
        editCommand = new EditCommand(this);
        cancelCommand = new CancelCommand(this);
        delCommand = new DelCommand(this);
        searchCommand = new SearchCommand(this);
    }

    private void updateCountLabel() {
        labelCounts.textProperty().bind((RESOURCE_FACTORY.getStringBinding("count_of_notes")).concat(": " + filteredList.size()));
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

    public void searchPerson(ActionEvent actionEvent) {
        executeCommand(searchCommand);
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

    public static class LocaleCell extends ListCell<Locale> {
        @Override
        protected void updateItem(Locale locale, boolean empty) {
            super.updateItem(locale, empty);
            if (empty) {
                setText(null);
            } else {
                setText(locale.getDisplayLanguage(locale));
            }
        }
    }

}
