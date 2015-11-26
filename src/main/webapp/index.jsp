<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="css" value="http://renben.neowave.com.cn:8080/ydzc/css"/>
<c:set var="img" value="http://renben.neowave.com.cn:8080/ydzc/img"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${css}/style.css" />
<link rel="stylesheet" type="text/css" href="${css}/base.css" />
</head>

<body>
<!--top start-->
<div class="The_total top">
	<div class="The_total1200">
        <div class="top_0">
            <ul>
                <li>您好${sessionScope.user.loginName}，欢迎来到盈时代！</li>
<c:if test="${sessionScope.user == null}">
                <li><a href="login.jsp" style="color:#eb953a;">登录</a></li>
                <li>|</li>
                <li><a href="../zc/index.html">注册</a></li>
</c:if>
<c:if test="${sessionScope.user != null}">
                <li><a href="/logout" style="color:#eb953a;">退出</a></li>
</c:if>
            </ul>
        </div>
        <div class="top_1">
            <ul>
                <li><a href="../bzzx/index.html" target="_blank">帮助中心</a></li>
                <li><a href="">手机版</a></li>
                <li><a href="">QQ</a></li>
                <li><a href="">微信</a></li>
            </ul>
        </div>
    </div>
</div>
<!--top end-->

<!--head  start-->
<div class="The_total head">
	<div class="The_total1200">
        <div class="logo"></div>
        <div class="fhsy">
        	<ul>
            	<li class="fhsy0"><a href="#">首页</a></li>
                <li><a href="../wdtz/index.html" target="_blank">我要投资</a></li>
                <li><a href="../zhzx/index.html" target="_blank">我的账户</a></li>
                <li><a href="../hdy/index.html" target="_blank">活动专区</a></li>
                <li><a href="" target="_blank">关于我们</a></li>
            </ul>
        </div>
    </div>
</div>
<!--head  end-->

<!--banner  start-->
<div class="The_total banner_b">
	<div class="banner"><img src="${img}/banner.png" /></div>
</div>
<!--banner  end-->
<div class=" The_total">
	<div class=" The_total1200 gg_"></div>
