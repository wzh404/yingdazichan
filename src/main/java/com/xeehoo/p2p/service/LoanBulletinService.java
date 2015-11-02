package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanBulletin;

import java.util.List;

/**
 * Created by wangzunhui on 2015/10/14.
 */
public interface LoanBulletinService {
    /**
     * 查询指定页的公告数据
     *
     * @param page
     * @param pageSize
     * @return
     */
    public List<LoanBulletin> getBbsPager(int page, int pageSize);

    /**
     * 获取公告板记录数
     * @return
     */
    public int getTotalBbs();
}
