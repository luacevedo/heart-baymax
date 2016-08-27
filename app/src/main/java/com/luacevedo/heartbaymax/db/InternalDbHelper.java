package com.luacevedo.heartbaymax.db;

import android.content.Context;

import com.luacevedo.heartbaymax.db.internal.PatientsDbHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;

import java.util.ArrayList;
import java.util.List;

public class InternalDbHelper {

    private PatientsDbHelper patientsDb;

    public InternalDbHelper(Context context) {
        this.patientsDb = new PatientsDbHelper(context);
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



}
