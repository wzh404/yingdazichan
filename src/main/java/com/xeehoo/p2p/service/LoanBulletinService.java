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
     *
     * @param bulletinId
     * @return
     */
    public LoanBulletin getBulletin(Integer bulletinId);

    /**
     * 分页查询公告板
     *
     * @param page
     * @param cond
     * @return
     */
    public LoanPagedListHolder getBulletinPager(int page, QueryCondition cond);

    /**
     * 新增公告
     *
     * @param bulletin
     * @return
     */
    public Integer saveBulletin(LoanBulletin bulletin);

    /**
     * 修改公告
     *
     * @param bulletin
     * @return
     */
    public Integer updateBulletin(LoanBulletin bulletin);

    /**
     * 修改公告状态
     *
     * @param bulletinId
     * @param bulletinStatus
     * @return
     */
    public Integer changeBulletinStatus(Integer bulletinId, Integer bulletinStatus);

}
