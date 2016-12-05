<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>写博客页面</title>
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
<div id="p" class="easyui-panel" title="编写博客" style="padding: 10px">
<form id="itemAddForm" >
 	<table cellspacing="20px">
   		<tr>
   			<td width="80px">博客标题：</td>
   			<td><input type="text" id="title" name="title" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td>分类：</td>
   			<td>
   				<select class="easyui-combobox" style="width: 154px" id="typeId" name="typeId" editable="false" panelHeight="auto" >
					<option value="">--请选择--</option>	
				    <c:forEach var="type" items="${typeCountList }">
				    	<option value="${type.typeid }">${type.typename }</option>
				    </c:forEach>			
                </select>
   			</td>
   		</tr>
   		<tr>
   		
   			<td>自定义类别：</td>
   			<td>
   				<select class="easyui-combobox" style="width: 154px" id="blogTypeId" name="blogTypeId" editable="false" panelHeight="auto" >
					<option value="">请选择博客类别...</option>	
				    <c:forEach var="blogType" items="${blogTypeCountList }">
				    	<option value="${blogType.blogtypeId }">${blogType.typeName }</option>
				    </c:forEach>			
                </select>
   			</td>
   		</tr>
   		<tr>
   			<td valign="top">博客内容：</td>
   			<td>
				   <!-- <script id="editor" type="text/plain" style="width:100%;height:400px;"></script> -->
				    <textarea style="width:100%;height:500px;visibility:hidden;" id="content"   name="content"></textarea>
				 
   			</td>
   		</tr>
   		<tr>
   			<td>关键字：</td>
   			<td><input type="text/plain" id="keyWord" name="keyWord" style="width: 980px;"/>&nbsp;(多个关键字中间用空格隔开)</td>
   		</tr>
   		
   		
   		<input  type="hidden"  type="username"  id="username"  value="${user.username}"  />
   		
   		<tr>
   			<td></td>
   			<td>
   				<a href="javascript:submitData();" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">发布博客</a>
   			</td>
   		</tr>
   	</table>
   	
   	</form>
 </div>
 

<script type="text/javascript">
	var itemAddEditor ;
	//页面初始化完毕后执行此方法
	$(function(){
		//创建富文本编辑器
		itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=content]");
		
		
		
		//初始化类目选择和图片上传器
		TAOTAO.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。
			TAOTAO.changeItemParam(node, "itemAddForm");
		}});
	});

	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
	
	
	
	
	function submitData(){
		var title=$("#title").val();
		var typeId=$("#typeId").combobox("getValue");
		var blogTypeId=$("#blogTypeId").combobox("getValue");
		var content=itemAddEditor.html();
		//UE.getEditor('editor').getContentTxt(),'summary':UE.getEditor('editor').getContentTxt().substr(0,155)
		itemAddEditor.sync();
		
		var dd=content.replace(/<\/?.+?>/g,"");
		
 		var contentNoTag=dd.replace(/(^\s*)|(\s*$)/g,"");//dds为得到后的内容
			
		var summary=contentNoTag.substr(0,155);
		
		
		var keyWord=$("#keyWord").val();

		var username=$("#username").val();
	
		
		if(title==null || title==''){
			alert("请输入标题！");
		}else if(typeId==null || typeId==''){
			alert("请选择博客类别！");
		}else if(blogTypeId==null || blogTypeId==''){
			alert("请选择博客类别！");
		}else if(content==null || content==''){
			alert("请输入内容！");
		}else{
			 $.post("/manager/user/blog/save",{'username':username,'title':title,'typeid':typeId,'blogtypeid':blogTypeId,'content':content,'summary':summary,'contentNoTag':contentNoTag,'keyword':keyWord},function(result){
				if(result.success){
					alert("博客发布成功！");
					resetValue();
				}else{
					alert("博客发布失败！");
				}
			},"json"); 
		}
	}
	
	// 重置数据
	function resetValue(){
		$("#title").val("");
		itemAddEditor.html("");
		$("#keyWord").val("");
	}
</script>


</body>
</html>