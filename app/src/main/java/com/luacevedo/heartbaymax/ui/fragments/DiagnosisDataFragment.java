package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
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

public class DiagnosisDataFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout diagnosisContentLayout;
    private PatientPageActivity activity;
    private Constants.PatientStage stage;

    public static DiagnosisDataFragment newInstance(Constants.PatientStage stage) {
        DiagnosisDataFragment fragment = new DiagnosisDataFragment();
        fragment.setStage(stage);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (PatientPageActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diagnosis_data, container, false);

        activity = (PatientPageActivity) getActivity();
        diagnosisContentLayout = (LinearLayout) view.findViewById(R.id.diagnosis_data_container);
        TextView title = (TextView) view.findViewById(R.id.diagnosis_data_title);
        title.setText(stage.equals(Constants.PatientStage.PRELIMINARY_DIAGNOSIS) ? R.string.preliminary_diagnosis : R.string.final_diagnosis);

        Button acceptBtn = (Button) view.findViewById(R.id.diagnosis_accept_btn);
        acceptBtn.setOnClickListener(this);

        showDiagnosis();

        activity.getSupportActionBar().hide();

        return view;
    }

    private void showDiagnosis() {
        List<PatientAttribute> diagnosisList = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();

            switch (stage) {
                case PRELIMINARY_DIAGNOSIS:
                    getPreliminaryDiagnosisAttributes(diagnosisList, attribute, root);
                    break;
                case FINAL_DIAGNOSIS:
                    getFinalDiagnosisAttributes(diagnosisList, attribute, root);
                    break;
            }
        }
        addValuesToLayout(diagnosisList);
    }

    private void getFinalDiagnosisAttributes(List<PatientAttribute> diagnosisList, PatientAttribute attribute, String root) {
        if ((root.equals(HEART_SITUATION) || root.equals(FINAL_DIAGNOSIS)
                || root.equals(FINAL_TREATMENT) || root.equals(FINAL_DIURETIC_TREATMENT) ||
                root.equals(FINAL_VASODILATOR_TREATMENT))
                && attribute.getValue() != null && !TextUtils.isEmpty(attribute.getValue().toString())) {
            diagnosisList.add(attribute);
        }
    }

    private void getPreliminaryDiagnosisAttributes(List<PatientAttribute> diagnosisList, PatientAttribute attribute, String root) {
        if ((root.equals(PRELIMINARY_DIAGNOSIS) || root.equals(IMMEDIATE_TREATMENT)
                || root.equals(IMMEDIATE_DIURETIC_TREATMENT) || root.equals(IMMEDIATE_VASODILATOR_TREATMENT))
                && attribute.getValue() != null && !TextUtils.isEmpty(attribute.getValue().toString())) {
            diagnosisList.add(attribute);
        }
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
            diagnosisContentLayout.addView(viewAttribute);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.diagnosis_accept_btn) {
            activity.refreshStages();
            removeCurrentFragment();
            activity.getSupportActionBar().show();
        }
    }

    public void setStage(Constants.PatientStage stage) {
        this.stage = stage;
    }
}