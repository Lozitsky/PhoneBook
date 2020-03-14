package com.kirilo.javafx.phone_book.commands;

import com.kirilo.javafx.phone_book.objects.Person;
import javafx.scene.control.TableView;

public abstract class AbstractTableViewCommand extends AbstractCommand {
    private TableView<Person> tableView;

    public TableView<Person> getTableView() {
        return tableView;
    }

    public AbstractTableViewCommand(TableView<Person> tableView) {
        super(tableView);
        this.tableView = tableView;
    }
}
