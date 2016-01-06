package com.xeehoo.p2p.util;

import org.springframework.beans.support.PagedListHolder;

import java.util.List;

/**
 * Created by wangzunhui on 2015/12/31.
 */
public abstract class QueryPager<E> {
    private QueryCondition queryCondition;
    private int page = 0;

    public abstract Integer total(QueryCondition cond);
    public abstract List<E> elements(int page, QueryCondition cond);

    public void init(QueryCondition cond){
        cond.put("_pageSize", Constant.PAGE_DEFAULT_SIZE);
        cond.put("_offset", Constant.PAGE_DEFAULT_SIZE * page);
    }

    public QueryPager(int page){
        this.page = page;
        this.queryCondition = new QueryCondition();
        init(queryCondition);
    }

    public QueryPager(int page, QueryCondition queryCondition){
        this.page = page;
        this.queryCondition = queryCondition;
        init(queryCondition);
    }

    public LoanPagedListHolder query(){
        int n = total(queryCondition);
        List<E> list = elements(page, queryCondition);

        LoanPagedListHolder pagedListHolder = new LoanPagedListHolder();
        pagedListHolder.setSource(list);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(Constant.PAGE_DEFAULT_SIZE);
        pagedListHolder.setTotalSize(n);
        pagedListHolder.setMaxLinkedPages(Constant.PAGE_MAX_LINKED_PAGES);

        return pagedListHolder;
    }
}
