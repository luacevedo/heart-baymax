package com.luacevedo.heartbaymax.helpers;

public class TranslationsHelper {

    public static final String TRUE = "Si";
    public static final String FALSE = "No";

    public static String translateBooleanValue(boolean value) {
        return value ? TRUE : FALSE;
    }

    public static String translateDyspnoeaValue(String value) {
        String translatedValue = value;
        switch (value) {
            case "0":
                translatedValue = "No presenta";
                break;
            case "1":
                translatedValue = "Tipo 1";
                break;
            case "2":
                translatedValue = "Tipo 2";
                break;
            case "3":
                translatedValue = "Tipo 3";
                break;
            case "4":
                translatedValue = "Tipo 4";
                break;
        }
        return translatedValue;
    }
}
