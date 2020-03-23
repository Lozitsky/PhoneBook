package com.kirilo.javafx.phone_book;

import com.kirilo.javafx.phone_book.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("fxml/phone_book.fxml"));

        Parent parent = null;
        fxmlLoader.setResources(ResourceBundle.getBundle("com/kirilo.javafx.phone_book.bundles.Locale", new Locale("uk")));

        try (InputStream inputStream = getClass().getResourceAsStream("fxml/phone_book.fxml")) {

            fxmlLoader.setClassLoader(MainController.class.getClassLoader());

            parent = fxmlLoader.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        primaryStage.setTitle("Address Book");
        primaryStage.setTitle(fxmlLoader.getResources().getString("address_book"));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(460);
        primaryStage.setScene(new Scene(parent, 460, 475));
        primaryStage.show();

        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);
    }
}
