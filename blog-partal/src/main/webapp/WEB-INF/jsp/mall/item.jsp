<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${item.title }</title>
<script>
	var jdpts = new Object();
	jdpts._st = new Date().getTime();
</script>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/mall/css/taotao.css"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/mall/css/pshow.css"
	media="all" />

<script type="text/javascript">
	window.pageConfig = {
		compatible : true,
		product : {
			skuid : 1221882,
			name : '\u957f\u8679\uff08\u0043\u0048\u0041\u004e\u0047\u0048\u004f\u004e\u0047\uff09\u004c\u0045\u0044\u0034\u0032\u0035\u0033\u0038\u0045\u0053\u0020\u0034\u0032\u82f1\u5bf8\u0020\u7a84\u8fb9\u84dd\u5149\u004c\u0045\u0044\u6db2\u6676\u7535\u89c6\uff08\u9ed1\u8272\uff09',
			skuidkey : 'E804B1D153D29E36088A33A134D85EEA',
			href : 'http://item.jd.com/1221882.html',
			src : 'jfs/t304/157/750353441/93159/e4ee9876/54227256N20d4f5ec.jpg',
			cat : [ 737, 794, 798 ],
			brand : 20710,
			nBrand : 20710,
			tips : false,
			type : 1,
			venderId : 0,
			shopId : '0',
			TJ : '0',
			specialAttrs : [ "HYKHSP-0", "isHaveYB", "isSelfService-0",
					"isWeChatStock-0", "isCanUseJQ", "isOverseaPurchase-0",
					"YuShou", "is7ToReturn-1", "isCanVAT" ],
			videoPath : '',
			HM : '0'
		}
	};
