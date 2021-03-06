<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
   <meta http-equiv="expires" content="0"> 
   <meta name="format-detection" content="telephone=no">  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
   <meta name="format-detection" content="telephone=no">
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/static/mall/css/base.css">
   <link href="${pageContext.request.contextPath}/static/mall/css/purchase.2012.css?v=201410141639" rel="stylesheet" type="text/css">
   <script>
   	var pageConfig  = {};
   </script>
<body> 
<!--shortcut start-->

<!--shortcut end-->

<div class="w cart">
	<div class="cart-hd group">
		<h2>我的购物车</h2>
	</div>
	<div id="show">
	
<div class="cart-frame">
    <div class="tl"></div>
    <div class="tr"></div>
</div>
<div class="cart-inner">
    <div class="cart-thead clearfix">
        <div class="column t-checkbox form"><input data-cart="toggle-cb" name="toggle-checkboxes" id="toggle-checkboxes_up" type="checkbox" checked="" value=""><label for="toggle-checkboxes_up">全选</label></div>
        <div class="column t-goods">商品</div>
        <div class="column t-price">价格</div>
        <div class="column t-promotion">优惠</div>
        <div class="column t-inventory">库存</div>
        <div class="column t-quantity">数量</div>
        <div class="column t-action">操作</div>
    </div>
    <div id="product-list" class="cart-tbody">
        <!-- ************************商品开始********************* -->
        <c:set var="totalPrice" value="0"></c:set>
        <c:forEach items="${cartList}" var="cart">
        	<c:set var="totalPrice"  value="${ totalPrice + (cart.price * cart.num)}"/>
	        <div id="product_11345721" data-bind="rowid:1" class="item item_selected ">
		        <div class="item_form clearfix">
		            <div class="cell p-checkbox"><input data-bind="cbid:1" class="checkbox" type="checkbox" name="checkItem" checked="" value="11345721-1"></div>
		            <div class="cell p-goods">
		                <div class="p-img">
		                	<a href="/item/${cart.id }.html" target="_blank">
		                		<img clstag="clickcart|keycount|xincart|p-imglistcart" src="${cart.image}" alt="${cart.title}" width="52" height="52">
		                	</a>
		                </div>    
		                <div class="p-name">
		                	<a href="/item/${cart.id }.html" clstag="clickcart|keycount|xincart|productnamelink" target="_blank">${cart.title}</a>
		    
		                </div>    
		            </div>
		            <div class="cell p-price"><span class="price">¥<fmt:formatNumber groupingUsed="false" value="${cart.price / 100}" maxFractionDigits="2" minFractionDigits="2"/> </span></div>
		            <div class="cell p-promotion">
		            </div>
		            <div class="cell p-inventory stock-11345721">有货</div>
		            <div class="cell p-quantity" for-stock="for-stock-11345721">
		                <div class="quantity-form" data-bind="">
		                    <a href="javascript:void(0);" class="decrement" clstag="clickcart|keycount|xincart|diminish1" id="decrement">-</a>
		                    <input type="text" class="quantity-text" itemPrice="${cart.price}" itemId="${cart.id}" value="${cart.num }" id="changeQuantity-11345721-1-1-0"  readonly="readonly">
		                    <a href="javascript:void(0);" class="increment" clstag="clickcart|keycount|xincart|add1" id="increment">+</a>
		                </div>
		            </div>
		            <div class="cell p-remove"><a id="remove-11345721-1" data-more="removed-87.20-1" clstag="clickcart|keycount|xincart|btndel318558" class="cart-remove" href="/cart/delete/${cart.id}.html">删除</a>
		            </div>
		        </div>
	        </div> 
        </c:forEach>
        
    </div><!-- product-list结束 -->
          <div class="cart-toolbar clearfix">
            <div class="total fr">
                <p><span class="totalSkuPrice">¥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></span>总计：</p>
                <p><span id="totalRePrice">- <fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></span>优惠：</p>
            </div>
            <div class="amout fr"><span id="selectedCount">1</span> 件商品</div>
        </div>
        <div class="ui-ceilinglamp-1" style="width: 988px; height: 49px;"><div class="cart-dibu ui-ceilinglamp-current" style="width: 988px; height: 49px;">
          <div class="control fdibu fdibucurrent">
              <span class="column t-checkbox form">
                  <input data-cart="toggle-cb" name="toggle-checkboxes" id="toggle-checkboxes_down" type="checkbox" checked="" value="" class="jdcheckbox">
                  <label for="toggle-checkboxes_down">
                          全选
                  </label>
              </span>
              <span class="delete">
                  <b>
                  </b>
                  <a href="javascript:void(0);" clstag="clickcart|keycount|xincart|clearcartlink" id="remove-batch">
                          删除选中的商品
                  </a>
              </span>
              <span class="shopping">
                  <b>
                  </b>
                  <a href="/" target="_blank" clstag="clickcart|keycount|xincart|coudanlink" id="continue">继续购物</a>
              </span>
          </div>
          <div class="cart-total-2014">
              <div class="cart-button">
                  <span class="check-comm-btns" id="checkout-jd">
                      <a class="checkout" href="javascript:void(0)"  onclick="toOrder();" clstag="clickcart|keycount|xincart|gotoOrderInfo" id="toSettlement">去结算<b></b></a>
                  </span>
                  
              </div>
              <div class="total fr">
                  总计（不含运费）：
                  <span class="totalSkuPrice">¥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></span>
              </div>
          </div>
      </div></div>
</div><!-- cart-inner结束 -->
</div>
</div>
<!--推荐位html修改处-->
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

<script type="text/javascript" src="${pageContext.request.contextPath}/static/mall/js/base-v1.js"></script>
<!-- footer start -->

<!-- footer end -->

<!-- 购物车相关业务 -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/mall/js/jquery.price_format.2.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/mall/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/mall/js/cart.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
<script>

	function toOrder(){
		
		var _ticket = $.cookie("TT_TOKEN_USER");
		var username=$.cookie("cusername");
	
		
		if (!_ticket) {
			//alert("请登录！");
			tipShow("#loginAlert");
		}else if(username==null || username==''){
			username=$("#cusername").val();
			location="${pageContext.request.contextPath}/order/order-cart?username="+username;
			
		} else {
			location="${pageContext.request.contextPath}/order/order-cart?username="+username;
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


</html>