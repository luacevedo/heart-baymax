package com.luacevedo.heartbaymax.api.model;

import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
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

        Attribute edemaPulm = new Attribute(1L, "EssentialSymptoms.PulmonaryEdema", "boolean");
        PatientAttribute<Boolean> edemaPulmonar = new PatientAttribute<>(edemaPulm, true);
        map.put(edemaPulm.getRoot(), edemaPulmonar);

        Attribute disn = new Attribute(2L, "EssentialSymptoms.Dyspnoea", "boolean");
        PatientAttribute<Boolean> disnea = new PatientAttribute<>(disn, false);
        map.put(disn.getRoot(), disnea);

        Attribute ortpn = new Attribute(3L, "EssentialSymptoms.Orthopnoea", "boolean");
        PatientAttribute<Boolean> ortopnea = new PatientAttribute<>(ortpn, false);
        map.put(ortpn.getRoot(), ortopnea);

        Attribute valSE = new Attribute(2L, "InitialPhysicalState.EssentialSymptomsAssessment", "integer");
        PatientAttribute<Integer> valoracionSE = new PatientAttribute<>(valSE, 0);
        map.put(valSE.getRoot(), valoracionSE);

        Attribute sintomasEsenc = new Attribute(3L, "InitialPhysicalState.EssentialSymptoms", "list");
        PatientAttribute<List<String>> sintomasEsenciales = new PatientAttribute<List<String>>(sintomasEsenc, new ArrayList<String>());
        map.put(sintomasEsenc.getRoot(), sintomasEsenciales);

        Attribute tipoSint = new Attribute(4L, "PreliminaryDiagnosis.SymptomsType", "string");
        PatientAttribute<String> tipoDeSintomas = new PatientAttribute<>(tipoSint);
        map.put(tipoSint.getRoot(), tipoDeSintomas);

        return map;
    }

    public static List<StepInputFields> getPreliminaryDiagnosisFields() {
        StepInputFields stepInputFields = new StepInputFields();
        stepInputFields.setStep(1);
        List<InputField> inputFields = new ArrayList<>();

        InputField pulmonaryEdema = new InputField(1, "Edema pulmonar?", "EssentialSymptoms.PulmonaryEdema", "select", "combobox");
        inputFields.add(pulmonaryEdema);

        InputField dyspnoea = new InputField(2, "Disnea?", "EssentialSymptoms.Dyspnoea", "select", "combobox");
        inputFields.add(dyspnoea);

        InputField orthopnoea = new InputField(3, "Edema pulmonar?", "EssentialSymptoms.Orthopnoea", "select", "combobox");
        inputFields.add(orthopnoea);
        stepInputFields.setInputFields(inputFields);

        List<StepInputFields> list = new ArrayList<>();
        list.add(stepInputFields);

        return list;
    }

}
