package com.idogfooding.bone.ui.recycler;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import java.util.Iterator;
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

}
