<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客云-遇见最好的你</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/blog.css">
<link href="${pageContext.request.contextPath}/static/images/title.jpg"
	rel="SHORTCUT ICON">
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/com.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/style.css">
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "//hm.baidu.com/hm.js?aa5c701f4f646931bf78b6f40b234ef5";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();

	$(function() {
		var _ticket = $.cookie("TT_TOKEN_USER");
		if (!_ticket) {
			$("#after").show();
			$("#before").hide();

		} else {

			$("#before").show();
			$("#after").hide();
		}

	});
	function ajaxlogin() {
		tipShow("#loginAlert");
	}
	
	function loginAjax() {
		var redirectUrl = "${redirect}";
		var username = $("#username").val();
		var password = $("#password").val();
		
		$.cookie('cusername', username);

		$.ajax({
			url : "http://localhost:8087/sso/user/login?r="+Math.random(),
			type : "post",
			dataType:'JSONP',
			data : {
				username : username,
				password : password,
			},
			success : function(data) {
				
				if (data.status == 200) {
					//隐藏掉登录框
					$("#error").text("");
					tipHide("#loginAlert");
					window.location.reload();
				} 
			},
			error : function(data) {
				$("#error").text("用户名或密码错误");
				tipHide("#loginAlert");
				window.location.reload();
			}
		});
		
	}
	
	
</script>

<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 40px;
	height: 1200px;
}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="common/head.jsp" />

		<jsp:include page="common/menu.jsp" />

		<div class="row">
			<div class="col-md-9">
				<jsp:include page="${mainPage }"></jsp:include>
			</div>

			<div class="col-md-3">

				<div id="after">
					<a href="javascript:void(0)" onclick="ajaxlogin();"><img
						src="${pageContext.request.contextPath}/static/img/login.gif" /></a>
					| <a href="http://localhost:8087/sso/page/register" target="_blank"><img
						src="${pageContext.request.contextPath}/static/img/register.gif" /></a>
				</div>
				<div id="before">
					<a href="http://localhost:8083/manager/user/main" target="_blank">个人中心
						| <img
						src="${pageContext.request.contextPath}/static/img/home.png" />
					</a>
				</div>


			</div>

		</div>

		<jsp:include page="common/foot.jsp" />
	</div>
</body>
</html>

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



