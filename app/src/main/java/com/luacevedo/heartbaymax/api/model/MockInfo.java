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

    public static Patient createPatient(){
        Patient patient = new Patient();
        patient.setId(1);
        patient.setAttributesMap(getMockedAttributesMap());
        return patient;
    }

    private static HashMap<String, PatientAttribute> getMockedAttributesMap() {
        HashMap<String, PatientAttribute> map = new HashMap<>();

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

        List<InputField> inputFields = new ArrayList<>();

        InputField pulmonaryEdema = new InputField(1, "Edema pulmonar?", "EssentialSymptoms.PulmonaryEdema", "boolean", "combobox", booleanValues);
        inputFields.add(pulmonaryEdema);
        InputField dyspnoea = new InputField(2, "Disnea?", "EssentialSymptoms.Dyspnoea", "select", "combobox", disphoneaValues);
        inputFields.add(dyspnoea);
        InputField orthopnoea = new InputField(3, "Orthopnoea?", "EssentialSymptoms.Orthopnoea", "boolean", "combobox", booleanValues);
        inputFields.add(orthopnoea);
        InputField legEdema = new InputField(4, "Edema de miembros inferiores?", "EssentialSymptoms.LegEdema", "boolean", "combobox", booleanValues);
        inputFields.add(legEdema);
        InputField thirdHeartSound = new InputField(5, "Tercer ruido?", "EssentialSymptoms.ThirdHeartSound", "boolean", "combobox", booleanValues);
        inputFields.add(thirdHeartSound);

        StepInputFields firstStepInputFields = new StepInputFields(1, inputFields);

        List<InputField> secondInputFields = new ArrayList<>();
        InputField hypertension = new InputField(6, "Hipertension?", "SecondarySymptoms.Hypertension", "boolean", "combobox", booleanValues);
        secondInputFields.add(hypertension);
        InputField nicturia = new InputField(7, "Nicturia?", "SecondarySymptoms.v", "boolean", "combobox", booleanValues);
        secondInputFields.add(nicturia);
        InputField obesity = new InputField(8, "Obesidad?", "SecondarySymptoms.Obesity", "boolean", "combobox", booleanValues);
        secondInputFields.add(obesity);
        InputField diabetes = new InputField(9, "Diabetes?", "SecondarySymptoms.Diabetes", "boolean", "combobox", booleanValues);
        secondInputFields.add(diabetes);
        InputField previousHeartAttack = new InputField(10, "Infarto previo?", "SecondarySymptoms.PreviousHeartAttack", "boolean", "combobox", booleanValues);
        secondInputFields.add(previousHeartAttack);
        InputField jugularVeinEngorgement = new InputField(11, "Ingurgitacion yugular?", "SecondarySymptoms.JugularVeinEngorgement", "boolean", "combobox", booleanValues);
        secondInputFields.add(jugularVeinEngorgement);
        InputField nighttimeCough = new InputField(12, "Tos nocturna?", "SecondarySymptoms.NighttimeCough", "boolean", "combobox", booleanValues);
        secondInputFields.add(nighttimeCough);

        StepInputFields secondStepInputFields = new StepInputFields(2, secondInputFields);

        List<StepInputFields> list = new ArrayList<>();

        list.add(firstStepInputFields);
        list.add(secondStepInputFields);

        return list;
    }

}
