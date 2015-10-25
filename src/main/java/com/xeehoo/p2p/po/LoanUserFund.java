package com.xeehoo.p2p.po;

import java.math.BigDecimal;

/**
 * Created by wangzunhui on 2015/10/15.
 */
public class LoanUserFund {
    private Integer userId;
    private BigDecimal totalAssets; // 总资产
    private BigDecimal notDueAmount; // 未到期金额
    private BigDecimal awaitEarnings; // 待分配收益
    private BigDecimal dynamicEarnings; // 动态净收益
    private BigDecimal totalEarnings; // 累计净收益

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getNotDueAmount() {
        return notDueAmount;
    }

    public void setNotDueAmount(BigDecimal notDueAmount) {
        this.notDueAmount = notDueAmount;
    }

    public BigDecimal getAwaitEarnings() {
        return awaitEarnings;
    }

    public void setAwaitEarnings(BigDecimal awaitEarnings) {
        this.awaitEarnings = awaitEarnings;
    }

    public BigDecimal getDynamicEarnings() {
        return dynamicEarnings;
    }

    public void setDynamicEarnings(BigDecimal dynamicEarnings) {
        this.dynamicEarnings = dynamicEarnings;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }
}
