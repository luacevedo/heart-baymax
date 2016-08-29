package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.model.patient.Patient;

public class PatientPageFragment extends BaseFragment implements View.OnClickListener {

    private Patient patient;
    private Button btnEditPreliminaryDiagnosis;
    private Button btnEditHeartSituation;
    private LinearLayout patientContent;
    private TextView patientName;

    public static PatientPageFragment newInstance(Patient patient) {
        PatientPageFragment fragment = new PatientPageFragment();
        fragment.setPatient(patient);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_page, container, false);
        patientContent = (LinearLayout) view.findViewById(R.id.patient_page_content);
        patientName = (TextView) view.findViewById(R.id.patient_page_name);
        patientName.setText(patient.getName());
        btnEditPreliminaryDiagnosis = (Button) view.findViewById(R.id.edit_preliminary_diag_btn);
        btnEditPreliminaryDiagnosis.setOnClickListener(this);
        btnEditHeartSituation = (Button) view.findViewById(R.id.edit_heart_situation_btn);
        btnEditHeartSituation.setOnClickListener(this);
        return view;
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
