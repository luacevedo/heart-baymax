package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class AffirmativeCondition extends BaseCondition<Boolean> {

    public AffirmativeCondition(String attributeRoot) {
        this.attributeRoot = attributeRoot;
    }

    @Override
    public boolean validate(PatientAttribute<Boolean> attribute) {
        return attribute.getValue();
    }
}
