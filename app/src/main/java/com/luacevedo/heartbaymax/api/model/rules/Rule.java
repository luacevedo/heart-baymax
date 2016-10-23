package com.luacevedo.heartbaymax.api.model.rules;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.model.rules.actions.AddNumberAction;
import com.luacevedo.heartbaymax.model.rules.actions.AddToListAction;
import com.luacevedo.heartbaymax.model.rules.actions.AssignAction;
import com.luacevedo.heartbaymax.model.rules.actions.BaseAction;
import com.luacevedo.heartbaymax.model.rules.conditions.AffirmativeCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.BaseCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.ContainsCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.GreaterThanCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.LessThanCondition;
import com.luacevedo.heartbaymax.model.rules.conditions.NotContainsCondition;

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
                case Constants.Rule.Condition.AFFIRMATIVE:
                    parsedCondition = new AffirmativeCondition(condition.getAttribute());
                    break;
                case Constants.Rule.Condition.GREATER_THAN:
                    parsedCondition = new GreaterThanCondition(condition.getAttribute(), Integer.parseInt(condition.getValue()));
                    break;
                case Constants.Rule.Condition.LESS_THAN:
                    parsedCondition = new LessThanCondition(condition.getAttribute(), Integer.parseInt(condition.getValue()));
                    break;
                case Constants.Rule.Condition.CONTAINS:
                    parsedCondition = new ContainsCondition(condition.getAttribute(), condition.getValue());
                    break;
                case Constants.Rule.Condition.NOT_CONTAINS:
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
                case Constants.Rule.Action.ADD_NUMBER:
                    parsedAction = new AddNumberAction(action.getAttribute(), Double.parseDouble(action.getValue()));
                    break;
                case Constants.Rule.Action.ADD_TO_LIST:
                    parsedAction = new AddToListAction(action.getAttribute(), action.getValue());
                    break;
                case Constants.Rule.Action.ASSIGN:
                    parsedAction = new AssignAction(action.getAttribute(), action.getValue());
                    break;
            }
            if (parsedAction != null) {
                parsedActions.add(parsedAction);
            }
        }
    }

}
