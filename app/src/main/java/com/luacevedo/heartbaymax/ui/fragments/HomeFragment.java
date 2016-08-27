package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.adapters.PatientsHomeAdapter;
import com.luacevedo.heartbaymax.api.model.MockInfo;
import com.luacevedo.heartbaymax.api.model.rules.Rule;
import com.luacevedo.heartbaymax.db.InternalDbHelper;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.interfaces.IOnPatientClicked;
import com.luacevedo.heartbaymax.model.patient.Patient;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeFragment extends BaseFragment implements View.OnClickListener, IOnPatientClicked {

    private View btnNewPatient;
    private RecyclerView patientsRecyclerView;
    private List<Rule> ruleList = new ArrayList<>();
    private List<Patient> patientsList = new ArrayList<>();
    private StaggeredGridLayoutManager layoutManager;
    private PatientsHomeAdapter patientsAdapter;
    private InternalDbHelper internalDbHelper = HeartBaymaxApplication.getApplication().getInternalDbHelper();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        patientsRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_patients);
        btnNewPatient = view.findViewById(R.id.new_patient_btn);
        btnNewPatient.setOnClickListener(this);
        patientsRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_patients);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpPatientsList();
    }

    private void setUpPatientsList() {
        layoutManager = new StaggeredGridLayoutManager(Constants.GridLayout.SINGLE_COLUMN, StaggeredGridLayoutManager.VERTICAL);
        patientsRecyclerView.setLayoutManager(layoutManager);
        patientsList = internalDbHelper.getPatients();
        setPatientsAdapter();
    }

    private void setPatientsAdapter() {
        patientsAdapter = new PatientsHomeAdapter(this);
        ArrayList<Patient> emptyArray = new ArrayList<>();
        patientsAdapter.setListToDisplay(emptyArray);
        patientsAdapter.setListToDisplay(patientsList);
        patientsRecyclerView.setAdapter(patientsAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.new_patient_btn) {
            Patient patient = MockInfo.createPatient();
            startActivity(IntentFactory.getPreliminaryDiagnosisActivityIntent(patient));
        }
    }

    @NonNull
    private Callback<List<Rule>> generateRulesCallback() {
        return new Callback<List<Rule>>() {
            @Override
            public void success(List<Rule> rules, Response response) {
                ruleList = rules;
//                RulesHelper.executeRules(ruleList, patient);
//                printPatient();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("LULI", error.toString());

            }
        };
    }

    @Override
    public void patientClicked(Patient patient, int position, View view) {
        startActivity(IntentFactory.getPatientPageActivityIntent(patient));
    }
}
