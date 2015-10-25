package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanBbs;
import com.xeehoo.p2p.po.LoanDict1;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/24.
 */
public interface DictMapper {
    /**
     * 根据字典代码查询字典数据
     *
     * @param dictCode  字典代码
     * @return
     */
    public List<LoanDict1> getAllDict1(String dictCode);
}
