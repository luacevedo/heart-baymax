package com.luacevedo.heartbaymax.utils;

import android.util.Log;

import com.luacevedo.heartbaymax.api.model.rules.Rule;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RulesUtils {

    public static void orderRules(List<Rule> list) {
        Comparator<Rule> comparator = new Comparator<Rule>() {
            @Override
            public int compare(Rule c1, Rule c2) {
                return c1.getRuleId() - c2.getRuleId();
            }
        };

        Collections.sort(list, comparator);
        Log.e("LULI", "la listaaaaa ordenadaaa " + String.valueOf(list.get(0).getRuleId()));
    }

}
