<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
	聚有财 - 稳健，规范，安全的互联网金融平台 - 汇聚你我，财有未来！
</title>
<meta charset="utf-8">
<meta name="keywords" content="聚有财，余额宝，理财通，风险准备金，全额本息担保，专款专用，房屋抵押，银行兑换,个人理财投资,p2p网贷,网络借贷,互联网金融" />
<meta name="description" content="聚有财是一个互联网金融投资理财平台，聚有财理财产品年化收益率高于支付宝的余额宝、财富通的理财通等宝宝类产品，部分理财产品利息是余额宝的两倍收益，在高收益的同时聚有财为投资者提供本息担保，使投资风险降到最低；聚有财不设资金池，所有资金进入监管账户受中信银行监管，专款专用确保投资人资金安全；聚有财致力于为广大缺乏投资渠道的人们提供一个安全、诚信、低风险、持续稳健回报的投资理财渠道。汇聚你我，财有未来！" />
    <link rel="shortcut icon" href="https://www.jyc99.com/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/header.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/global.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/master.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/font.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/base.css?v=0.0.1" />
    <link rel="stylesheet" type="text/css" href="http://renben.neowave.com.cn:8080/xeehoo/css/boxy.css?v=0.0.1" />

    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery.jslides.js"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery.boxy.js?v=0.0.1"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/default.js?v=0.0.1"></script>


    <script src="http://renben.neowave.com.cn:8080/xeehoo/js/react.js" type="text/javascript"></script>
    <script src="http://renben.neowave.com.cn:8080/xeehoo/js/react-dom.js" type="text/javascript"></script>
    <%--<script src="http://renben.neowave.com.cn:8080/xeehoo/js/browser.min.js"></script>--%>
    <script src="/js/validate-code.react.js?1234"></script>
</head>
<body>


