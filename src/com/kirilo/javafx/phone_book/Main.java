package com.kirilo.javafx.phone_book;

import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.interfaces.impls.CollectionAddressBook;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("fxml/phone_book.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/phone_book.fxml"));
        Parent parent = fxmlLoader.<Parent>load();
        MainController mainController = fxmlLoader.<MainController>getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("Address Book");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(460);
        primaryStage.setScene(new Scene(parent, 460, 475));
        primaryStage.show();
    }
}
