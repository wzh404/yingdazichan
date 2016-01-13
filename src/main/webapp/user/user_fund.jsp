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
    <title>账户中心</title>
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
        <jsp:include page="user_left.jsp?menu=0106"/>

        <div class="nr_right">
            <div class="nr_right_">
                <div class="nr_right_left">
                    <p style=" float:left;"><img src="${img}/nr_.jpg"/></p>

                    <div class="nr_right_left0">
                        <p>早上好</p>

                        <p>${sessionScope.user.loginName}</p>

                        <div>
                            <a href=""><img src="${img}/nr-0.png"/></a>&nbsp;&nbsp;&nbsp;<a href=""><img
                                src="${img}/nr-2.png"/></a>&nbsp;&nbsp;&nbsp;<a href="">
                            <img src="${img}/nr-1.png"/></a>
                        </div>
                    </div>
                </div>
                <div class="nr_right_right">
                    <p>上次登陆的时间：2015年11/5/10:30:57</p>
                </div>
                <div class=" clear"></div>
                <div class="nr_right_bottom">
                    <h1>账户总览</h1>

                    <div class="nr_right_bottom_">
                        <div style=" float:left; width:212px; height:90px; margin-left:15px;">
                            <p style=" float:left;"><img src="${img}/nr-3.png"/></p>

                            <div style=" margin-top:25px">
                                <p>总资产</p>

                                <p><fmt:formatNumber value="${userFund.totalFund}" type="CURRENCY"
                                                     currencySymbol=""/>元</p>
                            </div>
                        </div>
                        <div style=" float:left; width:212px; height:90px; border-left:1px solid #dadadb; margin-left:15px;">
                            <p style=" float:left;"><img src="${img}/nr-4.png"/></p>

                            <div style=" margin-top:15px;">
                                <p>待回本息</p>

                                <p>本金<fmt:formatNumber value="${userFund.recoveryPrincipal}" type="CURRENCY"
                                                       currencySymbol=""/>元</p>

                                <p>利息0.00元</p>
                            </div>
                        </div>
                        <div style=" float:left; width:212px; height:90px; border-left:1px solid #dadadb; margin-left:15px;">
                            <p style=" float:left;"><img src="${img}/nr-5.png"/></p>

                            <div style=" margin-top:25px">
                                <p>已赚取收益总额</p>

                                <p><fmt:formatNumber value="${userFund.totalEarnings}" type="CURRENCY"
                                                     currencySymbol=""/>元</p>
                            </div>
                        </div>

                    </div>
                    <div class=" clear"></div>
                    <div class="nr_wz">
                        <p>总资产：理财资产+可用余额+冻结金额</p>

                        <p>待回本息：截止目前，无不确定事件发生的情况下，用户持有的所有投资项目的本金及预期收益总额</p>

                        <p>已赚取收益金额：用户所有已兑现投资项目的收益金额。</p>
                    </div>
                </div>
                <div class="nr_a2">
                    <p style="float:left;"><img src="${img}/nr-6.png"/></p>

                    <div style="float:left;" class="nr_b">
                        <p>可用金额</p>

                        <p><fmt:formatNumber value="${balance.ca}" type="CURRENCY" currencySymbol=""/>元</p>
                    </div>
                    <div class="nr_c">
                        <br/>

                        <p><a href="/investment/index.jsp"><img src="${img}/nr_a.jpg"/></a></p>

                        <p><a href="/user/enterUserWithdraw"><img src="${img}/nr_b.jpg"/></a></p>
                    </div>

                </div>
                <p style=" font-size:16px; color:#848587; margin-top:10px; margin-left:4px;">
                    账户可用余额：用于继续投资或者提现，提现通过第三方资金托管平台进行操作。</p>
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
