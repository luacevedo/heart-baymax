package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.BasePatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.IntegerPatientAttribute;

public class LessThanCondition extends BaseCondition {

    private int max;

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean validate(BasePatientAttribute attribute) {
        if (attribute instanceof IntegerPatientAttribute) {
            return ((IntegerPatientAttribute) attribute).getValue() < max;
        }
        return false;
    }
}
