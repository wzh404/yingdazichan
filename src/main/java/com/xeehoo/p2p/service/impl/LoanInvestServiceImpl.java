package com.xeehoo.p2p.service.impl;

import com.fuiou.data.*;
import com.fuiou.service.FuiouService;
import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.mybatis.mapper.RepayMapper;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanUserInvestment;
import com.xeehoo.p2p.po.LoanUserRepay;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wangzunhui on 2015/10/16.
 */
@Service("investService")
public class LoanInvestServiceImpl implements LoanInvestService {
    private final Logger logger = Logger.getLogger(LoanInvestServiceImpl.class);

    @Autowired
    private Environment environment;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    RepayMapper repayMapper;

    /**
     * 6.转账(商户与个人之间)
     *
     * @param outCustNo
     * @return
     */
    private CommonRspData transferBmu(String outCustNo, String inCustNo, String contractNo, String amt) {
        TransferBmuReqData data = new TransferBmuReqData();
        String seqno = CommonUtil.getMchntTxnSsn();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(seqno); // 流水号
        data.setOut_cust_no(outCustNo);  // 转出账号
        data.setIn_cust_no(inCustNo);   // 转入账号

        if (!StringUtils.isEmpty(contractNo)) {
            data.setContract_no(contractNo); //预授权号
        }
        data.setAmt(amt); // 划拨金额
        data.setRem("test"); // 备注

        try {
            CommonRspData rsp = FuiouService.transferBmu(data);
            logger.info(rsp.toString());

            return rsp;
        } catch (Exception e) {
            e.printStackTrace();
            CommonRspData rsp = new CommonRspData();
            rsp.setResp_code("9999");
            rsp.setMchnt_txn_ssn(seqno);
            return rsp;
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
        if (product == null || product.isNotReleaseStatus()) {
            logger.warn("product settle failed");
            return;
        }

        // 状态用户冻结资金到盈达账户 modify 2016-01-19
        List<Map<String, Object>> userInvestments = productMapper.getProductInvestments(productId);
        for (Map<String, Object> map : userInvestments) {
            String payRespCode = (String) map.get("payrespcode"); // 冻结状态
            String tranRespCode = (String) map.get("tranrespcode"); // 划拨状态
            if ("0000".equalsIgnoreCase(payRespCode) && !"0000".equalsIgnoreCase(tranRespCode)) {
                Integer investId = (Integer) map.get("investid");
                String contractNo = (String) map.get("contractno");
                String mobile = (String) map.get("mobile");
                BigDecimal amt = (BigDecimal) map.get("amount");
                String amount = (new Long(amt.longValue() * 100)).toString();
                logger.info(contractNo + " - " + mobile + " - " + amount);
                CommonRspData rsp = transferBmu(mobile, "user114", contractNo, amount);

                Map<String, Object> settleMap = new HashMap<>();
                String respCode = rsp.getResp_code();
                settleMap.put("transferRespCode", respCode);
                settleMap.put("investId", investId);
                settleMap.put("transferSeqno", rsp.getMchnt_txn_ssn());

                if (!respCode.equalsIgnoreCase("0000")) {
                    productStatus = Constant.PRODUCT_STATUS_EXCEPTION;  // 产品未能全部转账成功，产品状态设置为异常，需人为干预。
                }
                settles.add(settleMap);
            }
        }

        // 根据转账返回结果，修改用户投资转账返回码
        if (settles.size() > 0) {
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
    public List<Map<String, Object>> getAppProduct(Integer productId) {
        return productMapper.getAppProduct(productId);
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

    /**
     * 预授权业务
     *
     * @param outCustNo
     * @param inCustNo
     * @param seqno
     * @param amt
     * @return
     */
    private PreAuthRspData preAuth(String outCustNo, String inCustNo, String seqno, String amt) {
        PreAuthReqData data = new PreAuthReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(seqno); // 流水号
        data.setOut_cust_no(outCustNo);  // 出账账号
        data.setIn_cust_no(inCustNo); // 入账账户
        data.setAmt(amt); // 冻结金额
        data.setRem("test"); // 备注

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
     * @param investment
     * @param product
     * @return
     */
    private LoanUserRepay createUserRepay(LoanUserInvestment investment, LoanProduct product) {
        LoanUserRepay userRepay = new LoanUserRepay();
        // 本金
        userRepay.setAmount(investment.getInvestAmount());

        // 利息
        long days = InterestUtil.calculateIntervals(investment.getInvestStartDate(), investment.getInvestClosingDate());
        userRepay.setInterest(InterestUtil.calculateInterest(investment.getInvestAmount(), product.getLoanRate(), days));

        userRepay.setUserId(investment.getUserId());
        userRepay.setInvestId(investment.getInvestId());
        userRepay.setRepayTime(investment.getInvestClosingDate());
        userRepay.setProductId(investment.getProductId());

        return userRepay;
    }

    /**
     * 投资人投标
     *
     * @param productId
     * @param userId
     * @param mobile
     * @param amount
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int updateProductUserAmount(Integer productId, Integer userId, String mobile, Long amount)
            throws Exception {
        LoanProduct product = productMapper.getProduct(productId);
        if (product == null) { //  产品不存在
            logger.warn("product " + productId + "is not found!");
            return 0;
        }

        // 产品不是发布状态
        if (product.isNotReleaseStatus()) {
            logger.warn("product status [" + product.getProductStatus() + "] is not release!");
            return 0;
        }

        // 1. 减少产品融资金额
        BigDecimal amt = new BigDecimal(amount / 100.0);
        int result = productMapper.updateProductAmount(productId, amt);
        if (result <= 0) {
            logger.warn("amount is not enough");
            return 0;
        }

        // 2. 用户投资项目
        LoanUserInvestment investment = new LoanUserInvestment();
        investment.setProductId(productId);
        investment.setProductName(product.getProductName());
        investment.setUserId(userId);
        investment.setInvestAmount(amt);
        investment.setInvestTime(new Date());
        investment.setInvestStartDate(InterestUtil.tomorrow()); // 计息日期为次日
        investment.setInvestClosingDate(
                InterestUtil.calculateInvestClosingDate(investment.getInvestStartDate(), product.getInvestDay()));
        investment.setInvestIncome(new BigDecimal(0.00));
        investment.setInvestServiceCharge(new BigDecimal(0.00));
        investment.setInvestStatus(Constant.USER_INVEST_STATUS_UNDUE); // 未到期
        String seqno = CommonUtil.getMchntTxnSsn();
        investment.setPaySeqno(seqno);
        investment.setPayResponseCode("9999");
        investment.setTransferResponseCode("9999");
        Integer rows = productMapper.saveUserInvestment(investment);
        if (rows <= 0) {
            throw new Exception("saveUserInvestment failed"); // database rollback
        }

        // 3. 还款计划
        List<LoanUserRepay> userRepays = new ArrayList<LoanUserRepay>();
        LoanUserRepay userRepay = createUserRepay(investment, product);
        userRepay.setInvestId(investment.getInvestId());
        userRepay.setMobile(mobile);
        userRepays.add(userRepay);
        rows = repayMapper.saveUserRepays(userRepays);
        if (rows <= 0) {
            throw new Exception("saveUserRepays error"); // database rollback
        }

        // 4. 第三方支付预授权
        PreAuthRspData resp = preAuth(mobile, "user114", seqno, amount.toString());
        if (resp != null && "0000".equalsIgnoreCase(resp.getResp_code())) { // 支付成功
            productMapper.updateUserInvestmentPay(investment.getInvestId(), resp.getContract_no(), resp.getResp_code());
        }else{ // 支付失败， 回滚
            throw new Exception("pay error"); // database rollback
        }

        return 1; // OK
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

//    @Override
//    public List<Map<String, Object>> getProductInvestments(Integer productId) {
//        return productMapper.getProductInvestments(productId);
//    }

    @Override
    public LoanPagedListHolder getProductInvestmentPager(int page, QueryCondition cond) {
        return new QueryPager<Map<String, Object>>(page, cond) {
            @Override
            public Integer total(QueryCondition cond) {
                return productMapper.getTotalProductInvestment(cond.getCond());
            }

            @Override
            public List<Map<String, Object>> elements(int page, QueryCondition cond) {
                return productMapper.getProductInvestmentPager(cond.getCond());
            }
        }.query();
    }

    @Override
    public LoanPagedListHolder getUserInvestments(int page, QueryCondition cond) {
        return new QueryPager<LoanUserInvestment>(page, cond) {
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
