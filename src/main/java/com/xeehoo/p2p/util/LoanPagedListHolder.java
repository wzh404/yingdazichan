package com.xeehoo.p2p.util;

import org.springframework.beans.support.PagedListHolder;

/**
 * Created by wangzunhui on 2015/10/14.
 */
public class LoanPagedListHolder extends PagedListHolder {
    private int totalSize;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public int getNrOfElements() {
        return totalSize;
    }
}
