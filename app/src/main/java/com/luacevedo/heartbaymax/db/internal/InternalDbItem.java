package com.luacevedo.heartbaymax.db.internal;

import com.luacevedo.heartbaymax.helpers.DateUtils;

public class InternalDbItem<T> {

    private T object;

    public InternalDbItem(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

}
