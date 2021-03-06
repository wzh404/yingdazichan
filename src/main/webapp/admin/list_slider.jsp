<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags" %>

<c:set var="js" value="http://119.254.84.18:8080/xeehoo/js"/>

<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>盈达资产 - 后台管理</title>
    <meta name="description" content="系统管理后台">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="stylesheet" href="/css/admin/amazeui.min.css"/>
    <link rel="stylesheet" href="/css/admin/admin.css">
</head>

<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->

<jsp:include page="header.jsp"/>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <jsp:include page="sidebar.jsp"/>
    <!-- sidebar end -->

    <!-- content start -->
    <form class="admin-content" id="form1" method="post" action="/admin/removeDict1">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">轮播图</strong> /
                <small>Slider</small>
            </div>
        </div>

        <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">状态:</div>
            <div class="am-u-sm-11 am-u-md-10">
                <div class="am-btn-group" id="query_dict">

                </div>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-check"><input type="checkbox"/></th>
                        <th class="table-id">ID</th>
                        <th class="table-title">名称</th>
                        <th class="table-title">URL</th>
                        <th class="table-type">图片</th>
                        <th class="table-set">发布时间</th>
                        <th class="table-set">状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sliders}" var="slider">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${slider.sliderId}</td>
                            <td><a href="javascript:add();">${slider.sliderName}</a></td>
                            <td>${slider.sliderUri}</td>
                            <td>${slider.sliderImg}</td>
                            <td><fmt:formatDate value="${slider.sliderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <c:if test="${slider.sliderStatus == 1}">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="/admin/changeSliderStatus?slider_id=${slider.sliderId}&slider_status=2">发布</a>
                                    </div>
                                </div>
                                </c:if>

                                <c:if test="${slider.sliderStatus == 2}">
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <a href="/admin/changeSliderStatus?slider_id=${slider.sliderId}&slider_status=1">暂停</a>
                                        </div>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <input type="hidden" name="dict_code" value="${dictCode}"/>
    </form>
</div>
<!-- content end -->
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">盈达资产</div>
        <div class="am-modal-bd">
            确定要删除选择的记录吗？
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>

<div class="am-modal am-modal-confirm" tabindex="-1" id="dic-modal-add">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"><span id="modal_add_title">dict</span>
        </div>
        <form id="save_update_dict_form" method="post" action="saveOrUpdateDict1"/>
        <div class="am-modal-bd">
            名称：<input type="text" id="dict_1_name" name="dict_1_name" value=""/><span style="color:red">*</span> &nbsp;&nbsp;
            代码：<input type="text" id="dict_1_code" name="dict_1_code" value=""/><span style="color:red">*</span>
            <input type="hidden" id="dict_1_id" name="dict_1_id" value=""/>
            <input type="hidden" name="dict_code" value="${dictCode}"/>
            <input type="hidden" id="dict_id" name="dict_id" value="0"/>
        </div>
        </form>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>

</div>

<jsp:include page="footer.jsp"/>
<script src="${js}/react.js" type="text/javascript"></script>
<script src="${js}/react-dom.js" type="text/javascript"></script>
<script src="/js/validate-code.react.js?12357"></script>
<script src="/js/ydzc.js?12357"></script>
<script src="/js/ydzc-validate.js?12357"></script>
<script type="text/javascript">
    var type_query = {
        "name": 'stat',
        'select': {
            'method': 'default',
            'options': [{"name": "全部", "value": "0"},
                        {"name": "未发布", "value": "1"},
                        {"name": "已发布", "value": "2"}]
        },
        'server': {
            'value': '${stat}',
            'uri': '/admin/listSlider?q=1'
        }
    }

    test_factor('query_dict', type_query);
</script>
</body>
</html>
