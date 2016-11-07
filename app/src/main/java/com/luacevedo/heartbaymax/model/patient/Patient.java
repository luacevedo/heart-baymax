package com.luacevedo.heartbaymax.model.patient;

import android.text.TextUtils;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.api.model.patients.Attribute;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Patient implements Serializable {

    private Long id;
    private HashMap<String, PatientAttribute> attributesMap = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<String, PatientAttribute> getAttributesMap() {
        return attributesMap;
    }

    public void setAttributesMap(HashMap<String, PatientAttribute> attributesMap) {
        this.attributesMap = attributesMap;
    }

    public String getName() {
        return attributesMap.get("PatientData.Name").getValue() != null ? attributesMap.get("PatientData.Name").getValue().toString() : "";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (PatientAttribute p : attributesMap.values()) {
            builder.append(p.getAttribute().getRoot() + " = " + p.getValue() + "\n");
        }
        return builder.toString();
    }

    public String getGender() {
        return attributesMap.get("PatientData.Gender").getValue() != null ? attributesMap.get("PatientData.Gender").getValue().toString() : "";
    }

    public String print() {
        StringBuilder builder = new StringBuilder();
        for (PatientAttribute p : attributesMap.values()) {
            builder.append(p.getAttribute().getRoot() + " = " + p.getValue() + "\n");
        }
        return builder.toString();
    }

    public boolean isECGCompleted() {
        return attributesMap.get(Constants.Attribute.ECG.HEART_RATE).getValue() != null
                && attributesMap.get(Constants.Attribute.ECG.ISCHEMIA).getValue() != null
                && attributesMap.get(Constants.Attribute.ECG.ARRHYTHMIA).getValue() != null
                && attributesMap.get(Constants.Attribute.ECG.ATRIAL_FIBRILLATION).getValue() != null;
    }

    public boolean isRXCompleted() {
        return attributesMap.get(Constants.Attribute.Rx.KERLEY_LINES).getValue() != null
                && attributesMap.get(Constants.Attribute.Rx.PLEURAL_EFFUSION).getValue() != null
                && attributesMap.get(Constants.Attribute.Rx.CARDIOMEGALY).getValue() != null
                && attributesMap.get(Constants.Attribute.Rx.FLOW_REDISTRIBUTION).getValue() != null;
    }

    public boolean isLabAnalysisCompleted() {
        return attributesMap.get(Constants.Attribute.LabAnalysis.SODIUM).getValue() != null
                && attributesMap.get(Constants.Attribute.LabAnalysis.POTASSIUM).getValue() != null
                && attributesMap.get(Constants.Attribute.LabAnalysis.UREMIA).getValue() != null
                && attributesMap.get(Constants.Attribute.LabAnalysis.CREATININE).getValue() != null
                && attributesMap.get(Constants.Attribute.LabAnalysis.RED_BLOOD_CELLS).getValue() != null
                && attributesMap.get(Constants.Attribute.LabAnalysis.WHITE_BLOOD_CELLS).getValue() != null;
    }

    public boolean isFinalDiagnosisCompleted() {
        return !TextUtils.isEmpty((String) attributesMap.get(Constants.Attribute.FinalDiagnosis.IC_CLASS).getValue());
    }

    public boolean isFinalDiagnosisEnabled() {
        return isECGCompleted() && isLabAnalysisCompleted() && isRXCompleted();
    }

    public String getAge() {
        Object age = attributesMap.get("PatientData.Age").getValue();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return age != null ? decimalFormat.format(age) : "";
    }
}
