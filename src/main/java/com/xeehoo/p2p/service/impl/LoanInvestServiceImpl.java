package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanUserInvestment;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
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
    @Transactional(propagation= Propagation.REQUIRED, readOnly = false)
    public int updateProductUserAmount(Integer productId, Integer userId, BigDecimal amount) {
        LoanProduct product = productMapper.getProduct(productId);
        if (product == null){
            logger.warn("product " + productId + "is not found!");
            return 0;
        }

        int result = productMapper.updateProductAmount(productId, amount);
        if (result > 0){
            LoanUserInvestment investment = new LoanUserInvestment();

            investment.setProductId(productId);
            investment.setProductName(product.getProductName());
            investment.setUserId(userId);
            investment.setInvestAmount(amount);
            investment.setInvestTime(new Date());
            investment.setInvestStartDate(new Date());
            investment.setInvestClosingDate(new Date());
            investment.setInvestIncome(new BigDecimal(0.00));
            investment.setInvestServiceCharge(new BigDecimal(0.00));
            investment.setInvestStatus("I"); //投标
            investment.setPaySeqno("");

            result = productMapper.saveUserInvestment(investment);
            return result;
        }

        logger.warn("amount is not enough");
        return 0;
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
