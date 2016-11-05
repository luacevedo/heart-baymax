package com.luacevedo.heartbaymax.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.TranslationsHelper;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class PatientAttributeExtendedView extends LinearLayout {
    private TextView label;
    private TextView value;

    public PatientAttributeExtendedView(Context context) {
        this(context, null);
    }

    public PatientAttributeExtendedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.view_patient_attribute_extended, this);
        label = (TextView) findViewById(R.id.patient_attribute_extended_label);
        value = (TextView) findViewById(R.id.patient_attribute_extended_value);
    }

    public void setData(PatientAttribute attribute) {
        label.setText(attribute.getAttribute().getName() + ":");
        String translatedValue = getTranslatedValue(attribute);
        value.setText(translatedValue);
    }

    private String getTranslatedValue(PatientAttribute attribute) {
        String translatedValue = attribute.getValue().toString();
        if (attribute.getValue() instanceof Boolean) {
            translatedValue = TranslationsHelper.translateBooleanValue((Boolean) attribute.getValue());
        }
        return translatedValue;
    }

}

