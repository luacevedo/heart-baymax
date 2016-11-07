package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class EqualsCondition extends BaseCondition<String> {

    private String value;

    public EqualsCondition(String attributeRoot, String value) {
        this.attributeRoot = attributeRoot;
        this.value = value;
    }

    @Override
    public boolean validate(PatientAttribute<String> attribute) {
        return attribute.getValue().toString().equals(value);
    }
}
