package com.example.luacevedo.heartbaymax.helpers;

import android.util.Log;

import com.example.luacevedo.heartbaymax.api.model.Rule;
import com.example.luacevedo.heartbaymax.model.patient.Patient;
import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.example.luacevedo.heartbaymax.model.rules.actions.BaseAction;
import com.example.luacevedo.heartbaymax.model.rules.conditions.BaseCondition;

import java.util.List;

public class RulesHelper {

    public static void executeRules(List<Rule> rules, Patient patient) {
        int i = 0;
        Log.e("LULI", "ejecuto las reglas");
        while (i < rules.size()) {
            Log.e("LULI", "Rule " + rules.get(i).getId());
            boolean conditionsFulfilled = checkConditions(rules.get(i), patient);
            if (conditionsFulfilled) {
                executeActions(rules.get(i), patient);
                excludeRules(rules, rules.get(i).getRulesToExclude());
            }
            i++;
        }
    }

    private static boolean checkConditions(Rule rule, Patient patient) {
        boolean conditionsFulfilled = true;
        for (BaseCondition condition : rule.getParsedConditions()) {
            Log.e("LULI", "Por cada condicion:");
            PatientAttribute attributeToCheck = patient.getAttributesMap().get(condition.getAttributeRoot());
            Log.e("LULI", "Attribute: " + condition.getAttributeRoot());
            Log.e("LULI", "Condition: " + condition);
            if (!condition.validate(attributeToCheck)) {
                Log.e("LULI", "no cumplio con la condicion el attributo...");
                conditionsFulfilled = false;
                break;
            }
        }
        return conditionsFulfilled;
    }

    private static void excludeRules(List<Rule> rules, List<Long> rulesToExclude) {
        for (Long id : rulesToExclude) {
            Log.e("LULI", "Excluyo regla " + id);
            Rule r = new Rule();
            r.setId(id);
            rules.remove(r);
        }
    }

    private static void executeActions(Rule rule, Patient patient) {
        Log.e("LULI", "SI conditionsFulfilled... ejecuto las acciones");
        for (BaseAction action : rule.getParsedActions()) {
            Log.e("LULI", "Attribute: " + action.getAttributeRoot());
            Log.e("LULI", "Action: " + action);
            PatientAttribute attributeToExecuteAction = patient.getAttributesMap().get(action.getAttributeRoot());
            action.execute(attributeToExecuteAction);
        }
    }

}