</script>
</head>
<body version="140120">
	<script type="text/javascript">
		try {
			(function(flag) {
				if (!flag) {
					return;
				}
				if (window.location.hash == '#m') {
					var exp = new Date();
					exp.setTime(exp.getTime() + 30 * 24 * 60 * 60 * 1000);
					document.cookie = "pcm=1;expires=" + exp.toGMTString()
							+ ";path=/;domain=jd.com";
					return;
				} else {
					var cook = document.cookie.match(new RegExp(
							"(^| )pcm=([^;]*)(;|$)"));
					var flag = false;
					if (cook && cook.length > 2 && unescape(cook[2]) == "1") {
						flag = true;
					}
				}
				var userAgent = navigator.userAgent;
				if (userAgent) {
					userAgent = userAgent.toUpperCase();
					if (userAgent.indexOf("PAD") > -1) {
						return;
					}
					var mobilePhoneList = [ "IOS", "IPHONE", "ANDROID",
							"WINDOWS PHONE" ];
					for ( var i = 0, len = mobilePhoneList.length; i < len; i++) {
						if (userAgent.indexOf(mobilePhoneList[i]) > -1) {
							var url = "http://m.jd.com/product/"
									+ pageConfig.product.skuid + ".html";
							if (flag) {
								pageConfig.product.showtouchurl = true;
							} else {
								window.location.href = url;
							}
							break;
						}
					}
				}
			})((function() {
				var json = {
					"6881" : 3,
					"1195" : 3,
					"10011" : 3,
					"6980" : 3,
					"12360" : 3
				};
				if (json[pageConfig.product.cat[0] + ""] == 1
						|| json[pageConfig.product.cat[1] + ""] == 2
						|| json[pageConfig.product.cat[2] + ""] == 3) {
					return false;
				} else {
					return true;
				}
			})());
		} catch (e) {
		}
	</script>
	<!-- header start -->
	<!-- header end -->
	<div class="w">
		<div class="breadcrumb">
			<strong><a href="#">博客云</a></strong><span>&nbsp;&gt;&nbsp;<a
				href="http://localhost:8085/mall?q=%E6%95%99%E7%A8%8B">积分商城</a>
		</div>
	</div>
	<!--breadcrumb end-->
	<div class="w">
		<div id="product-intro">
			<div id="name">
				<h1>${item.title }</h1>
				<strong>${item.sellPoint}</strong>
			</div>
			<!--name end-->
			<script type='text/javascript'>
				var warestatus = 1;
				var eleSkuIdKey = [];
			</script>
			<div class="clearfix" clstag="shangpin|keycount|product|share">
				<script type="text/javascript">
					pageConfig.product.marketPrice = '';
				</script>
				<ul id="summary">
					<li id="summary-price">
						<div class="dt">K&nbsp;币&nbsp;：</div>
						<div class="dd">
							<strong class="p-price" id="jd-price">￥<fmt:formatNumber
									groupingUsed="false" maxFractionDigits="2"
									minFractionDigits="2" value="${item.price / 100 }" />
							</strong> <a id="notice-downp" href="#none" target="_blank"
								clstag="shangpin|keycount|product|jiangjia">(降价通知)</a>
						</div>
					</li>
					<li id="summary-market"><div class="dt">商品编号：</div>
						<div class="dd">
							<span>${item.id }</span>
						</div></li>
					<li id="summary-grade">
						<div class="dt">商品评分：</div>
						<div class="dd">
							<span class="star  sa0"></span> <a href="#comment"></a>
						</div>
					</li>

					<li id="summary-service">
						<div class="dt">物&#x3000;&#x3000;流：</div>
						<div class="dd">无需物流, 系统自动发货。</div>
					</li>

				</ul>
				<!--summary end-->
				<div id="brand-bar" clstag="shangpin|keycount|product|btn-coll">
					<dl class="slogens">
						<dt>淘淘·正品保证</dt>
						<dd class="fore1">
							<a target="_blank"
								href="http://help.360buy.com/help/question-67.html"><b></b><span>货到</span><span>付款</span></a>
						</dd>
						<dd class="fore2"></dd>
						<dd class="fore3">
							<a target="_blank"
								href="http://help.360buy.com/help/question-65.html"><b></b><span>满39</span><span>免运费</span></a>
						</dd>
						<dd class="fore4"></dd>
						<dd class="fore5">
							<a target="_blank"
								href="http://help.360buy.com/help/question-97.html"><b></b><span>售后</span><span>上门</span></a>
						</dd>
					</dl>
					<div class="seller hide">
						<p class="seller-link"></p>
						<p id="evaluate">
							<em class="dt">服务评价：</em> <span class="heart-white"><span
								class="heart-red h4">&nbsp;</span></span> <em class="evaluate-grade"></em>
						</p>
					</div>
				</div>
				<!--brand-bar-->
				<ul id="choose" clstag="shangpin|keycount|product|choose">
					<li id='choose-type'></li>
					<script type="text/javascript">
						var ColorSize = [ {
							"SkuId" : 1221882,
							"Size" : "",
							"Color" : ""
						} ];
					</script>
					<li id="choose-amount">
						<div class="dt">购买数量：</div> <font color="black"><strong>
								1 </strong></font> <!-- <div class="dd">
							<div class="wrap-input">
								<a class="btn-reduce" href="javascript:;"
									onclick="setAmount.reduce('#buy-num')">减少数量</a> <a
									class="btn-add" href="javascript:;"
									onclick="setAmount.add('#buy-num')">增加数量</a> <input
									class="text" id="buy-num" value="1"
									onkeyup="setAmount.modify('#buy-num');" />
							</div>
						</div> -->
					</li>
					<li id="choose-result"><div class="dt"></div>
						<div class="dd"></div></li>
					<li id="choose-btns">
						<div id="choose-btn-append" class="btn">
							<a class="btn-append " id="InitCartUrl" href="javascript:void(0)"
								onclick="changScore(${item.price})"
								clstag="shangpin|keycount|product|initcarturl">立即兑换<b></b></a>
						</div>
						<div id="choose-btn-easybuy" class="btn"></div>
						<div id="choose-btn-divide" class="btn"></div>
					</li>
				</ul>
				<!--choose end-->
				<span class="clr"></span>
			</div>

			<div id="preview">
				<div id="spec-n1" class="jqzoom"
					clstag="shangpin|keycount|product|spec-n1">
					<img data-img="1" width="350" height="350" src="${item.images[0]}"
						alt="${item.title}" jqimg="${item.images[0]}" />
				</div>

				<div id="spec-list" clstag="shangpin|keycount|product|spec-n5">
					<a href="javascript:;" class="spec-control" id="spec-forward"></a>
					<a href="javascript:;" class="spec-control" id="spec-backward"></a>
					<div class="spec-items">
						<ul class="lh">
							<c:forEach items="${item.images}" var="pic" varStatus="status">
								<c:choose>
									<c:when test="${status.index == 0 }">
										<li><img data-img="1" class="img-hover"
											alt="${item.title}" src="${pic}" width="50" height="50"
											data-url="${pic}"></li>
									</c:when>
									<c:otherwise>
										<li><img data-img="1" alt="${item.title}" src="${pic}"
											width="50" height="50" data-url="${pic}"></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div id="short-share">
					<div id="view-bigimg" class="fl">
						<b></b><a href="javascript:void(0)">查看大图</a>
					</div>
					<div id="share-list" class="share-list"
						clstag="shangpin|keycount|product|share">
						<div class="share-bd">
							<em class="share-hd">分享到：</em>
							<ul class="share-list-item clearfix">
								<li><a href="javascript:;" id="site-sina" title="分享到新浪微博">新浪微博</a></li>
								<li><a href="javascript:;" id="site-qq" title="分享到给QQ好友">QQ</a></li>
								<li><a href="javascript:;" id="site-qzone" title="分享到腾讯微博">腾讯微博</a></li>
								<li><a href="javascript:;" id="site-renren" title="分享到人人网">人人网</a></li>
								<li><a href="javascript:;" id="site-kaixing" title="分享到开心网">开心网</a></li>
								<li><a href="javascript:;" id="site-douban" title="分享到豆瓣">豆瓣</a></li>
								<li><a href="javascript:;" id="site-msn" title="分享给MSN好友">MSN</a></li>
								<li><a href="javascript:;" id="site-email" title="邮件分享给好友">邮件</a></li>
							</ul>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div>
		<div>
			<div id="product-detail" class="m m1" data-widget="tabs"
				clstag="shangpin|keycount|product|detail">
				<div class="mt">
					<ul class="tab">
						<li clstag="shangpin|keycount|product|pinfotab"
							data-widget="tab-item" class="curr"><a href="javascript:;">商品介绍</a></li>
						<li clstag="shangpin|keycount|product|pcanshutab"
							data-widget="tab-item"><a href="javascript:;">兑换结果</a></li>

					</ul>
				</div>
				<div class="mc" data-widget="tab-content" id="product-detail-1">

					<div class="detail-correction">
						<b></b>如果您发现商品信息不准确，欢迎纠错
					</div>
					<div id="item-desc" class="detail-content">
						${itemDesc.itemDesc }</div>
				</div>
				<div class="mc" data-widget="tab-content" id="product-detail-2">

				</div>

				<div id="state">
					<strong>权利声明：</strong><br />博客云上的所有商品信息、客户评价、商品咨询、网友讨论等内容，是博客云重要的经营资源，未经许可，禁止非法转载使用。
					<p>
						<b>注：</b>本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。
					</p>
				</div>
			</div>
			<!--product-detail end-->
		</div>
		<!--right end-->


		<span class="clr"></span>
	</div>

	<div id="loginAlert" class="alt login" style="display:none">
		<h2 class="h2">
			<em title="登录">博客云用户登录</em><cite></cite>
		</h2>
		<a href="javascript:void(0);" id="loginAlertClose" class="close"
			title="关闭"></a>
		<div class="cont">

			<ul class="uls form">
				<li><font color="red"><span id="error"></span></font></li>
				<li><label>用户名：</label> <INPUT id="username" name="username"
					class="ipt" type="text" placeholder="请输入用户名"">
						</P></li>
				<li><label>密码：</label> <INPUT id="password" name="password"
					class="ipt" type="password" placeholder="请输入密码"">
						</P></li>
				<li><label>&nbsp;</label><input type="button" id="loginSubmit"
					class="hand btn66x23" value="登 录" onclick="loginAjax()"><a
						title="忘记密码？" href="#">忘记密码？</a></li>
				<br />
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<li class="alg_c dev gray"><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>还不是博客云会员？
					<a title="免费注册" href="http://localhost:8087/sso/page/register"
					target="_blank">免费注册</a></li>
			</ul>

		</div>
	</div>

	<input type="hidden" id="cusername" value="${user.username }" />
	<!-- footer start -->

	<!-- footer end -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/mall/js/jquery-1.6.4.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/mall/js/base-2011.js"
		charset="utf-8"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/mall/js/base-v1.js"
		charset="utf-8"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/mall/js/lib-v1.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/mall/js/product.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/mall/js/iplocation_server.js"></script>

	<script type="text/javascript">
		var itemControl = {
			param : {
				descUrl : "/item/desc/",
				paramUrl : "/item/param/"
			},
			//请求商品描述
			getItemDesc : function(itemId) {
				$.get(itemControl.param.descUrl + itemId + ".html", function(
						data) {
					//返回商品描述的html，直接显示到页面
					$("#item-desc").append(data);
				}, 'html');
			},
			//参数请求flag
			haveParam : false,
			//请求规格参数
			getItemParam : function(itemId) {
				//如果没有查询过规格参数，就做请求
				if (!itemControl.haveParam) {
					$.get(itemControl.param.paramUrl + itemId + ".html",
							function(data) {
								//返回商品规格的html，直接显示到页面
								$("#product-detail-2").append(data);
								//更改flag状态
								itemControl.haveParam = true;
							}, 'html');
				}
			}
		};
		$(function() {
			//取商品id
			var itemId = "${item.id}";
			
			//延迟一秒加载商品描述信息
			setTimeout(function() {
				itemControl.getItemDesc(itemId);
			}, 1000);
		});
		
		
		//兑换积分的处理
		
		
	function changScore(price){
		
		var _ticket = $.cookie("TT_TOKEN_USER");
		var username=$.cookie("cusername");
		var itemId = "${item.id}";
		
		if (!_ticket) {
			//alert("请登录！");
			tipShow("#loginAlert");
		}else if(username==null || username==''){
			username=$("#cusername").val();
			$.post("${pageContext.request.contextPath}/score/redure",{price:price,username:username},function(data){
				if(data>0){
					alert("兑换成功");
					alert(itemId);
					//给商品规格参数tab页绑定事件
				$("#p-con-attr").bind("click", function() {

					itemControl.getItemParam(itemId);
				});
				}else{
					alert("积分不足，请充值");
					location="/score/buy/148128583372196";
				}
			});
		
		} else {

			$.post("${pageContext.request.contextPath}/score/redure",{price:price,username:username},function(data){
				
				if(data>0){
					alert("兑换成功,请在下面兑换结果查看");
						
					$("#p-con-attr").bind("click", function() {
						itemControl.getItemParam(itemId);
					});
					
				}else{
					alert("K币不足，请充值");
					location="/score/buy/148128583372196";
				}
			});
		}
			
		}
		
	function loginAjax() {
		var redirectUrl = "${redirect}";
		var username = $("#username").val();
		var password = $("#password").val();
		
		

	 	$.post("${pageContext.request.contextPath}/login",{username : username,password : password},function(data){
	 		
	 		if(data.data){
	 			tipHide("#loginAlert");
	 			$("#error").text("");
	 		}else{
	 			$("#error").text(data.msg);
	 		}
		
		}) ;
	 	
	}
	
	</script>
</body>
</html>