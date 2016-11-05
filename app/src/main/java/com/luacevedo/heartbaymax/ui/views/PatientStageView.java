package com.luacevedo.heartbaymax.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.ResourcesHelper;
import com.luacevedo.heartbaymax.interfaces.OnPatientStageClick;

public class PatientStageView extends LinearLayout implements View.OnClickListener {

    private TextView textView;
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

    public void setupView(Constants.PatientStage stage, boolean isCompleted, boolean isEnabled, OnPatientStageClick onClickListener) {
        this.stage = stage;
        this.isCompleted = isCompleted;
        this.onClickListener = onClickListener;
        textView = (TextView) findViewById(R.id.patient_stage_text);
        textView.setTextColor(isEnabled ? ResourcesHelper.getColor(R.color.black) : ResourcesHelper.getColor(R.color.disabled));
        LinearLayout layout = (LinearLayout) findViewById(R.id.patient_stage_layout);
        layout.setOnClickListener(this);
        ImageView image = (ImageView) findViewById(R.id.patient_stage_img);
        image.setImageResource(isCompleted ? R.drawable.ic_tick : isEnabled ? R.drawable.ic_plus_red : R.drawable.ic_plus_gray);
        setTextViewText(stage, isCompleted);
    }

    private void setTextViewText(Constants.PatientStage stage, boolean isCompleted) {
        switch (stage) {
            case INITIAL_STATE:
                textView.setText(R.string.initial_situation);
                break;
            case PRELIMINARY_DIAGNOSIS:
                textView.setText(R.string.preliminary_diagnosis);
                break;
            case IMMEDIATE_TREATMENT:
                textView.setText(R.string.immediate_treatment);
                break;
            case ECG:
                textView.setText(isCompleted ? R.string.ecg : R.string.enter_ecg);
                break;
            case RX:
                textView.setText(isCompleted ? R.string.rx : R.string.enter_rx);
                break;
            case LAB_ANALYSIS:
                textView.setText(isCompleted ? R.string.lab_analysis : R.string.enter_lab_analysis);
                break;
            case FINAL_DIAGNOSIS:
                textView.setText(isCompleted ? R.string.final_diagnosis : R.string.enter_final_diagnosis);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        onClickListener.onPageStageClick(this.stage, this.isCompleted);
    }
}
