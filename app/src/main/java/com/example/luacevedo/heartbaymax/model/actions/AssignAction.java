package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.PatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.StringPatientAttribute;

public class AssignAction extends BaseAction {

    private String value; // el valor que se va a asignar al attributo que se mande en execute

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void execute(PatientAttribute attribute) {
        if (attribute instanceof StringPatientAttribute) {
            StringPatientAttribute stringAttribute = (StringPatientAttribute) attribute;
            stringAttribute.setValue(value);
        }
    }
}
