<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8" content="盈达资产"/>
    <title>产品详情</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="https://www.jyc99.com/AndroidInterface/css/global.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .i-assets-data { padding:15px 0 10px 0; height:65px; background:#fff; border-bottom:1px solid #dfe3e6;}
        .i-assets-data dl { float:left; padding:0 15px 0 10px; height:60px; border-right:1px solid #e5e6e8;}
        .i-assets-data dt { height:28px; line-height:24px; color:#666; font-size:11px;}
        .i-assets-data dd {  color: #333;}
        .i-assets-data dd em,.i-Charity-data td em { font-size:22px; color:#000; font-weight:bold; font-family:Tahoma;}
        .i-assets-data dd ins,.i-Charity-data td ins {font-size: 12px; color: #a1acb4; text-decoration: none;}
        .i-assets-data dd .fw-normal { font-size:14px; vertical-align:middle;}
        .d-main { margin:10px 0 50px 0; padding:0 10px; background:#fff; border-top:1px solid #dfe3e6; border-bottom:1px solid #dfe3e6;}
        .d-con li { height:45px; line-height:45px; font-size:14px; border-bottom:1px solid #dfe3e6; color:#000;}
        .d-con-t,.d-con-z dt { display:inline-block; margin-right:20px; width:57px; text-align:right; color:#5f646e; font-size:13px;}
        .Progress { position:relative; display:inline-block; width:160px; height:9px; background:#d9d9d9;}
        .Progress span { position:absolute; left:0; top:0; height:9px; background:#18b160;}
        .Progress-text { margin-left:10px;}
        .d-con-z { border-bottom:1px solid #d9d9d9;}
        .d-con-z dt { display:block; margin:15px 0 0 5px; text-align:left;}
        .d-con-z dd { padding:5px 5px 10px 5px; display:block; font-size:14px; line-height:160%; color:#000;}
        .d-fkcs li { margin-left:10px; line-height:160%; border-bottom:1px solid #dfe3e6;}
        .d-fkcs li a { display:block; color:#000; text-decoration:none;}
    </style>
</head>
<body>
<div class="i-assets-data">
    <dl class="Wide">
        <dt>转让金额</dt>
        <dd>
            <em><label id="labMJGuiMo" style="color:#1172bd">${transfer.transferAmount}</label></em>
            <span class="fw-normal"><label id="labUnit">元</label></span>
        </dd>
    </dl>
    <dl class="Deadline">
        <dt>投资期限</dt>
        <dd><em><label id="labQiXian">${transfer.getInvestDayValue()}</label></em><span class="fw-normal">
            <label id="labQiXianDesc">${transfer.getInvestDayUnitName()}</label></span>
        </dd>
    </dl>
    <dl class="border-none Yield">
        <dt class="iocn-help-pr">年化收益率</dt>
        <dd><em><label id="labShouYiLv" style="color:#FF0033">${transfer.rate}</label></em>%</dd>
    </dl>
</div>
<div class="d-main">
    <ul class="d-con">
        <li><span class="d-con-t">转让人</span><label id="labQiXiRi1">${transfer.transferOutMobile}</label></li>
        <li><span class="d-con-t">投资金额</span><label id="labQiXiRi2">${transfer.investAmount}</label></li>
        <li><span class="d-con-t">转让折扣</span><label id="labQiXiRi5">${transfer.transferDiscount}</label></li>
        <li><span class="d-con-t">起息日</span><label id="labQiXiRi3"><fmt:formatDate value="${transfer.investStartDate}" pattern="yyyy-MM-dd"/></label></li>
        <li><span class="d-con-t">结息日</span><label id="labJieXiRi"><fmt:formatDate value="${transfer.investCloseDate}" pattern="yyyy-MM-dd"/></label></li>
        <li><span class="d-con-t">分配方式</span><label id="labFenPeiFangShi">到期分配</label></li>
    </ul>

    <dl class="d-con-z">
        <dt>产品简介</dt>
        <dd><label id="labProJieShao">本项目为个人购车贷款产品。由于银行按揭贷款审批有一定的周期，在该周期内由合作方优先借款人进行初步审核，审核通过后再由合作方向聚有财提交在按揭额度内的融资申请。由合作方进行担保，当抵押车辆销售后立即要求借款人进行还款。</label>
        </dd>
    </dl>
    <dl class="d-con-z">
        <dt>风控措施</dt>
        <dd><label id="labFKCuoShi">专业尽调团队对合作方进行全面的实地尽职调查，调查报告的数据包括实地调查数据、税务系统数据、工商局系统数据、车辆管理系统数据、银行流水数据等。对融资项目和借款人采用多层级的数据核实，以确保项目及融资需求真实、合法，借款人有稳定、可信的还款能力，最终为风险把控提供可信依据。
            由合作方提供全额本息担保，借款人一旦发生逾期合作方即时代偿。</label></dd>
    </dl>
    <dl class="d-con-z border-none">
        <dt>风险提示</dt>
        <dd><label id="labFXTiShi">尽管该债权产品由第三方合作平台进行全额本息担保，但仍存有风险的。当借款人发生逾期时，担保方未能履行担保责任，仍有可能导致资金损失。如果借款人发生逾期，并且在到期后30天后担保方仍未履行代偿责任，聚有财支持您通过法律手段维护自己的合法权益，同时我们也会积极配合您向有关部门反馈情况。</label>
        </dd>
    </dl>
</div>
</body>
</html>
