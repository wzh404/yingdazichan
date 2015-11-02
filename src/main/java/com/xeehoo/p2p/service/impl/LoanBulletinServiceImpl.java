package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.BulletinMapper;
import com.xeehoo.p2p.po.LoanBulletin;
import com.xeehoo.p2p.service.LoanBulletinService;
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
    BulletinMapper bbsMapper;

    @Override
    public List<LoanBulletin> getBbsPager(int page, int pageSize) {
        int offset = pageSize * page;

        Map<String, Object> cond = new HashMap<String, Object>();
        cond.put("pageSize", pageSize);
        cond.put("offset", offset);
        List<LoanBulletin> bbs = bbsMapper.getBulletinPager(cond);
        return bbs;
    }

    @Override
    public int getTotalBbs() {
        Integer total = bbsMapper.getTotalBulletin();
        return total;
    }
}
