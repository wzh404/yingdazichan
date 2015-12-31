<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="js" value="http://renben.neowave.com.cn:8080/xeehoo/js"/>

<div class="nr_left">
    <h1>我的盈达</h1>

    <div style="width:193px;margin:30px auto 0 auto;">

        <div class="vtitle" id="menu_01"><em class="v v02"></em>我的投资</div>
        <div class="vcon">
            <ul class="vconlist clearfix">
                <li id="menu_0106"><a href="/user/fund">我的资产</a></li>
                <li id="menu_0101"><a href="/user/invest">投资记录</a></li>
                <li id="menu_0103"><a href="/user/enterUserRecharge">充值</a></li>
                <li id="menu_0104"><a href="/user/enterUserWithdraw">提现</a></li>
                <li id="menu_0105"><a href="#">债权转让</a></li>
            </ul>
        </div>
        <div class="vtitle" id="menu_02"><em class="v"></em>我的账户</div>
        <div class="vcon" style="display: none;">
            <ul class="vconlist clearfix">
                <li id="menu_0202"><a href="/user/user_info.jsp">个人信息</a></li>
                <li id="menu_0203"><a href="/user/enterUserSecurity">安全信息</a></li>
                <li id="menu_0201"><a href="/user/user_welfare.jsp">我的福利</a></li>
                <li><a href="#">银行卡</a></li>
            </ul>
        </div>
    </div>

    <script src="${js}/jquery-1.7.2.min.js"></script>
    <script>
        var tabs_i = 0
        $(function () {
            $('.vtitle').click(function () {
                var _self = $(this);
                slideMenu(_self);
            });
        })

        //菜单隐藏展开
        function slideMenu(inx){
            console.log(inx);
            var j = $('.vtitle').index(inx);
            if (tabs_i == j) return false;
            tabs_i = j;
            $('.vtitle em').each(function (e) {
                if (e == tabs_i) {
                    $('em', inx).removeClass('v01').addClass('v02');
                } else {
                    $(this).removeClass('v02').addClass('v01');
                }
            });
            $('.vcon').slideUp().eq(tabs_i).slideDown();
        }

        slideMenu($('#menu_<%=request.getParameter("menu").substring(0,2)%>'));
        $('#menu_<%=request.getParameter("menu")%>').addClass('select');
    </script>
</div>