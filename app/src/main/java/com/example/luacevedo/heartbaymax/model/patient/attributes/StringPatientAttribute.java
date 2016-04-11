package com.example.luacevedo.heartbaymax.model.patient.attributes;


import com.example.luacevedo.heartbaymax.api.model.Attribute;

public class StringPatientAttribute extends BasePatientAttribute{

    private String value;

    public String getValue() {
        return value;
    }

    public StringPatientAttribute(Attribute attribute) {
        super(attribute);
    }

    public void setValue(String value) {
        this.value = value;
    }
}

