package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanSlider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzunhui on 2015/10/29.
 */
public interface SliderMapper {
    /**
     * 获取slider列表
     *
     * @return
     */
    public List<LoanSlider> getSliders(@Param("sliderStatus")Integer sliderStatus);

    /**
     * 修改slider状态
     *
     * @param sliderId
     * @param sliderStatus
     * @return
     */
    public Integer updateSliderStatus(@Param("sliderId")Integer sliderId, @Param("sliderStatus")Integer sliderStatus);

    /**
     * 修改slider
     *
     * @param slider
     * @return
     */
    public Integer updateSlider(LoanSlider slider);
}
