package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class AssignAction extends BaseAction {

    private String valueToAssign;

    public String getValue() {
        return valueToAssign;
    }

    public void setValueToAssign(String value) {
        this.valueToAssign = value;
    }

    @Override
    public <String> void execute(PatientAttribute<String> stringAttribute) {
        stringAttribute.setValue((String) valueToAssign);
    }
}

