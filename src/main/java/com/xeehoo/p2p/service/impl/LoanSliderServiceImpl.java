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
@Service
public class LoanSliderServiceImpl implements LoanSliderService {
    @Autowired
    SliderMapper sliderMapper;

    @Override
    public List<LoanSlider> getSliders() {
        List<LoanSlider> sliders = sliderMapper.getSliders();
        return sliders;
    }
}
