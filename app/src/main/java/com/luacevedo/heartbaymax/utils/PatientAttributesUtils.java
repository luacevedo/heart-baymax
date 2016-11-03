package com.luacevedo.heartbaymax.utils;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.luacevedo.heartbaymax.Constants.Attribute.Type.BOOLEAN;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.LIST;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.NUMBER;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.STRING;

public class PatientAttributesUtils {

    public static HashMap<String, PatientAttribute> parsePatientAttributes(List<Attribute> attributesList) {
        HashMap<String, PatientAttribute> map = new HashMap<>();
        PatientAttribute patientAttribute = null;
        for (Attribute attribute : attributesList) {
            patientAttribute = new PatientAttribute<>(attribute);
            if (!attribute.needsInputValue()) {
                switch (attribute.getDataType()) {
                    case BOOLEAN:
                        patientAttribute.setValue(false);
                        break;
                    case STRING:
                        patientAttribute.setValue("");
                        break;
                    case LIST:
                        patientAttribute.setValue(new ArrayList<>());
                        break;
                    case NUMBER:
                        patientAttribute.setValue(0);
                        break;
                }
            }
            map.put(attribute.getRoot(), patientAttribute);
        }

        return map;
    }

}
