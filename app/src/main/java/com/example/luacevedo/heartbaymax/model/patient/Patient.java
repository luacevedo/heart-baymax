package com.example.luacevedo.heartbaymax.model.patient;

import com.example.luacevedo.heartbaymax.model.patient.attributes.PatientAttribute;

import java.util.HashMap;

public class Patient {

    private String name;
    private int age;
    private String gender;
    private HashMap<String, PatientAttribute> attributesMap;

    public HashMap<String, PatientAttribute> getAttributesMap() {
        return attributesMap;
    }

    public void setAttributesMap(HashMap<String, PatientAttribute> attributesMap) {
        this.attributesMap = attributesMap;
    }

}
