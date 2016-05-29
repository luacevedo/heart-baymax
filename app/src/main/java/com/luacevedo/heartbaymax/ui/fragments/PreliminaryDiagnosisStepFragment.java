package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreliminaryDiagnosisStepFragment extends BaseFragment implements View.OnClickListener {

    private TextView nextBtn;
    private TextView finishBtn;
    private List<InputField> stepInputFields;
    private PreliminaryDiagnosisActivity preliminaryDiagnosisActivity;
    private Map<Long, InputFieldView> inputFieldsControlsById = new HashMap<>();
    private OnInputFieldValueChangedListener onInputFieldValueChangedListener = getOnInputFieldValueChangedListener();

    private boolean isLastStep;
    private LinearLayout formContent;

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
        createStepControls();
        return view;
    }

    private void setupViews(View view) {
        formContent = (LinearLayout) view.findViewById(R.id.diagnosis_form_container);
        nextBtn = (TextView) view.findViewById(R.id.diagnosis_next_step);
        nextBtn.setText(isLastStep ? ResourcesHelper.getString(R.string.finish_step) : ResourcesHelper.getString(R.string.next_step));
    }

    public void createStepControls() {
        for (InputField inputField : stepInputFields) {
            Long inputFieldId = inputField.getId();
            if (!inputFieldsControlsById.containsKey(inputFieldId)) {
                InputFieldView inputFieldView = createInputFieldControl(inputField);
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

    public InputFieldView createInputFieldControl(InputField inputField) {
        InputFieldView inputFieldView;
        switch (inputField.getFieldType()) {
            case Constants.InputField.FieldType.COMBOBOX:
                inputFieldView = new SelectFieldView(getContext(), inputField);
                break;
            case Constants.InputField.FieldType.TEXT:
                inputFieldView = new TextFieldView(getContext(), inputField);
                break;
            default:
                return null;
        }

        inputFieldView.setOnInputFieldValueChangedListener(onInputFieldValueChangedListener);
        return inputFieldView;
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
                preliminaryDiagnosisActivity.nextStep();
            }
        }
    }

    private OnInputFieldValueChangedListener getOnInputFieldValueChangedListener() {
        return new OnInputFieldValueChangedListener() {
            @Override
            public void valueSelectChanged(InputField inputField, Value attributeValue) {
                if (preliminaryDiagnosisActivity != null) {
                    preliminaryDiagnosisActivity.setInputFieldSelectValue(inputField, attributeValue);
                }
            }

            @Override
            public void valueTextChanged(InputField inputField, String attributeValueText) {
                if (!TextUtils.isEmpty(attributeValueText) && preliminaryDiagnosisActivity != null) {
                    preliminaryDiagnosisActivity.setInputFieldTextValue(inputField, attributeValueText);
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
