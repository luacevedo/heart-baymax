package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class AffirmativeCondition extends BaseCondition<Boolean> {

    public AffirmativeCondition(String attributeRoot) {
        this.attributeRoot = attributeRoot;
    }

    @Override
    public boolean validate(PatientAttribute<Boolean> attribute) {
        return attribute.getValue();
    }
}
