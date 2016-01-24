package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.annotation.Permission;
import com.xeehoo.p2p.po.LoanBulletin;
import com.xeehoo.p2p.po.LoanDict1;
import com.xeehoo.p2p.po.StaffSessionObject;
import com.xeehoo.p2p.service.LoanBulletinService;
import com.xeehoo.p2p.service.LoanDictService;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;
import com.xeehoo.p2p.util.QueryHolder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzunhui on 2015/10/14.
 */
@Controller
public class LoanBulletinController {
    private final Logger logger = Logger.getLogger(LoanBulletinController.class);

    @Autowired
    private LoanBulletinService bulletinService;

    @Autowired
    private LoanDictService dictService;

    /**
     * 列表公告
     *
     * @param request
     * @param bulletinType
     * @param bulletinStatus
     * @param page
     * @return
     */
    @RequestMapping(value = "/admin/listBulletin", method = RequestMethod.GET)
    @Permission("0201")
    public ModelAndView listBulletin(HttpServletRequest request,
                               @RequestParam(value = "type", required = false) String bulletinType,
                               @RequestParam(value = "stat", required = false) Integer bulletinStatus,
                               @RequestParam(value = "page", required = false) Integer page){
        if (page == null) page = 0;
        if (bulletinType == null) bulletinType = "0000";
        if (bulletinStatus == null) bulletinStatus = 0;

        ModelAndView mav = new ModelAndView("/admin/list_bulletin");
        dictService.setDict1NameAndCode(mav, "bulletinTypes", Constant.DICT_BULLETIN_TYPE);

        QueryHolder queryHolder = new QueryHolder<LoanBulletin>(page, mav){
            @Override
            public LoanPagedListHolder getPagedListHolder(int page, QueryCondition queryCondition) {
                return bulletinService.getBulletinPager(page, queryCondition);
            }
        };
        queryHolder.put("type", bulletinType);
        queryHolder.put("stat", bulletinStatus);
        queryHolder.query(request.getRequestURI());

        return mav;
    }

    /**
     *
     * @param bulletinId
     * @return
     */
    @RequestMapping(value = "/admin/editBulletin", method = RequestMethod.GET)
    @Permission("0201")
    public ModelAndView editBulletin(@RequestParam(value = "bulletin_id", required = true) Integer bulletinId){
        ModelAndView mav = new ModelAndView("/admin/edit_bulletin");
        LoanBulletin bulletin = null;
        if (bulletinId <= 0){
            bulletin = new LoanBulletin();
            bulletin.setBulletinId(0);
        }
        else{
            bulletin = bulletinService.getBulletin(bulletinId);

        }
        mav.addObject("bulletin", bulletin);
        List<LoanDict1> dict1s = dictService.getAllDict1(Constant.DICT_BULLETIN_TYPE);
        mav.addObject("bulletinTypes", dict1s);

        return mav;
    }

    /**
     * 新增公告
     *
     * @param request
     * @param attr
     * @param bulletin
     * @return
     */
    @RequestMapping(value="/admin/saveBulletin")
    @Permission("0201")
    public ModelAndView saveBulletin(HttpServletRequest request,
                                  RedirectAttributes attr,
                                  @ModelAttribute("bulletin")LoanBulletin bulletin){
        HttpSession session = request.getSession();
        StaffSessionObject sso = (StaffSessionObject) session.getAttribute("staff");
        if (sso == null) {
            return new ModelAndView("/admin/index");
        }

        if (bulletin.getBulletinId() == null || bulletin.getBulletinId() != 0){
            return new ModelAndView("redirect:/error");
        }

        bulletin.setBulletinDate(new Date());
        bulletin.setBulletinStatus(1);
        bulletin.setBulletinStaff(sso.getStaffId());
        int rows = bulletinService.saveBulletin(bulletin);
        if (rows < 1){
            ModelAndView mav = new ModelAndView("/admin/edit_bulletin");
            mav.addObject("bulletin", bulletin);
            mav.addObject("error_message", "保存失败");
            return mav;
        }

        ModelAndView redirect = new ModelAndView("redirect:/admin/editBulletin");
        attr.addAttribute("bulletin_id", bulletin.getBulletinId());
        return redirect;
    }

    /**
     * 修改公告
     *
     * @param request
     * @param attr
     * @param bulletin
     * @return
     */
    @RequestMapping(value="/admin/updateBulletin")
    @Permission("0201")
    public ModelAndView updateBulletin(HttpServletRequest request,
                                  RedirectAttributes attr,
                                  @ModelAttribute("bulletin")LoanBulletin bulletin){
        if (bulletin.getBulletinId() == null || bulletin.getBulletinId() < 1){
            return new ModelAndView("redirect:/error");
        }

        int rows = bulletinService.updateBulletin(bulletin);
        if (rows < 1){
            ModelAndView mav = new ModelAndView("/admin/edit_bulletin");
            mav.addObject("bulletin", bulletin);
            mav.addObject("error_message", "保存失败");
            return mav;
        }

        ModelAndView redirect = new ModelAndView("redirect:/admin/editBulletin");
        attr.addAttribute("bulletin_id", bulletin.getBulletinId());
        return redirect;
    }

    /**
     * 修改公告状态
     *
     * @param request
     * @param bulletinId
     * @param bulletinStatus
     * @return
     */
    @RequestMapping(value = "/admin/changeBulletinStatus", method = {RequestMethod.POST, RequestMethod.GET})
    @Permission("0101")
    public ModelAndView changeStaffStatus(HttpServletRequest request,
                                          @RequestParam(value = "bulletin_id", required = true) Integer bulletinId,
                                          @RequestParam(value = "bulletin_status", required = true) Integer bulletinStatus) {
        bulletinService.changeBulletinStatus(bulletinId, bulletinStatus);
        return new ModelAndView("redirect:/admin/listBulletin");
    }
}
