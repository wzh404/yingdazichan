package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.util.LoanPagedListHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/16.
 */
public interface LoanInvestService {
    /**
     *
     * @param page
     * @param pageSize
     * @param cont
     * @return
     */
    public List<LoanProduct> getInvestProductPager(int page, int pageSize, Map<String, Object> cond);

    /**
     *
     * @param cont
     * @return
     */
    public int getTotalProduct(Map<String, Object> cont);

    /*  admin service   */
    public LoanPagedListHolder getProductPager(int page, Map<String, Object> cond);
}
