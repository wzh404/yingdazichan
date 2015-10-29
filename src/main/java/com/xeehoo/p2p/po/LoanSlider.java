package com.xeehoo.p2p.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by wangzunhui on 2015/10/29.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class LoanSlider {
    private Integer sliderId;
    private String sliderName;
    private String sliderUri;
    private String sliderImg;
    private Date sliderTime;
    private Integer staffId;
    private Integer sliderStatus;

    public Integer getSliderId() {
        return sliderId;
    }

    public void setSliderId(Integer sliderId) {
        this.sliderId = sliderId;
    }

    public String getSliderName() {
        return sliderName;
    }

    public void setSliderName(String sliderName) {
        this.sliderName = sliderName;
    }

    public String getSliderUri() {
        return sliderUri;
    }

    public void setSliderUri(String sliderUri) {
        this.sliderUri = sliderUri;
    }

    public String getSliderImg() {
        return sliderImg;
    }

    public void setSliderImg(String sliderImg) {
        this.sliderImg = sliderImg;
    }

    public Date getSliderTime() {
        return sliderTime;
    }

    public void setSliderTime(Date sliderTime) {
        this.sliderTime = sliderTime;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getSliderStatus() {
        return sliderStatus;
    }

    public void setSliderStatus(Integer sliderStatus) {
        this.sliderStatus = sliderStatus;
    }
}
