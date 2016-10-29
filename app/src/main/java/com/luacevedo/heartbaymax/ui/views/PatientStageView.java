package com.luacevedo.heartbaymax.ui.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.interfaces.OnPatientStageClick;

public class PatientStageView extends LinearLayout implements View.OnClickListener {

    private TextView textView;
    private ImageView image;
    private LinearLayout layout;
    private Constants.PatientStage stage;
    private boolean isCompleted;
    private OnPatientStageClick onClickListener;

    public PatientStageView(Context context) {
        super(context);
        inflate(getContext(), R.layout.view_patient_stage, this);
    }

    public PatientStageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_patient_stage, this);
    }

    public PatientStageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.view_patient_stage, this);
    }

    public void setupView(Constants.PatientStage stage, boolean isCompleted, OnPatientStageClick onClickListener) {
        this.stage = stage;
        this.isCompleted = isCompleted;
        this.onClickListener = onClickListener;
        textView = (TextView) findViewById(R.id.patient_stage_text);
        layout = (LinearLayout) findViewById(R.id.patient_stage_layout);
        layout.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.patient_stage_img);
        image.setImageResource(isCompleted ? R.drawable.ic_tick : R.drawable.ic_plus_red);

        setTextViewText(stage, isCompleted);
    }

    private void setTextViewText(Constants.PatientStage stage, boolean isCompleted) {
        switch (stage) {
            case INITIAL_STATE:
                textView.setText(isCompleted ? R.string.initial_situation : R.string.enter_initial_situation);
                break;
            case HEART_SITUATION:
                textView.setText(isCompleted ? R.string.heart_situation : R.string.enter_heart_situation);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        onClickListener.onPageStageClick(this.stage, this.isCompleted);
    }
}
