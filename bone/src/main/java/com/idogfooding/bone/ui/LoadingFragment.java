package com.idogfooding.bone.ui;

import com.idogfooding.bone.R;

/**
 * LoadingFragment
 *
 * @author Charles Zhang
 */
public class LoadingFragment extends BaseFragment {

    public static LoadingFragment newInstance() {
        return new LoadingFragment();
    }

    @Override
    protected int getContentView() {
        return R.layout.loading;
    }
}
