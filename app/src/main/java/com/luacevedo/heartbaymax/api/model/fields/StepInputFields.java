package com.luacevedo.heartbaymax.api.model.fields;

import java.util.List;

public class StepInputFields {

    private int stage;
    private int step;
    private List<InputField> inputFields;

    public StepInputFields(int step, List<InputField> inputFields) {
        this.step = step;
        this.inputFields = inputFields;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

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
