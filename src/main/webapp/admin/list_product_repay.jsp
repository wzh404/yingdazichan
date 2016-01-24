<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags" %>

<table class="am-table am-table-striped am-table-hover table-main">
    <thead>
    <tr>
      <th class="table-id" style="width: 20%;">还款时间</th>
      <th class="table-title" style="width: 30%;">本金</th>
      <th class="table-type" style="width: 30%;">利息</th>
      <th class="table-type" style="width: 20%;">还款用户</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pagedListHolder.source}" var="userRepay">
      <tr>
        <td><fmt:formatDate value="${userRepay.repayTime}" pattern="yyyy-MM-dd"/></td>
        <td><fmt:formatNumber value="${userRepay.amount}" type="currency"/></td>
        <td><fmt:formatNumber value="${userRepay.interest}" type="currency"/></td>
        <td>${userRepay.mobile}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <c:url value="${pageUri}" var="pagedLink">
    <c:param name="page" value="~"/>
  </c:url>
  <pg:paging-url-js pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
