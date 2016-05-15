package com.luacevedo.heartbaymax.model.patient;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.api.model.patients.Attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Patient {

    private String name;
    private int age;
    private String gender;
    private HashMap<String, PatientAttribute> attributesMap = new HashMap<>();

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
                case Constants.Attribute.Type.INTEGER:
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
}
