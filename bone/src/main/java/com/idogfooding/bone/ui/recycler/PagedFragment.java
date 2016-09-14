package com.idogfooding.bone.ui.recycler;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.idogfooding.bone.network.PagedResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * PagedFragment
 *
 * @author Charles
 */
public abstract class PagedFragment<T, A extends BaseRegularAdapter> extends RecyclerViewFragment<A> {

    protected int pageNumber = 1;
    protected int pageSize = 10;

    @Override
    protected void afterViewCreated(View view, Bundle savedInstanceState) {
        super.afterViewCreated(view, savedInstanceState);
        if (isLoadListOnStart()) {
            loadList(true);
        }
    }

    protected boolean isLoadListOnStart() {
        return true;
    }

    protected void loadList(boolean refresh) {

    }

    @Override
    public void onFireRefresh() {
        pageNumber = 1;
        disableLoadMore();
        loadList(true);
    }

    @Override
    protected void onLoadMore(int itemsCount, int maxLastVisiblePosition) {
        if (maxLastVisiblePosition <= 0)
            return;

        loadList(false);
    }

    /**
     * call Subscribe on load list
     */
    protected void loadListOnSubscribe() {
        ultimateRecyclerView.setRefreshing(true);
    }

    protected void loadListOnNext(boolean refresh, PagedResult<T> result) {
        ultimateRecyclerView.setRefreshing(false);
        /*if (refresh) {
            adapter.removeAll();
        }*/
        if (result.getTotalRow() == 0) {
            ultimateRecyclerView.showEmptyView();
            adapter.replace(new ArrayList<T>());
        } else {
            ultimateRecyclerView.hideEmptyView();
            if (refresh) {
                adapter.replace(result.getList());
                scrollToPosition(0);
            } else {
                adapter.insert(result.getList());
            }
        }
        if (result.hasNextPage()) {
            pageNumber++;
            if (!ultimateRecyclerView.isLoadMoreEnabled()) {
                enableLoadMore();
            }
        } else {
            disableLoadMore();
        }
    }

    protected void loadListOnError(Throwable throwable) {
        ultimateRecyclerView.setRefreshing(false);
        handleNetworkError(throwable);
    }

    protected Map<String, Object> initFields() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("pageNum", pageNumber);
        return fields;
    }

    @Override
    protected void onListItemClick(RecyclerView parent, View clickedView, int position) {
        onListItemClick(parent, clickedView, position, (T)clickedView.getTag());
    }

    protected void onListItemClick(RecyclerView parent, View clickedView, int position, T data) {}

    @Override
    protected void onListItemLongClick(RecyclerView parent, View clickedView, int position) {
        onListItemLongClick(parent, clickedView, position, (T) clickedView.getTag());
    }

    protected void onListItemLongClick(RecyclerView parent, View clickedView, int position, T data) {}

}
