package com.luacevedo.heartbaymax.utils;

import android.util.Log;

import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.model.rules.actions.BaseAction;
import com.luacevedo.heartbaymax.model.rules.conditions.BaseCondition;

import java.util.List;

public class RulesExecutor {

    public static void executeRules(List<Rule> rules, Patient patient) {
        int i = 0;
        Log.e("LULI", "ejecuto las reglas");
        while (i < rules.size()) {
            Log.e("LULI", "Rule " + rules.get(i).getRuleId());
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
            if (rule.getRuleId() == 23) {
                Log.e("sjs", "djkdfjd");
            }
            if (rule.getRuleId() == 57) {
                Log.e("sjs", "djkdfjd");
            }
            if (!condition.validate(attributeToCheck)) {
                Log.e("LULI", "no cumplio con la condicion el attributo...");
                conditionsFulfilled = false;
                break;
            }
        }
        return conditionsFulfilled;
    }

    private static void excludeRules(List<Rule> rules, List<Integer> rulesToExclude) {
        for (Integer id : rulesToExclude) {
            Log.e("LULI", "Excluyo regla " + id);
            Rule r = new Rule();
            r.setRuleId(id);
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
