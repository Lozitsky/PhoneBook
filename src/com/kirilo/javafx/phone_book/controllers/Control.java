package com.kirilo.javafx.phone_book.controllers;

import com.kirilo.javafx.phone_book.commands.Command;

public interface Control {
    void executeCommand(Command command);
}
