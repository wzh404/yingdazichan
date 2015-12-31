package com.xeehoo.p2p.util;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/11/4.
 */
public class QueryCondition {
    private static final Logger logger = Logger.getLogger(QueryCondition.class);

    List<Condition> conds = new ArrayList<Condition>();

    /**
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value){
        if (key == null)
            return;

        Condition c = new Condition();
        c.put(key, value);
        conds.add(c);
    }

    /**
     *
     * @param key
     * @param low
     * @param high
     */
    public void put(String key, Object low, Object high){
        if (key == null)
            return;

        Condition c = new Condition();
        c.put(key, low, high);
        conds.add(c);
    }

    /**
     *
     * @param key
     * @param value
     * @param low
     * @param high
     */
    public void put(String key, String value, Object low, Object high){
        if (key == null)
            return;

        Condition c = new Condition();
        c.put(key, value, low, high);
        conds.add(c);
    }

    /**
     *
     * @return
     */
    public Map<String, Object> getCond(){
        Map<String, Object> map = new HashMap<>();
        for (Condition c : conds){
            c.getMap().forEach((key, value) -> {
                if (value != null) {
                    map.put(key, value);
                }
            });
        }

        return map;
    }

    /**
     *
     *
     * @param uri
     * @param mav
     */
    public void setModelAndView( String uri, ModelAndView mav){
        String pageUri = getQueryParams("page");
        pageUri = uri + (pageUri == null ? "" : "?" + pageUri);
        mav.addObject("pageUri", pageUri);
        logger.info(" pageUri -> " + pageUri);

        for (Condition c : conds){
            if (c.getKey().startsWith("_"))
                continue;

            c.setModelAndView(mav);

            String condUri = getQueryParams(c.getKey());
            condUri = uri + (condUri == null ? "?page=0" : "?page=0&" + condUri);
            mav.addObject(c.getKey() + "Uri", condUri);
            logger.info(c.getKey() + "Uri -> " + condUri);
        }
    }

    /**
     * 获取制定key的uri参数
     *
     * @param key
     * @return
     */
    private String getQueryParams(String key){
        StringBuilder query = new StringBuilder("");
        for (Condition c : conds){
            if (c.getKey().equalsIgnoreCase(key))
                continue;

            if (c.getKey().startsWith("_"))
                continue;

            if (c.getUriParams() == null)
                continue;

            if (!StringUtils.isEmpty(query.toString())) {
                query.append("&");
            }

            query.append(c.getUriParams());
        }

        return StringUtils.isEmpty(query.toString()) ? null : query.toString();
    }
}
