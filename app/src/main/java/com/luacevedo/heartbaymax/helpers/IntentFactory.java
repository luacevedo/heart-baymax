package com.luacevedo.heartbaymax.helpers;

import android.content.Intent;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.ui.activities.HomeActivity;
import com.luacevedo.heartbaymax.ui.activities.PatientPageActivity;
import com.luacevedo.heartbaymax.ui.activities.PreliminaryDiagnosisActivity;

public class IntentFactory {

    public static Intent getPreliminaryDiagnosisActivityIntent(Patient patient) {
        Intent intent = new Intent(HeartBaymaxApplication.getApplication(), PreliminaryDiagnosisActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        BundleHelper.putJsonBundle(intent, Constants.BundleKey.PATIENT, patient);
        return intent;
    }

    public static Intent getPatientPageActivityIntent(Patient patient) {
        Intent intent = new Intent(HeartBaymaxApplication.getApplication(), PatientPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        BundleHelper.putJsonBundle(intent, Constants.BundleKey.PATIENT, patient);
        return intent;
    }

    public static Intent getHomeActivityIntent() {
        Intent intent = new Intent(HeartBaymaxApplication.getApplication(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }
}
