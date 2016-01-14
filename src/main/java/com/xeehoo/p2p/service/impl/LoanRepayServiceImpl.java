package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.ProductMapper;
import com.xeehoo.p2p.mybatis.mapper.RepayMapper;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanUserInvestment;
import com.xeehoo.p2p.po.LoanUserRepay;
import com.xeehoo.p2p.service.LoanRepayService;
import com.xeehoo.p2p.util.InterestUtil;
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




}
