package com.kirilo.javafx.phone_book;

import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Lang;
import com.kirilo.javafx.phone_book.utils.ObservableResourceFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;


public class Main extends Application implements Observer {

    private static final String PHONE_BOOK = "fxml/phone_book.fxml";
    private static final String LOCALE_BUNDLE = "com/kirilo/javafx/phone_book/bundles/Locale";
    private ObservableResourceFactory RESOURCE_FACTORY;

    private Stage primaryStage;
    private VBox parent;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
//        createGUI(new Locale(uk.getCode()));
        createGUI(new Locale("uk"));
    }

    private void createGUI(Locale locale) {
        parent = loadTreeFromFXML(locale);
        primaryStage.setScene(new Scene(parent, 460, 475));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(460);
        primaryStage.show();
    }

    private VBox loadTreeFromFXML(Locale locale) {
        FXMLLoader fxmlLoader = changeLocale(locale);

        VBox vBox = null;

        try (InputStream inputStream = getClass().getResourceAsStream(PHONE_BOOK)) {
            vBox = fxmlLoader.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);
        primaryStage.titleProperty().bind(RESOURCE_FACTORY.getStringBinding("address_book"));
        return vBox;
    }

    private FXMLLoader changeLocale(Locale locale) {
        FXMLLoader fxmlLoader = new FXMLLoader();

        RESOURCE_FACTORY = ObservableResourceFactory.getInstance(ResourceBundle.getBundle(LOCALE_BUNDLE, locale));
        fxmlLoader.setResources(RESOURCE_FACTORY.getResource());

        return fxmlLoader;
    }

    @Override
    public void update(Observable o, Object arg) {
        Lang lang = (Lang) arg;
        VBox newVBox = loadTreeFromFXML(lang.getLocale());
        parent.getChildren().setAll(newVBox.getChildren());
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }
}