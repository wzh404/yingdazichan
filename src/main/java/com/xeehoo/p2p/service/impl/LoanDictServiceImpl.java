package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.DictMapper;
import com.xeehoo.p2p.po.LoanDict;
import com.xeehoo.p2p.po.LoanDict1;
import com.xeehoo.p2p.service.LoanDictService;
import com.xeehoo.p2p.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public void setDict1NameAndCode(ModelAndView mav, String key, String dict1Code) {
        List<LoanDict1>  dict1s = getAllDict1(dict1Code);
        Map<String, Object> map = new HashMap<String, Object>();
        for (LoanDict1 dict1 : dict1s){
            map.put(dict1.getDict1Code(), dict1.getDict1Name());
        }
        mav.addObject(key, map);
    }

    @Override
    public List<LoanDict> getAllDict() {
        return dictMapper.getAllDict();
    }

    @Override
    public Integer removeDict1(List<Map<String, Integer>> listDict1) {
        return dictMapper.removeDict1(listDict1);
    }

    @Override
    public Integer updateDict1(LoanDict1 dict1) {
        return dictMapper.updateDict1(dict1);
    }

    @Override
    public Integer saveDict1(LoanDict1 dict1) {
        return dictMapper.saveDict1(dict1);
    }
}
