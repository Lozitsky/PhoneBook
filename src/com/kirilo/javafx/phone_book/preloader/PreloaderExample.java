package com.kirilo.javafx.phone_book.preloader;

import com.kirilo.javafx.phone_book.controllers.PreloaderController;
import com.kirilo.javafx.phone_book.utils.ObservableResourceFactory;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;


public class PreloaderExample extends Preloader {
    public static final String PRELOADER_FXML = "../fxml/preloader.fxml";
    private static final String LOCALE_BUNDLE = "com/kirilo/javafx/phone_book/bundles/Locale";
    private static final String STYLESHEET = PreloaderExample.class.getResource("/resources/css/dialog.css").toExternalForm();
    private ObservableResourceFactory RESOURCE_FACTORY;

    private Stage primaryStage;

    private ProgressBar progressBar;
    private boolean noLoadingProgress = true;

    private Scene createPreloadScene() {
        RESOURCE_FACTORY = ObservableResourceFactory.getInstance(ResourceBundle.getBundle(LOCALE_BUNDLE, new Locale("uk")));

        Label label = new Label();
        label.textProperty().bind(RESOURCE_FACTORY.getStringBinding("wait"));
        VBox loading = new VBox(10, label);
        loading.setMaxWidth(Region.USE_PREF_SIZE);
        loading.setMaxHeight(Region.USE_PREF_SIZE);

        progressBar = new ProgressBar();
        progressBar.setMinWidth(300);
        loading.getChildren().add(progressBar);
        loading.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(loading);
        root.getStylesheets().add(STYLESHEET);
        return new Scene(root, 350, 100);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        RESOURCE_FACTORY = ObservableResourceFactory.getInstance(ResourceBundle.getBundle(LOCALE_BUNDLE, new Locale("uk")));
        FXMLLoader fxmlLoader = new FXMLLoader();
        this.primaryStage = primaryStage;
        fxmlLoader.setResources(RESOURCE_FACTORY.getResource());

        primaryStage.initStyle(StageStyle.UNDECORATED);

        BorderPane borderPane = null;

        try (InputStream inputStream = getClass().getResourceAsStream(PRELOADER_FXML)) {
            borderPane = fxmlLoader.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PreloaderController preloaderController = fxmlLoader.getController();
        progressBar = preloaderController.getProgressBar();

//        primaryStage.setScene(createPreloadScene());
        Scene scene = new Scene(borderPane, 350, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        /*        if (info.getType() == Type.BEFORE_START) {
         *//*            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*//*
            primaryStage.hide();
        }*/
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if (info instanceof ProgressNotification) {
            double progress = ((ProgressNotification) info).getProgress();
            if (!noLoadingProgress) {
                progress = 0.5 + progress / 2;
            }
            progressBar.setProgress(progress);
        } else if (info instanceof StateChangeNotification) {
            primaryStage.hide();
        }
    }

    @Override
    public void handleProgressNotification(ProgressNotification info) {
        if (info.getProgress() != 1.0 || !noLoadingProgress) {
            progressBar.setProgress(info.getProgress() / 2);
            if (info.getProgress() > 0) {
                noLoadingProgress = false;
            }
        }
    }

}
