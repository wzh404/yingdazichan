package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.RepayMapper;
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

    @Override
    public void repay(Integer productId) {

    }

    @Override
    public List<LoanUserRepay> getUserRepays(Integer investId) {
        return repayMapper.getUserRepays(investId);
    }

    @Override
    public List<LoanProductRepay> getProductRepay(Integer productId) {
        return repayMapper.getProductRepay(productId);
    }
}
