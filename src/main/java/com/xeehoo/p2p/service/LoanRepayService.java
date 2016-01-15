package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanUserRepay;

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

}
