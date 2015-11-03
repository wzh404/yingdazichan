<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.xeehoo.p2p.util.MenuUtil"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Amaze UI Admin index Examples</title>
  <meta name="description" content="系统管理后台">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="/css/admin/amazeui.min.css"/>
  <link rel="stylesheet" href="/css/admin/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>盈达资产</strong> <small>后台管理</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> ${sessionScope.staff.staffName} <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list">
        <li><a href="admin-index.html"><span class="am-icon-home"></span> 首页</a></li>

        <c:if test="${MenuUtil.mainMenu(sessionScope.menu, '01')}">
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}">
              <span class="am-icon-file"></span> 系统管理 <span class="am-icon-angle-right am-fr am-margin-right"></span>
          </a>
          <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
              <c:if test="${MenuUtil.subMenu(sessionScope.menu, '01', '0101')}">
              <li><a href="admin-user.html" class="am-cf"><span class="am-icon-check"></span> 数据字典<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
              </c:if>

              <c:if test="${MenuUtil.subMenu(sessionScope.menu, '01', '0102')}">
            <li><a href="admin-help.html"><span class="am-icon-puzzle-piece"></span> 一级字典</a></li>
              </c:if>

                  <c:if test="${MenuUtil.subMenu(sessionScope.menu, '01', '0103')}">
            <li><a href="admin-gallery.html"><span class="am-icon-th"></span> 二级字典<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
                  </c:if>

              <c:if test="${MenuUtil.subMenu(sessionScope.menu, '01', '0104')}">
              <li><a href="admin-log.html"><span class="am-icon-calendar"></span> 用户管理</a></li>
              </c:if>

              <c:if test="${MenuUtil.subMenu(sessionScope.menu, '01', '0105')}">
            <li><a href="admin-404.html"><span class="am-icon-user"></span>角色管理</a></li>
              </c:if>

                  <c:if test="${MenuUtil.subMenu(sessionScope.menu, '01', '0106')}">
              <li><a href="admin-404.html"><span class="am-icon-bug"></span>权限管理</a></li>
                  </c:if>
          </ul>
        </li>
        </c:if>
          <li class="admin-parent">
              <a class="am-cf" data-am-collapse="{target: '#collapse-page-nav'}">
                  <span class="am-icon-table"></span> 页面维护 <span class="am-icon-angle-right am-fr am-margin-right"></span>
              </a>
              <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-page-nav">
                  <c:if test="${MenuUtil.subMenu(sessionScope.menu, '01', '0101')}">
                      <li><a href="admin-user.html" class="am-cf"><span class="am-icon-check"></span> 幻灯片<span class="am-icon-bars am-fr am-margin-right admin-icon-yellow"></span></a></li>
                  </c:if>
              </ul>
          </li>

        <li><a href="admin-table.html"><span class="am-icon-bookmark"></span> 发布公告</a></li>

        <li><a href="admin-form.html"><span class="am-icon-pencil-square-o"></span> 产品管理</a></li>
        <li><a href="#"><span class="am-icon-sign-out"></span> 财务结算</a></li>
      </ul>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-bookmark"></span> 公告</p>
          <p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
        </div>
      </div>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-tag"></span> wiki</p>
          <p>Welcome to the Amaze UI wiki!</p>
        </div>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal information</small></div>
        </div>

        <hr/>

        <div class="am-g">

            <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                <div class="am-panel am-panel-default">
                    <div class="am-panel-bd">
                        <div class="am-g">
                            <div class="am-u-md-4">
                                <img class="am-img-circle am-img-thumbnail" src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/200/h/200/q/80" alt=""/>
                            </div>
                            <div class="am-u-md-8">
                                <p>你可以使用<a href="#">gravatar.com</a>提供的头像或者使用本地上传头像。 </p>
                                <form class="am-form">
                                    <div class="am-form-group">
                                        <input type="file" id="user-pic">
                                        <p class="am-form-help">请选择要上传的文件...</p>
                                        <button type="button" class="am-btn am-btn-primary am-btn-xs">保存</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="am-panel am-panel-default">
                    <div class="am-panel-bd">
                        <div class="user-info">
                            <p>等级信息</p>
                            <div class="am-progress am-progress-sm">
                                <div class="am-progress-bar" style="width: 60%"></div>
                            </div>
                            <p class="user-info-order">当前等级：<strong>LV8</strong> 活跃天数：<strong>587</strong> 距离下一级别：<strong>160</strong></p>
                        </div>
                        <div class="user-info">
                            <p>信用信息</p>
                            <div class="am-progress am-progress-sm">
                                <div class="am-progress-bar am-progress-bar-success" style="width: 80%"></div>
                            </div>
                            <p class="user-info-order">信用等级：正常当前 信用积分：<strong>80</strong></p>
                        </div>
                    </div>
                </div>

            </div>

            <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                <form class="am-form am-form-horizontal">
                    <div class="am-form-group">
                        <label for="user-name" class="am-u-sm-3 am-form-label">姓名 / Name</label>
                        <div class="am-u-sm-9">
                            <input type="text" id="user-name" placeholder="姓名 / Name">
                            <small>输入你的名字，让我们记住你。</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-email" class="am-u-sm-3 am-form-label">电子邮件 / Email</label>
                        <div class="am-u-sm-9">
                            <input type="email" id="user-email" placeholder="输入你的电子邮件 / Email">
                            <small>邮箱你懂得...</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-phone" class="am-u-sm-3 am-form-label">电话 / Telephone</label>
                        <div class="am-u-sm-9">
                            <input type="email" id="user-phone" placeholder="输入你的电话号码 / Telephone">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-QQ" class="am-u-sm-3 am-form-label">QQ</label>
                        <div class="am-u-sm-9">
                            <input type="email" id="user-QQ" placeholder="输入你的QQ号码">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-weibo" class="am-u-sm-3 am-form-label">微博 / Twitter</label>
                        <div class="am-u-sm-9">
                            <input type="email" id="user-weibo" placeholder="输入你的微博 / Twitter">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-intro" class="am-u-sm-3 am-form-label">简介 / Intro</label>
                        <div class="am-u-sm-9">
                            <textarea class="" rows="5" id="user-intro" placeholder="输入个人简介"></textarea>
                            <small>250字以内写出你的一生...</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="button" class="am-btn am-btn-primary">保存修改</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- content end -->

</div>

<a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
  <span class="am-icon-btn am-icon-th-list"></span>
</a>

<footer>
  <hr>
  <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="/js/admin/jquery.min.js"></script>
<!--<![endif]-->
<script src="/js/admin/amazeui.min.js"></script>
<script src="/js/admin/app.js"></script>
</body>
</html>
