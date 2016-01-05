<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags" %>

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
    <link rel="stylesheet" href="/css/admin/amazeui.min.css"/>
    <link rel="stylesheet" href="/css/admin/admin.css">
</head>

<body>
<!--top start-->
<jsp:include page="/header.jsp?tab=03"/>
<!--head  end-->

<!--nr  start-->
<div class="The_total">
    <div class="The_total1200">
        <jsp:include page="user_left.jsp?menu=0101"/>

        <div class="nr">
            <div class="nr_right">
                <div style="margin-top: 10px;margin-left: 30px">
                    <p>产品状态:
                        <span style="color: white; background: #0e90d2; padding: 5px 10px 5px 10px">全部</span>
                        <span style="padding: 5px 10px 5px 10px">未到期</span>
                        <span style="padding: 5px 10px 5px 10px">已到期</span>
                        <span style="padding: 5px 10px 5px 10px">逾期中</span>
                    </p>
                </div>
                <div class="nt" style="margin-top: 30px">
                    <table style="width:100%">
                    <tr>
                        <td style="width: 30%"><span>项目名称</span></td>
                        <td style="width: 20%"><span >投资本金</span></td>
                        <td style="width: 20%"><span >待收本息</span></td>
                        <td style="width: 20%"><span >投资时间</span></td>
                        <td style="width: 10%"><span >项目状态</span></td>
                    </tr>
                    <c:forEach items="${pagedListHolder.source}" var="invest">
                    <tr>
                        <td><a href="">${invest.productName}</a></td>
                        <td><fmt:formatNumber value="${invest.investAmount}" type="currency"/></td>
                        <td><fmt:formatNumber value="${invest.investIncome}" type="currency"/></td>
                        <td><fmt:formatDate value="${invest.investTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td><c:if test="${invest.investStatus.equals(\"U\")}">未到期</c:if>
                            <c:if test="${invest.investStatus.equals(\"D\")}">已到期</c:if>
                            <c:if test="${invest.investStatus.equals(\"O\")}">逾期中</c:if>
                        </td>
                    </tr>
                    </c:forEach>
                    </table>

                    <c:url value="${pageUri}" var="pagedLink">
                        <c:param name="page" value="~"/>
                    </c:url>
                    <pg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
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