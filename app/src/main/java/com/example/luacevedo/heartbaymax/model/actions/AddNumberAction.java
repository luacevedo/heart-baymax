package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class AddNumberAction extends BaseAction {

    private Integer valueToAdd;

    public int getValue() {
        return valueToAdd;
    }

    public void setValueToAdd(int value) {
        this.valueToAdd = value;
    }

    @Override
    public <Integer> void execute(PatientAttribute<Integer> intAttribute) {
        int currentValue;
        if (intAttribute.getValue() == null) {
            currentValue = 0;
        } else {
            currentValue = java.lang.Integer.parseInt(intAttribute.getValue().toString());
        }
        java.lang.Integer newValueInt = currentValue + valueToAdd;
        Integer newValueInteger = (Integer) newValueInt;
        intAttribute.setValue(newValueInteger);
    }
}
