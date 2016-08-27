package com.luacevedo.heartbaymax.api.model;

import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.fields.Value;
import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockInfo {

    public static HashMap<String, PatientAttribute> getMockedAttributesMap() {
        HashMap<String, PatientAttribute> map = new HashMap<>();

        PatientAttribute<Boolean> name = new PatientAttribute<>(new Attribute(22L, "PatientData.Name", "string"));
        map.put("PatientData.Name", name);

        PatientAttribute<Boolean> age = new PatientAttribute<>(new Attribute(23L, "EssentialSymptoms.Age", "string"));
        map.put("PatientData.Age", age);

        PatientAttribute<Boolean> gender = new PatientAttribute<>(new Attribute(24L, "PatientData.Gender", "string"));
        map.put("PatientData.Gender", gender);

        PatientAttribute<Boolean> edemaPulmonar = new PatientAttribute<>(new Attribute(1L, "EssentialSymptoms.PulmonaryEdema", "boolean"));
        map.put("EssentialSymptoms.PulmonaryEdema", edemaPulmonar);

        PatientAttribute<String> disnea = new PatientAttribute<>(new Attribute(2L, "EssentialSymptoms.Dyspnoea", "string"));
        map.put("EssentialSymptoms.Dyspnoea", disnea);

        PatientAttribute<Boolean> ortopnea = new PatientAttribute<>(new Attribute(3L, "EssentialSymptoms.Orthopnoea", "boolean"));
        map.put("EssentialSymptoms.Orthopnoea", ortopnea);

        PatientAttribute<Boolean> legEdema = new PatientAttribute<>(new Attribute(4L, "EssentialSymptoms.LegEdema", "boolean"));
        map.put("EssentialSymptoms.LegEdema", legEdema);

        PatientAttribute<Boolean> thirdHeartSound = new PatientAttribute<>(new Attribute(5L, "EssentialSymptoms.ThirdHeartSound", "boolean"));
        map.put("EssentialSymptoms.ThirdHeartSound", thirdHeartSound);

        PatientAttribute<Boolean> hypertension = new PatientAttribute<>(new Attribute(6L, "SecondarySymptoms.Hypertension", "boolean"));
        map.put("SecondarySymptoms.Hypertension", hypertension);

        PatientAttribute<Boolean> nicturia = new PatientAttribute<>(new Attribute(7L, "SecondarySymptoms.Nicturia", "boolean"));
        map.put("SecondarySymptoms.Nicturia", nicturia);

        PatientAttribute<Boolean> obesity = new PatientAttribute<>(new Attribute(8L, "SecondarySymptoms.Obesity", "boolean"));
        map.put("SecondarySymptoms.Obesity", obesity);

        PatientAttribute<Boolean> diabetes = new PatientAttribute<>(new Attribute(9L, "SecondarySymptoms.Diabetes", "boolean"));
        map.put("SecondarySymptoms.Diabetes", diabetes);

        PatientAttribute<Boolean> previousHeartAttack = new PatientAttribute<>(new Attribute(10L, "SecondarySymptoms.PreviousHeartAttack", "boolean"));
        map.put("SecondarySymptoms.PreviousHeartAttack", previousHeartAttack);

        PatientAttribute<Boolean> jugularVeinEngorgement = new PatientAttribute<>(new Attribute(11L, "SecondarySymptoms.JugularVeinEngorgement", "boolean"));
        map.put("SecondarySymptoms.JugularVeinEngorgement", jugularVeinEngorgement);

        PatientAttribute<Boolean> nighttimeCough = new PatientAttribute<>(new Attribute(12L, "SecondarySymptoms.NighttimeCough", "boolean"));
        map.put("SecondarySymptoms.NighttimeCough", nighttimeCough);

        PatientAttribute<Integer> essentialSymptomsAssessment = new PatientAttribute<>(new Attribute(2L, "InitialPhysicalState.EssentialSymptomsAssessment", "integer"), 0);
        map.put("InitialPhysicalState.EssentialSymptomsAssessment", essentialSymptomsAssessment);

        PatientAttribute<Integer> secondarySymptomsAssessment = new PatientAttribute<>(new Attribute(3L, "InitialPhysicalState.SecondarySymptomsAssessment", "integer"), 0);
        map.put("InitialPhysicalState.SecondarySymptomsAssessment", secondarySymptomsAssessment);

        PatientAttribute<List<String>> essentialSymptoms = new PatientAttribute<List<String>>(new Attribute(4L, "InitialPhysicalState.EssentialSymptoms", "list"), new ArrayList<String>());
        map.put("InitialPhysicalState.EssentialSymptoms", essentialSymptoms);

        PatientAttribute<List<String>> secondarySymptoms = new PatientAttribute<List<String>>(new Attribute(5L, "InitialPhysicalState.SecondarySymptoms", "list"), new ArrayList<String>());
        map.put("InitialPhysicalState.SecondarySymptoms", secondarySymptoms);

        PatientAttribute<String> symptomsType = new PatientAttribute<>(new Attribute(6L, "PreliminaryDiagnosis.SymptomsType", "string"));
        map.put("PreliminaryDiagnosis.SymptomsType", symptomsType);

        return map;
    }

    public static List<StepInputFields> getPreliminaryDiagnosisFields() {

        List<Value> booleanValues = new ArrayList<>();
        booleanValues.add(new Value("0", "No"));
        booleanValues.add(new Value("1", "Si"));

        List<Value> disphoneaValues = new ArrayList<>();
        disphoneaValues.add(new Value("1", "Tipo 1"));
        disphoneaValues.add(new Value("2", "Tipo 2"));
        disphoneaValues.add(new Value("3", "Tipo 3"));
        disphoneaValues.add(new Value("4", "Tipo 4"));

        List<Value> genderValues = new ArrayList<>();
        genderValues.add(new Value("1", "F"));
        genderValues.add(new Value("2", "M"));

        List<InputField> firstInputFields = new ArrayList<>();
        InputField name = new InputField(1, "Nombre?", "PatientData.Name", "string", "text");
        firstInputFields.add(name);
        InputField age = new InputField(2, "Edad?", "PatientData.Age", "string", "text");
        firstInputFields.add(age);
        InputField gender = new InputField(3, "Genero?", "PatientData.Gender", "select", "combobox", genderValues);
        firstInputFields.add(gender);

        StepInputFields firstStepInputFields = new StepInputFields(1, firstInputFields);

        List<InputField> secondInputFields = new ArrayList<>();
        InputField pulmonaryEdema = new InputField(1, "Edema pulmonar?", "EssentialSymptoms.PulmonaryEdema", "boolean", "combobox", booleanValues);
        secondInputFields.add(pulmonaryEdema);
        InputField dyspnoea = new InputField(2, "Disnea?", "EssentialSymptoms.Dyspnoea", "select", "combobox", disphoneaValues);
        secondInputFields.add(dyspnoea);
        InputField orthopnoea = new InputField(3, "Orthopnoea?", "EssentialSymptoms.Orthopnoea", "boolean", "combobox", booleanValues);
        secondInputFields.add(orthopnoea);
        InputField legEdema = new InputField(4, "Edema de miembros inferiores?", "EssentialSymptoms.LegEdema", "boolean", "combobox", booleanValues);
        secondInputFields.add(legEdema);
        InputField thirdHeartSound = new InputField(5, "Tercer ruido?", "EssentialSymptoms.ThirdHeartSound", "boolean", "combobox", booleanValues);
        secondInputFields.add(thirdHeartSound);

        StepInputFields secondStepInputFields = new StepInputFields(1, secondInputFields);

        List<InputField> thirdInputFields = new ArrayList<>();
        InputField hypertension = new InputField(6, "Hipertension?", "SecondarySymptoms.Hypertension", "boolean", "combobox", booleanValues);
        thirdInputFields.add(hypertension);
        InputField nicturia = new InputField(7, "Nicturia?", "SecondarySymptoms.Nicturia", "boolean", "combobox", booleanValues);
        thirdInputFields.add(nicturia);
        InputField obesity = new InputField(8, "Obesidad?", "SecondarySymptoms.Obesity", "boolean", "combobox", booleanValues);
        thirdInputFields.add(obesity);
        InputField diabetes = new InputField(9, "Diabetes?", "SecondarySymptoms.Diabetes", "boolean", "combobox", booleanValues);
        thirdInputFields.add(diabetes);
        InputField previousHeartAttack = new InputField(10, "Infarto previo?", "SecondarySymptoms.PreviousHeartAttack", "boolean", "combobox", booleanValues);
        thirdInputFields.add(previousHeartAttack);
        InputField jugularVeinEngorgement = new InputField(11, "Ingurgitacion yugular?", "SecondarySymptoms.JugularVeinEngorgement", "boolean", "combobox", booleanValues);
        thirdInputFields.add(jugularVeinEngorgement);
        InputField nighttimeCough = new InputField(12, "Tos nocturna?", "SecondarySymptoms.NighttimeCough", "boolean", "combobox", booleanValues);
        thirdInputFields.add(nighttimeCough);

        StepInputFields thirdStepInputFields = new StepInputFields(3, thirdInputFields);

        List<StepInputFields> list = new ArrayList<>();

        list.add(firstStepInputFields);
        list.add(secondStepInputFields);
        list.add(thirdStepInputFields);

        return list;
    }

}
