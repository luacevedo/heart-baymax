package com.luacevedo.heartbaymax.ui.views.controls;

import android.content.Context;
import android.widget.LinearLayout;

import com.luacevedo.heartbaymax.interfaces.OnFocusReceivedListener;
import com.luacevedo.heartbaymax.interfaces.OnInputFieldValueChangedListener;

public abstract class InputFieldView extends LinearLayout {

    protected OnInputFieldValueChangedListener onValueChangedListener;
    protected OnFocusReceivedListener focusReceivedListener;

    public InputFieldView(Context context) {
        super(context);
    }

    public abstract void setError(String validationError);

    public abstract void clearError();

    public void setFocus() {

    }

    public void setFocusReceivedListener(OnFocusReceivedListener focusReceivedListener) {
        this.focusReceivedListener = focusReceivedListener;
    }

    public void setOnInputFieldValueChangedListener(OnInputFieldValueChangedListener onValueIdChanged) {
        this.onValueChangedListener = onValueIdChanged;
    }

}
