package com.xeehoo.p2p.controller.app;

import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanTransfer;
import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.po.LoanUserInvestment;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
     * 投资产品
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
                                      @RequestParam(value = "product_id", required = true) Integer productId,
                                      @RequestParam(value = "pwd", required = true) String payPwd,
                                      @RequestHeader(value = "authorization", required = true) String token) {
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

        logger.info("user is " + userId + " - mobile is " + v[1]);
        try {
            Integer result = investService.updateProductUserAmount(productId, userId, v[1], amount * 100);
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
     * 在售产品
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/product", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> invest(HttpServletRequest request,
                                      @RequestParam(value = "product_id", required = true) Integer productId) {
        Map<String, Object> map = CommonUtil.generateJsonMap("OK", null);
        map.put("data", investService.getAppProduct(productId));
        return map;
    }

    @RequestMapping(value = "/app/product/details", method = {RequestMethod.GET})
    public ModelAndView invest(@RequestParam(value = "product_id", required = true) Integer productId) {
        LoanProduct product = investService.getProduct(productId);
        if (product == null){
            return new ModelAndView("/app/error");
        }

        Map<String, String> map = new HashMap<String, String>();
        Integer progress = product.getResidualAmount()
                .divide(product.getTotalAmount(), 2, BigDecimal.ROUND_DOWN)
                .multiply(new BigDecimal(100))
                .intValue();

        String amt = CommonUtil.getMoney(product.getTotalAmount());

        amt = amt.substring(1, amt.indexOf("."));
        map.put("totalAmount", amt);
        map.put("startDay", CommonUtil.tomorrow());
        map.put("progress", progress.toString());
        map.put("investDayUnitName", product.getInvestDayUnitName());
        map.put("investDayValue", product.getInvestDayValue());

        return new ModelAndView("/app/product", map);
    }

    /**
     * 用户投资产品
     *
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/user/investments", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> investments(HttpServletRequest request,
                                            @RequestParam(value = "invest_id", required = true) Integer investId,
                                            @RequestHeader(value = "authorization", required = true) String token     ) {
        Integer userId = tokenService.getUserId(token);
        if (userId == null) {
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        Map<String, Object> map = CommonUtil.generateJsonMap("OK", null);
        map.put("data", investService.getAppUserInvestment(userId, investId));
        return map;
    }

    /**
     * 用户债权转让发布
     *
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/user/transfer", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> transfer(HttpServletRequest request,
                                        @RequestParam(value = "invest_id", required = true) Integer investId,
                                        @RequestParam(value = "amount", required = true) BigDecimal amount,
                                        @RequestHeader(value = "authorization", required = true) String token) {
        Integer userId = tokenService.getUserId(token);
        if (userId == null) {
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        Integer res = investService.transfer(investId, userId, amount);
        logger.info("result is " + res);
        if (res <= 0){
            return CommonUtil.generateJsonMap("ER10", "转让申请失败!");
        }

        return CommonUtil.generateJsonMap("OK", null);
    }

    /**
     * 用户债权转让完成
     *
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/user/transferComplete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> transferComplete(HttpServletRequest request,
                                        @RequestParam(value = "transfer_id", required = true) Integer transferId,
                                        @RequestParam(value = "pwd", required = true) String payPwd,
                                        @RequestHeader(value = "authorization", required = true) String token) {
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
        logger.info("user is " + userId + " - mobile is " + v[1]);
        if (user == null){
            return CommonUtil.generateJsonMap("ER10", "用户不存在");
        }

        if (StringUtils.isEmpty(user.getPayPwd())){
            return CommonUtil.generateJsonMap("ER12", "没有设置支付密码");
        }

        if (!user.isEqualPayPwd(payPwd)){
            return CommonUtil.generateJsonMap("ER11", "支付密码不正确");
        }

        Integer res = null;
        try {
            res = investService.transferComplete(transferId, userId, v[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.generateJsonMap("ER99", "转让失败");
        }
        logger.info("result is " + res);

        if (res > 0)
            return CommonUtil.generateJsonMap("OK", null);
        else
            return CommonUtil.generateJsonMap("ER99", "转让失败");
    }

    /**
     * 债权转让列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/user/transfers", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> transfers(HttpServletRequest request) {
        List<LoanTransfer> transfers = investService.getTransfers();
        Map<String, Object> map = CommonUtil.generateJsonMap("OK", null);
        map.put("data", transfers);

        return map;
    }

    /**
     *
     * @param request
     * @param investId
     * @param token
     * @return
     */
    @RequestMapping(value = "/app/user/transfer/cancel", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> cancelTransferRequest(
            HttpServletRequest request,
            @RequestParam(value = "invest_id", required = true) Integer investId,
            @RequestHeader(value = "authorization", required = true) String token){
        Integer userId = tokenService.getUserId(token);
        if (userId == null) {
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        try{
            int res = investService.cancelTransferRequest(investId);
            if (res > 0){
                return CommonUtil.generateJsonMap("OK", null);
            }
            else{
                return CommonUtil.generateJsonMap("ER81", "取消失败");
            }
        }catch (Exception e){
            return CommonUtil.generateJsonMap("ER80", "取消失败");
        }
    }

    @RequestMapping(value = "/app/transfer/detail", method = {RequestMethod.GET})
    public ModelAndView getTransfer(HttpServletRequest request,
                                    @RequestParam(value = "transfer_id", required = true) Integer transferId){
        LoanTransfer transfer = investService.getTransfer(transferId);
        if (transfer == null){
            return new ModelAndView("/admin/error");
        }
        return new ModelAndView("/app/transfer", "transfer", transfer);
    }

    @RequestMapping(value = "/app/user/investment", method = {RequestMethod.GET})
    public ModelAndView getUserInvestment(HttpServletRequest request,
                                    @RequestParam(value = "invest_id", required = true) Integer investId){
        LoanUserInvestment investment = investService.getUserInvestment(investId);
        if (investment == null){
            return new ModelAndView("/admin/error");
        }

        LoanProduct product = investService.getProduct(investment.getProductId());
        if (product == null){
            return new ModelAndView("/admin/error");
        }

        ModelAndView mav = new ModelAndView("/app/investment");
        mav.addObject("investment", investment);
        mav.addObject("product", product);

        return mav;
    }
}
