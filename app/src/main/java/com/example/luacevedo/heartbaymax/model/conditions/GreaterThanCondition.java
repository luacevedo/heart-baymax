package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.BasePatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.IntegerPatientAttribute;

public class GreaterThanCondition extends BaseCondition {

    private int min;

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public boolean validate(BasePatientAttribute attribute) {
        if (attribute instanceof IntegerPatientAttribute) {
            return ((IntegerPatientAttribute) attribute).getValue() > min;
        }
        return false;
    }
}
