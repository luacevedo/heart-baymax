package com.example.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luacevedo.heartbaymax.R;
import com.example.luacevedo.heartbaymax.api.model.Attribute;
import com.example.luacevedo.heartbaymax.api.model.MockInfo;
import com.example.luacevedo.heartbaymax.api.model.Rule;
import com.example.luacevedo.heartbaymax.model.actions.AddNumberAction;
import com.example.luacevedo.heartbaymax.model.actions.AddToListAction;
import com.example.luacevedo.heartbaymax.model.actions.AssignAction;
import com.example.luacevedo.heartbaymax.model.actions.BaseAction;
import com.example.luacevedo.heartbaymax.model.conditions.AffirmativeCondition;
import com.example.luacevedo.heartbaymax.model.conditions.BaseCondition;
import com.example.luacevedo.heartbaymax.model.conditions.ContainsCondition;
import com.example.luacevedo.heartbaymax.model.conditions.GreaterThanCondition;
import com.example.luacevedo.heartbaymax.model.conditions.LessThanCondition;
import com.example.luacevedo.heartbaymax.model.conditions.NotContainsCondition;
import com.example.luacevedo.heartbaymax.model.patient.Patient;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;

    }

    List<Rule> rules = MockInfo.getMockedRules();
    Patient patient = MockInfo.createPatient();


    @Override
    public void onStart() {
        super.onStart();
        Log.e("LULI", "creo los datos");

        Log.e("LULI", "ejecuto las reglas");
        for (Rule rule : rules) {
            Log.e("LULI", "Rule " + rule.getId());
            boolean conditionsFulfilled = checkConditions(rule);
            if (conditionsFulfilled) {
                executeActions(rule);
            }
        }
        printPatient();
    }

    private void executeActions(Rule rule) {
        Log.e("LULI", "SI conditionsFulfilled... ejecuto las acciones");
        for (BaseAction action : rule.getParsedActions()) {
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
            Log.e("LULI", "Attribute: " + attributeToCheck.getAttribute().getRoot());
            if (!condition.validate(attributeToCheck)) {
                Log.e("LULI", "no cumplio con la condicion el attributo... NO reviso mas condiciones y NO se ejecutan las actiones");
                conditionsFulfilled = false;
                break;
            }
        }
        return conditionsFulfilled;
    }


}
