package com.kirilo.javafx.phone_book.utils;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Labeled;

import java.util.ResourceBundle;

// https://stackoverflow.com/questions/32464974/javafx-change-application-language-on-the-run
public class ObservableResourceFactory {
    private static volatile ObservableResourceFactory instance;

    private ObjectProperty<ResourceBundle> resource;

    private ObservableResourceFactory(ResourceBundle resource) {
        this.resource = new SimpleObjectProperty<>(resource);
    }

    public static ObservableResourceFactory getInstance(ResourceBundle resource) {
        ObservableResourceFactory resultInstance = instance;
        if (resultInstance != null) {
            return resultInstance;
        }
        synchronized (ObservableResourceFactory.class) {
            if (instance == null) {
                instance = new ObservableResourceFactory(resource);
            }
            return instance;
        }
    }

    public ResourceBundle getResource() {
        return resource.get();
    }

    public void setResource(ResourceBundle resource) {
        this.resource.set(resource);
    }

    public ObjectProperty<ResourceBundle> resourceProperty() {
        return resource;
    }

    public StringBinding getStringBinding(String key) {
        return new StringBinding() {
            {
                bind(resource);
            }

            @Override
            protected String computeValue() {
                return getResource().getString(key);
            }
        };
    }

    public void setText(Labeled labeled, String text) {
        labeled.textProperty().bind(getStringBinding(text));
    }
}
