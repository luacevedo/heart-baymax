package com.luacevedo.heartbaymax.helpers;

import android.util.Log;

import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.model.rules.actions.BaseAction;
import com.luacevedo.heartbaymax.model.rules.conditions.BaseCondition;

import java.util.List;

public class RulesHelper {

    public static void executeRules(List<Rule> rules, Patient patient) {
        int i = 0;
        Log.e("LULI", "ejecuto las reglas");
        while (i < rules.size()) {
            Log.e("LULI", "Rule " + rules.get(i).getId());
            boolean conditionsFulfilled = checkConditions(rules.get(i).getParsedConditions(), patient);
            if (conditionsFulfilled) {
                executeActions(rules.get(i).getParsedActions(), patient);
                excludeRules(rules, rules.get(i).getRulesToExclude());
            }
            i++;
        }
    }

    public static boolean checkConditions(List<BaseCondition> conditions, Patient patient) {
        boolean conditionsFulfilled = true;
        for (BaseCondition condition : conditions) {
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

    public static void executeActions(List<BaseAction> actions, Patient patient) {
        Log.e("LULI", "SI conditionsFulfilled... ejecuto las acciones");
        for (BaseAction action : actions) {
            Log.e("LULI", "Attribute: " + action.getAttributeRoot());
            Log.e("LULI", "Action: " + action);
            PatientAttribute attributeToExecuteAction = patient.getAttributesMap().get(action.getAttributeRoot());
            action.execute(attributeToExecuteAction);
        }
    }

    public static void excludeRules(List<Rule> rules, List<Long> rulesToExclude) {
        for (Long id : rulesToExclude) {
            Log.e("LULI", "Excluyo regla " + id);
            Rule r = new Rule();
            r.setId(id);
            rules.remove(r);
        }
    }

}
