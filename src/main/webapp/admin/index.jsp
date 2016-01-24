<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>Login Page | Amaze UI Example</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="/css/admin/amazeui.min.css"/>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
</head>
<body>
<div class="header">
  <div class="am-g">
    <h1>盈达资产后台管理</h1>
    <p>YingDaZiChan System Management Platform<br/>系统管理、发布公告、产品融资、资产结算</p>
  </div>
  <hr />
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <h3>登录</h3>
    <hr>
    <br>
    <br>

    <form method="post" action="/admin/login" class="am-form" method="get">
        <div class="am-form-group am-form-icon am-form-feedback">
            <label for="staff_name" >用户名:</label>
            <input type="text" name="staff_name" id="staff_name" class="am-form-field" value="" placeholder="验证失败">
        </div>

        <div class="am-form-group  am-form-icon am-form-feedback">
            <label for="staff_pwd">密码:</label>
            <input type="password" name="staff_pwd" id="staff_pwd" value="">
        </div>

      <label for="remember-me">
        <input id="remember-me" type="checkbox">
        记住密码
      </label>

      <br />
      <div class="am-cf">
        <input type="submit" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
        <input type="button" name="" value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm am-fr">
      </div>
    </form>
    <hr>
    <p>© 2014 YingDaZiChan, Inc. Licensed under MIT license.</p>
  </div>
</div>
</body>
</html>