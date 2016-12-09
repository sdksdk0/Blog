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
</style>
</head>
<body>
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



