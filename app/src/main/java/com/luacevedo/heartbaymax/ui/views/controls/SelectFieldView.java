package com.luacevedo.heartbaymax.ui.views.controls;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.Value;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class SelectFieldView extends InputFieldView {
    private InputField inputField;
    private MaterialSpinner spinner;
    private Value selectedAttributeValue = null;
    private boolean firstSelect = true;

    public SelectFieldView(Context context, InputField inputField) {
        super(context);
        this.inputField = inputField;
        selectedAttributeValue = inputField.getValue();
    }

    @Override
    public void initialize() {
        inflate(getContext(), R.layout.view_select_field, this);
        setOrientation(LinearLayout.VERTICAL);
        spinner = (MaterialSpinner) findViewById(R.id.select_field_option_spinner);
        spinner.setHint(inputField.getLabelMessage());
        refreshAdapter();
    }

    @Override
    public void setError(String validationError) {
        spinner.setError(validationError);
    }

    @Override
    public void clearError() {
        spinner.setError(null);
    }

    private void refreshAdapter() {
        List<Value> fieldValues = inputField.getValues();
        Value[] fieldValuesArray = new Value[fieldValues.size()];
        fieldValuesArray = fieldValues.toArray(fieldValuesArray);
        ArrayAdapter<Value> adapter = new ArrayAdapter<>(getContext(), R.layout.view_input_field_option_item, fieldValuesArray);
        clearListener();
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        firstSelect = true;

        if (selectedAttributeValue != null) {
            boolean isPreviousValuePresent = false;
            for (int i = 0; i < fieldValues.size(); i++) {
                if (fieldValues.get(i).getKey().equals(selectedAttributeValue.getKey())) {
                    spinner.setSelection(i + 1, false);
                    isPreviousValuePresent = true;
                    break;
                }
            }
            if (!isPreviousValuePresent) {
                spinner.setSelection(0, false);
                selectedAttributeValue = null;
            }
        }
        setListeners();
    }

    private void clearListener() {
        spinner.setOnItemSelectedListener(null);
        spinner.setOnTouchListener(null);
    }

    private void setListeners() {
        spinner.setOnItemSelectedListener(getOnItemSelectedListener());
        spinner.setOnTouchListener(getHideKeyboardOnTouchListener());
    }

    private OnTouchListener getHideKeyboardOnTouchListener() {
        return new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onValueChangedListener.valueSelectionStart();
                return false;
            }
        };
    }

    private AdapterView.OnItemSelectedListener getOnItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (onValueChangedListener != null) {
                    if (position > -1) {
                        clearError();
                        Value fieldValue = inputField.getValues().get(position);
                        selectedAttributeValue = fieldValue;
                        onValueChangedListener.valueSelectChanged(inputField, fieldValue);
                    } else {
                        selectedAttributeValue = null;
                        onValueChangedListener.valueSelectChanged(inputField, null);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (onValueChangedListener != null) {
                    selectedAttributeValue = null;
                    onValueChangedListener.valueSelectChanged(inputField, null);
                }
            }
        };
    }
}


