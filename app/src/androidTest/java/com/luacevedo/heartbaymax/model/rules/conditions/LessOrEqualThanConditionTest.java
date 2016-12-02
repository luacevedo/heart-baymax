package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LessOrEqualThanConditionTest {

    @Test()
    public void testLessThan_whenGreater_returnFalse() {
        LessOrEqualThanCondition condition = new LessOrEqualThanCondition("root", 2.0);
        Attribute attribute = new Attribute(1, "root", "number", false);
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, 4.0);

        assertFalse(condition.validate(patientAttribute));
    }

    @Test
    public void testLessThan_whenLess_returnTrue() {
        LessOrEqualThanCondition condition = new LessOrEqualThanCondition("root", 2.0);
        Attribute attribute = new Attribute(1, "root", "number", false);
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, 1.0);

        assertTrue(condition.validate(patientAttribute));
    }

    @Test
    public void testLessThan_whenEqual_returnTrue() {
        LessOrEqualThanCondition condition = new LessOrEqualThanCondition("root", 2.0);
        Attribute attribute = new Attribute(1, "root", "number", false);
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, 2.0);

        assertTrue(condition.validate(patientAttribute));
    }

}
