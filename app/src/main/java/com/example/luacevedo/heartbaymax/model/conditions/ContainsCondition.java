package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class ContainsCondition extends BaseCondition {

    private String value; //el valor que se valida que contenga

    @Override
    public <String> Boolean validate(PatientAttribute<String> attribute) {
        return attribute.getValue().toString().contains(value);
    }
}
