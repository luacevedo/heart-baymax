package com.example.luacevedo.heartbaymax.api.model;

import com.example.luacevedo.heartbaymax.model.actions.BaseAction;
import com.example.luacevedo.heartbaymax.model.conditions.BaseCondition;

import java.util.List;

public class Rule {

    private Long id;
    private List<Condition> conditions;
    private List<Action> actions;
    private List<Long> rulesToExclude;

    private List<BaseCondition> parsedConditions;
    private List<BaseAction> parsedActions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Long> getRulesToExclude() {
        return rulesToExclude;
    }

    public void setRulesToExclude(List<Long> rulesToExclude) {
        this.rulesToExclude = rulesToExclude;
    }

    public List<BaseCondition> getParsedConditions() {
        return parsedConditions;
    }

    public void setParsedConditions(List<BaseCondition> parsedConditions) {
        this.parsedConditions = parsedConditions;
    }

    public List<BaseAction> getParsedActions() {
        return parsedActions;
    }

    public void setParsedActions(List<BaseAction> parsedActions) {
        this.parsedActions = parsedActions;
    }

}
