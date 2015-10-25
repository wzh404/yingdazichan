package com.xeehoo.p2p.po;

import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.EncryptUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * Created by wangzunhui on 2015/9/28.
 */
public class LoanUser implements Serializable {
    private Integer userId;
    private String realName;
    private String loginName;
    private String payPwd;  //支付密码
    private String loginPwd;

    private String sex;
    private String birth;
    private String education;
    private String marriage;
    private Double income;
    private String email;
    private String mobile;

    private String idType;  // 证件类型
    private String idCard;  // 证件号码
    private String idCardUp;    // 证件正面照片文件
    private String idCardDown;  // 证件背面照片文件

    private Integer currentPoint;  //当前积分

    private String cityCode;
    private String address;
    private String zipcode;

    private String securityMessage;  //安全信息
    private String securityQa1;
    private String securityQa2;

    private Date registerTime;
    private String registerIP;
    private Integer userStatus;
    private String inviteCode;

    public String getRegisterIP() {
        return registerIP;
    }

    public void setRegisterIP(String registerIP) {
        this.registerIP = registerIP;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardUp() {
        return idCardUp;
    }

    public void setIdCardUp(String idCardUp) {
        this.idCardUp = idCardUp;
    }

    public String getIdCardDown() {
        return idCardDown;
    }

    public void setIdCardDown(String idCardDown) {
        this.idCardDown = idCardDown;
    }

    public Integer getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Integer currentPoint) {
        this.currentPoint = currentPoint;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSecurityMessage() {
        return securityMessage;
    }

    public void setSecurityMessage(String securityMessage) {
        this.securityMessage = securityMessage;
    }

    public String getSecurityQa1() {
        return securityQa1;
    }

    public void setSecurityQa1(String securityQa1) {
        this.securityQa1 = securityQa1;
    }

    public String getSecurityQa2() {
        return securityQa2;
    }

    public void setSecurityQa2(String securityQa2) {
        this.securityQa2 = securityQa2;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /** other function **/
    public String encryptPwd(String pwd){
        return EncryptUtil.encryptPwd(pwd, registerTime, registerIP);
    }

    public boolean isNormal(){
        return (userStatus & Constant.USER_STATUS_NORMAL) == 1;
    }

    public boolean isEqualPwd(String pwd){
        return encryptPwd(pwd).equalsIgnoreCase(loginPwd);
    }
}
