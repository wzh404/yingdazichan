package com.xeehoo.p2p.service.impl;

import com.fuiou.data.CommonRspData;
import com.fuiou.data.TransferBmuReqData;
import com.fuiou.service.FuiouService;
import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.mybatis.mapper.RepayMapper;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanUserInvestment;
import com.xeehoo.p2p.po.LoanUserRepay;
import com.xeehoo.p2p.service.LoanRepayService;
import com.xeehoo.p2p.util.CommonUtil;
import com.xeehoo.p2p.util.InterestUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2016/1/7.
 */
@Service("repayService")
public class LoanRepayServiceImpl implements LoanRepayService {
    private final Logger logger = Logger.getLogger(LoanRepayServiceImpl.class);

    @Autowired
    private RepayMapper repayMapper;

    @Autowired
    private Environment environment;

    @Override
    public Integer repay(LoanUserRepay userRepay) {
        BigDecimal amount = userRepay.getAmount();
        BigDecimal interest = userRepay.getInterest();
        Long amt = amount.add(interest).setScale(2, BigDecimal.ROUND_HALF_UP).longValue() * 100L;
        logger.info("repay to " + userRepay.getMobile() + " - " + amt.toString());

        CommonRspData rsp = transferBmu("user114", userRepay.getMobile(), amt.toString());
        return repayMapper.updateRepayResponse(userRepay.getRepayId(), rsp.getResp_code(), rsp.getMchnt_txn_ssn());
    }

    @Override
    public void repayNow() {
        List<LoanUserRepay> userRepays = repayMapper.getUserRepayNow();
        for (LoanUserRepay userRepay : userRepays){
            repay(userRepay);
        }
    }

    /**
     * 6.转账(商户与个人之间)
     *
     * @param outCustNo 转出账户
     * @param inCustNo 转入账户
     * @param amt 转入金额
     * @return
     */
    private CommonRspData transferBmu(String outCustNo, String inCustNo, String amt) {
        TransferBmuReqData data = new TransferBmuReqData();
        String seqno = CommonUtil.getMchntTxnSsn();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(seqno); // 流水号
        data.setOut_cust_no(outCustNo);  // 转出账号
        data.setIn_cust_no(inCustNo);   // 转入账号
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
}
