package com.luacevedo.heartbaymax.helpers;

import android.util.Log;

import com.luacevedo.heartbaymax.BuildConfig;

public class LogInternal {

    private static final String TAG = "Heart-Baymax";
    private static boolean LOG_BASE_API_CALLS = true;

    public static boolean isDebugging() {
        return BuildConfig.DEBUG;
    }

    public static void logBaseApiCall(String eventMessage, String parametersMessage) {
        if (LOG_BASE_API_CALLS) {
            dualColumnLog("BaseApi: " + eventMessage, parametersMessage, Log.DEBUG);
        }
    }

    private static void dualColumnLog(String leftColumnMessage, String rightColumnMessage, int iType) {
        log(String.format("%-40s %s", leftColumnMessage, rightColumnMessage), iType);
    }

    public static void log(String sMessage, int iType) {
        if (!isDebugging()) {
            return;
        }
        switch (iType) {
            case Log.ERROR:
                Log.e(LogInternal.TAG, "" + sMessage);
                break;
            case Log.DEBUG:
                Log.d(LogInternal.TAG, "" + sMessage);
                break;
            case Log.INFO:
                Log.i(LogInternal.TAG, "" + sMessage);
                break;
            case Log.VERBOSE:
                Log.v(LogInternal.TAG, "" + sMessage);
                break;
            case Log.WARN:
                Log.w(LogInternal.TAG, "" + sMessage);
                break;
        }

    }

}