</div>
<!--content  start-->
<div class=" The_total">
	<div class=" The_total1200">
    	<div class="sy_left">
        	<div class="xr_">
                <h1>新人专享---新手标</h1>
                <div class="xrb_zx">
                    <div class="xrb_a">
                    	<div class="xrb_a0">
                        	<div class="xrb_a01">
                                <img src="${img}/xs_1.jpg" />
                                <p style=" font-weight:bold;">8.1%</p>
                                <p style=" color:#848587;">年化收益率</p>
                            </div>
                        </div>
                        <div class="xrb_a1">
                        	<div class="xrb_a1_0">雪花28号<span style=" margin-left:62px;"><img src="${img}/xs0_1.jpg" /></span></div>
                        </div>
                        <div class="xrb_a2">
                        	<div class="xrb_a20">
                            	<p style=" font-weight:bold;">1个月</p>
                                <p style=" color:#848587;">投资期限</p>
                            </div>
                            <div class="xrb_a20">
                            	<p style=" font-weight:bold;">1000元</p>
                                <p style=" color:#848587;">起投金额</p>
                            </div>
                            <div class="xrb_a20"><a href=""><img src="${img}/xs0_2.jpg" /></a></div>
                        </div>
                    </div>
                    <div class="xrb_a">
                    	<div class="xrb_a0">
                        	<div class="xrb_a01">
                                <img src="${img}/xs_2.jpg" />
                                <p style=" font-weight:bold;">7.5%</p>
                                <p style=" color:#848587;">年化收益率</p>
                            </div>
                        </div>
                        <div class="xrb_a1">
                        	<div class="xrb_a1_0">雪花28号<span style=" margin-left:62px;"><img src="${img}/xs0_1.jpg" /></span></div>
                        </div>
                        <div class="xrb_a2">
                        	<div class="xrb_a20">
                            	<p style=" font-weight:bold;">1个月</p>
                                <p style=" color:#848587;">投资期限</p>
                            </div>
                            <div class="xrb_a20">
                            	<p style=" font-weight:bold;">1000元</p>
                                <p style=" color:#848587;">起投金额</p>
                            </div>
                            <div class="xrb_a20"><a href=""><img src="${img}/xs0_2.jpg" /></a></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="xh_">
                <h1>雪花系列</h1>
                <div class="xh_0">
                	<p class="xh_0_"><img src="${img}/xh.jpg" /></p>
                    <div class="xh_01">
                    	<div class="xh_01a">雪花1号</div>
                        <div  class="xrb_a2">
                        	<div class="xrb_a20_">
                            	<p style="  color:#F00; font-size:18px;">10.2%</p>
                                <p style=" color:#848587; font-size:18px;">年化收益率</p>
                            </div>
                            <div class="xrb_a20_">
                            	<p style="  font-size:18px;">365天</p>
                                <p style=" color:#848587; font-size:18px;">投资期限</p>
                            </div>
                            <div class="xrb_a20_">
                            	<p style="  font-size:18px;">7,500元</p>
                                <p style=" color:#848587; font-size:18px;">标的总额</p>
                            </div>
                            <div class="xrb_a20_">
                            	<p style="  font-size:18px; margin-bottom:10px;"><img src="${img}/xs0_3.jpg" /></p>
                                <p style=" color:#848587; font-size:18px;">投资进度</p>
                            </div>
                            <div class="xrb_a20_0"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <h1>基金系列</h1>
                <div>
                    <div class="xh_0">
                	<p class="xh_0_"><img src="${img}/zj.jpg" /></p>
                    <div class="xh_01">
                    	<div class="xh_01a_">金算盘-----能源盈</div>
                        <div  class="xrb_a2">
                        	<div class="xrb_a21_">
                            	<p style="  color:#F00; font-size:18px;">8%</p>
                                <p style=" color:#848587; font-size:18px;">年化收益率</p>
                            </div>
                            <div class="xrb_a21_">
                            	<p style="  font-size:18px;">1个月</p>
                                <p style=" color:#848587; font-size:18px;">投资期限</p>
                            </div>
                            <div class="xrb_a21_">
                            	<p style="  font-size:18px;">302,000元</p>
                                <p style=" color:#848587; font-size:18px;">标的总额</p>
                            </div>
                            <div class="xrb_a21_">
                            	<p style="  font-size:18px; margin-bottom:10px;"><img src="${img}/xs0_3_0.jpg" /></p>
                                <p style=" color:#848587; font-size:18px;">投资进度</p>
                            </div>
                            <div class="xrb_a21">
                            	<p style=" padding-top:20px; text-indent:2em; color:#292929;">275，000（剩余金额）</p>
                            	<div class="xrb_a20_1">
                                	<p style=" float:left; color:#848587;">1000元起投</p>
                                    <a href="">立即抢购</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                    <div class="xh_0">
                	<p class="xh_0_"><img src="${img}/zj.jpg" /></p>
                    <div class="xh_01">
                    	<div class="xh_01a_">金算盘-----城建通</div>
                        <div  class="xrb_a2">
                        	<div class="xrb_a21_">
                            	<p style="  color:#F00; font-size:18px;">10%</p>
                                <p style=" color:#848587; font-size:18px;">年化收益率</p>
                            </div>
                            <div class="xrb_a21_">
                            	<p style="  font-size:18px;">3个月</p>
                                <p style=" color:#848587; font-size:18px;">投资期限</p>
                            </div>
                            <div class="xrb_a21_">
                            	<p style="  font-size:18px;">542,000元</p>
                                <p style=" color:#848587; font-size:18px;">标的总额</p>
                            </div>
                            <div class="xrb_a21_">
                            	<p style="  font-size:18px; margin-bottom:10px;"><img src="${img}/xs0_3_1.jpg" /></p>
                                <p style=" color:#848587; font-size:18px;">投资进度</p>
                            </div>
                            <div class="xrb_a21">
                            	<p style=" padding-top:20px; text-indent:2em; color:#292929;">333，600（剩余金额）</p>
                            	<div class="xrb_a20_1">
                                	<p style=" float:left; color:#848587;">1000元起投</p>
                                    <a href="">立即抢购</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
            <div>
                <h1>投行系列</h1>
                <div>
                    <div class="xh_0">
                	<p class="xh_0_"><img src="${img}/tz.jpg" /></p>
                    <div class="xh_01">
                    	<div class="xh_01a_0">土豪金-----农信盈</div>
                        <div  class="xrb_a2">
                        	<div class="xrb_a20_">
                            	<p style="  color:#F00; font-size:18px;">12%</p>
                                <p style=" color:#848587; font-size:18px;">年化收益率</p>
                            </div>
                            <div class="xrb_a20_">
                            	<p style="  font-size:18px;">12个月</p>
                                <p style=" color:#848587; font-size:18px;">投资期限</p>
                            </div>
                            <div class="xrb_a20_">
                            	<p style="  font-size:18px;">502,000元</p>
                                <p style=" color:#848587; font-size:18px;">标的总额</p>
                            </div>
                            <div class="xrb_a20_">
                            	<p style="  font-size:18px; margin-bottom:10px;"><img src="${img}/xs0_3.jpg" /></p>
                                <p style=" color:#848587; font-size:18px;">投资进度</p>
                            </div>
                            <div class="xrb_a20_0"></div>
                        </div>
                    </div>
                </div>
                    <div class="xh_0">
                	<p class="xh_0_"><img src="${img}/tz.jpg" /></p>
                    <div class="xh_01">
                    	<div class="xh_01a_0">土豪金-----文娱通</div>
                        <div  class="xrb_a2">
                        	<div class="xrb_a21_">
                            	<p style="  color:#F00; font-size:18px;">14%</p>
                                <p style=" color:#848587; font-size:18px;">年化收益率</p>
                            </div>
                            <div class="xrb_a21_">
                            	<p style="  font-size:18px;">12个月</p>
                                <p style=" color:#848587; font-size:18px;">投资期限</p>
                            </div>
                            <div class="xrb_a21_">
                            	<p style="  font-size:18px;">500，000元</p>
                                <p style=" color:#848587; font-size:18px;">标的总额</p>
                            </div>
                            <div class="xrb_a21_">
                            	<p style="  font-size:18px; margin-bottom:10px;"><img src="${img}/xs0_3_2.jpg" /></p>
                                <p style=" color:#848587; font-size:18px;">投资进度</p>
                            </div>
                            <div class="xrb_a21">
                            	<p style=" padding-top:20px; text-indent:2em; color:#292929;">468，543（剩余金额）</p>
                            	<div class="xrb_a20_1">
                                	<p style=" float:left; color:#848587;">1000元起投</p>
                                    <a href="">立即抢购</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
        <div class="sy_right">
        	<div class="wd_zx">
            	<div class="wd_">
                	<h1>网贷行业资讯</h1>
                    <p class="gd_" style=" float:right; color:#848587;">更多>></p>
                </div>
                <div class="zx_">
                	<ul>
                    	<li><span></span><a href="">P2P平台应专注细分 提升竞争力</a></li>
                        <li><span></span><a href="">众多网贷平台不愿被称为P2P 转型理财管理</a></li>
                        <li><span></span><a href="">P2P协调处处长：网贷监管需遵循三个原则</a></li>
                        <li><span></span><a href="">助力中小微企业发展, P2P网贷成功倒逼传统金融改革</a></li>
                        <li><span></span><a href="">成交量迫近1200亿元 P2P网贷继续狂飙</a></li>
                        <li><span></span><a href="">消费金融和互联网P2P金融有什么差别？</a></li>
                        <li><span></span><a href="">发展网络借贷 推进众筹融资发展</a></li>
                    </ul>
                </div>
            </div>
            <div class="wd_zx_">
            	<div class="wd_">
                	<h1>大家都在抢</h1>
                </div>
                <div class="zx_">
                	<p><span>雪花32号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">X*****购买了此产品</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">19分钟前</span></p>
                    <p><span>雪花136号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">花*****购买了此产品</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">20分钟前</span></p>
                    <p><span>雪花58号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">李*****购买了此产品</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">20分钟前</span></p>
                    <p><span>雪花02号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">Z*****购买了此产品</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=" color:#848587;">21分钟前</span></p>
                </div>
            </div>
            <div class="wd_zx">
            	<div class="wd_">
                	<h1>理财风云榜</h1>
                </div>
                <div class="zx_">
                	<table cellpadding="0" cellspacing="0">
                    	<colgroup>
                        	<col width="" />
                            <col width="" />
                            <col width="" />
                        </colgroup>
                        <tr>
                        	<td>排名</td>
                            <td>会员</td>
                            <td>投资金额</td>
                        </tr>
                        <tr>
                        	<td><img  src="${img}/pm_1.jpg" /></td>
                            <td class="zx_a">Z******</td>
                            <td class="zx_a">26,925,000.00</td>
                        </tr>
                        <tr>
                        	<td><img  src="${img}/pm_2.jpg" /></td>
                            <td class="zx_a">啊******</td>
                            <td class="zx_a">15,698,000.00</td>
                        </tr>
                        <tr>
                        	<td><img  src="${img}/pm_3.jpg" /></td>
                            <td class="zx_a">LI******</td>
                            <td class="zx_a">10,021,000.00</td>
                        </tr>
                        <tr>
                        	<td class="zx_b">4</td>
                            <td class="zx_a">王******</td>
                            <td class="zx_a">8,256,000.00</td>
                        </tr>
                        <tr>
                        	<td class="zx_b">5</td>
                            <td class="zx_a">I*******</td>
                            <td class="zx_a">4,883,000.00</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="wd_zx">
            	<div class="wd_">
                	<h1>投资对比</h1>
                </div>
                <div class="zx_">
                	<div style=" margin-top:10px; margin-left:10px;"><img src="${img}/qs_.jpg" /></div>
                </div>
            </div>
            <div style="margin-left:90px; width:206px; height:224px; float:left; margin-top:23px;"><img src="${img}/gg_.jpg" /></div>
        </div>
    </div>
