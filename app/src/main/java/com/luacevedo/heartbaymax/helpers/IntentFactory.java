package com.luacevedo.heartbaymax.helpers;

import android.content.Intent;

import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.ui.activities.NewPatientActivity;
import com.luacevedo.heartbaymax.ui.activities.PreliminaryDiagnosisActivity;

public class IntentFactory {

    public static Intent getNewPatientActivityIntent() {
        Intent intent = new Intent(HeartBaymaxApplication.getApplication(), NewPatientActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

    public static Intent getPreliminaryDiagnosisActivityIntent() {
        Intent intent = new Intent(HeartBaymaxApplication.getApplication(), PreliminaryDiagnosisActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

}
