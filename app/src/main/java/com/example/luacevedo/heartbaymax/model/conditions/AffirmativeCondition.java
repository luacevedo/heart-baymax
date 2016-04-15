package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.PatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.BooleanPatientAttribute;

public class AffirmativeCondition extends BaseCondition {

    public boolean validate(PatientAttribute<Boolean> attribute) {
        if (attribute instanceof BooleanPatientAttribute) {
            return ((BooleanPatientAttribute) attribute).getValue();
        }
        return false;
    }
}
