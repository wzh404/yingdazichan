<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
        <dt>交易类型</dt>
        <dd>
            <em><label id="labMJGuiMo" style="color:#1172bd">${type}</label></em>
        </dd>
    </dl>
    <dl class="Deadline">
        <dt>交易结果</dt>
        <dd><em><label id="labQiXian">${respcode}</label></em>
        </dd>
    </dl>
    <dl class="border-none Yield">
        <dt class="iocn-help-pr">交易金额</dt>
        <dd><em><label id="labShouYiLv" style="color:#FF0033">${amt}</label></em></dd>
    </dl>
</div>

</body>
</html>
