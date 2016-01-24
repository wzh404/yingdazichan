package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.BulletinMapper;
import com.xeehoo.p2p.po.LoanBulletin;
import com.xeehoo.p2p.po.LoanUserInvestment;
import com.xeehoo.p2p.service.LoanBulletinService;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;
import com.xeehoo.p2p.util.QueryPager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/14.
 */
@Service("bbsService")
public class LoanBulletinServiceImpl implements LoanBulletinService {
    @Autowired
    BulletinMapper bulletinMapper;

    @Override
    public LoanBulletin getBulletin(Integer bulletinId) {
        return bulletinMapper.getBulletin(bulletinId);
    }

    @Override
    public LoanPagedListHolder getBulletinPager(int page, QueryCondition cond) {
        return new QueryPager<LoanBulletin>(page, cond) {
            @Override
            public Integer total(QueryCondition cond) {
                return bulletinMapper.getTotalBulletin();
            }

            @Override
            public List<LoanBulletin> elements(int page, QueryCondition cond) {
                return bulletinMapper.getBulletinPager(cond.getCond());
            }
        }.query();
    }

    @Override
    public Integer saveBulletin(LoanBulletin bulletin) {
        return bulletinMapper.saveBulletin(bulletin);
    }

    @Override
    public Integer updateBulletin(LoanBulletin bulletin) {
        return bulletinMapper.updateBulletin(bulletin);
    }

    @Override
    public Integer changeBulletinStatus(Integer bulletinId, Integer bulletinStatus) {
        return bulletinMapper.changeBulletinStatus(bulletinId, bulletinStatus);
    }
}
