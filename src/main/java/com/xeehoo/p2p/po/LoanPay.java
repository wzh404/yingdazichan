package com.xeehoo.p2p.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付流水
 *
 * Created by wangzunhui on 2016/5/5.
 */
public class LoanPay {
    private Integer payId;
    private String outAccout;
    private String inAccount;
    private BigDecimal amount;
    private String seqno;
    private String respCode;
    private Date payTime;
    private String payType;
    private Integer serviceId;

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getOutAccout() {
        return outAccout;
    }

    public void setOutAccout(String outAccout) {
        this.outAccout = outAccout;
    }

    public String getInAccount() {
        return inAccount;
    }

    public void setInAccount(String inAccount) {
        this.inAccount = inAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
