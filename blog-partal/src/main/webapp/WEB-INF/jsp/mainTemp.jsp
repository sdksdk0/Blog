<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客云-遇见最好的你</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
<link href="${pageContext.request.contextPath}/static/images/title.jpg" rel="SHORTCUT ICON">
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>

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
      }
</style>
</head>
<body>
<div class="container">
	<jsp:include page="common/head.jsp"/>
	
	<jsp:include page="common/menu.jsp"/>
	
	<div class="row">
		<div class="col-md-9">
			<jsp:include page="${mainPage }"></jsp:include>
		</div>
		
		<div class="col-md-3">
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/user_icon.png"/>
					<a href="${pageContext.request.contextPath}/userlist?username=${user.username}"   >博主信息</a>
				</div>
				<div class="user_image">
					<a href="${pageContext.request.contextPath}/userlist?username=${user.username}"   ><img src="${user.image }"/></a>
				</div>
				<div class="nickName"><a href="${pageContext.request.contextPath}/userlist?username=${user.username}"   >${user.nickName }</a></div>
				<div class="userSign"><a href="${pageContext.request.contextPath}/userlist?username=${user.username}"   >(${user.sign })</a></div>
			</div>
			
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>
					按日志类别
				</div>
				<div class="datas">
					<ul>
						<c:forEach var="blogTypeCount" items="${blogTypeCountList }">
							<li><span><a href="${pageContext.request.contextPath}/userlist?blogtypeid=${blogTypeCount.blogtypeId }&username=${blogTypeCount.username}">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/>
					按日志日期
				</div>
				<div class="datas">
					<ul>
						<c:forEach var="blogCount" items="${countList }">
							<li><span><a href="${pageContext.request.contextPath}/userlist?releaseDateStr=${blogCount.releaseDateStr }&username=${blogCount.username}">${blogCount.releaseDateStr }(${blogCount.blogCount })</a></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="data_list">
					<div class="data_list_title"  style="clear:both;">
						<img
							src="${pageContext.request.contextPath}/static/images/list_icon.png" />
						最新评论
					</div>
					<div class="datas">
						<ul>
						<c:forEach var="comment" items="${commentList }">
							<li><span><a href="${pageContext.request.contextPath}/userlist?username=${comment.username }">${comment.username }</a>:
								评论了:<a href="${pageContext.request.contextPath}/blog/articles/${comment.username}/${comment.blogid}.html">${comment.title }</a>
								: ${comment.content }
							</span></li>
						</c:forEach>
					</ul>
					</div>
				</div>
			
			
			
			
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/link_icon.png"/>
					友情链接
				</div>
				<div class="datas">
					<ul>
						<c:forEach var="link" items="${linkList }">
							<li><span><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
		</div>
		
		
	</div>
	
	<jsp:include page="common/foot.jsp"/>
</div>
</body>
</html>