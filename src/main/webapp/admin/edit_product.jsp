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
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">产品管理</strong> /
                <small>Product</small>
            </div>
        </div>

        <div class="am-tabs am-margin" data-am-tabs>
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab1">基本信息</a></li>
                <li><a href="#tab2">风控信息</a></li>
                <li><a href="#tab3" onclick="javascript:invest();">投资记录</a></li>
                <li><a href="#tab4" onclick="javascript:repay();">还款表现</a></li>
            </ul>

            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                    <form class="am-form" action="" id="form_product" method="post">
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">产品名称</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" name="productName" id="productName"
                                       data-validate="isNotNull=true" value="${product.productName}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填，不可重复</div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">产品类型</div>
                            <div class="am-u-sm-8 am-u-md-10">
                                <select data-am-selected="{btnSize: 'sm'}" id="productType" name="productType">
                                    <c:forEach items="${productTypes}" var="type">
                                        <option value="${type.dict1Code}">${type.dict1Name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">还款方式</div>
                            <div class="am-u-sm-8 am-u-md-10">
                                <select data-am-selected="{btnSize: 'sm'}" name="incomeMode">
                                    <option value="01">按月付息，到期还本</option>
                                </select>
                            </div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">起息方式</div>
                            <div class="am-u-sm-8 am-u-md-10">
                                <select data-am-selected="{btnSize: 'sm'}" name="interestWay">
                                    <option value="01">次日计息</option>
                                </select>
                            </div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">投资周期</div>
                            <div class="am-u-sm-8 am-u-md-10">
                                <input type="text" class="am-form-field am-input-sm" style="width:200px" id="investDayValue"
                                       data-validate="isNumber3=true" onchange="onchange_invest_day();">

                                <input type="hidden" name="investDay" id="investDay"  value="${product.investDay}"/>
                                <div id="div_item">
                                    <select data-am-selected="{btnSize: 'sm'}" id="investDayType" onchange="onchange_invest_day();">
                                        <option value="D">天</option>
                                        <option value="W">周</option>
                                        <option value="M">月</option>
                                        <option value="Y">年</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">年化收益率</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:200px" name="loanRate" id="loanRate"
                                       data-validate="isYearRate=true" value="${product.loanRate}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填</div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">募集周期</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:200px" name="raiseDays" id="raiseDays"
                                       data-validate="isNumber3=true" value="${product.raiseDays}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填(天)</div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">融资额度</div>
                            <div class="am-u-sm-8 am-u-md-10">
                                <input type="text" class="am-form-field am-input-sm" style="width:200px" id="totalAmount"
                                       name="totalAmount"  data-validate="isMoney=true" value="${product.totalAmount}">
                            </div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">最小投资额度</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:200px" name="minAmount" id="minAmount"
                                       data-validate="isMoney=true" value="${product.minAmount}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填(0为无限制)</div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">最小增加额度</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:200px" name="minAddAmount" id="minAddAmount"
                                       data-validate="isMoney=true" value="${product.minAddAmount}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填(0为无限制)</div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">最大投资额度</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" style="width:200px" name="maxAmount"  id="maxAmount"
                                       data-validate="isMoney=true" value="${product.maxAmount}">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6">*必填(0为无限制)</div>
                        </div>

                        <div class="am-g am-margin-top-sm">
                            <div class="am-u-sm-12 am-u-md-2 am-text-right admin-form-text">产品简介</div>
                            <div class="am-u-sm-12 am-u-md-10">
                                <textarea rows="6" placeholder="请输入产品简介" name="productDesc">${product.productDesc}</textarea>
                            </div>
                        </div>

                        <div class="am-g am-margin-top-sm">
                            <div class="am-u-sm-12 am-u-md-2 am-text-right admin-form-text">借款用途</div>
                            <div class="am-u-sm-12 am-u-md-10">
                                <textarea rows="6" placeholder="请输入借款用途" name="loanPurpose">${product.loanPurpose}</textarea>
                            </div>
                        </div>
                        <input type="hidden" id="productId" name="productId" value="${product.productId}"/>
                    </form>
                </div>

                <div class="am-tab-panel am-fade" id="tab2">
                    <form class="am-form">
                        <div class="am-g am-margin-top-sm">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                征信情况
                            </div>
                            <div class="am-u-sm-8 am-u-md-4 am-u-end">
                                <textarea rows="4"></textarea>
                            </div>
                        </div>

                        <div class="am-g am-margin-top-sm">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                控制措施
                            </div>
                            <div class="am-u-sm-8 am-u-md-4 am-u-end">
                                <textarea rows="8"></textarea>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="am-tab-panel am-fade" id="tab3">
                </div>

                <div class="am-tab-panel am-fade" id="tab4">
                </div>
            </div>

            <div class="am-margin">
                <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="submitProduct();">提交保存</button>
                <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="submitSettleProduct();">满标</button>
            </div>
        </div>
        <!-- content end -->
    </div>
</div>
    <jsp:include page="footer.jsp"/>

    <script src="${js}/react.js" type="text/javascript"></script>
    <script src="${js}/react-dom.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/ydzc-validate.js?v=0.1.6"></script>
    <script type="text/javascript" src="/js/validate-form.js?v=0.1.6"></script>

    <script type="text/javascript">
        function setSelection(id, code){
             $('#'+id).val(code);
        }

        function onchange_invest_day(){
            $('#investDay').val($('#investDayValue').val() + $('#investDayType').val());
        }

        function setInvestDayValueAndType(v){
            $('#investDayValue').val(v.substr(0, v.length - 1));
            setSelection('investDayType', v.substr(v.length - 1, 1))
        }

        setSelection('productType', '${product.productType}');
        setSelection('incomeMode', '${product.incomeMode}');
        setSelection('interestWay', '${product.interestWay}');
        setInvestDayValueAndType('${product.investDay}');


        var v = create_form_validate();

        function submitProduct() {
            if ($('#productId').val() == '0') {
                $('#form_product').attr("action", '/admin/saveProduct');
            }
            else{
                $('#form_product').attr("action", '/admin/updateProduct');
            }
            v.submit('#form_product');
        }

        function submitSettleProduct(){
            if ($('#productId').val() != '0'){
                $('#form_product').attr("action", '/admin/settleAccount');
                $('#form_product').submit();
            }
        }

        var currentTab;
        function repay(){
            currentTab = "#tab4";
            toPage('/admin/listProductRepay?page=0');
        }

        function invest(){
            currentTab = "#tab3";
            toPage('/admin/listProductInvestment?page=0');
        }

        function toPage(url){
            $.ajax({
                url: url,
                data: {product_id: ${product.productId}},
                type: "get",
                dataType: "html",
                async: false,
                success: function success(html) {
                    console.log(html);
                    $(currentTab).html(html);
                },
                error: function error(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus + ": " + XMLHttpRequest.status);
                }
            });
        }
    </script>
</body>
</html>
