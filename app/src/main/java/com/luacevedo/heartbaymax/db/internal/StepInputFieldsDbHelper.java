package com.luacevedo.heartbaymax.db.internal;

import android.content.Context;

public class StepInputFieldsDbHelper extends InternalDbTemplate {
    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "heartBaymaxDatabase3";
    private final static String TABLE_NAME = "inputFields";
    private final static String COLUMN_KEY = "inputFieldKey";
    private final static String COLUMN_DATA = "inputFieldData";

    public StepInputFieldsDbHelper(Context context) {
        super(context, DB_NAME, DB_VERSION, TABLE_NAME, COLUMN_KEY, COLUMN_DATA);
    }
}
