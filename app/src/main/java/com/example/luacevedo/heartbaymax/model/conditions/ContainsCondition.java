package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.PatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.ListPatientAttribute;

public class ContainsCondition extends BaseCondition {

    private String value; //el valor que se valida que contenga

    @Override
    public boolean validate(PatientAttribute attribute) {
        if (attribute instanceof ListPatientAttribute) {
            ((ListPatientAttribute) attribute).getValue().contains(value);
        }
        return false;
    }
}
