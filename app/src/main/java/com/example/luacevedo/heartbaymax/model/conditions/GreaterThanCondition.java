package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class GreaterThanCondition extends BaseCondition<Integer> {

    private int min;

    public void setMin(Integer min) {
        this.min = min;
    }

    @Override
    public boolean validate(PatientAttribute<Integer> attribute) {
        return attribute.getValue() > min;
    }
}
