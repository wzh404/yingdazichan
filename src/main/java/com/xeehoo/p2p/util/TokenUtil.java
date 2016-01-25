package com.xeehoo.p2p.util;

import java.math.BigInteger;

/**
 * Created by wangzunhui on 2016/1/25.
 */
public class TokenUtil {
    public static String generateAuthorizationToken(String mobile){
        StringBuilder sb = new StringBuilder(mobile);
        sb.append(new Long(System.nanoTime()).toString());

        return MD5.MD5Encode(sb.toString());
    }
}
