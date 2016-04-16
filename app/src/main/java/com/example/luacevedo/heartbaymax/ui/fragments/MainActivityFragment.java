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
import com.example.luacevedo.heartbaymax.model.actions.AssignAction;
import com.example.luacevedo.heartbaymax.model.actions.BaseAction;
import com.example.luacevedo.heartbaymax.model.conditions.AffirmativeCondition;
import com.example.luacevedo.heartbaymax.model.conditions.BaseCondition;
import com.example.luacevedo.heartbaymax.model.conditions.GreaterThanCondition;
import com.example.luacevedo.heartbaymax.model.conditions.LessThanCondition;
import com.example.luacevedo.heartbaymax.model.patient.Patient;
import com.example.luacevedo.heartbaymax.model.patient.attributes.BooleanPatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.IntegerPatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.ListPatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.PatientAttribute;
import com.example.luacevedo.heartbaymax.model.patient.attributes.StringPatientAttribute;

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

            Log.e("LULI", "Por cada condicion:");
            boolean conditionsFulfilled = true;
            for (BaseCondition condition : rule.getParsedConditions()) {
                PatientAttribute attributeToCheck = patient.getAttributesMap().get(condition.getAttributeRoot());
                Log.e("LULI", "Attribute: " + attributeToCheck.getAttribute().getRoot());
                if (attributeToCheck.getAttribute().isInput()) {
                    Log.e("LULI", "Es input, lo tengo que pedir antes de validar");
                    Log.e("LULI", "Mockeo la respuesta");
                    attributeToCheck.setValue(true);
                } else {
                    Log.e("LULI", "No es input, ya lo valido");
                }
                if (!condition.validate(attributeToCheck)) {
                    Log.e("LULI", "no cumplio con la condicion el attributo... NO reviso mas condiciones y NO se ejecutan las actiones");
                    conditionsFulfilled = false;
                    break;
                } else {
                    Log.e("LULI", "SI se cumplio con la condicion el attributo... sigo con el resto de las condiciones ");

                }
            }

            if (conditionsFulfilled) {
                Log.e("LULI", "SI conditionsFulfilled... ejecuto las acciones");
                for (BaseAction action : rule.getParsedActions()) {
                    PatientAttribute attributeToExecuteAction = patient.getAttributesMap().get(action.getAttributeRoot());
                    action.execute(attributeToExecuteAction);
                }
            } else {
                Log.e("LULI", "NO conditionsFulfilled... ");

            }

        }

        Log.e("LULI", "EL Pacienteeeee: ");
        for (String key : patient.getAttributesMap().keySet()) {
            PatientAttribute att = patient.getAttributesMap().get(key);
            Log.e("LULI", key + " = " + att.getValue());

        }


    }

    private void prepareMockInfo() {
        addEdemaPulmonarRule();
        addRule2();
        addRule3();
        patient.setAttributesMap(getMockedAttributesMap());
    }

    private HashMap<String, PatientAttribute> getMockedAttributesMap() {
        HashMap<String, PatientAttribute> map = new HashMap<>();
        Attribute edemaPulm = new Attribute(1L, "SintomasEsenciales.EdemaPulmonar", "boolean", true);
        PatientAttribute<Boolean> edemaPulmonar = new PatientAttribute<>(edemaPulm);
        map.put(edemaPulm.getRoot(), edemaPulmonar);

        Attribute valSE = new Attribute(2L, "EstadoFisicoInicial.ValoracionSintomasEsenciales", "integer", false);
        PatientAttribute<Integer> valoracionSE = new PatientAttribute<>(valSE);
        map.put(valSE.getRoot(), valoracionSE);

        Attribute sintomasEsenc = new Attribute(3L, "EstadoFisicoInicial.SintomasEsenciales", "list", false);
        PatientAttribute<List<String>> sintomasEsenciales = new PatientAttribute<>(sintomasEsenc);
        map.put(sintomasEsenc.getRoot(), sintomasEsenciales);

        Attribute tipoSint = new Attribute(4L, "DiagnósticoPreliminar.TipoDeSintomas", "string", false);
        PatientAttribute<String> tipoDeSintomas = new PatientAttribute<>(tipoSint);
        map.put(tipoSint.getRoot(), tipoDeSintomas);

        return map;
    }

    private void addEdemaPulmonarRule() {
        Rule rule = new Rule();
        rule.setId(1L);
        List<BaseCondition> conditions1 = new ArrayList<>();
        AffirmativeCondition affCondition1 = new AffirmativeCondition();
        affCondition1.setAttributeRoot("SintomasEsenciales.EdemaPulmonar");
        conditions1.add(affCondition1);
        rule.setParsedConditions(conditions1);

        List<BaseAction> actions1 = new ArrayList<>();
        AddNumberAction addNumberAction1 = new AddNumberAction();
        addNumberAction1.setAttributeRoot("EstadoFisicoInicial.ValoracionSintomasEsenciales");
        addNumberAction1.setValue(3);
        actions1.add(addNumberAction1);

        rule.setParsedConditions(conditions1);
        rule.setParsedActions(actions1);
        rules.add(rule);
    }

    private void addRule2() {
        Rule rule = new Rule();
        rule.setId(2L);

        List<BaseCondition> conditions1 = new ArrayList<>();
        LessThanCondition lessThanCondition1 = new LessThanCondition();
        lessThanCondition1.setAttributeRoot("EstadoFisicoInicial.ValoracionSintomasEsenciales");
        lessThanCondition1.setMax(1);
        conditions1.add(lessThanCondition1);
        rule.setParsedConditions(conditions1);

        List<BaseAction> actions1 = new ArrayList<>();
        AssignAction assignAction1 = new AssignAction();
        assignAction1.setAttributeRoot("DiagnósticoPreliminar.TipoDeSintomas");
        assignAction1.setValue("No presenta");
        actions1.add(assignAction1);

        rule.setParsedConditions(conditions1);
        rule.setParsedActions(actions1);
        rules.add(rule);
    }

    private void addRule3() {
        Rule rule = new Rule();
        rule.setId(3L);

        List<BaseCondition> conditions1 = new ArrayList<>();
        GreaterThanCondition greaterThanCondition1 = new GreaterThanCondition();
        greaterThanCondition1.setAttributeRoot("EstadoFisicoInicial.ValoracionSintomasEsenciales");
        greaterThanCondition1.setMin(1);
        conditions1.add(greaterThanCondition1);
        rule.setParsedConditions(conditions1);

        List<BaseAction> actions1 = new ArrayList<>();
        AssignAction assignAction1 = new AssignAction();
        assignAction1.setAttributeRoot("DiagnósticoPreliminar.TipoDeSintomas");
        assignAction1.setValue("SIIII prensenta");
        actions1.add(assignAction1);

        rule.setParsedConditions(conditions1);
        rule.setParsedActions(actions1);
        rules.add(rule);
    }

}
