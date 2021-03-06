package com.luacevedo.heartbaymax.ui.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.reflect.TypeToken;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.fields.Value;
import com.luacevedo.heartbaymax.db.InternalDbHelper;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.helpers.ResourcesHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.fragments.ComplementaryMethodsStepFragment;
import com.luacevedo.heartbaymax.utils.InputFieldsUtils;

import java.util.ArrayList;
import java.util.List;

import static com.luacevedo.heartbaymax.utils.InputFieldsUtils.*;

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
        setActionBar();
    }

    public void setActionBar() {
        ActionBar bar = getSupportActionBar();
        bar.setTitle(null);
        String title = ResourcesHelper.getString(R.string.patient);
        switch (stage) {
            case STAGE_2:
                title = ResourcesHelper.getString(R.string.ecg);
                break;
            case STAGE_3:
                title = ResourcesHelper.getString(R.string.rx);
                break;
            case STAGE_4:
                title = ResourcesHelper.getString(R.string.lab_analysis);
                break;
        }
        bar.setTitle(title);
        bar.show();
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
                try {
                    Double numberValue = Double.valueOf(value);
                    patientAttribute.setValue(numberValue);
                } catch (Throwable t) {
                    Toast.makeText(this, R.string.enter_valid_value, Toast.LENGTH_LONG).show();
                }
            } else if (inputField.getDataType().equals(Constants.InputField.DataType.STRING)) {
                patientAttribute.setValue(value);
            }
        }
        inputField.setValue(new Value(value));

    }

    public void finishDiagnosis() {
        HeartBaymaxApplication.getApplication().getInternalDbHelper().savePatient(patient);
        Intent intent = this.getIntent();
        BundleHelper.putJsonBundle(intent, Constants.BundleKey.PATIENT, patient);
        this.setResult(RESULT_OK, intent);
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
