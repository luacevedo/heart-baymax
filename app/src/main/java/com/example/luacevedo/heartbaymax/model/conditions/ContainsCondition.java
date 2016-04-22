package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.List;

public class ContainsCondition extends BaseCondition<List<String>> {

    private String value;

    @Override
    public boolean validate(PatientAttribute<List<String>> attribute) {
        return attribute.getValue().toString().contains(value);
    }
}
