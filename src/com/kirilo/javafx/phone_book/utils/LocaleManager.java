package com.kirilo.javafx.phone_book.utils;

import com.kirilo.javafx.phone_book.objects.Lang;
import com.kirilo.javafx.phone_book.objects.LangCode;

import java.util.EnumMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {
    private static Lang currentLang;
    private static EnumMap<LangCode, Lang> enumMap = new EnumMap<>(LangCode.class);

    public static Lang getCurrentLang() {
        return currentLang;
    }

    public static void setCurrentLang(Lang currentLang) {
        LocaleManager.currentLang = currentLang;
    }

    public static void setCurrentLang(LangCode langCode) {
        currentLang = enumMap.get(langCode);
    }

    public static Lang getLang(int number, LangCode langCode, ResourceBundle resources) {
        if (!enumMap.containsKey(langCode)) {
            String code = langCode.getCode();

            System.out.println("code: " + code);
            String string = resources.getString(code);
            Locale locale = new Locale(langCode.getCode());
            enumMap.put(langCode, new Lang(code, string, locale, number));
        }
        return enumMap.get(langCode);
    }

    public static Lang getLang(LangCode langCode) {
        System.out.println(langCode);
        return enumMap.get(langCode);
    }


}
