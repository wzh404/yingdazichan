package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanDict1;

import java.util.List;

/**
 * Created by wangzunhui on 2015/10/16.
 */
public interface LoanDictService {
    /**
     *
     * @param dictCode
     * @return
     */
    public List<LoanDict1> getAllDict1(String dictCode);

    /**
     *
     * @param dict1Code
     * @return
     */
    public LoanDict1 getDict1(String dict1Code);
}
