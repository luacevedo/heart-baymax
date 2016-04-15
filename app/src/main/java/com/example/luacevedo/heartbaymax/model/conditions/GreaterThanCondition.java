package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.PatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.IntegerPatientAttribute;

public class GreaterThanCondition extends BaseCondition {

    private int min;

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public boolean validate(PatientAttribute attribute) {
        if (attribute instanceof IntegerPatientAttribute) {
            return ((IntegerPatientAttribute) attribute).getValue() > min;
        }
        return false;
    }
}
