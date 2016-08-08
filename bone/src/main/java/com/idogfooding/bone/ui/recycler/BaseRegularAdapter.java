package com.idogfooding.bone.ui.recycler;

import android.support.v4.app.Fragment;

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

}
