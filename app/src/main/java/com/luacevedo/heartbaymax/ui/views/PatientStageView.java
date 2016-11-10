package com.luacevedo.heartbaymax.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.ResourcesHelper;
import com.luacevedo.heartbaymax.interfaces.OnPatientStageClick;

public class PatientStageView extends LinearLayout implements View.OnClickListener {

    private TextView textView;
    private Constants.PatientStage stage;
    private boolean isCompleted;
    private boolean isEnabled;
    private OnPatientStageClick onClickListener;
    private ImageView image;

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
        this.isEnabled = isEnabled;
        this.onClickListener = onClickListener;
        textView = (TextView) findViewById(R.id.patient_stage_text);
        textView.setTextColor(isEnabled ? ResourcesHelper.getColor(R.color.black) : ResourcesHelper.getColor(R.color.disabled));
        LinearLayout layout = (LinearLayout) findViewById(R.id.patient_stage_layout);
        layout.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.patient_stage_img);
        setTextViewText(stage, isCompleted, isEnabled);
    }

    private void setTextViewText(Constants.PatientStage stage, boolean isCompleted, boolean isEnabled) {
        switch (stage) {
            case INITIAL_STATE:
                textView.setText(R.string.see_initial_situation);
                image.setImageResource(R.drawable.ic_heart_colors);
                break;
            case PRELIMINARY_DIAGNOSIS:
                textView.setText(R.string.preliminary_diagnosis);
                image.setImageResource(R.drawable.ic_book);
                break;
            case IMMEDIATE_TREATMENT:
                textView.setText(R.string.immediate_treatment);
                image.setImageResource(R.drawable.ic_syringe);
                break;
            case ECG:
                textView.setText(isCompleted ? R.string.ecg : R.string.enter_ecg);
                image.setImageResource(R.drawable.ic_heart_pulse);
                break;
            case RX:
                textView.setText(isCompleted ? R.string.rx : R.string.enter_rx);
                image.setImageResource(R.drawable.ic_lungs);
                break;
            case LAB_ANALYSIS:
                textView.setText(isCompleted ? R.string.lab_analysis : R.string.enter_lab_analysis);
                image.setImageResource(R.drawable.ic_lab_analysis);
                break;
            case FINAL_DIAGNOSIS:
                textView.setText(isCompleted ? R.string.final_diagnosis : R.string.get_final_diagnosis);
                image.setImageResource(isEnabled ? R.drawable.ic_treatment_plan : R.drawable.ic_treatment_plan_gray);
                break;
            case FINAL_TREATMENT:
                textView.setText(R.string.final_treatment);
                image.setImageResource(isEnabled ? R.drawable.ic_pill : R.drawable.ic_pill_gray);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (this.isEnabled) {
            onClickListener.onPageStageClick(this.stage, this.isCompleted);
        } else {
            Toast.makeText(getContext(), R.string.final_diagnosis_disabled, Toast.LENGTH_SHORT).show();
        }
    }
}
