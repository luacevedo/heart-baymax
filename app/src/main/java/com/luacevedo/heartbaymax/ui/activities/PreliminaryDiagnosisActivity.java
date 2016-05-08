package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;

import com.luacevedo.heartbaymax.api.model.InputAttribute;

import java.util.List;

public class PreliminaryDiagnosisActivity extends BaseFragmentActivity {

    private List<InputAttribute> inputAttributes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public List<InputAttribute> getInputAttributes() {
        return inputAttributes;
    }
}
