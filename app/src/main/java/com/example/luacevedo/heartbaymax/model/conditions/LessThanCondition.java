package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.PatientAttribute;

public class LessThanCondition extends BaseCondition {

    private int max;

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public <Integer> java.lang.Boolean validate(PatientAttribute<Integer> intAttribute) {
        return java.lang.Integer.parseInt(intAttribute.getValue().toString()) < max;
    }
}
