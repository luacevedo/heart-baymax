package com.luacevedo.heartbaymax.api.model.patients;

public class Attribute {

    private long id;
    private String root;
    private String dataType;
    private String name;
    private boolean needsInputValue;

    public Attribute(long id, String root, String dataType, String name, boolean needsInputValue) {
        this.id = id;
        this.root = root;
        this.dataType = dataType;
        this.name = name;
        this.needsInputValue = needsInputValue;
    }

    public Attribute(long id, String root, String dataType, boolean needsInputValue) {
        this.id = id;
        this.root = root;
        this.dataType = dataType;
        this.name = null;
        this.needsInputValue = needsInputValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
