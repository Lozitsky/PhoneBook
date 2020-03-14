package com.kirilo.javafx.phone_book.commands;

import javafx.scene.Node;
import javafx.stage.Stage;

public class CancelCommand extends AbstractCommand {
    public CancelCommand(Node node) {
        super(node);
    }

    @Override
    public boolean execute() {
        Stage stage = (Stage) getNode().getScene().getWindow();
        stage.close();
        return true;
    }
}
