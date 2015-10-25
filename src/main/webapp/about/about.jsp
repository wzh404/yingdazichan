<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/header.css?v=0.0.1"/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/global.css?v=0.0.1"/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/master.css?v=0.0.1"/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/font.css?v=0.0.1"/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/base.css?v=0.0.1"/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/boxy.css?v=0.0.1"/>

    <script type="text/javascript" src="https://www.jyc99.com/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="https://www.jyc99.com/js/boxy/jquery.boxy.js?v=0.0.1"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/new_shenqing.js"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/Validate.js?v=0.0.1"></script>

    <title>账户注册 - 聚有财</title>
</head>
<body>
<jsp:include page="../top.jsp"/>
<div class="u-main">
    <div class="u-left">
        <ul class="leftsidebar">
            <li><a href="forours.aspx?index=0&sub=0"><i class="speck2-icon"></i>关于我们</a>
                <ul>
                    <li><a href="forours.aspx?index=0&sub=0"><i class="speck-icon"></i>公司简介</a></li>
                    <li><a href="gltd.aspx?index=0&sub=1"><i class="speck-icon"></i>管理团队</a></li>
                    <li><a href="yjbg.aspx?index=0&sub=2"><i class="speck-icon"></i>业绩报告</a></li>
                    <li><a href="zhaopin.aspx?index=0&sub=3"><i class="speck-icon"></i>加入我们</a></li>
                    <li><a href="/bbs?page=0"><i class="speck-icon"></i>最新公告</a></li>
                </ul>
            </li>
            <li><a href="zjjg.aspx?index=1"><i class="speck2-icon"></i>资金监管</a></li>
            <li><a href="forours.aspx?index=2"><i class="speck2-icon"></i>风险准备金</a></li>
            <li><a href="forours.aspx?index=3"><i class="speck2-icon"></i>法律声明</a></li>
            <li><a href="forours.aspx?index=4"><i class="speck2-icon"></i>风险提示</a></li>
            <li><a href="hzjglist.aspx?index=5"><i class="speck2-icon"></i>合作机构</a></li>
            <li><a href="media_reports.aspx?index=6"><i class="speck2-icon"></i>媒体报道</a></li>
        </ul>
    </div>

    <div class="u-right">
        <div class="title">
            <h2><label id="labTitle">管理团队</label></h2>
            <b class="line"></b>
        </div>
        <div class="secondary-content">
        </div>
    </div>
    <div class="clear"></div>
</div>

<jsp:include page="../footer.jsp"/>
<script type="text/javascript">
    var sideIndex = '0';
    var sideSub = '0'
    $(function () {
        if (sideIndex != "" && sideIndex != undefined) {
            $($(".u-main .u-left .leftsidebar").children("li")[sideIndex]).children('a').attr("class", "leftsidebar-current");
            if (sideSub != "" && sideSub != undefined) {
                $($($(".u-main .u-left .leftsidebar").children("li")[sideIndex]).children("ul").children("li")[sideSub]).children('a').attr("class", "leftsidebar-current");
            }
        }
    });
</script>
</body>
</html>