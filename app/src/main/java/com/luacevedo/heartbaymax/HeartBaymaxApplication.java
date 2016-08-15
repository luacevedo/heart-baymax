package com.luacevedo.heartbaymax;

import android.app.Application;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

import com.luacevedo.heartbaymax.api.HeartBaymaxApi;
import com.luacevedo.heartbaymax.db.cache.CachingDbHelper;

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

    public static void hideKeyboard(IBinder token) {
        InputMethodManager imm = (InputMethodManager) application.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(token, 0);
    }
}