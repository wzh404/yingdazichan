package com.xeehoo.p2p.po;

import java.util.Date;

/**
 * Created by wangzunhui on 2015/10/14.
 */
public class LoanBbs {
    private Integer bbsId;
    private String bbsTitle;
    private String bbsUrl;
    private String bbsType;
    private Integer bbsStaff;
    private Date bbsDate;

    public Integer getBbsId() {
        return bbsId;
    }

    public void setBbsId(Integer bbsId) {
        this.bbsId = bbsId;
    }

    public String getBbsTitle() {
        return bbsTitle;
    }

    public void setBbsTitle(String bbsTitle) {
        this.bbsTitle = bbsTitle;
    }

    public String getBbsUrl() {
        return bbsUrl;
    }

    public void setBbsUrl(String bbsUrl) {
        this.bbsUrl = bbsUrl;
    }

    public String getBbsType() {
        return bbsType;
    }

    public void setBbsType(String bbsType) {
        this.bbsType = bbsType;
    }

    public Integer getBbsStaff() {
        return bbsStaff;
    }

    public void setBbsStaff(Integer bbsStaff) {
        this.bbsStaff = bbsStaff;
    }

    public Date getBbsDate() {
        return bbsDate;
    }

    public void setBbsDate(Date bbsDate) {
        this.bbsDate = bbsDate;
    }
}
