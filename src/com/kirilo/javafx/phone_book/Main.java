package com.kirilo.javafx.phone_book;

import com.kirilo.javafx.phone_book.controllers.MainController;
import com.kirilo.javafx.phone_book.objects.preloader.PreloaderExample;
import com.kirilo.javafx.phone_book.utils.ObservableResourceFactory;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.application.Preloader.ProgressNotification;
import static javafx.application.Preloader.StateChangeNotification;

// https://docs.oracle.com/javafx/2/deployment/preloaders.htm
public class Main extends Application {

    private static final String PHONE_BOOK = "fxml/phone_book.fxml";
    private static final String LOCALE_BUNDLE = "com/kirilo/javafx/phone_book/bundles/Locale";
    private ObservableResourceFactory RESOURCE_FACTORY;

//    private Stage primaryStage;
//    private VBox parent;

    BooleanProperty ready = new SimpleBooleanProperty(false);

    private void longStart() {
        //simulate long init in background
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 10;
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(200);
                    // Send progress to preloader
                    notifyPreloader(new ProgressNotification(((double) i)/max));
                }
                // After init is ready, the app is ready to be shown
                // Do this before hiding the preloader stage to prevent the
                // app from exiting prematurely
                ready.setValue(Boolean.TRUE);

                notifyPreloader(new StateChangeNotification(
                        StateChangeNotification.Type.BEFORE_START));

                return null;
            }
        };
        new Thread(task).start();
    }

    public static void main(String[] args) {
//        launch(args);
        LauncherImpl.launchApplication(Main.class, PreloaderExample.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        longStart();
//        this.primaryStage = primaryStage;
//        createGUI(new Locale(uk.getCode()));
        createGUI(primaryStage, new Locale("uk"));

        initPreloaderListener(primaryStage);
    }

    private void initPreloaderListener(Stage primaryStage) {
        ready.addListener(new ChangeListener<Boolean>(){
            public void changed(
                    ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (Boolean.TRUE.equals(t1)) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            primaryStage.show();
                        }
                    });
                }
            }
        });
    }

    private void createGUI(Stage primaryStage, Locale locale) {
        VBox parent = loadTreeFromFXML(primaryStage, locale);
        primaryStage.setScene(new Scene(parent, 460, 475));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(460);
//        primaryStage.show();
    }

    private VBox loadTreeFromFXML(Stage primaryStage, Locale locale) {
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

/*    @Override
    public void update(Observable o, Object arg) {
        Lang lang = (Lang) arg;
        VBox newVBox = loadTreeFromFXML(lang.getLocale());
        parent.getChildren().setAll(newVBox.getChildren());
    }*/

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }
}