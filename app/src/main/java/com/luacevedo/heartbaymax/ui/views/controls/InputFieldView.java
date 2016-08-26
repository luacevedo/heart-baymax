package com.luacevedo.heartbaymax.ui.views.controls;

import android.content.Context;
import android.widget.LinearLayout;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.interfaces.OnFocusReceivedListener;
import com.luacevedo.heartbaymax.interfaces.OnInputFieldValueChangedListener;

public abstract class InputFieldView extends LinearLayout {

    protected OnInputFieldValueChangedListener onValueChangedListener;
    protected OnFocusReceivedListener focusReceivedListener;

    public InputFieldView(Context context) {
        super(context);
    }

    public abstract void setError(String validationError);

    public abstract void clearError();

    public void setFocus() {

    }

    public void initialize() {

    }

    public static InputFieldView createInputFieldControl(InputField inputField, OnInputFieldValueChangedListener onInputFieldValueChangedListener, Context context) {
        InputFieldView inputFieldView;
        switch (inputField.getFieldType()) {
            case Constants.InputField.FieldType.COMBOBOX:
                inputFieldView = new SelectFieldView(context, inputField);
                break;
            case Constants.InputField.FieldType.TEXT:
                inputFieldView = new TextFieldView(context, inputField);
                break;
            default:
                return null;
        }

        inputFieldView.setOnInputFieldValueChangedListener(onInputFieldValueChangedListener);
        return inputFieldView;
    }

    public void setFocusReceivedListener(OnFocusReceivedListener focusReceivedListener) {
        this.focusReceivedListener = focusReceivedListener;
    }

    public void setOnInputFieldValueChangedListener(OnInputFieldValueChangedListener onValueIdChanged) {
        this.onValueChangedListener = onValueIdChanged;
    }

}
