package com.example.luacevedo.heartbaymax.model.patient.attributes;

import com.example.luacevedo.heartbaymax.api.model.Attribute;

public class BooleanPatientAttribute extends BasePatientAttribute {

    private boolean value;

    public BooleanPatientAttribute(Attribute attribute) {
        super(attribute);
        this.value = false;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
