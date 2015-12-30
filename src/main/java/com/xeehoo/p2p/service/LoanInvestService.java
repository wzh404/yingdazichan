package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.util.LoanPagedListHolder;

import java.math.BigDecimal;
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
     * @param cond
     * @return
     */
    public List<LoanProduct> getInvestProductPager(int page, int pageSize, Map<String, Object> cond);

    /**
     *
     * @param cond
     * @return
     */
    public int getTotalProduct(Map<String, Object> cond);

    /**
     *
     * @param productId
     * @param amount
     * @return
     */
    public int updateProductUserAmount(Integer productId, Integer userId, String mobile, Long amount) throws  Exception;

    /*  admin service   */

    /**
     *
     * @param page
     * @param cond
     * @return
     */
    public LoanPagedListHolder getProductPager(int page, Map<String, Object> cond);

    /**
     *
     * @param loanProduct
     * @return
     */
    public Integer saveProduct(LoanProduct loanProduct);

    /**
     *
     * @param productId
     * @return
     */
    public LoanProduct getProduct(Integer productId);

    /**
     *
     * @param productId
     * @return
     */
    public List<Map<String, Object>> getProductInvestments(Integer productId);

}
