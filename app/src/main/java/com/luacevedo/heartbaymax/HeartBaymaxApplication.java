package com.luacevedo.heartbaymax;

import android.app.Application;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

import com.luacevedo.heartbaymax.api.MochiApi;
import com.luacevedo.heartbaymax.db.InternalDbHelper;
import com.luacevedo.heartbaymax.db.cache.CachingDbHelper;

public class HeartBaymaxApplication extends Application {

    private static HeartBaymaxApplication application;
    private CachingDbHelper cachingDbHelper;
    private MochiApi mochiApi;
    private InternalDbHelper internalDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        this.application = this;
        this.mochiApi = new MochiApi();
        this.cachingDbHelper = new CachingDbHelper(getApplicationContext());
        this.internalDbHelper = new InternalDbHelper(getApplicationContext());
    }

    public static HeartBaymaxApplication getApplication() {
        return application;
    }

    public CachingDbHelper getCachingDbHelper() {
        return cachingDbHelper;
    }

    public InternalDbHelper getInternalDbHelper() {
        return internalDbHelper;
    }

    public MochiApi getMochiApi() {
        return mochiApi;
    }

    public static void hideKeyboard(IBinder token) {
        InputMethodManager imm = (InputMethodManager) application.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(token, 0);
    }
}