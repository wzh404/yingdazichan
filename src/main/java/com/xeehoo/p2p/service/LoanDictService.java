package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanDict;
import com.xeehoo.p2p.po.LoanDict1;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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

    /**
     *
     * @param dict1Code
     * @return
     */
    public void setDict1NameAndCode(ModelAndView mav, String key, String dict1Code);

    /**
     *
     * @return
     */
    public List<LoanDict> getAllDict();

    /**
     * 根据ID删除1级字典
     *
     * @return
     */
    public Integer removeDict1(List<Map<String, Integer>> listDict1);


    /**
     * 修改1级字典
     *
     * @param dict1
     * @return
     */
    public Integer updateDict1(LoanDict1 dict1);

    /**
     * 新增1级字典
     *
     * @param dict1
     * @return
     */
    public Integer saveDict1(LoanDict1 dict1);
}
