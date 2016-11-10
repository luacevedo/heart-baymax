package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class LessOrEqualThanCondition extends BaseCondition<Double> {

    private Double max;

    public LessOrEqualThanCondition(String attributeRoot, Double max) {
        this.attributeRoot = attributeRoot;
        this.max = max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Override
    public boolean validate(PatientAttribute<Double> attribute) {
        return attribute.getValue() <= max;
    }
}
