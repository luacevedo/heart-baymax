package com.example.luacevedo.heartbaymax.model.rules.actions;

import com.example.luacevedo.heartbaymax.api.model.Attribute;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AddToListActionTest {
    private List<String> valuesList = new ArrayList<>();

    @Before
    public void getValuesList() {
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
