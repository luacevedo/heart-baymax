package com.luacevedo.heartbaymax.ui.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toolbar;

import com.luacevedo.heartbaymax.ui.fragments.HomeFragment;

public class HomeActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitialFragment(HomeFragment.newInstance());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setActionBar(Toolbar toolbar) {
        toolbar.setTitle("hola flopy");
        super.setActionBar(toolbar);
    }
}
