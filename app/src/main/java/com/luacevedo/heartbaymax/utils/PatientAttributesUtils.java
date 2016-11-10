package com.luacevedo.heartbaymax.utils;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static com.luacevedo.heartbaymax.Constants.Attribute.Type.BOOLEAN;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.LIST;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.NUMBER;
import static com.luacevedo.heartbaymax.Constants.Attribute.Type.STRING;

public class PatientAttributesUtils {

    private static List<String> extendedAttributes = new ArrayList<String>() {{
        add("PreliminaryDiagnosis.PreliminaryICClassExplanation");
        add("ImmediateDiureticTreatment.Furosemide");
        add("ImmediateDiureticTreatment.Epleronone");
        add("ImmediateDiureticTreatment.Spironolactone");
        add("ImmediateVasodilatorTreatment.SodiumNitroprusside");
        add("FinalDiagnosis.ICClassExplanation");
        add("FinalTreatment.Antiarrhythmic");
        add("FinalDiureticTreatment.Furosemide");
        add("FinalDiureticTreatment.Epleronone");
        add("FinalDiureticTreatment.Spironolactone");
        add("FinalDiureticTreatment.Enalapril");
        add("FinalDiureticTreatment.FurosemideForRenalFailure");
        add("FinalVasodilatorTreatment.SodiumNitroprusside");
        add("FinalVasodilatorTreatment.Nitroglycerine");
        add("ImmediateTreatment.Oxygen");
        add("FinalTreatment.Oxygen");
    }};

    static {
        addExtendedAttributes();
    }

    private static void addExtendedAttributes() {

    }

    public static HashMap<String, PatientAttribute> parsePatientAttributes(List<Attribute> attributesList) {
        HashMap<String, PatientAttribute> map = new HashMap<>();
        PatientAttribute patientAttribute = null;
        for (Attribute attribute : attributesList) {
            patientAttribute = new PatientAttribute<>(attribute);
            if (!attribute.needsInputValue()) {
                switch (attribute.getDataType()) {
                    case BOOLEAN:
                        patientAttribute.setValue(false);
                        break;
                    case STRING:
                        patientAttribute.setValue("");
                        break;
                    case LIST:
                        patientAttribute.setValue(new ArrayList<>());
                        break;
                    case NUMBER:
                        patientAttribute.setValue(0);
                        break;
                }
            }
            map.put(attribute.getRoot(), patientAttribute);
        }

        return map;
    }

    public static boolean isExtended(PatientAttribute attribute) {
        return extendedAttributes.contains(attribute.getAttribute().getRoot());
    }

    public static void orderDiagnosisAttributes(List<PatientAttribute> attributes) {
        Comparator<PatientAttribute> comparator = new Comparator<PatientAttribute>() {
            @Override
            public int compare(PatientAttribute c1, PatientAttribute c2) {
                return c1.getAttribute().getAttributeId() - c2.getAttribute().getAttributeId();
            }
        };

        Collections.sort(attributes, comparator);
    }

}
