package com.xeehoo.p2p.controller.app;

import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.service.LoanUserService;
import com.xeehoo.p2p.service.TokenService;
import com.xeehoo.p2p.util.CommonUtil;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.TokenUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by WIN10 on 2016/1/24.
 */
@Controller
public class InvestmentController {
    private final Logger logger = Logger.getLogger(InvestmentController.class);

    @Autowired
    private LoanUserService userService;

    @Autowired
    private LoanInvestService investService;

    @Autowired
    private TokenService tokenService;


    /**
     * 个人投资产品
     *
     * @param request
     * @param amount  投资金额
     * @param productId  投资产品ID
     * @param token 签名数据（未用）
     * @return
     */
    @RequestMapping(value = "/app/invest", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> invest(HttpServletRequest request,
                                      @RequestParam(value = "amount", required = true) Long amount,
                                      @RequestParam(value = "product", required = true) Integer productId,
                                      @RequestParam(value = "pwd", required = true) String payPwd,
                                      @RequestParam(value = "token", required = true) String token) {
        String val = tokenService.get(token);
        if (val == null){
            return CommonUtil.generateJsonMap("ER01", "请重新登录");
        }

        String[] v = val.split(",");
        if (v == null || v.length != 2){
            return CommonUtil.generateJsonMap("ER98", "数据错误");
        }

        Integer userId = Integer.parseInt(v[0]);
        LoanUser user = userService.getUser(v[1]);
        if (user == null){
            return CommonUtil.generateJsonMap("ER10", "用户不存在");
        }

        if (StringUtils.isEmpty(user.getPayPwd())){
            return CommonUtil.generateJsonMap("ER12", "没有设置支付密码");
        }
        if (!user.isEqualPayPwd(payPwd)){
            return CommonUtil.generateJsonMap("ER11", "支付密码不正确");
        }

        try {
            Integer result = investService.updateProductUserAmount(productId, userId, v[1], amount);
            if (result > 0) {
                return CommonUtil.generateJsonMap("OK", null);
            } else {
                return CommonUtil.generateJsonMap("ER98", "交易失败");
            }
        } catch (Exception e){
            e.printStackTrace();
            return CommonUtil.generateJsonMap("ER99", "系统例外");
        }
    }

    /**
     * 个人投资产品
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/product", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public List<Map<String, Object>> invest(HttpServletRequest request,
                                      @RequestParam(value = "max_product_id", required = true) Integer productId) {
        return investService.getAppProduct(productId);
    }
}
