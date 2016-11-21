package com.luacevedo.heartbaymax.model.rules.conditions;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainsConditionTest {

    private List<String> valuesList = new ArrayList<>();

    @Before
    public void getValuesList() {
        valuesList.add("PulmonaryEdema");
        valuesList.add("Dyspnoea");
    }

    @Test()
    public void testContains_whenContainsValue_returnTrue() {
        ContainsCondition condition = new ContainsCondition("root", "PulmonaryEdema");
        Attribute attribute = new Attribute(1, "root", "list", false);
        PatientAttribute<List<String>> patientAttribute = new PatientAttribute<>(attribute, valuesList);

        assertTrue(condition.validate(patientAttribute));
    }

    @Test
    public void testContains_whenNotContainsValue_returnFalse() {
        ContainsCondition condition = new ContainsCondition("root", "Orthopnoea");
        Attribute attribute = new Attribute(1, "root", "list", false);
        PatientAttribute<List<String>> patientAttribute = new PatientAttribute<>(attribute, valuesList);

        assertFalse(condition.validate(patientAttribute));
    }

}
