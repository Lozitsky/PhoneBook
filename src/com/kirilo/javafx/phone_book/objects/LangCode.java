package com.kirilo.javafx.phone_book.objects;

public enum LangCode {
    en("en"),
    uk("uk"),
    ru("ru");

    public String getCode() {
        return code;
    }

    private String code;

    LangCode(String code) {

        this.code = code;
    }
}
