package com.luacevedo.heartbaymax.api.model.patients;

public class Attribute {

    private long id;
    private String root;
    private String dataType;

    public Attribute(long id, String root, String dataType) {
        this.id = id;
        this.root = root;
        this.dataType = dataType;
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
}
