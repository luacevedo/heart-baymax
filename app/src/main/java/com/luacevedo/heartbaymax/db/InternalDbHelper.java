package com.luacevedo.heartbaymax.db;

import com.luacevedo.heartbaymax.db.internal.PatientsDbHelper;
import com.luacevedo.heartbaymax.model.patient.Patient;

public class InternalDbHelper {

    private PatientsDbHelper patientsDb;

    private void savePatient(Patient patient) {
        patientsDb.insert(String.valueOf(patient.getId()), patient);
    }


}
