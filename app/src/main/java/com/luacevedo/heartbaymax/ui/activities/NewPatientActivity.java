package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.Value;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

public class NewPatientActivity extends BaseFragmentActivity {

    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setInputFieldTextValue(InputField inputField, String value) {
        PatientAttribute patientAttribute = patient.getAttributesMap().get(inputField.getRootToAffect());
        if (patientAttribute != null) {
            if (inputField.getDataType() == Constants.InputField.DataType.INTEGER) {
                patientAttribute.setValue(Integer.parseInt(value));
            } else if (inputField.getDataType() == Constants.InputField.DataType.STRING) {
                patientAttribute.setValue(value);
            }
        }
        inputField.setValue(new Value(value));

    }
}
