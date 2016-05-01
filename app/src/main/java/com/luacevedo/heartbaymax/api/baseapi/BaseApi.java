package com.luacevedo.heartbaymax.api.baseapi;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.api.ExcludedFromAPISerialization;
import com.luacevedo.heartbaymax.helpers.LogInternal;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

public abstract class BaseApi<T> {

    private static final long DEFAULT_TIMEOUT = 10 * 1000;
    private static final long DEFAULT_CACHE_DIR_SIZE = 2048;

    private Cache cache;
    private Class<T> contract;
    protected T service;
    private Map<CallId, BaseApiCall> ongoingCalls = new HashMap<>();

    public BaseApi(String baseUrl, Class<T> contract) {
        this.contract = contract;
        initializeHttpCache(DEFAULT_CACHE_DIR_SIZE);
        setUrl(baseUrl);
    }

    private void initializeHttpCache(long dirSize) {
        String cacheDirectoryName = this.getClass().getSimpleName() + Constants.CACHE;
        File cacheDirectory = new File(HeartBaymaxApplication.getApplication().getCacheDir(), cacheDirectoryName);
        cache = new Cache(cacheDirectory, dirSize);
    }

    protected long getGeneralTimeout() {
        return DEFAULT_TIMEOUT;
    }

    public void setUrl(String baseUrl) {
        service = generateService(contract, baseUrl);
    }

    private T generateService(Class<T> contract, String baseUrl) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(getGeneralTimeout(), TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(getGeneralTimeout(), TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(getGeneralTimeout(), TimeUnit.SECONDS);
        okHttpClient.setCache(cache);

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(new OkClient(okHttpClient))
                .setConverter(getConverter())
                .setRequestInterceptor(new RequestInterceptor() {
                                           @Override
                                           public void intercept(RequestFacade request) {
                                               onRequest(request);
                                           }
                                       }
                );
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        return builder.build().create(contract);
    }

    protected Converter getConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if (f.getAnnotation(ExcludedFromAPISerialization.class) != null) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                if (clazz.getAnnotation(ExcludedFromAPISerialization.class) != null) {
                    return true;
                }
                return false;
            }
        });

        Gson gson = gsonBuilder.create();
        return new GsonConverter(gson);
    }

    protected void onRequest(RequestInterceptor.RequestFacade request) {
    }

    protected T getService() {
        return service;
    }

    public boolean hasOngoingCall(CallId callId) {
        return ongoingCalls.containsKey(callId);
    }

    public synchronized <CT> BaseApiCall<CT> registerCall(CallId callId, CachePolicy cachePolicy, Callback<CT> callback, Type responseType) {
        LogInternal.logBaseApiCall("register-call", callId.toString());
        if (ongoingCalls.containsKey(callId)) {
            LogInternal.logBaseApiCall("pre-existing call detected", callId.toString());
            cancelCall(callId);
        }
        BaseApiCall<CT> newCall = new BaseApiCall<>(this, callId, cachePolicy, callback, responseType);
        if (callback == null) {
            newCall.cancelCall(); // If callback == null on register then ignore the response.
        }
        ongoingCalls.put(callId, newCall);
        return newCall;
    }

    public synchronized boolean registerCallback(CallId callId, Callback callback) {
        BaseApiCall ongoingCall = ongoingCalls.get(callId);
        if (ongoingCall != null) {
            ongoingCall.updateCallback(callback);
            LogInternal.logBaseApiCall("register-callback", callId.toString());
            return true;
        } else {
            LogInternal.logBaseApiCall("register-callback ignored", callId.toString());
            return false;
        }
    }

    public synchronized boolean unregisterCallback(CallId callId) {
        BaseApiCall ongoingCall = ongoingCalls.get(callId);
        if (ongoingCall != null) {
            ongoingCall.removeCallback();
            LogInternal.logBaseApiCall("unregister-callback", callId.toString());
            return true;
        } else {
            LogInternal.logBaseApiCall("unregister-callback ignored", callId.toString());
            return false;
        }
    }

    public synchronized void cancelCalls(CallOrigin callOrigin) {
        Set<CallId> ongoingCallIds = ongoingCalls.keySet();
        if (ongoingCallIds != null && ongoingCallIds.size() > 0) {
            LogInternal.logBaseApiCall("cancel-calls", callOrigin.name());
            for (CallId callId : ongoingCallIds) {
                if (callId.getOrigin() == callOrigin) {
                    cancelCall(callId);
                }
            }
        } else {
            LogInternal.logBaseApiCall("cancel-calls ignored", callOrigin.name());
        }
    }

    public synchronized void cancelCall(CallId callId) {
        BaseApiCall ongoingCall = ongoingCalls.get(callId);
        if (ongoingCall != null) {
            ongoingCall.cancelCall();
            ongoingCalls.remove(callId);
            LogInternal.logBaseApiCall("cancel-call", callId.toString());
        } else {
            LogInternal.logBaseApiCall("cancel-call ignored", callId.toString());
        }
    }

    public synchronized void removeCall(CallId callId) {
        BaseApiCall ongoingCall = ongoingCalls.get(callId);
        if (ongoingCall != null) {
            ongoingCalls.remove(callId);
            LogInternal.logBaseApiCall("remove-call done", callId.toString());
        } else {
            LogInternal.logBaseApiCall("remove-call ignored", callId.toString());
        }
    }

    protected TypedInput generateJsonTypedInput(Object object) {
        byte[] requestBytes = new byte[0];
        try {
            requestBytes = (new Gson()).toJson(object).getBytes(Constants.UTF_8);
        } catch (Throwable t) {
//            LogInternal.error(t.toString());
        }
        return new TypedByteArray("application/json", requestBytes);
    }

    public void deleteCache() {
        try {
            cache.delete();
        } catch (Exception e) {
//            LogInternal.error(e.toString());
        }
        initializeHttpCache(DEFAULT_CACHE_DIR_SIZE);
    }
}
