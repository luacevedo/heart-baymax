package com.example.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luacevedo.heartbaymax.R;
import com.example.luacevedo.heartbaymax.api.model.MockInfo;
import com.example.luacevedo.heartbaymax.api.model.Rule;
import com.example.luacevedo.heartbaymax.model.actions.BaseAction;
import com.example.luacevedo.heartbaymax.model.conditions.BaseCondition;
import com.example.luacevedo.heartbaymax.model.patient.Patient;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

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
        int i = 0;
        Log.e("LULI", "ejecuto las reglas");
        while (i < rules.size()) {
            Log.e("LULI", "Rule " + rules.get(i).getId());
            boolean conditionsFulfilled = checkConditions(rules.get(i));
            if (conditionsFulfilled) {
                executeActions(rules.get(i));
                excludeRules(rules.get(i).getRulesToExclude());
            }
            i++;
        }
        printPatient();
    }

    private void excludeRules(List<Long> rulesToExclude) {
        for (Long id : rulesToExclude) {
            Log.e("LULI", "Excluyo regla " + id);
            Rule r = new Rule();
            r.setId(id);
            rules.remove(r);
        }
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
