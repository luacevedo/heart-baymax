package com.luacevedo.heartbaymax.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.api.MochiApi;
import com.luacevedo.heartbaymax.ui.activities.BaseFragmentActivity;

public class BaseFragment extends Fragment {

    protected MochiApi mochiApi;
    private BaseFragmentActivity navigationActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mochiApi = HeartBaymaxApplication.getApplication().getMochiApi();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.navigationActivity = (BaseFragmentActivity) getActivity();
    }

    protected void slideNextFragment(Fragment fragment) {
        navigationActivity.slideNextFragmentFromRight(fragment);
    }

    protected void slidePreviousFragment() {
        navigationActivity.slidePreviousFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
