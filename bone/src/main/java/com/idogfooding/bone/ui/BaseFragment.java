package com.idogfooding.bone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONException;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.idogfooding.bone.BaseApplication;
import com.idogfooding.bone.R;
import com.idogfooding.bone.network.ApiException;
import com.idogfooding.bone.utils.AppManager;
import com.umeng.analytics.MobclickAgent;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Base Fragment offers
 * umeng analytics
 * home optional selected
 *
 * @author Charles
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {

    protected final String TAG = getClass().getSimpleName();

    private Unbinder unbinder;

    /**
     * Is this fragment usable from the UI-thread
     *
     * @return true if usable, false otherwise
     */
    protected boolean isUsable() {
        return getActivity() != null;
    }

    /**
     * Get content view to be used when {@link #onCreate(Bundle)} is called
     *
     * @return layout resource id
     */
    protected abstract int getContentView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initArgument(getArguments());
        super.onViewCreated(view, savedInstanceState);
        afterViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void afterViewCreated(View view, Bundle savedInstanceState) {}

    protected void loadImg(ImageView image, String pic) {
        loadImg(image, pic, R.mipmap.ic_placeholder, R.mipmap.ic_errorholder);
    }

    protected void loadImg(ImageView image, String pic, @DrawableRes int placeholder, @DrawableRes int errorholder) {
        image.setVisibility(View.VISIBLE);
        Glide.with(this).load(pic).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).error(errorholder).placeholder(placeholder).into(image);
    }

    // [+] network
    /**
     * handle api error
     * @param throwable
     */
    protected void handleNetworkError(Throwable throwable) {
        int errorMsg = R.string.unknown_error;
        if (throwable instanceof ApiException) {
            handleApiError((ApiException)throwable);
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
        Log.e(TAG, throwable.getMessage());
        throwable.printStackTrace();
    }

    /**
     * handle biz api error
     * @param apiException
     */
    protected void handleApiError(ApiException apiException) {
        if (apiException.isUnauthorized()) {
            //AppContext.getInstance().accountLogout();
            BaseApplication.showToast("登录信息失效,请重新登录!");
            Intent intent = new Intent(getContext().getPackageName() + ".USER.LOGIN");
            startActivity(intent);
            AppManager.getAppManager().finishAllActivityExcept("LoginActivity");
        } else {
            Log.e(TAG, "ApiException,code:" + apiException.getCode() + ",msg=" + apiException.getMessage());
            BaseApplication.showToast(apiException.getCode() + ":" + apiException.getMessage());
        }
    }
    // [-] network

    // [+] Progress Dialog
    /**
     * Shows the progress UI and hides the login_bg form.
     */
    public void hiddenProgress() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hiddenProgress();
        }
    }

    public void showProgress(int contentResId) {
        showProgress(getString(contentResId));
    }

    public void showProgress(String content) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showProgress(content);
        }
    }
    // [-] Progress Dialog

    /**
     * init argument
     */
    protected void initArgument(Bundle args) {
        // blank
    }

    // [+] title
    private String mTitle;

    public void setTitle(int titleResId) {
        setTitle(getString(titleResId));
    }

    public void setTitle(String title) {
        this.mTitle = title;
        getActivity().setTitle(mTitle);
    }

    public String getTitle() {
        return mTitle;
    }

    private String mSubTitle;

    public void setSubTitle(String subTitle) {
        this.mSubTitle = subTitle;
        ((BaseActivity) getActivity()).setSubTitle(subTitle);
    }

    public String getSubTitle() {
        return mSubTitle;
    }
    // [-] title

    // [+] Options Menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        addMenuItem(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!isUsable())
            return false;

        switch (item.getItemId()) {
            case (android.R.id.home):
                getActivity().onBackPressed();
                return true;
            case Menu.FIRST:
                return onFirstMenuSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
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

    // [+] umeng analytics
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }
    // [-] umeng analytics

    // [+] view utils
    public BaseFragment fadeIn(View view, boolean animate) {
        if (view != null)
            if (animate)
                view.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
            else
                view.clearAnimation();
        return this;
    }

    public BaseFragment fadeOut(View view, boolean animate) {
        if (view != null)
            if (animate)
                view.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
            else
                view.clearAnimation();
        return this;
    }

    public BaseFragment show(View view) {
        view.setVisibility(View.VISIBLE);
        return this;
    }

    public BaseFragment hide(View view) {
        view.setVisibility(View.GONE);
        return this;
    }
    // [-] view utils

}
