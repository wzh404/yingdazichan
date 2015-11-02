package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanBulletin;

import java.util.List;
import java.util.Map;

/**
 * Created by WIN10 on 2015/10/24.
 */
public interface BulletinMapper {
    /**
     * 分页查询产品信息
     *
     * @param map 查询条件及分页信息(offset, pagesize)
     * @return
     */
    public List<LoanBulletin> getBulletinPager(Map<String, Object> map);

    /**
     * 查询产品记录数
     *
     * @return
     */
    public Integer getTotalBulletin();
}
