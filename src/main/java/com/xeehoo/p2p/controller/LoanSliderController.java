package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.po.LoanSlider;
import com.xeehoo.p2p.service.LoanSliderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/29.
 */
@Controller
public class LoanSliderController {
    private final org.apache.log4j.Logger logger = Logger.getLogger(LoanSliderController.class);

    @Autowired
    LoanSliderService loanSliderService;

    @RequestMapping(value = "/cache/slide", method = RequestMethod.GET)
    @ResponseBody
    public List<LoanSlider> cacheSlide(HttpServletRequest request, HttpServletResponse response){
        logger.info("-----------------cache slide1-------------");
        List<LoanSlider> sliders = loanSliderService.getSliders();

        return sliders;
    }
}
