package com.xeehoo.p2p.util;

/**
 * Created by wangzunhui on 2015/10/16.
 */
public class UriUtils {
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
}
