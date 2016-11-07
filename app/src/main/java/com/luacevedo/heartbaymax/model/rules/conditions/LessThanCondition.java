package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class LessThanCondition extends BaseCondition<Double> {

    private Double max;

    public LessThanCondition(String attributeRoot, Double max) {
        this.attributeRoot = attributeRoot;
        this.max = max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Override
    public boolean validate(PatientAttribute<Double> attribute) {
        return attribute.getValue() < max;
    }
}
