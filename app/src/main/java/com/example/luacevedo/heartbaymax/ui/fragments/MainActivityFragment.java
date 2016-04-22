package com.example.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luacevedo.heartbaymax.R;
import com.example.luacevedo.heartbaymax.api.model.Attribute;
import com.example.luacevedo.heartbaymax.api.model.Rule;
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

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;

    }

    List<Rule> rules = new ArrayList<>();
    Patient patient = new Patient();


    @Override
    public void onStart() {
        super.onStart();
        Log.e("LULI", "creo los datos");
        prepareMockInfo();

        Log.e("LULI", "ejecuto las reglas");
        for (Rule rule : rules) {
            Log.e("LULI", "Rule " + rule.getId());
            boolean conditionsFulfilled = checkConditions(rule);
            if (conditionsFulfilled) {
                executeActions(rule);
            }
        }
        printPatient();
    }

    private void executeActions(Rule rule) {
        Log.e("LULI", "SI conditionsFulfilled... ejecuto las acciones");
        for (BaseAction action : rule.getParsedActions()) {
            PatientAttribute attributeToExecuteAction = patient.getAttributesMap().get(action.getAttributeRoot());
            action.execute(attributeToExecuteAction);
        }
    }

    private void printPatient() {
        Log.e("LULI", "EL Pacienteeeee: ");
        for (String key : patient.getAttributesMap().keySet()) {
            PatientAttribute att = patient.getAttributesMap().get(key);
            Log.e("LULI", key + " = " + att.getValue());
        }
    }

    private boolean checkConditions(Rule rule) {
        boolean conditionsFulfilled = true;
        for (BaseCondition condition : rule.getParsedConditions()) {
            Log.e("LULI", "Por cada condicion:");
            PatientAttribute attributeToCheck = patient.getAttributesMap().get(condition.getAttributeRoot());
            Log.e("LULI", "Attribute: " + attributeToCheck.getAttribute().getRoot());
            if (!condition.validate(attributeToCheck)) {
                Log.e("LULI", "no cumplio con la condicion el attributo... NO reviso mas condiciones y NO se ejecutan las actiones");
                conditionsFulfilled = false;
                break;
            }
        }
        return conditionsFulfilled;
    }

    private void prepareMockInfo() {
        addEdemaPulmonarRule();
        addDisnea();
        addOrtopnea();
        addRule4();
        addRule5();
        addRule6();
        addRule7();
        addRule8();
        patient.setAttributesMap(getMockedAttributesMap());
    }

    private HashMap<String, PatientAttribute> getMockedAttributesMap() {
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

    private void addEdemaPulmonarRule() {
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

    private void addDisnea() {
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

    private void addOrtopnea() {
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

    private void addRule4() {
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

    private void addRule5() {
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

    private void addRule6() {
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

    private void addRule7() {
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

    private void addRule8() {
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
