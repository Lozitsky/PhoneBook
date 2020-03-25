package com.kirilo.javafx.phone_book;

import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.Lang;
import com.kirilo.javafx.phone_book.utils.LocaleManager;
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

import static com.kirilo.javafx.phone_book.objects.LangCode.*;


public class Main extends Application implements Observer {

    private static final String PHONE_BOOK = "fxml/phone_book.fxml";
    private static final String LOCALE_BUNDLE = "com/kirilo.javafx.phone_book.bundles.Locale";
    private Stage primaryStage;
    private VBox parent;
    private ResourceBundle resources;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

//        loadTreeFromFXML(new Locale(uk.getCode()));
//        LocaleManager.setCurrentLang();

        createGUI(new Locale(uk.getCode()));

    }

    private void createGUI(Locale locale) {

        parent = loadTreeFromFXML(locale);

//        LocaleManager.getLang(0, uk, resources);
//        LocaleManager.getLang(1, en, resources);
//        LocaleManager.getLang(2, ru, resources);
//        LocaleManager.setCurrentLang(uk);

        primaryStage.setScene(new Scene(parent, 460, 475));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(460);
        primaryStage.show();
    }

    private VBox loadTreeFromFXML(Locale locale) {
        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("fxml/phone_book.fxml"));

//        fxmlLoader.setResources(ResourceBundle.getBundle(LOCALE_BUNDLE, new Locale("uk")));
        fxmlLoader.setResources(ResourceBundle.getBundle(LOCALE_BUNDLE, locale));
        this.resources = fxmlLoader.getResources();

        LocaleManager.getLang(0, uk, resources);
        LocaleManager.getLang(1, en, resources);
        LocaleManager.getLang(2, ru, resources);
        if (LocaleManager.getCurrentLang() == null) {
            LocaleManager.setCurrentLang(uk);
        }

        VBox vBox = null;

        try (InputStream inputStream = getClass().getResourceAsStream(PHONE_BOOK)) {

//            fxmlLoader.setClassLoader(MainController.class.getClassLoader());

            vBox = fxmlLoader.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);
        mainController.addObserver(this);
        primaryStage.setTitle(fxmlLoader.getResources().getString("address_book"));
        return vBox;
    }

    @Override
    public void update(Observable o, Object arg) {
        Lang lang = (Lang) arg;
        System.out.println(lang.getLocale());
        VBox newVBox = (VBox) loadTreeFromFXML(lang.getLocale());

        parent.getChildren().setAll(newVBox.getChildren());
    }
}