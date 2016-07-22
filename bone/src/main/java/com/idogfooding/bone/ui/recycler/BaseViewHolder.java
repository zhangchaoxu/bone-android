package com.idogfooding.bone.ui.recycler;

import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;

/**
 * BaseViewHolder
 *
 * @author Charles Zhang
 */
public class BaseViewHolder extends UltimateRecyclerviewViewHolder {

    public BaseViewHolder(View itemView) {
        this(itemView, true);
    }

    public BaseViewHolder(View itemView, boolean isItem) {
        super(itemView);

        if (isItem) {
            ButterKnife.bind(this, itemView);
            AutoUtils.auto(itemView);
        }
    }

}
