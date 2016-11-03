package com.luacevedo.heartbaymax.helpers;

import android.support.annotation.ColorInt;

import com.luacevedo.heartbaymax.HeartBaymaxApplication;

public class ResourcesHelper {

    public static String getString(int resource) {
        return HeartBaymaxApplication.getApplication().getString(resource);
    }

    public static @ColorInt int getColor(int resource) {
        return HeartBaymaxApplication.getApplication().getResources().getColor(resource);
    }
}
