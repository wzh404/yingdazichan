package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.BbsMapper;
import com.xeehoo.p2p.po.LoanBbs;
import com.xeehoo.p2p.service.LoanBbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/14.
 */
@Service("bbsService")
public class LoanBbsServiceImpl implements LoanBbsService{
    @Autowired
    BbsMapper bbsMapper;

    @Override
    public List<LoanBbs> getBbsPager(int page, int pageSize) {
        int offset = pageSize * page;

        Map<String, Object> cond = new HashMap<String, Object>();
        cond.put("pageSize", pageSize);
        cond.put("offset", offset);
        List<LoanBbs> bbs = bbsMapper.getBbsPager(cond);
        return bbs;
    }

    @Override
    public int getTotalBbs() {
        Integer total = bbsMapper.getTotalBbs();
        return total;
    }
}
