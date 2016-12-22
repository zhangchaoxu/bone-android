package com.idogfooding.bone.ui.tab;

import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.idogfooding.bone.R;
import com.idogfooding.bone.ui.view.ViewPager;
import com.idogfooding.bone.utils.ViewUtils;

/**
 * TabLayoutPagerActivity
 *
 * @author Charles Zhang
 */
public abstract class TabLayoutPagerActivity<V extends TabFragmentPagerAdapter> extends PagerActivity {

    private static final int OFFSCREEN_PAGES = 3;

    protected ViewPager pager;
    protected CommonTabLayout tabLayout;

    /**
     * Pager adapter
     */
    protected V adapter;

    @Override
    public void onPageSelected(final int position) {
        super.onPageSelected(position);
    }

    /**
     * Create pager adapter
     *
     * @return pager adapter
     */
    protected abstract V createAdapter();

    /**
     * Get title for position
     *
     * @param position
     * @return title
     */
    protected String getTitle(final int position) {
        return adapter.getPageTitle(position).toString();
    }

    /**
     * Set tab and pager as gone or visible
     *
     * @param gone
     * @return this activity
     */
    protected TabLayoutPagerActivity<V> setGone(boolean gone) {
        ViewUtils.setGone(tabLayout, gone);
        ViewUtils.setGone(pager, gone);
        return this;
    }

    /**
     * Set current item to new position
     * <p>
     * This is guaranteed to only be called when a position changes and the
     * current item of the pager has already been updated to the given position
     * <p>
     * Sub-classes may override this method
     *
     * @param position
     */
    protected void setCurrentItem(int position) {
        // Intentionally left blank
    }

    /**
     * Get content view to be used when {@link #onCreate(Bundle)} is called
     *
     * @return layout resource id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.tab_layout_pager;
    }

    protected void updateCurrentItem(final int newPosition) {
        if (newPosition > -1 && newPosition < adapter.getCount()) {
            pager.setItem(newPosition);
            setCurrentItem(newPosition);
        }
    }

    protected void onTabClick(final int newPosition) {

    }

    protected void onTabLayoutReselect(int position) {

    }

    private void createPager() {
        adapter = createAdapter();
        invalidateOptionsMenu();
        pager.setAdapter(adapter);
    }

    /**
     * Create tab using information from current adapter
     * <p>
     * This can be called when the tabs changed but must be called after an
     */
    protected void createTabs() {
        if (tabLayout.getTabCount() > 0) {
            tabLayout.removeAllViews();
        }

        tabLayout.setTabData(adapter.getTabEntities());
    }

    /**
     * Configure tabs and pager
     */
    protected void configureTabPager() {
        if (adapter == null) {
            createPager();
            createTabs();
        }
    }

    /**
     * Configure tabs and pager
     */
    public void switchToTab(int position) {
    }

    @Override
    protected void afterContentView(Bundle savedInstanceState) {
        super.afterContentView(savedInstanceState);
        pager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (CommonTabLayout) findViewById(R.id.tab_layout);

        // setup ViewPager
        pager.addOnPageChangeListener(this);
        pager.setOffscreenPageLimit(OFFSCREEN_PAGES);
        pager.setScrollable(false);

        //set up tab layout
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                onTabClick(position);
                updateCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                onTabClick(position);
                onTabLayoutReselect(position);
            }
        });

        configureTabPager();
    }

    @Override
    protected FragmentProvider getProvider() {
        return adapter;
    }
}
