package com.luacevedo.heartbaymax.helpers;

public class DateUtils {

    public static int differenceInSeconds(long date) {
        return differenceInSeconds(System.currentTimeMillis(), date);
    }

    public static int differenceInSeconds(long date1, long date2) {
        long diff = Math.abs(date1 - date2);
        diff = diff / (1000);
        return (int) diff;
    }

}
