package com.example.luacevedo.heartbaymax.model.patient;

import com.example.luacevedo.heartbaymax.api.model.Attribute;

public class PatientAttribute<T> {
    private Attribute attribute;
    private T value;

    public PatientAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
