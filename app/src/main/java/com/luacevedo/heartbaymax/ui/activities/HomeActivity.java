package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;

import com.luacevedo.heartbaymax.ui.fragments.HomeFragment;

public class HomeActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitialFragment(HomeFragment.newInstance());
    }

}
