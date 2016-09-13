package com.luacevedo.heartbaymax.helpers;

import com.luacevedo.heartbaymax.api.model.patients.Attribute;
import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.model.rules.actions.AddNumberAction;
import com.luacevedo.heartbaymax.model.rules.actions.AddToListAction;
import com.luacevedo.heartbaymax.model.rules.actions.AssignAction;
import com.luacevedo.heartbaymax.model.rules.actions.BaseAction;
import com.luacevedo.heartbaymax.model.rules.conditions.AffirmativeCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.BaseCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.ContainsCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.GreaterThanCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.LessThanCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.NotContainsCondition;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RulesHelperTest {

    private Patient patient = new Patient();

    @Before
    public void getValuesList() {
        HashMap<String, PatientAttribute> map = new HashMap<>();

        Attribute pulmonaryEdem = new Attribute(1L, "EssentialSymptoms.PulmonaryEdema", "boolean");
        PatientAttribute<Boolean> pulmonaryEdema = new PatientAttribute<>(pulmonaryEdem, true);
        map.put(pulmonaryEdem.getRoot(), pulmonaryEdema);

        Attribute dysp = new Attribute(2L, "EssentialSymptoms.Dyspnoea", "boolean");
        PatientAttribute<Boolean> dyspnoea = new PatientAttribute<>(dysp, false);
        map.put(dysp.getRoot(), dyspnoea);

        Attribute orthop = new Attribute(3L, "EssentialSymptoms.Orthopnoea", "boolean");
        PatientAttribute<Boolean> orthopnoea = new PatientAttribute<>(orthop, false);
        map.put(orthop.getRoot(), orthopnoea);

        Attribute valSE = new Attribute(2L, "InitialPhysicalState.EssentialSymptomsAssessment", "integer");
        PatientAttribute<Integer> assessmentES = new PatientAttribute<>(valSE, 5);
        map.put(valSE.getRoot(), assessmentES);

        Attribute essentialSymp = new Attribute(3L, "InitialPhysicalState.EssentialSymptoms", "list");
        List<String> valuesList = new ArrayList<>();
        valuesList.add("PulmonaryEdema");
        PatientAttribute<List<String>> essentialSymptoms = new PatientAttribute<>(essentialSymp, valuesList);
        map.put(essentialSymp.getRoot(), essentialSymptoms);

        Attribute sympType = new Attribute(4L, "PreliminaryDiagnosis.SymptomsType", "string");
        PatientAttribute<String> symptomsType = new PatientAttribute<>(sympType, "Moderate");
        map.put(sympType.getRoot(), symptomsType);

        patient.setAttributesMap(map);
    }

    @Test()
    public void testCheckConditions_fulfilled() {
        List<BaseCondition> conditionsToFulFill = new ArrayList<>();
        AffirmativeCondition affCondition = new AffirmativeCondition("EssentialSymptoms.PulmonaryEdema");
        conditionsToFulFill.add(affCondition);
        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 3);
        conditionsToFulFill.add(greaterThanCondition);
        LessThanCondition lessThanCondition = new LessThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 7);
        conditionsToFulFill.add(lessThanCondition);
        ContainsCondition containsCondition = new ContainsCondition("InitialPhysicalState.EssentialSymptoms", "PulmonaryEdema");
        conditionsToFulFill.add(containsCondition);
        NotContainsCondition notContainsCondition = new NotContainsCondition("InitialPhysicalState.EssentialSymptoms", "Dyspnoea");
        conditionsToFulFill.add(notContainsCondition);

        assertTrue(RulesHelper.checkConditions(conditionsToFulFill, patient));
    }

    @Test()
    public void testCheckConditions_notFulfilled() {
        List<BaseCondition> conditionsNotToFulFill = new ArrayList<>();
        AffirmativeCondition affCondition = new AffirmativeCondition("EssentialSymptoms.PulmonaryEdema");
        conditionsNotToFulFill.add(affCondition);
        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 3);
        conditionsNotToFulFill.add(greaterThanCondition);
        LessThanCondition lessThanCondition = new LessThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 3);
        conditionsNotToFulFill.add(lessThanCondition);

        assertFalse(RulesHelper.checkConditions(conditionsNotToFulFill, patient));
    }

    @Test()
    public void testExecuteActions() {
        List<BaseAction> actions = new ArrayList<>();
        AddNumberAction addNumberAction = new AddNumberAction("InitialPhysicalState.EssentialSymptomsAssessment", 3);
        actions.add(addNumberAction);
        AddToListAction addToListAction = new AddToListAction("InitialPhysicalState.EssentialSymptoms", "Orthopnea");
        actions.add(addToListAction);
        AssignAction assignAction = new AssignAction("PreliminaryDiagnosis.SymptomsType", "Urgent");
        actions.add(assignAction);

        RulesHelper.executeActions(actions, patient);

        assertEquals(patient.getAttributesMap().get("InitialPhysicalState.EssentialSymptomsAssessment").getValue(), 8);
        List<String> expectedList = new ArrayList<>();
        expectedList.add("PulmonaryEdema");
        expectedList.add("Orthopnea");
        assertEquals(patient.getAttributesMap().get("InitialPhysicalState.EssentialSymptoms").getValue(), expectedList);
        assertEquals(patient.getAttributesMap().get("PreliminaryDiagnosis.SymptomsType").getValue(), "Urgent");

    }

    @Test
    public void testExecuteRules(){
        List<Rule> rules = new ArrayList<>();
        List<BaseCondition> conditions = new ArrayList<>();
        AffirmativeCondition affCondition = new AffirmativeCondition("EssentialSymptoms.PulmonaryEdema");
        conditions.add(affCondition);

        List<BaseAction> actions = new ArrayList<>();
        AddNumberAction addNumberAction = new AddNumberAction("InitialPhysicalState.EssentialSymptomsAssessment", 3);
        actions.add(addNumberAction);

        Rule rule = new Rule();
        rule.setParsedConditions(conditions);
        rule.setParsedActions(actions);
        rules.add(rule);
        rules.add(rule);
        rules.add(rule);

        RulesHelper.executeRules(rules, patient);

        assertEquals(patient.getAttributesMap().get("InitialPhysicalState.EssentialSymptomsAssessment").getValue(), 14);

        assertTrue(RulesHelper.checkConditions(conditions, patient));

    }

}
