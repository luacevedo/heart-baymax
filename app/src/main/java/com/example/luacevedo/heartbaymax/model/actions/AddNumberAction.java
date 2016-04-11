package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.BasePatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.IntegerPatientAttribute;

public class AddNumberAction extends BaseAction {

    private int value; // el valor que se va a agregar al attributo que se mande en execute

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void execute(BasePatientAttribute attribute) {
        if (attribute instanceof IntegerPatientAttribute) {
            IntegerPatientAttribute intAttribute = (IntegerPatientAttribute) attribute;
            intAttribute.setValue(intAttribute.getValue() + value);
        }
    }
}
