package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class AddNumberAction extends BaseAction<Integer> {

    private int valueToAdd;

    public int getValue() {
        return valueToAdd;
    }

    public AddNumberAction(String attributeRoot, int valueToAdd) {
        this.attributeRoot = attributeRoot;
        this.valueToAdd = valueToAdd;
    }

    public void setValueToAdd(int value) {
        this.valueToAdd = value;
    }

    @Override
    public void execute(PatientAttribute<Integer> intAttribute) {
        int currentValue;
        if (intAttribute.getValue() == null) {
            currentValue = 0;
        } else {
            currentValue = intAttribute.getValue();
        }
        intAttribute.setValue(currentValue + valueToAdd);
    }
}
