package com.luacevedo.heartbaymax.helpers;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

public final class BundleHelper {
    private static Gson gson = newGSONInstance();

    private BundleHelper() {
    }

    private static Gson newGSONInstance() {
        GsonBuilder builder = new GsonBuilder();
//        new GraphAdapterBuilder()
//                .addType(Category.class)
//                .registerOn(builder);
//        builder.registerTypeAdapter(IItem.class, new IItem.IItemDataAdapter());
        builder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if (f.getAnnotation(ExcludedFromBundleSerialization.class) != null) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                if (clazz.getAnnotation(ExcludedFromBundleSerialization.class) != null) {
                    return true;
                }
                return false;
            }
        });
        return builder.create();
    }

    public static boolean bundleContains(Bundle bundle, Class<? extends Serializable> aClass) {
        return bundle != null && bundle.containsKey(aClass.getCanonicalName());
    }

    public static boolean bundleContains(Bundle bundle, String key) {
        return bundle != null && bundle.containsKey(key);
    }

    public static <T> T fromBundle(Bundle bundle, String key) {
        return fromBundle(bundle, key, (T) null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromBundle(Bundle bundle, String key, T defaultValue) {
        if (bundle == null) {
            return defaultValue;
        }
        return bundleContains(bundle, key) ? (T) bundle.get(key) : defaultValue;
    }

    public static <T> T fromBundleJson(Intent intent, String key, Type type, T defaultValue) {
        if (intent == null) {
            return defaultValue;
        }
        return fromBundleJson(intent.getExtras(), key, type, defaultValue);
    }

    public static <T> T fromBundleJson(Bundle bundle, String key, Type type, T defaultValue) {
        if (bundle == null) {
            return defaultValue;
        }
        return bundleContains(bundle, key) ? (T) gson.fromJson(bundle.getString(key), type) : defaultValue;
    }

    public static void putJsonBundle(Bundle bundle, String key, Object value) {
        bundle.putString(key, gson.toJson(value));
    }

    public static void putJsonBundle(Bundle bundle, String key, Object value, Type type) {
        bundle.putString(key, gson.toJson(value, type));
    }

    public static void putJsonBundle(Intent intent, String key, Object value) {
        intent.putExtra(key, gson.toJson(value));
    }

    public static void putJsonBundle(Intent intent, String key, Object value, Type type) {
        intent.putExtra(key, gson.toJson(value, type));
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})
    public @interface ExcludedFromBundleSerialization {
    }
}
