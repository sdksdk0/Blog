<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布公告</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/common.js"></script>
<link href="${pageContext.request.contextPath}/static/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/js/kindeditor-4.1.10/lang/zh_CN.js"></script>



</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="发布公告" style="padding: 10px">
<form id="itemAddForm" >
 	<table cellspacing="20px">
		<tr>
				    <textarea style="width:100%;height:100px;" id="content"   name="content"></textarea>
	
   		<tr>
  
   			<td>
   				<a href="javascript:submitData();" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">发布公告</a>
   			</td>
   	</table>
   	
   	</form>
 </div>
 

<script type="text/javascript">

	function submitData(){
		
		var content=$("#content").val();
		
		if(content==null || content==''){
			alert("请输入内容！");
		}else{
			 $.post("msg",{'content':content},function(result){
				if(result.success){
					alert("发布成功！");
					resetValue();
				}else{
					alert("发布失败！");
				}
			},"json"); 
		}
	}
	
	// 重置数据
	function resetValue(){
		$("#content").val('');
	}
</script>


</body>
</html>