package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanUserRepay;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;

import java.util.List;

/**
 * Created by wangzunhui on 2016/1/7.
 */
public interface LoanRepayService {

    /**
     * 根据还款计划还款。
     *
     * @param userRepay 还款计划
     * @return
     */
    public Integer repay(LoanUserRepay userRepay);

    /**
     *
     * 执行当天的还款计划
     *
     */
    public void repayNow();

    /**
     * 根据产品查询还款记录
     *
     * @param page
     * @param cond
     * @return
     */
    public LoanPagedListHolder getProductRepayPager(int page, QueryCondition cond);

}
