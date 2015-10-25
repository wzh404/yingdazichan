package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.po.LoanBbs;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.service.LoanInvestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/16.
 */
@Service("investService")
public class LoanInvestServiceImpl implements LoanInvestService{
    private final Logger logger = Logger.getLogger(LoanInvestServiceImpl.class);

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<LoanProduct> getInvestProductPager(int page, int pageSize, Map<String, Object> cond) {
        int offset = pageSize * page;

        cond.put("pageSize", pageSize);
        cond.put("offset", offset);
        List<LoanProduct> products = productMapper.getProductPager(cond);
        return products;
    }

    @Override
    public int getTotalProduct(Map<String, Object> cond) {
        Integer total = productMapper.getTotalProduct(cond);
        return total;
    }
}
