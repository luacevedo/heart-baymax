package com.luacevedo.heartbaymax.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.interfaces.IOnPatientClicked;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.ui.views.PatientHomeViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PatientsHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final IOnPatientClicked listenerOnPatientClicked;
    private List<Patient> patientsListToDisplay = new ArrayList<>();

    public PatientsHomeAdapter(IOnPatientClicked listenerOnPatientClicked) {
        this.listenerOnPatientClicked = listenerOnPatientClicked;
    }

    public void setListToDisplay(List<Patient> newListToDisplay) {
        this.patientsListToDisplay = newListToDisplay;
        notifyDataChanged();
    }

    private void notifyDataChanged() {
        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        };
        handler.post(r);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.viewholder_home_patient, viewGroup, false);
        RecyclerView.ViewHolder viewHolder = new PatientHomeViewHolder(view, listenerOnPatientClicked);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Patient patient = patientsListToDisplay.get(position);
        PatientHomeViewHolder patientHomeViewHolder = (PatientHomeViewHolder) viewHolder;
        patientHomeViewHolder.setUpData(patient, position);
    }


    @Override
    public int getItemCount() {
        return this.patientsListToDisplay.size();
    }

}