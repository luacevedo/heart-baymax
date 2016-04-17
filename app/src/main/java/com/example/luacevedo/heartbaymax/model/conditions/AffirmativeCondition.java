package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class AffirmativeCondition extends BaseCondition {

    @Override
    public <Boolean> java.lang.Boolean validate(PatientAttribute<Boolean> attribute) {
        return (java.lang.Boolean) attribute.getValue();
    }
}
