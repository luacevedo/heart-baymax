package com.example.luacevedo.heartbaymax.model.rules.conditions;

import com.example.luacevedo.heartbaymax.api.model.Attribute;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AffirmativeConditionTest {
    @Test()
    public void testAffirmativeTrue() {
        AffirmativeCondition test = new AffirmativeCondition("root");

        Attribute attribute = new Attribute(1, "root", "boolean");
        PatientAttribute<Boolean> patientAttribute = new PatientAttribute<>(attribute, true);

        assertTrue(test.validate(patientAttribute));
    }

    @Test
    public void testAffirmativeFalse() {
        AffirmativeCondition test = new AffirmativeCondition("root");

        Attribute attribute = new Attribute(1, "root", "boolean");
        PatientAttribute<Boolean> patientAttribute = new PatientAttribute<>(attribute, false);

        assertFalse(test.validate(patientAttribute));
    }

}
