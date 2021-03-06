package com.xeehoo.p2p.po;

import com.xeehoo.p2p.util.Constant;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/16.
 */
public class LoanProduct {
    private Integer productId;
    private String productName; // 产品名称
    private String productType; // 产品类型
    private BigDecimal loanRate; // 年化收益率
    //    private Date valueDate;  //起息日期
    private String investDay;  //投资期限 1M
    private BigDecimal totalAmount;  //总额度
    private BigDecimal residualAmount; //剩余金额
    private String incomeMode;  //还款方式（到期分配，按月付息）
    private String interestWay; //起息方式
    private String productDesc;  //产品简介
    private String loanPurpose; //借款用途
    private Integer raiseDays; // 募集周期
    private String riskControl; //风控措施
    private String riskCue;  //风险提示
    //    private String bankCode;  //收款银行
//    private String bankAccountNo;  //收款银行账户
    private BigDecimal minAmount;  //最小投资额度
    private BigDecimal minAddAmount; //最小增加额度
    private BigDecimal maxAmount; //最大投资额度
    private Date releaseTime; //发布时间
    private Integer staffId;  //发布人
    private Integer productStatus;  //产品状态 1 录入  2 发布  3 满标结算

    private String investDayValue;
    private String investDayUnitName;
    private String investDayUnit;
    private String investDayName;

    public String getInvestDayUnitName(){
        Map<String, String> map = new HashMap<String, String>(4);
        map.put("D", "天");
        map.put("W", "周");
        map.put("M", "个月");
        map.put("Y", "年");

        String unit = getInvestDayUnit();
        return map.get(unit);
    }

    public String getInvestDayValue(){
        return investDay.substring(0, this.investDay.length() - 1);
    }

    public String getInvestDayUnit(){
        return  investDay.substring(this.investDay.length() - 1);
    }

    public String getInvestDayName(){
        if (this.investDay == null)
            return "-";

        String unitName = getInvestDayUnitName(); // this.investDay.substring(this.investDay.length() - 1);
        String val = getInvestDayValue(); //this.investDay.substring(0, this.investDay.length() - 1);

        return val + unitName;
    }

    /**
     * 检查产品状态是否为s
     *
     * @param s
     * @return
     */
    public boolean checkStatus(int s){
        return (this.productStatus != null && this.productStatus.intValue() == s);
    }

    /**
     * 检查产品状态是否不为s
     *
     * @param s
     * @return
     */
    public boolean checkNotStatus(int s){
        return (this.productStatus != null && !(this.productStatus.intValue() == s));
    }

    /**
     * 产品状态不是发布状态
     * @return
     */
    public boolean isNotReleaseStatus(){
        return checkNotStatus(Constant.PRODUCT_STATUS_RELEASE);
    }

    /**
     * 产品状态是发布状态
     * @return
     */
    public boolean isRelease(){
        return checkStatus(Constant.PRODUCT_STATUS_RELEASE);
    }

    /**
     * 产品为录入状态
     *
     * @return
     */
    public boolean isInputStatus(){
        return checkStatus(Constant.PRODUCT_STATUS_INPUT);
    }

    /**
     * 是否可以结算
     *
     * @return
     */
    public boolean isSettle(){
        return (checkStatus(Constant.PRODUCT_STATUS_RELEASE) && this.residualAmount.longValue() == 0L);
    }

    public String getInterestWay() {
        return interestWay;
    }

    public void setInterestWay(String interestWay) {
        this.interestWay = interestWay;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public Integer getRaiseDays() {
        return raiseDays;
    }

    public void setRaiseDays(Integer raiseDays) {
        this.raiseDays = raiseDays;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

//    public Date getValueDate() {
//        return valueDate;
//    }
//
//    public void setValueDate(Date valueDate) {
//        this.valueDate = valueDate;
//    }

    public String getInvestDay() {
        return investDay;
    }

    public void setInvestDay(String investDay) {
        this.investDay = investDay;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(BigDecimal residualAmount) {
        this.residualAmount = residualAmount;
    }

    public String getIncomeMode() {
        return incomeMode;
    }

    public void setIncomeMode(String incomeMode) {
        this.incomeMode = incomeMode;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getRiskControl() {
        return riskControl;
    }

    public void setRiskControl(String riskControl) {
        this.riskControl = riskControl;
    }

    public String getRiskCue() {
        return riskCue;
    }

    public void setRiskCue(String riskCue) {
        this.riskCue = riskCue;
    }

//    public String getBankCode() {
//        return bankCode;
//    }
//
//    public void setBankCode(String bankCode) {
//        this.bankCode = bankCode;
//    }
//
//    public String getBankAccountNo() {
//        return bankAccountNo;
//    }
//
//    public void setBankAccountNo(String bankAccountNo) {
//        this.bankAccountNo = bankAccountNo;
//    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMinAddAmount() {
        return minAddAmount;
    }

    public void setMinAddAmount(BigDecimal minAddAmount) {
        this.minAddAmount = minAddAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
}