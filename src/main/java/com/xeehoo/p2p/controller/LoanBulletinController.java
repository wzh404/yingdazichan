package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.annotation.Permission;
import com.xeehoo.p2p.po.LoanBulletin;
import com.xeehoo.p2p.service.LoanBulletinService;
import com.xeehoo.p2p.service.LoanDictService;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;
import com.xeehoo.p2p.util.QueryHolder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/admin/bulletin", method = RequestMethod.GET)
    @Permission("0201")
    public ModelAndView paging(HttpServletRequest request,
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
}
