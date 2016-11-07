package com.luacevedo.heartbaymax.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.interfaces.OnPatientStageClick;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.ui.activities.PatientPageActivity;
import com.luacevedo.heartbaymax.ui.views.PatientStageView;
import com.luacevedo.heartbaymax.utils.InputFieldsUtils;

import static com.luacevedo.heartbaymax.Constants.PatientStage.ECG;
import static com.luacevedo.heartbaymax.Constants.PatientStage.FINAL_DIAGNOSIS;
import static com.luacevedo.heartbaymax.Constants.PatientStage.IMMEDIATE_TREATMENT;
import static com.luacevedo.heartbaymax.Constants.PatientStage.INITIAL_STATE;
import static com.luacevedo.heartbaymax.Constants.PatientStage.LAB_ANALYSIS;
import static com.luacevedo.heartbaymax.Constants.PatientStage.PRELIMINARY_DIAGNOSIS;
import static com.luacevedo.heartbaymax.Constants.PatientStage.RX;

public class PatientPageFragment extends BaseFragment implements OnPatientStageClick {

    private static final int REQUEST_CODE = 34256;
    private PatientPageActivity activity;
    private Patient patient;
    private TextView patientName;
    private PatientStageView initialSituationView;
    private PatientStageView preliminaryDiagnosisView;
    private PatientStageView immediateTreatmentView;
    private PatientStageView ecgView;
    private PatientStageView rxView;
    private PatientStageView labAnalysisView;
    private PatientStageView finalDiagnosisView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (PatientPageActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_page, container, false);
        activity = (PatientPageActivity) getActivity();
        if (patient == null) {
            patient = activity.getPatient();
        } else {
            activity.setPatient(patient);
        }
        findViews(view);
        setupViews();
        return view;
    }

    private void setupViews() {
        patientName.setText(String.format("%s - %s años", patient.getName(), patient.getAge()));
        initialSituationView.setupView(INITIAL_STATE, true, true, this);
        preliminaryDiagnosisView.setupView(Constants.PatientStage.PRELIMINARY_DIAGNOSIS, true, true, this);
        immediateTreatmentView.setupView(Constants.PatientStage.IMMEDIATE_TREATMENT, true, true, this);
        ecgView.setupView(Constants.PatientStage.ECG, patient.isECGCompleted(), true, this);
        rxView.setupView(Constants.PatientStage.RX, patient.isRXCompleted(), true, this);
        labAnalysisView.setupView(Constants.PatientStage.LAB_ANALYSIS, patient.isLabAnalysisCompleted(), true, this);
        finalDiagnosisView.setupView(Constants.PatientStage.FINAL_DIAGNOSIS, patient.isFinalDiagnosisCompleted(), patient.isFinalDiagnosisEnabled(), this);
    }

    private void findViews(View view) {
        patientName = (TextView) view.findViewById(R.id.patient_page_name);
        initialSituationView = (PatientStageView) view.findViewById(R.id.initial_state_stage);
        preliminaryDiagnosisView = (PatientStageView) view.findViewById(R.id.preliminary_diagnosis_stage);
        immediateTreatmentView = (PatientStageView) view.findViewById(R.id.immediate_treatment_stage);
        ecgView = (PatientStageView) view.findViewById(R.id.ecg_stage);
        rxView = (PatientStageView) view.findViewById(R.id.rx_stage);
        labAnalysisView = (PatientStageView) view.findViewById(R.id.lab_analysis_stage);
        finalDiagnosisView = (PatientStageView) view.findViewById(R.id.final_diagnosis_stage);
    }

    @Override
    public void onPageStageClick(Constants.PatientStage stage, boolean isCompleted) {
        switch (stage) {
            case INITIAL_STATE:
                slideNextFragment(PatientPageDataFragment.newInstance(INITIAL_STATE));
                break;
            case PRELIMINARY_DIAGNOSIS:
                slideNextFragment(PatientPageDataFragment.newInstance(PRELIMINARY_DIAGNOSIS));
                break;
            case IMMEDIATE_TREATMENT:
                slideNextFragment(PatientPageDataFragment.newInstance(IMMEDIATE_TREATMENT));
                break;
            case ECG:
                if (activity.getPatient().isECGCompleted()) {
                    slideNextFragment(PatientPageDataFragment.newInstance(ECG));
                } else {
                    startActivityForResult(IntentFactory.getComplementaryMethodsActivityIntent(patient, InputFieldsUtils.STAGE_2), REQUEST_CODE);
                }
                break;
            case RX:
                if (activity.getPatient().isRXCompleted()) {
                    slideNextFragment(PatientPageDataFragment.newInstance(RX));
                } else {
                    startActivityForResult(IntentFactory.getComplementaryMethodsActivityIntent(patient, InputFieldsUtils.STAGE_3), REQUEST_CODE);
                }
                break;
            case LAB_ANALYSIS:
                if (activity.getPatient().isLabAnalysisCompleted()) {
                    slideNextFragment(PatientPageDataFragment.newInstance(LAB_ANALYSIS));
                } else {
                    startActivityForResult(IntentFactory.getComplementaryMethodsActivityIntent(patient, InputFieldsUtils.STAGE_4), REQUEST_CODE);
                }
                break;
            case FINAL_DIAGNOSIS:
                if (activity.getPatient().isFinalDiagnosisCompleted()) {
                    slideNextFragment(PatientPageDataFragment.newInstance(FINAL_DIAGNOSIS));
                } else {
                    activity.executeSecondStageRules();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                activity.setPatient(BundleHelper.fromBundleJson(data, Constants.BundleKey.PATIENT, Patient.class, activity.getPatient() != null ? activity.getPatient() : null));
            }
        }
    }

    public void refreshStages() {
        setupViews();
    }
}
