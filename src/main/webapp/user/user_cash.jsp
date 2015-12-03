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
        <jsp:include page="user_left.jsp?menu=0104"/>

        <div class="nr">
            <h1>提现</h1>
            <div class="nr_right">

                <div class="nr_at">
                    <p>选择银行卡：<input type="submit" value="添加银行卡" style=" color:#FFF; background:#eb953a; cursor: pointer;"  class="submit" /></p>
                    <p>可用金额：<a href="">0.00元</a></p>
                    <p>提现金额：<input type="text" style=" color:#dadadb;" value="请输入提现金额"onblur="(this.value=='')?this.value='请输入提现金额':this.value"
                                   onfocus="if(this.value=='请输入提现金额'){this.value='';};this.select();" />元</p>
                    <p><input type="submit" value="提&nbsp;&nbsp;现" style=" color:#FFF; background:#e60012; cursor: pointer;"  class="submit" /></p>
                    <p>备注：支付密码默认为手机号后6位，若忘记密码请点击<a href="">找回密码</a></p>
                    <div class="nr_a0">
                        <p style="font-size:16px;">温馨提示</p>
                        <br />
                        <ul style="color:#848587;">
                            <li>1、每笔提现金额最高不能超过1000000元。</li>
                            <li>2、请确保您输入的提现金额，以及绑定的银行卡号信息准确无误。</li>
                            <li>3、如果您填写的提现信息不正确可能会导致提现失败，由此产生的提现费用将不予返还。</li>
                            <li>4、在双休日和法定节假日期间，用户可以申请提现，我们会在下一个工作日进行处理。由此造成的不便，请多多谅解！</li>
                            <li>5、平台禁止洗钱、信用卡套现、虚假交易等行为，一经发现并确认，将终止该账户的使用。</li>
                        </ul>
                    </div>
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