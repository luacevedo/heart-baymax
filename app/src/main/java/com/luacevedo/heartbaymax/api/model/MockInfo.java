package com.luacevedo.heartbaymax.api.model;

import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.api.model.fields.Value;
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
import com.luacevedo.heartbaymax.model.rules.conditions.EqualsCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.GreaterThanCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.LessThanCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.NotContainsCondition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockInfo {

    public static HashMap<String, PatientAttribute> getMockedAttributesMap() {
        HashMap<String, PatientAttribute> map = new HashMap<>();

        PatientAttribute<Boolean> name = new PatientAttribute<>(new Attribute(22L, "PatientData.Name", "string", "Nombre"));
        map.put("PatientData.Name", name);

        PatientAttribute<String> age = new PatientAttribute<>(new Attribute(23L, "PatientData.Age", "string", "Edad"));
        map.put("PatientData.Age", age);

        PatientAttribute<Boolean> gender = new PatientAttribute<>(new Attribute(24L, "PatientData.Gender", "string", "Sexo"));
        map.put("PatientData.Gender", gender);

        PatientAttribute<Boolean> edemaPulmonar = new PatientAttribute<>(new Attribute(1L, "EssentialSymptoms.PulmonaryEdema", "boolean", "Edema de pulmon"));
        map.put("EssentialSymptoms.PulmonaryEdema", edemaPulmonar);

        PatientAttribute<String> disnea = new PatientAttribute<>(new Attribute(2L, "EssentialSymptoms.Dyspnoea", "string", "Disnea"));
        map.put("EssentialSymptoms.Dyspnoea", disnea);

        PatientAttribute<Boolean> ortopnea = new PatientAttribute<>(new Attribute(3L, "EssentialSymptoms.Orthopnoea", "boolean", "Ortopnea"));
        map.put("EssentialSymptoms.Orthopnoea", ortopnea);

        PatientAttribute<Boolean> legEdema = new PatientAttribute<>(new Attribute(4L, "EssentialSymptoms.LegEdema", "boolean", "Edema de miembros inferiores"));
        map.put("EssentialSymptoms.LegEdema", legEdema);

        PatientAttribute<Boolean> thirdHeartSound = new PatientAttribute<>(new Attribute(5L, "EssentialSymptoms.ThirdHeartSound", "boolean", "Tercer ruido"));
        map.put("EssentialSymptoms.ThirdHeartSound", thirdHeartSound);

        PatientAttribute<Boolean> hypertension = new PatientAttribute<>(new Attribute(6L, "SecondarySymptoms.Hypertension", "boolean", "Hipertension"));
        map.put("SecondarySymptoms.Hypertension", hypertension);

        PatientAttribute<Boolean> nicturia = new PatientAttribute<>(new Attribute(7L, "SecondarySymptoms.Nicturia", "boolean", "Nicturia"));
        map.put("SecondarySymptoms.Nicturia", nicturia);

        PatientAttribute<Boolean> obesity = new PatientAttribute<>(new Attribute(8L, "SecondarySymptoms.Obesity", "boolean", "Obesidad"));
        map.put("SecondarySymptoms.Obesity", obesity);

        PatientAttribute<Boolean> diabetes = new PatientAttribute<>(new Attribute(9L, "SecondarySymptoms.Diabetes", "boolean", "Diabetes"));
        map.put("SecondarySymptoms.Diabetes", diabetes);

        PatientAttribute<Boolean> previousHeartAttack = new PatientAttribute<>(new Attribute(10L, "SecondarySymptoms.PreviousHeartAttack", "boolean", "Infarto previo"));
        map.put("SecondarySymptoms.PreviousHeartAttack", previousHeartAttack);

        PatientAttribute<Boolean> jugularVeinEngorgement = new PatientAttribute<>(new Attribute(11L, "SecondarySymptoms.JugularVeinEngorgement", "boolean", "Injurgitacion yugular"));
        map.put("SecondarySymptoms.JugularVeinEngorgement", jugularVeinEngorgement);

        PatientAttribute<Boolean> nighttimeCough = new PatientAttribute<>(new Attribute(12L, "SecondarySymptoms.NighttimeCough", "boolean", "Tos nocturna"));
        map.put("SecondarySymptoms.NighttimeCough", nighttimeCough);

        PatientAttribute<Double> essentialSymptomsAssessment = new PatientAttribute<>(new Attribute(2L, "InitialPhysicalState.EssentialSymptomsAssessment", "integer"), 0.0);
        map.put("InitialPhysicalState.EssentialSymptomsAssessment", essentialSymptomsAssessment);

        PatientAttribute<Double> secondarySymptomsAssessment = new PatientAttribute<>(new Attribute(3L, "InitialPhysicalState.SecondarySymptomsAssessment", "integer"), 0.0);
        map.put("InitialPhysicalState.SecondarySymptomsAssessment", secondarySymptomsAssessment);

        PatientAttribute<List<String>> essentialSymptoms = new PatientAttribute<List<String>>(new Attribute(4L, "InitialPhysicalState.EssentialSymptoms", "list"), new ArrayList<String>());
        map.put("InitialPhysicalState.EssentialSymptoms", essentialSymptoms);

        PatientAttribute<List<String>> secondarySymptoms = new PatientAttribute<List<String>>(new Attribute(5L, "InitialPhysicalState.SecondarySymptoms", "list"), new ArrayList<String>());
        map.put("InitialPhysicalState.SecondarySymptoms", secondarySymptoms);

        PatientAttribute<String> symptomsType = new PatientAttribute<>(new Attribute(6L, "PreliminaryDiagnosis.SymptomsType", "string", "Tipos de sintomas"));
        map.put("PreliminaryDiagnosis.SymptomsType", symptomsType);

        return map;
    }

    public static List<StepInputFields> getPreliminaryDiagnosisFields() {

        List<Value> booleanValues = new ArrayList<>();
        booleanValues.add(new Value("0", "No"));
        booleanValues.add(new Value("1", "Si"));

        List<Value> disphoneaValues = new ArrayList<>();
        disphoneaValues.add(new Value("0", "No presenta"));
        disphoneaValues.add(new Value("1", "Tipo 1"));
        disphoneaValues.add(new Value("2", "Tipo 2"));
        disphoneaValues.add(new Value("3", "Tipo 3"));
        disphoneaValues.add(new Value("4", "Tipo 4"));

        List<Value> genderValues = new ArrayList<>();
        genderValues.add(new Value("F", "Femenino"));
        genderValues.add(new Value("M", "Masculino"));

        List<InputField> firstInputFields = new ArrayList<>();
        InputField name = new InputField(1, "Nombre?", "PatientData.Name", "string", "text");
        firstInputFields.add(name);
        InputField age = new InputField(2, "Edad?", "PatientData.Age", "string", "text");
        firstInputFields.add(age);
        InputField gender = new InputField(3, "Genero?", "PatientData.Gender", "select", "combobox", genderValues);
        firstInputFields.add(gender);

        StepInputFields firstStepInputFields = new StepInputFields(1, firstInputFields);

        List<InputField> secondInputFields = new ArrayList<>();
        InputField pulmonaryEdema = new InputField(1, "Edema pulmonar?", "EssentialSymptoms.PulmonaryEdema", "boolean", "combobox", booleanValues);
        secondInputFields.add(pulmonaryEdema);
        InputField dyspnoea = new InputField(2, "Disnea?", "EssentialSymptoms.Dyspnoea", "select", "combobox", disphoneaValues);
        secondInputFields.add(dyspnoea);
        InputField orthopnoea = new InputField(3, "Orthopnoea?", "EssentialSymptoms.Orthopnoea", "boolean", "combobox", booleanValues);
        secondInputFields.add(orthopnoea);
        InputField legEdema = new InputField(4, "Edema de miembros inferiores?", "EssentialSymptoms.LegEdema", "boolean", "combobox", booleanValues);
        secondInputFields.add(legEdema);
        InputField thirdHeartSound = new InputField(5, "Tercer ruido?", "EssentialSymptoms.ThirdHeartSound", "boolean", "combobox", booleanValues);
        secondInputFields.add(thirdHeartSound);

        StepInputFields secondStepInputFields = new StepInputFields(1, secondInputFields);

        List<InputField> thirdInputFields = new ArrayList<>();
        InputField hypertension = new InputField(6, "Hipertension?", "SecondarySymptoms.Hypertension", "boolean", "combobox", booleanValues);
        thirdInputFields.add(hypertension);
        InputField nicturia = new InputField(7, "Nicturia?", "SecondarySymptoms.Nicturia", "boolean", "combobox", booleanValues);
        thirdInputFields.add(nicturia);
        InputField obesity = new InputField(8, "Obesidad?", "SecondarySymptoms.Obesity", "boolean", "combobox", booleanValues);
        thirdInputFields.add(obesity);
        InputField diabetes = new InputField(9, "Diabetes?", "SecondarySymptoms.Diabetes", "boolean", "combobox", booleanValues);
        thirdInputFields.add(diabetes);
        InputField previousHeartAttack = new InputField(10, "Infarto previo?", "SecondarySymptoms.PreviousHeartAttack", "boolean", "combobox", booleanValues);
        thirdInputFields.add(previousHeartAttack);
        InputField jugularVeinEngorgement = new InputField(11, "Ingurgitacion yugular?", "SecondarySymptoms.JugularVeinEngorgement", "boolean", "combobox", booleanValues);
        thirdInputFields.add(jugularVeinEngorgement);
        InputField nighttimeCough = new InputField(12, "Tos nocturna?", "SecondarySymptoms.NighttimeCough", "boolean", "combobox", booleanValues);
        thirdInputFields.add(nighttimeCough);

        StepInputFields thirdStepInputFields = new StepInputFields(3, thirdInputFields);

        List<StepInputFields> list = new ArrayList<>();

        list.add(firstStepInputFields);
        list.add(secondStepInputFields);
        list.add(thirdStepInputFields);



        return list;
    }

    public static List<Rule> getMockedRules() {
        List<Rule> rules = new ArrayList<>();
//        addEdemaPulmonarRule(rules);
//        addDisnea(rules);
//        addOrtopnea(rules);
//        addRule4(rules);
//        addRule5(rules);
//        addRule6(rules);
//        addRule7(rules);
//        addRule8(rules);
        return rules;
    }


//    private static void addEdemaPulmonarRule(List<Rule> rules) {
//        Rule rule = new Rule();
//        rule.setId(1L);
//        List<BaseCondition> conditions1 = new ArrayList<>();
//        AffirmativeCondition affCondition1 = new AffirmativeCondition("EssentialSymptoms.PulmonaryEdema");
//        conditions1.add(affCondition1);
//        rule.setParsedConditions(conditions1);
//
//        List<BaseAction> actions = new ArrayList<>();
//        AddNumberAction addNumberAction1 = new AddNumberAction("InitialPhysicalState.EssentialSymptomsAssessment", 3.0);
//        actions.add(addNumberAction1);
//        AddToListAction addToListAction = new AddToListAction("InitialPhysicalState.EssentialSymptoms", "PulmonaryEdema");
//        actions.add(addToListAction);
//        rule.setParsedActions(actions);
//
//        rules.add(rule);
//    }
//
//    private static void addDisnea(List<Rule> rules) {
//        Rule rule = new Rule();
//        rule.setId(2L);
//        List<BaseCondition> conditions1 = new ArrayList<>();
//        EqualsCondition affCondition1 = new EqualsCondition("EssentialSymptoms.Dyspnoea", "4");
//        conditions1.add(affCondition1);
//        rule.setParsedConditions(conditions1);
//
//        List<BaseAction> actions = new ArrayList<>();
//        AddNumberAction addNumberAction1 = new AddNumberAction("InitialPhysicalState.EssentialSymptomsAssessment", 3.0);
//        actions.add(addNumberAction1);
//        AddToListAction addToListAction = new AddToListAction("InitialPhysicalState.EssentialSymptoms", "Disnea");
//        actions.add(addToListAction);
//        rule.setParsedActions(actions);
//
//        rules.add(rule);
//    }
//
//    private static void addOrtopnea(List<Rule> rules) {
//        Rule rule = new Rule();
//        rule.setId(3L);
//        List<BaseCondition> conditions1 = new ArrayList<>();
//        AffirmativeCondition affCondition1 = new AffirmativeCondition("EssentialSymptoms.Orthopnoea");
//        conditions1.add(affCondition1);
//        rule.setParsedConditions(conditions1);
//
//        List<BaseAction> actions = new ArrayList<>();
//        AddNumberAction addNumberAction1 = new AddNumberAction("InitialPhysicalState.EssentialSymptomsAssessment", 2.0);
//        actions.add(addNumberAction1);
//        AddToListAction addToListAction = new AddToListAction("InitialPhysicalState.EssentialSymptoms", "Ortopnea");
//        actions.add(addToListAction);
//        rule.setParsedActions(actions);
//
//        rules.add(rule);
//    }
//
//    private static void addRule4(List<Rule> rules) {
//        Rule rule = new Rule();
//        rule.setId(4L);
//
//        List<BaseCondition> conditions = new ArrayList<>();
//        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 4);
//        conditions.add(greaterThanCondition);
//        ContainsCondition containsCondition = new ContainsCondition("InitialPhysicalState.EssentialSymptoms", "PulmonaryEdema");
//        conditions.add(containsCondition);
//        rule.setParsedConditions(conditions);
//
//        List<BaseAction> actions = new ArrayList<>();
//        AssignAction assignAction1 = new AssignAction("PreliminaryDiagnosis.SymptomsType", "Urgentes");
//        actions.add(assignAction1);
//
//        rule.setParsedConditions(conditions);
//        rule.setParsedActions(actions);
//
//        List<Long> rulesToExclude = new ArrayList<>();
//        rulesToExclude.add(5L);
//        rulesToExclude.add(6L);
//        rulesToExclude.add(7L);
//        rulesToExclude.add(8L);
//        rule.setRulesToExclude(rulesToExclude);
//
//        rules.add(rule);
//    }
//
//    private static void addRule5(List<Rule> rules) {
//        Rule rule = new Rule();
//        rule.setId(5L);
//
//        List<BaseCondition> conditions = new ArrayList<>();
//        GreaterThanCondition greaterThanCondition1 = new GreaterThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 4);
//        conditions.add(greaterThanCondition1);
//        NotContainsCondition notContainsCondition = new NotContainsCondition("InitialPhysicalState.EssentialSymptoms", "PulmonaryEdema");
//        conditions.add(notContainsCondition);
//        rule.setParsedConditions(conditions);
//
//        List<BaseAction> actions1 = new ArrayList<>();
//        AssignAction assignAction1 = new AssignAction("PreliminaryDiagnosis.SymptomsType", "Moderados");
//        actions1.add(assignAction1);
//
//        rule.setParsedConditions(conditions);
//        rule.setParsedActions(actions1);
//
//        List<Long> rulesToExclude = new ArrayList<>();
//        rulesToExclude.add(6L);
//        rulesToExclude.add(7L);
//        rulesToExclude.add(8L);
//        rule.setRulesToExclude(rulesToExclude);
//
//        rules.add(rule);
//    }
//
//    private static void addRule6(List<Rule> rules) {
//        Rule rule = new Rule();
//        rule.setId(6L);
//
//        List<BaseCondition> conditions = new ArrayList<>();
//        LessThanCondition lessThanCondition = new LessThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 4);
//        conditions.add(lessThanCondition);
//        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 0);
//        conditions.add(greaterThanCondition);
//        ContainsCondition containsCondition = new ContainsCondition("InitialPhysicalState.EssentialSymptoms", "PulmonaryEdema");
//        conditions.add(containsCondition);
//        rule.setParsedConditions(conditions);
//
//        List<BaseAction> actions1 = new ArrayList<>();
//        AssignAction assignAction1 = new AssignAction("PreliminaryDiagnosis.SymptomsType", "Moderados");
//        actions1.add(assignAction1);
//
//        rule.setParsedConditions(conditions);
//        rule.setParsedActions(actions1);
//
//        List<Long> rulesToExclude = new ArrayList<>();
//        rulesToExclude.add(7L);
//        rulesToExclude.add(8L);
//        rule.setRulesToExclude(rulesToExclude);
//
//        rules.add(rule);
//    }
//
//    private static void addRule7(List<Rule> rules) {
//        Rule rule = new Rule();
//        rule.setId(7L);
//
//        List<BaseCondition> conditions = new ArrayList<>();
//        LessThanCondition lessThanCondition = new LessThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 4);
//        conditions.add(lessThanCondition);
//        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 0);
//        conditions.add(greaterThanCondition);
//        NotContainsCondition notContainsCondition = new NotContainsCondition("InitialPhysicalState.EssentialSymptoms", "PulmonaryEdema");
//        conditions.add(notContainsCondition);
//        rule.setParsedConditions(conditions);
//
//        List<BaseAction> actions1 = new ArrayList<>();
//        AssignAction assignAction1 = new AssignAction("PreliminaryDiagnosis.SymptomsType", "Escasos");
//        actions1.add(assignAction1);
//
//        rule.setParsedConditions(conditions);
//        rule.setParsedActions(actions1);
//
//        List<Long> rulesToExclude = new ArrayList<>();
//        rulesToExclude.add(8L);
//        rule.setRulesToExclude(rulesToExclude);
//
//        rules.add(rule);
//    }
//
//    private static void addRule8(List<Rule> rules) {
//        Rule rule = new Rule();
//        rule.setId(8L);
//
//        List<BaseCondition> conditions = new ArrayList<>();
//        LessThanCondition lessThanCondition = new LessThanCondition("InitialPhysicalState.EssentialSymptomsAssessment", 1);
//        conditions.add(lessThanCondition);
//        rule.setParsedConditions(conditions);
//
//        List<BaseAction> actions1 = new ArrayList<>();
//        AssignAction assignAction1 = new AssignAction("PreliminaryDiagnosis.SymptomsType", "No presenta");
//        actions1.add(assignAction1);
//
//        rule.setParsedConditions(conditions);
//        rule.setParsedActions(actions1);
//        rules.add(rule);
//    }


}
