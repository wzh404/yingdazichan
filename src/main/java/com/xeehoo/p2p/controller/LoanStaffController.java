package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.annotation.Permission;
import com.xeehoo.p2p.po.LoanBulletin;
import com.xeehoo.p2p.po.LoanRole;
import com.xeehoo.p2p.po.LoanStaff;
import com.xeehoo.p2p.po.StaffSessionObject;
import com.xeehoo.p2p.service.LoanPermissionService;
import com.xeehoo.p2p.service.LoanStaffService;
import com.xeehoo.p2p.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/11/3.
 */
@Controller
public class LoanStaffController {
    private final Logger logger = Logger.getLogger(LoanStaffController.class);

    @Autowired
    private LoanStaffService staffService;

    @Autowired
    private LoanPermissionService permissionService;

    /**
     * 登录页
     *
     * @return
     */
    @RequestMapping(value = "/staff", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView execute() {
        return new ModelAndView("/admin/index");
    }

    /**
     * 登录页
     *
     * @return
     */
    @RequestMapping(value = "/admin/home", method = {RequestMethod.POST, RequestMethod.GET})
    @Permission("0000")
    public ModelAndView home(HttpServletRequest request) {
        HttpSession session = request.getSession();
        StaffSessionObject sso = (StaffSessionObject) session.getAttribute("staff");
        if (sso == null) {
            return new ModelAndView("/admin/index");
        }
        LoanStaff staff = staffService.getStaff(sso.getStaffId());
        return new ModelAndView("/admin/home", "loanStaff", staff);
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "/admin/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return new ModelAndView("/admin/index");
    }

    /**
     * 员工登录
     *
     * @param request
     * @param response
     * @param staffName
     * @param staffPwd
     * @return
     */
    @RequestMapping(value = "/admin/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "staff_name", required = true) String staffName,
                              @RequestParam(value = "staff_pwd", required = true) String staffPwd) {
        Map<String, Object> map = staffService.staffLogin(staffName, staffPwd);
        String resultCode = (String) map.get("resultCode");
        if (Constant.RESULT_ERROR.equalsIgnoreCase(resultCode)) {
            return new ModelAndView("/admin/index", map);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("staff", map.get("staff"));
//        session.setAttribute("permission", map.get("perm"));
//        session.setAttribute("menu", map.get("menu"));

        return new ModelAndView("/admin/home");
    }

    /**
     * 修改员工状态
     *
     * @param request
     * @param staffId
     * @param staffStatus
     * @return
     */
    @RequestMapping(value = "/admin/changeStaffStatus", method = {RequestMethod.POST, RequestMethod.GET})
    @Permission("0101")
    public ModelAndView changeStaffStatus(HttpServletRequest request,
                                     @RequestParam(value = "staff_id", required = true) Integer staffId,
                                     @RequestParam(value = "staff_status", required = true) Integer staffStatus) {
        staffService.changeStaffStatus(staffId, staffStatus);
        return new ModelAndView("redirect:/admin/listStaff");
    }

    /**
     * 修改员工密码
     *
     * @param request
     * @param staffOldPwd
     * @param staffPwd
     * @return
     */
    @RequestMapping(value = "/admin/changeStaffPwd", method = {RequestMethod.POST, RequestMethod.GET})
    @Permission("0101")
    public ModelAndView changeStaffPwd(HttpServletRequest request,
                                  @RequestParam(value = "staff_old_pwd", required = true) String staffOldPwd,
                                  @RequestParam(value = "staff_new_pwd", required = true) String staffPwd) {
        HttpSession session = request.getSession();
        StaffSessionObject sso = (StaffSessionObject) session.getAttribute("staff");
        if (sso == null) {
            return new ModelAndView("/admin/index");
        }
        LoanStaff staff = staffService.getStaff(sso.getStaffId());
        if (!staff.isEqualPwd(staffOldPwd)) {
            return new ModelAndView("/admin/change_staff_pwd", "error_message", "旧密码不正确");
        }

        staffService.changeStaffPwd(sso.getStaffId(), staff.encryptPwd(staffPwd));
        return new ModelAndView("redirect:/admin/home");
    }

    /**
     *  分页显示员工列表
     *
     * @param request
     * @param staffStatus
     * @param staffName
     * @param staffRole
     * @param page
     * @return
     */
    @RequestMapping(value = "/admin/listStaff", method = {RequestMethod.POST, RequestMethod.GET})
    @Permission("0101")
    public ModelAndView listStaff(HttpServletRequest request,
                                  @RequestParam(value = "stat", required = false) Integer staffStatus,
                                  @RequestParam(value = "name", required = false) String staffName,
                                  @RequestParam(value = "role", required = false) String staffRole,
                                  @RequestParam(value = "page", required = false) Integer page) {
        if (page == null) page = 0;
        if (staffStatus == null) staffStatus = 0;
        if (staffRole == null) staffRole = "00";

        List<LoanRole> roles = permissionService.getRoles();
        ModelAndView mav = new ModelAndView("/admin/list_staff");
        mav.addObject("roles", roles);

        QueryHolder queryHolder = new QueryHolder<LoanBulletin>(page, mav) {
            @Override
            public LoanPagedListHolder getPagedListHolder(int page, QueryCondition queryCondition) {
                return staffService.getStaffPager(page, queryCondition);
            }
        };
        queryHolder.put("role", staffRole);
        queryHolder.put("stat", staffStatus);
        if (staffName != null) {
            queryHolder.put("name", staffName);
        }
        queryHolder.query(request.getRequestURI());

        return mav;
    }

    @RequestMapping(value="/admin/editStaff")
    @Permission("0201")
    public ModelAndView editStaff(HttpServletRequest request,
                                  @RequestParam(value = "staff_id", required = false) Integer staffId){
        List<LoanRole> roles = permissionService.getRoles();
        ModelAndView mav = new ModelAndView("/admin/edit_staff");
        mav.addObject("roles", roles);

        if (staffId != null && staffId > 0){
            logger.info("staffId is " + staffId);
            LoanStaff staff = staffService.getStaff(staffId);
            mav.addObject("loanStaff", staff);
        }
        else{
            LoanStaff staff  = new LoanStaff();
            staff.setStaffId(0);
            mav.addObject("loanStaff", staff);
        }

        return mav;
    }

    @RequestMapping(value="/admin/saveStaff")
    @Permission("0201")
    public ModelAndView saveStaff(HttpServletRequest request,
                                  RedirectAttributes attr,
                                  @ModelAttribute("staff")LoanStaff staff){

        if (staff.getStaffId() == null ||  staff.getStaffId() != 0){
            logger.warn("staff id is not null");
            return new ModelAndView("redirect:/error");
        }

        staff.setStaffStatus(1);
        staff.setStaffRegtime(new Date());
        staff.setStaffPwd(staff.encryptPwd(staff.getStaffPwd()));
        Integer rows = staffService.saveStaff(staff);
        if (rows < 1){
            ModelAndView mav = new ModelAndView("/admin/edit_bulletin");
            mav.addObject("staff", staff);
            mav.addObject("error_message", "保存失败");
            return mav;
        }
        else{
            ModelAndView mav = new ModelAndView("redirect:/admin/editStaff");
            attr.addAttribute("staff_id", staff.getStaffId());
            return mav;
        }
    }

    @RequestMapping(value="/admin/updateStaff")
    @Permission("0201")
    public ModelAndView updateStaff(HttpServletRequest request,
                                  RedirectAttributes attr,
                                  @ModelAttribute("staff")LoanStaff staff){
        ModelAndView mav = new ModelAndView("redirect:/admin/editStaff");
        if (staff.getStaffId() == null || staff.getStaffId() <= 0){
            logger.warn("staff id is null or is 0.");
            attr.addAttribute("staff_id", 0);
            return mav;
        }

        Integer rows = staffService.updateStaff(staff);
        if (rows < 1){
            logger.warn("update staff failed rows is " + rows);
            attr.addAttribute("staff_id", 0);
        }
        else{
            attr.addAttribute("staff_id", staff.getStaffId());
        }
        return mav;
    }

    /* admin  ajax */
    @RequestMapping(value="/admin/ajax/checkStaff")
    @Permission("0201")
    @ResponseBody
    public Map<String, Object> checkStaff(HttpServletRequest request,
                                  @RequestParam(value = "name", required = false) String staffLogin){
        if (StringUtils.isEmpty(staffLogin)){
            return CommonUtil.generateJsonMap("ERROR", "非法参数");
        }

        LoanStaff loanStaff = staffService.getStaffByLogin(staffLogin);
        if (loanStaff == null || loanStaff.getStaffId() <= 0){
            return CommonUtil.generateJsonMap("ERROR", "工号不存在");
        }

        return CommonUtil.generateJsonMap("OK", "工号已存在");
    }
}
