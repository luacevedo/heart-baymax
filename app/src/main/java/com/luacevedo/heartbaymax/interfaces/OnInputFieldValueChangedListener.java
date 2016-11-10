package com.luacevedo.heartbaymax.interfaces;

import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.Value;

public interface OnInputFieldValueChangedListener {
    void valueSelectChanged(InputField inputField, Value fieldValue);

    void valueTextChanged(InputField inputField, String fieldValueText);

    void valueSelectionStart();

    void updateValueError(boolean isValid);
}
