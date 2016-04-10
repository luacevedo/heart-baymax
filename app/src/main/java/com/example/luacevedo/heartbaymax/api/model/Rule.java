package com.example.luacevedo.heartbaymax.api.model;

import java.util.List;

public class Rule {

    private Long id;
    private List<Condition> conditions;
    private List<Action> actions;
    private List<Long> rulesToExclude;
    private AttributeInput attributeInput;
}
