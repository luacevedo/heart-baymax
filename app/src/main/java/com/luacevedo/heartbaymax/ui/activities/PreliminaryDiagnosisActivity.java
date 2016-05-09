package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;

import com.luacevedo.heartbaymax.api.model.fields.InputField;

import java.util.List;

public class PreliminaryDiagnosisActivity extends BaseFragmentActivity {

    private List<InputField> inputFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public List<InputField> getInputFields() {
        return inputFields;
    }
}
