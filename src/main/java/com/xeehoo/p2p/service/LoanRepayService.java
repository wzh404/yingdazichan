package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanProductRepay;
import com.xeehoo.p2p.po.LoanUserRepay;

import java.util.List;

/**
 * Created by wangzunhui on 2016/1/7.
 */
public interface LoanRepayService {
    /**
     * 产品按月还款
     *
     * @param repayId
     */
    public void repay(Integer repayId);

    /**
     * 查询用户投资产品的还款情况
     *
     * @param investId
     * @return
     */
    public List<LoanUserRepay> getUserRepays(Integer investId);

    /**
     * 查询产品的还款情况
     *
     * @param productId
     * @return
     */
    public List<LoanProductRepay> getProductRepay(Integer productId);

    /**
     * 生成还款计划
     *
     * @param productId
     * @return
     */
    public Integer createRepayPlan(Integer productId);
}
