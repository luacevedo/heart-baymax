package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.api.model.MockInfo;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.ui.fragments.PreliminaryDiagnosisStepFragment;

public class PreliminaryDiagnosisActivity extends BaseFragmentActivity {

    private PreliminaryDiagnosisFields preliminaryDiagnosisFields;
    private Patient patient;
    //    private List<PreliminaryDiagnosisStepFragment> fragmentsStack = new ArrayList<>();
    private int currentStep = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preliminaryDiagnosisFields = BundleHelper.fromBundle(savedInstanceState, Constants.BundleKey.INPUT_FIELDS);
        patient = BundleHelper.fromBundle(savedInstanceState, Constants.BundleKey.PATIENT);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(Constants.BundleKey.INPUT_FIELDS, preliminaryDiagnosisFields);
        outState.putSerializable(Constants.BundleKey.PATIENT, patient);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (preliminaryDiagnosisFields.getStepsInputFields().isEmpty()) {
            getPreliminaryDiagnosisFields();
            //this will then be in success callback
            if (!preliminaryDiagnosisFields.getStepsInputFields().isEmpty()) {
                PreliminaryDiagnosisStepFragment fragment;
                fragment = PreliminaryDiagnosisStepFragment.newInstance(preliminaryDiagnosisFields.getStepsInputFields().get(currentStep).getInputFields(), isLastStep());
                setInitialFragment(fragment);
            }
        }
    }

    private boolean isLastStep() {
        return !preliminaryDiagnosisFields.getStepsInputFields().isEmpty() && currentStep == preliminaryDiagnosisFields.getStepsInputFields().size() - 1;
    }

    private void getPreliminaryDiagnosisFields() {
        preliminaryDiagnosisFields = MockInfo.getPreliminaryDiagnosisFields();
    }


    public void finishDiagnosis() {

    }

    public void nextStep() {
        PreliminaryDiagnosisStepFragment fragment;
        fragment = PreliminaryDiagnosisStepFragment.newInstance(preliminaryDiagnosisFields.getStepsInputFields().get(currentStep).getInputFields(), isLastStep());
        slideNextFragment(fragment);
    }
}
