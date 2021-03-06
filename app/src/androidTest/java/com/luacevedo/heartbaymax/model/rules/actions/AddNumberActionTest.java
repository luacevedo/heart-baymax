package com.luacevedo.heartbaymax.model.rules.actions;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddNumberActionTest {

    @Test()
    public void testAddNumber_whenAddingAValidNumber_returnTheAddedValue() {
        AddNumberAction action = new AddNumberAction("root", 3.0);
        Attribute attribute = new Attribute(1, "root", "number", false);
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, 2.0);

        action.execute(patientAttribute);
        assertEquals(patientAttribute.getValue().intValue(), 5);
    }

    @Test()
    public void testAddNumber_whenAddingNull_returnTheAlreadyGivenValue() {
        AddNumberAction action = new AddNumberAction("root", 3.0);
        Attribute attribute = new Attribute(1, "root", "number", false);
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, null);

        action.execute(patientAttribute);
        assertEquals(patientAttribute.getValue().intValue(), 3);
    }

}
