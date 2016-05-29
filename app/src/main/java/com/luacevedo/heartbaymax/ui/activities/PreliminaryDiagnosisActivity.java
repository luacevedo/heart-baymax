package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.api.HeartBaymaxApi;
import com.luacevedo.heartbaymax.api.baseapi.CallId;
import com.luacevedo.heartbaymax.api.baseapi.CallOrigin;
import com.luacevedo.heartbaymax.api.baseapi.CallType;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.fields.Value;
import com.luacevedo.heartbaymax.helpers.BundleHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.fragments.PreliminaryDiagnosisStepFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PreliminaryDiagnosisActivity extends BaseFragmentActivity {

    private HeartBaymaxApi heartBaymaxApi = HeartBaymaxApplication.getApplication().getHeartBaymaxApi();
    private List<StepInputFields> preliminaryDiagnosisFields = new ArrayList<>();
    private Patient patient;
    //    private List<PreliminaryDiagnosisStepFragment> fragmentsStack = new ArrayList<>();
    private int currentStep = 0;


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
        if (!preliminaryDiagnosisFields.isEmpty()) {
            //this will then be in success callback
            createStepFragment();
        } else {
            getPreliminaryDiagnosisFields();
        }
    }

    private void createStepFragment() {
        PreliminaryDiagnosisStepFragment fragment;
        fragment = PreliminaryDiagnosisStepFragment.newInstance(preliminaryDiagnosisFields.get(currentStep).getInputFields(), isLastStep());
        setInitialFragment(fragment);
    }

    private boolean isLastStep() {
        return !preliminaryDiagnosisFields.isEmpty() && currentStep == preliminaryDiagnosisFields.size() - 1;
    }

    private void getPreliminaryDiagnosisFields() {
//        preliminaryDiagnosisFields = MockInfo.getPreliminaryDiagnosisFields();
        CallId callId = new CallId(CallOrigin.PRELIMINARY_DIAGNOSIS, CallType.INPUT_FIELDS);
        heartBaymaxApi.getPatientStepInputFields(callId, getPatientStepInputFieldsCallback());
    }

    private Callback<List<StepInputFields>> getPatientStepInputFieldsCallback() {
        return new Callback<List<StepInputFields>>() {
            @Override
            public void success(List<StepInputFields> inputFields, Response response) {
                preliminaryDiagnosisFields = inputFields;
                createStepFragment();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("LULI", error.toString());
            }
        };
    }

    public void setInputFieldSelectValue(InputField inputField, Value value) {
        PatientAttribute patientAttribute = patient.getAttributesMap().get(inputField.getRootToAffect());
        if (patientAttribute != null) {
            if (inputField.getDataType() == Constants.InputField.DataType.BOOLEAN) {
                patientAttribute.setValue(value.getKey() == Constants.InputField.Value.TRUE);
            }
        }
    }

    public void setInputFieldTextValue(InputField inputField, String value) {
        PatientAttribute patientAttribute = patient.getAttributesMap().get(inputField.getRootToAffect());
        if (patientAttribute != null) {
            if (inputField.getDataType() == Constants.InputField.DataType.INTEGER) {
                patientAttribute.setValue(Integer.parseInt(value));
            } else if (inputField.getDataType() == Constants.InputField.DataType.STRING) {
                patientAttribute.setValue(value);
            }
        }

    }

    public void finishDiagnosis() {

    }

    public void nextStep() {
        PreliminaryDiagnosisStepFragment fragment;
        fragment = PreliminaryDiagnosisStepFragment.newInstance(preliminaryDiagnosisFields.get(currentStep).getInputFields(), isLastStep());
        slideNextFragment(fragment);
    }
}
