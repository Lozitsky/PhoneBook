package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.objects.Person;
import javafx.scene.control.TableView;

public class AddTableViewCommand extends AbstractTableViewCommand {
    public AddTableViewCommand(TableView<Person> tableView) {
        super(tableView);
    }

    @Override
    public boolean execute() {
        System.out.print("add ");
        return true;
    }
}
