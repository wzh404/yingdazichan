<%@ tag pageEncoding="UTF-8" %>
<%@ tag import="org.springframework.util.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="pagedListHolder" required="true" type="com.xeehoo.p2p.util.LoanPagedListHolder" %>
<%@ attribute name="pagedLink" required="true" type="java.lang.String" %>

<div class="am-cf">
    <div class="am-fr">
        <ul class="am-pagination">
            <c:if test="${pagedListHolder.pageCount > 1}">
                <c:if test="${!pagedListHolder.firstPage}">
                    <li ><a href="javascript:toPage('<%= StringUtils.replace(pagedLink, "~", String.valueOf(0)) %>');">首页</a></li>
                </c:if>
                <c:if test="${!pagedListHolder.firstPage}">
                    <li><a href="javascript:toPage('<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage() - 1)) %>');">上一页</a></li>
                </c:if>

                <c:forEach begin="${pagedListHolder.firstLinkedPage}" end="${pagedListHolder.lastLinkedPage}" var="i">
                    <c:choose>
                        <c:when test="${pagedListHolder.page == i}">
                            <li class="am-active"><a href="javascript:void(0);">${i+1}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li ><a href="javascript: toPage('<%= StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i"))) %>');">${i+1}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${!pagedListHolder.lastPage}">
                    <li><a href="javascript:toPage('<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage() + 1)) %>');">下一页</a></li>
                </c:if>
                <c:if test="${!pagedListHolder.lastPage}">
                    <li><a href="javascript:toPage('<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPageCount() - 1)) %>');">尾页</a></li>
                </c:if>
            </c:if>
        </ul>
    </div>
</div>