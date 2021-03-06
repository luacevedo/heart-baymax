package com.luacevedo.heartbaymax.ui.views.controls;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
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

import java.text.DecimalFormat;
import java.util.regex.Pattern;

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
        setupEditText();
        setListeners();
    }

    private void setupTooltip() {
        if (inputField.getToolTip() != null) {
            txtViewTooltip.setText(inputField.getToolTip());
            txtViewTooltip.setVisibility(VISIBLE);
        }
    }

    private void setupEditText() {
        editText.setHint(inputField.getLabelMessage());
        if (inputField.getDataType().equals(Constants.InputField.DataType.NUMBER)) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
        }

        editText.setOnTouchListener(null);
        editText.setFloatingLabel(MaterialEditText.FLOATING_LABEL_NORMAL);
        editText.setFloatingLabelAnimating(true);
        editText.setFloatingLabelText(inputField.getLabelMessage());

        setCurrentValue();

        if (inputField.getMaxLength() != null) {
            InputFilter[] filters = new InputFilter[1];
            filters[0] = new InputFilter.LengthFilter(inputField.getMaxLength());
            editText.setFilters(filters);
        }
    }

    private void setCurrentValue() {
        if (currentValue != null) {
            String value = currentValue.getValue();
            if (inputField.getDataType().equals(Constants.InputField.DataType.NUMBER)) {
                if (Double.valueOf(value) % 1 == 0) {
                    DecimalFormat decimalFormat = new DecimalFormat("#.#");
                    value = decimalFormat.format(Double.valueOf(value));
                }
            }
            editText.setText(value);
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
        onValueChangedListener.updateValueError(false);
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
                if (inputField.getMatches() != null) {
                    Pattern p = Pattern.compile(inputField.getMatches());
                    if (!p.matcher(editText.getText().toString()).matches() || !inputField.isInRange(editText.getText().toString())) {
                        setError(getContext().getString(R.string.invalid_value_msg));
                        onValueChangedListener.updateValueError(true);
                        return;
                    }
                }
                onValueChangedListener.valueTextChanged(inputField, editText.getText().toString());
            }
        };
    }

    @Override
    public void setFocus() {
        editText.requestFocus();
    }

}
