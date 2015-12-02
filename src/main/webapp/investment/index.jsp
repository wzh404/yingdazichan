<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags" %>

<c:set var="css" value="http://renben.neowave.com.cn:8080/ydzc/css"/>
<c:set var="img" value="http://renben.neowave.com.cn:8080/ydzc/img"/>
<c:set var="js" value="http://renben.neowave.com.cn:8080/xeehoo/js"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我要投资-基金系列</title>
    <link rel="stylesheet" type="text/css" href="${css}/style.css"/>
    <link rel="stylesheet" type="text/css" href="${css}/investment.css"/>

    <style type="text/css">
        /* 翻页 */
        #pager_pager { height:31px; display:inline; }
        #pager_pager input { padding:0; text-align:center;}
        .pagerInput { margin-left:5px; padding:0 5px !important; height:18px; border:1px solid #ccc; vertical-align:top; }
        .pagination>div { display:inline;}
        .pagination input { margin:0 3px;}
        .paginationText { display:inline;}
        .pagination { height:31px; margin-top:20px; text-align:center; font:12px/20px simsun; color:#333; }
        .pagination a { margin:0 5px; text-decoration:none; }
        .pagination a:hover { text-decoration:underline; }
        .pagination .cp { color:#f00; font-weight:bold; }
        .pagingItem {font: 14px simsun;
            display:-moz-inline-box;
            display:inline-block;
            min-width:30px;}
        .pagingItemCurrent {font: 14px simsun;color:#333;margin: 0 5px; background: #dfdfdf;
            display:-moz-inline-box;
            display:inline-block;
            min-width:30px;}
    </style>
</head>

<body>
<!--top start-->
<jsp:include page="/header.jsp?tab=02"/>
<!--head  end-->

<!--nr  start-->
<div class="The_total1200 cl" style="clear:both">
    <div class="nav">
        <ul id="tab_product">
            <li class="nav_1" id="tab_product_1001"><a href="javascript: product('1001', 1)">基金系列</a></li>
            <li id="tab_product_1002"><a href="javascript: product('1002', 1)" >投行系列</a></li>
            <li id="tab_product_1003"><a href="javascript: product('1003', 1)" >雪花系列</a></li>
            <li>债权转让</li>
        </ul>
        <div class="nav_" style=" float:right;">
            <p class="nav_right"><img src="${img}/b3.png"/></p>

            <div class="nav_0">
                <p><a href="">工作日10:30 , 15:30固定发标，节假日少量随机发标</a></p>
            </div>
        </div>
    </div>
    <div class="nav_left" id="investment">

    </div>
    <div class="nav_right0">
        <div>
            <img src="${img}/n1.jpg"/>
        </div>
        <div class="nav_right1">
            <div class="nav_right_1"><h1>常见问题</h1></div>
            <ul class="nav_right_2">
                <li><a href="">用户名/密码忘记了如何处理？</a></li>
                <li><a href="">如何联系客服？</a></li>
                <li><a href="">如何投资？</a></li>
                <li><a href="">盈达资产理财与银行定期有什么区别？</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="ys_ The_total1200" style="margin-top: 10px;" id="pager_pager_1">

</div>
<!--nr  start-->

<!--bottom  start-->
<jsp:include page="/footer.jsp"/>

<script type="text/javascript" src="${js}/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${js}/jquery.jslides.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="${js}/es5-shim.js"></script>
<script type="text/javascript" src="${js}/es5-sham.js"></script>
<![endif]-->
<script type="text/javascript" src="${js}/react.js"></script>
<script type="text/javascript" src="${js}/react-dom.js"></script>
<script src="/js/investment.react.js?v=0.1.5"></script>
<script src="/js/validate-code.react.js?1235ed"></script>
<script src="/js/paging.js?1235ed"></script>

<script type="text/javascript">
    function setCurrentProduct(type){
        var tabs = ['1001', '1002', '2003'];
        for (var i = 0; i<tabs.length;i++){
            var tab = tabs[i];
//            console.log(tab + ' - ' + type);
            if (tab == type){
//                console.log('111');
                $('#' + 'tab_product_' + tab).attr("class", "nav_1");
            }
            else{
//                console.log('113');
                $('#' + 'tab_product_' + tab).attr("class", "");
            }
        }
    }

    function product(type,page){
        var products = null;
        var totalSize = 0;

        $.ajax({
            url: "/product",
            data: {version: "0.1.3", type : type, page: page},
            type: "get",
            dataType: "json",
            async: false,
            success: function (data) {
                products = data.product;
                totalSize = data.totalSize;
            },
            error: function(xhr, status, err) {
                alert(status + ": " + xhr.status);
            }
        });

        setCurrentProduct(type);
        if (products != null){
            react_investment_render('investment', products, '${img}');
            test_paging('pager_pager_1', totalSize, 2);
        }
    }
    product('1001', 1);
</script>

</body>
</html>
