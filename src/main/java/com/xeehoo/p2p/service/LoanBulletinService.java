package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanBulletin;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;

import java.util.List;

/**
 * Created by wangzunhui on 2015/10/14.
 */
public interface LoanBulletinService {
    /**
     * 分页查询公告板
     *
     * @param page
     * @param cond
     * @return
     */
    public LoanPagedListHolder getBulletinPager(int page, QueryCondition cond);
}
