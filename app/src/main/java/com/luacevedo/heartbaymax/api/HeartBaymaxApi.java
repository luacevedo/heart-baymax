package com.luacevedo.heartbaymax.api;

import com.google.gson.reflect.TypeToken;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.api.baseapi.BaseApi;
import com.luacevedo.heartbaymax.api.baseapi.BaseApiCall;
import com.luacevedo.heartbaymax.api.baseapi.CachePolicy;
import com.luacevedo.heartbaymax.api.baseapi.CallId;
import com.luacevedo.heartbaymax.api.contract.HeartBaymaxApiContract;
import com.luacevedo.heartbaymax.api.model.rules.Rule;

import java.util.List;

import retrofit.Callback;

public class HeartBaymaxApi extends BaseApi<HeartBaymaxApiContract> {

    private static final String BASE_URL = "https://heart-baymax-api.herokuapp.com";

    public HeartBaymaxApi() {
        super(BASE_URL, HeartBaymaxApiContract.class);
    }

    public void getRules(CallId callId, Callback<List<Rule>> callback) {
        CachePolicy cachePolicy = CachePolicy.CACHE_ELSE_NETWORK;
        cachePolicy.setCacheKey("rules");
        cachePolicy.setCacheTTL(Constants.Time.TEN_MINUTES);

        BaseApiCall<List<Rule>> apiCall = registerCall(callId, cachePolicy, callback, new TypeToken<List<Rule>>() {
        }.getType());

        if (apiCall != null && apiCall.requiresNetworkCall()) {
            getService().getRules(apiCall);
        }
    }


}
