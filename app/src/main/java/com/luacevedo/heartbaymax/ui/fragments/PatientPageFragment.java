package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;

import java.util.ArrayList;
import java.util.List;

public class PatientPageFragment extends BaseFragment implements View.OnClickListener {

    private Patient patient;
    private Button btnEditPreliminaryDiagnosis;
    private Button btnEditHeartSituation;
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
        patientContentLayout = (LinearLayout) view.findViewById(R.id.patient_page_content);
        patientName = (TextView) view.findViewById(R.id.patient_page_name);
        patientName.setText(patient.getName());
        btnEditPreliminaryDiagnosis = (Button) view.findViewById(R.id.edit_preliminary_diag_btn);
        btnEditPreliminaryDiagnosis.setOnClickListener(this);
        btnEditHeartSituation = (Button) view.findViewById(R.id.edit_heart_situation_btn);
        btnEditHeartSituation.setOnClickListener(this);

        addPatientAttributes();

        return view;
    }

    private void addPatientAttributes() {
        List<PatientAttribute> essentialSymptomsList = new ArrayList<>();
        List<PatientAttribute> secondarySymptomsList = new ArrayList<>();
        for (PatientAttribute attribute : patient.getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            switch (root) {
                case Constants.Patient.ESSENTIAL_SYMPTOMS:
                    essentialSymptomsList.add(attribute);
                    break;
                case Constants.Patient.SECONDARY_SYMPTOMS:
                    secondarySymptomsList.add(attribute);
                    break;
                default:
                    break;
            }
        }

        addValuesToLayout(essentialSymptomsList);
        addValuesToLayout(secondarySymptomsList);
    }

    private void addValuesToLayout(List<PatientAttribute> list) {
        for (PatientAttribute attribute : list) {
            PatientAttributeView viewAttribute = new PatientAttributeView(getActivity());
            viewAttribute.setData(attribute);
            patientContentLayout.addView(viewAttribute);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit_preliminary_diag_btn) {
            startActivity(IntentFactory.getPreliminaryDiagnosisActivityIntent(patient));
        } else if (v.getId() == R.id.edit_heart_situation_btn) {

        }
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
