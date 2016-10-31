package com.luacevedo.heartbaymax.utils;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.luacevedo.heartbaymax.Constants.Attribute.Type.BOOLEAN;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.NUMBER;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.LIST;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.STRING;

public class PatientAttributesUtils {

    public static HashMap<String, PatientAttribute> parsePatientAttributes(List<Attribute> attributesList) {
        HashMap<String, PatientAttribute> map = new HashMap<>();
        PatientAttribute patientAttribute = null;
        for (Attribute attribute : attributesList) {
            switch (attribute.getDataType()) {
                case BOOLEAN:
                    patientAttribute = new PatientAttribute<>(attribute, false);
                    break;
                case STRING:
                    patientAttribute = new PatientAttribute<>(attribute, "");
                    break;
                case LIST:
                    patientAttribute = new PatientAttribute<>(attribute, new ArrayList<>());
                    break;
                case NUMBER:
                    patientAttribute = new PatientAttribute<>(attribute, 0);
                    break;
            }
            if (patientAttribute != null) {
                map.put(attribute.getRoot(), patientAttribute);
            }
        }

        return map;
    }

}
