package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.objects.Person;
import javafx.scene.control.TableView;

public class DelTableViewCommand extends AbstractTableViewCommand {
    public DelTableViewCommand(TableView<Person> tableView) {
        super(tableView);
    }

    @Override
    public boolean execute() {
        System.out.print("delete ");
        return true;
    }
}
