package com.luacevedo.heartbaymax.ui.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.interfaces.IOnPatientClicked;
import com.luacevedo.heartbaymax.model.patient.Patient;

public class PatientHomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final IOnPatientClicked listenerPatientClicked;
    private TextView txtTitle;
    private Patient patient;
    private int position;

    public PatientHomeViewHolder(View itemView, IOnPatientClicked listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.listenerPatientClicked = listener;
        txtTitle = (TextView) itemView.findViewById(R.id.home_patient_title);
    }

    public void setUpData(Patient patient, int position) {
        this.patient = patient;
        this.position = position;
        this.txtTitle.setText(patient.getId());
    }

    @Override
    public void onClick(View view) {
        listenerPatientClicked.patientClicked(this.patient, position, view);
    }

}

