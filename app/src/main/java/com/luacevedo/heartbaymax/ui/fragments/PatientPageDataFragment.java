package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.activities.PatientPageActivity;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeExtendedView;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;
import com.luacevedo.heartbaymax.utils.PatientAttributesUtils;

import java.util.ArrayList;
import java.util.List;

import static com.luacevedo.heartbaymax.Constants.Patient.Root.*;

public class PatientPageDataFragment extends BaseFragment {

    private LinearLayout patientContentLayout;
    private PatientPageActivity activity;
    private Constants.PatientStage patientStage;

    public static PatientPageDataFragment newInstance(Constants.PatientStage patientStage) {
        PatientPageDataFragment fragment = new PatientPageDataFragment();
        fragment.setPatientStage(patientStage);
        return fragment;
    }

    public void setPatientStage(Constants.PatientStage patientStage) {
        this.patientStage = patientStage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (PatientPageActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_page_data, container, false);
        activity = (PatientPageActivity) getActivity();
        setupViews(view);
        addPatientAttributes();
        return view;
    }

    private void setupViews(View view) {
        patientContentLayout = (LinearLayout) view.findViewById(R.id.patient_page_data_content);
    }

    private void addPatientAttributes() {
        switch (patientStage) {
            case INITIAL_STATE:
                showInitialState();
                break;
            case PRELIMINARY_DIAGNOSIS:
                showPreliminaryDiagnosis();
                break;
            case IMMEDIATE_TREATMENT:
                showImmediateTreatment();
                break;
            case ECG:
                showComplementaryMethod(Constants.Patient.Root.ECG);
                break;
            case RX:
                showComplementaryMethod(Constants.Patient.Root.RX);
                break;
            case LAB_ANALYSIS:
                showComplementaryMethod(Constants.Patient.Root.LAB_ANALYSIS);
                break;

        }
    }

    private void showImmediateTreatment() {
        List<PatientAttribute> preliminaryDiagnosisList = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            if ((root.equals(IMMEDIATE_TREATMENT) || root.equals(IMMEDIATE_DIURETIC_TREATMENT)
                    || root.equals(IMMEDIATE_VASODILATOR_TREATMENT))
                    && attribute.getValue() != null && !TextUtils.isEmpty(attribute.getValue().toString())) {
                preliminaryDiagnosisList.add(attribute);
            }
        }
        addValuesToLayout(preliminaryDiagnosisList);
    }

    private void showComplementaryMethod(String root) {
        List<PatientAttribute> attributesList = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String attributeRoot = attribute.getAttribute().getRootParent();
            if (attributeRoot.equals(root)) {
                attributesList.add(attribute);
            }
        }
        addValuesToLayout(attributesList);
    }


    private void showPreliminaryDiagnosis() {
        List<PatientAttribute> preliminaryDiagnosisList = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            if (root.equals(PRELIMINARY_DIAGNOSIS) && attribute.getValue() != null
                    && !TextUtils.isEmpty(attribute.getValue().toString())) {
                preliminaryDiagnosisList.add(attribute);
            }
        }
        addValuesToLayout(preliminaryDiagnosisList);
    }

    private void showInitialState() {
        List<PatientAttribute> essentialSymptomsList = new ArrayList<>();
        List<PatientAttribute> secondarySymptomsList = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            switch (root) {
                case Constants.Patient.Root.ESSENTIAL_SYMPTOMS:
                    essentialSymptomsList.add(attribute);
                    break;
                case Constants.Patient.Root.SECONDARY_SYMPTOMS:
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

            View viewAttribute;
            if (PatientAttributesUtils.isExtended(attribute)) {
                viewAttribute = new PatientAttributeExtendedView(getActivity());
                ((PatientAttributeExtendedView) viewAttribute).setData(attribute);
            } else {
                viewAttribute = new PatientAttributeView(getActivity());
                ((PatientAttributeView) viewAttribute).setData(attribute);
            }

            patientContentLayout.addView(viewAttribute);

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            slidePreviousFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
