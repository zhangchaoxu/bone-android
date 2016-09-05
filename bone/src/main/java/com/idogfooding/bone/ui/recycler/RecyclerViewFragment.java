package com.idogfooding.bone.ui.recycler;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.idogfooding.bone.R;
import com.idogfooding.bone.ui.BaseFragment;
import com.marshalchen.ultimaterecyclerview.ItemTouchListenerAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ClassicSpanGridLayoutManager;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;
import com.marshalchen.ultimaterecyclerview.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.HorizontalDividerItemDecoration;
import com.marshalchen.ultimaterecyclerview.ui.emptyview.emptyViewOnShownListener;

/**
 * RecyclerViewFragment
 *
 * support one type adapter with header/load more/refresh/empty
 *
 * @author Charles
 */
public abstract class RecyclerViewFragment<A extends easyRegularAdapter> extends BaseFragment implements emptyViewOnShownListener, UltimateRecyclerView.OnParallaxScroll {

    protected A adapter;
    protected UltimateRecyclerView ultimateRecyclerView;

    @Override
    protected int getContentView() {
        return R.layout.ultimate_recycler_view;
    }

    @Override
    protected void afterViewCreated(View view, Bundle savedInstanceState) {
        super.afterViewCreated(view, savedInstanceState);

        // init adapter
        createAdapter();

        // init urv ui
        ultimateRecyclerView = (UltimateRecyclerView) view.findViewById(R.id.ultimate_recycler_view);
        // empty view and policy
        ultimateRecyclerView.setEmptyView(R.layout.empty_view, UltimateRecyclerView.EMPTY_CLEAR_ALL, this);
        // fixed size
        ultimateRecyclerView.setHasFixedSize(getHasFixedSize());
        // layout manager
        ultimateRecyclerView.setLayoutManager(getLayoutManager());
        // header
        if (0 != getParallaxHeaderView()) {
            ultimateRecyclerView.setParallaxHeader(getParallaxHeaderView());
            ultimateRecyclerView.setOnParallaxScroll(this);
        } else if (0 != getNormalHeaderView()) {
            ultimateRecyclerView.setNormalHeader(LayoutInflater.from(getContext()).inflate(getNormalHeaderView(), null));
        }
        // ItemDecoration
        RecyclerView.ItemDecoration itemDecoration = getItemDecoration();
        if (null != itemDecoration) {
            ultimateRecyclerView.addItemDecoration(itemDecoration);
        }
        // refresh
        boolean isSwipeRefresh = getIsSwipeRefresh();
        ultimateRecyclerView.enableDefaultSwipeRefresh(isSwipeRefresh);
        if (isSwipeRefresh) {
            ultimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    onFireRefresh();
                }
            });
        }
        // load more
        if (isLoadMore()) {
            ultimateRecyclerView.setLoadMoreView(getLoadMoreView());
            ultimateRecyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
                @Override
                public void loadMore(int itemsCount, final int maxLastVisiblePosition) {
                    onLoadMore(itemsCount, maxLastVisiblePosition);
                }
            });
        }
        disableLoadMore();
        // click enable
        if (isClickEnabled()) {
            enableItemClick();
        }

        // init adapter and data
        ultimateRecyclerView.setAdapter(adapter);
    }

    protected abstract void createAdapter();

    protected void disableLoadMore() {
        if (null != ultimateRecyclerView) {
            ultimateRecyclerView.disableLoadmore();
        }
    }

    protected void enableLoadMore() {
        if (null != ultimateRecyclerView) {
            ultimateRecyclerView.reenableLoadmore();
        }
    }

    public void onFireRefresh() {

    }

    protected void onLoadMore(int itemsCount, int maxLastVisiblePosition) {

    }

    protected boolean getIsSwipeRefresh() {
        return true;
    }

    protected boolean isLoadMore() {
        return true;
    }

    protected boolean isClickEnabled() {
        return false;
    }

    protected int getLoadMoreView() {
        return R.layout.view_none;
    }

    protected int getParallaxHeaderView() {
        return 0;
    }

    protected int getNormalHeaderView() {
        return 0;
    }

    @Override
    public void onParallaxScroll(float percentage, float offset, View parallax) {

    }

    // ItemDecoration
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return getHorizontalDividerItemDecoration();
    }

    protected final RecyclerView.ItemDecoration getHorizontalDividerItemDecoration() {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.divider));
        paint.setAntiAlias(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            ultimateRecyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        return new HorizontalDividerItemDecoration.Builder(getActivity()).paint(paint).build();
    }

    protected final RecyclerView.ItemDecoration getStickyRecyclerHeadersDecoration() {
        return new StickyRecyclerHeadersDecoration(adapter);
    }

    // LayoutManager
    protected RecyclerView.LayoutManager getLayoutManager() {
        return getLinearLayoutManager();
    }

    protected final RecyclerView.LayoutManager getStaggerLayoutManager() {
        return new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    }

    protected final RecyclerView.LayoutManager getGridLayoutManager() {
        return new ClassicSpanGridLayoutManager(getContext(), 2, adapter);
    }

    protected final RecyclerView.LayoutManager getLinearLayoutManager() {
        return new ScrollSmoothLineaerLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false, 300);
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
        msg.setText("没有内容哦!");
        icon.setImageResource(R.mipmap.ic_common_empty);
    }

    // on click
    protected void enableItemClick() {
        ItemTouchListenerAdapter itemTouchListenerAdapter = new ItemTouchListenerAdapter(ultimateRecyclerView.mRecyclerView,
                new ItemTouchListenerAdapter.RecyclerViewOnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View clickedView, int position) {
                        onListItemClick(parent, clickedView, position);
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

    protected void onListItemLongClick(RecyclerView parent, View clickedView, int position) {

    }
}
