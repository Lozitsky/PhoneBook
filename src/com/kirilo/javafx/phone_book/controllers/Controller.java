package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.commands.Command;

public interface Controller {
    void executeCommand(Command command);
}
