package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.DictMapper;
import com.xeehoo.p2p.po.LoanDict1;
import com.xeehoo.p2p.service.LoanDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangzunhui on 2015/10/16.
 */
@Service("dictService")
public class LoanDictServiceImpl implements LoanDictService {
    @Autowired
    DictMapper dictMapper;

    @Override
    public List<LoanDict1> getAllDict1(String dictCode) {
        List<LoanDict1> dict1s = dictMapper.getAllDict1(dictCode);
        return dict1s;
    }

    @Override
    public LoanDict1 getDict1(String dict1Code) {
        return null;
    }
}
