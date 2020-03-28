package com.kirilo.javafx.phone_book.objects;

import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

// https://stackoverflow.com/questions/55696100/javafx-change-node-generated-by-fxml-to-another-node
public class ClearableTextField extends TextField {
    public static TextField getTextField() {
        return TextFields.createClearableTextField();
    }
}
