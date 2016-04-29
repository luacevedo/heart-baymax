package com.example.luacevedo.heartbaymax.api.model;

public class Action {

    private String function; // funcion que determina que tipo de BaseAction creo
    private String attribute;
    private String value;

    public Action(String function, String attribute, String value) {
        this.function = function;
        this.attribute = attribute;
        this.value = value;
    }

    public String getAFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
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
