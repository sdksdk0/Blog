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
</script>
<script src="${pageContext.request.contextPath}/static/js/qq.js"></script>
<script>
	myEvent(window,'load',function(){
		cs_box.set({
			img_path : 'http://139.199.158.214/images/2016/12/10/1481369986375648.png',	//设置图片路径
			qq : '690644511',	//设置QQ号码
		});
	});
</script>

<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 40px;
	height: 1200px;
}

#content {
	font-size: 14px;
	letter-spacing: 5px;
	text-decoration: underline;
	white-space: nowrap; /* 强制文本在一行显示 */
	white-space: nowrap; /* 强制文本在一行显示 */
	overflow: hidden; /*  超出部分隐藏 */
	text-overflow: ellipsis; /*  加省略号  */
	list-style-type: circle;
}

#cs_box {width:120px; height:220px; color:#FFF; background:#54a7ff; position:fixed; right:10px; top:100px; border-radius:10px; z-index:1000}
#cs_box span {height:20px; line-height:20px;display:block;}
.cs_close {color:#FFF; position:absolute; right:10px; top:8px; cursor:pointer; font-size:20px;font-family:Verdana, Geneva, sans-serif}
.cs_title {font-size:14px; margin:10px; font-weight:bold;}
.cs_img {width:100px; height:100px; background:#FFF; margin:10px;background-image:url(http://139.199.158.214/images/2016/12/10/1481370643213201.png) }
.cs_info {font-size:12px; margin:0px 10px; overflow:hidden; text-align:center;}
.cs_btn {width:100px; height:25px; background:#333; margin:5px 10px; border-radius:5px; font-size:12px; line-height:25px; color:#FFF; text-align:center; cursor:pointer;}


</style>
</head>
<body>

<!-- 代码部分begin -->
<div id='cs_box'>
	<span class='cs_title'>在线咨询</span>
	<span class='cs_close'>x</span>
	<div class='cs_img'></div>
	<span class='cs_info'>有什么可以帮到你</span>
	<div class='cs_btn'>点击咨询</div>
</div>
	<div class="container">
		<jsp:include page="common/head.jsp" />

		<jsp:include page="common/menu.jsp" />

		<div class="row">
	
				<jsp:include page="${mainPage }"></jsp:include>

		</div>

		<jsp:include page="common/foot.jsp" />
	</div>
</body>
</html>



