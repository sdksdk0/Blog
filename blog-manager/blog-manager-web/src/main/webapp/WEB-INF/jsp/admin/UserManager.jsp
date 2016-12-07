<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

	function commentReview(state){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要冻结的数据！");
			 return;
		 }
		 var strIds=[];
		 for(var i=0;i<selectedRows.length;i++){
			 strIds.push(selectedRows[i].userId);
		 }
		 var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要审核这<font color=red>"+selectedRows.length+"</font>条信息吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/user/delete",{ids:ids,state:state},function(result){
					if(result.success){
						 $.messager.alert("系统提示","提交成功！");
						 $("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","提交失败！");
					}
				},"json");
			} 
  	   });
	}

	
		
	function formatBlogusername(val,row){
		return "<a target='_blank' href='http://localhost:8085/userlist?username="+row.username + "'>"+row.username+"</a>";			

	}
	
	
	function formatState(val,row){
		if(val==0){
			return "<font color='red' >未激活</font>";
		}else if(val==1){
			return "正常";
		}else if(val==2){
			return "<font color='red' >已冻结</font>";
		}
	}

</script>
</head>
<body style="margin: 1px">
<table id="dg" title="用户状态管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/admin/user/list" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="userId" width="20" align="center">编号</th>
   		<th field="username" width="200" align="center"  formatter="formatBlogusername">用户名</th>
   		<th field="nickName" width="100" align="center" >昵称</th>
   		<th field="createtime" width="100" align="center" >创建日期</th>
   		<th field="state" width="200" align="center"  formatter="formatState">状态</th>
   		
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:commentReview(2)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">冻结</a>
 		<a href="javascript:commentReview(1)" class="easyui-linkbutton" iconCls="icon-no" plain="true">解冻</a>
 	</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
 </div>
 
 
</body>
</html>