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

import java.util.ArrayList;
import java.util.List;

public class Rule {

    private Long id;
    private List<Condition> conditions;
    private List<Action> actions;
    private List<Long> rulesToExclude = new ArrayList<>();

    private List<BaseCondition> parsedConditions = new ArrayList<>();
    private List<BaseAction> parsedActions = new ArrayList<>();

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
        if (parsedConditions.isEmpty()) {
            populateTransientConditions();
        }
        return parsedConditions;
    }

    public void setParsedConditions(List<BaseCondition> parsedConditions) {
        this.parsedConditions = parsedConditions;
    }

    public List<BaseAction> getParsedActions() {
        if (parsedActions.isEmpty()) {
            populateTransientActions();
        }
        return parsedActions;
    }

    public void setParsedActions(List<BaseAction> parsedActions) {
        this.parsedActions = parsedActions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        return id.equals(rule.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void populateTransientConditions() {
        for (Condition condition : conditions) {
            BaseCondition<?> parsedCondition = null;
            switch (condition.getType()) {
                case "affirmative":
                    parsedCondition = new AffirmativeCondition(condition.getAttribute());
                    break;
                case "greaterThan":
                    parsedCondition = new GreaterThanCondition(condition.getAttribute(), Integer.parseInt(condition.getValue()));
                    break;
                case "lessThan":
                    parsedCondition = new LessThanCondition(condition.getAttribute(), Integer.parseInt(condition.getValue()));
                    break;
                case "contains":
                    parsedCondition = new ContainsCondition(condition.getAttribute(), condition.getValue());
                    break;
                case "notContains":
                    parsedCondition = new NotContainsCondition(condition.getAttribute(), condition.getValue());
                    break;
            }
            if (parsedCondition != null) {
                parsedConditions.add(parsedCondition);
            }
        }
    }

    public void populateTransientActions() {
        for (Action action : actions) {
            BaseAction<?> parsedAction = null;
            switch (action.getAFunction()) {
                case "addNumber":
                    parsedAction = new AddNumberAction(action.getAttribute(), Integer.parseInt(action.getValue()));
                    break;
                case "addToList":
                    parsedAction = new AddToListAction(action.getAttribute(), action.getValue());
                    break;
                case "assign":
                    parsedAction = new AssignAction(action.getAttribute(), action.getValue());
                    break;
            }
            if (parsedAction != null) {
                parsedActions.add(parsedAction);
            }
        }
    }

}
