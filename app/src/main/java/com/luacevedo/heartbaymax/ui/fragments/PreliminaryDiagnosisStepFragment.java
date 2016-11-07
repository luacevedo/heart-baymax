package com.luacevedo.heartbaymax.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.Value;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.helpers.ResourcesHelper;
import com.luacevedo.heartbaymax.interfaces.OnInputFieldValueChangedListener;
import com.luacevedo.heartbaymax.ui.activities.PreliminaryDiagnosisActivity;
import com.luacevedo.heartbaymax.ui.views.controls.InputFieldView;
import com.luacevedo.heartbaymax.ui.views.controls.SelectFieldView;
import com.luacevedo.heartbaymax.ui.views.controls.TextFieldView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PreliminaryDiagnosisStepFragment extends BaseFragment implements View.OnClickListener {

    private static final float NEXT_BUTTON_SHOW_ALPHA = 1.0f;
    private static final float NEXT_BUTTON_HIDE_ALPHA = 0.0f;
    private static final int NEXT_BUTTON_ANIMATION_DURATION = 70;

    private TextView nextBtn;
    private LinearLayout formContent;

    private OnInputFieldValueChangedListener onInputFieldValueChangedListener = getOnInputFieldValueChangedListener();

    private boolean isLastStep;
    private List<InputField> stepInputFields;
    private PreliminaryDiagnosisActivity preliminaryDiagnosisActivity;
    private Map<Integer, InputFieldView> inputFieldsControlsById = new TreeMap<>();

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
            stepInputFields = BundleHelper.fromBundleJson(savedInstanceState, Constants.BundleKey.STEP_INPUT_ATTRIBUTES, new TypeToken<List<InputField>>() {
            }.getType(), new ArrayList<InputField>());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleHelper.putJsonBundle(outState, Constants.BundleKey.STEP_INPUT_ATTRIBUTES, stepInputFields, new TypeToken<List<InputField>>() {
        }.getType());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diagnosis_step, container, false);
        setupViews(view);
        createStepControls();
        return view;
    }

    private void setupViews(View view) {
        formContent = (LinearLayout) view.findViewById(R.id.diagnosis_form_container);
        nextBtn = (TextView) view.findViewById(R.id.diagnosis_next_step);
        nextBtn.setText(isLastStep ? ResourcesHelper.getString(R.string.finish_step) : ResourcesHelper.getString(R.string.next_step));
        nextBtn.setOnClickListener(this);
        nextBtn.setVisibility(isStepFinished() ? View.VISIBLE : View.GONE);
    }

    private void setNextButtonStatus() {
        if (nextBtn != null) {
            if (isStepFinished()) {
                nextBtn.setVisibility(View.VISIBLE);
                nextBtn.animate().alpha(NEXT_BUTTON_SHOW_ALPHA).setDuration(NEXT_BUTTON_ANIMATION_DURATION);
            } else {
                nextBtn.animate().alpha(NEXT_BUTTON_HIDE_ALPHA).setDuration(NEXT_BUTTON_ANIMATION_DURATION)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                if (!isStepFinished()) {
                                    nextBtn.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        }
    }

    private boolean isStepFinished() {
        boolean isFinished = true;
        for (InputField inputField : stepInputFields) {
            isFinished &= inputField.getValue() != null;
        }
        return isFinished;
    }

    public void createStepControls() {
        for (InputField inputField : stepInputFields) {
            int inputFieldId = inputField.getId();
            if (!inputFieldsControlsById.containsKey(inputFieldId)) {
                InputFieldView inputFieldView = InputFieldView.createInputFieldControl(inputField, onInputFieldValueChangedListener, getContext());
                inputFieldsControlsById.put(inputFieldId, inputFieldView);
            }
        }
        for (InputFieldView inputFieldView : inputFieldsControlsById.values()) {
            if (inputFieldView.getParent() == null) {
                inputFieldView.initialize();
                formContent.addView(inputFieldView);
            }
        }
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
            if (isLastStep) {
                preliminaryDiagnosisActivity.finishDiagnosis();
            } else {
                preliminaryDiagnosisActivity.getNextStep();
            }
        }
    }

    private OnInputFieldValueChangedListener getOnInputFieldValueChangedListener() {
        return new OnInputFieldValueChangedListener() {
            @Override
            public void valueSelectChanged(InputField inputField, Value attributeValue) {
                if (preliminaryDiagnosisActivity != null && attributeValue != null) {
                    preliminaryDiagnosisActivity.setInputFieldSelectValue(inputField, attributeValue);
                    setNextButtonStatus();
                }
            }

            @Override
            public void valueTextChanged(InputField inputField, String attributeValueText) {
                if (!TextUtils.isEmpty(attributeValueText) && preliminaryDiagnosisActivity != null) {
                    preliminaryDiagnosisActivity.setInputFieldTextValue(inputField, attributeValueText);
                    setNextButtonStatus();
                }
            }

            @Override
            public void valueSelectionStart() {
                if (getActivity().getCurrentFocus() != null) {
                    HeartBaymaxApplication.hideKeyboard(getActivity().getCurrentFocus().getWindowToken());
                }
            }
        };
    }

}
