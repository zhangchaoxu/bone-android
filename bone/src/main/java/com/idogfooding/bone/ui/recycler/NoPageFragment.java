package com.idogfooding.bone.ui.recycler;

import android.os.Bundle;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import java.util.List;

/**
 * NoPageFragment
 *
 * @author Charles
 */
public abstract class NoPageFragment<T, A extends easyRegularAdapter> extends RecyclerViewFragment<A> {

    @Override
    protected void afterViewCreated(View view, Bundle savedInstanceState) {
        super.afterViewCreated(view, savedInstanceState);
        loadList(false);
    }

    protected void loadList(final boolean refresh) {

    }

    @Override
    public void onFireRefresh() {
        loadList(true);
    }

    @Override
    protected boolean isLoadMore() {
        return false;
    }

    protected void loadListOnSubscribe() {
        ultimateRecyclerView.setRefreshing(true);
    }

    protected void loadListOnNext(boolean refresh, List<T> list) {
        if (refresh) {
            adapter.removeAll();
        }
        if (list.isEmpty()) {
            ultimateRecyclerView.showEmptyView();
        } else {
            adapter.insert(list);
        }
        ultimateRecyclerView.setRefreshing(false);
    }

    protected void loadListOnError(Throwable throwable) {
        ultimateRecyclerView.setRefreshing(false);
        handleNetworkError(throwable);
    }

}
