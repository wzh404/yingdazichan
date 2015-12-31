<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">财务结算</strong> /
                <small>Product</small>
            </div>
        </div>

        <div class="am-tabs am-margin" data-am-tabs>
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab1">账户信息</a></li>
                <li><a href="#tab2">结算信息</a></li>
                <li><a href="#tab3">投资记录</a></li>
                <li><a href="#tab4">还款表现</a></li>
            </ul>


            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                    <div class="am-g am-margin-top">
                        <div class="am-u-sm-4 am-u-md-2 am-text-right">账面总余额</div>
                        <div class="am-u-sm-11 am-u-md-10" >
                            <div class="am-btn-group" >
                                <fmt:formatNumber value="${balance.ct}" type="currency"/>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-top">
                        <div class="am-u-sm-4 am-u-md-2 am-text-right">可用余额</div>
                        <div class="am-u-sm-11 am-u-md-10" >
                            <div class="am-btn-group" >
                                <fmt:formatNumber value="${balance.ca}" type="currency"/>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-top">
                        <div class="am-u-sm-4 am-u-md-2 am-text-right">冻结余额</div>
                        <div class="am-u-sm-11 am-u-md-10" >
                            <div class="am-btn-group" >
                                <fmt:formatNumber value="${balance.cf}" type="currency"/>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-top">
                        <div class="am-u-sm-4 am-u-md-2 am-text-right">未转结余额</div>
                        <div class="am-u-sm-11 am-u-md-10" >
                            <div class="am-btn-group" >
                                <fmt:formatNumber value="${balance.cu}" type="currency"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="am-tab-panel am-fade" id="tab2">

                </div>
                <div class="am-tab-panel am-fade" id="tab3">

                </div>

                <div class="am-tab-panel am-fade" id="tab4">

                </div>
            </div>

            <div class="am-margin">
                <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="submitProduct();">提交保存</button>
            </div>
        </div>
        <!-- content end -->
    </div>
</div>
    <jsp:include page="footer.jsp"/>

    <script src="http://renben.neowave.com.cn:8080/xeehoo/js/react.js" type="text/javascript"></script>
    <script src="http://renben.neowave.com.cn:8080/xeehoo/js/react-dom.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/ydzc-validate.js?v=0.1.6"></script>

    <script type="text/javascript">

    </script>
</body>
</html>