</div>
<!--content  end-->

<!--new  start-->
<div class="The_total">
    <div class="The_total1200" >
    	<h1 style="font-size:18px; margin-top:10px;">新手指导</h1>
    </div>
</div>
<div class=" The_total new_">
	<p style="text-align:center;"><img src="${img}/new_a.jpg" usemap="#Map" border="0" />
      <map name="Map" id="Map">
        <area shape="rect" coords="6,23,123,144" href="#" />
        <area shape="rect" coords="256,11,381,144" href="#" />
        <area shape="rect" coords="498,12,627,150" href="#" />
        <area shape="rect" coords="750,10,870,142" href="#" />
        <area shape="rect" coords="1002,4,1122,144" href="#" />
      </map>
    </p>
</div>
<!--new  start-->
<div class="The_total">
	<div class="The_total1200">
    	<div class="t_l">
        	<div class="wd_0">
                 <h1>关于我们</h1>
            </div>
            <div>
            	<dl>
                	<dt><img src="${img}/gywm_a.jpg" /></dt>
                    <dd>安全联盟  实名验证</dd>
                </dl>
                <dl>
                	<dt><img src="${img}/gywm_b.jpg" /></dt>
                    <dd>中国电子金融产业联盟会员单位</dd>
                </dl>
                <dl>
                	<dt><img src="${img}/gywm_c.jpg" /></dt>
                    <dd>中国互联网协会会员单位</dd>
                </dl>
                <dl>
                	<dt><img src="${img}/gywm_d.jpg" /></dt>
                    <dd>中国投资协会会员单位</dd>
                </dl>
            </div>
            <div class="clear"></div>
            <p style="color:#848587;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;盈达资产成立于2014年，注册资本金一亿元，总部设立于北京金融街，依托股东的央企、省国投、国际经合组织、大型产业集团的雄厚背景和实力，定位于为高净值客户提供全面金融解决方案的卓越财富管理机构，致力于成为中国领先的集投资银行、资产管理、综合财富管理为一体的综合金融服务平台。</p>
        </div>
        <div class="t_c">
        	<div class="wd_">
                 <h1>媒体报道</h1>
            </div>
            <div class="mt_">
            	<img src="${img}/mt_1.jpg" usemap="#Map2" border="0" />
                <map name="Map2" id="Map2">
                  <area shape="rect" coords="-5,31,74,63" href="#" />
                  <area shape="rect" coords="0,2,72,27" href="#" />
                </map>
                <img src="${img}/mt_2.jpg" usemap="#Map3" border="0" />
                <map name="Map3" id="Map3">
                  <area shape="rect" coords="1,2,103,33" href="#" />
                  <area shape="rect" coords="3,38,105,68" href="#" />
                </map>
                <img src="${img}/mt_3.jpg" usemap="#Map4" border="0" />
                <map name="Map4" id="Map4">
                  <area shape="rect" coords="3,-1,105,24" href="#" />
                  <area shape="rect" coords="2,29,103,61" href="#" />
                </map>
                <img src="${img}/mt_4.jpg" usemap="#Map5" border="0" />
                <map name="Map5" id="Map5">
                  <area shape="rect" coords="26,0,125,36" href="#" />
                  <area shape="rect" coords="7,46,138,77" href="#" />
                </map>
                <img src="${img}/mt_5.jpg" usemap="#Map6" border="0" />
                <map name="Map6" id="Map6">
                  <area shape="rect" coords="4,3,121,30" href="#" />
                  <area shape="rect" coords="16,41,113,71" href="#" />
                </map>
            </div>
        </div>
        <div class="t_r">
        	<div class="wd_">
                 <h1>互联网金融研究</h1>
            </div>
            <p>近年来，网上借贷成了主流的趋势， P2C网络贷款或称个体对企业信贷。网络贷款中介帮助确定借贷的条款和准备好必需的法律文本。更重要的是，中介网络平台的可以帮助借款人通过和其他借款人一起分担一笔借款额度的方式来分散风险，也帮助借款人在充分比较的信息中选择有吸引力的利率条件。而同时，收取一定的服务费作为中介平台的回报。</p>
        </div>
    </div>
</div>

<div class="clear"></div>
<br />
<jsp:include page="footer.jsp"/>
</body>
</html>
