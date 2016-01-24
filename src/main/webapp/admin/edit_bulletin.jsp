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
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">公告管理</strong> /
                <small>Bulletin</small>
            </div>
        </div>

        <div class="am-tabs am-margin" data-am-tabs>
            <ul class="am-tabs-nav am-nav am-nav-tabs">

            </ul>

            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                    <form class="am-form" action="" id="form_bulletin" method="post">
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">公告标题</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:360px" name="bulletinTitle"
                                       id="bulletinTitle"
                                       data-validate="isNotNull=true" value="${bulletin.bulletinTitle}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填，不可重复</div>
                        </div>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">链接地址</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:360px" name="bulletinUrl"
                                       id="bulletinUrl"
                                       value="${bulletin.bulletinUrl}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">-可选</div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">公告类型</div>
                            <div class="am-u-sm-8 am-u-md-10">
                                <select data-am-selected="{btnSize: 'sm'}" id="bulletinType" name="bulletinType">
                                    <c:forEach items="${bulletinTypes}" var="type">
                                        <option value="${type.dict1Code}">${type.dict1Name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-g am-margin-top-sm">
                            <div class="am-u-sm-12 am-u-md-2 am-text-right admin-form-text">公告内容</div>
                            <div class="am-u-sm-12 am-u-md-10">
                                <textarea rows="6" placeholder="请输入公告内容" name="bulletinContent"
                                          style="width:480px">${bulletin.bulletinContent}</textarea>
                            </div>
                        </div>

                        <input type="hidden" id="bulletinId" name="bulletinId" value="${bulletin.bulletinId}"/>
                    </form>
                </div>

                <div class="am-margin">
                    <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="submitBulletin();">提交保存
                    </button>
                </div>
            </div>

        </div>
        <!-- content end -->
    </div>
</div>
<jsp:include page="footer.jsp"/>

<script src="${js}/react.js" type="text/javascript"></script>
<script src="${js}/react-dom.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/ydzc-validate.js?v=0.1.6"></script>
<script type="text/javascript" src="/js/ydzc.js?v=0.1.6"></script>
<script type="text/javascript" src="/js/validate-form.js?v=0.1.6"></script>

<script type="text/javascript">
    setSelection('bulletinType', '${bulletin.bulletinType}');

    var v = create_form_validate();

    function submitBulletin() {
        if ($('#bulletinId').val() == '0') {
            $('#form_bulletin').attr("action", '/admin/saveBulletin');
        }
        else {
            $('#form_bulletin').attr("action", '/admin/updateBulletin');
        }
        v.submit('#form_bulletin');
    }

</script>
</body>
</html>
