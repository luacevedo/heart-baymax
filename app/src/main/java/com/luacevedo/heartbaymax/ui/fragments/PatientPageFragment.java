package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.interfaces.OnPatientStageClick;
import com.luacevedo.heartbaymax.ui.activities.PatientPageActivity;
import com.luacevedo.heartbaymax.ui.views.PatientStageView;
import com.luacevedo.heartbaymax.utils.InputFieldsUtils;

import static com.luacevedo.heartbaymax.Constants.PatientStage.INITIAL_STATE;

public class PatientPageFragment extends BaseFragment implements OnPatientStageClick {

    private PatientPageActivity activity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (PatientPageActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_page, container, false);
        activity = (PatientPageActivity) getActivity();
        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        TextView patientName = (TextView) view.findViewById(R.id.patient_page_name);
        patientName.setText(activity.getPatient().getName());

        PatientStageView initialSituationView = (PatientStageView) view.findViewById(R.id.initial_state_stage);
        initialSituationView.setupView(INITIAL_STATE, true, true, this);

        PatientStageView preliminaryDiagnosisView = (PatientStageView) view.findViewById(R.id.preliminary_diagnosis_stage);
        preliminaryDiagnosisView.setupView(Constants.PatientStage.PRELIMINARY_DIAGNOSIS, true, true, this);

        PatientStageView ecgView = (PatientStageView) view.findViewById(R.id.ecg_stage);
        ecgView.setupView(Constants.PatientStage.ECG, activity.getPatient().isECGCompleted(), true, this);

        PatientStageView rxView = (PatientStageView) view.findViewById(R.id.rx_stage);
        rxView.setupView(Constants.PatientStage.RX, activity.getPatient().isRXCompleted(), true, this);

        PatientStageView labAnalysisView = (PatientStageView) view.findViewById(R.id.lab_analysis_stage);
        labAnalysisView.setupView(Constants.PatientStage.LAB_ANALYSIS, activity.getPatient().isLabAnalysisCompleted(), true, this);

        PatientStageView finalDiagnosisView = (PatientStageView) view.findViewById(R.id.final_diagnosis_stage);
        finalDiagnosisView.setupView(Constants.PatientStage.FINAL_DIAGNOSIS, activity.getPatient().isFinalDiagnosisCompleted(), activity.getPatient().isFinalDiagnosisEnabled(), this);
    }

    @Override
    public void onPageStageClick(Constants.PatientStage stage, boolean isCompleted) {
        switch (stage) {
            case INITIAL_STATE:
                slideNextFragment(PatientPageDataFragment.newInstance(INITIAL_STATE));
                break;
            case PRELIMINARY_DIAGNOSIS:
                break;
            case ECG:
                if (activity.getPatient().isECGCompleted()) {
                    //mostrar los datos
                } else {
                    startActivity(IntentFactory.getComplementaryMethodsActivityIntent(activity.getPatient(), InputFieldsUtils.STAGE_2));
                }
                break;
            case RX:
                if (activity.getPatient().isRXCompleted()) {
                    //mostrar los datos
                } else {
                    startActivity(IntentFactory.getComplementaryMethodsActivityIntent(activity.getPatient(), InputFieldsUtils.STAGE_3));
                }
                break;
            case LAB_ANALYSIS:
                if (activity.getPatient().isLabAnalysisCompleted()) {
                    //mostrar los datos
                } else {
                    startActivity(IntentFactory.getComplementaryMethodsActivityIntent(activity.getPatient(), InputFieldsUtils.STAGE_4));
                }
                break;
            case FINAL_DIAGNOSIS:
                break;
        }
    }
}
