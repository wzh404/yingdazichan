package com.xeehoo.p2p.util;

import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangzunhui on 2016/1/19.
 */
public abstract class QueryHolder<E> {
    private QueryCondition queryCondition;
    private  ModelAndView modelAndView;
    private int page;

    public abstract LoanPagedListHolder getPagedListHolder(int page, QueryCondition queryCondition);

    public QueryHolder(int page, ModelAndView modelAndView){
        this.page = page;
        this.modelAndView = modelAndView;
        this.queryCondition = new QueryCondition();
    }

    public void put(String key, Object val){
        this.queryCondition.put(key, val);
    }

    public void query(String url){
        LoanPagedListHolder pagedListHolder = getPagedListHolder(page, queryCondition);

        queryCondition.setModelAndView(url, modelAndView);
        modelAndView.addObject("pagedListHolder", pagedListHolder);
    }
}
