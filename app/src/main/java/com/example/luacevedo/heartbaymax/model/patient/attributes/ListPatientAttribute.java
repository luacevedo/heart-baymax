package com.example.luacevedo.heartbaymax.model.patient.attributes;

import com.example.luacevedo.heartbaymax.api.model.Attribute;

import java.util.ArrayList;
import java.util.List;

public class ListPatientAttribute extends BasePatientAttribute{

    private List<String> value;

    public List<String> getValue() {
        return value;
    }

    public ListPatientAttribute(Attribute attribute) {
        super(attribute);
        this.value = new ArrayList<>();
    }

    public void setValue(String value) {
        this.value.add(value);
    }
}
