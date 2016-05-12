package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanTransfer;
import com.xeehoo.p2p.po.LoanUserInvestment;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
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
     * @param loanProduct
     * @return
     */
    public Integer updateProduct(LoanProduct loanProduct);

    /**
     *
     * @param productId
     * @return
     */
    public LoanProduct getProduct(Integer productId);

    /**
     *
     * @param investId
     * @return
     */
    public LoanUserInvestment getUserInvestment(Integer investId);

//    /**
//     *
//     * @param productId
//     * @return
//     */
//    public List<Map<String, Object>> getProductInvestments(Integer productId);

    /**
     *
     * @param page
     * @param cond
     * @return
     */
    public LoanPagedListHolder getProductInvestmentPager(int page, QueryCondition cond);

    /**
     *
     * @param page
     * @param cond
     * @return
     */
    public LoanPagedListHolder getUserInvestments(int page, QueryCondition cond);

    /**
     *
     * @param productId
     */
    public void settleProductById(Integer productId);

    /**
     *
     * @param productId
     * @param productStatus
     * @return
     */
    public Integer updateProductStatus(Integer productId, Integer productStatus);


    /**
     * 发布产品
     *
     * @param productId
     * @param productStatus
     * @param investStartDate
     * @param investCloseDate
     * @return
     */
    public Integer updateProductStatusAndDate(Integer productId, Integer productStatus, Date investStartDate, Date investCloseDate);

    /**
     * APP产品列表，查询大于productId的产品的30条记录
     *
     * @param productId
     * @return
     */
    public List<Map<String, Object>> getAppProduct(Integer productId);

    /**
     * 个人理财产品
     *
     * @param userId
     * @param investId
     * @return
     */
    public List<Map<String, Object>> getAppUserInvestment(Integer userId, Integer investId);

    /**
     *
     * @param investId
     * @param discount
     * @return
     */
    public Integer transfer(Integer investId, Integer userId, BigDecimal discount);

    /**
     *
     * @param transferId
     * @param userId
     * @param mobile
     * @return
     * @throws Exception
     */
    public Integer transferComplete(Integer transferId, Integer userId, String mobile)
            throws Exception ;

    /**
     *
     * @return
     */
    public List<LoanTransfer> getTransfers();

    /**
     *
     * @param transferId
     * @return
     */
    public LoanTransfer getTransfer(Integer transferId);

    /**
     *
     * @param investId
     * @return
     * @throws Exception
     */
    public Integer cancelTransferRequest(Integer investId) throws  Exception;
}
