package com.example.luacevedo.heartbaymax.model.patient;

import com.example.luacevedo.heartbaymax.model.patient.attributes.BasePatientAttribute;

import java.util.HashMap;
import java.util.Map;

public class Patient {

    private String name;
    private int age;
    private String gender;
    private HashMap<String, BasePatientAttribute> attributesMap;

    public HashMap<String, BasePatientAttribute> getAttributesMap() {
        return attributesMap;
    }

    public void setAttributesMap(HashMap<String, BasePatientAttribute> attributesMap) {
        this.attributesMap = attributesMap;
    }

}
