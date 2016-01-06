package com.xeehoo.p2p.service.impl;

import com.fuiou.data.*;
import com.fuiou.service.FuiouService;
import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanUserInvestment;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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

    /**
     * 6.转账(商户与个人之间)
     *
     * @param outCustNo
     * @return
     */
    private String transferBmu(String outCustNo, String inCustNo, String contractNo, String amt){
        TransferBmuReqData data = new TransferBmuReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
        data.setOut_cust_no(outCustNo);  // 转出账号
        data.setIn_cust_no(inCustNo);   // 转入账号
        data.setContract_no(contractNo); //预授权号
        data.setAmt(amt); //划拨金额
        data.setRem("test"); //备注

        CommonRspData rsp = null;
        try {
            rsp = FuiouService.transferBmu(data);
            logger.info(rsp.toString());

            return rsp.getResp_code();
        } catch (Exception e) {
            e.printStackTrace();
            return "9999";
        }
    }

    /**
     * 满标结算
     *
     * @param productId
     */
    @Async
    @Override
    public void settleProductById(Integer productId) {
        List<Map<String, Object>> settles = new ArrayList<Map<String, Object>>();
        Integer productStatus = Constant.PRODUCT_STATUS_SETTLE;

        LoanProduct product = getProduct(productId);
        // 检测产品状态是否为发布状态（1)，产品是否到期
        if (product == null || product.isNotReleaseStatus()){
            logger.warn("product settle failed");
            return;
        }

        // 状态用户冻结资金到盈达账户
        List<Map<String, Object>> userInvestments = getProductInvestments(productId);
        for (Map<String, Object> map : userInvestments){
            String payRespCode = (String)map.get("payrespcode"); // 冻结状态
            String tranRespCode = (String)map.get("tranrespcode"); // 划拨状态
            if ("0000".equalsIgnoreCase(payRespCode) && !"0000".equalsIgnoreCase(tranRespCode)){
                Integer investId = (Integer)map.get("investid");
                String contractNo = (String)map.get("contractno");
                String mobile = (String)map.get("mobile");
                BigDecimal amt = (BigDecimal)map.get("amount");
                String amount = (new Long(amt.longValue() * 100)).toString();
                logger.info(contractNo + " - " + mobile + " - " + amount);
                String respCode = transferBmu(mobile, "user114", contractNo, amount);

                Map<String, Object> settleMap = new HashMap<>();
                settleMap.put("transferRespCode", respCode);
                settleMap.put("investId", investId);

                if (!respCode.equalsIgnoreCase("0000")){
                    productStatus = Constant.PRODUCT_STATUS_EXCEPTION;  // 产品未能全部转账成功，产品状态设置为异常，需人为干预。
                }
                settles.add(settleMap);
            }
        }

        // 根据转账返回结果，修改用户投资转账返回码
        if (settles.size() > 0){
            productMapper.updateUserInvestmentTransferCode(settles);
        }

        // 修改产品状态
        productMapper.updateProductStatus(productId, productStatus);
    }

    @Override
    public Integer updateProductStatus(Integer productId, Integer productStatus) {
        return productMapper.updateProductStatus(productId, productStatus);
    }

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

    /**
     *  投资
     *
     * @param productId
     * @param userId
     * @param mobile
     * @param amount
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = false, rollbackFor=Exception.class)
    public int updateProductUserAmount(Integer productId, Integer userId, String mobile, Long amount)
        throws Exception{
        LoanProduct product = productMapper.getProduct(productId);
        if (product == null){
            logger.warn("product " + productId + "is not found!");
            return 0;
        }

        if (product.isNotReleaseStatus()){
            logger.warn("product status [" + product.getProductStatus() + "] is not release!");
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
                investment.setInvestStatus(Constant.USER_INVEST_STATUS_UNDUE); // 未到期
                investment.setPaySeqno(seqno);
                investment.setPayContractNo(resp.getContract_no());
                investment.setPayResponseCode("0000");
                investment.setTransferResponseCode("9999");

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
