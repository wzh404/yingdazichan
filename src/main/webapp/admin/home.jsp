<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人资料</strong> /
                <small>Personal information</small>
            </div>
        </div>

        <hr/>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                <div class="am-panel am-panel-default">
                    <div class="am-panel-bd">
                        <div class="am-g">
                            <div class="am-u-md-4">
                                <img class="am-img-circle am-img-thumbnail"
                                     src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/200/h/200/q/80"
                                     alt=""/>
                            </div>
                            <div class="am-u-md-8">
                                <p>你可以使用<a href="#">gravatar.com</a>提供的头像或者使用本地上传头像。 </p>

                                <form class="am-form">
                                    <div class="am-form-group">
                                        <input type="file" id="user-pic">
                                        <p class="am-form-help">请选择要上传的文件...</p>
                                        <button type="button" class="am-btn am-btn-primary am-btn-xs">保存</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="am-panel am-panel-default">
                    <div class="am-panel-bd">
                        <div class="user-info">
                            <p>等级信息</p>

                            <div class="am-progress am-progress-sm">
                                <div class="am-progress-bar" style="width: 60%"></div>
                            </div>
                            <p class="user-info-order">当前等级：<strong>LV8</strong> 活跃天数：<strong>587</strong>
                                距离下一级别：<strong>160</strong></p>
                        </div>
                        <div class="user-info">
                            <p>信用信息</p>

                            <div class="am-progress am-progress-sm">
                                <div class="am-progress-bar am-progress-bar-success" style="width: 80%"></div>
                            </div>
                            <p class="user-info-order">信用等级：正常当前 信用积分：<strong>80</strong></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                <form class="am-form am-form-horizontal">
                    <div class="am-form-group">
                        <label for="staffName" class="am-u-sm-3 am-form-label">工号</label>

                        <div class="am-u-sm-9" style="margin-top: 8px">
                            ${loanStaff.staffLogin}
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="staffName" class="am-u-sm-3 am-form-label">姓名</label>

                        <div class="am-u-sm-9">
                            <input type="text" id="staffName" name="staffName" placeholder="姓名 / Name" value="${loanStaff.staffName}">

                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="staffName" class="am-u-sm-3 am-form-label">性别</label>

                        <div class="am-u-sm-9">
                            <select data-am-selected="{btnSize: 'sm'}" name="staffSex" id="staffSex">
                                <option value="F">女</option>
                                <option value="M">男</option>
                            </select>

                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="staffMobile" class="am-u-sm-3 am-form-label">手机</label>

                        <div class="am-u-sm-9">
                            <input type="text" id="staffMobile" name="staffMobile" placeholder="输入你的电话号码 / Telephone" value="${loanStaff.staffMobile}">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="staffEmail" class="am-u-sm-3 am-form-label">电子邮件</label>

                        <div class="am-u-sm-9">
                            <input type="email" id="staffEmail" name="staffEmail" name="staffEmail" placeholder="输入你的电子邮件 / Email" value="${loanStaff.staffEmail}">

                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="staffDesc" class="am-u-sm-3 am-form-label">简介 / Intro</label>

                        <div class="am-u-sm-9">
                            <textarea class="" rows="5" id="staffDesc" name="staffDesc" placeholder="输入个人简介">${loanStaff.staffDesc}</textarea>
                            <small>250字以内写出你的一生...</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="button" class="am-btn am-btn-primary">保存修改</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- content end -->
</div>

<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/js/ydzc-validate.js?v=0.1.6"></script>
<script type="text/javascript" src="/js/ydzc.js?v=0.1.6"></script>
<script type="text/javascript" src="/js/validate-form.js?v=0.1.6"></script>

<script type="text/javascript">
    setSelection('staffSex', '${loanStaff.staffSex}');

    var v = create_form_validate();

    function submitStaff() {
        $('#form_staff').attr("action", '/admin/updateStaff');
        v.submit('#form_staff');
    }

    function checkStaff(id){
        var json = callAjax('/admin/ajax/checkStaff', {'name': $('#' + id).val()})
        if (json.resultCode == 'OK'){
            showErrorMsg(id, "工号已经存在");
            return false;
        }
        else{
            hideErrorMsg(id);
            return true;
        }
    }
</script>
</body>
</html>
