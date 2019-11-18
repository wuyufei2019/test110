<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>权限管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	
	$(function(){
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid = $("#inputForm").validate().form();
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data){   
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		}); 
	});
	</script>
</head>
<body>

	<form id="inputForm" action="${ctx}/system/permission/${action}" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${permission.id }"/>
	<input type="hidden" id="pid" name="pid" value="${permission.pid }"/>
	<input type="hidden" name="type" value="${permission.type }"/>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
		<tr>
			<td class="width-35 active"><label class="pull-right">权限名称：</label></td>
			<td class="width-65"><input id="name" name="name" type="text" class="required form-control" value="${permission.name }" /></td>
		</tr>
		<tr>
			<td class="width-35 active"><label class="pull-right">权限编码：</label></td>
			<td class="width-65"><input id="permCode" name="permCode" type="text" class="required form-control"  value="${permission.permCode }" /></td>
		</tr>
		<tr>
			<td class="width-35 active"><label class="pull-right">访问路径：</label></td>
			<td class="width-65"><input id="url" name="url" type="text" value="${permission.url }" class="form-control" /></td>
		</tr>
		<tr>
			<td class="width-35 active"><label class="pull-right">所属菜单：</label></td>
			<td class="width-65"><input name="pname" type="text" value="${pname}" class="form-control" readonly="readonly"/></td>
		</tr>
		<tr>
			<td class="width-35 active"><label class="pull-right">描述：</label></td>
			<td class="width-65"><textarea rows="3" name="description" class="form-control ">${permission.description}</textarea></td>
		</tr>
		</tbody>
	</table>
	</form>

</body>
</html>