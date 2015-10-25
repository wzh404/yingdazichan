<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="top">
    <div class="container">
        <div class="top-left">
            <span class="tel"><i class="icon-Hotline"></i><strong><label id="ucHeader_tel">4008-555-685</label></strong><a
                    href="system/20150129_notice.aspx" class="mobile" target="_blank">| <i class="icon-mobile"></i>手机聚有财</a></span>

        </div>
        <div class="interflow">
            <a class="weix" href="javascript:void(0);"></a>
            <dl class="weixin2" style="display: none; opacity: 1;">
                <dt>扫码关注聚有财微信</dt>
                <dd><img src="https://bbs.jyc99.com/images/6.1Activity/gzh.jpg" width="120" height="120">
                    <p>服务号</p></dd>
                <dd><img src="https://bbs.jyc99.com/images/weixindyh.jpg" width="120" height="120">
                    <p>订阅号</p></dd>
            </dl>
        </div>
        <div class="weibo">
            <a href="http://weibo.com/u/5211697358" target="_blank" class="weibo-icon"></a>
        </div>


        <div id="ucHeader_div_login" class="top-right">
        <c:if test="${sessionScope.user != null}">
            欢迎您，<a href="/user/home" class="Gold">${sessionScope.user.loginName}</a>
            |
            <a href="/logout" class="LightBlue" >退出</a>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <a href="/user/register.jsp" class="Gold">免费注册</a>
            |
            <a href="javascript:void(0);" onclick="ShowLogin('index');" class="LightBlue">立即登录</a>
        </c:if>
            |
            <a href="HelpCenter/HelpDefault.aspx" class="LightBlue" target="_blank">帮助中心</a>
        </div>
        <input type="submit" name="ucHeader$btnResh" value="" id="ucHeader_btnResh" class="head_resh"
               style="display:none;"/>

        <div class="clear"></div>
    </div>
</div>

<div class="container">
    <div class="header">
        <a href="/index.jsp" class="logo">聚有财</a>

        <div id="ucHeader_div_top" class="header-right"><a href="zjaq/zijinsafe.aspx" class="Sign" target="_blank">100%本息担保，资金银行全程监管，确保专款专用不做信用类业务，所有产品均有足额抵质物。</a>
            <a class="guidenew relative" href="HelpCenter/guidenew.html" target="_blank"><i class="nav-new-icon"></i>新手指南</a>
            <ul class="nav">
                <li><a href="yjgs/yejigongshi.aspx">业绩公示</a></li>
                <li><a href="/invest?page=0">我要投资</a>

                </li>
                <li><a href="javascript:void(0);"
                       onclick="window.location.href = '/fund';">我的账户</a>
                </li>
                <li><a href="http://bbs.jyc99.com/" target="_blank">财迷社区 </a></li>
                <li><a href="jgy/jugongyischoollist.aspx">聚公益</a></li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $(".interflow").hover(
            function () {
                $(".weixin2").filter(':not(:animated)').slideDown(500);
            },
            function () {
                $(".weixin2").slideUp(500);
            });
    })


    function ShowLogin(toUrl, IsfromUrl) {
        var url ="/user/poplogin.jsp";
        if (toUrl != undefined) {
            url += "?toUrl=" + toUrl;
        }
        if (IsfromUrl) {
            if (toUrl != undefined) {
                url += "&fromUrl=" + window.location.href;
            }
            else {
                url += "?fromUrl=" + window.location.href;
            }
        }

//        alert(url);
        Boxy.iframeDialog({
            iframeUrl: url,
            title: "登录",
            modal: true,
            width: "400px",
            height: "280px"
        });
    }
</script>