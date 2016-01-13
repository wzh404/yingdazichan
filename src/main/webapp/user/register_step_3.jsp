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
<title>注册1</title>
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
                <div class="register_one"><img src="${img}/zc_2_1.png" /><p>验证账户信息</p></div>
                <div class="register_one1"><img src="${img}/zc_1_0.png" /></div>
                <div class="register_one"><img src="${img}/zc_3_0.png" /><p>注册成功</p></div>
            </div>
            <div class="clear"></div>
        	<div class="register_b">
            	<div class="rb">
                    <p class="rb_0"><img src="${img}/ra_1.jpg" /></p>
                    <p style=" color:#fd4750;">${sessionScope.register_user.mobile}</p>
                    <p>恭喜您注册成功，请实名认证享用<strong style="color:#fd4750; font-size:24px;">5000</strong>元体验金</p>
                </div>
                <p style="margin-left:122px;"><a href="/user/webReg"><img src="${img}/ra.jpg" /></a></p>
                <br />
                <p style="margin-left:122px;"><a href="/index.jsp"><img src="${img}/ra_0.jpg" /></a></p>
            </div>
        </div>
    </div>
</div>
<!--register  end-->

<!--bottom  start-->
<jsp:include page="/footer.jsp"/>
<!--bottom_0  end-->

</body>
</html>
