<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js?v=1.0"></script>
<title></title>

</head>
<body>
		<form id="inputForm" action="${ctx}/yhpc/aqkf/${action}" method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="margin:auto;">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">人员姓名：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M1" class="easyui-textbox" value="${aqkf.m1 }" data-options="validType:'length[0,10]'"/></td>
					<td class="width-15 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-35"><input id="M2" name="M2" value="${aqkf.m2}"  class="easyui-combobox" style="width: 100%;height: 30px;" data-options="editable:false,panelHeight:110, valueField: 'id',textField: 'text',url:'${ctx}/system/department/deptjson' "/></td>
				</tr>
				
				<c:if test="${not empty aqkf.ID}">
					<input type="hidden" name="ID" value="${aqkf.ID}" />
					<input type="hidden" name="ID1" value="${aqkf.ID1}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${aqkf.s1}" pattern="yyyy-MM-dd HH:mm:ss" />" />
					<input type="hidden" name="S3" value="${aqkf.s3}" />					
					<input type="hidden" name="M3" value="<fmt:formatDate value="${aqkf.m3}" pattern="yyyy-MM-dd HH:mm:ss" />" />
				</c:if>
				</tbody>
			</table>
		</form>
<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>