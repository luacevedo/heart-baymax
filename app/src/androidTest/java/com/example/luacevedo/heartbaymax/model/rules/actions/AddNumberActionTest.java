package com.example.luacevedo.heartbaymax.model.rules.actions;

import com.example.luacevedo.heartbaymax.api.model.Attribute;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddNumberActionTest {
    @Test()
    public void testAddNumberAction() {
        AddNumberAction action = new AddNumberAction("root", 3);
        Attribute attribute = new Attribute(1, "root", "integer");
        PatientAttribute<Integer> patientAttribute = new PatientAttribute<>(attribute, 2);

        action.execute(patientAttribute);
        assertEquals(patientAttribute.getValue().intValue(), 5);
    }

    @Test()
    public void testAddNumberActionWhenNull() {
        AddNumberAction action = new AddNumberAction("root", 3);
        Attribute attribute = new Attribute(1, "root", "integer");
        PatientAttribute<Integer> patientAttribute = new PatientAttribute<>(attribute, null);

        action.execute(patientAttribute);
        assertEquals(patientAttribute.getValue().intValue(), 3);
    }

}
