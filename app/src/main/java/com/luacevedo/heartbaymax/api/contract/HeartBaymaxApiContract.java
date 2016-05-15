package com.luacevedo.heartbaymax.api.contract;

import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.api.model.rules.Rule;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface HeartBaymaxApiContract {

    @GET("/rules")
    void getRules(Callback<List<Rule>> callback);

    @GET("/patients/fields")
    void getStepPatientInputFields(Callback<List<StepInputFields>> callback);

    @GET("/patients/attributes")
    void getPatientAttributes(Callback<List<Attribute>> callback);
}

