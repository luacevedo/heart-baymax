package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EqualsConditionTest {

    private String value = "Severos";

    @Test()
    public void testEquals_whenEqual_returnTrue() {
        EqualsCondition condition = new EqualsCondition("root", "Severos");
        Attribute attribute = new Attribute(1, "root", "list", false);
        PatientAttribute<String> patientAttribute = new PatientAttribute<>(attribute, value);

        assertTrue(condition.validate(patientAttribute));
    }

    @Test
    public void testEquals_whenNotEqual_returnFalse() {
        EqualsCondition condition = new EqualsCondition("root", "Moderados");
        Attribute attribute = new Attribute(1, "root", "list", false);
        PatientAttribute<String> patientAttribute = new PatientAttribute<>(attribute, value);

        assertFalse(condition.validate(patientAttribute));
    }

}
