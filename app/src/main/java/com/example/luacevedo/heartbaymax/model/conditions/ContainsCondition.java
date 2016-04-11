package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.BasePatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.ListPatientAttribute;

public class ContainsCondition extends BaseCondition {

    private String value; //el valor que se valida que contenga

    @Override
    public boolean validate(BasePatientAttribute attribute) {
        if (attribute instanceof ListPatientAttribute) {
            ((ListPatientAttribute) attribute).getValue().contains(value);
        }
        return false;
    }
}
