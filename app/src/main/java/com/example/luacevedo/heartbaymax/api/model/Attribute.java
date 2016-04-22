package com.example.luacevedo.heartbaymax.api.model;

public class Attribute {

    private long id;
    private String root;
    private String type;

    public Attribute(long id, String root, String type) {
        this.id = id;
        this.root = root;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
