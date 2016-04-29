package com.example.luacevedo.heartbaymax.model.rules.conditions;

import com.example.luacevedo.heartbaymax.api.model.Attribute;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotContainsConditionTest {

    private List<String> valuesList = new ArrayList<>();

    @Before
    public void getValuesList() {
        valuesList.add("PulmonaryEdema");
        valuesList.add("Dyspnoea");
    }

    @Test()
    public void testNotContainsTrue() {
        NotContainsCondition condition = new NotContainsCondition("root", "Orthopnoea");
        Attribute attribute = new Attribute(1, "root", "list");
        PatientAttribute<List<String>> patientAttribute = new PatientAttribute<>(attribute, valuesList);

        assertTrue(condition.validate(patientAttribute));
    }

    @Test
    public void testNotContainsFalse() {
        NotContainsCondition condition = new NotContainsCondition("root", "PulmonaryEdema");
        Attribute attribute = new Attribute(1, "root", "list");
        PatientAttribute<List<String>> patientAttribute = new PatientAttribute<>(attribute, valuesList);

        assertFalse(condition.validate(patientAttribute));
    }

}
