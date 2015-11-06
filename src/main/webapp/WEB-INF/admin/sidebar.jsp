<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
        <ul class="am-list admin-sidebar-list">
            <li><a href="admin-index.html"><span class="am-icon-home"></span> 首页</a></li>

            <c:if test="${sessionScope.staff.mainMenu('01')}">
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}">
                        <span class="am-icon-file"></span> 系统管理 <span
                            class="am-icon-angle-right am-fr am-margin-right"></span>
                    </a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                        <c:if test="${sessionScope.staff.subMenu('01', '0101')}">
                            <li><a href="admin-user.html" class="am-cf"><span class="am-icon-check"></span>
                                数据字典<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.staff.subMenu('01', '0102')}">
                            <li><a href="admin-help.html"><span class="am-icon-puzzle-piece"></span> 一级字典</a></li>
                        </c:if>

                        <c:if test="${sessionScope.staff.subMenu('01', '0103')}">
                            <li><a href="admin-gallery.html"><span class="am-icon-th"></span> 二级字典<span
                                    class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
                        </c:if>

                        <c:if test="${sessionScope.staff.subMenu('01', '0104')}">
                            <li><a href="admin-log.html"><span class="am-icon-calendar"></span> 用户管理</a></li>
                        </c:if>

                        <c:if test="${sessionScope.staff.subMenu('01', '0105')}">
                            <li><a href="admin-404.html"><span class="am-icon-user"></span>角色管理</a></li>
                        </c:if>

                        <c:if test="${sessionScope.staff.subMenu('01', '0106')}">
                            <li><a href="admin-404.html"><span class="am-icon-bug"></span>权限管理</a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <li class="admin-parent">
                <a class="am-cf" data-am-collapse="{target: '#collapse-page-nav'}">
                    <span class="am-icon-table"></span> 页面维护 <span
                        class="am-icon-angle-right am-fr am-margin-right"></span>
                </a>
                <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-page-nav">
                    <c:if test="${sessionScope.staff.subMenu('01', '0101')}">
                        <li><a href="admin-user.html" class="am-cf"><span class="am-icon-check"></span> 幻灯片<span
                                class="am-icon-bars am-fr am-margin-right admin-icon-yellow"></span></a></li>
                    </c:if>
                </ul>
            </li>

            <li><a href="admin-table.html"><span class="am-icon-bookmark"></span> 发布公告</a></li>

            <li><a href="/product"><span class="am-icon-pencil-square-o"></span> 产品管理</a></li>
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