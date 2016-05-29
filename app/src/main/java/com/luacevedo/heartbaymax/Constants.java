package com.luacevedo.heartbaymax;

public class Constants {

    public static final String EMPTY_STRING = "";
    public static final String UTF_8 = "UTF-8";
    public static String CACHE = "Cache";

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

    public class Time {
        public static final long ONE_MINUTE = 60;
        public static final long TEN_MINUTES = ONE_MINUTE * 10;
    }

    public class BundleKey {
        public static final String INPUT_FIELDS = "inputFields";
        public static final String STEP_INPUT_ATTRIBUTES = "stepInputAttributes";
        public static final String PATIENT = "patient";
    }

    public static class Attribute {
        public static class Type {
            public static final String BOOLEAN = "boolean";
            public static final String INTEGER = "integer";
            public static final String LIST = "list";
            public static final String STRING = "string";
        }
    }

    public static class InputField {
        public static class FieldType {
            public static final String COMBOBOX = "combobox";
            public static final String TEXT = "text";
        }

        public static class DataType {
            public static final String BOOLEAN = "boolean";
            public static final String STRING = "string";
            public static final String INTEGER = "integer";
        }

        public class Value {
            public static final String TRUE = "1";
            public static final String FALSE = "0";
        }
    }
}
