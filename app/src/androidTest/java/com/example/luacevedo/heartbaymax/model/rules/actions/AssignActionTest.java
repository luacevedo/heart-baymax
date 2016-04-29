package com.example.luacevedo.heartbaymax.model.rules.actions;

import com.example.luacevedo.heartbaymax.api.model.Attribute;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssignActionTest {

    @Test()
    public void testAddToListAction() {
        AssignAction action = new AssignAction("root", "PulmonaryEdema");
        Attribute attribute = new Attribute(1, "root", "string");
        PatientAttribute<String> patientAttribute = new PatientAttribute<>(attribute, null);

        action.execute(patientAttribute);
        assertEquals(patientAttribute.getValue(), "PulmonaryEdema");
    }
}
