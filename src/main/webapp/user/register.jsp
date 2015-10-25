<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/login.css" />

    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/jquery.boxy.js?v=0.0.1"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/new_shenqing.js"></script>
    <script type="text/javascript" src="http://renben.neowave.com.cn:8080/xeehoo/js/Validate.js?v=0.0.1"></script>

    <title>账户注册 - 聚有财</title>
</head>
<body>
<script type="text/javascript">
    function isexist(id) {
        var resultStr = "";
        $.ajax({
            url: "../ajax/Ajax.aspx",
            data: { op: "Ajax.CheckUserCode", code: escape($("#" + id).val()), id: "" },
            type: "post",
            dataType: "text",
            async: false,
            success: function (data) {
                resultStr = data
            }
        });
        return resultStr;
    }

    //检查密码
    function issame(id) {
        var resultStr = "";
        if ($("#" + id).val() != $("#txtPassword").val()) {
            resultStr = "两次密码输入不相同";
        }
        return resultStr;
    }

    function popzcxy() {
        Boxy.iframeDialog({
            iframeUrl: "popzcxieyi.aspx",
            title: "聚有财网站注册协议",
            modal: true,
            width: "850px",
            height: "700px"
        });
    }

    function popfwxy() {
        Boxy.iframeDialog({
            iframeUrl: "popfuwuxieyi.aspx",
            title: "聚有财服务协议",
            modal: true,
            width: "850px",
            height: "700px"
        });
    }
</script>

<jsp:include page="../top.jsp"/>
<div class="main">
    <div class="title">
        <h2>账户注册</h2>
        <b class="line"></b>
    </div>
    <div class="tablecontent">
        <div id="ValidateMsg" class="l-Verification" style="display:none;"></div>
        <!-- 验证信息框 -->
        <form name="form1" method="post" action="/reguser" id="form1">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align="right" width="150">
                    <span style="color:Red;">*</span>用户名：
                </td>
                <td>
                    <input name="txtUserCode" type="text" id="txtUserCode" class="InputStyle" size="50" Validate="isnotnull=true;maxlen=20;minlen=4;function=isexist" />
                    <span class="Gray">4~20位字符，可包含数字、字母。</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <span style="color:Red;">*</span>登录密码：
                </td>
                <td>
                    <input name="txtPassword" type="password" id="txtPassword" class="InputStyle" size="50" Validate="isnotnull=true;ispassword=ture" />
                </td>
            </tr>
            <tr>
                <td align="right">
                    <span style="color:Red;">*</span>确认登录密码：
                </td>
                <td>
                    <input name="txtPasswordAgain" type="password" id="txtPasswordAgain" class="InputStyle" size="50" Validate="isnotnull=true;function=issame" />
                    <span class="Gray">6~16位字符，至少包含数字、字母（区分大小写）、符号中的2种。</span>
                </td>
            </tr>
            <tr id="tryqma">
                <td align="right">
                    邀请码：
                </td>
                <td>
                    <input name="txtInviteCode" type="text" id="txtInviteCode" class="InputStyle" size="20" />
                    <span class="Gray">请填写邀请您注册的好友手机号码</span>
                </td>
            </tr>

            <tr>
                <td align="right">
                    &nbsp;
                </td>
                <td>
                    <label>

                        <input name="" type="checkbox" value="" onclick="checkagree(this);" checked="checked" />
                        我同意《<a href="javascript:void(0);" class="size12 Blue" onclick="popzcxy();">聚有财网站注册协议</a>》及《<a href="javascript:void(0);" class="size12 Blue" onclick="popfwxy();">聚有财服务协议</a>》
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">
                    &nbsp;
                </td>
                <td>
                    <input type="submit" name="btnNext" value="下一步" onclick="return SubmitValidate();" id="btnNext" class="LargeBtn" />
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>


<jsp:include page="../footer.jsp"/>
</body>
</html>