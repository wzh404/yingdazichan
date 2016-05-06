package com.xeehoo.p2p.po;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by wangzunhui on 2015/12/10.
 */
public class LoanUserInvestment {
    private Integer investId;
    private Integer productId;
    private String productName;
    private Integer userId; // 投资用户
    private Date investStartDate; // 开始日期
    private Date investClosingDate; // 结束日期
    private BigDecimal investAmount; // 本金
    private BigDecimal investIncome; // 应收利息
    private BigDecimal investServiceCharge; // 投资手续费
    private String investStatus; // 投资状态
    private Date investTime; // 投资时间

    /**
     * @deprecated
     */
    private String paySeqno;
    private String payContractNo;
    private String payResponseCode;
    private String transferResponseCode;
    private String transferSeqno;
    private Date transferTime;
    /*--------------*/

    private String transferStatus; // 是否债权转让

    public boolean isDue(){
        return this.investStatus.equalsIgnoreCase("D");
    }

    public boolean isUnDue(){
        return this.investStatus.equalsIgnoreCase("U") && this.investClosingDate.after(new Date());
    }

    public boolean isOverDue(){
        return this.investStatus.equalsIgnoreCase("U") && this.investClosingDate.before(new Date());
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public String getTransferSeqno() {
        return transferSeqno;
    }

    public void setTransferSeqno(String transferSeqno) {
        this.transferSeqno = transferSeqno;
    }

    public String getTransferResponseCode() {
        return transferResponseCode;
    }

    public void setTransferResponseCode(String transferResponseCode) {
        this.transferResponseCode = transferResponseCode;
    }

    public String getPayResponseCode() {
        return payResponseCode;
    }

    public void setPayResponseCode(String payResponseCode) {
        this.payResponseCode = payResponseCode;
    }

    public String getPayContractNo() {
        return payContractNo;
    }

    public void setPayContractNo(String payContractNo) {
        this.payContractNo = payContractNo;
    }

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
