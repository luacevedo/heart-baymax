package com.example.luacevedo.heartbaymax.api.model;

import com.example.luacevedo.heartbaymax.model.actions.AddNumberAction;
import com.example.luacevedo.heartbaymax.model.actions.AddToListAction;
import com.example.luacevedo.heartbaymax.model.actions.AssignAction;
import com.example.luacevedo.heartbaymax.model.actions.BaseAction;
import com.example.luacevedo.heartbaymax.model.conditions.AffirmativeCondition;
import com.example.luacevedo.heartbaymax.model.conditions.BaseCondition;
import com.example.luacevedo.heartbaymax.model.conditions.ContainsCondition;
import com.example.luacevedo.heartbaymax.model.conditions.GreaterThanCondition;
import com.example.luacevedo.heartbaymax.model.conditions.LessThanCondition;
import com.example.luacevedo.heartbaymax.model.conditions.NotContainsCondition;
import com.example.luacevedo.heartbaymax.model.patient.Patient;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockInfo {

    public static Patient createPatient(){
        Patient patient = new Patient();
        patient.setAttributesMap(getMockedAttributesMap());
        return patient;
    }

    private static HashMap<String, PatientAttribute> getMockedAttributesMap() {
        HashMap<String, PatientAttribute> map = new HashMap<>();

        Attribute edemaPulm = new Attribute(1L, "SintomasEsenciales.EdemaPulmonar", "boolean");
        PatientAttribute<Boolean> edemaPulmonar = new PatientAttribute<>(edemaPulm, true);
        map.put(edemaPulm.getRoot(), edemaPulmonar);

        Attribute disn = new Attribute(2L, "SintomasEsenciales.Disnea", "boolean");
        PatientAttribute<Boolean> disnea = new PatientAttribute<>(disn, true);
        map.put(disn.getRoot(), disnea);

        Attribute ortpn = new Attribute(3L, "SintomasEsenciales.Ortopnea", "boolean");
        PatientAttribute<Boolean> ortopnea = new PatientAttribute<>(ortpn, true);
        map.put(ortpn.getRoot(), ortopnea);

        Attribute valSE = new Attribute(2L, "EstadoFisicoInicial.ValoracionSintomasEsenciales", "integer");
        PatientAttribute<Integer> valoracionSE = new PatientAttribute<>(valSE, 0);
        map.put(valSE.getRoot(), valoracionSE);

        Attribute sintomasEsenc = new Attribute(3L, "EstadoFisicoInicial.SintomasEsenciales", "list");
        PatientAttribute<List<String>> sintomasEsenciales = new PatientAttribute<List<String>>(sintomasEsenc, new ArrayList<String>());
        map.put(sintomasEsenc.getRoot(), sintomasEsenciales);

        Attribute tipoSint = new Attribute(4L, "DiagnósticoPreliminar.TipoDeSintomas", "string");
        PatientAttribute<String> tipoDeSintomas = new PatientAttribute<>(tipoSint);
        map.put(tipoSint.getRoot(), tipoDeSintomas);

        return map;
    }

    public static List<Rule> getMockedRules() {
        List<Rule> rules = new ArrayList<>();
        addEdemaPulmonarRule(rules);
        addDisnea(rules);
        addOrtopnea(rules);
        addRule4(rules);
        addRule5(rules);
        addRule6(rules);
        addRule7(rules);
        addRule8(rules);
        return rules;
    }

    private static void addEdemaPulmonarRule(List<Rule> rules) {
        Rule rule = new Rule();
        rule.setId(1L);
        List<BaseCondition> conditions1 = new ArrayList<>();
        AffirmativeCondition affCondition1 = new AffirmativeCondition("SintomasEsenciales.EdemaPulmonar");
        conditions1.add(affCondition1);
        rule.setParsedConditions(conditions1);

        List<BaseAction> actions = new ArrayList<>();
        AddNumberAction addNumberAction1 = new AddNumberAction("EstadoFisicoInicial.ValoracionSintomasEsenciales", 3);
        actions.add(addNumberAction1);
        AddToListAction addToListAction = new AddToListAction("EstadoFisicoInicial.SintomasEsenciales", "EdemaPulmonar");
        actions.add(addToListAction);
        rule.setParsedActions(actions);

        rules.add(rule);
    }

    private static void addDisnea(List<Rule> rules) {
        Rule rule = new Rule();
        rule.setId(2L);
        List<BaseCondition> conditions1 = new ArrayList<>();
        AffirmativeCondition affCondition1 = new AffirmativeCondition("SintomasEsenciales.Disnea");
        conditions1.add(affCondition1);
        rule.setParsedConditions(conditions1);

        List<BaseAction> actions = new ArrayList<>();
        AddNumberAction addNumberAction1 = new AddNumberAction("EstadoFisicoInicial.ValoracionSintomasEsenciales", 3);
        actions.add(addNumberAction1);
        AddToListAction addToListAction = new AddToListAction("EstadoFisicoInicial.SintomasEsenciales", "Disnea");
        actions.add(addToListAction);
        rule.setParsedActions(actions);

        rules.add(rule);
    }

    private static void addOrtopnea(List<Rule> rules) {
        Rule rule = new Rule();
        rule.setId(3L);
        List<BaseCondition> conditions1 = new ArrayList<>();
        AffirmativeCondition affCondition1 = new AffirmativeCondition("SintomasEsenciales.Ortopnea");
        conditions1.add(affCondition1);
        rule.setParsedConditions(conditions1);

        List<BaseAction> actions = new ArrayList<>();
        AddNumberAction addNumberAction1 = new AddNumberAction("EstadoFisicoInicial.ValoracionSintomasEsenciales", 2);
        actions.add(addNumberAction1);
        AddToListAction addToListAction = new AddToListAction("EstadoFisicoInicial.SintomasEsenciales", "Ortopnea");
        actions.add(addToListAction);
        rule.setParsedActions(actions);

        rules.add(rule);
    }

    private static void addRule4(List<Rule> rules) {
        Rule rule = new Rule();
        rule.setId(4L);

        List<BaseCondition> conditions = new ArrayList<>();
        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("EstadoFisicoInicial.ValoracionSintomasEsenciales", 4);
        conditions.add(greaterThanCondition);
        ContainsCondition containsCondition = new ContainsCondition("EstadoFisicoInicial.SintomasEsenciales", "EdemaPulmonar");
        conditions.add(containsCondition);
        rule.setParsedConditions(conditions);

        List<BaseAction> actions = new ArrayList<>();
        AssignAction assignAction1 = new AssignAction("DiagnósticoPreliminar.TipoDeSintomas", "Urgentes");
        actions.add(assignAction1);

        rule.setParsedConditions(conditions);
        rule.setParsedActions(actions);
        rules.add(rule);
    }

    private static void addRule5(List<Rule> rules) {
        Rule rule = new Rule();
        rule.setId(5L);

        List<BaseCondition> conditions = new ArrayList<>();
        GreaterThanCondition greaterThanCondition1 = new GreaterThanCondition("EstadoFisicoInicial.ValoracionSintomasEsenciales", 4);
        conditions.add(greaterThanCondition1);
        NotContainsCondition notContainsCondition = new NotContainsCondition("EstadoFisicoInicial.SintomasEsenciales", "EdemaPulmonar");
        conditions.add(notContainsCondition);
        rule.setParsedConditions(conditions);

        List<BaseAction> actions1 = new ArrayList<>();
        AssignAction assignAction1 = new AssignAction("DiagnósticoPreliminar.TipoDeSintomas", "Moderados");
        actions1.add(assignAction1);

        rule.setParsedConditions(conditions);
        rule.setParsedActions(actions1);
        rules.add(rule);
    }

    private static void addRule6(List<Rule> rules) {
        Rule rule = new Rule();
        rule.setId(6L);

        List<BaseCondition> conditions = new ArrayList<>();
        LessThanCondition lessThanCondition = new LessThanCondition("EstadoFisicoInicial.ValoracionSintomasEsenciales", 4);
        conditions.add(lessThanCondition);
        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("EstadoFisicoInicial.ValoracionSintomasEsenciales", 0);
        conditions.add(greaterThanCondition);
        ContainsCondition containsCondition = new ContainsCondition("EstadoFisicoInicial.SintomasEsenciales", "EdemaPulmonar");
        conditions.add(containsCondition);
        rule.setParsedConditions(conditions);

        List<BaseAction> actions1 = new ArrayList<>();
        AssignAction assignAction1 = new AssignAction("DiagnósticoPreliminar.TipoDeSintomas", "Moderados");
        actions1.add(assignAction1);

        rule.setParsedConditions(conditions);
        rule.setParsedActions(actions1);
        rules.add(rule);
    }

    private static void addRule7(List<Rule> rules) {
        Rule rule = new Rule();
        rule.setId(7L);

        List<BaseCondition> conditions = new ArrayList<>();
        LessThanCondition lessThanCondition = new LessThanCondition("EstadoFisicoInicial.ValoracionSintomasEsenciales", 4);
        conditions.add(lessThanCondition);
        GreaterThanCondition greaterThanCondition = new GreaterThanCondition("EstadoFisicoInicial.ValoracionSintomasEsenciales", 0);
        conditions.add(greaterThanCondition);
        NotContainsCondition notContainsCondition = new NotContainsCondition("EstadoFisicoInicial.SintomasEsenciales", "EdemaPulmonar");
        conditions.add(notContainsCondition);
        rule.setParsedConditions(conditions);

        List<BaseAction> actions1 = new ArrayList<>();
        AssignAction assignAction1 = new AssignAction("DiagnósticoPreliminar.TipoDeSintomas", "Escasos");
        actions1.add(assignAction1);

        rule.setParsedConditions(conditions);
        rule.setParsedActions(actions1);
        rules.add(rule);
    }

    private static void addRule8(List<Rule> rules) {
        Rule rule = new Rule();
        rule.setId(8L);

        List<BaseCondition> conditions = new ArrayList<>();
        LessThanCondition lessThanCondition = new LessThanCondition("EstadoFisicoInicial.ValoracionSintomasEsenciales", 1);
        conditions.add(lessThanCondition);
        rule.setParsedConditions(conditions);

        List<BaseAction> actions1 = new ArrayList<>();
        AssignAction assignAction1 = new AssignAction("DiagnósticoPreliminar.TipoDeSintomas", "No presenta");
        actions1.add(assignAction1);

        rule.setParsedConditions(conditions);
        rule.setParsedActions(actions1);
        rules.add(rule);
    }

}
