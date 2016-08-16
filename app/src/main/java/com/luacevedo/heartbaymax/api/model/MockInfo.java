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
        patient.setAttributesMap(getMockedAttributesMap());
        return patient;
    }

    private static HashMap<String, PatientAttribute> getMockedAttributesMap() {
        HashMap<String, PatientAttribute> map = new HashMap<>();

        PatientAttribute<Boolean> edemaPulmonar = new PatientAttribute<>(new Attribute(1L, "EssentialSymptoms.PulmonaryEdema", "boolean"), true);
        map.put("EssentialSymptoms.PulmonaryEdema", edemaPulmonar);

        PatientAttribute<String> disnea = new PatientAttribute<>(new Attribute(2L, "EssentialSymptoms.Dyspnoea", "string"));
        map.put("EssentialSymptoms.Dyspnoea", disnea);

        PatientAttribute<Boolean> ortopnea = new PatientAttribute<>(new Attribute(3L, "EssentialSymptoms.Orthopnoea", "boolean"), false);
        map.put("EssentialSymptoms.Orthopnoea", ortopnea);

        PatientAttribute<Boolean> legEdema = new PatientAttribute<>(new Attribute(4L, "EssentialSymptoms.LegEdema", "boolean"), false);
        map.put("EssentialSymptoms.LegEdema", legEdema);

        PatientAttribute<Boolean> thirdHeartSound = new PatientAttribute<>(new Attribute(5L, "EssentialSymptoms.ThirdHeartSound", "boolean"), false);
        map.put("EssentialSymptoms.ThirdHeartSound", thirdHeartSound);

        PatientAttribute<Boolean> hypertension = new PatientAttribute<>(new Attribute(6L, "SecondarySymptoms.Hypertension", "boolean"), false);
        map.put("SecondarySymptoms.Hypertension", hypertension);

        PatientAttribute<Boolean> nicturia = new PatientAttribute<>(new Attribute(7L, "SecondarySymptoms.Nicturia", "boolean"), false);
        map.put("SecondarySymptoms.Nicturia", nicturia);

        PatientAttribute<Boolean> obesity = new PatientAttribute<>(new Attribute(8L, "SecondarySymptoms.Obesity", "boolean"), false);
        map.put("SecondarySymptoms.Obesity", obesity);

        PatientAttribute<Boolean> diabetes = new PatientAttribute<>(new Attribute(9L, "SecondarySymptoms.Diabetes", "boolean"), false);
        map.put("SecondarySymptoms.Diabetes", diabetes);

        PatientAttribute<Boolean> previousHeartAttack = new PatientAttribute<>(new Attribute(10L, "SecondarySymptoms.PreviousHeartAttack", "boolean"), false);
        map.put("SecondarySymptoms.PreviousHeartAttack", previousHeartAttack);

        PatientAttribute<Boolean> jugularVeinEngorgement = new PatientAttribute<>(new Attribute(11L, "SecondarySymptoms.JugularVeinEngorgement", "boolean"), false);
        map.put("SecondarySymptoms.JugularVeinEngorgement", jugularVeinEngorgement);

        PatientAttribute<Boolean> nighttimeCough = new PatientAttribute<>(new Attribute(12L, "SecondarySymptoms.NighttimeCough", "boolean"), false);
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

        InputField pulmonaryEdema = new InputField(1, "Edema pulmonar?", "EssentialSymptoms.PulmonaryEdema", "select", "combobox", booleanValues);
        inputFields.add(pulmonaryEdema);
        InputField dyspnoea = new InputField(2, "Disnea?", "EssentialSymptoms.Dyspnoea", "select", "combobox", disphoneaValues);
        inputFields.add(dyspnoea);
        InputField orthopnoea = new InputField(3, "Orthopnoea?", "EssentialSymptoms.Orthopnoea", "select", "combobox", booleanValues);
        inputFields.add(orthopnoea);
        InputField legEdema = new InputField(4, "Edema de miembros inferiores?", "EssentialSymptoms.LegEdema", "select", "combobox", booleanValues);
        inputFields.add(legEdema);
        InputField thirdHeartSound = new InputField(5, "Tercer ruido?", "EssentialSymptoms.ThirdHeartSound", "select", "combobox", booleanValues);
        inputFields.add(thirdHeartSound);

        InputField hypertension = new InputField(6, "Hipertension?", "SecondarySymptoms.Hypertension", "select", "combobox", booleanValues);
        inputFields.add(hypertension);
        InputField nicturia = new InputField(7, "Nicturia?", "EssentialSymptoms.ThirdHeartSound", "select", "combobox", booleanValues);
        inputFields.add(nicturia);
        InputField thirdHeartSound = new InputField(5, "Tercer ruido?", "EssentialSymptoms.ThirdHeartSound", "select", "combobox", booleanValues);
        inputFields.add(thirdHeartSound);
        InputField thirdHeartSound = new InputField(5, "Tercer ruido?", "EssentialSymptoms.ThirdHeartSound", "select", "combobox", booleanValues);
        inputFields.add(thirdHeartSound);
        InputField thirdHeartSound = new InputField(5, "Tercer ruido?", "EssentialSymptoms.ThirdHeartSound", "select", "combobox", booleanValues);
        inputFields.add(thirdHeartSound);
        InputField thirdHeartSound = new InputField(5, "Tercer ruido?", "EssentialSymptoms.ThirdHeartSound", "select", "combobox", booleanValues);
        inputFields.add(thirdHeartSound);

        map.put("SecondarySymptoms.Hypertension", hypertension);
        map.put("SecondarySymptoms.Nicturia", nicturia);
        map.put("SecondarySymptoms.Obesity", obesity);
        map.put("SecondarySymptoms.Diabetes", diabetes);
        map.put("SecondarySymptoms.PreviousHeartAttack", previousHeartAttack);
        map.put("SecondarySymptoms.JugularVeinEngorgement", jugularVeinEngorgement);
        map.put("SecondarySymptoms.NighttimeCough", nighttimeCough);



        StepInputFields firstStepInputFields = new StepInputFields(1, inputFields);

        firstStepInputFields.setInputFields(inputFields);

        List<StepInputFields> list = new ArrayList<>();
        list.add(firstStepInputFields);




        val pulmonaryEdemaField = InputField(1, "Presenta edema pulmonar?", "EssentialSymptoms.PulmonaryEdema", "boolean", "combobox", booleanValues)
        val orthopneaField = InputField(2, "Presenta ortopnea?", "EssentialSymptoms.Orthopnoea", "boolean", "combobox", booleanValues)
        val dyspnoeaField  = InputField(3, "Presenta disnea?", "EssentialSymptoms.Dyspnoea", "boolean", "combobox", booleanValues)
        val lowerLimbEdemaField = InputField(4, "Presenta edema de miembros inferiores?", "EssentialSymptoms.LowerLimbEdema", "boolean", "combobox", booleanValues)

        val step1 = StepInputFields(1, List(pulmonaryEdemaField, orthopneaField))
        val step2 = StepInputFields(2, List(dyspnoeaField, lowerLimbEdemaField))


        return list;
    }

}
