package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GreaterThanOrEqualConditionTest {

    @Test()
    public void testGreaterOrEqualThan_whenGreater_returnTrue() {
        GreaterOrEqualThanCondition condition = new GreaterOrEqualThanCondition("root", 2.0);
        Attribute attribute = new Attribute(1, "root", "number", false);
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, 4.0);

        assertTrue(condition.validate(patientAttribute));
    }

    @Test
    public void testGreaterOrEqualThan_whenEqual_returnTrue() {
        GreaterOrEqualThanCondition condition = new GreaterOrEqualThanCondition("root", 2.0);
        Attribute attribute = new Attribute(1, "root", "number", false);
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, 2.0);

        assertTrue(condition.validate(patientAttribute));
    }

    @Test
    public void testGreaterOrEqualThan_whenLess_returnFalse() {
        GreaterOrEqualThanCondition condition = new GreaterOrEqualThanCondition("root", 2.0);
        Attribute attribute = new Attribute(1, "root", "number", false);
        PatientAttribute<Double> patientAttribute = new PatientAttribute<>(attribute, 1.0);

        assertFalse(condition.validate(patientAttribute));
    }

}
