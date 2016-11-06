package com.luacevedo.heartbaymax.utils;

import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.rules.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InputFieldsUtils {

    public static final int STAGE_1 = 1;
    public static final int STAGE_2 = 2;
    public static final int STAGE_3 = 3;
    public static final int STAGE_4 = 4;

    public static List<StepInputFields> filterStageInputFields(List<StepInputFields> inputFields, int stage) {
        List<StepInputFields> firstStageFields = new ArrayList<>();
        for (StepInputFields stepInputField : inputFields) {
            if (stepInputField.getStage() == stage) {
                firstStageFields.add(stepInputField);
            }
        }
        return firstStageFields;
    }


    public static List<StepInputFields> orderInputFields(List<StepInputFields> inputFields) {

        List<StepInputFields> orderedFields = new ArrayList<>();
        int stage = 1;
        int step = 1;

        while (!inputFields.isEmpty()) {
            StepInputFields stepInputFields = findInputFieldForStageAndStep(inputFields, stage, step);
            if (stepInputFields != null) {
                inputFields.remove(stepInputFields);
                orderInputFieldsById(stepInputFields.getInputFields());
                orderedFields.add(stepInputFields);
                step++;
            } else {
                stage++;
                step = 1;
            }
        }
        return orderedFields;

    }

    public static void orderInputFieldsById(List<InputField> list) {
        Comparator<InputField> comparator = new Comparator<InputField>() {
            @Override
            public int compare(InputField c1, InputField c2) {
                return c1.getId() - c2.getId();
            }
        };

        Collections.sort(list, comparator);
    }

    private static StepInputFields findInputFieldForStageAndStep(List<StepInputFields> inputFields, int stage, int step) {
        for (StepInputFields stepInputField : inputFields) {
            if (stepInputField.getStage() == stage && stepInputField.getStep() == step) {
                return stepInputField;
            }
        }
        return null;
    }
}

