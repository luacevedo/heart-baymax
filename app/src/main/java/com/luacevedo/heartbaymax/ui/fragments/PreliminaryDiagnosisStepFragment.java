package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.InputAttribute;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.ui.activities.NewPatientActivity;

import java.util.List;

public class PreliminaryDiagnosisStepFragment extends BaseFragment {

    private LinearLayout formLayout;
    private ScrollView scrollview;
    private List<InputAttribute> stepInputAttributes;
    private NewPatientActivity newPatientActivity;

    public PreliminaryDiagnosisStepFragment newInstance(Bundle args) {
        PreliminaryDiagnosisStepFragment fragment = new PreliminaryDiagnosisStepFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newPatientActivity = (NewPatientActivity) getActivity();
        stepInputAttributes = BundleHelper.fromBundle(savedInstanceState, Constants.BundleKey.STEP_INPUT_ATTRIBUTES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_patient, container, false);
        formLayout = (LinearLayout) view.findViewById(R.id.input_attributes_form_layout);
        scrollview = (ScrollView) view.findViewById(R.id.input_attributes_scrollview);
        return view;
    }
}
