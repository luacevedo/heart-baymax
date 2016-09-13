package com.luacevedo.heartbaymax.interfaces;

import com.luacevedo.heartbaymax.api.model.fields.InputField;

public interface ICustomField {

    InputField getField();

    String getValue();

    void setHint(String hint);

    void setError(String error);

    void saveData();
}
