package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.ui.activities.PreliminaryDiagnosisActivity;

import java.util.List;

public class PreliminaryDiagnosisStepFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout formLayout;
    private ScrollView scrollview;
    private TextView nextBtn;
    private TextView finishBtn;
    private List<InputField> stepInputFields;
    private PreliminaryDiagnosisActivity preliminaryDiagnosisActivity;
    private boolean isLastStep;

    public static PreliminaryDiagnosisStepFragment newInstance(List<InputField> stepInputFields, boolean isLastStep) {
        PreliminaryDiagnosisStepFragment fragment = new PreliminaryDiagnosisStepFragment();
        fragment.setStepInputFields(stepInputFields);
        fragment.setIsLastStep(isLastStep);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preliminaryDiagnosisActivity = (PreliminaryDiagnosisActivity) getActivity();
        if (savedInstanceState != null) {
            stepInputFields = BundleHelper.fromBundle(savedInstanceState, Constants.BundleKey.STEP_INPUT_ATTRIBUTES);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleHelper.putJsonBundle(outState, Constants.BundleKey.STEP_INPUT_ATTRIBUTES, stepInputFields);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diagnosis_step, container, false);
        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        scrollview = (ScrollView) view.findViewById(R.id.diagnosis_step_scroll);
        formLayout = (LinearLayout) view.findViewById(R.id.diagnosis_form_container);
        nextBtn = (TextView) view.findViewById(R.id.diagnosis_next_step);
        nextBtn.setVisibility(isLastStep ? View.GONE : View.VISIBLE);
        nextBtn.setOnClickListener(this);
        finishBtn = (TextView) view.findViewById(R.id.diagnosis_finish_step);
        finishBtn.setVisibility(isLastStep ? View.VISIBLE : View.GONE);
        finishBtn.setOnClickListener(this);
    }

    public void setStepInputFields(List<InputField> stepInputFields) {
        this.stepInputFields = stepInputFields;
    }

    public void setIsLastStep(boolean isLastStep) {
        this.isLastStep = isLastStep;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.diagnosis_next_step) {
            preliminaryDiagnosisActivity.nextStep();
        } else if (v.getId() == R.id.diagnosis_finish_step) {
            preliminaryDiagnosisActivity.finishDiagnosis();
        }
    }
}
