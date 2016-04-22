package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class AssignAction extends BaseAction<String> {

    private String valueToAssign;

    public String getValue() {
        return valueToAssign;
    }

    public void setValueToAssign(String value) {
        this.valueToAssign = value;
    }

    @Override
    public void execute(PatientAttribute<String> attribute) {
        attribute.setValue(valueToAssign);
    }
}

