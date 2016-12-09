<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function checkData(){
		var q=document.getElementById("q").value.trim();
		if(q==null || q==""){
			alert("请输入您要查询的关键字！");
			return false;
		}else{
			return true;
		}
	}
	
	//ajax请求，动态导航栏
	$(function(){
		
		 $.ajax("${pageContext.request.contextPath}/typelist",
            {
                method:"post",
                async : false,  
                data:{},
                onsuccess:function(result){
                	
                }
            }
        );
	
	});
	
	
	
	
</script>
<div class="row">
	<div class="col-md-12" style="padding-top: 10px">
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="${pageContext.request.contextPath}/index.html"><font color="black"><strong>首页</strong></font></a>
		     
		    </div>

		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" >
		      <ul class="nav navbar-nav"  id="typelist">
		       
		         <li><a href="${pageContext.request.contextPath}/mall?q=教程"><font color="black"><strong>积分商城</strong></font></a></li>
		        <c:forEach var="type" items="${typeList }">
							<li><a href="${pageContext.request.contextPath}/adminlist?typeid=${type.typeid }"><font color="black"><strong>${type.typename }</strong></font></a></li>
				</c:forEach>
		        
		        
		        
		        
		       
		      </ul>
		      <form action="${pageContext.request.contextPath}/blog/q.html" class="navbar-form navbar-right" role="search" method="post" onsubmit="return checkData()">
		        <div class="form-group" >
		          <input type="text" id="q" name="q" value="${q }" class="form-control" placeholder="请输入要查询的关键字...">
		        </div>
		        <button type="submit" class="btn btn-default">搜索</button>
		      </form>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
	</div>
</div>