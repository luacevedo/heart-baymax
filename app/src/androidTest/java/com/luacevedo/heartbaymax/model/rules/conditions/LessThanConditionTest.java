package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LessThanConditionTest {

    @Test()
    public void testLessThanFalse() {
        LessThanCondition condition = new LessThanCondition("root", 2);
        Attribute attribute = new Attribute(1, "root", "integer");
        PatientAttribute<Integer> patientAttribute = new PatientAttribute<>(attribute, 4);

        assertFalse(condition.validate(patientAttribute));
    }

    @Test
    public void testLessThanTrue() {
        LessThanCondition condition = new LessThanCondition("root", 2);
        Attribute attribute = new Attribute(1, "root", "integer");
        PatientAttribute<Integer> patientAttribute = new PatientAttribute<>(attribute, 1);

        assertTrue(condition.validate(patientAttribute));
    }

}
