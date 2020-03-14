package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.objects.Person;
import javafx.scene.control.TableView;

public class EditTableViewCommand extends AbstractTableViewCommand {
    public EditTableViewCommand(TableView<Person> tableView) {
        super(tableView);
    }

    @Override
    public boolean execute() {
        System.out.print("edit ");
        return true;
    }
}
