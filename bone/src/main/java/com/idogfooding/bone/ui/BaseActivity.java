package com.idogfooding.bone.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONException;
import com.idogfooding.bone.BaseApplication;
import com.idogfooding.bone.R;
import com.idogfooding.bone.network.ApiException;
import com.idogfooding.bone.utils.AppManager;
import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.ButterKnife;

/**
 * Base Activity of all activities of Application
 *
 * @author Charles
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    protected final String TAG = getClass().getSimpleName();

    // toolbar
    Toolbar mToolbar;
    public TextView mToolbarTitle;
    View mToolbarDivider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // before content view
        beforeContentView();
        setContentView(getLayoutId());
        Log.d(TAG, TAG + ".onCreate...");
        // after content view
        afterContentView(savedInstanceState);

        // add the activity into the stack
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.openActivityDurationTrack(false);
    }

    /**
     * Get content view to be used when {@link #onCreate(Bundle)} is called
     *
     * @return layout resource id
     */
    protected abstract int getLayoutId();

    /**
     * init before content view
     */
    protected void beforeContentView() {
        // empty
    }

    /**
     * init after content view
     */
    protected void afterContentView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initActionBar(isShowHomeAsUp());
    }

    // [+] actionbar
    public Toolbar getToolbar() {
        return mToolbar;
    }

    protected void showHomeAsUp(boolean show) {
        ActionBar actionBar = getSupportActionBar();
        if (null == actionBar)
            return;

        if (show) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    protected void changeTheme(int dividerVisible, int bgColor, int titleColor) {
        if (null == mToolbar)
            return;

        mToolbar.setBackgroundColor(getResources().getColor(bgColor));
        if (mToolbarTitle != null) {
            mToolbarTitle.setTextColor((getResources().getColor(titleColor)));
        }
        if (mToolbarDivider != null) {
            mToolbarDivider.setVisibility(dividerVisible);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(bgColor));
        }
    }

    protected void hideToolbar(boolean hidden) {
        if (null != mToolbar) {
            if (hidden && mToolbar.isShown()) {
                mToolbar.setVisibility(View.GONE);
            } else if (!hidden && !mToolbar.isShown()) {
                mToolbar.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (null != mToolbarTitle) {
            mToolbarTitle.setText(title);
        } else {
            super.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        this.setTitle(getString(titleId));
    }

    public void setSubTitle(String subtitle) {
        if (null != mToolbar) {
            mToolbar.setSubtitle(subtitle);
        }
    }

    public void setSubTitle(int resId) {
        setSubTitle(getString(resId));
    }

    /**
     * initActionBar
     */
    protected void initActionBar(boolean showHomeAsUp) {
        // set Toolbar as actionbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null == mToolbar)
            return;

        mToolbarDivider = mToolbar.findViewById(R.id.toolbar_divider);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowTitleEnabled(null == mToolbarTitle);
            actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }

    protected boolean isShowHomeAsUp() {
        return true;
    }

    protected boolean isDarkStatusIcon() {
        return true;
    }

    protected void setDarkStatusIcon(boolean bDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (bDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

    // [-] actionbar

    // [+] network

    /**
     * handle api error
     *
     * @param throwable
     */
    protected void handleNetworkError(Throwable throwable) {
        int errorMsg = R.string.unknown_error;
        if (throwable instanceof ApiException) {
            handleApiError((ApiException) throwable);
            return;
        } else if (throwable instanceof NullPointerException) {
            errorMsg = R.string.null_point_exception;
        } else if (throwable instanceof UnknownHostException) {
            errorMsg = R.string.unknown_host_exception;
        } else if (throwable instanceof JSONException) {
            errorMsg = R.string.json_exception;
        } else if (throwable instanceof SocketTimeoutException) {
            errorMsg = R.string.socket_timeout_exception;
        }
        BaseApplication.showToast(errorMsg);
    }

    /**
     * handle biz api error
     *
     * @param apiException
     */
    protected void handleApiError(ApiException apiException) {
        if (apiException.isUnauthorized()) {
            //AppContext.getInstance().accountLogout();
            BaseApplication.showToast("登录信息失效,请重新登录!");
            Intent intent = new Intent("LOGIN");
            startActivity(intent);
            AppManager.getAppManager().finishAllActivityExcept("LoginActivity");
        } else {
            BaseApplication.showToast(apiException.getMessage());
        }
    }
    // [-] network

    // [+] Options Menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case Menu.FIRST:
                return onFirstMenuSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        addMenuItem(menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * add single menu item
     *
     * @param menu
     * @return
     */
    protected void addMenuItem(Menu menu) {
        // add menu like this
        // menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, R.string.register).setIcon(R.drawable.ic_action_add).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    /**
     * on first menu selected/click event
     *
     * @param item
     * @return
     */
    protected boolean onFirstMenuSelected(MenuItem item) {
        return false;
    }

    // [-] Options Menu

    // [+] Progress Dialog
    /**
     * Shows the progress UI and hides the login_bg form.
     */
    private MaterialDialog mProgressDialog;

    public void hiddenProgress() {
        if (null != mProgressDialog && mProgressDialog.isShowing() && !isFinishing())
            mProgressDialog.dismiss();
    }

    public void showProgress(int contentResId) {
        showProgress(getString(contentResId));
    }

    public void showProgress(String content) {
        if (null == mProgressDialog) {
            mProgressDialog = new MaterialDialog.Builder(this)
                    .content(content)
                    .progress(true, 0)
                    .cancelable(false)
                    .show();
        } else {
            mProgressDialog.setContent(content);
            mProgressDialog.show();
        }
    }
    // [-] Progress Dialog

    // [+] start activity
    protected void goActivity(Intent intent) {
        goActivity(false, intent);
    }

    protected void goActivity(boolean isFinish, Intent intent) {
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }
    // [-] start activity

    // [+] Intent extra

    /**
     * Get intent extra
     *
     * @param name
     * @return serializable
     */
    @SuppressWarnings("unchecked")
    protected <V extends Serializable> V getSerializableExtra(final String name) {
        return (V) getIntent().getSerializableExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return int
     */
    protected int getIntExtra(final String name) {
        return getIntent().getIntExtra(name, -1);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return long
     */
    protected long getLongExtra(final String name) {
        return getIntent().getLongExtra(name, -1l);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return int array
     */
    protected int[] getIntArrayExtra(final String name) {
        return getIntent().getIntArrayExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return boolean
     */
    protected boolean getBooleanExtra(final String name) {
        return getIntent().getBooleanExtra(name, false);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return boolean array
     */
    protected boolean[] getBooleanArrayExtra(final String name) {
        return getIntent().getBooleanArrayExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return string
     */
    protected String getStringExtra(final String name) {
        return getIntent().getStringExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return string array
     */
    protected String[] getStringArrayExtra(final String name) {
        return getIntent().getStringArrayExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return char sequence array
     */
    protected CharSequence[] getCharSequenceArrayExtra(final String name) {
        return getIntent().getCharSequenceArrayExtra(name);
    }
    // [-] Intent extra

    // [+] umeng analytics
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
        MobclickAgent.onPause(this);
    }
    // [-] umeng analytics

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // end the Activity & remove it from stack
        AppManager.getAppManager().finishActivity(this);
    }
}
