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

    public final static int PRODUCT_STATUS_INPUT = 1;  // 录入
    public final static int PRODUCT_STATUS_RELEASE = 2; // 发布
    public final static int PRODUCT_STATUS_SETTLE = 3; // 结算
    public final static int PRODUCT_STATUS_FAILED = 4;  // 流标
    public final static int PRODUCT_STATUS_COMPLETE = 5; // 完成
    public final static int PRODUCT_STATUS_EXCEPTION = 9; // 转账例外
    public final static int PRODUCT_STATUS_OVERDUE = 6; // 逾期

    public final static String USER_INVEST_STATUS_UNDUE = "U";  // 未到期
    public final static String USER_INVEST_STATUS_DUE = "D"; // 已到期
    public final static String USER_INVEST_STATUS_OVERDUE = "O"; // 逾期

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
