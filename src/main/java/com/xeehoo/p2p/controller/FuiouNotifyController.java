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

    @RequestMapping(value = "/rechargeNotify", method = RequestMethod.POST)
    @ResponseBody
    public String register(HttpServletRequest request,
                           @RequestParam(value = "resp_code", required = true) String v1,
                           @RequestParam(value = "resp_desc", required = true) String v2,
                           @RequestParam(value = "mchnt_cd", required = false) String v3,
                           @RequestParam(value = "mchnt_txn_ssn", required = false) String v4,
                           @RequestParam(value = "login_id", required = false) String v5,
                           @RequestParam(value = "amt", required = false) String v6,
                           @RequestParam(value = "signature", required = true) String v7) {
        String str = v1 + "|" + v2 + "|" +v3 + "|" +v4 + "|" +v5 + "|" +v6 + "|" +v7;
        logger.info(str);

        return str;
    }
}
