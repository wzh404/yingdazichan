package com.xeehoo.p2p.util;

import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.zip.CRC32;

/**
 * Created by wangzunhui on 2015/10/20.
 */
public class EncryptUtil {
    private static final String HMAC_SHA1 = "HmacSHA1";

    private static final char[] HEX_CHARS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * HmacSHA1签名
     *
     * @param data 待加密的数据
     * @param key  加密使用的key MD5
     */
    public static String getSignature(String data, String key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes());

        return new String(encodeHex(rawHmac));
    }

    private static char[] encodeHex(byte[] bytes) {
        char chars[] = new char[32];
        for (int i = 0; i < chars.length; i = i + 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
            chars[i + 1] = HEX_CHARS[b & 0xf];
        }
        return chars;
    }

    /**
     * 加密用户密码
     *
     * @param loginPwd
     * @param regtime
     * @param regip
     * @return
     */
    public static String encryptPwd(String loginPwd, Date regtime, String regip){
        StringBuilder key = new StringBuilder(regip);
        key.append(regtime.getTime());
        CRC32 crc32 = new CRC32();
        crc32.update(key.toString().getBytes());
        long crc32Value = crc32.getValue();
        key.append(crc32Value);

        try {
            String md5Key = DigestUtils.md5DigestAsHex(key.toString().getBytes());
            String pwd = EncryptUtil.getSignature(loginPwd, md5Key);

            return pwd;
        }catch (Exception e){
            return null;
        }
    }
}
