package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.gson.reflect.TypeToken;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.fields.Value;
import com.luacevedo.heartbaymax.db.InternalDbHelper;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.fragments.ComplementaryMethodsStepFragment;
import com.luacevedo.heartbaymax.utils.InputFieldsUtils;

import java.util.ArrayList;
import java.util.List;

public class ComplementaryMethodsInputActivity extends BaseFragmentActivity {

    private List<StepInputFields> diagnosisFields = new ArrayList<>();
    private Patient patient;
    private int currentStep = 0;
    private InternalDbHelper internalDbHelper = HeartBaymaxApplication.getApplication().getInternalDbHelper();
    private int stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            diagnosisFields = BundleHelper.fromBundleJson(savedInstanceState, Constants.BundleKey.INPUT_FIELDS, new TypeToken<List<StepInputFields>>() {
            }.getType(), new ArrayList<StepInputFields>());
            patient = BundleHelper.fromBundleJson(savedInstanceState, Constants.BundleKey.PATIENT, Patient.class, null);
            stage = BundleHelper.fromBundle(savedInstanceState, Constants.BundleKey.STAGE, 0);
        } else {
            stage = BundleHelper.fromBundle(getIntent().getExtras(), Constants.BundleKey.STAGE, 0);
            patient = BundleHelper.fromBundleJson(getIntent().getExtras(), Constants.BundleKey.PATIENT, Patient.class, null);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleHelper.putJsonBundle(outState, Constants.BundleKey.INPUT_FIELDS, diagnosisFields, new TypeToken<List<StepInputFields>>() {
        }.getType());
        BundleHelper.putJsonBundle(outState, Constants.BundleKey.PATIENT, patient);
        outState.putSerializable(Constants.BundleKey.STAGE, stage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        unlockMenu();
        if (diagnosisFields.isEmpty()) {
            List<StepInputFields> inputFields = internalDbHelper.getStepInputFields();
            diagnosisFields = InputFieldsUtils.filterStageInputFields(inputFields, stage);
        }

        ComplementaryMethodsStepFragment fragment = createStepFragment();
        setInitialFragment(fragment);

    }

    private ComplementaryMethodsStepFragment createStepFragment() {
        List<InputField> inputFields = setCurrentValuesFromPatient();
        return ComplementaryMethodsStepFragment.newInstance(inputFields, isLastStep());
    }

    @NonNull
    private List<InputField> setCurrentValuesFromPatient() {
        List<InputField> inputFields = diagnosisFields.get(currentStep).getInputFields();
        for (InputField inputField : inputFields) {

            PatientAttribute attribute = patient.getAttributesMap().get(inputField.getRootToAffect());
            if (attribute.getValue() != null) {
                String keyToFind;
                if (inputField.getDataType().equals(Constants.InputField.DataType.BOOLEAN)) {
                    keyToFind = (Boolean) attribute.getValue() ? Constants.InputField.Value.TRUE : Constants.InputField.Value.FALSE;
                } else {
                    // in this case I know that the key was saved as it is in the patient attribute
                    keyToFind = attribute.getValue().toString();
                }
                Value value;
                if (inputField.getDataType().equals(Constants.InputField.DataType.STRING) || inputField.getDataType().equals(Constants.InputField.DataType.NUMBER)) {
                    value = new Value(keyToFind);
                } else {
                    value = inputField.getValue(keyToFind);
                }
                inputField.setValue(value);
            }
        }
        return inputFields;
    }

    private boolean isLastStep() {
        return !diagnosisFields.isEmpty() && currentStep == diagnosisFields.size() - 1;
    }

    public void setInputFieldSelectValue(InputField inputField, Value value) {
        PatientAttribute patientAttribute = patient.getAttributesMap().get(inputField.getRootToAffect());
        if (patientAttribute != null) {
            if (inputField.getDataType().equals(Constants.InputField.DataType.BOOLEAN)) {
                patientAttribute.setValue(value.getKey().equals(Constants.InputField.Value.TRUE));
            } else if (inputField.getDataType().equals(Constants.InputField.DataType.SELECT)) {
                patientAttribute.setValue(value.getKey());
            }
        }
        inputField.setValue(value);
    }

    public void setInputFieldTextValue(InputField inputField, String value) {
        PatientAttribute patientAttribute = patient.getAttributesMap().get(inputField.getRootToAffect());
        if (patientAttribute != null) {
            if (inputField.getDataType().equals(Constants.InputField.DataType.NUMBER)) {
                patientAttribute.setValue(Double.valueOf(value));
            } else if (inputField.getDataType().equals(Constants.InputField.DataType.STRING)) {
                patientAttribute.setValue(value);
            }
        }
        inputField.setValue(new Value(value));

    }

    public void finishDiagnosis() {
        finish();
    }

    public void getNextStep() {
        currentStep++;
        slideNextFragmentFromRight(createStepFragment());
    }

    @Override
    public void onBackPressed() {
        if (currentStep == 0) {
            finish();
        } else {
            getBackStep();
        }
    }

    private void getBackStep() {
        currentStep--;
        slideNextFragmentFromLeft(createStepFragment());
    }

}
