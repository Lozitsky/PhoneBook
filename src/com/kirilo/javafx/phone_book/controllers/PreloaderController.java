package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.utils.ObservableResourceFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class PreloaderController implements Initializable {
    private static final String LOCALE_BUNDLE = "com/kirilo.javafx.phone_book.bundles.Locale";

    private static ObservableResourceFactory RESOURCE_FACTORY;
    @FXML
    ProgressBar progressBar;
    @FXML
    private VBox vbox;
    @FXML
    private Label labelWait;

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
/*        RESOURCE_FACTORY = ObservableResourceFactory.getInstance(ResourceBundle.getBundle(LOCALE_BUNDLE, new Locale("uk")));
        labelWait.textProperty().bind(RESOURCE_FACTORY.getStringBinding("wait"));*/
    }
}
