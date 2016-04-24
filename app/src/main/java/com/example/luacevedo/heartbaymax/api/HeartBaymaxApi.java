package com.example.luacevedo.heartbaymax.api;

import com.example.luacevedo.heartbaymax.api.contract.HeartBaymaxApiContract;
import com.example.luacevedo.heartbaymax.api.model.Rule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class HeartBaymaxApi {

    private static final String BASE_URL = "https://heart-baymax-api.herokuapp.com";
    private HeartBaymaxApiContract contract;

    public HeartBaymaxApi() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        contract = restAdapter.create(HeartBaymaxApiContract.class);
    }

    public void getRules(Callback<List<Rule>> callback) {
        contract.getRules(callback);
    }

}
