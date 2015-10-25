<%@ page import="com.xeehoo.p2p.util.UriUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags" %>

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

    <link href="http://www.ezubo.com/Style/newStatic/css/module/home/common.css?v=1444907696"  rel="stylesheet" type="text/css">
    <link href="http://www.ezubo.com/Style/newStatic/css/module/home/investment.css?v=1441520876" rel="stylesheet" type="text/css">


    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery.boxy.js?v=0.0.1"></script>

    <title>账户注册 - 聚有财</title>
</head>
<body>


<jsp:include page="../top.jsp"/>
<div class="main">
    <div class="title">
        <h2>我要投资</h2>
        <b class="line"></b>
    </div>
    <div class="Filter relative">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align="right" width="80">产品类型：</td>
                <td>
                    <a id="p0000" href="${typeUri}&type=0000">全部</a>
                    <c:forEach  items="${productTypes}" var="p">
                        <a id="p${p.key}" href="${typeUri}&type=${p.key}">${p.value}</a>
                    </c:forEach>

                </td>
            </tr>
            <tr>
                <td align="right" width="80">年化收益率：</td>
                <td>
                    <a id="r0" href="${rateUri}&rate=1-50">全部</a>
                    <a id="r1" href="${rateUri}&rate=8-13">8-13%</a>
                    <a id="r2" href="${rateUri}&rate=13-14">13-14%</a>
                    <a id="r3" href="${rateUri}&rate=14-50">14%以上</a>
                </td>
            </tr>
            <tr>
                <td align="right">产品状态：</td>
                <td>
                    <a id="s9" href="${statUri}&stat=9">全部</a>
                    <a id="s2"  href="${statUri}&stat=2">投标中</a>
                    <a id="s1" href="${statUri}&stat=1">已满标</a>
                    <a id="s0" href="${statUri}&stat=0">已完成</a>
                </td>
            </tr>

        </table>
    </div>

    <div class="box clearfix" style="display: block;width: 100%;">
        <div class="treetop-list" id="pl_home_projectList">
            <c:forEach  items="${pagedListHolder.source}" var="product">
            <ul>
                <li>
                    <div class="wrap">
                        <div class="tlleft">
                            <p class="p-name clearfix">
                                <span>${productTypes.get(product.productType)}</span><a href="/invest/2647.html" class="myATarget" attr_id='2647'>${product.productName}</a>
                            </p>
                            <div class="dllustration clearfix">
                                <div class="divstyle divstyle01"> <em style="font-size: 24px;">${product.loanRate}<i>%</i></em><br>
                                    <label>预期年化收益率</label>
                                </div>
                                <div class="divstyle divstyle02">
                                    <em style="font-size: 24px;">40-365<i>天</i></em><br>
                                    <label>投资期限</label>
                                </div>
                                <div class="divstyle divstyle03">
                                    <p class="p-progress-bar clearfix"> <span class="out-progress-bar clearfix"> <span class="in-progress-bar" style="width:${product.residualAmount / product.totalAmount * 100}%"> </span> </span> </p>
                                    <p>可投：${product.residualAmount / 10000}万元  /  总额：${product.totalAmount / 10000}万元</p>
                                </div>
                            </div>
                            <p class="p-redemption-mechanism clearfix">
                                <span> 起投金额：${product.minAmount}元 </span>
                                <span class="span02" style="width:25%;">付息方式：按月 </span>
                                <span class="bn span03"> 赎回方式：<em class="underline">T+10</em>，投资满30天即可申请赎回，赎回期内同等计息</span> </p>
                        </div>
                        <div class="tlright" style="padding-top: 25px;padding-right: 5px">
                            <div class="tlright-box">
                                <p class=" pinput-box">
                                    <input maxlength="11" id="h_enter_value2" data-id="2647" class="money money-input" name="money" type="text" value="" />
                                    <span class="span-pos">输入金额：</span> </p>
                                <p id="h_jx_notice2" class="p-error"></p>
                                <p class="p-mon">预期收益：<em id="h_ys2"></em></p>
                                <a href="javascript:;" id="h_jx_payment2" class="a-btn invest-btn" >立即投资</a>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
            </c:forEach>

            <c:url value="${link}" var="pagedLink">
                <c:param name="page" value="~"/>
            </c:url>
            <pg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>

<script type="text/javascript">
    function filterRate(k){
        var a = [['1-50','0'], ['8-13','1'], ['13-14','2'], ['14-50', '3']];
        for (i = 0; i < a.length; i++){
            if (k == a[i][0]){
                return a[i][1];
            }
        }

        return k;
    }

    function setQueryCond(p, k, f){
        if (k == '')
            k = 'all';

        if (f){
            k = f(k);
        }
        $('#' + p + k).attr('class', 'FilterCurrent');
    }

    setQueryCond('p', '${type}');
    setQueryCond('r', '${rate}', filterRate);
    setQueryCond('s', '${stat}');
</script>
</body>
</html>