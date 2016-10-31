package com.luacevedo.heartbaymax.utils;

import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.luacevedo.heartbaymax.Constants.Attribute.Type.BOOLEAN;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.INTEGER;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.LIST;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.STRING;

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
