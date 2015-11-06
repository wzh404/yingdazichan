package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public LoanPagedListHolder getProductPager(int page, Map<String, Object> cond) {
        Integer totalSize = getTotalProduct(cond);
        List<LoanProduct> products = getInvestProductPager(page, Constant.PAGE_DEFAULT_SIZE, cond);
        LoanPagedListHolder pagedListHolder = new LoanPagedListHolder();
        pagedListHolder.setSource(products);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(Constant.PAGE_DEFAULT_SIZE);
        pagedListHolder.setTotalSize(totalSize);
        pagedListHolder.setMaxLinkedPages(Constant.PAGE_MAX_LINKED_PAGES);

        return pagedListHolder;
    }
}
