package com.xeehoo.p2p.util;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/11/5.
 */
public class Condition {
    private static final Logger logger = Logger.getLogger(Condition.class);

    private String key;
    private Map<String, Object> map = new HashMap<String, Object>();

    public String getKey(){
        return key;
    }

    public Map<String, Object> getMap(){
        return map;
    }

    /**
     * 设置选项类的查询行
     *
     * @param key    关键字
     * @param value  关键字值
     */
    public void put(String key, Object value){
        if (key == null)
            return ;

        this.key = key;
        map.put(key, value);
    }

    /**
     * 设置选项范围类的查询行
     *
     * @param key  关键字
     * @param low  最小值
     * @param high 最大值
     */
    public void put(String key, Object low, Object high){
        if (key == null)
            return ;

        this.key = key;
        if (low == null && high == null) {
//            map.put(key, null);
            return;
        }

        setLowAndHighValue(key, low, high);
    }

    /**
     * 设置选项类包含范围的查询行
     *
     * @param key   关键字
     * @param value 关键字值
     * @param low   最小值
     * @param high  最大值
     */
    public void put(String key, String value, Object low, Object high){
        if (key == null)
            return ;

        put(key, value);
        if (value == null)
            return;

        if (low == null && high == null)
            return;

        setLowAndHighValue(key, low, high);
    }

    /**
     * 设置最大值和最小值
     *
     * @param key   关键字
     * @param low   最小值
     * @param high  最大值
     */
    private void setLowAndHighValue(String key, Object low, Object high){
        /* L -> H */
        if (low != null && high != null){
            map.put("low_" + key, low);
            map.put("high_" + key, high);
        }
        /* > L */
        else if (low != null){
            map.put("low_" + key, low);
        }
        /* < H */
        else{
            map.put("high_" + key, high);
        }
    }

    /**
     * 设置返回页面的Map
     *
     * @param mav
     */
    public void setModelAndView(ModelAndView mav){
        if (key == null)
            return;

        map.forEach((key, value) -> {
            if (value != null) {
                 mav.addObject(key, formatValue(value));
            }
        });
    }

    /**
     * 根据Map拼接返回页面的Uri参数
     *
     * @return
     */
    public String getUriParams(){
        if (key == null)
            return null;

        final StringBuilder query = new StringBuilder("");
        map.forEach((key, value) -> {
            if (value != null) {
                if (!StringUtils.isEmpty(query.toString())) {
                    query.append("&");
                }

                query.append(key);
                query.append("=");
                query.append(formatValue(value));
            }
        });

        return StringUtils.isEmpty(query.toString()) ? null : query.toString();
    }

    /**
     * 根据类型格式化value值为String
     *
     * @param value
     * @return
     */
    private String formatValue(Object value){
        if (value instanceof Date){
            return (new SimpleDateFormat("yyyy-MM-dd")).format(value);
        }
        else if (value instanceof BigDecimal){
            return(new DecimalFormat("0.00")).format(value);
        }
        else{
            return value.toString();
        }
    }
}
