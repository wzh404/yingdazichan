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
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">员工管理</strong> /
                <small>Product</small>
            </div>
        </div>

        <div class="am-tabs am-margin" data-am-tabs>
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab1">基本信息</a></li>
                <li><a href="#tab2">权限</a></li>
            </ul>

            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                    <form class="am-form" action="" id="form_staff" method="post">
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">工号</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <c:if test="${loanStaff.staffId == 0}">
                                <input type="text" class="am-input-sm" style="width:200px" name="staffLogin" id="staffLogin"
                                       data-validate="isNotNull=true;function=checkStaff" value="${loanStaff.staffLogin}">
                                </c:if>
                                <c:if test="${loanStaff.staffId >0}">
                                    ${loanStaff.staffLogin}
                                </c:if>
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填，不可重复</div>
                        </div>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">名称</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:200px" name="staffName" id="staffName"
                                       data-validate="isNotNull=true" value="${loanStaff.staffName}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填</div>
                        </div>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">性别</div>
                            <div class="am-u-sm-8 am-u-md-10">
                                <select data-am-selected="{btnSize: 'sm'}" name="staffSex" id="staffSex">
                                    <option value="F">女</option>
                                    <option value="M">男</option>
                                </select>
                            </div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">手机</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:200px" name="staffMobile" id="staffMobile"
                                       data-validate="isMobile=true" value="${loanStaff.staffMobile}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填</div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">邮箱</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:200px" name="staffEmail" id="staffEmail"
                                       data-validate="isMail=true" value="${loanStaff.staffEmail}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填</div>
                        </div>

                        <c:if test="${loanStaff.staffId == 0}">
                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">密码</div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <input type="password" class="am-input-sm" style="width:200px" name="staffPwd" id="staffPwd"
                                           data-validate="function=checkPassword" value="">
                                </div>
                                <div class="am-hide-sm-only am-u-md-6">*必填</div>
                            </div>
                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">确认密码</div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <input type="password" class="am-input-sm" style="width:200px"  id="staffPwd2"
                                           data-validate="function=checkPassword" value="">
                                </div>
                                <div class="am-hide-sm-only am-u-md-6">*必填</div>
                            </div>

                        </c:if>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">角色</div>
                            <div class="am-u-sm-8 am-u-md-10">
                                <select data-am-selected="{btnSize: 'sm'}" id="staffRole" name="staffRole">
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role.roleCode}">${role.roleName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-g am-margin-top-sm">
                            <div class="am-u-sm-12 am-u-md-2 am-text-right admin-form-text">个人介绍</div>
                            <div class="am-u-sm-12 am-u-md-10">
                                <textarea rows="6" placeholder="请输入个人简介" name="staffDesc">${loanStaff.staffDesc}</textarea>
                            </div>
                        </div>


                        <input type="hidden" id="staffId" name="staffId" value="${loanStaff.staffId}"/>
                    </form>
                </div>

                <div class="am-tab-panel am-fade" id="tab2">
                    <form class="am-form">
                        <div class="am-g am-margin-top-sm">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                征信情况
                            </div>
                            <div class="am-u-sm-8 am-u-md-4 am-u-end">
                                <textarea rows="4"></textarea>
                            </div>
                        </div>

                        <div class="am-g am-margin-top-sm">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                控制措施
                            </div>
                            <div class="am-u-sm-8 am-u-md-4 am-u-end">
                                <textarea rows="8"></textarea>
                            </div>
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
        setSelection('staffRole', '${loanStaff.staffRole}');
        setSelection('staffSex', '${loanStaff.staffSex}');

        var v = create_form_validate();
        var currentTab;
        function setCurrentTab(tab){
            currentTab = tab;
        }

        function submitStaff() {
            if ($('#staffId').val() == '0') {
                $('#form_staff').attr("action", '/admin/saveStaff');
                if (comparePassword('staffPwd', 'staffPwd2')){
                    v.submit('#form_staff');
                }
            }
            else{
                $('#form_staff').attr("action", '/admin/updateStaff');
                v.submit('#form_staff');
            }
        }

        function checkStaff(id){
            var json = callAjax('/admin/ajax/checkStaff', {'name': $('#' + id).val()})
            if (json.resultCode == 'OK'){
                showErrorMsg(id, "工号已经存在");
                return false;
            }else{
                hideErrorMsg(id);
                return true;
            }
        }
    </script>
</body>
</html>
