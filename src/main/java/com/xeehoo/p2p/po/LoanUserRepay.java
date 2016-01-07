package com.xeehoo.p2p.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangzunhui on 2016/1/7.
 */
public class LoanUserRepay {
    private Integer repayId;  // 还款记录ID
    private Integer investId; // 用户投资记录ID
    private Integer userId;  // 用户ID
    private BigDecimal amount; // 应还本金
    private BigDecimal interest; // 应还利息
    private Date repayTime;  // 还款日期
    private String repaySeqno; // 支付流水
    private String repayResponseCode; // 支付应答码

    public Integer getRepayId() {
        return repayId;
    }

    public void setRepayId(Integer repayId) {
        this.repayId = repayId;
    }

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public String getRepaySeqno() {
        return repaySeqno;
    }

    public void setRepaySeqno(String repaySeqno) {
        this.repaySeqno = repaySeqno;
    }

    public String getRepayResponseCode() {
        return repayResponseCode;
    }

    public void setRepayResponseCode(String repayResponseCode) {
        this.repayResponseCode = repayResponseCode;
    }
}
