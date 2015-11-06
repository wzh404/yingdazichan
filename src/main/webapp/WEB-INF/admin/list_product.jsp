<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags" %>
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
    <style type="text/css">
        .current-cond {
            background: red;
            color: white;
        }
    </style>
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
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">产品管理</strong> /
                <small>Product</small>
            </div>
        </div>

        <div class="am-g am-margin-top">
            <div class="am-u-sm-1 am-u-md-1 am-text-left">产品类型</div>
            <div class="am-u-sm-11 am-u-md-10" >
                <div class="am-btn-group" id="i_product_type">

                </div>
            </div>
        </div>
        <div class="am-g am-margin-top">
            <div class="am-u-sm-1 am-u-md-1 am-text-left">产品状态</div>
            <div class="am-u-sm-11 am-u-md-10" >
                <div class="am-btn-group" id="i_product_status">

                </div>
            </div>
        </div>

        <div class="am-g am-margin-top">
            <div class="am-u-sm-1 am-u-md-1 am-text-left">年化收益率</div>
            <div class="am-u-sm-11 am-u-md-10">
                <div class="am-btn-group" id="i_product_rate">

                </div>
            </div>
        </div>

        <div class="am-g am-margin-top">
            <div class="am-u-sm-1 am-u-md-1 am-text-left">发布日期</div>
            <div class="am-u-sm-11 am-u-md-10">
                <div class="am-btn-group" id="i_product_date">

                </div>
            </div>
        </div>

        <br/>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span>发布新产品
                        </button>
                    </div>
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-input-group am-input-group-sm">
                    <form action="/admin/product" method="get">
                        <input type="text" class="am-form-field" id="_name" name="_name">
                        <span class="am-input-group-btn">
                            <input type="submit" class="am-btn am-btn-default" >搜索</input>
                        </span>
                    </form>
                </div>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <form class="am-form">
                    <table class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="table-check"><input type="checkbox"/></th>
                            <th class="table-id">ID</th>
                            <th class="table-title">产品</th>
                            <th class="table-type">类别</th>
                            <th class="table-author am-hide-sm-only">发行金额</th>
                            <th class="table-date am-hide-sm-only">发行日期</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pagedListHolder.source}" var="product">
                            <tr>
                                <td><input type="checkbox"/></td>
                                <td>${product.productID}</td>
                                <td><a href="#">${product.productName}</a></td>
                                <td>${productTypes.get(product.productType)}</td>
                                <td class="am-hide-sm-only">${product.totalAmount}</td>
                                <td class="am-hide-sm-only"><fmt:formatDate value="${product.releaseTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                                <td>
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span
                                                    class="am-icon-pencil-square-o"></span> 编辑
                                            </button>
                                            <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span
                                                    class="am-icon-copy"></span> 复制
                                            </button>
                                            <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                                <span class="am-icon-trash-o"></span> 删除
                                            </button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:url value="${pageUri}" var="pagedLink">
                        <c:param name="page" value="~"/>
                    </c:url>
                    <pg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>


                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>

<jsp:include page="footer.jsp"/>
<script src="http://renben.neowave.com.cn:8080/xeehoo/js/react.js" type="text/javascript"></script>
<script src="http://renben.neowave.com.cn:8080/xeehoo/js/react-dom.js" type="text/javascript"></script>
<script src="/js/a.js?12357"></script>
<script type="text/javascript">
    var p_conds = [
        {"key": "全部", "value": "all"},
    <c:forEach items="${productTypes}" var="p">
        {"key": "${p.value}", "value": "${p.key}"},
    </c:forEach>
    ]

    var p_query = {
        "name": 'type',
        "type": 'select',
        "params": 1,
        "value" : '${type}',
        "uri": '${typeUri}',
        "input": "no",
        "options" : p_conds
    }

    var query = {
        "name": 'stat',
        "type": 'select',
        "params": 1,
        "value" : '${stat}',
        "uri": '${statUri}',
        "input": "no",
        "options" : [
            {"key": "全部", "value": "all"},
            {"key": "未发布", "value": "0"},
            {"key": "投标中", "value": "1"},
            {"key": "已满标", "value": "2"},
            {"key": "已完成", "value": "9"}
        ]
    }

    var r_query = {
        "name": 'rate',
        "type": 'select',
        "params": 2,
        "value" : '${rate}',
        "low": '${low_rate}',
        "high": '${high_rate}',
        "uri": '${rateUri}',
        "input": "low_high",
        "width": 40,
        "options" : [
            {"key": "全部", "value": "all"},
            {"key": "8-13%", "low": 8, "high": 13},
            {"key": "13-14%", "low": 13, "high": 14},
            {"key": "14以上", "low": 14}
        ]
    }

    var d_query = {
        "name": 'date',
        "type": 'select',
        "params": 1,
        "value" : '${date}',
        "low": '${low_date}',
        "high": '${high_date}',
        "uri": '${dateUri}',
        "input": "low_high",
        "width": 100,
        "options" : [
            {"key": "全部", "value": "all"},
            {"key": "最近一天", "value": "day"},
            {"key": "最近一周", "value": "week"},
            {"key": "最近一月", "value": "month"},
            {"key": "最近一年", "value": "year"}
        ]
    }

    test_factor('i_product_type', p_query);
    test_factor('i_product_status', query);
    test_factor('i_product_rate', r_query);
    test_factor('i_product_date', d_query);
</script>
</body>
</html>
