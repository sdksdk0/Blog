<script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
    SyntaxHighlighter.all();
</script>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>	
<script type="text/javascript">
	function loadimage(){
		document.getElementById("randImage").src="${pageContext.request.contextPath}/image.jsp?"+Math.random();
	}
	
	function submitData(){
		var content=$("#content").val();
		var _ticket = $.cookie("TT_TOKEN_USER");
		if(!_ticket){
				alert("请登录！");
				
				return;
		}else  if(content==null || content==''){
			alert("请输入评论内容！");
		}else{
		
			$.post("${pageContext.request.contextPath}/comment/save",{'content':content,'imageCode':imageCode,'blog.blogid':'${blog.blogid}'},function(result){
				if(result.success){
					
					alert("评论已提交成功，审核通过后显示！");
					window.location.reload();
				}else{
					alert(result.errorInfo);
				}
			},"json");
		}
	}
	
	function showOtherComment(){
		$('.otherComment').show();
	}
	
	
	function loginAjax(){
	var username=$("#username").val();
	var password=$("#password").val();
	var captcha=$("#captcha").val();
	$.ajax({
		url:"${path}/user/loginAjax.do",
		type:"post",
		dataType:"text",
		data:{
			username:username,
			password:password,
			captcha:captcha
		},
		success:function(responseText){
			if(responseText == "cap_error"){
				$("#errorName").html("验证码错误");
				$("#errorName").show(500);
			}else if(responseText == "userpass_error"){
				$("#errorName").html("用户名或者错误");
				$("#errorName").show(500);
			}else{
				//隐藏掉登录框
				tipHide("#loginAlert");
				//回显用户名
				$("#loginAlertIs").html(username);
				//库存判断
				var result = validCart();
				if(result != "success"){
					alert(result);
				}else{
					window.location.href = "${path}/order/toSubmitOrder.do"
				}
			}
		},
		error:function(){
			alert("系统错误");
		}
		});
	

}
	
	
	
	
</script>
<div class="data_list">
	<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/blog_show_icon.png"/>
		博客信息
	</div>
	<div>
	   <div class="blog_title"><h3><strong>${blog.title }</strong></h3></div>
	   <div style="padding-left: 330px;padding-bottom: 20px;padding-top: 10px">
		<div class="bshare-custom"><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span class="BSHARE_COUNT bshare-share-count">0</span></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=1&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
	   </div>
		<div class="blog_info">
			发布时间：『 <fmt:formatDate value="${blog.releasedate }" type="date" pattern="yyyy-MM-dd HH:mm"/>』&nbsp;&nbsp;用户名：${blog.username}&nbsp;&nbsp;阅读(${blog.clickhit}) 评论(${blog.replyhit})
		</div>
		<div class="blog_content">
			${blog.content }
		</div>
		<div class="blog_keyWord">
			<font><strong>关键字：</strong></font>
			<c:choose>
				<c:when test="${keyWords==null}">
					&nbsp;&nbsp;无
				</c:when>
				<c:otherwise>
					<c:forEach var="keyWord" items="${keyWords }">
						&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/blog/q.html?q=${keyWord}" target="_blank">${keyWord }</a>&nbsp;&nbsp;
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="blog_lastAndNextPage">
			${pageCode }
		</div>
	</div>
</div>
<div class="data_list">
	<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/comment_icon.png"/>
		评论信息    
		<c:if test="${commentList.size()>10}">
			<a href="javascript:showOtherComment()" style="float: right;padding-right: 40px;">显示所有评论</a>
		</c:if>
	</div>
	<div class="commentDatas">
		<c:choose>
			<c:when test="${commentList.size()==0}">
				暂无评论
			</c:when>
			<c:otherwise>
				<c:forEach var="comment" items="${commentList }" varStatus="status">
						<c:choose>
							<c:when test="${status.index<10 }">
								<div class="comment">
									<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.username }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate value="${comment.commentdate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>
								</div>								
							</c:when>
							<c:otherwise>
								<div class="otherComment">
									<div class="comment">
										<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.username }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate value="${comment.commentdate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>
									</div>		
								</div>						
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div class="data_list" >
	<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/publish_comment_icon.png"/>
		发表评论
	</div>
	<div class="publish_comment">
			<div>
				<textarea style="width: 100%" rows="3" id="content" name="content" placeholder="来说两句吧..."></textarea>
			</div>

			<div class="publishButton">
				<button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
			</div>
		</form>
	</div>
</div>






<div id="loginAlert" class="alt login" style="display:none">
		<h2 class="h2">
			<em title="登录">登录</em><cite></cite>
		</h2>
		<a href="javascript:void(0);" id="loginAlertClose" class="close"
			title="关闭"></a>
		<div class="cont">
			<ul class="uls form">
				<li id="errorName" class="errorTip" style="display:none"></li>

				<li><label>用户名：</label> <span class="bg_text"> <input
						type="text" maxlength="50" vld="{required:true}" name="username"
						id="username"
						reg1="^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$"
						desc="用户名长度不超过50个，必须是邮箱格式！" /> <em id="userNameLabel" class="def">请输入手机号码</em>
				</span> <span class="word"><a title="免费注册"
						href="/ecps-portal/ecps/portal/register.do">免费注册</a></span></li>

				<li><label>密码：</label> <span class="bg_text"><input
						type="password" vld="{required:true}" maxlength="20"
						name="password" id="password" value="" reg1="^.{6,20}$"
						desc="密码长度范围为6-20，允许为中英文、数字或特殊字符！" /></span></li>
				<li><label for="captcha">验 证 码：</label> <span
					class="bg_text small"><input type="text"
						vld="{required:true}" maxlength="7" name="captcha" id="captcha"
						value="" reg1="^\w{6}$" desc="验证码不正确" /></span> <img alt="换一张"
					id="captchaImage" class="code" onclick="changeImage();"
					src="${path }/user/getImage.do" /><a href="#"
					onclick="changeImage();" title="换一张">换一张</a></li>
				<li class="gray"><label>&nbsp;</label><input type="checkbox"
					name="">记住我的手机号码</li>
				<li><label>&nbsp;</label><input type="button" id="loginSubmit"
					class="hand btn66x23" value="登 录" onclick="loginAjax()"><a
					title="忘记密码？" href="/ecps-portal/ecps/portal/getpwd/getpwd1.do">忘记密码？</a></li>
				<!--li class="alg_c dev gray">还不是移动商城会员？<a title="免费注册" href="/ecps-portal/ecps/portal/register.do">免费注册</a></li-->
			</ul>
		</div>
	</div>









