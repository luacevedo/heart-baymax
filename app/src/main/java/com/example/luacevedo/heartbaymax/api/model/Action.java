package com.example.luacevedo.heartbaymax.api.model;

public class Action {

    private String aFunction; // funcion que determina que tipo de BaseAction creo
    private String attribute;
    private String value;

    public Action(String aFunction, String attribute, String value) {
        this.aFunction = aFunction;
        this.attribute = attribute;
        this.value = value;
    }

    public String getAFunction() {
        return aFunction;
    }

    public void setAFunction(String aFunction) {
        this.aFunction = aFunction;
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
