package com.xeehoo.p2p.po;

import java.util.Date;

/**
 * Created by wangzunhui on 2015/10/14.
 */
public class LoanBulletin {
    private Integer bulletinId;
    private String bulletinTitle;
    private String bulletinUrl;
    private String bulletinType;
    private Integer bulletinStaff;
    private Integer bulletinStatus;
    private Date bulletinDate;

    public Integer getBulletinStatus() {
        return bulletinStatus;
    }

    public void setBulletinStatus(Integer bulletinStatus) {
        this.bulletinStatus = bulletinStatus;
    }

    public Integer getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(Integer bulletinId) {
        this.bulletinId = bulletinId;
    }

    public String getBulletinTitle() {
        return bulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        this.bulletinTitle = bulletinTitle;
    }

    public String getBulletinUrl() {
        return bulletinUrl;
    }

    public void setBulletinUrl(String bulletinUrl) {
        this.bulletinUrl = bulletinUrl;
    }

    public String getBulletinType() {
        return bulletinType;
    }

    public void setBulletinType(String bulletinType) {
        this.bulletinType = bulletinType;
    }

    public Integer getBulletinStaff() {
        return bulletinStaff;
    }

    public void setBulletinStaff(Integer bulletinStaff) {
        this.bulletinStaff = bulletinStaff;
    }

    public Date getBulletinDate() {
        return bulletinDate;
    }

    public void setBulletinDate(Date bulletinDate) {
        this.bulletinDate = bulletinDate;
    }
}
