<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="css" value="http://119.254.84.18:8080/ydzc/css"/>
<c:set var="img" value="http://119.254.84.18:8080/ydzc/img"/>
<c:set var="js" value="http://119.254.84.18:8080/xeehoo/js"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<link rel="stylesheet" type="text/css" href="${css}/style.css" />
<link rel="stylesheet" type="text/css" href="${css}/register.css" />
</head>

<body>
<!--top start-->
<jsp:include page="register_header.jsp"/>
<!--head  end-->

<!--register  start-->
<div class="The_total" style="  margin-bottom:10px;">
	<div class="The_total1200">
    	<div class="register_">
        	<div class="register_0">
            	<div class="register_one"><img src="${img}/zc_0.png" /><p>填写账户信息</p></div>
                <div class="register_one1"><img src="${img}/zc_1.png" /></div>
                <div class="register_one"><img src="${img}/zc_2.png" /><p>验证账户信息</p></div>
                <div class="register_one1"><img src="${img}/zc_1.png" /></div>
                <div class="register_one"><img src="${img}/zc_3.png" /><p>注册成功</p></div>
            </div>
            <div class="clear"></div>
        	<div class="register_left">
            	<form method="post" action="/register" id="form1">
                	<p>
                    	<label>手机号：</label>
                        <input  type="text" maxlength="11" id="mobile" name="mobile" data-validate="isMobile=true;function=checkMobile"/>
                        <span style="color: red;display: none" id="span_errmsg_mobile">sss</span>
                    </p>
                    <p>
                    	<label>密码：</label>
                        <input type="password" id="pwd" name="pwd" data-validate="function=checkPassword"/>
                        <span style="color: red;display: none" id="span_errmsg_pwd"></span>
                    </p>
                    <p>
                    	<label>确认密码：</label>
                        <input type="password" id="pwd2" name="pwd2"/>
                        <span style="color: red;display: none" id="span_errmsg_pwd2"></span>
                    </p>

                    <p style="width:200px;float:left">
                    	<label>验证码：</label>
                        <input type="text" maxlength="4" style="width: 120px;" id="vcode" name="vcode" data-validate="function=checkValideCode"/>
                        <div id="validateCode"></div>
                        <span style="color: red;display: none" id="span_errmsg_vcode"></span>
                    </p>

                    <p style=" margin-left:62px;"><img src="${img}/zl_0.png" />我已阅读<span style=" color:#f44278; border-bottom:1px solid #f44278;">《盈达资产用户协议》</span></p>
                    <input type="button" class="submit" id="submit_register" value="马&nbsp;&nbsp;上&nbsp&nbsp;注&nbsp;&nbsp;册" style="cursor: pointer;" />
                </form>
            </div>
            <div class="register_right">
            	<p><a href="/usr/login.jsp">已经是盈时代用户，<span style="color:#fd4750;">点击登录>></span></a></p>
                <p><a href=""><img src="${img}/gg.jpg" /></a></p>
            </div>
        </div>
    </div>
</div>
<!--register  end-->

<!--bottom  start-->
<jsp:include page="/footer.jsp"/>
<!--bottom_0  end-->

<script type="text/javascript" src="${js}/jquery-1.7.2.min.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="${js}/es5-shim.js"></script>
<script type="text/javascript" src="${js}/es5-sham.js"></script>
<![endif]-->
<script type="text/javascript" src="${js}/react.js"></script>
<script type="text/javascript" src="${js}/react-dom.js"></script>



<script type="text/javascript" src="/js/validate-code.react.js?v=0.1.5"></script>
<script type="text/javascript" src="/js/ydzc.js?v=0.1.5"></script>
<script type="text/javascript" src="/js/ydzc-validate.js?v=0.1.5"></script>


<script type="text/javascript">
    react_validate_code_render('validateCode');

    function setMask(r, m, v){
        if (r)
            return (m | v);
        else
            return (m & (0x0F - v));
    }

    var mask = 0x00;

    $("#mobile").blur(function(){
        var r = checkValue('mobile')
        mask = setMask(r, mask, 0x01);
    });

    $("#pwd").blur(function(){
        var r = checkValue('pwd');
        mask = setMask(r, mask, 0x02);
    });

    $("#pwd2").blur(function(){
        var r = comparePassword('pwd', 'pwd2');
        mask = setMask(r, mask, 0x04);
    });

    $("#vcode").blur(function(){
        var r = checkValue('vcode');
        mask = setMask(r, mask, 0x08);
    });

    $("#submit_register").click(function(){
        console.log(mask);
        if (mask == 0x0F){
            $("#form1").submit();
        };
    });
</script>
</body>
</html>
