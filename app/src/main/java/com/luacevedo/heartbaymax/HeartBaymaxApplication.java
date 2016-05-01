package com.luacevedo.heartbaymax;

import android.app.Application;

import com.luacevedo.heartbaymax.api.HeartBaymaxApi;
import com.luacevedo.heartbaymax.db.CachingDbHelper;

public class HeartBaymaxApplication extends Application {

    private static HeartBaymaxApplication application;
    private CachingDbHelper cachingDbHelper;
    private HeartBaymaxApi heartBaymaxApi;

    @Override
    public void onCreate() {
        super.onCreate();
        this.application = this;
        this.heartBaymaxApi = new HeartBaymaxApi();
        this.cachingDbHelper = new CachingDbHelper(getApplicationContext());
    }

    public static HeartBaymaxApplication getApplication() {
        return application;
    }

    public CachingDbHelper getCachingDbHelper() {
        return cachingDbHelper;
    }

    public HeartBaymaxApi getHeartBaymaxApi() {
        return heartBaymaxApi;
    }
}