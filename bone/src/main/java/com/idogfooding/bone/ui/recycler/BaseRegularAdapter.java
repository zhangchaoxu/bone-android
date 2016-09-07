package com.idogfooding.bone.ui.recycler;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONException;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.idogfooding.bone.BaseApplication;
import com.idogfooding.bone.R;
import com.idogfooding.bone.network.ApiException;
import com.idogfooding.bone.utils.AppManager;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

/**
 * BaseRegularAdapter
 *
 * @author Charles
 */
public abstract class BaseRegularAdapter<T, B extends BaseViewHolder> extends easyRegularAdapter<T, B> {

    protected final String TAG = getClass().getSimpleName();

    protected RecyclerViewFragment mFragment;

    public BaseRegularAdapter(List<T> list) {
        super(list);
    }

    public BaseRegularAdapter(RecyclerViewFragment fragment, List<T> list) {
        super(list);
        mFragment = fragment;
    }

    public void replace(List<T> insert_data) {
        try {
            source.clear();
            Iterator<T> id = insert_data.iterator();
            int g = getItemCount();
            //   if (hasHeaderView()) g--;
            if (enableLoadMore()) g--;
            final int start = g;
            synchronized (mLock) {
                while (id.hasNext()) {
                    source.add(id.next());
                }
            }
            notifyDataSetChanged();
            if (enabled_custom_load_more_view) {
                revealDispatchLoadMoreView();
            }
        } catch (Exception e) {
            String o = e.fillInStackTrace().getCause().getMessage().toString();
            Log.d("fillInStackTrace", o + " : ");
        }
    }

    protected void loadImg(ImageView image, String pic) {
        loadImg(image, pic, R.mipmap.ic_placeholder, R.mipmap.ic_errorholder);
    }

    protected void loadImg(ImageView image, String pic, @DrawableRes int placeholder, @DrawableRes int errorholder) {
        image.setVisibility(View.VISIBLE);
        Glide.with(mFragment).load(pic).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).error(errorholder).placeholder(placeholder).into(image);
    }

    // [+] Progress Dialog
    /**
     * Shows the progress UI and hides the login_bg form.
     */
    private MaterialDialog mProgressDialog;

    public void hiddenProgress() {
        if (null != mProgressDialog && mProgressDialog.isShowing() && !mFragment.getActivity().isFinishing())
            mProgressDialog.dismiss();
    }

    public void showProgress(int contentResId) {
        showProgress(mFragment.getString(contentResId));
    }

    public void showProgress(String content) {
        if (null == mProgressDialog) {
            mProgressDialog = new MaterialDialog.Builder(mFragment.getContext())
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
        Log.e(TAG, "handleNetworkError: " +  throwable.getMessage());
        throwable.printStackTrace();
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
            Intent intent = new Intent(".USER.LOGIN");
            mFragment.startActivity(intent);
            AppManager.getAppManager().finishAllActivityExcept("LoginActivity");
        } else {
            Log.e(TAG, "ApiException,code:" + apiException.getCode() + ",msg=" + apiException.getMessage());
            BaseApplication.showToast(apiException.getCode() + ":" + apiException.getMessage());
        }
    }
    // [-] network
}
