package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanSlider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzunhui on 2015/10/29.
 */
public interface LoanSliderService {
    /**
     *
     * @return
     */
    public List<LoanSlider> getSliders(Integer sliderStatus);

    /**
     * 修改slider状态
     *
     * @param sliderId
     * @param sliderStatus
     * @return
     */
    public Integer updateSliderStatus(Integer sliderId, Integer sliderStatus);

    /**
     * 修改slider
     *
     * @param slider
     * @return
     */
    public Integer updateSlider(LoanSlider slider);
}
