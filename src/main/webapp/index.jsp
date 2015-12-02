<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="css" value="http://renben.neowave.com.cn:8080/ydzc/css"/>
<c:set var="img" value="http://renben.neowave.com.cn:8080/ydzc/img"/>
<c:set var="js" value="http://renben.neowave.com.cn:8080/xeehoo/js"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="${css}/style.css"/>
    <link rel="stylesheet" type="text/css" href="${css}/base.css"/>

    <style type="text/css">
        .banner{height: 330px; background: #ddd; position: relative;}
        #full-screen-slider{width: 100%; height: 330px; float: left; position: relative;}
        #slides{display: block; width: 100%; height: 330px; list-style: none; padding: 0; margin: 0; position: relative}
        #slides li{display: block; width: 100%; height: 100%; list-style: none; padding: 0; margin: 0; position: absolute;}
        #slides li a{display: block; width: 100%; height: 100%; text-indent: -9999px}
        #pagination{display: block; list-style: none; position: absolute; right: 50%; top: 300px; z-index: 999; padding: 5px 15px 5px 0; margin: 0}
        #pagination li{display: block; list-style: none; width: 10px; height: 10px; float: left; margin-left: 15px; border-radius: 5px; background: #fff}
        #pagination li a{display: block; width: 100%; height: 100%; padding: 0; margin: 0; text-indent: -9999px;}
        #pagination li.current{background: #0092ce;}
    </style>
</head>

<body>
<!--top start-->
<jsp:include page="header.jsp?tab=01"/>
<!--head  end-->

<!--banner  start-->
<div id="full-screen-slider">

</div>
<!--banner  end
<div class=" The_total">
    <div class=" The_total1200 gg_"></div>
</div>-->
<!--content  start-->
<div class=" The_total">
    <div class=" The_total1200">
        <div class="sy_left">
            <div>
                <h1>雪花系列</h1>
                <div id="invest_1001">
                </div>
            </div>
            <div>
                <h1>基金系列</h1>
                <div id="invest_1002">
                </div>
            </div>
            <div>
                <h1>投行系列</h1>
                <div id="invest_1003">
                </div>
            </div>
        </div>
        <div class="sy_right">
            <div class="wd_zx">
                <div class="wd_">
                    <h1>网贷行业资讯</h1>

                    <p class="gd_" style=" float:right; color:#848587;">更多>></p>
                </div>
                <div class="zx_">
                    <ul>
                        <li><span></span><a href="">P2P平台应专注细分 提升竞争力</a></li>
                        <li><span></span><a href="">众多网贷平台不愿被称为P2P 转型理财管理</a></li>
                        <li><span></span><a href="">P2P协调处处长：网贷监管需遵循三个原则</a></li>
                        <li><span></span><a href="">助力中小微企业发展, P2P网贷成功倒逼传统金融改革</a></li>
                        <li><span></span><a href="">成交量迫近1200亿元 P2P网贷继续狂飙</a></li>
                        <li><span></span><a href="">消费金融和互联网P2P金融有什么差别？</a></li>
                        <li><span></span><a href="">发展网络借贷 推进众筹融资发展</a></li>
                    </ul>
                </div>
            </div>
            <div class="wd_zx_">
                <div class="wd_">
                    <h1>大家都在抢</h1>
                </div>
                <div class="zx_">
                    <p><span>雪花32号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">X*****购买了此产品</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                            style=" color:#848587;">19分钟前</span></p>

                    <p><span>雪花136号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">花*****购买了此产品</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                            style=" color:#848587;">20分钟前</span></p>

                    <p><span>雪花58号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">李*****购买了此产品</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                            style=" color:#848587;">20分钟前</span></p>

                    <p><span>雪花02号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">Z*****购买了此产品</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                            style=" color:#848587;">21分钟前</span></p>
                </div>
            </div>
            <div class="wd_zx">
                <div class="wd_">
                    <h1>理财风云榜</h1>
                </div>
                <div class="zx_">
                    <table cellpadding="0" cellspacing="0">
                        <colgroup>
                            <col width=""/>
                            <col width=""/>
                            <col width=""/>
                        </colgroup>
                        <tr>
                            <td>排名</td>
                            <td>会员</td>
                            <td>投资金额</td>
                        </tr>
                        <tr>
                            <td><img src="${img}/pm_1.jpg"/></td>
                            <td class="zx_a">Z******</td>
                            <td class="zx_a">26,925,000.00</td>
                        </tr>
                        <tr>
                            <td><img src="${img}/pm_2.jpg"/></td>
                            <td class="zx_a">啊******</td>
                            <td class="zx_a">15,698,000.00</td>
                        </tr>
                        <tr>
                            <td><img src="${img}/pm_3.jpg"/></td>
                            <td class="zx_a">LI******</td>
                            <td class="zx_a">10,021,000.00</td>
                        </tr>
                        <tr>
                            <td class="zx_b">4</td>
                            <td class="zx_a">王******</td>
                            <td class="zx_a">8,256,000.00</td>
                        </tr>
                        <tr>
                            <td class="zx_b">5</td>
                            <td class="zx_a">I*******</td>
                            <td class="zx_a">4,883,000.00</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="wd_zx">
                <div class="wd_">
                    <h1>投资对比</h1>
                </div>
                <div class="zx_">
                    <div style=" margin-top:10px; margin-left:10px;"><img src="${img}/qs_.jpg"/></div>
                </div>
            </div>
            <div style="margin-left:90px; width:206px; height:224px; float:left; margin-top:23px;"><img
                    src="${img}/gg_.jpg"/></div>
        </div>
    </div>
</div>
<!--content  end-->

<!--new  start-->
<div class="The_total">
    <div class="The_total1200">
        <h1 style="font-size:18px; margin-top:10px;">新手指导</h1>
    </div>
</div>
<div class=" The_total new_">
    <p style="text-align:center;"><img src="${img}/new_a.jpg" usemap="#Map" border="0"/>
        <map name="Map" id="Map">
            <area shape="rect" coords="6,23,123,144" href="#"/>
            <area shape="rect" coords="256,11,381,144" href="#"/>
            <area shape="rect" coords="498,12,627,150" href="#"/>
            <area shape="rect" coords="750,10,870,142" href="#"/>
            <area shape="rect" coords="1002,4,1122,144" href="#"/>
        </map>
    </p>
</div>
<!--new  start-->
<jsp:include page="news.jsp"/>
<div class="clear"></div>
<br/>
<jsp:include page="footer.jsp"/>

<script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery.jslides.js"></script>

<!--[if lt IE 9]>
<script type="text/javascript" src="${js}/es5-shim.js"></script>
<script type="text/javascript" src="${js}/es5-sham.js"></script>
<![endif]-->

<script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/react.js"></script>
<script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/react-dom.js"></script>
<script src="/js/investment.react.js?v=0.1.5"></script>
<script src="/js/validate-code.react.js?1235ed"></script>


<script type="text/javascript">
    var products = null;
    $.ajax({
        url: "/cache/product",
        data: {version: "0.1.3"},
        type: "get",
        dataType: "json",
        async: false,
        success: function (data) {
            products = data;
        },
        error: function(xhr, status, err) {
            alert(status + ": " + xhr.status);
        }
    });
    if (products != null){
        react_investment_render('invest_1001', products.I1001, 'http://renben.neowave.com.cn:8080/ydzc/img');
        react_investment_render('invest_1002', products.I1002, 'http://renben.neowave.com.cn:8080/ydzc/img');
        react_investment_render('invest_1003', products.I1003, 'http://renben.neowave.com.cn:8080/ydzc/img');
    }

    react_slides_render('full-screen-slider');
</script>

</body>
</html>
