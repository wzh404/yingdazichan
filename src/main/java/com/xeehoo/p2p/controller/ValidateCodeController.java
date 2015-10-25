package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.service.LoanCacheService;
import com.xeehoo.p2p.util.CommonUtil;
import com.xeehoo.p2p.util.Constant;
import org.apache.log4j.Logger;
import org.patchca.background.BackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.filter.ConfigurableFilterFactory;
import org.patchca.filter.library.AbstractImageOp;
import org.patchca.filter.library.WobbleImageOp;
import org.patchca.filter.predefined.*;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.text.renderer.TextRenderer;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by wangzunhui on 2015/10/20.
 */
@Controller
public class ValidateCodeController {
    private final Logger logger = Logger.getLogger(ValidateCodeController.class);

    @Autowired
    private LoanCacheService cacheService;

    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    private static Random random = new Random();
    static {
        cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("23456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);

        // 随机字体生成器
        RandomFontFactory fontFactory = new RandomFontFactory();
        fontFactory.setMaxSize(32);
        fontFactory.setMinSize(28);
        cs.setFontFactory(fontFactory);

        // 图片滤镜设置
        ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
        java.util.List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
        WobbleImageOp wobbleImageOp = new WobbleImageOp();
        wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_MIRROR);
        wobbleImageOp.setxAmplitude(2.0);
        wobbleImageOp.setyAmplitude(1.0);
        filters.add(wobbleImageOp);
        filterFactory.setFilters(filters);
        cs.setFilterFactory(filterFactory);

        // 文字渲染器设置
        TextRenderer textRenderer = new BestFitTextRenderer();
        textRenderer.setBottomMargin(3);
        textRenderer.setTopMargin(3);
        cs.setTextRenderer(textRenderer);

        // 验证码图片的大小
        cs.setWidth(100);
        cs.setHeight(37);
    }

    @RequestMapping(value = "/vdcode", method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setDateHeader("Expires", 0);

        MyCustomBackgroundFactory backgroundFactory = new MyCustomBackgroundFactory();
        cs.setBackgroundFactory(backgroundFactory);

        // 取得验证码字符串放入Session
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        Captcha captcha = cs.getCaptcha();
        String validationCode = captcha.getChallenge();
        session.setAttribute(Constant.SESSION_VALIDATE_CODE, validationCode);
        session.setMaxInactiveInterval(1000);

        logger.info("当前的SessionID=" + session.getId() + "，验证码=" + validationCode);
        try {
            // 取得验证码图片并输出
            OutputStream outputStream = response.getOutputStream();
            BufferedImage bufferedImage = captcha.getImage();
            ImageIO.write(bufferedImage, "png", response.getOutputStream());

            outputStream.flush();
            outputStream.close();
        }
        catch (Exception e){
            return;
        }
    }

    @RequestMapping(value = "/ajax/vdshow", method = RequestMethod.GET)
    @ResponseBody
    public Map validateCodeErrorTimes(HttpServletRequest request){
        String host = CommonUtil.getRemoteHost(request);
        HttpSession session = request.getSession(false);
        Boolean showValidateCode = false;

        Map<String, Object> map = CommonUtil.generateJsonMap("OK", null);
        if (session == null){
            int errorTimes = cacheService.getUserPwdErrorTimes(host);
            if (errorTimes > 5){
                showValidateCode = true;
            }
        }
        else{
            String validateCode = (String)session.getAttribute(Constant.SESSION_VALIDATE_CODE);
            if (validateCode != null){
                showValidateCode = true;
            }
        }

        map.put("showValidateCode", showValidateCode);

        return map;
    }

    /**
     * 自定义验证码图片背景,主要画一些噪点和干扰线
     */
    private class MyCustomBackgroundFactory implements BackgroundFactory {
        private Random random = new Random();

        public void fillBackground(BufferedImage image) {
            Graphics graphics = image.getGraphics();

            // 验证码图片的宽高
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();

            // 填充为白色背景
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, imgWidth, imgHeight);

            // 画100个噪点(颜色及位置随机)
            for(int i = 0; i < 100; i++) {
                // 随机颜色
                int rInt = random.nextInt(255);
                int gInt = random.nextInt(255);
                int bInt = random.nextInt(255);

                graphics.setColor(new Color(rInt, gInt, bInt));

                // 随机位置
                int xInt = random.nextInt(imgWidth - 3);
                int yInt = random.nextInt(imgHeight - 2);

                // 随机旋转角度
                int sAngleInt = random.nextInt(360);
                int eAngleInt = random.nextInt(360);

                // 随机大小
                int wInt = random.nextInt(6);
                int hInt = random.nextInt(6);

                graphics.fillArc(xInt, yInt, wInt, hInt, sAngleInt, eAngleInt);

                // 画5条干扰线
                if (i % 20 == 0) {
                    int xInt2 = random.nextInt(imgWidth);
                    int yInt2 = random.nextInt(imgHeight);
                    graphics.drawLine(xInt, yInt, xInt2, yInt2);
                }
            }
        }
    }
}
