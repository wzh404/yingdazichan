<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="sidebar-item account-status">
    <h2 style="width: 120px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
        Hi，<label id="ucLeft_labUserCode">wzh404</label></h2>

    <div class="auth-opts">
        <ul>
            <li class="button-un-name">身份认证
                <div class="floatMgsBox">
                    <div class="floatMgs"><span class="f-Angle">
                                </span>您尚未进行实名认证 <a href="sfrenzheng.aspx" class="float-Operating">马上认证</a>
                    </div>
                    <a href="javascript:void(0);" class="Close" title="关闭"></a></div>
            </li>
            <li class="button-ve-tels">手机验证
                <div class="floatMgsBox">
                    <div class="floatMgs">
                        <span class="f-Angle"></span>您已完成手机绑定<label id="ucLeft_labMobile">186****0404</label>
                        <a href="xiugaishouji.aspx" class="float-Operating">更改</a>
                    </div>
                    <a href="javascript:void(0);" class="Close" title="关闭"></a>
                </div>
            </li>
            <li class="button-un-BankCards">绑定银行卡
                <div class="floatMgsBox">
                    <div class="floatMgs"><span class="f-Angle">
                                </span>您尚未绑定银行卡<a href="mybankcards.aspx"  class="float-Operating">立即绑定</a>
                        <p class="ma-top-5">您可以通过我们平台</p>
                        <a href="sqzxcard.aspx" class="float-Operating">申请中信借记卡&gt;&gt;</a>
                    </div>
                    <a href="javascript:void(0);" class="Close" title="关闭"></a></div>
            </li>
            <li class="button-un-Protection">安全保护问题
                <div class="floatMgsBox">
                    <div class="floatMgs"><span class="f-Angle"></span>您尚未设置安全保护问题 <a
                            href="acctountwenti_huida.aspx" class="float-Operating">马上设置</a></div>
                    <a href="javascript:void(0);" class="Close" title="关闭"></a></div>
            </li>
        </ul>
    </div>
    <div class="auth-rate">
        <div style="width: 50%"></div>
    </div>
    <div class="auth-level">安全等级:<em>中</em><a href="safecenter.aspx" target="_blank">[提升]</a></div>

</div>
<ul class="sidebar">
    <li><a href="/fund"><i class="zhzl"></i>账户总览</a></li>
    <li><a href="../wdzh/orderrecord.aspx"><i class="ddjl"></i>我的订单</a></li>
    <li><a href="../rzjl/rongzirecord.aspx"><i class="rzjl"></i>我的融资</a></li>
    <li><a href="../wdzh/jiaoyirecord.aspx"><i class="jyjl"></i>我的投资</a></li>
    <li><a href="../wdzh/wdgy.aspx"><i class="wdgy"></i>我的公益</a></li>
    <li><a href="../wdzh/mybankcards.aspx"><i class="yhk"></i>银行卡</a></li>
    <li><a href="../wdzh/mylicaishi.aspx"><i class="zskf"></i>我的客服</a></li>
    <li><a href="../wdzh/safecenter.aspx"><i class="aqzx"></i>安全中心</a></li>
    <li>
        <div class="relative">
            <div class="new-icon2"></div>
            <a href="../wdzh/myjifens.aspx"><i class="wdjf"></i>我的积分</a></div>
    </li>
    <li><a href="../wdzh/myshouyiquan.aspx"><i class="wdsyq"></i>我的有财券</a></li>
</ul>