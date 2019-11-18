<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var url="${ctx}/system/permission/${action}";
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	
	$(function(){
		if(parent.parentPermId!=null&&parent.parentPermId!=0)
			$('#pid').combotree('setValue', parent.parentPermId);
		
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data){   
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.dg.treegrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		}); 
		
		
		
/*		$('#icon').combobox({
			  valueField:'value',
			  textField:'text',
			  url:'${ctx}/system/permission/getBigIcons',
			  editable:true,
			     formatter:function(data){
			       return '<img class="item-img" width="50" height="50" src="${ctxStatic}/model/main/images/'+data.text+'"/><span class="item-text">'+data.value+'</span>';
			     },
		});*/
	});


	function clearValue(){
	    $("#pid").combotree('clear');
	}
	</script>
</head>
<body>
<!-- 
	<form:form id="inputForm" modelAttribute="permission" action="${ctx}/system/permission/${action}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="pid"/>
		<form:hidden path="type"/>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">上级菜单:</label></td>
		         <td class="width-35" ><sys:treeselect id="menu" name="pid" value="${permission.pid}" labelName="parent.name" labelValue="${parentname}"
					title="菜单" url="/system/permission/menu/json" extId="${menu.id}" cssClass="form-control"/></td>
		         <td  class="width-15 active"><label class="pull-right"><font color="red">*</font> 名称:</label></td>
		         <td  class="width-35" ><form:input path="name" htmlEscape="false" maxlength="50" class="required form-control "/></td>
		      </tr>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">链接:</label></td>
		         <td class="width-35" ><form:input path="url" htmlEscape="false" maxlength="2000" class="form-control "/>
					<span class="help-inline">点击菜单跳转的页面</span></td>
		          <td  class="width-15 active"><label class="pull-right">排序:</label></td>
		         <td  class="width-35" ><form:input path="sort" htmlEscape="false" maxlength="50" class="required digits form-control "/>
					<span class="help-inline">排列顺序，升序。</span></td>
		      </tr>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">图标:</label></td>
		         <td class="width-35" ><sys:iconselect id="icon" name="icon" value="${permission.icon}"/></td>
		         <td  class="width-15 active"><label class="pull-right">备注:</label></td>
		         <td class="width-35" ><form:textarea path="description" htmlEscape="false" rows="3" maxlength="200" class="form-control "/></td>
		      </tr>
		      
		    </tbody>
		  </table>
	</form:form>
 -->	
	<form id="inputForm" action="${ctx}/system/permission/${action}" method="post" class="form-horizontal">
		<input type="hidden"  name="id" value="${permission.id }"/>
		<input type="hidden" name="type" value="${permission.type}"/>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>

		      <tr>
		         <td class="width-15 active">	<label class="pull-right">菜单名称:</label></td>
		         <td class="width-35" ><input type="text" name="name" value="${permission.name}"  class="easyui-textbox " style="width: 100%;height: 30px;" data-options="required:true,"/></td>
				 <td class="width-15 active"><label class="pull-right">英文名称：</label></td>
				 <td class="width-35"><input name="enname" type="text" class="form-control" value="${permission.enname }" /></td>
		      </tr>

		      <tr>
		         <td class="width-15 active"><label class="pull-right">菜单路径:</label></td>
		         <td class="width-35"><input type="text" name="url" value="${permission.url}" class="easyui-textbox " style="width: 100%;height: 30px;"  /></td>
		      </tr>

		      <tr>
		         <td class="width-15 active">	<label class="pull-right">菜图标:</label></td>
		          <td class="width-35" ><sys:iconselect id="icon" name="icon" value="${permission.icon}"/></td>
		        <%-- <td class="width-35"><input id="icon" name="icon"  style="width: 100%;height: 30px;" value="${permission.icon}"/></td>--%>
		         <td class="width-15 active">	<label class="pull-right">上级菜单:</label></td>
		         <td class="width-35" ><input type="text" id="pid" name="pid" value="${permission.pid}" class="easyui-combotree" style="width: 100%;height: 30px;" data-options="url:'${ctx}/system/permission/menu/json2'"/>
					 <span class="btn btn-danger btn-sm" onclick="clearValue()">清除</span></td>
		      </tr>
		      <tr>
		      	 <td class="width-15 active">	<label class="pull-right">排序:</label></td>
		         <td class="width-35" ><input type="text" name="sort" value="${permission.sort}" class="easyui-textbox easyui-validatebox" style="width: 100%;height: 30px;" data-options="validType:'number'"/></td>
		         <td class="width-15 active">	<label class="pull-right">描述:</label></td>
		         <td class="width-35"><input type="text" name="description" value="${permission.description}" class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true"/></td>
		      </tr>
		           
		           
		   </tbody>
		   </table>   
	</form>
	
</body>
</html>