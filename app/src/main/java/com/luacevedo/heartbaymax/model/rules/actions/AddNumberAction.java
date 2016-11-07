package com.luacevedo.heartbaymax.model.rules.actions;

import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class AddNumberAction extends BaseAction<Double> {

    private Double valueToAdd;

    public Double getValue() {
        return valueToAdd;
    }

    public AddNumberAction(String attributeRoot, Double valueToAdd) {
        this.attributeRoot = attributeRoot;
        this.valueToAdd = valueToAdd;
    }

    public void setValueToAdd(Double value) {
        this.valueToAdd = value;
    }

    @Override
    public void execute(PatientAttribute<Double> intAttribute) {
        Double currentValue;
        if (intAttribute.getValue() == null) {
            currentValue = 0.0;
        } else {
            currentValue = intAttribute.getValue();
        }
        intAttribute.setValue(currentValue + valueToAdd);
    }
}
