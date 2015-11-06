package com.xeehoo.p2p.util;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/16.
 */
public class UriUtils {
    private static final Logger logger = Logger.getLogger(UriUtils.class);

    public static String deleteLinkKey(String uri, String key){
        String deleteString = null;

        int s = uri.indexOf(key);
        if (s < 0)
            return uri;

        int e = uri.indexOf("&", s);
        if (e < 0)
            deleteString = uri.substring(s < 1 ? s : s - 1);
        else
            deleteString = uri.substring(s, e + 1);

        uri = uri.replaceAll(deleteString, "");

        return uri;
    }

    public static void createQueryCond(HttpServletRequest request, ModelAndView mav, Map<String, Object> cond){
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();

        // 删除page
        String pageUri = UriUtils.deleteLinkKey(queryString, "page");
        logger.info(uri + " pageUri -> " + pageUri);
        mav.addObject("pageUri", uri + "?" + pageUri);

        for (Map.Entry<String, Object> entry : cond.entrySet()){
            if ("_".equals(entry.getKey()))//传递的内部查询条件，不传递到ModelAndView
                continue;

            String condUri = UriUtils.deleteLinkKey(pageUri, entry.getKey());
            mav.addObject(entry.getKey(), entry.getValue());
            if (StringUtils.isEmpty(condUri)){
                mav.addObject(entry.getKey() + "Uri", uri + "?page=0");
            }
            else{
                mav.addObject(entry.getKey() + "Uri", uri + "?page=0" + (condUri.charAt(0) == '&' ? "" : "&") + condUri);
            }
        }
    }
}
