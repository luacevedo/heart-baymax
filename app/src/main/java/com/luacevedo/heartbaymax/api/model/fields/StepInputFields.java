package com.luacevedo.heartbaymax.api.model.fields;

import java.util.List;

public class StepInputFields {

    private int step;
    private List<InputField> inputFields;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public List<InputField> getInputFields() {
        return inputFields;
    }

    public void setInputFields(List<InputField> inputFields) {
        this.inputFields = inputFields;
    }
}
