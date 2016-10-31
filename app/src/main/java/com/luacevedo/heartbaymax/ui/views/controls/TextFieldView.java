package com.luacevedo.heartbaymax.ui.views.controls;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.Value;
import com.rengwuxian.materialedittext.MaterialEditText;

public class TextFieldView extends InputFieldView {

    protected MaterialEditText editText;
    protected InputField inputField;
    protected Value currentValue;
    protected TextView txtViewTooltip;

    public TextFieldView(Context context, InputField inputField) {
        super(context);
        this.inputField = inputField;
        this.currentValue = inputField.getValue();
    }

    @Override
    public void initialize() {
        inflate(getContext(), R.layout.view_text_field, this);
        setOrientation(LinearLayout.VERTICAL);
        editText = (MaterialEditText) findViewById(R.id.text_field_edit_text);
        txtViewTooltip = (TextView) findViewById(R.id.text_field_tooltip_help);

        setupTooltip();
        setupEditTExt();
        setListeners();
    }

    private void setupTooltip() {
        if (inputField.getTooltip() != null) {
            txtViewTooltip.setText(inputField.getTooltip());
            txtViewTooltip.setVisibility(VISIBLE);
        }
    }

    private void setupEditTExt() {
        editText.setHint(inputField.getLabelMessage());
        if (inputField.getDataType().equals(Constants.InputField.DataType.NUMBER)) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        editText.setOnTouchListener(null);
        editText.setFloatingLabel(MaterialEditText.FLOATING_LABEL_NORMAL);
        editText.setFloatingLabelAnimating(true);
        editText.setFloatingLabelText(inputField.getLabelMessage());
        if (currentValue != null) {
            editText.setText(currentValue.getValue());
        }
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

}
