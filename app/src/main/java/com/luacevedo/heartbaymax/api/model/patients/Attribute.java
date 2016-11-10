package com.luacevedo.heartbaymax.api.model.patients;

public class Attribute {

    private int attributeId;
    private String root;
    private String dataType;
    private String name;
    private boolean needsInputValue;

    public Attribute(int attributeId, String root, String dataType, String name, boolean needsInputValue) {
        this.attributeId = attributeId;
        this.root = root;
        this.dataType = dataType;
        this.name = name;
        this.needsInputValue = needsInputValue;
    }

    public Attribute(int id, String root, String dataType, boolean needsInputValue) {
        this.attributeId = attributeId;
        this.root = root;
        this.dataType = dataType;
        this.name = null;
        this.needsInputValue = needsInputValue;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return this.name;
    }

    public String getRootParent() {
        String[] x = this.root.split("\\.");
        return x[0];
    }

    public boolean needsInputValue() {
        return needsInputValue;
    }

    public void setNeedsInputValue(boolean needsInputValue) {
        this.needsInputValue = needsInputValue;
    }
}