<jsp:include page="top.jsp"/>

    <div class="banner">
        <div id="div_logined" class="login-wrap">
    	    <div class="login-box">
            <!--begin 登录 -->
            <c:if test="${sessionScope.user == null}">
                <form name="form1" method="post" action="/login" id="form1">
                <c:if test="${requestScope.errorMsg == null}">
        	    <div id="ValidateMsg" class="l-Verification" style="display: none;">
                </div>
                </c:if>

                <c:if test="${requestScope.errorMsg != null}">
                <div id="ValidateMsg" class="l-Verification">
                    ${errorMsg}
                </div>
                </c:if>

                <div class="ui-form-item">
                    <label class="ui-label">
                        <span class="ui-icon-userDEF"></span>
                    </label>
                    <input name="login_name" type="text" id="login_name" class="ui-input-normal" placeholder="用户名/手机号" />
                </div>
                <div class="ui-form-item">
                    <label class="ui-label">
                        <span class="ui-icon-securityON"></span>
                    </label>
                    <input name="login_pwd" type="password" id="login_pwd" class="ui-input-normal" />
                </div>
                <div id="div_yz" class="ui-form-item bg-none" style="display:block">
                    <label class="ui-label">
                        <span class="ui-icon-checkcodeT"></span>
                    </label>
                    <input name="txtYZCode" type="text" maxlength="8" id="txtYZCode" class="ui-input-checkcode" size="10" placeholder="验证码" />
                    <div id="validateCode"style="float:right">
                        <%--<img id="imgCode" name="" src="/vdcode" width="100" height="37" alt="验证码"--%>
                             <%--title="点击图片刷新验证码" onclick="UpdateCheckCode();" />--%>
                    </div>
                </div>


                    <p class="ui-form-other">
                    <a href="hyzc/yanzheng_forget.aspx" class="forgot">忘记登录密码？</a>
                    <span class="RememberPwd"><input id="chkPwd" type="checkbox" name="chkPwd" /><label for="chkPwd">记住密码</label></span>
                </p>
                <p>
                    <input name="" type="button" class="RegBtn" value="注册" onclick="regclick();" />
                    <input type="submit" name="btnLogin" value="登录" id="btnLogin" class="LargeBtn" />
                </p>
                </form>
            </c:if>
            <!--end 登录 -->

            <c:if test="${sessionScope.user != null}">
                <h3>聚有财欢迎您！</h3>
                <p class="login-mgs">您当前的登录账户是: <span class="Orange"><label id="labUserCode">${sessionScope.user.loginName}</label></span></p>
                <label id="labYuLiuXinxi"><p class="login-mgs">您尚未设置预留信息，为了保障您的交易安全，请<a href="wdzh/yuliuxinxi.aspx">立即设置</a></p></label>
                <input type="submit" name="btnEnterIn" value="进入我的账户" id="btnEnterIn" class="login-btn" onclick="javascript:homeclick();"/>
            </c:if>
            </div>
        </div>

        <div id="full-screen-slider">
	        <ul id="slides">
                
                        <li style="background: url('http://renben.neowave.com.cn:8080/xeehoo/slides/20150824131046.png') no-repeat center top"><a href="/zscp/productdetail_xs.aspx" target="_blank" ></a></li>
                    
                        <li style="background: url('http://renben.neowave.com.cn:8080/xeehoo/slides/20150917113953.jpg') no-repeat center top"><a href="/system/20150831_notice.aspx" target="_blank" ></a></li>
                    
                        <li style="background: url('http://renben.neowave.com.cn:8080/xeehoo/slides/20150917114013.jpg') no-repeat center top"><a href="/system/20150818_notice.aspx" target="_blank" ></a></li>
                    
                        <li style="background: url('http://renben.neowave.com.cn:8080/xeehoo/slides/20150602135536.png') no-repeat center top"><a href="system/20150526_notice.aspx" target="_blank" ></a></li>
                    
                        <li style="background: url('http://renben.neowave.com.cn:8080/xeehoo/slides/20150731145738.png') no-repeat center top"><a href="system/20150129_notice.aspx" target="_blank" ></a></li>
                    
            </ul>
        </div>
    </div>

    <div class="container">
        <div class="Not-same">
            <a href="system/20150424_notice.aspx" class="zjaq" target="_blank"></a>
            <a href="system/20150424_notice.aspx" class="cpwj" target="_blank"></a>
            <a href="system/20150424_notice.aspx" class="gktm" target="_blank"></a>
        </div>
        <div class="jycnews"> <a href="/bbs?page=0" class="mroe" target="_blank">更多</a>
        <div class="jn-title">最新公告</div>
            <div id="jn-con-ul">
                <ul>
                    <li><a href="bbsdetail_917_1.html" target="_blank">[08/25]新人必看！聚有财小白财迷19问！</a></li>
                    <li><a href="bbsdetail_963_1.html" target="_blank">[08/25]8.18财迷节！1%加息券拿到嗨翻天！</a></li>
                    <li><a href="bbsdetail_886_1.html" target="_blank">[08/03]【财正部评审之新产品--爱上租】</a></li>
                    <li><a href="bbsdetail_888_1.html" target="_blank">[08/03]【租客贷】新品上线 | 租客贷产品Q&A</a></li>
                </ul>
            </div>
        </div>
        <div style="text-align: center">
        <ul id="ullist" class="plans-items" style="width:100%;">
            <li >
                <div class="ite  relative " style="background: white;cursor: default;">
                    <h3 style="background: white;font-size: 30px;text-align: center;font-weight: 800;color: #82cef2;">
                            房贷通</h3>
                    <div class="time-limit2" style="background: white;border-top:0px;">
                        <div class="per2" style="background: white">
                            <span style="font-size: 12px;">预期年化收益率<br></span>
                            <span class="light-Gold2" style="font-size: 48px; font-weight: 800;">
                                9.6<em>%</em>
                            </span>
                        </div>
                    </div>
                    <div class="rate" style="background: white;border-bottom:0px;padding-bottom: 10px">
                        起投金额: 10,000元<br>期限：3月</div>

                    <div class="ite-button" style="background: white;padding: 0px 0 0px 0;">
                        <p class="iteBtn">
                            <a href="javascript:void(0);" tag="tag" onclick="ShowPage(this);return false;"
                               style="border-radius: 3px;display: inline-block;background: #0bafe6;width: 170px; height: 45px;line-height:45px; font-size: 18px;color: #fff;text-align: center;">立即购买</a>
                        </p>
                    </div>
                </div>
            </li>
            <li>
                <div class="ite  relative " style="background: white; cursor: default;">
                    <h3 style="background: white;font-size: 30px;text-align: center;font-weight: 800;color: #82cef2;">
                        房贷通</h3>
                    <div class="time-limit2" style="background: white;border-top: 0px;">
                        <div class="per2" style="background: white">
                            <span style="font-size: 12px;">预期年化收益率<br></span>
                            <span class="light-Gold2" style="font-size: 48px; font-weight: 800;">
                                9.6<em>%</em>
                            </span>

                        </div>
                    </div>
                    <div class="rate" style="background: white;border-bottom:0px;padding-bottom: 10px">
                        起投金额: 10,000元<br>期限：3月</div>

                    <div class="ite-button" style="background: white;padding: 0px 0 0px 0;">
                        <p class="iteBtn">
                            <a href="javascript:void(0);"  tag="tag" onclick="alert('ok');"
                               style="border-radius: 3px;display: inline-block;background: #0bafe6;width: 170px; height: 45px;line-height:45px; font-size: 18px;color: #fff;text-align: center;cursor:hand">立即购买</a>
                        </p>
                    </div>
                </div>
            </li>
            <li>
                <div class="ite  relative " style="background: white">
                    <h3 style="background: white;font-size: 30px;text-align: center;font-weight: 800;color: #82cef2;">
                        房贷通</h3>
                    <div class="time-limit2" style="background: white;border-top: 0px;">
                        <div class="per2" style="background: white">
                            <span style="font-size: 12px;">预期年化收益率<br></span>
                            <span class="light-Gold2" style="font-size: 48px; font-weight: 800;">
                                9.6<em>%</em>
                            </span>

                        </div>
                    </div>
                    <div class="rate" style="background: white;border-bottom:0px;padding-bottom: 10px">
                        起投金额: 10,000元<br>期限：3月</div>

                    <div class="ite-button" style="background: white;padding: 0px 0 0px 0;">
                        <p class="iteBtn">
                            <a href="javascript:void(0);"  tag="tag" onclick="ShowPage(this);return false;"
                               style="border-radius: 3px;display: inline-block;background: #0bafe6;width: 170px; height: 45px;line-height:45px; font-size: 18px;color: #fff;text-align: center;">立即购买</a>
                        </p>
                    </div>
                </div>
            </li>
        </ul>
        </div>
    </div>
    
<jsp:include page="footer.jsp"/>

<script type="text/javascript">
function homeclick() {
    window.location.href = "/fund";
}

react_validate_code_render('validateCode');

</script>


</body>
</html>
