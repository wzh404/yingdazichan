package com.xeehoo.p2p.util;

/**
 * Created by wangzunhui on 2015/9/30.
 */
public class Constant {
    public final static int LOGIN_OK = 0x00;
    public final static int LOGIN_INVALID_NAME = 0x01;
    public final static int LOGIN_INVALID_PWD = 0x02;
    public final static int PAGE_DEFAULT_SIZE = 5;
    public final static int PAGE_MAX_LINKED_PAGES = 5;

    public final static int USER_STATUS_NORMAL = 0x0001;

    public final static String RESULT_ERROR = "ERROR";
    public final static String RESULT_OK = "OK";

    public final static String DICT_PRODUCT_TYPE = "10";

    //用户注册按步存储数据
    public final static String SESSION_REGISTER_USER = "register_user";

    // 用户登录后存储的会话数据
    public final static String SESSION_USER_LOGIN = "user";

    public final static String SESSION_VALIDATE_CODE = "login_validate_code";
}
