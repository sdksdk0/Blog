<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单结算页</title>
<!--结算页面样式-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/mall/css/base.css"
	media="all" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/mall/css/order-commons.css"
	source="widget" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/mall/js/jquery-1.6.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/mall/js/base.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/mall/js/order.common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/mall/js/jquery.checkout.js"></script>
</head>
<body id="mainframe">


	<form id="orderForm" class="text" action="/order/create.html"
		method="post">
		<input type="hidden" name="paymentType" value="1" />
		<c:forEach items="${cartList }" var="cart" varStatus="status">
			<c:set var="totalPrice"
				value="${ totalPrice + (cart.price * cart.num)}" />
			<input type="hidden" name="orderItems[${status.index}].itemId"
				value="${cart.id}" />
			<input type="hidden" name="orderItems[${status.index}].num"
				value="${cart.num }" />
			<input type="hidden" name="orderItems[${status.index}].price"
				value="${cart.price}" />
			<input type="hidden" name="orderItems[${status.index}].totalFee"
				value="${cart.price * cart.num}" />
			<input type="hidden" name="orderItems[${status.index}].title"
				value="${cart.title}" />
			<input type="hidden" name="orderItems[${status.index}].picPath"
				value="${cart.image}" />
		</c:forEach>
		<input type="hidden" name="payment"
			value="<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2" value="${totalPrice/100 }"/>" />
		<font color="red">收货人:<input type="text" name="orderShipping.receiverName"  id="receiverName" value="${username }"  /> (注，若此处为空请在自己填写您的收货的用户名)</font>
		<input
			type="hidden" name="orderShipping.receiverState" value="北京" /> <input
			type="hidden" name="orderShipping.receiverCity" value="北京" /> <input
			type="hidden" name="orderShipping.receiverDistrict" value="默认地址" /> <input
			type="hidden" name="orderShipping.receiverAddress" value="默认地址" />
	</form>

	<!-- main -->
	<div id="container">
		<div id="content" class="w">
			<div class="m">
				<div class="mt">
					<h2>填写并核对订单信息</h2>
				</div>
				<div class="mc">
					<div class="checkout-steps">


						<div id="shipAndSkuInfo">
							<div id="payShipAndSkuInfo">
								<div class="step-tit">
									<h3>支付方式</h3>
								</div>
								<div class="step-cont">
									<div class="payment-list" id="">
										<div class="list-cont">
											<ul id="payment-list">
												<input type="hidden" id="instalmentPlan" value="false">
												<li style="cursor: pointer;" onclick="save_Pay(4);">

													<div class="payment-item  item-selected online-payment "
														for="pay-method-4" payname="在线支付" payid="1">
														<b></b> 在线支付 <font class="whiteBarSpanClass hide"
															color="#FF6600"></font> <span
															class="qmark-icon qmark-tip"
															data-tips="即时到帐，支持绝大数银行借记卡及部分银行信用卡 <a href='http://www.jd.com/help/onlinepay.aspx' target='_blank' class='ftx-05'> 查看银行及限额</a>"></span>

													</div>
												</li>


												<!--div id="shipment"></div-->
												<script>
													$('.online-payment')
															.hover(
																	function() {
																		$(this)
																				.addClass(
																						'payment-item-hover');
																	},
																	function() {
																		$(this)
																				.removeClass(
																						'payment-item-hover');
																	});
												</script>
											</ul>
										</div>
									</div>
								</div>
								<!--/ /widget/payment-step/payment-step.tpl -->
								<div class="step-tit">
									<h3>送货清单</h3>
									<div class="extra-r">
										<a href="/cart/cart.html" id="cartRetureUrl"
											class="return-edit ftx-05">返回修改购物车</a>
									</div>
								</div>
								<div class="step-cont" id="skuPayAndShipment-cont">
									<!--添加商品清单  zhuqingjie -->
									<div class="shopping-lists" id="shopping-lists">
										<div class="shopping-list ABTest">
											<div class="goods-list">
												<!--配送方式-->
												<h4 class="vendor_name_h" id="0">商家：淘淘商城</h4>
												<div class="goods-suit goods-last">
													<c:forEach items="${cartList }" var="cart">
														<div class="goods-item goods-item-extra">

															<div class="p-img">
																<a target="_blank" href="/item/${cart.id}.html"> <img
																	src="${cart.image}" alt=""></a>
															</div>
															<div class="goods-msg">
																<div class="p-name">
																	<a href="/item/${cart.id}.html" target="_blank">
																		${cart.title } </a>
																</div>
																<div class="p-price">
																	<!--增加预售金额显示 begin   预售分阶段支付类型（1：一阶梯全款支付；2：一阶梯定金支付(全款或定金可选)；3：三阶梯仅定金支付） -->
																	<strong>￥<fmt:formatNumber
																			groupingUsed="false" maxFractionDigits="2"
																			minFractionDigits="2" value="${cart.price / 100 }" /></strong>
																	<!--增加预售金额显示 end-->
																	<span class="ml20"> x${cart.num} </span> <span
																		class="ml20 p-inventory" skuId="11555193">有货</span>
																</div>
																<i class="p-icon p-icon-w"></i><span class="ftx-04">7天无理由退货</span>
															</div>
															<div class="clr"></div>
														</div>
													</c:forEach>
												</div>
											</div>
											<!--goods-list 结束-->
											<div class="dis-modes">
												<!--配送方式-->
												<div class="mode-item mode-tab">
													<h4>
														配送方式：（<a id="jd-goods-item" class="ftx-05 alink"
															href="#none">对应商品</a>）
													</h4>
													<div class="mode-tab-nav">
														<ul>
															<li class="mode-tab-item " id="jd_shipment_item"
																onclick="doSwithTab('pay')"><span
																id="jdShip-span-tip" class="m-txt">无需快递<i
																	class='qmark-icon qmark-tip'
																	data-tips='由淘淘公司负责配送，速度很快，还接受上门刷卡付款服务'></i></span><b></b></li>
														</ul>
													</div>
												</div>
											</div>
											<!--dis-modes 结束-->
											<div class="clr"></div>
											<div class="freight-cont">
												<strong class="ftx-01" style="color: #666"
													freightByVenderId="0" popJdShipment="false">免运费</strong>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
						<!--  /widget/invoice-step/invoice-step.tpl -->
						<div class="step-tit">
							<h3>发票信息</h3>
						</div>
						<div class="step-content"></div>
						<div class="order-summary">
							<!--  预售 计算支付展现方式 begin -->
							<div class="statistic fr">
								<div class="list">
									<span> 总商品金额： </span> <em class="price" id="warePriceId">¥<fmt:formatNumber
											value="${totalPrice / 100}" maxFractionDigits="2"
											minFractionDigits="2" groupingUsed="true" /></em>
								</div>
								<div class="list">
									<span>运费：</span> <em class="price" id="freightPriceId">
										￥0.00</em>
								</div>
								<div class="list">
									<span>应付总额：</span> <em class="price" id="sumPayPriceId"> ￥<fmt:formatNumber
											value="${totalPrice / 100}" maxFractionDigits="2"
											minFractionDigits="2" groupingUsed="true" /></em>
								</div>
							</div>
							<div class="clr"></div>
						</div>
					</div>
				</div>
				<!--/ /widget/order-summary/order-summary.tpl -->

				<!--  /widget/checkout-floatbar/checkout-floatbar.tpl -->
				<div class="trade-foot">
					<div id="checkout-floatbar" class="group">
						<div class="ui-ceilinglamp checkout-buttons">
							<div class="sticky-placeholder hide" style="display: none;">
							</div>
							<div class="sticky-wrap">
								<div class="inner">
									<button type="submit" class="checkout-submit btn-1"
										id="order-submit" onclick="checkUsername();">提交订单</button>
									<span class="total">应付总额：<strong id="payPriceId">￥<fmt:formatNumber
												value="${totalPrice / 100}" maxFractionDigits="2"
												minFractionDigits="2" groupingUsed="true" /></strong>
									</span> <span id="checkCodeDiv"></span>

								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

		</div>
	</div>

</body>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>

	<script>
	
	function checkUsername(){
		var username=$("#receiverName").val();
		
		if(username!=''){
			$('#orderForm').submit();
			
		}else{
			alert("请输入你的用户名");
		}
	
	}
	
	
	
	</script>

</html>