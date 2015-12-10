package com.xeehoo.p2p.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangzunhui on 2015/12/10.
 */
public class LoanUserInvestment {
    private Integer investId;
    private Integer productId;
    private String productName;
    private Integer userId;
    private Date investStartDate;
    private Date investClosingDate;
    private BigDecimal investAmount;
    private BigDecimal investIncome;
    private BigDecimal investServiceCharge;
    private String investStatus;
    private Date investTime;
    private String paySeqno;

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getInvestStartDate() {
        return investStartDate;
    }

    public void setInvestStartDate(Date investStartDate) {
        this.investStartDate = investStartDate;
    }

    public Date getInvestClosingDate() {
        return investClosingDate;
    }

    public void setInvestClosingDate(Date investClosingDate) {
        this.investClosingDate = investClosingDate;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getInvestIncome() {
        return investIncome;
    }

    public void setInvestIncome(BigDecimal investIncome) {
        this.investIncome = investIncome;
    }

    public BigDecimal getInvestServiceCharge() {
        return investServiceCharge;
    }

    public void setInvestServiceCharge(BigDecimal investServiceCharge) {
        this.investServiceCharge = investServiceCharge;
    }

    public String getInvestStatus() {
        return investStatus;
    }

    public void setInvestStatus(String investStatus) {
        this.investStatus = investStatus;
    }

    public Date getInvestTime() {
        return investTime;
    }

    public void setInvestTime(Date investTime) {
        this.investTime = investTime;
    }

    public String getPaySeqno() {
        return paySeqno;
    }

    public void setPaySeqno(String paySeqno) {
        this.paySeqno = paySeqno;
    }
}
