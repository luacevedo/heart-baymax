package com.luacevedo.heartbaymax.helpers;

import com.luacevedo.heartbaymax.Constants;

public class TranslationsHelper {

    public static final String TRUE = "Si";
    public static final String FALSE = "No";

    public static String translateBooleanValue(boolean value) {
        return value ? TRUE : FALSE;
    }
}
