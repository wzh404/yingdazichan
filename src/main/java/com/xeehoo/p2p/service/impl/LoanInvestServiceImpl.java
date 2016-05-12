package com.xeehoo.p2p.service.impl;

import com.fuiou.data.*;
import com.fuiou.service.FuiouService;
import com.xeehoo.p2p.mybatis.mapper.PayMapper;
import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.mybatis.mapper.RepayMapper;
import com.xeehoo.p2p.mybatis.mapper.TransferMapper;
import com.xeehoo.p2p.po.*;
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

    @Autowired
    PayMapper payMapper;

    @Autowired
    TransferMapper transferMapper;

    /**
     * 6.转账(商户与个人之间)
     *
     * @param outCustNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public CommonRspData transferBmu(
            String outCustNo,
            String inCustNo,
            String seqno,
            String contractNo,
            String amt) throws Exception {
        LoanPay pay = new LoanPay();
        pay.setAmount(new BigDecimal(amt).divide(new BigDecimal(100.0)).setScale(2, BigDecimal.ROUND_HALF_UP));
        pay.setInAccount(inCustNo);
        pay.setOutAccout(outCustNo);
        pay.setSeqno(seqno);
        pay.setRespCode("9999");
        pay.setPayTime(new Date());

        Integer rows = payMapper.savePay(pay);
        if (rows <= 0) {
            return null;
        }

        TransferBmuReqData data = new TransferBmuReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(seqno); // 流水号
        data.setOut_cust_no(outCustNo);  // 转出账号
        data.setIn_cust_no(inCustNo);   // 转入账号
        if (! StringUtils.isEmpty(contractNo)) {
            data.setContract_no(contractNo); //预授权号
        }
        data.setAmt(amt); // 划拨金额
        data.setRem("-"); // 备注

        try {
            CommonRspData rsp = FuiouService.transferBmu(data);
            if (rsp != null) {
                payMapper.updateRespcode(pay.getPayId(), rsp.getResp_code());
            }

            return rsp;
        } catch (Exception e) {
            return null;
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
//        List<Map<String, Object>> userInvestments = productMapper.getProductInvestments(productId);
//        for (Map<String, Object> map : userInvestments) {
//            String payRespCode = (String) map.get("payrespcode"); // 冻结状态
//            String tranRespCode = (String) map.get("tranrespcode"); // 划拨状态
//            if ("0000".equalsIgnoreCase(payRespCode) && !"0000".equalsIgnoreCase(tranRespCode)) {
//                Integer investId = (Integer) map.get("investid");
//                String contractNo = (String) map.get("contractno"); // 合同号
//                String mobile = (String) map.get("mobile");
//                BigDecimal amt = (BigDecimal) map.get("amount");
//                String amount = (new Long(amt.longValue() * 100)).toString();
//                logger.info(contractNo + " - " + mobile + " - " + amount);
//                String seqno = CommonUtil.getMchntTxnSsn();
//                CommonRspData rsp = transferBmu(mobile, "user114", seqno, contractNo, amount);
//
//                Map<String, Object> settleMap = new HashMap<>();
//                String respCode = rsp.getResp_code();
//                settleMap.put("transferRespCode", respCode);
//                settleMap.put("investId", investId);
//                settleMap.put("transferSeqno", rsp.getMchnt_txn_ssn());
//
//                if (!respCode.equalsIgnoreCase("0000")) {
//                    productStatus = Constant.PRODUCT_STATUS_EXCEPTION;  // 产品未能全部转账成功，产品状态设置为异常，需人为干预。
//                }
//                settles.add(settleMap);
//            }
//        }
//
//        // 根据转账返回结果15712898321，修改用户投资转账返回码
//        if (settles.size() > 0) {
//            productMapper.updateUserInvestmentTransferCode(settles);
//        }

        // 修改产品状态
        productMapper.updateProductStatus(productId, productStatus);
    }

    @Override
    public Integer updateProductStatus(Integer productId, Integer productStatus) {
        return productMapper.updateProductStatus(productId, productStatus);
    }

    @Override
    public Integer updateProductStatusAndDate(Integer productId, Integer productStatus, Date investStartDate, Date investCloseDate) {
        return productMapper.updateProductStatusAndDate(productId, productStatus, investStartDate, investCloseDate);
    }

    @Override
    public List<Map<String, Object>> getAppProduct(Integer productId) {
        return productMapper.getAppProduct(productId);
    }

    @Override
    public List<Map<String, Object>> getAppUserInvestment(Integer userId, Integer investId) {
        return productMapper.getAppUserInvestment(userId, investId);
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
//    private PreAuthRspData preAuth(String outCustNo, String inCustNo, String seqno, String amt) {
//        PreAuthReqData data = new PreAuthReqData();
//        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
//        data.setMchnt_txn_ssn(seqno); // 流水号
//        data.setOut_cust_no(outCustNo);  // 出账账号
//        data.setIn_cust_no(inCustNo); // 入账账户
//        data.setAmt(amt); // 冻结金额
//        data.setRem("test"); // 备注
//
//        try {
//            PreAuthRspData rsp = FuiouService.preAuth(data);
//            logger.info(rsp.toString());
//            return rsp;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

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
     * 投标
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

        // 2. 第三方支付转账
        String seqno = CommonUtil.getMchntTxnSsn();
//        CommonRspData resp = transferBmu(mobile, "user114", seqno, null, amount.toString());
//        if (resp != null && !"0000".equalsIgnoreCase(resp.getResp_code())) { // 支付成功
//            throw new Exception("pay error"); // database rollback
//        }

        // 2. 用户投资项目
        LoanUserInvestment investment = new LoanUserInvestment();
        investment.setProductId(productId);
        investment.setProductName(product.getProductName());
        investment.setUserId(userId);
        investment.setInvestAmount(amt);
        investment.setInvestTime(new Date());
        investment.setInvestStartDate(InterestUtil.tomorrow());  // 计息日期为次日
        investment.setInvestClosingDate(
                InterestUtil.calculateInvestClosingDate(investment.getInvestStartDate(), product.getInvestDay()));

        BigDecimal income = InterestUtil.calculateInterest(amt, product.getLoanRate(), InterestUtil.calculateIntervals(investment.getInvestStartDate(), investment.getInvestClosingDate()));
        investment.setInvestIncome(income);
        investment.setInvestServiceCharge(new BigDecimal(0.00));
        investment.setInvestStatus(Constant.USER_INVEST_STATUS_UNDUE); // 未到期

        investment.setPaySeqno(seqno);
        investment.setPayResponseCode("0000");
        investment.setTransferResponseCode("9999");
        investment.setTransferStatus("N");
        investment.setUserMobile(mobile);
        investment.setInvestRate(product.getLoanRate());

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
        CommonRspData resp = transferBmu(mobile, "user114", seqno, null, amount.toString());
        if (resp != null && "0000".equalsIgnoreCase(resp.getResp_code())) { // 支付成功
//            productMapper.updateUserInvestmentPay(investment.getInvestId(), "0", resp.getResp_code());
        } else { // 支付失败， 回滚
            throw new Exception("pay error"); // database rollback
        }

        return 1; // OK
    }

    /**
     * 债权转让申请
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Integer transfer(Integer investId, Integer userId, BigDecimal transferAmount) {
        LoanUserInvestment investment = productMapper.getUserInvestment(investId);
        if (investment == null) {
            logger.warn("invalid invest id!");
            return 0;
        }

        if (investment.getUserId().intValue() != userId.intValue()){
            logger.warn("invalid user id!");
            return 0;
        }

        if (! "N".equalsIgnoreCase(investment.getTransferStatus()) &&
            ! "U".equalsIgnoreCase(investment.getInvestStatus())){
            logger.warn("transfer status != N && invest status != U");
            return 0;
        }

        LoanProduct product = productMapper.getProduct(investment.getProductId());
        if (product == null) {
            logger.warn("invalid product!");
            return 0;
        }

//        BigDecimal b = investment.getInvestRate();
//        BigDecimal a = investment.getInvestAmount();
//        // closing_date - now 利息补偿天数
//        long d = InterestUtil.calculateIntervals(new Date(), investment.getInvestClosingDate());
//        BigDecimal f = a.add(
//                new BigDecimal(d / 360.0)
//                        .multiply(a)
//                        .multiply(b)
//                        .divide(new BigDecimal(100.0))
//                        .multiply(x))
//                .setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal f = investment.getInvestAmount().add(investment.getInvestIncome());
        if (transferAmount.compareTo(f) == 1){
            logger.warn("invalid transfer amount!");
            return 0;
        }

        BigDecimal discount = transferAmount.divide(f, 2, BigDecimal.ROUND_HALF_UP);

        // 手续费计算
        BigDecimal fee = f.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_HALF_UP);
        if (fee.compareTo(new BigDecimal(1.0)) == -1) { // < 1.0
            fee = new BigDecimal(1.0);
        }
        if (fee.compareTo(new BigDecimal(15.0)) == 1) { // > 15.0
            fee = new BigDecimal(15.0);
        }

        int rows = productMapper.updateUserInvestmentTransferStatus(investId);
        if (rows <= 0){
            logger.warn("update user investment transfer status failed!");
            return 0;
        }

        LoanTransfer transfer = new LoanTransfer();
        transfer.setTransferStatus(Constant.TRANSFER_STATUS_REQUEST);
        transfer.setTransferAmount(transferAmount);
        transfer.setTransferDiscount(discount);
        transfer.setTransferFee(fee);// 手续费
        transfer.setTransferInUser(0);
        transfer.setTransferOutUser(investment.getUserId());
        transfer.setTransferOutMobile(investment.getUserMobile());
        transfer.setTransferTime(new Date());
        transfer.setInvestId(investId);
        transfer.setProductId(product.getProductId());
        transfer.setProductName(product.getProductName());
        transfer.setInvestAmount(investment.getInvestAmount());
        transfer.setInvestStartDate(investment.getInvestStartDate());
        transfer.setInvestCloseDate(investment.getInvestClosingDate());
        transfer.setRate(investment.getInvestRate());
        transfer.setInvestDay(product.getInvestDay());

        rows = transferMapper.saveTransfer(transfer);
        if (rows <= 0){
            return 0;
        }
        return 1;
    }

    /**
     * 债权转让完成
     *
     * @param transferId
     * @param userId
     * @param mobile
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Integer transferComplete(Integer transferId, Integer userId, String mobile)
            throws Exception {
        LoanTransfer transfer = transferMapper.getTransfer(transferId);
        if (! "R".equalsIgnoreCase(transfer.getTransferStatus())){
            logger.info("**** transfer status != R");
            return 0;
        }

        LoanUserInvestment investment = productMapper.getUserInvestment(transfer.getInvestId());
        if (investment == null) {
            logger.info("**** investment is null");
            return 0;
        }

        if (investment.getUserId().intValue() == userId.intValue()){
            logger.info("**** investment user == transfer user");
            return 0;
        }

        String seqno = CommonUtil.getMchntTxnSsn();
        String seqno2 = CommonUtil.getMchntTxnSsn();
        while(seqno.equalsIgnoreCase(seqno2)){
            seqno2 = CommonUtil.getMchntTxnSsn();
        }

        // 1. 债权转让流水
        String transferSeqno = seqno +"," + seqno2;
        BigDecimal f = transfer.getTransferAmount();
        BigDecimal fee = transfer.getTransferFee();

        Integer rows = transferMapper.updateTransfer(transferId, userId, f, fee, transferSeqno);
        if (rows <= 0){
            logger.info("**** updateTransfer failed");
            return 0;
        }

        // 2. 设置投资人为债权接手人
        rows = productMapper.updateUserInvestmentTransfer(transfer.getInvestId(), userId, mobile);
        if (rows <= 0){
            throw new Exception(" updateUserInvestmentTransfer "); // rollback
        }

        String plat = "user114";
        // 3. 接手人 -> 平台  金额：f（转让金额）
        String sf = new Long(f.multiply(new BigDecimal(100)).longValue()).toString();
        CommonRspData resp = transferBmu(mobile, plat, seqno, null, sf);
        if (resp != null && "0000".equalsIgnoreCase(resp.getResp_code())) { // 支付成功
            // 4. 平台 -> 转让人  transferAmount（转让金额） - transferFee（手续费）
            String sfee = new Long(f.subtract(fee).multiply(new BigDecimal(100.0)).longValue()).toString();
            try{
                transferBmu(plat, transfer.getTransferOutMobile(), seqno2, null, sfee);
            }catch (Exception e){
                e.printStackTrace(); // no rollback, 平台转账失败，可以手工转
            }

            return 1;
        } else {
            throw new Exception(" transferBmu "); // rollback
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Integer cancelTransferRequest(Integer investId) throws  Exception{
        LoanUserInvestment investment = productMapper.getUserInvestment(investId);
        if (investment == null) {
            return 0;
        }

        if (! "R".equalsIgnoreCase(investment.getTransferStatus()) &&
            ! "U".equalsIgnoreCase(investment.getInvestStatus())){
            return 0;
        }

        int rows = productMapper.cancelInvestmentTransferRequest(investId);
        if (rows <= 0){
            return 0;
        }

        transferMapper.cancelTransferRequest(investId);
        if (rows <= 0){
            throw new Exception(" cancel");
        }
        return 1;
    }

    @Override
    public List<LoanTransfer> getTransfers() {
        return transferMapper.getTransfers();
    }

    @Override
    public LoanTransfer getTransfer(Integer transferId) {
        return transferMapper.getTransfer(transferId);
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
    public Integer updateProduct(LoanProduct loanProduct) {
        return productMapper.updateProduct(loanProduct);
    }

    @Override
    public LoanProduct getProduct(Integer productId) {
        return productMapper.getProduct(productId);
    }

    @Override
    public LoanUserInvestment getUserInvestment(Integer investId) {
        return productMapper.getUserInvestment(investId);
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
