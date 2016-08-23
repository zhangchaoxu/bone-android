package com.idogfooding.bone.ui.recycler;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.idogfooding.bone.R;
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

    protected void loadImg(ImageView image, String pic) {
        loadImg(image, pic, R.mipmap.ic_placeholder, R.mipmap.ic_errorholder);
    }

    protected void loadImg(ImageView image, String pic, @DrawableRes int placeholder, @DrawableRes  int errorholder) {
        image.setVisibility(View.VISIBLE);
        Glide.with(mFragment).load(pic).diskCacheStrategy(DiskCacheStrategy.ALL).error(errorholder).placeholder(placeholder).into(image);
    }

}
