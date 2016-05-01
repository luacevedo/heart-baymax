package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class LessThanCondition extends BaseCondition<Integer> {

    private int max;

    public LessThanCondition(String attributeRoot, int max) {
        this.attributeRoot = attributeRoot;
        this.max = max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean validate(PatientAttribute<Integer> attribute) {
        return attribute.getValue() < max;
    }
}
