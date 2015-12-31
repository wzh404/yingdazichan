package com.xeehoo.p2p.service.impl;

import com.fuiou.data.PreAuthCancelReqData;
import com.fuiou.data.PreAuthReqData;
import com.fuiou.data.PreAuthRspData;
import com.fuiou.service.FuiouService;
import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanUserInvestment;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/16.
 */
@Service("investService")
public class LoanInvestServiceImpl implements LoanInvestService{
    private final Logger logger = Logger.getLogger(LoanInvestServiceImpl.class);

    @Autowired
    private Environment environment;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<LoanProduct> getInvestProductPager(int page, int pageSize, Map<String, Object> cond) {
        int offset = pageSize * page;

        cond.put("pageSize", pageSize);
        cond.put("offset", offset);
        List<LoanProduct> products = productMapper.getProductPager(cond);
        return products;
    }

    @Override
    public int getTotalProduct(Map<String, Object> cond) {
        Integer total = productMapper.getTotalProduct(cond);
        return total;
    }

    private PreAuthRspData preAuth(String outCustNo, String inCustNo, String seqno, String amt){
        PreAuthReqData data = new PreAuthReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(seqno); //流水号
        data.setOut_cust_no(outCustNo);  // 出账账号
        data.setIn_cust_no(inCustNo); //入账账户
        data.setAmt(amt); //冻结金额
        data.setRem("test"); //备注

        try {
            PreAuthRspData rsp = FuiouService.preAuth(data);
            logger.info(rsp.toString());
            return rsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = false, rollbackFor=Exception.class)
    public int updateProductUserAmount(Integer productId, Integer userId, String mobile, Long amount)
        throws Exception{
        LoanProduct product = productMapper.getProduct(productId);
        if (product == null){
            logger.warn("product " + productId + "is not found!");
            return 0;
        }

        BigDecimal amt = new BigDecimal(amount / 100.0);
        int result = productMapper.updateProductAmount(productId, amt);
        if (result > 0) {
            String seqno = CommonUtil.getMchntTxnSsn();
            logger.info(mobile + " - user114 " + " - " + amount);
            PreAuthRspData resp = preAuth(mobile, "user114", seqno, amount.toString());
            if (resp != null && resp.getResp_code().equalsIgnoreCase("0000")){
                LoanUserInvestment investment = new LoanUserInvestment();

                investment.setProductId(productId);
                investment.setProductName(product.getProductName());
                investment.setUserId(userId);
                investment.setInvestAmount(amt);
                investment.setInvestTime(new Date());
                investment.setInvestStartDate(new Date());
                investment.setInvestClosingDate(new Date());
                investment.setInvestIncome(new BigDecimal(0.00));
                investment.setInvestServiceCharge(new BigDecimal(0.00));
                investment.setInvestStatus("I"); // 已投标
                investment.setPaySeqno(seqno);
                investment.setPayContractNo(resp.getContract_no());
                investment.setPayResponseCode("0000");

                return productMapper.saveUserInvestment(investment);
            }
            else{
                throw new Exception("error");// database rollback
            }
        }

        logger.warn("amount is not enough");
        return 0; // failed
    }

    @Override
    public LoanPagedListHolder getProductPager(int page, Map<String, Object> cond) {
        Integer totalSize = getTotalProduct(cond);
        List<LoanProduct> products = getInvestProductPager(page, Constant.PAGE_DEFAULT_SIZE, cond);
        LoanPagedListHolder pagedListHolder = new LoanPagedListHolder();
        pagedListHolder.setSource(products);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(Constant.PAGE_DEFAULT_SIZE);
        pagedListHolder.setTotalSize(totalSize);
        pagedListHolder.setMaxLinkedPages(Constant.PAGE_MAX_LINKED_PAGES);

        return pagedListHolder;
    }

    @Override
    public Integer saveProduct(LoanProduct loanProduct) {
        return productMapper.saveProduct(loanProduct);
    }

    @Override
    public LoanProduct getProduct(Integer productId) {
        return productMapper.getProduct(productId);
    }

    @Override
    public List<Map<String, Object>> getProductInvestments(Integer productId) {
        return productMapper.getProductInvestments(productId);
    }

    @Override
    public LoanPagedListHolder getUserInvestments(int page, QueryCondition cond) {
        return new QueryPager<LoanUserInvestment>(page, cond){
            @Override
            public Integer total(QueryCondition cond) {
                return productMapper.getTotalUserInvestment(cond.getCond());
            }

            @Override
            public List<LoanUserInvestment> elements(int page, QueryCondition cond) {
                return productMapper.getUserInvestments(cond.getCond());
            }
        }.query();
    }
}
