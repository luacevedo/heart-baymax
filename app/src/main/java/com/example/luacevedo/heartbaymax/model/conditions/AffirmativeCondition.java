package com.example.luacevedo.heartbaymax.model.conditions;

import com.example.luacevedo.heartbaymax.model.patient.attributes.BasePatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.BooleanPatientAttribute;

public class AffirmativeCondition extends BaseCondition {
    @Override
    public boolean validate(BasePatientAttribute attribute) {
        if (attribute instanceof BooleanPatientAttribute) {
            return ((BooleanPatientAttribute) attribute).getValue();
        }
        return false;
    }
}
