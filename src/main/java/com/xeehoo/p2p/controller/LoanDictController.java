package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.annotation.Permission;
import com.xeehoo.p2p.po.LoanDict;
import com.xeehoo.p2p.po.LoanDict1;
import com.xeehoo.p2p.service.LoanDictService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2016/1/18.
 */
@Controller
public class LoanDictController {
    private final Logger logger = Logger.getLogger(LoanDictController.class);

    @Autowired
    private LoanDictService dictService;

    @RequestMapping(value = "/admin/listDict1", method = RequestMethod.GET)
    @Permission("0102")
    public ModelAndView listDict1(HttpServletRequest request,
                                  @RequestParam(value = "dict_code", required = true) String dictCode){
        List<LoanDict1> dict1List = dictService.getAllDict1(dictCode);
        List<LoanDict> dictList = dictService.getAllDict();
        ModelAndView mav = new ModelAndView("/admin/list_dict1");
        mav.addObject("dictList", dictList);
        mav.addObject("dict1List", dict1List);
        mav.addObject("dictCode", dictCode);

        return mav;
    }

    @RequestMapping(value = "/admin/removeDict1", method = {RequestMethod.GET, RequestMethod.POST})
    @Permission("0102")
    public ModelAndView removeDict1(HttpServletRequest request,
                                    @RequestParam(value = "dict_code", required = true) String dictCode,
                                    @RequestParam(value = "dict_ids", required = true) Integer[] dictIds){
        logger.warn("remove " + dictCode + " - " + dictIds.length);
        List<Map<String, Integer>> listDict1 = new ArrayList<Map<String, Integer>>();
        for (Integer id : dictIds){
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("dict1ID", id);
            listDict1.add(map);
        }

        if (listDict1.size() > 0){
            dictService.removeDict1(listDict1);
        }

        return new ModelAndView("redirect:/admin/listDict1?dict_code=" + dictCode);
    }

    @RequestMapping(value = "/admin/saveOrUpdateDict1", method = {RequestMethod.GET, RequestMethod.POST})
    @Permission("0102")
    public ModelAndView saveOrUpdateDict1(HttpServletRequest request,
        @RequestParam(value = "dict_code", required = true) String dictCode,
        @RequestParam(value = "dict_id", required = true) Integer dictId,
        @RequestParam(value = "dict_1_id", required = true) Integer dict1Id,
        @RequestParam(value = "dict_1_code", required = true) String dict1Code,
        @RequestParam(value = "dict_1_name", required = true) String dict1Name){

        if (dictId <= 0 || StringUtils.isEmpty(dictCode)){
            logger.warn(dictId + " - " + dictCode);
            return new ModelAndView("/admin/error");
        }

        LoanDict1 dict1 = new LoanDict1();
        dict1.setDictID(dictId);
        dict1.setDict1Code(dict1Code);
        dict1.setDict1Name(dict1Name);
        dict1.setDict1ID(dict1Id);

        if (dict1Id > 0){
            dictService.updateDict1(dict1);
        }
        else{
            dictService.saveDict1(dict1);
        }

        return new ModelAndView("redirect:/admin/listDict1?dict_code=" + dictCode);
    }
}
