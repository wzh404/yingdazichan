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
    <title>注册0</title>
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
            	<div class="register_one"><img src="${img}/zc_0_1.png" /><p>填写账户信息</p></div>
                <div class="register_one1"><img src="${img}/zc_1_0.png" /></div>
                <div class="register_one"><img src="${img}/zc_2_0.png" /><p>验证账户信息</p></div>
                <div class="register_one1"><img src="${img}/zc_1.png" /></div>
                <div class="register_one"><img src="${img}/zc_3.png" /><p>注册成功</p></div>
            </div>
            <div class="clear"></div>
        	<div class="register_a">
            	<p class="ra">验证码已发送到您手机，请输入短信中的验证码，激活您的账户</p>
                <div>
                    <form method="post" action="/register2">
                	<p class="ra_">手机号：${sessionScope.register_user.mobile}</p>
                    <p class="ra_0">
                    	<label>短信验证码：</label>
                        <input type="text" id="authcode" name="authcode"/>
                        <input type="button" style="width: 100px;color: #000; display:none" value="免费获取" id="btnMianFei" onclick="javascript:sendValideCode();"/>
                        <span id="spantime" style="font-size: 12px;color:#fd4750;"></span>
                    </p>
                    <input type="submit" class="submit0" value="验&nbsp;&nbsp;&nbsp;&nbsp;证" style="cursor: pointer;" />
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--register  end-->

<!--bottom  start-->
<jsp:include page="/footer.jsp"/>
<!--bottom_0  end-->

<script type="text/javascript" src="${js}/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
    var timer = null;
    var waittime = 20;

    function Countdown() {
        var num = parseInt($("#spantime").attr("num"));
        num = num - 1;
        if (num >= 0 && num < 10) {
            $("#spantime").text("剩余0" + num.toString() + "秒");
            $("#spantime").attr("num","0" + num.toString());
        }
        else {
            $("#spantime").text("剩余" + num.toString() + "秒");
            $("#spantime").attr("num", num);
        }

        if (num <= 0) {
            $("#spantime").text("");
            $("#btnMianFei").css('display','');
            $("#btnMianFei").val("重新获取");
            clearInterval(timer);
        }
    }

    function reset() {
        $("#spantime").text("发送成功，" + waittime + "秒后重新获取");
        $("#spantime").attr("num", waittime);
        timer = setInterval("Countdown()", 1000);
        $("#btnMianFei").css('display','none');
    }

    function sendValideCode() {
        reset();
    }

    reset();
</script>
</body>
</html>
