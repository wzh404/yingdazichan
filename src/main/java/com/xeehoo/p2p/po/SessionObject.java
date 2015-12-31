package com.xeehoo.p2p.po;


import java.io.Serializable;

/**
 * Created by wangzunhui on 2015/9/29.
 */
public class SessionObject implements Serializable {
    private Integer userID;
    private String loginName;
    private String escrowAccount;
    private String host;
    private String token;

    public String getEscrowAccount() {
        return escrowAccount;
    }

    public void setEscrowAccount(String escrowAccount) {
        this.escrowAccount = escrowAccount;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
