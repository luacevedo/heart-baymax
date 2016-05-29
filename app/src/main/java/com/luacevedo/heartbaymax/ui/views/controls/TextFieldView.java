package com.luacevedo.heartbaymax.ui.views.controls;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;

import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.rengwuxian.materialedittext.MaterialEditText;

public class TextFieldView extends InputFieldView {

    protected MaterialEditText editText;
    protected InputField inputField;
    private String initialValue;
    private boolean enabled = true;
    private Integer inputType = null;

    public TextFieldView(Context context, InputField inputField) {
        super(context);
        this.inputField = inputField;
        initialize();
    }

    @Override
    public void initialize() {
        inflate(getContext(), R.layout.view_text_field, this);
        setOrientation(LinearLayout.VERTICAL);
        editText = (MaterialEditText) findViewById(R.id.text_field_edit_text);
        editText.setHint(inputField.getLabelMessage());
        editText.setFloatingLabel(MaterialEditText.FLOATING_LABEL_NORMAL);
        editText.setFloatingLabelAnimating(true);
        editText.setFloatingLabelText(inputField.getLabelMessage());
        editText.setEnabled(enabled);
        if (inputType != null) {
            editText.setInputType(inputType);
        }
        if (initialValue != null) {
            editText.setText(initialValue);
        }
        setListeners();
    }

    @Override
    public void setError(final String validationError) {
        editText.post(new Runnable() {
            @Override
            public void run() {
                editText.setError(validationError);
            }
        });
    }

    @Override
    public void clearError() {
        editText.setError(null);
    }

    private void setListeners() {
        editText.addTextChangedListener(getTextWatcher());
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (focusReceivedListener != null) {
                    focusReceivedListener.onFocusReceived(editText);
                }
            }
        });
    }

    protected TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                clearError();
                onValueChangedListener.valueTextChanged(inputField, editText.getText().toString());
            }
        };
    }

    @Override
    public void setFocus() {
        editText.requestFocus();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (editText == null) {
            this.enabled = enabled;
        } else {
            editText.setEnabled(enabled);
        }
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }
}
