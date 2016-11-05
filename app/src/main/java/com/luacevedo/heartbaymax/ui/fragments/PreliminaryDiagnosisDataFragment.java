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

import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.activities.PatientPageActivity;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeExtendedView;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;
import com.luacevedo.heartbaymax.utils.PatientAttributesUtils;

import java.util.ArrayList;
import java.util.List;

import static com.luacevedo.heartbaymax.Constants.Patient.Root.*;

public class PreliminaryDiagnosisDataFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout diagnosisContentLayout;
    private PatientPageActivity activity;
    private Button acceptBtn;


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
        View view = inflater.inflate(R.layout.fragment_preliminary_diagnosis_data, container, false);

        activity = (PatientPageActivity) getActivity();
        diagnosisContentLayout = (LinearLayout) view.findViewById(R.id.preliminary_diagnosis_data_container);
        acceptBtn = (Button) view.findViewById(R.id.preliminary_diagnosis_accept_btn);
        acceptBtn.setOnClickListener(this);
        showPreliminaryDiagnosis();

        return view;
    }

    private void showPreliminaryDiagnosis() {
        List<PatientAttribute> preliminaryDiagnosisList = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            if ((root.equals(PRELIMINARY_DIAGNOSIS) || root.equals(IMMEDIATE_TREATMENT)
                    || root.equals(IMMEDIATE_DIURETIC_TREATMENT) || root.equals(IMMEDIATE_VASODILATOR_TREATMENT))
                    && attribute.getValue() != null && !TextUtils.isEmpty(attribute.getValue().toString())) {
                preliminaryDiagnosisList.add(attribute);
            }
        }
        addValuesToLayout(preliminaryDiagnosisList);
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
        if (v.getId() == R.id.preliminary_diagnosis_accept_btn) {
            removeCurrentFragment();
        }
    }


}