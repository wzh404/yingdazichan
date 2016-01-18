package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanDict;
import com.xeehoo.p2p.po.LoanDict1;
import org.apache.ibatis.annotations.Param;

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

    /**
     *
     * @return
     */
    public List<LoanDict> getAllDict();

    /**
     *
     * @param listDict1
     * @return
     */
    public Integer removeDict1(@Param("listDict1")List<Map<String, Integer>> listDict1);

    /**
     *
     * @param dict1
     * @return
     */
    public Integer updateDict1(LoanDict1 dict1);

    /**
     *
     * @param dict1
     * @return
     */
    public Integer saveDict1(LoanDict1 dict1);
}
