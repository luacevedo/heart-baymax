package com.luacevedo.heartbaymax.utils;

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


public class RulesExecutorTest {

    private Patient patient = new Patient();

    @Before
    public void getValuesList() {
        HashMap<String, PatientAttribute> map = new HashMap<>();

        Attribute pulmonaryEdem = new Attribute(1, "EssentialSymptoms.PulmonaryEdema", "boolean", true);
        PatientAttribute<Boolean> pulmonaryEdema = new PatientAttribute<>(pulmonaryEdem, true);
        map.put(pulmonaryEdem.getRoot(), pulmonaryEdema);

        Attribute dysp = new Attribute(2, "EssentialSymptoms.Dyspnoea", "boolean", true);
        PatientAttribute<Boolean> dyspnoea = new PatientAttribute<>(dysp, false);
        map.put(dysp.getRoot(), dyspnoea);

        Attribute orthop = new Attribute(3, "EssentialSymptoms.Orthopnoea", "boolean", true);
        PatientAttribute<Boolean> orthopnoea = new PatientAttribute<>(orthop, false);
        map.put(orthop.getRoot(), orthopnoea);

        Attribute valSE = new Attribute(2, "InitialPhysicalState.EssentialSymptomsAssessment", "number", false);
        PatientAttribute<Double> assessmentES = new PatientAttribute<>(valSE, 5.0);
        map.put(valSE.getRoot(), assessmentES);

        Attribute essentialSymp = new Attribute(3, "InitialPhysicalState.EssentialSymptoms", "list", false);
        List<String> valuesList = new ArrayList<>();
        valuesList.add("PulmonaryEdema");
        PatientAttribute<List<String>> essentialSymptoms = new PatientAttribute<>(essentialSymp, valuesList);
        map.put(essentialSymp.getRoot(), essentialSymptoms);

        Attribute sympType = new Attribute(4, "PreliminaryDiagnosis.SymptomsType", "string", false);
        PatientAttribute<String> symptomsType = new PatientAttribute<>(sympType, "Moderate");
        map.put(sympType.getRoot(), symptomsType);

        patient.setAttributesMap(map);
    }

    @Test()
    public void testCheckConditions_fulfilled() {
        List<BaseCondition> conditionsToFulFill = new ArrayList<>();
        AffirmativeCondition affCondition = new AffirmativeCondition("EssentialSymptoms.PulmonaryEdema");
        conditionsToFulFill.add(affCondition);
        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 3.0);
        conditionsToFulFill.add(greaterThanCondition);
        LessThanCondition lessThanCondition = new LessThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 7.0);
        conditionsToFulFill.add(lessThanCondition);
        ContainsCondition containsCondition = new ContainsCondition("InitialPhysicalState.EssentialSymptoms", "PulmonaryEdema");
        conditionsToFulFill.add(containsCondition);
        NotContainsCondition notContainsCondition = new NotContainsCondition("InitialPhysicalState.EssentialSymptoms", "Dyspnoea");
        conditionsToFulFill.add(notContainsCondition);
        Rule rule = new Rule();
        rule.setParsedConditions(conditionsToFulFill);

        assertTrue(RulesExecutor.checkConditions(rule, patient));
    }

    @Test()
    public void testCheckConditions_notFulfilled() {
        List<BaseCondition> conditionsNotToFulFill = new ArrayList<>();
        AffirmativeCondition affCondition = new AffirmativeCondition("EssentialSymptoms.PulmonaryEdema");
        conditionsNotToFulFill.add(affCondition);
        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 3.0);
        conditionsNotToFulFill.add(greaterThanCondition);
        LessThanCondition lessThanCondition = new LessThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 3.0);
        conditionsNotToFulFill.add(lessThanCondition);
        Rule rule = new Rule();
        rule.setParsedConditions(conditionsNotToFulFill);

        assertFalse(RulesExecutor.checkConditions(rule, patient));
    }

    @Test()
    public void testExecuteActions() {
        List<BaseAction> actions = new ArrayList<>();
        AddNumberAction addNumberAction = new AddNumberAction("InitialPhysicalState.EssentialSymptomsAssessment", 3.0);
        actions.add(addNumberAction);
        AddToListAction addToListAction = new AddToListAction("InitialPhysicalState.EssentialSymptoms", "Orthopnea");
        actions.add(addToListAction);
        AssignAction assignAction = new AssignAction("PreliminaryDiagnosis.SymptomsType", "Urgent");
        actions.add(assignAction);
        Rule rule = new Rule();
        rule.setParsedActions(actions);

        RulesExecutor.executeActions(rule, patient);

        assertEquals(patient.getAttributesMap().get("InitialPhysicalState.EssentialSymptomsAssessment").getValue(), 8.0);
        List<String> expectedList = new ArrayList<>();
        expectedList.add("PulmonaryEdema");
        expectedList.add("Orthopnea");
        assertEquals(patient.getAttributesMap().get("InitialPhysicalState.EssentialSymptoms").getValue(), expectedList);
        assertEquals(patient.getAttributesMap().get("PreliminaryDiagnosis.SymptomsType").getValue(), "Urgent");

    }

    @Test
    public void testExecuteRules() {
        List<Rule> rules = new ArrayList<>();
        List<BaseCondition> conditions = new ArrayList<>();
        AffirmativeCondition affCondition = new AffirmativeCondition("EssentialSymptoms.PulmonaryEdema");
        conditions.add(affCondition);

        List<BaseAction> actions = new ArrayList<>();
        AddNumberAction addNumberAction = new AddNumberAction("InitialPhysicalState.EssentialSymptomsAssessment", 3.0);
        actions.add(addNumberAction);

        Rule rule = new Rule();
        rule.setParsedConditions(conditions);
        rule.setParsedActions(actions);
        rules.add(rule);
        rules.add(rule);
        rules.add(rule);

        RulesExecutor.executeRules(rules, patient);

        assertEquals(patient.getAttributesMap().get("InitialPhysicalState.EssentialSymptomsAssessment").getValue(), 14.0);

    }

}
