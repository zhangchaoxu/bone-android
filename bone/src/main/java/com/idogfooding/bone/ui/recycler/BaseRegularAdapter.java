package com.idogfooding.bone.ui.recycler;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import java.util.List;

/**
 * BaseRegularAdapter
 *
 * @author Charles
 */
public abstract class BaseRegularAdapter<T, B extends BaseViewHolder> extends easyRegularAdapter<T, B> {

    protected Fragment mFragment;

    public BaseRegularAdapter(List<T> list) {
        super(list);
    }

    public BaseRegularAdapter(Fragment fragment, List<T> list) {
        super(list);
        mFragment = fragment;
    }

    protected String getFirstPic(String pics) {
        if (TextUtils.isEmpty(pics)) {
            return null;
        } else {
            String[] picList = pics.split("\\|", -1);
            if (picList.length > 0) {
                return picList[0];
            } else {
                return null;
            }
        }
    }

}
