package com.luacevedo.heartbaymax.api.model.fields;

import android.text.TextUtils;

import com.luacevedo.heartbaymax.Constants;

import java.io.Serializable;
import java.util.List;

public class InputField implements Serializable {

    private int id;
    private String labelMessage;
    private String rootToAffect;
    private String dataType;
    private String fieldType;
    private Value value;
    private List<Value> values;
    private String toolTip;
    private Integer maxLength;
    private String matches;
    private Integer min;
    private Integer max;

    public InputField(int id, String labelMessage, String rootToAffect, String dataType, String fieldType, List<Value> values) {
        this.id = id;
        this.labelMessage = labelMessage;
        this.rootToAffect = rootToAffect;
        this.dataType = dataType;
        this.fieldType = fieldType;
        this.values = values;
    }

    public InputField(int id, String labelMessage, String rootToAffect, String dataType, String fieldType) {
        this.id = id;
        this.labelMessage = labelMessage;
        this.rootToAffect = rootToAffect;
        this.dataType = dataType;
        this.fieldType = fieldType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public boolean isInRange(String value) {
        if (dataType.equals(Constants.InputField.DataType.NUMBER) && !TextUtils.isEmpty(value) && min != null && max != null) {
            Double number = Double.valueOf(value);
            return number >= min && number <= max;
        }
        return true;
    }
}
