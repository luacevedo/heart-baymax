package com.luacevedo.heartbaymax.api.model.fields;

public class InputField {

    private long id;
    private String labelMessage;
    private String rootToAffect;
    private String dataType;
    private String fieldType;

    public InputField(long id, String labelMessage, String rootToAffect, String dataType, String fieldType) {
        this.id = id;
        this.labelMessage = labelMessage;
        this.rootToAffect = rootToAffect;
        this.dataType = dataType;
        this.fieldType = fieldType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabelMessage() {
        return labelMessage;
    }

    public void setLabelMessage(String labelMessage) {
        this.labelMessage = labelMessage;
    }

    public String getRootToAffect() {
        return rootToAffect;
    }

    public void setRootToAffect(String rootToAffect) {
        this.rootToAffect = rootToAffect;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
