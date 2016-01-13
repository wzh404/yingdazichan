package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.mybatis.mapper.RepayMapper;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanProductRepay;
import com.xeehoo.p2p.po.LoanUserRepay;
import com.xeehoo.p2p.service.LoanRepayService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangzunhui on 2016/1/7.
 */
public class LoanRepayServiceImpl implements LoanRepayService {
    @Autowired
    private RepayMapper repayMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void repay(Integer repayId) {
//        productMapper
    }

    @Override
    public List<LoanUserRepay> getUserRepays(Integer investId) {
        return repayMapper.getUserRepays(investId);
    }

    @Override
    public List<LoanProductRepay> getProductRepay(Integer productId) {
        return repayMapper.getProductRepay(productId);
    }

    @Override
    public Integer createRepayPlan(Integer productId) {
        LoanProduct product = productMapper.getProduct(productId);
        product.getInvestDay();
        return null;
    }
}
