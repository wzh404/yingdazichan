package com.xeehoo.p2p.controller.app;

import com.xeehoo.p2p.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by WIN10 on 2016/1/24.
 */
@Controller
public class UserController {
    /**
     * 个人投资产品
     *
     * @param request
     * @param amount  投资金额
     * @param productId  投资产品ID
     * @param sign 签名数据（未用）
     * @return
     */
    @RequestMapping(value = "/app/invest", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> invest(HttpServletRequest request,
                                      @RequestParam(value = "amount", required = true) Long amount,
                                      @RequestParam(value = "product", required = true) Integer productId,
                                      @RequestParam(value = "sign", required = false) String sign) {

        return CommonUtil.generateJsonMap("OK", null);
    }
}
