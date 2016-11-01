package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.model.rules.actions.BaseAction;
import com.luacevedo.heartbaymax.model.rules.conditions.BaseCondition;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;

import java.util.ArrayList;
import java.util.List;

public class RulesExecutionFragment extends BaseFragment {

    private Patient patient;
    private List<Rule> ruleList = new ArrayList<>();

    private LinearLayout patientContentLayout;

    public static RulesExecutionFragment newInstance(Patient patient, List<Rule> rules) {
        RulesExecutionFragment fragment = new RulesExecutionFragment();
        fragment.setPatient(patient);
        fragment.setRuleList(rules);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rules_execution, container, false);
        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        patientContentLayout = (LinearLayout) view.findViewById(R.id.rules_exec_content);
        TextView patientName = (TextView) view.findViewById(R.id.rules_exec_patient_name);
        patientName.setText(patient.getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        executeRules();
        addPatientAttributes();
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

    private void excludeRules(List<Integer> rulesToExclude) {
        for (Integer id : rulesToExclude) {
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

    private void addPatientAttributes() {
        List<PatientAttribute> essentialSymptomsList = new ArrayList<>();
        List<PatientAttribute> secondarySymptomsList = new ArrayList<>();
        List<PatientAttribute> preliminaryDiagnosisList = new ArrayList<>();
        for (PatientAttribute attribute : patient.getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            switch (root) {
                case Constants.Patient.Root.ESSENTIAL_SYMPTOMS:
                    essentialSymptomsList.add(attribute);
                    break;
                case Constants.Patient.Root.SECONDARY_SYMPTOMS:
                    secondarySymptomsList.add(attribute);
                    break;
                case Constants.Patient.Root.PRELIMINARY_DIAGNOSIS:
                    preliminaryDiagnosisList.add(attribute);
                    break;
                default:
                    break;
            }
        }

        addValuesToLayout(essentialSymptomsList);
        addValuesToLayout(secondarySymptomsList);
        addValuesToLayout(preliminaryDiagnosisList);
    }

    private void addValuesToLayout(List<PatientAttribute> list) {
        for (PatientAttribute attribute : list) {
            PatientAttributeView viewAttribute = new PatientAttributeView(getActivity());
            viewAttribute.setData(attribute);
            patientContentLayout.addView(viewAttribute);
        }
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }
}
