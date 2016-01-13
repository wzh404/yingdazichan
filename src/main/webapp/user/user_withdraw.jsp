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
        <jsp:include page="user_left.jsp?menu=0104"/>

        <div class="nr">
            <h1>提现</h1>
            <div class="nr_right">

                <div class="nr_at">
                    <p>提现方式：<span style="color:#eb953a; font-size:18px;">网银提现</span></p>
                    <p>账面总余额：<fmt:formatNumber value="${balance.ct}" type="currency"/></p>
                    <p>可用余额：<fmt:formatNumber value="${balance.ca}" type="currency"/></p>
                    <p>冻结余额：<fmt:formatNumber value="${balance.cf}" type="currency"/></p>
                    <p>未转结余额：<fmt:formatNumber value="${balance.cu}" type="currency"/></p>
                    <p>充值金额：
                        <input type="text" style=" color:#dadadb;" value="请输入提现金额" id="amount"
                               onblur="(this.value=='')?this.value='请输入提现金额':this.value"
                               onfocus="if(this.value=='请输入提现金额'){this.value='';};this.select();"/>元
                    </p>
                    <p><input type="button" value="提&nbsp;&nbsp;现" style="color:#FFF; background:#e60012; cursor: pointer;"  class="submit" onclick="submitWithdraw();"/></p>
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
<script type="text/javascript">
    function submitWithdraw(){
        var amt = $('#amount').val();
        var isValidMoney = /^\d{2,8}(\.\d{0,2})?$/.test(amt);
        if (isValidMoney){
            window.location.href="/user/withdraw?amt=" + amt;
        }
        else{
            alert('请输入正确的提现金额');
        }
    }

</script>
</body>
</html>