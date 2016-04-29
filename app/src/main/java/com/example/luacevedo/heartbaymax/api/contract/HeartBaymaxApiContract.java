package com.example.luacevedo.heartbaymax.api.contract;

import com.example.luacevedo.heartbaymax.api.model.Rule;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface HeartBaymaxApiContract {

    @GET("/rules")
    void getRules(Callback<List<Rule>> callback);
}

