package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.Value;
import com.luacevedo.heartbaymax.interfaces.OnInputFieldValueChangedListener;
import com.luacevedo.heartbaymax.ui.activities.NewPatientActivity;
import com.luacevedo.heartbaymax.ui.views.controls.InputFieldView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewPatientActivityFragment extends BaseFragment {

    private LinearLayout formContent;
    private ScrollView scrollview;
    private NewPatientActivity newPatientActivity;
    private Map<Long, InputFieldView> newPatientInputFieldsControlsById = new HashMap<>();
    private OnInputFieldValueChangedListener onInputFieldValueChangedListener = getOnInputFieldValueChangedListener();

    public static NewPatientActivityFragment newInstance() {
        return new NewPatientActivityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newPatientActivity = (NewPatientActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_patient, container, false);
        formContent = (LinearLayout) view.findViewById(R.id.new_patient_form_layout);
        scrollview = (ScrollView) view.findViewById(R.id.new_patient_scrollview);
        createNewPatientStepControls();
        return view;
    }

    public void createNewPatientStepControls() {
        List<InputField> stepInputFields = setupNewPatientFields();
        for (InputField inputField : stepInputFields) {
            Long inputFieldId = inputField.getId();
            if (!newPatientInputFieldsControlsById.containsKey(inputFieldId)) {
                InputFieldView inputFieldView = InputFieldView.createInputFieldControl(inputField, onInputFieldValueChangedListener, getContext());
                newPatientInputFieldsControlsById.put(inputFieldId, inputFieldView);
            }
        }
        for (InputFieldView inputFieldView : newPatientInputFieldsControlsById.values()) {
            if (inputFieldView.getParent() == null) {
                inputFieldView.initialize();
                formContent.addView(inputFieldView);
            }
        }
    }

    private List<InputField> setupNewPatientFields() {
        List<InputField> stepInputFields = new ArrayList<>();
        return stepInputFields;

    }

    private OnInputFieldValueChangedListener getOnInputFieldValueChangedListener() {
        return new OnInputFieldValueChangedListener() {
            @Override
            public void valueSelectChanged(InputField inputField, Value attributeValue) {

            }

            @Override
            public void valueTextChanged(InputField inputField, String attributeValueText) {
                if (!TextUtils.isEmpty(attributeValueText) && newPatientActivity != null) {
                    newPatientActivity.setInputFieldTextValue(inputField, attributeValueText);
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
