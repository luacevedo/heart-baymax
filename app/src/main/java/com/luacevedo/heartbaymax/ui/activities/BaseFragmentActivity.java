package com.luacevedo.heartbaymax.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.luacevedo.heartbaymax.R;

public class BaseFragmentActivity extends AppCompatActivity {

    public enum Direction {
        RIGHT;
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setInitialFragment(Fragment fragment) {
        removeAllFragments();
        setFragment(fragment, Direction.RIGHT);
    }

    private void removeAllFragments() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate(getSupportFragmentManager().getBackStackEntryAt(0).getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void slideNextFragment(Fragment fragment) {
        setFragment(fragment, Direction.RIGHT);
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
            default:
                break;
        }
    }

}
