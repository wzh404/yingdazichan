<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/header.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/global.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/master.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/font.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/base.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/boxy.css?v=0.0.1" />

    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery-1.7.2.min.js"></script>


    <title>账户注册 - 聚有财</title>
</head>
<body>
<script type="text/javascript">
    var Headerindex = '2';
    var topProId = '';
    var ShowPopMessage = '';
    $(function () {
        if (Headerindex != "" && Headerindex != undefined) {
            $($(".header").children("div").children(".nav").children("li")[Headerindex]).children("a").attr("class", "nav-current");
        }

        if (topProId != "") {
            HeaderCountDown($(".top-Countdown")[0], topProId)
        }

        if (ShowPopMessage == 'true') {
            ShowDZBMessage();
        }
/*
        if (getQueryString("statisticsType") == "3") {
            if (!CheckLogin()) {
                ShowLogin();
            }
        }*/
    });

    var index = '0';
    //认证图标的显示.隐藏
    $(function () {
        $('.auth-opts li').hover(function () {
            if ($(this).children('.floatMgsBox').attr("isshow") != "true") {
                $(this).children('.floatMgsBox').show();
            }
        }, function () {
            if ($(this).children('.floatMgsBox').attr("isshow") != "true") {
                $(this).children('.floatMgsBox').hide();
            }
        });
        $('.auth-opts li .Close').click(function () {
            $(this).parent().hide();
            $(this).parent().attr("isshow", "");
        });
        $($('.sidebar').children("li")[index]).attr("class", "sidebar-current");
    });



</script>

<jsp:include page="../top.jsp"/>
<div class="u-main">
    <div class="u-left">
        <jsp:include page="home_left.jsp"/>
    </div>

    <div class="u-right">
        <div class="title">
            <h2>账户总览</h2>
            <div class="guild"><i class="guild-icon"></i>您的投资正获得银行级别的保障</div>
            <b class="line"></b>
        </div>

        <p class="yddzd-rk"><a href="../MonthDuiZhang.aspx" target="_blank"><i class="ydzdd-icon"></i>月度对账单</a></p>
        <div class="items">
            <div class="stats-entry">
                <h4>
                    总投资金额</h4>
                <p class="Amount">
                    <span id="labLeiJiTouZi"><fmt:formatNumber value="${userFund.totalAssets}" type="CURRENCY" currencySymbol=""/></span><span class="size18">元</span></p>
                <p>
                    累计投资金额之和，包括已到期、未到期的投资</p>
            </div>
            <div class="stats-entry">
                <h4>
                    未到期金额</h4>
                <p class="Amount">
                    <span id="labWeiDaoQi"><fmt:formatNumber value="${userFund.notDueAmount}" type="CURRENCY" currencySymbol=""/></span><span class="size18">元</span></p>
                <p>
                    当前尚未到期的投资金额之和</p>
            </div>
            <div class="stats-entry">
                <h4>
                    累计净收益</h4>
                <p class="Amount">
                    <span id="labLeiJiShouYi"><fmt:formatNumber value="${userFund.totalEarnings}" type="CURRENCY" currencySymbol=""/></span><span class="size18">元</span></p>
                <p>
                    累计已经到帐的投资收益之和</p>
            </div>
            <div class="stats-entry">
                <h4>
                    动态净收益</h4>
                <p class="Amount">
                    <span id="labDangRiYuQi"><fmt:formatNumber value="${userFund.dynamicEarnings}" type="CURRENCY" currencySymbol=""/></span><span class="size18">元</span></p>
                <p>
                    累计净收益 + 截止至当日待分配的投资收益</p>
            </div>
        </div>

    </div>
    <div class="clear"></div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>