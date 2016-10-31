package com.luacevedo.heartbaymax.utils;

import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;

import java.util.ArrayList;
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


}
