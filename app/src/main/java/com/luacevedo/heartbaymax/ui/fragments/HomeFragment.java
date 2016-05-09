package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.baseapi.CallId;
import com.luacevedo.heartbaymax.api.baseapi.CallOrigin;
import com.luacevedo.heartbaymax.api.baseapi.CallType;
import com.luacevedo.heartbaymax.api.model.MockInfo;
import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.helpers.RulesHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private TextView btnNewPatient;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnNewPatient = (TextView) view.findViewById(R.id.new_patient_btn);
        btnNewPatient.setOnClickListener(this);
        return view;

    }

    List<Rule> ruleList = new ArrayList<>();
    Patient patient = MockInfo.createPatient();

    @Override
    public void onStart() {
        super.onStart();
        Log.e("LULI", "creo los datos");
        CallId rulesCallId = new CallId(CallOrigin.HOME, CallType.RULES);
        heartBaymaxApi.getRules(rulesCallId, generateRulesCallback());
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.new_patient_btn) {
            startActivity(IntentFactory.getPreliminaryDiagnosisActivityIntent());
        }
    }
}
