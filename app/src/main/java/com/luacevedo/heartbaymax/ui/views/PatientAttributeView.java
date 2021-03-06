package com.luacevedo.heartbaymax.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.TranslationsHelper;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.text.DecimalFormat;

public class PatientAttributeView extends LinearLayout {
    private TextView label;
    private TextView value;

    public PatientAttributeView(Context context) {
        this(context, null);
    }

    public PatientAttributeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.view_patient_attribute, this);
        label = (TextView) findViewById(R.id.patient_attribute_label);
        value = (TextView) findViewById(R.id.patient_attribute_value);
    }

    public void setData(PatientAttribute attribute) {
        label.setText(attribute.getAttribute().getName() + ":");
        String translatedValue = getTranslatedValue(attribute);
        if (attribute.getAttribute().getUnit() != null) {
            translatedValue += " " + attribute.getAttribute().getUnit();
        }
        value.setText(translatedValue);
    }

    private String getTranslatedValue(PatientAttribute attribute) {
        Object attributeValue = attribute.getValue();
        String translatedValue = attribute.getValue().toString();
        if (attribute.getValue() instanceof Boolean) {
            translatedValue = TranslationsHelper.translateBooleanValue((Boolean) attribute.getValue());
        } else if (attributeValue instanceof Double) {
            if ((Double) attributeValue % 1 == 0) {
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                translatedValue = decimalFormat.format(attributeValue);
            }
        } else if (attribute.getValue() instanceof String && attribute.getAttribute().getRoot().equals(Constants.Attribute.EssentialSymptoms.DYSPNOEA)) {
            translatedValue = TranslationsHelper.translateDyspnoeaValue(attribute.getValue().toString());
        }
        return translatedValue;
    }

}

