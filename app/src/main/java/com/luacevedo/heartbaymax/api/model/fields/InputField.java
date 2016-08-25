package com.luacevedo.heartbaymax.api.model.fields;

import java.io.Serializable;
import java.util.List;

public class InputField implements Serializable {

    private long id;
    private String labelMessage;
    private String rootToAffect;
    private String dataType;
    private String fieldType;
    private Value value;
    private List<Value> values;
    private String error;

    public InputField(long id, String labelMessage, String rootToAffect, String dataType, String fieldType, List<Value> values) {
        this.id = id;
        this.labelMessage = labelMessage;
        this.rootToAffect = rootToAffect;
        this.dataType = dataType;
        this.fieldType = fieldType;
        this.values = values;
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

    public String getKeyValue() {
        if (value != null) {
            return value.getKey() != null ? value.getKey() : value.getValue();
        }
        return null;
    }

    public String getTextValue() {
        if (value != null) {
            return value.getValue();
        }
        return null;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public List<Value> getValues() {
        return values;
    }

    public Value getValue(String key) {
        for (Value value : values) {
            if (key.equals(value.getKey())) {
                return value;
            }
        }
        return null;
    }

}
