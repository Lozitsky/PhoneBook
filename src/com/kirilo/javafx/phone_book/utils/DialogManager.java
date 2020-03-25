package com.kirilo.javafx.phone_book.utils;

import com.kirilo.javafx.phone_book.objects.Person;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class DialogManager {
    private static void baseDialog(String title, String text, AlertType information) {
        Alert alert = new Alert(information);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }

    public static void showInfoDialog(String title, String text) {
        baseDialog(title, text, INFORMATION);
    }

    public static void showErrorDialog(String title, String text) {
        baseDialog(title, text, ERROR);
    }

    public static boolean isSelected(Object selectedObject, String title, String text) {
//        System.out.println(selectedObject.toString().equals("[]"));
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
