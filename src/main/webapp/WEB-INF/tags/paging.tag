<%@ tag pageEncoding="UTF-8" %>
<%@ tag import="org.springframework.util.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="pagedListHolder" required="true" type="com.xeehoo.p2p.util.LoanPagedListHolder" %>
<%@ attribute name="pagedLink" required="true" type="java.lang.String" %>

<div class="pagination">
    <div id="pager_pager">
        <c:if test="${pagedListHolder.pageCount > 1}">
            <c:if test="${!pagedListHolder.firstPage}">
                <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage() - 1)) %>">上一页</a></span>
            </c:if>

            <!-- 首页 -->
            <c:if test="${pagedListHolder.firstLinkedPage > 0}">
                <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", "0") %>">1</a></span>
            </c:if>

            <c:if test="${pagedListHolder.firstLinkedPage > 1}">
                <span class="pagingDots">...</span>
            </c:if>
            <c:forEach begin="${pagedListHolder.firstLinkedPage}" end="${pagedListHolder.lastLinkedPage}" var="i">
                <c:choose>
                    <c:when test="${pagedListHolder.page == i}">
                        <span class="pagingItemCurrent">${i+1}</span>
                    </c:when>
                    <c:otherwise>
                        <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i"))) %>">${i+1}</a></span>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 2}">
                <span class="pagingDots">...</span>
            </c:if>

            <!-- 尾页 -->
            <c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 1}">
                <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPageCount() - 1)) %>">${pagedListHolder.pageCount}</a></span>
            </c:if>

            <c:if test="${!pagedListHolder.lastPage}">
                <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage() + 1)) %>">下一页</a></span>
            </c:if>
        </c:if>
        &nbsp;&nbsp;跳转至：<input class="" style="width: 30px" type="text" id="gopage" size=2  value="${pagedListHolder.getPage() + 1}"/>
        <input class="" type="button" value="转到" name="pager$pager" id="pager$pager_btn" class="pagerInput" onclick="javascript:window.location.href ='${pageUri}&page=' + (document.getElementById('gopage').value - 1);"/>
    </div>
    <div class="paginationText">
        <span class="pagingItem">共${pagedListHolder.totalSize}条</span>
    </div>
</div>