package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.ResourcesHelper;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.activities.PatientPageActivity;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeExtendedView;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;
import com.luacevedo.heartbaymax.utils.PatientAttributesUtils;

import java.util.ArrayList;
import java.util.List;

import static com.luacevedo.heartbaymax.Constants.Patient.Root.FINAL_DIAGNOSIS;
import static com.luacevedo.heartbaymax.Constants.Patient.Root.FINAL_DIURETIC_TREATMENT;
import static com.luacevedo.heartbaymax.Constants.Patient.Root.FINAL_TREATMENT;
import static com.luacevedo.heartbaymax.Constants.Patient.Root.FINAL_VASODILATOR_TREATMENT;
import static com.luacevedo.heartbaymax.Constants.Patient.Root.HEART_SITUATION;
import static com.luacevedo.heartbaymax.Constants.Patient.Root.IMMEDIATE_DIURETIC_TREATMENT;
import static com.luacevedo.heartbaymax.Constants.Patient.Root.IMMEDIATE_TREATMENT;
import static com.luacevedo.heartbaymax.Constants.Patient.Root.IMMEDIATE_VASODILATOR_TREATMENT;
import static com.luacevedo.heartbaymax.Constants.Patient.Root.PRELIMINARY_DIAGNOSIS;

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
        setupAttributesByStage();
        return view;
    }

    private void setupViews(View view) {
        patientContentLayout = (LinearLayout) view.findViewById(R.id.patient_page_data_content);
    }

    private void setupAttributesByStage() {
        ActionBar bar = activity.getSupportActionBar();
        bar.setTitle(null);
        switch (patientStage) {
            case INITIAL_STATE:
                bar.setTitle(R.string.see_initial_situation);
                showInitialState();
                break;
            case PRELIMINARY_DIAGNOSIS:
                bar.setTitle(R.string.preliminary_diagnosis);
                showPreliminaryDiagnosis();
                break;
            case IMMEDIATE_TREATMENT:
                bar.setTitle(R.string.immediate_treatment);
                showImmediateTreatment();
                break;
            case ECG:
                bar.setTitle(R.string.ecg);
                showComplementaryMethod(Constants.Patient.Root.ECG);
                break;
            case RX:
                bar.setTitle(R.string.rx);
                showComplementaryMethod(Constants.Patient.Root.RX);
                break;
            case LAB_ANALYSIS:
                bar.setTitle(R.string.lab_analysis);
                showComplementaryMethod(Constants.Patient.Root.LAB_ANALYSIS);
                break;
            case FINAL_DIAGNOSIS:
                bar.setTitle(R.string.final_diagnosis);
                showFinalDiagnosis();
                break;

        }
    }

    private void showFinalDiagnosis() {
        List<PatientAttribute> attributes = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            if ((root.equals(HEART_SITUATION) || root.equals(FINAL_DIAGNOSIS)
                    || root.equals(FINAL_TREATMENT) || root.equals(FINAL_DIURETIC_TREATMENT)
                    || root.equals(FINAL_VASODILATOR_TREATMENT))
                    && attribute.getValue() != null && !TextUtils.isEmpty(attribute.getValue().toString())) {
                attributes.add(attribute);
            }
        }
        PatientAttributesUtils.orderDiagnosisAttributes(attributes);
        addValuesToLayout(attributes);
    }

    private void showImmediateTreatment() {
        List<PatientAttribute> attributes = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            if ((root.equals(IMMEDIATE_TREATMENT) || root.equals(IMMEDIATE_DIURETIC_TREATMENT)
                    || root.equals(IMMEDIATE_VASODILATOR_TREATMENT))
                    && attribute.getValue() != null && !TextUtils.isEmpty(attribute.getValue().toString())) {
                attributes.add(attribute);
            }
        }
        PatientAttributesUtils.orderDiagnosisAttributes(attributes);
        addValuesToLayout(attributes);
    }

    private void showComplementaryMethod(String root) {
        List<PatientAttribute> attributes = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String attributeRoot = attribute.getAttribute().getRootParent();
            if (attributeRoot.equals(root)) {
                attributes.add(attribute);
            }
        }
        PatientAttributesUtils.orderDiagnosisAttributes(attributes);
        addValuesToLayout(attributes);
    }


    private void showPreliminaryDiagnosis() {
        List<PatientAttribute> attributes = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            if (root.equals(PRELIMINARY_DIAGNOSIS) && attribute.getValue() != null
                    && !TextUtils.isEmpty(attribute.getValue().toString())) {
                attributes.add(attribute);
            }
        }
        PatientAttributesUtils.orderDiagnosisAttributes(attributes);
        addValuesToLayout(attributes);
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
        PatientAttributesUtils.orderDiagnosisAttributes(essentialSymptomsList);
        PatientAttributesUtils.orderDiagnosisAttributes(secondarySymptomsList);
        addValuesToLayout(essentialSymptomsList);
        addValuesToLayout(secondarySymptomsList);
    }

    private void addValuesToLayout(List<PatientAttribute> list) {
        if (list.isEmpty()) {
            TextView viewAttribute = new TextView(getActivity());
            viewAttribute.setTextSize(ResourcesHelper.getDimensionPixelSize(R.dimen.no_treatment_size));
            viewAttribute.setText(R.string.no_treatment);
            patientContentLayout.addView(viewAttribute);
            return;
        }
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
