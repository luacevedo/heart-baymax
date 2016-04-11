package com.example.luacevedo.heartbaymax.model.patient.attributes;

import com.example.luacevedo.heartbaymax.api.model.Attribute;

public abstract class BasePatientAttribute {
    private Attribute attribute;

    public BasePatientAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

}
