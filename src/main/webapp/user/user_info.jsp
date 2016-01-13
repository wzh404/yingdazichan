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
        <jsp:include page="user_left.jsp?menu=0202"/>

        <div class="nr">
            <h1>个人信息</h1>
            <div class="nr_right">
                <div class="nr_g" ><img src="${img}/nr_.jpg" /></div>
                <div class="nr_ga">
                    <p><span style=" color:#F00;">*</span>昵称：<input type="text"  style="border:none; color:#F30;" value="13xxxxxxxxx"/></p>
                    <p><span style=" color:#F00;">*</span>真实姓名：<input type="text" style="border:none;" /></p>
                    <p><span style=" color:#F00;">*</span>身份证号：<input type="text" style="border:none;" /></p>
                    <p><span style=" color:#F00;">*</span>手机号码：<input type="text" style="border:none; color:#F30;" value="13xxxxxxxxx" /></p>
                    <p><span style=" color:#F00;">*</span>性别：男<input type="radio" style="border:none;"name="sex" value="male" />
                        女<input type="radio" style="border:none;" name="sex" value="female" /></p>
                    <p>居住地址：<input type="text" style="border:none;" /></p>
                    <p>毕业院校：<input type="text" style="border:none;" />最高学历：<input type="text" style="border:none;" /></p>
                    <p>公司行业：<input type="text" style="border:none;" />公司规模：<input type="text" style="border:none;" /></p>
                    <p>职业：<input type="text" style="border:none;" />月收入：<input type="text" style="border:none;" /></p>
                    <p>婚姻状况：<input type="text" style="border:none;" />是否有房：<input type="text" style="border:none;" /></p>
                    <p>是否有车：<input type="text" style="border:none;" />紧急联系人姓名：<input type="text" style="border:none;" /></p>
                    <p>紧急联系人电话：<input type="text" style="border:none;" /></p>
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