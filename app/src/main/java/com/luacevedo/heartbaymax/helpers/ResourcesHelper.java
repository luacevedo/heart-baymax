package com.luacevedo.heartbaymax.helpers;

import android.support.annotation.ColorInt;
import android.util.TypedValue;

import com.luacevedo.heartbaymax.HeartBaymaxApplication;

public class ResourcesHelper {

    public static String getString(int resource) {
        return HeartBaymaxApplication.getApplication().getString(resource);
    }

    public static @ColorInt int getColor(int resource) {
        return HeartBaymaxApplication.getApplication().getResources().getColor(resource);
    }

    public static int getDimensionPixelSize(int resource) {
        HeartBaymaxApplication app = HeartBaymaxApplication.getApplication();
        TypedValue rawValue = new TypedValue();
        app.getResources().getValue(resource, rawValue, true);
        if (rawValue.type == TypedValue.TYPE_FIRST_INT) {
            return app.getResources().getInteger(resource);
        } else {
            return app.getResources().getDimensionPixelSize(resource);
        }
    }
}
