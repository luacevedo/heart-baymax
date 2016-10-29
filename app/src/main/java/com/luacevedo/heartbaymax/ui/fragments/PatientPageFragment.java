package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.helpers.LogInternal;
import com.luacevedo.heartbaymax.interfaces.OnPatientStageClick;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;
import com.luacevedo.heartbaymax.ui.views.PatientStageView;

import java.util.ArrayList;
import java.util.List;

public class PatientPageFragment extends BaseFragment implements OnPatientStageClick {

    private Patient patient;
    private PatientStageView heartSituationView;
    private PatientStageView initialSituationView;
    private LinearLayout patientContentLayout;
    private TextView patientName;

    public static PatientPageFragment newInstance(Patient patient) {
        PatientPageFragment fragment = new PatientPageFragment();
        fragment.setPatient(patient);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_page, container, false);
        setupViews(view);
        addPatientAttributes();
        return view;
    }

    private void setupViews(View view) {
        patientContentLayout = (LinearLayout) view.findViewById(R.id.patient_page_content);
        patientName = (TextView) view.findViewById(R.id.patient_page_name);
        patientName.setText(patient.getName());
        initialSituationView = (PatientStageView) view.findViewById(R.id.initial_state_stage);
        initialSituationView.setupView(Constants.PatientStage.INITIAL_STATE, true, this);
        heartSituationView = (PatientStageView) view.findViewById(R.id.heart_situation_stage);
        heartSituationView.setupView(Constants.PatientStage.HEART_SITUATION, true, this);
    }

    private void addPatientAttributes() {
        List<PatientAttribute> essentialSymptomsList = new ArrayList<>();
        List<PatientAttribute> secondarySymptomsList = new ArrayList<>();
        List<PatientAttribute> preliminaryDiagnosisList = new ArrayList<>();
        for (PatientAttribute attribute : patient.getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            switch (root) {
                case Constants.Patient.ESSENTIAL_SYMPTOMS:
                    essentialSymptomsList.add(attribute);
                    break;
                case Constants.Patient.SECONDARY_SYMPTOMS:
                    secondarySymptomsList.add(attribute);
                    break;
                case Constants.Patient.PRELIMINARY_DIAGNOSIS:
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

    @Override
    public void onPageStageClick(Constants.PatientStage stage, boolean isCompleted) {
        switch (stage) {
            case INITIAL_STATE:
                Log.e("LULI","INITIAL STATE");
                break;
            case HEART_SITUATION:
                Log.e("LULI","HEART SITUATION");
                break;
        }
    }
}
