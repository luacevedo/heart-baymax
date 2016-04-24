package com.example.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luacevedo.heartbaymax.R;
import com.example.luacevedo.heartbaymax.api.HeartBaymaxApi;
import com.example.luacevedo.heartbaymax.api.model.MockInfo;
import com.example.luacevedo.heartbaymax.api.model.Rule;
import com.example.luacevedo.heartbaymax.model.actions.BaseAction;
import com.example.luacevedo.heartbaymax.model.conditions.BaseCondition;
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
        heartBaymaxApi.getRules(new Callback<List<Rule>>() {
            @Override
            public void success(List<Rule> rules, Response response) {
                Log.e("LULI", "SUCCESS DE RETROFIT =)");
                ruleList = rules;

                executeRules();
                printPatient();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("LULI", "FAILURE DE RETROFIT =(");
                Log.e("LULI", error.toString());

            }
        });
    }

    private void executeRules() {
        int i = 0;
        Log.e("LULI", "ejecuto las reglas");
        while (i < ruleList.size()) {
            Log.e("LULI", "Rule " + ruleList.get(i).getId());
            boolean conditionsFulfilled = checkConditions(ruleList.get(i));
            if (conditionsFulfilled) {
                executeActions(ruleList.get(i));
                excludeRules(ruleList.get(i).getRulesToExclude());
            }
            i++;
        }
    }

    private void excludeRules(List<Long> rulesToExclude) {
        for (Long id : rulesToExclude) {
            Log.e("LULI", "Excluyo regla " + id);
            Rule r = new Rule();
            r.setId(id);
            ruleList.remove(r);
        }
    }

    private void executeActions(Rule rule) {
        Log.e("LULI", "SI conditionsFulfilled... ejecuto las acciones");
        for (BaseAction action : rule.getParsedActions()) {
            Log.e("LULI", "Attribute: " + action.getAttributeRoot());
            Log.e("LULI", "Action: " + action);
            PatientAttribute attributeToExecuteAction = patient.getAttributesMap().get(action.getAttributeRoot());
            action.execute(attributeToExecuteAction);
        }
    }

    private void printPatient() {
        Log.e("LULI", "EL Pacienteeeee: ");
        for (String key : patient.getAttributesMap().keySet()) {
            PatientAttribute att = patient.getAttributesMap().get(key);
            Log.e("LULI", key + " = " + att.getValue());
        }
    }

    private boolean checkConditions(Rule rule) {
        boolean conditionsFulfilled = true;
        for (BaseCondition condition : rule.getParsedConditions()) {
            Log.e("LULI", "Por cada condicion:");
            PatientAttribute attributeToCheck = patient.getAttributesMap().get(condition.getAttributeRoot());
            Log.e("LULI", "Attribute: " + condition.getAttributeRoot());
            Log.e("LULI", "Condition: " + condition);
            if (!condition.validate(attributeToCheck)) {
                Log.e("LULI", "no cumplio con la condicion el attributo...");
                conditionsFulfilled = false;
                break;
            }
        }
        return conditionsFulfilled;
    }


}
