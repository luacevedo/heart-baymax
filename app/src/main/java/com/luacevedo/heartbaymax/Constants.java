package com.luacevedo.heartbaymax;

public class Constants {

    public static final String EMPTY_STRING = "";
    public static final String UTF_8 = "UTF-8";
    public static String CACHE = "Cache";

    public static class Rule {

        public static class Condition {
            public static final String AFFIRMATIVE = "affirmative";
            public static final String GREATER_THAN = "greaterThan";
            public static final String GREATER_OR_EQUAL_THAN = "greaterOrEqualThan";
            public static final String LESS_THAN = "lessThan";
            public static final String LESS_OR_EQUAL_THAN = "lessOrEqualThan";
            public static final String CONTAINS = "contains";
            public static final String NOT_CONTAINS = "notContains";
            public static final String EQUALS = "equals";
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
        public static final long ONE_HOUR = ONE_MINUTE * 60;
        public static final long ONE_DAY = ONE_HOUR * 24;
        public static final long ONE_WEEK = ONE_DAY * 7;
    }

    public class BundleKey {
        public static final String INPUT_FIELDS = "inputFields";
        public static final String STEP_INPUT_ATTRIBUTES = "stepInputAttributes";
        public static final String PATIENT = "patient";
        public static final String RULES = "rules";
        public static final String STAGE = "stage";
        public static final String IS_FROM_INITIAL_STATE = "isFromInitialState";
    }

    public static class Attribute {
        public static class Type {
            public static final String BOOLEAN = "boolean";
            public static final String NUMBER = "number";
            public static final String LIST = "list";
            public static final String STRING = "string";
        }

        public class EssentialSymptoms {
            public static final String DYSPNOEA = "EssentialSymptoms.Dyspnoea";
        }

        public class ECG {
            public static final String HEART_RATE = "ECG.HeartRate";
            public static final String ISCHEMIA = "ECG.Ischemia";
            public static final String ARRHYTHMIA = "ECG.Arrhythmia";
            public static final String ATRIAL_FIBRILLATION = "ECG.AtrialFibrillation";
        }

        public class Rx {
            public static final String KERLEY_LINES = "Rx.KerleyLines";
            public static final String FLOW_REDISTRIBUTION = "Rx.FlowRedistribution";
            public static final String PLEURAL_EFFUSION = "Rx.PleuralEffusion";
            public static final String CARDIOMEGALY = "Rx.Cardiomegaly";
        }

        public class LabAnalysis {
            public static final String SODIUM = "LabAnalysis.Sodium";
            public static final String POTASSIUM = "LabAnalysis.Potassium";
            public static final String UREMIA = "LabAnalysis.Uremia";
            public static final String CREATININE = "LabAnalysis.Creatinine";
            public static final String RED_BLOOD_CELLS = "LabAnalysis.RedBloodCells";
            public static final String WHITE_BLOOD_CELLS = "LabAnalysis.WhiteBloodCells";
        }

        public class FinalDiagnosis {
            public static final String IC_CLASS = "FinalDiagnosis.ICClass";
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
            public static final String NUMBER = "number";
            public static final String SELECT = "select";
        }

        public class Value {
            public static final String TRUE = "1";
            public static final String FALSE = "0";
        }
    }

    public static class GridLayout {
        public static final int SINGLE_COLUMN = 1;
    }

    public class Patient {
        public static final String FEMALE = "F";
        public static final String MALE = "M";

        public class Root {
            public static final String ESSENTIAL_SYMPTOMS = "EssentialSymptoms";
            public static final String SECONDARY_SYMPTOMS = "SecondarySymptoms";
            public static final String PRELIMINARY_DIAGNOSIS = "PreliminaryDiagnosis";
            public static final String IMMEDIATE_TREATMENT = "ImmediateTreatment";
            public static final String IMMEDIATE_DIURETIC_TREATMENT = "ImmediateDiureticTreatment";
            public static final String IMMEDIATE_VASODILATOR_TREATMENT = "ImmediateVasodilatorTreatment";
            public static final String ECG = "ECG";
            public static final String RX = "Rx";
            public static final String LAB_ANALYSIS = "LabAnalysis";
            public static final String HEART_SITUATION = "HeartSituation";
            public static final String FINAL_DIAGNOSIS = "FinalDiagnosis";
            public static final String FINAL_TREATMENT = "FinalTreatment";
            public static final String FINAL_DIURETIC_TREATMENT = "FinalDiureticTreatment";
            public static final String FINAL_VASODILATOR_TREATMENT = "FinalVasodilatorTreatment";
        }

    }

    public enum PatientStage {
        INITIAL_STATE, PRELIMINARY_DIAGNOSIS, IMMEDIATE_TREATMENT, RX, LAB_ANALYSIS,
        FINAL_DIAGNOSIS, ECG, FINAL_TREATMENT
    }

}
