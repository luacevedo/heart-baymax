package com.luacevedo.heartbaymax.model.rules.actions;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AddToListActionTest {
    private List<String> valuesList = new ArrayList<>();

    @Before
    public void setValuesList() {
        valuesList.add("Dyspnoea");
    }

    @Test()
    public void testAddToListAction() {
        AddToListAction action = new AddToListAction("root", "PulmonaryEdema");
        Attribute attribute = new Attribute(1, "root", "list");
        PatientAttribute<List<String>> patientAttribute = new PatientAttribute<>(attribute, valuesList);

        action.execute(patientAttribute);
        assertTrue(patientAttribute.getValue().contains("PulmonaryEdema"));
    }


}
