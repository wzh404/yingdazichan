package com.xeehoo.p2p.po;

import com.xeehoo.p2p.util.EncryptUtil;

import java.beans.Transient;
import java.util.Date;

/**
 * Created by wangzunhui on 2015/10/31.
 */
public class LoanStaff {
    private Integer staffId;
    private String staffLogin;
    private String staffName;
    private String staffPwd;
    private Integer staffStatus;
    private String staffRole;
    private String staffDesc;
    private Date staffRegtime;
    private String staffSex;
    private String staffEmail;
    private String staffMobile;
    private String roleName;

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffLogin() {
        return staffLogin;
    }

    public void setStaffLogin(String staffLogin) {
        this.staffLogin = staffLogin;
    }

    public String getStaffDesc() {
        return staffDesc;
    }

    public void setStaffDesc(String staffDesc) {
        this.staffDesc = staffDesc;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getStaffRegtime() {
        return staffRegtime;
    }

    public void setStaffRegtime(Date staffRegtime) {
        this.staffRegtime = staffRegtime;
    }

    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex;
    }

    public String getStaffMobile() {
        return staffMobile;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPwd() {
        return staffPwd;
    }

    public void setStaffPwd(String staffPwd) {
        this.staffPwd = staffPwd;
    }

    public Integer getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(Integer staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String encryptPwd(String pwd){
        return EncryptUtil.encryptPwd(pwd, staffRegtime, "0.0.0.0");
    }

    public boolean isEqualPwd(String pwd){
        return encryptPwd(pwd).equalsIgnoreCase(staffPwd);
    }
}
