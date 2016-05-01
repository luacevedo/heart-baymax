package com.luacevedo.heartbaymax;

public class Constants {

    public static class Rule {
        
        public static class Condition {
            public static final String AFFIRMATIVE = "affirmative";
            public static final String GREATER_THAN = "greaterThan";
            public static final String LESS_THAN = "lessThan";
            public static final String CONTAINS = "contains";
            public static final String NOT_CONTAINS = "notContains";
        }

        public static class Action {
            public static final String ADD_NUMBER = "addNumber";
            public static final String ADD_TO_LIST = "addToList";
            public static final String ASSIGN = "assign";
        }
    }

}
