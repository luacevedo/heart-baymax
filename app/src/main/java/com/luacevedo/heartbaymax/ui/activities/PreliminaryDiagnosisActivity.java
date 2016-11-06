package com.luacevedo.heartbaymax.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.MochiApi;
import com.luacevedo.heartbaymax.api.baseapi.CallId;
import com.luacevedo.heartbaymax.api.baseapi.CallOrigin;
import com.luacevedo.heartbaymax.api.baseapi.CallType;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.fields.Value;
import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.db.InternalDbHelper;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.fragments.PreliminaryDiagnosisStepFragment;
import com.luacevedo.heartbaymax.utils.InputFieldsUtils;
import com.luacevedo.heartbaymax.utils.RulesExecutor;
import com.luacevedo.heartbaymax.utils.RulesUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PreliminaryDiagnosisActivity extends BaseFragmentActivity {

    private List<StepInputFields> preliminaryDiagnosisFields = new ArrayList<>();
    private Patient patient;
    private int currentStep = 0;
    private ProgressDialog progress;
    private MochiApi mochiApi = HeartBaymaxApplication.getApplication().getMochiApi();
    private InternalDbHelper internalDbHelper = HeartBaymaxApplication.getApplication().getInternalDbHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            preliminaryDiagnosisFields = BundleHelper.fromBundleJson(savedInstanceState, Constants.BundleKey.INPUT_FIELDS, new TypeToken<List<StepInputFields>>() {
            }.getType(), new ArrayList<StepInputFields>());
            patient = BundleHelper.fromBundleJson(savedInstanceState, Constants.BundleKey.PATIENT, Patient.class, null);
        } else {
            patient = BundleHelper.fromBundleJson(getIntent().getExtras(), Constants.BundleKey.PATIENT, Patient.class, null);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleHelper.putJsonBundle(outState, Constants.BundleKey.INPUT_FIELDS, preliminaryDiagnosisFields, new TypeToken<List<StepInputFields>>() {
        }.getType());
        BundleHelper.putJsonBundle(outState, Constants.BundleKey.PATIENT, patient);
    }

    @Override
    protected void onResume() {
        super.onResume();
        unlockMenu();
        if (preliminaryDiagnosisFields.isEmpty()) {
            getPreliminaryDiagnosisFields();
        }
    }

    private PreliminaryDiagnosisStepFragment createStepFragment() {
        List<InputField> inputFields = setCurrentValuesFromPatient();

        return PreliminaryDiagnosisStepFragment.newInstance(inputFields, isLastStep());
    }

    @NonNull
    private List<InputField> setCurrentValuesFromPatient() {
        List<InputField> inputFields = preliminaryDiagnosisFields.get(currentStep).getInputFields();
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
                if (inputField.getDataType().equals(Constants.InputField.DataType.STRING)
                        || inputField.getDataType().equals(Constants.InputField.DataType.NUMBER)) {
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
        return !preliminaryDiagnosisFields.isEmpty() && currentStep == preliminaryDiagnosisFields.size() - 1;
    }

    private void getPreliminaryDiagnosisFields() {
        progress = ProgressDialog.show(this, null, getString(R.string.loading), true);
        CallId callId = new CallId(CallOrigin.PRELIMINARY_DIAGNOSIS, CallType.INPUT_FIELDS);
        mochiApi.getPatientStepInputFields(callId, getPatientStepInputFieldsCallback());
    }

    private Callback<List<StepInputFields>> getPatientStepInputFieldsCallback() {
        return new Callback<List<StepInputFields>>() {
            @Override
            public void success(List<StepInputFields> inputFields, Response response) {
                List<StepInputFields> orderedFields = InputFieldsUtils.orderInputFields(inputFields);
                internalDbHelper.saveStepInputFields(orderedFields);
                preliminaryDiagnosisFields = InputFieldsUtils.filterStageInputFields(orderedFields, InputFieldsUtils.STAGE_1);
                PreliminaryDiagnosisStepFragment fragment = createStepFragment();
                setInitialFragment(fragment);
                progress.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("LULI", error.toString());
                progress.dismiss();
            }
        };
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
        getRules();
    }

    private void getRules() {
        progress = ProgressDialog.show(this, null, getString(R.string.loading), true);
        CallId callId = new CallId(CallOrigin.RULES_EXECUTION_STAGE_1, CallType.RULES);
        mochiApi.getRules(callId, getRulesCallback());
    }

    private Callback<List<Rule>> getRulesCallback() {
        return new Callback<List<Rule>>() {
            @Override
            public void success(List<Rule> rules, Response response) {
                List<Rule> firstStageRules = RulesUtils.getRulesForStage(rules, RulesUtils.STAGE_1);
                RulesExecutor.executeRules(firstStageRules, patient);
                HeartBaymaxApplication.getApplication().getInternalDbHelper().savePatient(patient);
                startActivity(IntentFactory.getPatientPageActivityIntent(patient, true));
                finish();
                progress.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("LULI", error.toString());
            }
        };
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
