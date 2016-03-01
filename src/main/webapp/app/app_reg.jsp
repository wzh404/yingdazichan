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
    <title>第三方托管账户</title>
    <meta name="description" content="第三方托管账户" />
    <meta name="keywords"
          content="" />

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
            document.getElementById("pay_form").submit();
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
            <img src="/image/loading.gif">
        </div>
    </div>
</div>
<form id="pay_form" method="post" action="http://www-1.fuiou.com:9057/jzh/app/${action}.action" >
    <input type="hidden" name="mchnt_cd" value="${data.mchnt_cd}" >
    <input type="hidden" name="mchnt_txn_ssn" value="${data.mchnt_txn_ssn}" >
    <input type="hidden" name="mobile_no" value="${data.mobile_no}" >
    <input type="hidden" name="signature" value="${data.signature}" >
</form>
</body>
</html>
