<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="css" value="http://renben.neowave.com.cn:8080/ydzc/css"/>
<c:set var="img" value="http://renben.neowave.com.cn:8080/ydzc/img"/>
<c:set var="js" value="http://renben.neowave.com.cn:8080/xeehoo/js"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>账户中心-投资记录</title>
    <link rel="stylesheet" type="text/css" href="${css}/style.css"/>
    <link rel="stylesheet" type="text/css" href="${css}/user.css"/>
</head>

<body>
<!--top start-->
<jsp:include page="/header.jsp?tab=03"/>
<!--head  end-->

<!--nr  start-->
<div class="The_total">
    <div class="The_total1200">
        <jsp:include page="user_left.jsp?menu=0103"/>

        <div class="nr">
            <h1>充值</h1>
            <div class="nr_right">
            <div class="nr_a">
                <p>充值方式：<span style="color:#eb953a; font-size:18px;">网银充值</span></p>
                <p>账户余额：<a href="">0元</a></p>
                <p>充值金额：<input type="text" style=" color:#dadadb;" value="请输入充值金额"onblur="(this.value=='')?this.value='请输入充值金额':this.value"
                               onfocus="if(this.value=='请输入充值金额'){this.value='';};this.select();" />元</p>
                <p><input type="submit" value="充&nbsp;&nbsp;值" style=" color:#FFF; background:#e60012; cursor: pointer;"  class="submit" /></p>
                <div class="nr_a0">
                    <p style="font-size:16px;">温馨提示</p>
                    <br />
                    <ul style="color:#848587;">
                        <li>1、为了您的账户安全，请在充值前进行手机绑定、第三方开户及绑定银行卡。</li>
                        <li>2、您的账户资金将由第三方合作机构托管。</li>
                        <li>3、请注意您的银行卡充值限制，以免造成不便。</li>
                        <li>4、尽职洗钱、信用卡套现、虚假交易等行为，一经发现并确认，将终止该账户的使用。</li>
                        <li>5、如果充值金额没有及时到账，请联系客服，400-900-5335.</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!--nr  end-->

<!--bottom  start-->
<jsp:include page="/footer.jsp"/>
<!--bottom_0  end-->

</body>
</html>