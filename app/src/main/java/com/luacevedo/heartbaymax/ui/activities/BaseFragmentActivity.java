package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.luacevedo.heartbaymax.R;

public class BaseFragmentActivity extends AppCompatActivity {

    public enum Direction {
        RIGHT, LEFT, FADE, BOTTOM, NONE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout activityFragmentLayout = (FrameLayout) findViewById(R.id.activity_fragment_layout);
        if (activityFragmentLayout != null) {
            activityFragmentLayout.setVisibility(View.VISIBLE);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void setInitialFragment(Fragment fragment) {
        removeAllFragments();
        setFragment(fragment, Direction.NONE);
    }

    private void removeAllFragments() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate(getSupportFragmentManager().getBackStackEntryAt(0).getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void slideNextFragmentFromRight(Fragment fragment) {
        setFragment(fragment, Direction.RIGHT);
    }

    public void slideNextFragmentFromLeft(Fragment fragment) {
        setFragment(fragment, Direction.LEFT);
    }

    public void slidePreviousFragment() {
        getSupportFragmentManager().popBackStackImmediate();
    }

    public void showOverlayFragment(Fragment fragment) {
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            setFragmentAnimation(Direction.BOTTOM, transaction);

            transaction.replace(R.id.activity_overlay_fragment_layout, fragment, fragment.getClass().getName());
            transaction.addToBackStack(fragment.getClass().getName());
            transaction.commitAllowingStateLoss();

        } catch (Exception ex) {
            Log.e("LULI","Error while setting overlay fragment: " + ex.toString());
        }
    }

    private void setFragment(Fragment fragment, Direction direction) {
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            setFragmentAnimation(direction, transaction);

            transaction.replace(R.id.activity_fragment_layout, fragment, fragment.getClass().getName());
            transaction.addToBackStack(fragment.getClass().getName());
            transaction.commitAllowingStateLoss();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setFragmentAnimation(Direction direction, FragmentTransaction transaction) {
        switch (direction) {
            case RIGHT:
                transaction.setCustomAnimations(R.anim.animation_appears_from_right, R.anim.animation_disappears_to_left,
                        R.anim.animation_appears_from_left, R.anim.animation_disappears_to_right);
                break;
            case LEFT:
                transaction.setCustomAnimations(R.anim.animation_appears_from_left, R.anim.animation_disappears_to_right,
                        R.anim.animation_appears_from_right, R.anim.animation_disappears_to_right);
                break;
            case FADE:
                transaction.setCustomAnimations(R.anim.animation_fade_in, 0, 0, R.anim.animation_fade_out);
                break;
            case BOTTOM:
                transaction.setCustomAnimations(R.anim.animation_appears_from_bottom, R.anim.animation_disappears_to_top,
                        R.anim.animation_appears_from_top, R.anim.animation_disappears_to_bottom);
                break;
            default:
                break;
        }
    }

    public void unlockMenu() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void removeCurrentFragment() {
        getSupportFragmentManager().popBackStack();
    }

}
