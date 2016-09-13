package com.luacevedo.heartbaymax.helpers;

import com.luacevedo.heartbaymax.HeartBaymaxApplication;

public class ResourcesHelper {

    public static String getString(int resource) {
        return HeartBaymaxApplication.getApplication().getString(resource);
    }
}
