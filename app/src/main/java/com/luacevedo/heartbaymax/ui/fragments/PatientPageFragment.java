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
import static com.luacevedo.heartbaymax.Constants.PatientStage.INITIAL_STATE;
import static com.luacevedo.heartbaymax.Constants.PatientStage.LAB_ANALYSIS;
import static com.luacevedo.heartbaymax.Constants.PatientStage.PRELIMINARY_DIAGNOSIS;
import static com.luacevedo.heartbaymax.Constants.PatientStage.RX;

public class PatientPageFragment extends BaseFragment implements OnPatientStageClick {

    private static final int REQUEST_CODE = 34256;
    private PatientPageActivity activity;
    private Patient patient;

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
        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        TextView patientName = (TextView) view.findViewById(R.id.patient_page_name);
        patientName.setText(patient.getName());

        PatientStageView initialSituationView = (PatientStageView) view.findViewById(R.id.initial_state_stage);
        initialSituationView.setupView(INITIAL_STATE, true, true, this);

        PatientStageView preliminaryDiagnosisView = (PatientStageView) view.findViewById(R.id.preliminary_diagnosis_stage);
        preliminaryDiagnosisView.setupView(Constants.PatientStage.PRELIMINARY_DIAGNOSIS, true, true, this);

        PatientStageView ecgView = (PatientStageView) view.findViewById(R.id.ecg_stage);
        ecgView.setupView(Constants.PatientStage.ECG, patient.isECGCompleted(), true, this);

        PatientStageView rxView = (PatientStageView) view.findViewById(R.id.rx_stage);
        rxView.setupView(Constants.PatientStage.RX, patient.isRXCompleted(), true, this);

        PatientStageView labAnalysisView = (PatientStageView) view.findViewById(R.id.lab_analysis_stage);
        labAnalysisView.setupView(Constants.PatientStage.LAB_ANALYSIS, patient.isLabAnalysisCompleted(), true, this);

        PatientStageView finalDiagnosisView = (PatientStageView) view.findViewById(R.id.final_diagnosis_stage);
        finalDiagnosisView.setupView(Constants.PatientStage.FINAL_DIAGNOSIS, patient.isFinalDiagnosisCompleted(), patient.isFinalDiagnosisEnabled(), this);
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
}
