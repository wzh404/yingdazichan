<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="css" value="http://renben.neowave.com.cn:8080/ydzc/css"/>
<c:set var="img" value="http://renben.neowave.com.cn:8080/ydzc/img"/>
<c:set var="js" value="http://renben.neowave.com.cn:8080/xeehoo/js"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
</head>
<link rel="stylesheet" type="text/css" href="${css}/style.css" />
<link rel="stylesheet" type="text/css" href="${css}/login.css" />

<body>
<!--head  start-->
<div class="The_total head">
	<div class="The_total1200">
        <div class="logo"></div>
        <p class="fhsy"><a href="../index.jsp">返回首页</a></p>
    </div>
</div>
<div class="banner The_total">
	<div class="bj_"></div>
    <div class="dlk_">
        <div class="dlk_a">    	
            <h1>登录</h1>
            <p>还不是会员，<a href="../zc/index.html"><span style=" color:#eb75a9;">免费注册</span></a></p>
        </div> 
        <div class="dlk_b">
            <c:if test="${requestScope.errorMsg == null}">
                <div id="ValidateMsg" style="display: none;">
                </div>
            </c:if>

            <c:if test="${requestScope.errorMsg != null}">
                <div id="ValidateMsg" >
                        ${errorMsg}
                </div>
            </c:if>

        	<form name="form1" method="post" action="/login" id="form1">
                <div>
                    <p class="img"><img  src="${img}/dlk_inp1.jpg" /></p>
                    <input type="text" name="login_name" id="login_name" value="请输入用户名/手机号"
          onblur="(this.value=='')?this.value='请输入用户名/手机号':this.value" 
            onfocus="if(this.value=='请输入用户名/手机号'){this.value='';};this.select();"/>
               </div>
                <div>
                    <p class=" img1"><img src="${img}/dlk_inp2.jpg" /></p>
                    <input type="password" name="login_pwd" id="login_pwd" value="请输入密码"
          onblur="(this.value=='')?this.value='请输入密码':this.value" 
            onfocus="if(this.value=='请输入密码'){this.value='';};this.select();"/>
        		</div>
                <div>
                	<p class=" img1"><img src="${img}/dlk_inp2.jpg" /></p>
                    <input type="text" class="dlk_yzm" value="请输入验证码" 
          onblur="(this.value=='')?this.value='请输入验证码':this.value" 
            onfocus="if(this.value=='请输入验证码'){this.value='';};this.select();" />
                    <div class="ing" id="validateCode"></div>
                </div>
                <input type="submit" value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" style=" color:#FFF; background:#ea68a2;cursor: pointer; "  class="submit" />
            </form>
        </div>   
    </div>
</div>
<!--head  end-->

<!--bottom  start-->
<jsp:include page="../footer.jsp"/>
<!--bottom_0  end-->

</body>
<script type="text/javascript" src="${js}/jquery-1.7.2.min.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="${js}/es5-shim.js"></script>
<script type="text/javascript" src="${js}/es5-sham.js"></script>
<![endif]-->

<script type="text/javascript" src="${js}/react.js"></script>
<script type="text/javascript" src="${js}/react-dom.js"></script>
<script src="/js/validate-code.react.js?v=0.1.5"></script>
<script src="/js/investment.react.js?v=0.1.5"></script>
<script type="text/javascript">
    react_validate_code_render('validateCode');
</script>
</html>
