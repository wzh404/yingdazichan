package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.annotation.Permission;
import com.xeehoo.p2p.po.LoanSlider;
import com.xeehoo.p2p.service.LoanSliderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/cache/slider", method = RequestMethod.GET)
    @ResponseBody
    public List<LoanSlider> cacheSlider(HttpServletRequest request, HttpServletResponse response){
        List<LoanSlider> sliders = loanSliderService.getSliders(2);

        return sliders;
    }

    @RequestMapping(value = "/admin/listSlider", method = RequestMethod.GET)
    @Permission("0201")
    public ModelAndView listSlider(HttpServletRequest request,
                                   @RequestParam(value = "stat", required = false) Integer sliderStatus){
        if (sliderStatus == null) sliderStatus = 0;

        List<LoanSlider> sliders = loanSliderService.getSliders(sliderStatus);
        ModelAndView mav = new ModelAndView("/admin/list_slider");
        mav.addObject("sliders", sliders);
        mav.addObject("stat", sliderStatus);

        return mav;
    }

    /**
     * 修改Slider状态
     *
     * @param request
     * @param sliderId
     * @param sliderStatus
     * @return
     */
    @RequestMapping(value = "/admin/changeSliderStatus", method = {RequestMethod.POST, RequestMethod.GET})
    @Permission("0101")
    public ModelAndView changeStaffStatus(HttpServletRequest request,
                                          @RequestParam(value = "slider_id", required = true) Integer sliderId,
                                          @RequestParam(value = "slider_status", required = true) Integer sliderStatus) {
        loanSliderService.updateSliderStatus(sliderId, sliderStatus);
        return new ModelAndView("redirect:/admin/listSlider");
    }
}
