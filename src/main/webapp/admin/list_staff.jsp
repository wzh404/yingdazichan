<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags" %>

<c:set var="js" value="http://119.254.84.18:8080/xeehoo/js"/>

<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>盈达资产 - 后台管理</title>
    <meta name="description" content="系统管理后台">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="stylesheet" href="/css/admin/amazeui.min.css"/>
    <link rel="stylesheet" href="/css/admin/admin.css">
</head>

<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->

<jsp:include page="header.jsp"/>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <jsp:include page="sidebar.jsp"/>
    <!-- sidebar end -->

    <!-- content start -->
    <form class="admin-content" id="form1" method="post" action="">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">员工管理</strong> /
                <small>Staff</small>
            </div>
        </div>

        <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">角色:</div>
            <div class="am-u-sm-11 am-u-md-10">
                <div class="am-btn-group" id="query_staff_role">

                </div>
            </div>
        </div>

        <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">状态:</div>
            <div class="am-u-sm-11 am-u-md-10">
                <div class="am-btn-group" id="query_staff_stat">

                </div>
            </div>
        </div>
        <br/>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span>
                            <a href="/admin/editStaff?staffId=0">增加新员工</a>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-check"><input type="checkbox"/></th>
                        <th class="table-id">ID</th>
                        <th class="table-title">名称</th>
                        <th class="table-title">性别</th>
                        <th class="table-set">角色</th>
                        <th class="table-set">注册时间</th>
                        <th class="table-set">状态</th>
                        <th class="table-set">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pagedListHolder.source}" var="staff">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${staff.staffId}</td>
                            <td><a href="/admin/editStaff?staff_id=${staff.staffId}">${staff.staffName}</a></td>
                            <td>
                                <c:if test="${staff.staffSex == 'F'}">
                                    女
                                </c:if>
                                <c:if test="${staff.staffSex == 'M'}">
                                    男
                                </c:if>
                            </td>
                            <td>${staff.roleName}</td>
                            <td><fmt:formatDate value="${staff.staffRegtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <c:if test="${staff.staffStatus == 1}">
                                    正常
                                </c:if>
                                <c:if test="${staff.staffStatus == 2}">
                                    已删除
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${staff.staffStatus == 1}">
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <a href="/admin/changeStaffStatus?staff_id=${staff.staffId}&staff_status=2">删除</a>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${staff.staffStatus == 2}">
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <a href="/admin/changeStaffStatus?staff_id=${staff.staffId}&staff_status=1">激活</a>
                                        </div>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:url value="${pageUri}" var="pagedLink">
                    <c:param name="page" value="~"/>
                </c:url>
                <pg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
            </div>
        </div>
    </form>
</div>


</div>

<jsp:include page="footer.jsp"/>
<script src="${js}/react.js" type="text/javascript"></script>
<script src="${js}/react-dom.js" type="text/javascript"></script>
<script src="/js/validate-code.react.js?12357"></script>
<script src="/js/ydzc.js?12357"></script>
<script src="/js/ydzc-validate.js?12357"></script>
<script type="text/javascript">
    var role_options = [
        {"name": "全部", "value": "00"},
        <c:forEach items="${roles}" var="role">
        {"name": "${role.roleName}", "value": "${role.roleCode}"},
        </c:forEach>
    ]

    var role_query = {
        "name": 'role',
        'select': {
            'method' : 'default',
            'options': role_options
        },
        'server':{
            'value':'${role}',
            'uri':'${roleUri}'
        }
    }

    var status_query = {
        "name": 'stat',
        'select': {
            'method': 'default',
            'options': [{"name": "全部", "value": "0"},
                        {"name": "正常", "value": "1"},
                        {"name": "删除", "value": "2"}]
        },
        'server': {
            'value': '${stat}',
            'uri': '${statUri}'
        }
    }

    test_factor('query_staff_stat', status_query);
    test_factor('query_staff_role', role_query);
</script>
</body>
</html>
