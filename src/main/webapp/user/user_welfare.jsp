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
        <jsp:include page="user_left.jsp?menu=0201"/>

        <div class="nr_">
            <h1>体验金</h1>
            <div class="nr_right_t">
                <p>我的体验金</p>
                <table cellpadding="0" cellspacing="0">
                    <colgroup>
                        <col width="143px" />
                        <col width="167px" />
                        <col width="158px" />
                        <col width="219px" />
                        <col width="184px" />
                    </colgroup>
                    <tr>
                        <td>体验金金额</td>
                        <td>截止有效日期</td>
                        <td>来源</td>
                        <td>发放日期</td>
                        <td>状态</td>
                    </tr>
                    <tr>
                        <td>5000</td>
                        <td>2015-12-30</td>
                        <td>新用户注册</td>
                        <td>2015-11-01 10:30:45</td>
                        <td><input type="submit" value="投&nbsp;&nbsp;资" style=" color:#FFF; background:#e60012; cursor: pointer;"  class="submit0"/></td>
                    </tr>
                </table>
            </div>
            <div class="nr_right_t0">
                <p>体验金是什么</p>
                <br />
                <ul style="color:#848587;">
                    <li><strong style="color:#323231;">1、什么是体验金？</strong><div>体验金是盈时代平台免费发放给盈时代用户投资体验标的一种投资体验券，体验金的本金不能提现，体验金投资产生的收益归盈时代用户所有。体验金有有效期限，需要在体验金过期之前投资才能享受到体验金带来的收益。</div></li>
                    <li><strong style="color:#323231;">2、如何获得体验金？</strong><div>新用户注册即获5000元体验金；其他用户参加盈时代每月专题活动有机会获得数额不等的体验金。</div></li>
                    <li><strong style="color:#323231;">3、如何使用体验金投资？</strong><div>用户可在“我的福利”页面找到“体验金”，点击“投资”，实名绑卡即可用体验金来投资体验标，体验标到期后，T+1个自然日收益将以先现金的形式打到您的可用余额。</div></li>
                </ul>
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