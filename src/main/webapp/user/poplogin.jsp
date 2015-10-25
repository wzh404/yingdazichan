<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
    登录框 - 聚有财
</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/header.css?v=0.0.1"/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/global.css?v=0.0.1"/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/master.css?v=0.0.1"/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/font.css?v=0.0.1"/>
    <link rel="stylesheet" type="text/css" href="https://www.jyc99.com/css/base.css?v=0.0.1"/>

    <script type="text/javascript" src="https://www.jyc99.com/js/jquery-1.7.2.min.js"></script>

    <style type="text/css">
        html, body {
            background: #f5f5ef;
            padding: 0;
            margin: 0;
            overflow: hidden;
        }

        .login-box {
            position: inherit;
            margin: 0 auto;
            background: #f5f5ef;
            color: #69737b;
            left: 50px;
            right: auto;
            filter: none;
        }

        .ui-form-item {
            border: 1px solid #c0d0dc;
        }

        .ui-label {
            background: #fff;
        }
    </style>

    <script type="text/javascript">
        function login() {
            var result = false;
            var msg = "";

            if ($("#login_name").val() == "" && $("#login_pwd").val() == "") {
                msg = "请输入账户名和密码";
                result = false;
            }
            else if ($("#login_name").val() == "") {
                msg = "请输入账户名";
                result = false;
            }
            else if ($("#login_pwd").val() == "") {
                msg = "请输入密码";
                result = false;
            }
            else {
                msg = "";
                result = true;
            }

            if (!result) {
                $(".l-Verification").show();
                $(".l-Verification").text(msg);
            }
            else {
                $(".l-Verification").hide()
                $(".l-Verification").text(msg);
                ajaxLogin();
            }
            return result;
        }

        function ajaxLogin(){
            $.ajax({
                url: "/ajax/login",
                data: { login_name: $("#login_name").val(),
                    login_pwd: $("#login_pwd").val()
                },
                type: "post",
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.resultCode == 'OK'){
                        $(".l-Verification").hide();
                        window.parent.location.reload(true);
                    }
                    else{
                        $(".l-Verification").show();
                        $(".l-Verification").text(data.resultMsg);
                    }
                },
                error: function(){
                    //请求出错处理
                    $(".l-Verification").show();
                    $(".l-Verification").text('登录失败!');
                },
                complete: function(){
                    // 完成后
                }
            });

        }

        function UpdateCheckCode() {
            $("#imgCode").attr("src", "ValidateCode.aspx?" + Math.floor(Math.random() * 100000 + 1));
        }

        //var yqcode = '';
        function regclick() {
            window.parent.location.href = "/user/register.jsp";
        }

        //关闭弹出框
        function closePop(isrefresh, toUrl) {
            if (toUrl != "" && (toUrl.indexOf('bbs.jyc99') > 0 || toUrl.indexOf('jyc99.cc:83') > 0)) {
                window.parent.location.href = toUrl;
            }
            if (isrefresh) {
                if (toUrl != "") {
                    window.parent.Resh(toUrl);
                }
                else {
                    if ($(window.parent.document).find("input[class=head_resh]").length > 0)
                        $(window.parent.document).find("input[class=head_resh]")[0].click();
                    else
                        window.parent.Resh(toUrl);
                }
            }
            $(window.parent.document).find("a[class=close2]")[0].click();
        }
    </script>
</head>
<body>
    <div class="login-box">
        <div id="ValidateMsg" class="l-Verification" style="display: none;">
        </div>
        <div class="ui-form-item">
            <label class="ui-label">
                <span class="ui-icon-userDEF"></span>
            </label>
            <input name="login_name" type="text" id="login_name" class="ui-input-normal" placeholder="用户名/手机号"/>
        </div>
        <div class="ui-form-item">
            <label class="ui-label">
                <span class="ui-icon-securityON"></span>
            </label>
            <input name="login_pwd" type="password" id="login_pwd" class="ui-input-normal"/>
        </div>


        <p class="ui-form-other">
            <a href="hyzc/yanzheng_forget.aspx" class="forgot Gold" target="_blank">忘记登录密码？</a>
            <span class="RememberPwd"><input id="chkPwd" type="checkbox" name="chkPwd"/><label for="chkPwd">记住密码</label></span>
        </p>

        <p>
            <input name="" type="button" class="RegBtn" value="注册" onclick="regclick();"/>
            <input type="button" name="btnLogin" value="登录" id="btnLogin" class="LargeBtn" onclick="javascript:login();"/>
        </p>
    </div>
</body>
</html>
