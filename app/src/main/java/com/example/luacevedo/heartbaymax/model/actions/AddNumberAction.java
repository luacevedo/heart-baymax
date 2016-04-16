package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.PatientAttribute;

public class AddNumberAction extends BaseAction {

    private Integer value; // el valor que se va a agregar al attributo que se mande en execute

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public <Integer> void execute(PatientAttribute<Integer> intAttribute) {
        int currentValue;
        if (intAttribute.getValue() == null) {
            currentValue = 0;
        } else {
            currentValue = java.lang.Integer.parseInt(intAttribute.getValue().toString());
        }
        java.lang.Integer newValueInt = currentValue + value;
        Integer newValueInteger = (Integer) newValueInt;
        intAttribute.setValue(newValueInteger);
    }
}
