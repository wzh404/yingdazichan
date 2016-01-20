<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags" %>

<table class="am-table am-table-striped am-table-hover table-main">
    <thead>
    <tr>
        <th class="table-id" style="width: 30%;">投资时间</th>
        <th class="table-title" style="width: 20%;">投资人</th>
        <th class="table-type" style="width: 20%;">投资金额</th>
        <th class="table-type" style="width: 20%;">划拨状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pagedListHolder.source}" var="invest">
        <tr>
            <td><fmt:formatDate value="${invest.investtime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${invest.username}</td>
            <td><fmt:formatNumber value="${invest.amount}" type="currency"/></td>
            <td>
                <c:if test="${invest.tranrespcode == '0000'}">
                    已划拨
                </c:if>
                <c:if test="${invest.tranrespcode != '0000'}">
                    划拨失败<span style="color:red">(${invest.tranrespcode})</span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
  <c:url value="${pageUri}" var="pagedLink">
    <c:param name="page" value="~"/>
  </c:url>
  <pg:paging-js pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
