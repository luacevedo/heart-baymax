package com.luacevedo.heartbaymax.db.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.db.ExcludedFromDBSerialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class InternalDbTemplate {
    protected String tableName;
    protected String columnKey;
    protected String columnData;

    private SQLiteDatabase db = null;
    private final Gson gson = newGSONInstance();

    private static Gson newGSONInstance() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if (f.getAnnotation(ExcludedFromDBSerialization.class) != null) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                if (clazz.getAnnotation(ExcludedFromDBSerialization.class) != null) {
                    return true;
                }
                return false;
            }
        });
        return gsonBuilder.create();
    }

    public InternalDbTemplate(Context context, String dbName, int dbVersion,
                              String tableName, String columnKey, String columnData) {

        this.tableName = tableName;
        this.columnKey = columnKey;
        this.columnData = columnData;

        SQLiteOpenHelper helper = new DatabaseHelper(context, dbName, dbVersion);
        this.db = helper.getWritableDatabase();

    }

    public void close() {
        db.close();
        db = null;
    }


    public void insert(String key, Object data) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(columnKey, key);
            cv.put(columnData, gson.toJson(data));

            int rows = db.update(tableName, cv, columnKey + "=?", new String[]{key});
            if (rows == 0) {
                db.insert(tableName, columnKey, cv);
            }
        } catch (Throwable t) {
        }
    }

    public void delete(String key) {
        db.delete(tableName, columnKey + "=?", new String[]{key});
    }

    public void deleteAll(String key) {
        db.delete(tableName, columnKey + " LIKE ?", new String[]{key});
    }

    public <T> InternalDbItem<T> getInternalDbItem(String key, Type type) {
        if (key == null || type == null) {
            return null;
        }
        Cursor c = db.query(tableName, new String[]{columnKey, columnData},
                columnKey + "=?", new String[]{key}, null, null, null);
        InternalDbItem<T> response = null;
        if (c.moveToFirst()) {
            String json = Constants.EMPTY_STRING;
            try {
                json = c.getString(c.getColumnIndex(columnData));
                response = new InternalDbItem<>((T) gson.fromJson(json, type));
            } catch (JsonSyntaxException jse) {
//                LogInternal.error(String.format("Unable to parse Json: %s as %s", json, type.toString()));
            } catch (Throwable e) {
//                LogInternal.error("Unable to read row");
            }
        }
        c.close();
        return response;
    }


    public <T> List<T> getAllDataFromTable(Type clazz) {
        Cursor c = db.query(tableName, new String[]{columnKey, columnData}, null, null, null, null, null);
        List<T> responseList = new ArrayList<>();
        T response;
        if (c.moveToFirst()) {
            String json;
            try {
                if (c.moveToFirst()) {
                    do {
                        json = c.getString(c.getColumnIndex(columnData));
                        response = gson.fromJson(json, clazz);
                        responseList.add(response);
                    } while (c.moveToNext());
                }
            } catch (JsonSyntaxException jse) {
//                LogInternal..error(String.format("Unable to parse Json: %s as %s", json, clazz.toString()));
            } catch (Throwable e) {
//                LogInternal.error("Unable to read row");
            }
        }
        c.close();
        return responseList;
    }

    public void clearDb() {
        db.delete(tableName, null, null);
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String dbName, int dbVersion) {
            super(context, dbName, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT);",
                    tableName, columnKey, columnData));
            db.execSQL(String.format("CREATE INDEX %s ON %s (%s);",
                    "patient_key", tableName, columnKey));
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            onCreate(db);
        }
    }
}
