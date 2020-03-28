package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.Controller;

public abstract class AbstractControllerCommand implements Command {

    private Controller controller;

    public AbstractControllerCommand(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }
}
