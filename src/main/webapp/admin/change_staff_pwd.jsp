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
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">修改密码</strong> /
                <small>Password</small>
            </div>
        </div>

        <div class="am-tabs am-margin" data-am-tabs>
            <ul class="am-tabs-nav am-nav am-nav-tabs"></ul>

            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                    <form class="am-form" action="" id="form_staff" method="post">
                        <span style="color:red;" id="loan_error_message">${error_message}</span>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">旧密码</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="password" class="am-input-sm" style="width:200px" name="staff_old_pwd"
                                       id="staff_old_pwd"
                                       data-validate="function=checkPassword" value="">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填</div>
                        </div>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">新密码</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="password" class="am-input-sm" style="width:200px" name="staff_new_pwd"
                                       id="staff_new_pwd"
                                       data-validate="function=checkPassword" value="">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填</div>
                        </div>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">确认密码</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="password" class="am-input-sm" style="width:200px"
                                       id="staff_retry_new_pwd"
                                       data-validate="function=checkPassword" value="">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填</div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="am-margin">
                <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="submitStaff();">提交保存</button>
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
    var v = create_form_validate();
    function submitStaff() {
        $('#form_staff').attr("action", '/admin/changeStaffPwd');
        if (comparePassword('staff_new_pwd', 'staff_retry_new_pwd')){
            v.submit('#form_staff');
        }
    }
</script>
</body>
</html>
