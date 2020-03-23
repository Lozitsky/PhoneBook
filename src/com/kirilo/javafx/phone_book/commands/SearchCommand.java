package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.controllers.MainController;

public class SearchCommand extends AbstractControllerCommand {
    private MainController controller;

    public SearchCommand(MainController controller) {
        super(controller);
        this.controller = controller;
        resetFilter();
    }

    private void resetFilter() {
        controller.getFilteredList().setPredicate(person -> true);
    }

    @Override
    public boolean execute() {
        String text = controller.getTextField().getText();
        controller.getFilteredList().setPredicate(person -> {
            if (text == null || text.isEmpty()) {
                return true;
            }
            String lowerCase = text.toLowerCase();
            return person.getFullName().toLowerCase().contains(lowerCase) ||
                    person.getPhone().toLowerCase().contains(lowerCase);
        });
        return false;
    }
}
