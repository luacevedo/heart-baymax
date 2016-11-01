package com.luacevedo.heartbaymax.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.ui.fragments.PatientPageFragment;
import com.luacevedo.heartbaymax.ui.fragments.PreliminaryDiagnosisDataFragment;

public class PatientPageActivity extends BaseFragmentActivity {

    private static final int REQUEST_COMPLETE_COMPLEMENTARY_METHODS = 34256;
    private Patient patient;
    private boolean isFromInitialState = false;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        unlockMenu();
        setInitialFragment(new PatientPageFragment());

        if (isFromInitialState) {
            showOverlayFragment(new PreliminaryDiagnosisDataFragment());
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
}
