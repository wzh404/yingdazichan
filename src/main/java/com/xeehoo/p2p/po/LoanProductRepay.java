package com.xeehoo.p2p.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangzunhui on 2016/1/7.
 */
public class LoanProductRepay {
    private Integer repayId;  // 还款记录ID
    private Integer productId;  // 产品ID
    private Date repayTime;  // 还款时间
    private BigDecimal amount; // 应还本金
    private BigDecimal interest; // 应还利息

    public Integer getRepayId() {
        return repayId;
    }

    public void setRepayId(Integer repayId) {
        this.repayId = repayId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
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
}
