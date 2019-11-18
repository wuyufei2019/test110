<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	
	$(function(){
		validateForm = $("#inputForm").validate({
			 debug:true
		});
		
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid = validateForm.form();
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
	<form:form id="inputForm" modelAttribute="dict"  class="form-horizontal" method="post" action="${ctx}/system/dict/${action}">
		<form:hidden path="id"/>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td class="width-15 active">	<label class="pull-right">键值:</label></td>
		         <td class="width-35" ><form:input path="value" htmlEscape="false" maxlength="50" class="form-control required"/></td>
		         <td class="width-15 active">	<label class="pull-right">标签:</label></td>
		         <td class="width-35" ><form:input path="label" htmlEscape="false" maxlength="50" class="form-control required"/></td>
		      </tr>
		       <tr>
		         <td class="width-15 active">	<label class="pull-right">类型:</label></td>
		         <td class="width-35" ><form:input path="type" htmlEscape="false" maxlength="50" class="form-control required abc"/></td>
		         <td class="width-15 active">	<label class="pull-right">排序:</label></td>
		         <td class="width-35" ><form:input path="sort" htmlEscape="false" maxlength="11" class="form-control required digits"/></td>
		      </tr>
		       <tr>
		         <td class="width-15 active">	<label class="pull-right">描述:</label></td>
		         <td class="width-85" colspan="3"><form:textarea path="description" htmlEscape="false" rows="3" maxlength="200" class="form-control"/></td>
		      </tr>
		   </tbody>
		   </table>   
	</form:form>
</body>
</html>