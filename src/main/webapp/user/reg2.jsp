<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/header.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/global.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/master.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/font.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/base.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/boxy.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/login.css" />

    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery.boxy.js?v=0.0.1"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/new_shenqing.js?v=0.0.5"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/Validate.js?v=0.0.1"></script>

    <title>账户注册 - 聚有财</title>
</head>
<body>
<jsp:include page="../top.jsp"/>
<div class="main">
    <div class="title">
        <h2>账户注册</h2>
        <b class="line"></b>
    </div>
    <div class="tablecontent">
        <div id="ValidateMsg" class="l-Verification" style="display:none"></div>
        <div class="l-mgs">点击"免费获取"，动态码会发送到您手机上，如有疑问请拨打客服热线咨询</div>
        <form name="form1" method="post" action="/reg2" id="form1">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="right">
                        手机号码：
                    </td>
                    <td>
                        <input name="txtMobile" type="text" id="txtMobile" class="InputStyle" size="50"
                               Validate="isnotnull=true;ismobile=true;"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="200">
                        手机动态密码：
                    </td>
                    <td>
                        <input name="txtShouJiMa" type="text" id="txtShouJiMa" class="InputStyle" size="20"
                               Validate="isnotnull=true;"/>
                        <input name="" type="button" class="SecondaryBtn" value="免费获取" id="btnMianFei"/>
                        <span id="spantime"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        &nbsp;
                    </td>
                    <td>
                        <input type="submit" name="btnSubmit" value="确定" onclick="return SubmitValidate();"
                               id="btnSubmit" class="LargeBtn"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>


<jsp:include page="../footer.jsp"/>

<c:if test="errorMsg != null">
<script type="text/javascript">
    $(".l-Verification").text('${errorMsg}');
    $(".l-Verification").show();
</script>
</c:if>
</body>
</html>