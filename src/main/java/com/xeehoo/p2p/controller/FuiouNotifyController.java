package com.xeehoo.p2p.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by WIN10 on 2015/12/5.
 */
@Controller
public class FuiouNotifyController {
    private final Logger logger = Logger.getLogger(FuiouNotifyController.class);

    @RequestMapping(value = "/rechargeNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String rechargeNotify(HttpServletRequest request,
                           @RequestParam(value = "resp_code", required = true) String v1,
                           @RequestParam(value = "mchnt_cd", required = false) String v3,
                           @RequestParam(value = "mchnt_txn_ssn", required = false) String v4,
                           @RequestParam(value = "login_id", required = false) String v5,
                           @RequestParam(value = "amt", required = false) String v6,
                           @RequestParam(value = "signature", required = true) String v7) {
        String str = v1 + "|" + "|" +v3 + "|" +v4 + "|" +v5 + "|" +v6 + "|" + v7;
        logger.info(str);

        return str;
    }

    @RequestMapping(value = "/withdrawNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String withdrawNotify(HttpServletRequest request,
                           @RequestParam(value = "resp_code", required = true) String v1,
                           @RequestParam(value = "resp_desc", required = false) String v2,
                           @RequestParam(value = "mchnt_cd", required = true) String v3,
                           @RequestParam(value = "mchnt_txn_ssn", required = true) String v4,
                           @RequestParam(value = "login_id", required = true) String v5,
                           @RequestParam(value = "amt", required = true) String v6,
                           @RequestParam(value = "signature", required = true) String v7) {
        String str = v1 + "|" + v2 + "|" +v3 + "|" +v4 + "|" +v5 + "|" +v6 + "|" + v7;
        logger.info(str);

        return str;
    }

    @RequestMapping(value = "/webregNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String webregNotify(HttpServletRequest request,
                                 @RequestParam(value = "resp_code", required = true) String v1,
                                 @RequestParam(value = "mchnt_cd", required = true) String v3,
                                 @RequestParam(value = "mchnt_txn_ssn", required = true) String v4,
                                 @RequestParam(value = "mobile_no", required = true) String v5,
                                 @RequestParam(value = "cust_nm", required = true) String v6,
                                @RequestParam(value = "certif_id", required = true) String v7,
                               @RequestParam(value = "city_id", required = true) String v8,
                               @RequestParam(value = "parent_bank_id", required = true) String v9,
                               @RequestParam(value = "capAcntNo", required = true) String v10,
                               @RequestParam(value = "signature", required = true) String v15) {
        String str = v1 + "|" +
                v8 + "|" +
                v3 + "|" +
                v4 + "|" +
                v5 + "|" +
                v6 + "|" +
                v7 + "|" +
                v9 + "|" +
                v10 + "|" +
                v15;
        logger.info(str);

        return str;
    }
}
