package com.example.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luacevedo.heartbaymax.R;
import com.example.luacevedo.heartbaymax.api.HeartBaymaxApi;
import com.example.luacevedo.heartbaymax.api.model.MockInfo;
import com.example.luacevedo.heartbaymax.api.model.Rule;
import com.example.luacevedo.heartbaymax.helpers.RulesHelper;
import com.example.luacevedo.heartbaymax.model.rules.actions.BaseAction;
import com.example.luacevedo.heartbaymax.model.rules.conditions.BaseCondition;
import com.example.luacevedo.heartbaymax.model.patient.Patient;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivityFragment extends Fragment {

    private HeartBaymaxApi heartBaymaxApi;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;

    }

    List<Rule> ruleList = new ArrayList<>();
    Patient patient = MockInfo.createPatient();
    
    @Override
    public void onStart() {
        super.onStart();
        Log.e("LULI", "creo los datos");
        heartBaymaxApi = new HeartBaymaxApi();
        heartBaymaxApi.getRules(generateRulesCallback());
    }

    @NonNull
    private Callback<List<Rule>> generateRulesCallback() {
        return new Callback<List<Rule>>() {
            @Override
            public void success(List<Rule> rules, Response response) {
                Log.e("LULI", "SUCCESS DE RETROFIT =)");
                ruleList = rules;

                RulesHelper.executeRules(ruleList, patient);
                printPatient();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("LULI", "FAILURE DE RETROFIT =(");
                Log.e("LULI", error.toString());

            }
        };
    }

    private void printPatient() {
        Log.e("LULI", "EL Pacienteeeee: ");
        for (String key : patient.getAttributesMap().keySet()) {
            PatientAttribute att = patient.getAttributesMap().get(key);
            Log.e("LULI", key + " = " + att.getValue());
        }
    }

}
