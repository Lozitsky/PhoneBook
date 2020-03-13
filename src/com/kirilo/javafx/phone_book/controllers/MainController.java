package com.kirilo.javafx.phone_book.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public Button buttonAdd;
    public Button buttonEdit;
    public Button buttonDel;
    public Label LabelCounts;
    public TextField textField;
    public Button buttonSearch;
    public TableView tableView;

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
}
