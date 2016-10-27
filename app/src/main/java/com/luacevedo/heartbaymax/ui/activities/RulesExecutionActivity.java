package com.luacevedo.heartbaymax.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.api.MochiApi;
import com.luacevedo.heartbaymax.api.baseapi.CallId;
import com.luacevedo.heartbaymax.api.baseapi.CallOrigin;
import com.luacevedo.heartbaymax.api.baseapi.CallType;
import com.luacevedo.heartbaymax.api.model.MockInfo;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.ui.fragments.PatientPageFragment;
import com.luacevedo.heartbaymax.ui.fragments.RulesExecutionFragment;
import com.luacevedo.heartbaymax.utils.PatientAttributesUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RulesExecutionActivity extends BaseFragmentActivity {

    private Patient patient;
    private List<Rule> ruleList = new ArrayList<>();
    private ProgressDialog progress;
    private MochiApi mochiApi = HeartBaymaxApplication.getApplication().getMochiApi();

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
        getRules();
    }

    private void getRules() {
        progress = ProgressDialog.show(this, null, "Cargando", true);
        CallId callId = new CallId(CallOrigin.RULES_EXECUTION_STAGE_1, CallType.RULES_STAGE_1);
        mochiApi.getRules(callId, getRulesCallback());
    }

    private Callback<List<Rule>> getRulesCallback() {
        return new Callback<List<Rule>>() {
            @Override
            public void success(List<Rule> rules, Response response) {
                ruleList = rules;
                setInitialFragment(RulesExecutionFragment.newInstance(patient, ruleList));
                progress.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("LULI", error.toString());
            }
        };
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
