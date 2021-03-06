package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.List;

public class NotContainsCondition extends BaseCondition<List<String>> {

    private String valueNotToContain;

    public NotContainsCondition(String attributeRoot, String valueNotToContain) {
        this.attributeRoot = attributeRoot;
        this.valueNotToContain = valueNotToContain;
    }

    @Override
    public boolean validate(PatientAttribute<List<String>> attribute) {
        return !attribute.getValue().toString().contains(valueNotToContain);
    }
}
