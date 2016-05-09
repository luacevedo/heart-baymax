package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.api.model.fields.InputField;
import com.luacevedo.heartbaymax.ui.activities.NewPatientActivity;

import java.util.List;

public class NewPatientActivityFragment extends BaseFragment {

    private LinearLayout formLayout;
    private ScrollView scrollview;
    private List<InputField> inputFields;
    private NewPatientActivity newPatientActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newPatientActivity = (NewPatientActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_patient, container, false);
        formLayout = (LinearLayout) view.findViewById(R.id.input_attributes_form_layout);
        scrollview = (ScrollView) view.findViewById(R.id.input_attributes_scrollview);
        return view;
    }
}
