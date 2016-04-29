package com.example.luacevedo.heartbaymax.model.rules.actions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class AssignAction extends BaseAction<String> {

    private String valueToAssign;

    public AssignAction(String attributeRoot, String valueToAssign) {
        this.attributeRoot = attributeRoot;
        this.valueToAssign = valueToAssign;
    }

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

