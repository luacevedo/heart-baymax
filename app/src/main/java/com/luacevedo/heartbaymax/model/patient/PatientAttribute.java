package com.luacevedo.heartbaymax.model.patient;

import com.luacevedo.heartbaymax.api.model.Attribute;

public class PatientAttribute<T> {
    private Attribute attribute;
    private T value;

    public PatientAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public PatientAttribute(Attribute attribute, T value) {
        this.attribute = attribute;
        this.value = value;
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