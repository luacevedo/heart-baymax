package com.luacevedo.heartbaymax.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.ui.fragments.PatientPageFragment;

public class PatientPageActivity extends BaseFragmentActivity {

    private static final int REQUEST_COMPLETE_ECG = 34256;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patient = BundleHelper.fromBundleJson(savedInstanceState != null ? savedInstanceState : getIntent().getExtras(), Constants.BundleKey.PATIENT, Patient.class, null);
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
            if (requestCode == REQUEST_COMPLETE_ECG) {
                patient = BundleHelper.fromBundleJson(data, Constants.BundleKey.PATIENT, Patient.class, patient);
            }
        }
    }
}
