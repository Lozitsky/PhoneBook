package com.kirilo.javafx.phone_book;

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
        Parent root = FXMLLoader.load(getClass().getResource("fxml/phone_book.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(460);
        primaryStage.setScene(new Scene(root, 460, 475));
        primaryStage.show();
    }
}
