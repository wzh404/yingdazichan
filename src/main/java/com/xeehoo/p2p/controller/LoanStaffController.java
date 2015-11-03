package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.service.LoanStaffService;
import com.xeehoo.p2p.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/11/3.
 */
@Controller
public class LoanStaffController {
    private final Logger logger = Logger.getLogger(LoanStaffController.class);

    @Autowired
    private LoanStaffService staffService;

    @RequestMapping(value="/admin")
    public ModelAndView execute(){
        return new ModelAndView("/admin/index");
    }

    @RequestMapping(value = "/admin/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(value = "staff_name", required = true) String staffName,
                      @RequestParam(value = "staff_pwd", required = true) String staffPwd){
        Map<String, Object> map = staffService.staffLogin(staffName, staffPwd);
        String resultCode = (String)map.get("resultCode");
        if (Constant.RESULT_ERROR.equalsIgnoreCase(resultCode)){
            return new ModelAndView("/amdin/index", map);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("staff", map.get("staff"));
        session.setAttribute("permission", map.get("perm"));
        session.setAttribute("menu", map.get("menu"));

        return new ModelAndView("/admin/home");
    }
}
