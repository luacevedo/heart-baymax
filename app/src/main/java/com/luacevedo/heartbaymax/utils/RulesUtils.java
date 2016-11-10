package com.luacevedo.heartbaymax.utils;

import android.util.Log;

import com.luacevedo.heartbaymax.api.model.rules.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RulesUtils {

    public static final int STAGE_1 = 1;
    public static final int STAGE_2 = 2;

    private static void orderRules(List<Rule> list) {
        Comparator<Rule> comparator = new Comparator<Rule>() {
            @Override
            public int compare(Rule c1, Rule c2) {
                return c1.getRuleId() - c2.getRuleId();
            }
        };

        Collections.sort(list, comparator);
    }

    public static List<Rule> getRulesForStage(List<Rule> rules, int stage) {
        List<Rule> stageRules = new ArrayList<>();
        for (Rule rule : rules) {
            if (rule.getStage() == stage) {
                stageRules.add(rule);
            }
        }
        orderRules(stageRules);
        return stageRules;
    }
}
