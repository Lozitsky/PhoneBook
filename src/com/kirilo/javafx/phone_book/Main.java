package com.kirilo.javafx.phone_book;

import com.kirilo.javafx.phone_book.interfaces.impls.CollectionAddressBook;
import com.kirilo.javafx.phone_book.objects.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static void testData() {
        CollectionAddressBook addressBook = new CollectionAddressBook();

        Person person1 = new Person();
        person1.setFullName("Full Name1");
        person1.setPhone("0639876543");
        Person person2 = new Person();
        person2.setFullName("Full Name2");
        person2.setPhone("0501235489");

        System.out.println("Added two persons into collections:");

        addressBook.add(person1);
        addressBook.add(person2);
        addressBook.getPersonList().forEach(System.out::println);

        addressBook.delete(person1);
        System.out.println("After deleting person1:");

        addressBook.getPersonList().forEach(System.out::println);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/phone_book.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(460);
        primaryStage.setScene(new Scene(root, 460, 475));
        primaryStage.show();

        testData();
    }
}
