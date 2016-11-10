package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class GreaterThanCondition extends BaseCondition<Double> {

    private Double min;

    public GreaterThanCondition(String attributeRoot, Double min) {
        this.attributeRoot = attributeRoot;
        this.min = min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    @Override
    public boolean validate(PatientAttribute<Double> attribute) {
        return attribute.getValue() > min;
    }
}
