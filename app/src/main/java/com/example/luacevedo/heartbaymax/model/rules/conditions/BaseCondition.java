package com.example.luacevedo.heartbaymax.model.rules.conditions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public abstract class BaseCondition<T> {

    protected String attributeRoot;

    public String getAttributeRoot() {
        return attributeRoot;
    }

    public void setAttributeRoot(String attributeRoot) {
        this.attributeRoot = attributeRoot;
    }

    public abstract boolean validate(PatientAttribute<T> attribute);


}
