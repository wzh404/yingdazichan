package com.xeehoo.p2p.po;

import java.math.BigDecimal;

/**
 * Created by wangzunhui on 2015/10/15.
 */
public class LoanUserFund {
    private Integer userId;
    private BigDecimal totalFund; // 总资产
    private BigDecimal totalPrincipal; // 累计本金
    private BigDecimal totalEarnings; // 累计净收益
    private BigDecimal recoveryPrincipal; // 未到期本金
    private BigDecimal freezeFund; // 冻结资金

    private BigDecimal availableFund; //可用资金

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public BigDecimal getTotalPrincipal() {
        return totalPrincipal;
    }

    public void setTotalPrincipal(BigDecimal totalPrincipal) {
        this.totalPrincipal = totalPrincipal;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public BigDecimal getRecoveryPrincipal() {
        return recoveryPrincipal;
    }

    public void setRecoveryPrincipal(BigDecimal recoveryPrincipal) {
        this.recoveryPrincipal = recoveryPrincipal;
    }

    public BigDecimal getFreezeFund() {
        return freezeFund;
    }

    public void setFreezeFund(BigDecimal freezeFund) {
        this.freezeFund = freezeFund;
    }

    /**
     * 可用资产 = 总资产 - 待收回本金 ( -冻结资金 )
     * @return
     */
    public  BigDecimal getAvailableFund(){
        return this.totalFund.subtract(this.recoveryPrincipal);
    }
}
