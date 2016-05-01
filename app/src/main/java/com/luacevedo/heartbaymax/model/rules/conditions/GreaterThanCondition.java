package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class GreaterThanCondition extends BaseCondition<Integer> {

    private int min;

    public GreaterThanCondition(String attributeRoot, int min) {
        this.attributeRoot = attributeRoot;
        this.min = min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    @Override
    public boolean validate(PatientAttribute<Integer> attribute) {
        return attribute.getValue() > min;
    }
}
