<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var url="${ctx}/system/dict/${action}";
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;
	
		function doSubmit(){
			$("#inputForm").submit(); 
		}
		
		
		$(function(){
			$('#inputForm').form({    
			    onSubmit: function(){    
			    	var isValid = $(this).form('validate');
					return isValid;	// 返回false终止表单提交
			    },    
			    success:function(data){   
			    	parent.layer.open({title: '提示',offset: 'rb',content: data,shade: 0 ,time: 2000 });
			    	parent.dg.datagrid('reload');
			    	parent.layer.close(index);//关闭对话框。
			    }    
			}); 
		});
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/system/dict/${action}" method="post" class="form-horizontal">
		<input type="hidden"  name="id" value="${dict.id }"/>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td class="width-15 active">	<label class="pull-right">键值:</label></td>
		         <td class="width-35" ><input type="text" name="value" value="${dict.value }"  class="form-control required"/></td>
		         <td class="width-15 active">	<label class="pull-right">标签:</label></td>
		         <td class="width-35" ><input type="text" name="label" value="${dict.label }" class="form-control required"/></td>
		      </tr>
		       <tr>
		         <td class="width-15 active">	<label class="pull-right">类型:</label></td>
		         <td class="width-35" ><input type="text" name="type"  value="${dict.type }"  class="form-control required abc"/></td>
		         <td class="width-15 active">	<label class="pull-right">排序:</label></td>
		         <td class="width-35" ><input type="text" name="sort" value="${dict.sort }"  maxlength="11" class="form-control required digits"/></td>
		      </tr>
		       <tr>
		         <td class="width-15 active">	<label class="pull-right">描述:</label></td>
		         <td class="width-85" colspan="3"><textarea id="description" name="description" maxlength="200" class="form-control " rows="3">${dict.description }</textarea> </td>
		      </tr>
		    		
		     
		      <tr>
		         <td class="width-15 active">	<label class="pull-right">键值:</label></td>
		         <td class="width-35" ><input type="text" name="value1" value=""  class="easyui-combobox " style="width: 100%;"/></td>
		         <td class="width-15 active">	<label class="pull-right">标签:</label></td>
		         <td class="width-35" ><input type="text" name="label1" value="" class="easyui-textbox " data-options="prompt:'Enter a email address...',required:true,"  /></td>
		      </tr>
		           
		   </tbody>
		   </table>   
	</form>
</body>
</html>