package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GreaterThanConditionTest {

    @Test()
    public void testGreaterThanTrue() {
        GreaterThanCondition condition = new GreaterThanCondition("root", 2.0);
        Attribute attribute = new Attribute(1, "root", "number");
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, 4.0);

        assertTrue(condition.validate(patientAttribute));
    }

    @Test
    public void testGreaterThanFalse() {
        GreaterThanCondition condition = new GreaterThanCondition("root", 2.0);
        Attribute attribute = new Attribute(1, "root", "number");
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, 2.0);

        assertFalse(condition.validate(patientAttribute));
    }

}
