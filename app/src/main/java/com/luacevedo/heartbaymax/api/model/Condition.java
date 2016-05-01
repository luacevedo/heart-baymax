package com.luacevedo.heartbaymax.api.model;

public class Condition {

    private String type; //tipo que determina que tipo de condicion creo
    private String attribute;
    private String value;

    public Condition(String type, String attribute, String value) {
        this.type = type;
        this.attribute = attribute;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
