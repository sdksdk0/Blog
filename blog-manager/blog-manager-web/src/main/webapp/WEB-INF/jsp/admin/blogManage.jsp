<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

	function formatBlogType(val,row){
		return val.typeName;
	}
	
	function formatTitle(val,row){
		return "<a target='_blank' href='http://localhost:8085/blog/articles/"+row.username+"/"+row.blogid+".html'>"+val+"</a>"
	}
	
	function formatBlogusername(val,row){
		return "<a target='_blank' href='http://localhost:8085/userlist?username="+row.username + "'>"+row.username+"</a>";			
	}
	
	 function formatState(val,row){
		if(val==1){
			return "正常";
		}else if(val==0){
			return "<font color='red' >已冻结</font>";
		}else{
			return "<font color='red' >未知</font>";
		}
	}
	
	function searchBlog(){
		$("#dg").datagrid('load',{
			"title":$("#s_title").val() 
		});
	}
	
	function deleteBlog(obj){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要操作的数据！");
			 return;
		 }
		 var strIds=[];
		 for(var i=0;i<selectedRows.length;i++){
			 strIds.push(selectedRows[i].blogid);
		 }
		 var ids=strIds.join(",");
		 $.messager.confirm("系统提示","您确定要处理这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
				if(r){
					$.post("${pageContext.request.contextPath}/admin/blog/delete",{ids:ids,state:obj},function(result){
						if(result.success){
							 $.messager.alert("系统提示","成功！");
							 $("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示","失败！");
						}
					},"json");
				} 
	   });
	}
	
	
	
</script>
</head>
<body style="margin: 1px">
<table id="dg" title="博客管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/admin/blog/list" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="blogid" width="50" align="center">编号</th>
   		<th field="title" width="200" align="center" formatter="formatTitle">标题</th>
   		<th field="username" width="50" align="center"  formatter="formatBlogusername">用户名</th>
   		<th field="releasedate" width="50" align="center">发布日期</th>
   		<th field="utypeName" width="50" align="center" >博客类别</th>
   		<th field="stypeName" width="50" align="center">大类</th>
   		<th field="state" width="50" align="center" formatter="formatState" >状态</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:deleteBlog(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">冻结博客</a>
 		<a href="javascript:deleteBlog(1)" class="easyui-linkbutton" iconCls="icon-add" plain="true">解冻博客</a>
 	</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
 	<div>
 		&nbsp;标题：&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode==13) searchBlog()"/>
 		<a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	</div>
 </div>
</body>
</html>