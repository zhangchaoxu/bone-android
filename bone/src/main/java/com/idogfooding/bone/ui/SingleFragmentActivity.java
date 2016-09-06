package com.idogfooding.bone.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.idogfooding.bone.R;

/**
 * Single Fragment Activity
 * activity with only one fragment
 *
 * @author Charles
 */
public abstract class SingleFragmentActivity extends BaseActivity {

    private boolean menuCreated;

    /**
     * content fragment
     */
    private Fragment mFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.single_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // replace content with fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragment = getContentFragment();
        fragmentTransaction.replace(R.id.fragment, mFragment);
        fragmentTransaction.commit();
    }

    /**
     * get the content fragment
     *
     * @return the content single fragment
     */
    protected abstract Fragment getContentFragment();

    protected Fragment getCurrentFragment() {
        return this.mFragment;
    }

    protected void replaceFragment(Fragment fragment) {
        mFragment = fragment;
        // replace content with fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, mFragment);
        fragmentTransaction.commit();
    }

    // [+] option menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mFragment != null) {
            boolean fragmentSelected = mFragment.onOptionsItemSelected(item);
            if (!fragmentSelected) {
                return super.onOptionsItemSelected(item);
            } else {
                return fragmentSelected;
            }
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void invalidateOptionsMenu() {
        if (menuCreated)
            super.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mFragment != null)
            mFragment.onCreateOptionsMenu(menu, getMenuInflater());

        boolean created = super.onCreateOptionsMenu(menu);
        menuCreated = true;
        return created;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mFragment != null)
            mFragment.onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }
    // [-] option menu
}
