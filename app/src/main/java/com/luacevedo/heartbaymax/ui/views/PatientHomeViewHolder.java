package com.luacevedo.heartbaymax.ui.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.ResourcesHelper;
import com.luacevedo.heartbaymax.interfaces.IOnPatientClicked;
import com.luacevedo.heartbaymax.model.patient.Patient;

public class PatientHomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private IOnPatientClicked listenerPatientClicked;
    private ImageView image;
    private TextView txtTitle;
    private TextView txtAge;
    private Patient patient;
    private int position;

    public PatientHomeViewHolder(View itemView, IOnPatientClicked listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.listenerPatientClicked = listener;
        txtTitle = (TextView) itemView.findViewById(R.id.home_patient_title);
        txtAge = (TextView) itemView.findViewById(R.id.home_patient_age);
        image = (ImageView) itemView.findViewById(R.id.home_patient_image);
    }

    public void setUpData(Patient patient, int position) {
        this.patient = patient;
        this.position = position;
        String name = ResourcesHelper.getString(R.string.patient_title_home).replace("{patient}", patient.getName());
        this.txtTitle.setText(name);
        this.txtAge.setText(ResourcesHelper.getString(R.string.patient_age_home).replace("{age}", patient.getAge()));
        this.image.setImageResource(patient.getGender().equals(Constants.Patient.FEMALE) ? R.drawable.ic_patient_woman : R.drawable.ic_patient_man);
    }

    @Override
    public void onClick(View view) {
        listenerPatientClicked.patientClicked(this.patient, position, view);
    }

}

