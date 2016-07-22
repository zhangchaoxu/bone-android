package com.idogfooding.bone.ui.tab;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

/**
 * Pager adapter that provides the current fragment
 *
 * @author Charles
 */
public abstract class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    protected ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    protected ArrayList<Fragment> mFragments = new ArrayList<>();

    protected Intent intent;

    public TabFragmentPagerAdapter(FragmentActivity activity) {
        this(activity, null);
    }

    public TabFragmentPagerAdapter(FragmentActivity activity, Intent intent) {
        super(activity);
        this.intent = intent;
        initTabEntities();
        initFragments();
    }

    protected void initTabEntities() {
        mTabEntities.clear();
    }

    protected void initFragments() {
        mFragments.clear();
    }

    protected ArrayList<CustomTabEntity> getTabEntities() {
        if (mTabEntities.isEmpty()) {
            initTabEntities();
        }
        return mTabEntities;
    }

    protected ArrayList<Fragment> getFragmentList() {
        if (mFragments.isEmpty()) {
            initFragments();
        }
        return mFragments;
    }

    @Override
    public int getCount() {
        return getTabEntities().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position < getTabEntities().size() ? getTabEntities().get(position).getTabTitle() : null;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < 0 || position >= getFragmentList().size())
            return null;

        saveFragment(getFragmentList().get(position));
        return getFragmentList().get(position);
    }
}

