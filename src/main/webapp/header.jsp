<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="css" value="http://119.254.84.18:8080/ydzc/css"/>
<c:set var="img" value="http://119.254.84.18:8080/ydzc/img"/>
<c:set var="js" value="http://119.254.84.18:8080/xeehoo/js"/>

<div class="The_total top">
    <div class="The_total1200">
        <div class="top_0">
            <ul>
                <li>您好${sessionScope.user.loginName}！</li>
                <c:if test="${sessionScope.user == null}">
                    <li><a href="/user/login.jsp" style="color:#eb953a;">登录</a></li>
                    <li>|</li>
                    <li><a href="/user/register_step_1.jsp">注册</a></li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li><a href="/logout" style="color:#eb953a;">退出</a></li>
                </c:if>
            </ul>
        </div>
        <div class="top_1">
            <ul>
                <li><a href="../bzzx/index.html" target="_blank">帮助中心</a></li>
                <li><a href="">手机版</a></li>
                <li><a href="">QQ</a></li>
                <li><a href="">微信</a></li>
            </ul>
        </div>
    </div>
</div>
<!--top end-->

<!--head  start-->
<div class="The_total head">
    <div class="The_total1200">
        <div class="logo"></div>
        <div class="fhsy">
            <ul>
                <li id="tab_01" class="fhsy0"><a href="/index.jsp">首页</a></li>
                <li id="tab_02"><a href="/investment/index.jsp">我要投资</a></li>
                <li id="tab_03"><a href="/user/fund" >我的账户</a></li>
                <li id="tab_04"><a href="../hdy/index.html" target="_blank">活动专区</a></li>
                <li id="tab_05"><a href="" target="_blank">关于我们</a></li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript" src="${js}/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
    var currentTab = '<%=request.getParameter("tab")%>'
    function setCurrentTab(type){
        var tabs = ['01', '02', '03', '04', '05'];
        for (var i = 0; i<tabs.length;i++){
            var tab = tabs[i];
//            console.log(tab + ' - ' + type);
            if (tab == type){
//                console.log('111');
                $('#' + 'tab_' + tab).attr("class", "fhsy0");
            }
            else{
//                console.log('113');
                $('#' + 'tab_' + tab).attr("class", "");
            }
        }
    }
    setCurrentTab(currentTab);
</script>