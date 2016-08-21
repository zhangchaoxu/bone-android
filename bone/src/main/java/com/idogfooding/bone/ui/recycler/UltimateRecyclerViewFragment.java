package com.idogfooding.bone.ui.recycler;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.idogfooding.bone.R;
import com.idogfooding.bone.ui.BaseFragment;
import com.marshalchen.ultimaterecyclerview.ItemTouchListenerAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;
import com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.HorizontalDividerItemDecoration;
import com.marshalchen.ultimaterecyclerview.ui.emptyview.emptyViewOnShownListener;

/**
 * UltimateRecyclerViewFragment
 *
 * @author Charles
 */
public abstract class UltimateRecyclerViewFragment<A extends easyRegularAdapter> extends BaseFragment implements emptyViewOnShownListener, UltimateRecyclerView.OnParallaxScroll {

    protected A adapter;
    protected UltimateRecyclerView ultimateRecyclerView;

    @Override
    protected int getContentView() {
        return R.layout.ultimate_recycler_view;
    }

    @Override
    protected void afterViewCreated(View view, Bundle savedInstanceState) {
        super.afterViewCreated(view, savedInstanceState);

        ultimateRecyclerView = (UltimateRecyclerView) view.findViewById(R.id.ultimate_recycler_view);
        // init urv ui
        ultimateRecyclerView.setEmptyView(R.layout.empty_view, UltimateRecyclerView.EMPTY_CLEAR_ALL, this);

        ultimateRecyclerView.setHasFixedSize(getHasFixedSize());
        ultimateRecyclerView.setLayoutManager(getLayoutManager());
        int headerView = getHeaderView();
        if (0 != headerView) {
            ultimateRecyclerView.setParallaxHeader(headerView);
            ultimateRecyclerView.setOnParallaxScroll(this);
        }
        RecyclerView.ItemDecoration itemDecoration =  getItemDecoration();
        if (null != itemDecoration) {
            ultimateRecyclerView.addItemDecoration(itemDecoration);
        }
        boolean isSwipeRefresh = getIsSwipeRefresh();
        ultimateRecyclerView.enableDefaultSwipeRefresh(isSwipeRefresh);
        if (isSwipeRefresh) {
            ultimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refresh();
                }
            });
        }
        if (getIsLoadMore()) {
            ultimateRecyclerView.setLoadMoreView(R.layout.load_more);
            ultimateRecyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
                @Override
                public void loadMore(int itemsCount, final int maxLastVisiblePosition) {
                    more();
                }
            });
        } else {
            disableLoadMore();
        }

        // init adapter and data
        createAdapter();
        ultimateRecyclerView.setAdapter(adapter);
    }

    protected abstract void createAdapter();

    protected void disableLoadMore() {
        ultimateRecyclerView.disableLoadmore();
    }

    protected void refresh() {

    }

    protected void more() {

    }

    protected boolean getIsSwipeRefresh() {
        return true;
    }

    protected boolean getIsLoadMore() {
        return true;
    }

    protected int getHeaderView() {
        return 0;
    }

    @Override
    public void onParallaxScroll(float percentage, float offset, View parallax) {

    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.divider));
        paint.setAntiAlias(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            ultimateRecyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        return new HorizontalDividerItemDecoration.Builder(getActivity()).paint(paint).build();
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new ScrollSmoothLineaerLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false, 300);
    }

    protected boolean getHasFixedSize() {
        return true;
    }

    @Override
    public void onEmptyViewShow(View view) {
        TextView msg = (TextView) view.findViewById(R.id.tv_exception_msg);
        ImageView icon = (ImageView) view.findViewById(R.id.iv_exception_icon);

        setEmptyMsgAndIcon(msg, icon);
    }

    protected void setEmptyMsgAndIcon(TextView msg, ImageView icon) {
    }

    protected void enableItemClick() {
        ItemTouchListenerAdapter itemTouchListenerAdapter = new ItemTouchListenerAdapter(ultimateRecyclerView.mRecyclerView,
                new ItemTouchListenerAdapter.RecyclerViewOnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View clickedView, int position) {
                        onListItemClick(parent,  clickedView, position);
                    }

                    @Override
                    public void onItemLongClick(RecyclerView parent, View clickedView, int position) {
                        onListItemLongClick(parent, clickedView, position);
                    }
                });
        ultimateRecyclerView.addOnItemTouchListener(itemTouchListenerAdapter);
    }

    protected void onListItemClick(RecyclerView parent, View clickedView, int position) {

    }

    protected void onListItemLongClick(RecyclerView parent, View clickedView, int positio) {

    }
}
