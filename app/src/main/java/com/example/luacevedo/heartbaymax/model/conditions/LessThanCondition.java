package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class LessThanCondition extends BaseCondition<Integer> {

    private int max;

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean validate(PatientAttribute<Integer> attribute) {
        return attribute.getValue() < max;
    }
}
