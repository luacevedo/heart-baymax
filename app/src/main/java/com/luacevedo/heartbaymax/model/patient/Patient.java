package com.luacevedo.heartbaymax.model.patient;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.api.model.patients.Attribute;

import java.io.Serializable;
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

    public void setAttributesMap(List<Attribute> attributes) {
        attributesMap = new HashMap<>();
        for (Attribute attribute : attributes) {
            PatientAttribute<?> patientAttribute = null;

            switch (attribute.getDataType()) {
                case Constants.Attribute.Type.BOOLEAN:
                    patientAttribute = new PatientAttribute<Boolean>(attribute, true);
                    break;
                case Constants.Attribute.Type.NUMBER:
                    patientAttribute = new PatientAttribute<Integer>(attribute, 0);
                    break;
                case Constants.Attribute.Type.LIST:
                    patientAttribute = new PatientAttribute<List<String>>(attribute, new ArrayList<String>());
                    break;
                case Constants.Attribute.Type.STRING:
                    patientAttribute = new PatientAttribute<String>(attribute, "");
                    break;
            }
            if (patientAttribute != null) {
                attributesMap.put(attribute.getRoot(), patientAttribute);
            }

        }
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
        return false;
    }

    public boolean isRXCompleted() {
        return false;
    }

    public boolean isLabAnalysisCompleted() {
        return false;
    }

    public boolean isFinalDiagnosisCompleted() {
        return false;
    }

    public boolean isFinalDiagnosisEnabled() {
        return isECGCompleted() && isLabAnalysisCompleted() && isRXCompleted();
    }
}
