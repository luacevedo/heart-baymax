package com.luacevedo.heartbaymax.db;

import com.luacevedo.heartbaymax.helpers.DateUtils;

public class DbItem<T> {

    private T object;
    private long date;

    public DbItem(T object, long date) {
        this.object = object;
        this.date = date;
    }

    public T getObject() {
        return object;
    }

    public boolean isExpired(long ttl) {
        int seconds = DateUtils.differenceInSeconds(date);
        return seconds > ttl;
    }

}
