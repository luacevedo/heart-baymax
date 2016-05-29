package com.luacevedo.heartbaymax.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.luacevedo.heartbaymax.HeartBaymaxApplication;
import com.luacevedo.heartbaymax.api.HeartBaymaxApi;
import com.luacevedo.heartbaymax.ui.activities.BaseFragmentActivity;

public class BaseFragment extends Fragment {

    protected HeartBaymaxApi heartBaymaxApi;
    private BaseFragmentActivity navigationActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.heartBaymaxApi = HeartBaymaxApplication.getApplication().getHeartBaymaxApi();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.navigationActivity = (BaseFragmentActivity) getActivity();
    }

    protected void slideNextFragment(Fragment fragment) {
        navigationActivity.slideNextFragmentFromRight(fragment);
    }


}
