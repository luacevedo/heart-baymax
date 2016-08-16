package com.luacevedo.heartbaymax.model.patient;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.api.model.patients.Attribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Patient implements Serializable {

    private int id;
    private String name;
    private int age;
    private String gender;
    private HashMap<String, PatientAttribute> attributesMap = new HashMap<>();

    public HashMap<String, PatientAttribute> getAttributesMap() {
        return attributesMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAttributesMap(HashMap<String, PatientAttribute> attributesMap) {
        this.attributesMap = attributesMap;
    }

    public void setAttributesMap(List<Attribute> attributes) {
        attributesMap = new HashMap<>();
        for (Attribute attribute : attributes) {
            PatientAttribute<?> patientAttribute = null;

            switch (attribute.getDataType()) {
                case Constants.Attribute.Type.BOOLEAN:
                    patientAttribute = new PatientAttribute<Boolean>(attribute, true);
                    break;
                case Constants.Attribute.Type.INTEGER:
                    patientAttribute = new PatientAttribute<Integer>(attribute, 0);
                    break;
                case Constants.Attribute.Type.LIST:
                    patientAttribute = new PatientAttribute<List<String>>(attribute, new ArrayList<String>());
                    break;
                case Constants.Attribute.Type.STRING:
                    patientAttribute = new PatientAttribute<String>(attribute, "");
                    break;
            }
            if (patientAttribute != null) {
                attributesMap.put(attribute.getRoot(), patientAttribute);
            }

        }
    }
}
