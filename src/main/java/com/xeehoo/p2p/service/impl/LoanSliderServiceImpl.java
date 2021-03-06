package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.SliderMapper;
import com.xeehoo.p2p.po.LoanSlider;
import com.xeehoo.p2p.service.LoanSliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangzunhui on 2015/10/29.
 */
@Service("sliderService")
public class LoanSliderServiceImpl implements LoanSliderService {
    @Autowired
    SliderMapper sliderMapper;

    @Override
    public List<LoanSlider> getSliders(Integer sliderStatus) {
        List<LoanSlider> sliders = sliderMapper.getSliders(sliderStatus);
        return sliders;
    }

    @Override
    public Integer updateSliderStatus(Integer sliderId, Integer sliderStatus) {
        return sliderMapper.updateSliderStatus(sliderId, sliderStatus);
    }

    @Override
    public Integer updateSlider(LoanSlider slider) {
        return sliderMapper.updateSlider(slider);
    }
}
