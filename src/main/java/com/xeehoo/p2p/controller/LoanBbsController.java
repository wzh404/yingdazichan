package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.po.LoanBbs;
import com.xeehoo.p2p.service.LoanBbsService;
import com.xeehoo.p2p.util.LoanPagedListHolder;
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
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/14.
 */
@Controller
public class LoanBbsController {
    private final Logger logger = Logger.getLogger(LoanBbsController.class);

    @Autowired
    private LoanBbsService  bbsService;

    @RequestMapping(value = "/bbs", method = RequestMethod.GET)
    public ModelAndView paging(HttpServletRequest request,
                               @RequestParam(value = "page", required = true) Integer page){
        int totalSize = bbsService.getTotalBbs();
        List<LoanBbs> items = bbsService.getBbsPager(page, PagedListHolder.DEFAULT_PAGE_SIZE);
        logger.info(totalSize + ", bbs size is " + items.size());
        LoanPagedListHolder pagedListHolder = new LoanPagedListHolder();
        pagedListHolder.setSource(items);
        pagedListHolder.setPage(page);
        pagedListHolder.setTotalSize(totalSize);
        pagedListHolder.setMaxLinkedPages(5);

        ModelAndView mav = new ModelAndView("/about/bbs");
        mav.addObject("pagedListHolder", pagedListHolder);

        return mav;
    }
}
