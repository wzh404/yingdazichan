package com.xeehoo.p2p.service;

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
}
