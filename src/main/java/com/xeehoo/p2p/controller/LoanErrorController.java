package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.interceptor.LoginInterceptor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by WIN10 on 2015/11/7.
 */
@Controller
public class LoanErrorController implements ErrorController {
    private final Logger logger = Logger.getLogger(LoanErrorController.class);

    private final ErrorAttributes errorAttributes;

    @Autowired
    public LoanErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(value = "${error.path:/error}", produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        Map<String, Object> map = getErrorAttributes(request, false);
        map.forEach((key, value) -> {
            logger.info(key + " -> " + value.toString());
        });
        return new ModelAndView("/admin/error", map);
    }

    @RequestMapping(value = "${error.path:/error}")
    @ResponseBody
    public Map<String, Object> error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
        return body;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }
}
