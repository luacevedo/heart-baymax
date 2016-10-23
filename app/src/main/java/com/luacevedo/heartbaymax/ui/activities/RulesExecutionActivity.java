package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.api.model.MockInfo;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.ui.fragments.PatientPageFragment;
import com.luacevedo.heartbaymax.ui.fragments.RulesExecutionFragment;

import java.util.ArrayList;
import java.util.List;

public class RulesExecutionActivity extends BaseFragmentActivity {

    private Patient patient;
    private List<Rule> ruleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            ruleList = BundleHelper.fromBundleJson(savedInstanceState, Constants.BundleKey.RULES, new TypeToken<List<Rule>>() {
            }.getType(), new ArrayList<Rule>());
        }
        patient = BundleHelper.fromBundleJson(savedInstanceState != null ? savedInstanceState : getIntent().getExtras(), Constants.BundleKey.PATIENT, Patient.class, null);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleHelper.putJsonBundle(outState, Constants.BundleKey.PATIENT, patient);
        BundleHelper.putJsonBundle(outState, Constants.BundleKey.RULES, ruleList, new TypeToken<List<Rule>>() {
        }.getType());
    }

    @Override
    protected void onResume() {
        super.onResume();
        unlockMenu();
        ruleList = MockInfo.getMockedRules();
        setInitialFragment(RulesExecutionFragment.newInstance(patient, ruleList));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
