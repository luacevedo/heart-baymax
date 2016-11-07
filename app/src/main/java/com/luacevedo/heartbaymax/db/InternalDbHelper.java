package com.luacevedo.heartbaymax.db;

import android.content.Context;

import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.api.model.fields.StepInputFields;
import com.luacevedo.heartbaymax.db.internal.StepInputFieldsDbHelper;
import com.luacevedo.heartbaymax.db.internal.PatientsDbHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;

import java.util.List;

public class InternalDbHelper {

    private PatientsDbHelper patientsDb;
    private StepInputFieldsDbHelper stepInputFieldsDb;

    public InternalDbHelper(Context context) {
        this.patientsDb = new PatientsDbHelper(context);
        this.stepInputFieldsDb = new StepInputFieldsDbHelper(context);
    }

    public void savePatient(Patient patient) {
        this.patientsDb.insert(String.valueOf(patient.getId()), patient);
    }

    public List<Patient> getPatients() {
//        List<Patient> list = new ArrayList<>();
//        Patient x = this.patientsDb.getPatientFromTable("1", Patient.class);
//        if (x != null) {
//            list.add(x);
//        }
//        return list;

        return this.patientsDb.getAllDataFromTable(Patient.class);

    }

    public void saveStepInputFields(List<StepInputFields> inputFields) {
        this.stepInputFieldsDb.clearDb();
        for (StepInputFields field : inputFields) {
            this.stepInputFieldsDb.insert(String.format("%s-%s", field.getStage(), field.getStep()), field);
        }
    }

    public List<StepInputFields> getStepInputFields() {
        return this.stepInputFieldsDb.getAllDataFromTable(StepInputFields.class);

    }


}
