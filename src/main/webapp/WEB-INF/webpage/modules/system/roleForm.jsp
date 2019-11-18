<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var url="${ctx}/system/role/${action}";
		var validateForm;
		
		$(document).ready(function() {
			 validateForm = $("#inputForm").validate({
				 debug:true
			});
		});
	
	</script>

</head>
<body>
	<form:form id="inputForm" modelAttribute="role"  class="form-horizontal">
		<form:hidden path="id"/>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td class="width-15 active">	<label class="pull-right">角色名:</label></td>
		         <td class="width-35" ><form:input path="name" htmlEscape="false" maxlength="50" class="form-control required"/></td>
		         <td class="width-15 active">	<label class="pull-right">角色编码:</label></td>
		         <td class="width-35" ><form:input path="roleCode" htmlEscape="false" maxlength="50" class="form-control required"/></td>
		      </tr>
		       <tr>
		         <td class="width-15 active">	<label class="pull-right">排序:</label></td>
		         <td class="width-35" ><form:input path="sort" htmlEscape="false" maxlength="11" class="form-control required digits"/></td>
		         <td class="width-15 active">	<label class="pull-right">描述:</label></td>
		         <td class="width-35"><form:textarea path="description" htmlEscape="false" rows="3" maxlength="200" class="form-control "/></td>
		      </tr>
		   </tbody>
		   </table>   
	</form:form>
</body>
</html>