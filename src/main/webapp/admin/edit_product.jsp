<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags" %>
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
                <li><a href="#tab3">投资记录</a></li>
                <li><a href="#tab4">还款表现</a></li>
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
                    <table class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="table-id" style="width: 30%;">投标时间</th>
                            <th class="table-title" style="width: 40%;">投标人</th>
                            <th class="table-type" style="width: 30%;">投标金额</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${investments}" var="invest">
                            <tr>
                                <td><fmt:formatDate value="${invest.investtime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <td>${invest.username}</td>
                                <td><fmt:formatNumber value="${invest.amount}" type="currency"/></td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="am-tab-panel am-fade" id="tab4">
                    <table class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="table-id" style="width: 20%;">还款日期</th>
                            <th class="table-title" style="width: 30%;">还款类型</th>
                            <th class="table-type" style="width: 30%;">还款金额</th>
                            <th class="table-type" style="width: 20%;">还款状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${incomes}" var="income">
                            <tr>
                                <td>${income.username}</td>
                                <td><fmt:formatNumber value="${income.amount}" type="currency"/></td>
                                <td>${income.investtime}</td>
                                <td>${income.investtime}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="am-margin">
                <input type="hidden" id="productId" value="${product.productId}"/>
                <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="submitProduct();">提交保存</button>
            </div>
        </div>
        <!-- content end -->
    </div>
</div>
    <jsp:include page="footer.jsp"/>


    <script src="http://renben.neowave.com.cn:8080/xeehoo/js/react.js" type="text/javascript"></script>
    <script src="http://renben.neowave.com.cn:8080/xeehoo/js/react-dom.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/ydzc-validate.js?v=0.1.6"></script>

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

        var FormValidate ={
            createNew: function(){
                var validate = {};
                var mask = 0x00;
                var ins = $("input[data-validate]");
                var mm = ((0x01 << ins.length) - 1);

                validate.getPosition = function(id){
                    for (var i = 0; i < ins.length; i++) {
                        if (ins[i].id == id)
                            return i;
                    }
                    return -1;
                };

                validate.setMaskById = function(b, id){
                    var inx = validate.getPosition(id);
                    if (inx >= 0) {
                        validate.setMask(b, 0x01 << inx);
                    }
                };

                validate.setMask = function(r, v){
                    if (r)
                        mask |= v;
                    else
                        mask &= (mm - v);
                };

                validate.blur = function(){
                    console.log(ins.length);
                    for (var i = 0; i < ins.length; i++) {
                        var errMsg = '<span style="color:red;display:none" id="span_errmsg_' + ins[i].id + '"></span>';
                        $('#' + ins[i].id).parent().append(errMsg);

                        $('#' + ins[i].id).blur(function () {
                            var b = checkValue(this.id);
                            validate.setMaskById(b, this.id);
                        });
                    }
                };

                validate.submit = function(id){
                    if (mask != mm) {
                        return;
                    }

                    $(id).submit();
                }

                return validate;
            }
        }

        var v = FormValidate.createNew();
        v.blur();

        function submitProduct() {
            if ($('#productId').val() == '') {
                $('#form_product').attr("action", '/admin/saveProduct');
            }
            else{
                $('#form_product').attr("action", '/admin/updateProduct');
            }
            v.submit('#form_product');
        }
    </script>
</body>
</html>
