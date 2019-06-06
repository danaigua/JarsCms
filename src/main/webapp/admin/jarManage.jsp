<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jar包管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;
	
	function searchJar(){
		$("#dg").datagrid("load",{
			"s_name":$("#s_name").val()
		});
	}
	
	function deleteJar(){
		var selectionRows=$("#dg").datagrid("getSelections");
		if(selectionRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectionRows.length;i++){
			strIds.push(selectionRows[i].uuid);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectionRows.length+"</font>条数据吗?",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/jar/delete.do",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","数据已成功删除！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","数据删除失败，请联系管理员！");
					}
				},"json");
			}
		});
	}
	
	function openJarAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加Jar包信息");
		url="${pageContext.request.contextPath}/admin/jar/save.do"
	}
	
	function openJarModifyDialog(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑Jar包信息");
		$("#fm").form("load",row);
		url="${pageContext.request.contextPath}/admin/jar/save.do?uuid="+row.uuid;
	}
	
	function saveJar(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败，请联系管理员！");
				}
			}
		});
	}
	
	function closeJarDialog(){
		resetValue();
		$("#dlg").dialog("close");
	}
	
	function resetValue(){
		$("#name").val("");
		$("#path").val("");
		$("#type").val("");
	}

</script>
</head>
<body style="margin: 1px">
<table id="dg" title="jar包管理" class="easyui-datagrid" 
  fitColumns="true" pagination="true" rownumbers="true"
  url="${pageContext.request.contextPath}/admin/jar/list.do" fit="true" toolbar="#tb">
  <thead>
  	<th field="cb" checkbox="true" align="center"></th>
  	<th field="uuid" width="100">编号</th>
  	<th field="name" width="100">jar包名称</th>
  	<th field="updateDate" width="50" align="center">更新日期</th>
  	<th field="type" width="50" align="center">jar包类型</th>
  </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openJarAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openJarModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteJar()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;jar包名称：&nbsp;<input type="text" id="s_name" size="20" onkeydown="if(event.keyCode==13) searchJar()"/>
		<a href="javascript:searchJar()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 600px;height: 200px;padding: 10px 20px"
closed="true" buttons="#dlg-buttons">

	<form id="fm" method="post">
		<table cellspacing="8px">
			<tr>
				<td>jar包名称：</td>
				<td>
					<input type="text" id="name" name="name" class="easyui-validatebox" required="true" style="widht:200px"/>
				</td>
			</tr>
			<tr>
				<td>jar包资源路径：</td>
				<td>
					<input type="text" id="path" name="path" class="easyui-validatebox" validtype="url" required="true" style="width:350px"/>
				</td>
			</tr>
			<tr>
				<td>jar包类型：</td>
				<td>
					<select id="type" name="type" class="easyui-validatebox" required="true" style="widht:200px">
						<option value="">请选择类型...</option>
						<option value="jar">jar</option>
						<option value="javadoc">javadoc</option>
						<option value="sources">sources</option>
					</select>
				</td>
			</tr>
		</table>
	</form>

</div>

<div id="dlg-buttons">
	<a href="javascript:saveJar()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeJarDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>