package com.luacevedo.heartbaymax.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.MochiApi;
import com.luacevedo.heartbaymax.api.baseapi.CallId;
import com.luacevedo.heartbaymax.api.baseapi.CallOrigin;
import com.luacevedo.heartbaymax.api.baseapi.CallType;
import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.ui.fragments.PatientPageFragment;
import com.luacevedo.heartbaymax.ui.fragments.DiagnosisDataFragment;
import com.luacevedo.heartbaymax.utils.RulesExecutor;
import com.luacevedo.heartbaymax.utils.RulesUtils;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PatientPageActivity extends BaseFragmentActivity {

    private static final int REQUEST_COMPLETE_COMPLEMENTARY_METHODS = 34256;
    private Patient patient;
    private boolean isFromInitialState = false;
    private ProgressDialog progress;
    private MochiApi mochiApi = HeartBaymaxApplication.getApplication().getMochiApi();
    private PatientPageFragment initialFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patient = BundleHelper.fromBundleJson(savedInstanceState != null ? savedInstanceState : getIntent().getExtras(), Constants.BundleKey.PATIENT, Patient.class, null);
        if (savedInstanceState != null) {
            isFromInitialState = savedInstanceState.getBoolean(Constants.BundleKey.IS_FROM_INITIAL_STATE);
        } else {
            isFromInitialState = getIntent().getExtras().getBoolean(Constants.BundleKey.IS_FROM_INITIAL_STATE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleHelper.putJsonBundle(outState, Constants.BundleKey.PATIENT, patient);
        outState.putBoolean(Constants.BundleKey.IS_FROM_INITIAL_STATE, isFromInitialState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        unlockMenu();
        initialFragment = new PatientPageFragment();
        setInitialFragment(initialFragment);

        if (isFromInitialState) {
            isFromInitialState = false;
            showOverlayFragment(DiagnosisDataFragment.newInstance(Constants.PatientStage.PRELIMINARY_DIAGNOSIS));
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_COMPLETE_COMPLEMENTARY_METHODS) {
                patient = BundleHelper.fromBundleJson(data, Constants.BundleKey.PATIENT, Patient.class, patient);
            }
        }
    }

    public void executeSecondStageRules() {
        getRules();
    }

    private void getRules() {
        progress = ProgressDialog.show(this, null, getString(R.string.loading), true);
        CallId callId = new CallId(CallOrigin.RULES_EXECUTION_STAGE_2, CallType.RULES);
        mochiApi.getRules(callId, getRulesCallback());
    }

    private Callback<List<Rule>> getRulesCallback() {
        return new Callback<List<Rule>>() {
            @Override
            public void success(List<Rule> rules, Response response) {
                List<Rule> secondStageRules = RulesUtils.getRulesForStage(rules, RulesUtils.STAGE_2);
                RulesExecutor.executeRules(secondStageRules, patient);
                HeartBaymaxApplication.getApplication().getInternalDbHelper().savePatient(patient);
                progress.dismiss();
                showOverlayFragment(DiagnosisDataFragment.newInstance(Constants.PatientStage.FINAL_DIAGNOSIS));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("LULI", error.toString());
                Toast.makeText(getApplicationContext(), R.string.networ_error, Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        };
    }

    public void refreshStages() {
        initialFragment.refreshStages();
    }
}
