package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.activities.PatientPageActivity;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;

import java.util.ArrayList;
import java.util.List;


public class PreliminaryDiagnosisDataFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout diagnosisContentLayout;
    private PatientPageActivity activity;
    private TextView acceptBtn;


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
        acceptBtn = (TextView) view.findViewById(R.id.preliminary_diagnosis_accept);
        acceptBtn.setOnClickListener(this);
        diagnosisContentLayout.setOnClickListener(this);
        showPreliminaryDiagnosis();

        return view;
    }

    private void showPreliminaryDiagnosis() {
        List<PatientAttribute> preliminaryDiagnosisList = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            if (root.equals(Constants.Patient.Root.PRELIMINARY_DIAGNOSIS) && attribute.getValue() != null && !TextUtils.isEmpty(attribute.getValue().toString())) {
                preliminaryDiagnosisList.add(attribute);
            }
        }
        addValuesToLayout(preliminaryDiagnosisList);
    }


    private void addValuesToLayout(List<PatientAttribute> list) {
        for (PatientAttribute attribute : list) {
            PatientAttributeView viewAttribute = new PatientAttributeView(getActivity());
            viewAttribute.setData(attribute);
            diagnosisContentLayout.addView(viewAttribute);
        }
    }


    @Override
    public void onClick(View v) {
        removeCurrentFragment();
    }


}