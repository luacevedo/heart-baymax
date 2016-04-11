package com.example.luacevedo.heartbaymax.model.patient.attributes;

import com.example.luacevedo.heartbaymax.api.model.Attribute;

public class IntegerPatientAttribute extends BasePatientAttribute {

    private int value;

    public int getValue() {
        return value;
    }

    public IntegerPatientAttribute(Attribute attribute) {
        super(attribute);
        this.value = 0;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
