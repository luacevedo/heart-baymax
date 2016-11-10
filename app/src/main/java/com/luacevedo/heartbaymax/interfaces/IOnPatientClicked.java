package com.luacevedo.heartbaymax.interfaces;

import android.view.View;

import com.luacevedo.heartbaymax.model.patient.Patient;

public interface IOnPatientClicked {
    void patientClicked(Patient patient, int position, View view);
}
