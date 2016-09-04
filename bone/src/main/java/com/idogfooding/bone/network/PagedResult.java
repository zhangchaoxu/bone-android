package com.idogfooding.bone.network;

import java.io.Serializable;
import java.util.List;

/**
 * PagedResult
 *
 * @author Charles
 */
public class PagedResult<E> implements Serializable {

    public PagedResult(int totalRow, boolean hasNextPage, List<E> list) {
        this.totalRow = totalRow;
        this.hasNextPage = hasNextPage;
        this.list = list;
    }

    private int totalRow;
    private boolean hasNextPage = false;
    private List<E> list;

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public boolean hasNextPage() {
        return hasNextPage;
    }
}
