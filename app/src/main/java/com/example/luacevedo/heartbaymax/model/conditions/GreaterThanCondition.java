package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.PatientAttribute;

public class GreaterThanCondition extends BaseCondition {

    private int min;

    public void setMin(Integer min) {
        this.min = min;
    }

    @Override
    public <Integer> java.lang.Boolean validate(PatientAttribute<Integer> intAttribute) {
        return java.lang.Integer.parseInt(intAttribute.getValue().toString()) > min;
    }
}
