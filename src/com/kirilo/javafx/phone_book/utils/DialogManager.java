package com.kirilo.javafx.phone_book.utils;

import com.kirilo.javafx.phone_book.objects.model.Person;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

// https://dgit.cs.uni-saarland.de/pseuco/pseuco-ide/-/commit/dd9dd246114617b68aa444320a8a4f2368b8caf6
public class DialogManager {
    private static final String LOCALE = "com/kirilo/javafx/phone_book/bundles/Locale";
    private static ObservableResourceFactory resourceFactory = ObservableResourceFactory.getInstance(ResourceBundle.getBundle(LOCALE, new Locale("uk")));
    private static final String STYLESHEET = DialogManager.class.getResource("/resources/css/dialog.css").toExternalForm();
    private static Label icon;
    private static Alert alert;

    private static void baseDialog(String title, String text, AlertType information) {
        DialogManager.alert = new Alert(information);
        Alert alert = DialogManager.alert;
        alert.getDialogPane().getStylesheets().add(STYLESHEET);
        alert.titleProperty().bind(resourceFactory.getStringBinding(title));
        alert.contentTextProperty().bind(resourceFactory.getStringBinding(text));
        alert.setHeaderText("");
//        alert.setGraphic(icon);
        alert.showAndWait();
    }

    public static void showInfoDialog(String title, String text) {
        icon = new Label (" ");
        icon.getStyleClass().add("info-dialog");

        baseDialog(title, text, INFORMATION);
    }

    public static void showErrorDialog(String title, String text) {
        baseDialog(title, text, ERROR);
    }

    public static boolean isSelected(Object selectedObject, String title, String text) {
        return checkWithPredicate(title, text, selectedObject == null || selectedObject.toString().equals("[]"));
    }

    public static boolean checkValues(Person person, String title, String text) {
        return checkWithPredicate(title, text, person.getFullName().trim().length() == 0 || person.getPhone().trim().length() == 0);
    }

    public static boolean checkValues(String title, String text, String... fields) {
        for (String field : fields) {
            if (!checkWithPredicate(title, text, field.trim().length() == 0)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkWithPredicate(String title, String text, boolean predicate) {
        if (predicate) {
            showInfoDialog(title, text);
            return false;
        }
        return true;
    }
}
