package com.kirilo.javafx.phone_book.commands;

import javafx.scene.Node;

public abstract class AbstractCommand implements Command {
    private Node node;

    public Node getNode() {
        return node;
    }

    public AbstractCommand(Node node) {
        this.node = node;
    }
}
