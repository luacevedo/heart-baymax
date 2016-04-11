package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.BasePatientAttribute;

public abstract class BaseAction {

    protected String attributeRoot;

    public String getAttributeRoot() {
        return attributeRoot;
    }

    public void setAttributeRoot(String attributeRoot) {
        this.attributeRoot = attributeRoot;
    }

    public abstract void execute(BasePatientAttribute attribute);

}
