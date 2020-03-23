package com.kirilo.javafx.phone_book.objects;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

public class ClearableTextField extends TextField {
    @FXML
    public static TextField getTextField() {
        return TextFields.createClearableTextField();
    }
}
