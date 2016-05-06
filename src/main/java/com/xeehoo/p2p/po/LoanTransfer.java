package com.xeehoo.p2p.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 债权转让
 * Created by wangzunhui on 2016/5/5.
 */
public class LoanTransfer {
    private Integer transferId;
    private Integer productId;
    private Integer investId;
    private String transferOutUser; // 转让用户
    private String transferInUser;  // 购买用户
    private BigDecimal transferAmount; // 转让金额
    private BigDecimal transferFee; // 转让手续费
    private BigDecimal transferDiscount; // 折扣
    private Date transferTime; // 转让发布时间
    private Date transferPayTime; // 转让交易时间
    private String transferStatus; // 转让状态

    private String  productName;  // 产品名称
    private Integer rate;  // 原年化率
    private BigDecimal investAmount; // 原投资金额
    private Date investStartDate;//原开始日期
    private Date investCloseDate; // 原终止日期

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public String getTransferOutUser() {
        return transferOutUser;
    }

    public void setTransferOutUser(String transferOutUser) {
        this.transferOutUser = transferOutUser;
    }

    public String getTransferInUser() {
        return transferInUser;
    }

    public void setTransferInUser(String transferInUser) {
        this.transferInUser = transferInUser;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public BigDecimal getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    public BigDecimal getTransferDiscount() {
        return transferDiscount;
    }

    public void setTransferDiscount(BigDecimal transferDiscount) {
        this.transferDiscount = transferDiscount;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public Date getTransferPayTime() {
        return transferPayTime;
    }

    public void setTransferPayTime(Date transferPayTime) {
        this.transferPayTime = transferPayTime;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public Date getInvestStartDate() {
        return investStartDate;
    }

    public void setInvestStartDate(Date investStartDate) {
        this.investStartDate = investStartDate;
    }

    public Date getInvestCloseDate() {
        return investCloseDate;
    }

    public void setInvestCloseDate(Date investCloseDate) {
        this.investCloseDate = investCloseDate;
    }
}
