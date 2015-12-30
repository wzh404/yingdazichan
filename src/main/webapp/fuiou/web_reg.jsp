<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <title>沃易付统一支付网关</title>
    <meta name="description" content="中国联通第三方支付平台" />
    <meta name="keywords"
          content="网上购物-网上支付-安全支付-安全购物-购物，安全-支付-联通支付,在线-付款,收款-网上,贸易-网上贸易" />

    <style type="text/css">
        #loader_container {
            text-align: center;
            position: absolute;
            top: 40%;
            width: 100%;
            left: 0;
        }

        #loader {
            font-family: Tahoma, Helvetica, sans;
            font-size: 14px;
            color: #000000;
            background-color: #FFFFFF;
            padding: 10px 0 16px 0;
            margin: 0 auto;
            display: block;
            width: 190px;
            border: 1px solid #5a667b;
            text-align: left;
            z-index: 2;
        }

        #loader_bg { /*background-color:#e4e7eb;*/
            position: relative;
            top: 8px;
            /*left:8px;*/
            height: 7px;
            width: 190px;
            font-size: 1px
        }

        #progress {
            height: 5px;
            font-size: 1px;
            width: 1px;
            position: relative;
            top: 1px;
            left: 0px;
            background-color: #70940D
        }
    </style>
    <script type="text/javascript">
        function doPay(){
            document.payR.payBtn.click();
        }
    </script>
</head>

<body onload="javascript:doPay();">
<div id="loader_container">
    <div id="loader">
        <div align="center">
            页面正在加载中,请稍后 ...
        </div>
        <div id="loader_bg" align="center">
            <img src="http://epay.10010.com:80/ecpay/images/loading.gif">
        </div>
    </div>
</div>
<form name="payR" method="post" action="http://www-1.fuiou.com:9057/jzh/${action}.action" >
    <input type="hidden" name="mchnt_cd" value="${data.mchnt_cd}" >
    <input type="hidden" name="mchnt_txn_ssn" value="${data.mchnt_txn_ssn}" >
    <input type="hidden" name="mobile_no" value="${data.mobile_no}" >
    <input type="hidden" name="page_notify_url" value="${data.page_notify_url}" >
    <input type="hidden" name="signature" value="${data.signature}" >

    <input type="submit" name="payBtn" value="提交" >
    <p>
        &nbsp;
    </p>
</form>
</body>
</html>
