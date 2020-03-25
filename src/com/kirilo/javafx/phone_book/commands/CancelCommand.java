package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.MainController;

public class CancelCommand extends AbstractControllerCommand {

    private MainController controller;

    public CancelCommand(MainController controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public boolean execute() {
//        System.out.println("Cancel command");
        controller.getFxmlEdit().getScene().getWindow().hide();
        return true;
    }
}
