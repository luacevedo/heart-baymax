package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.helpers.LogInternal;
import com.luacevedo.heartbaymax.interfaces.OnPatientStageClick;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.activities.PatientPageActivity;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;
import com.luacevedo.heartbaymax.ui.views.PatientStageView;

import java.util.ArrayList;
import java.util.List;

public class PatientPageFragment extends BaseFragment implements OnPatientStageClick {

    private PatientStageView heartSituationView;
    private PatientStageView initialSituationView;
    private TextView patientName;
    private PatientPageActivity activity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (PatientPageActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_page, container, false);
        activity = (PatientPageActivity) getActivity();
        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        patientName = (TextView) view.findViewById(R.id.patient_page_name);
        patientName.setText(activity.getPatient().getName());
        initialSituationView = (PatientStageView) view.findViewById(R.id.initial_state_stage);
        initialSituationView.setupView(Constants.PatientStage.INITIAL_STATE, true, this);
        heartSituationView = (PatientStageView) view.findViewById(R.id.heart_situation_stage);
        heartSituationView.setupView(Constants.PatientStage.HEART_SITUATION, true, this);
    }

    @Override
    public void onPageStageClick(Constants.PatientStage stage, boolean isCompleted) {
        switch (stage) {
            case INITIAL_STATE:
                slideNextFragment(new PatientPageDataFragment());
                break;
            case HEART_SITUATION:
                Log.e("LULI","HEART SITUATION");
                break;
        }
    }
}
