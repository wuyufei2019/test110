<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js?v=1.2"></script>
<title></title>

</head>
<body>
		<form id="inputForm" action="${ctx}/bis/tzsbxx/${action}" method="post">
			<table class="table table-bordered dataTable" style="margin:auto;">
			<tbody>
			<tr>
				<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
				<td class="width-35" colspan="3">${qylist.m1 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">设备编号：</label></td>
				<td class="width-35">${qylist.m2 }</td>
				<td class="width-15 active"><label class="pull-right">设备类型：</label></td>
				<td class="width-35">${qylist.m3 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">规格型号：</label></td>
				<td class="width-35">${qylist.m4 }</td>
				<td class="width-15 active"><label class="pull-right">数量：</label></td>
				<td class="width-35">${qylist.m6 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">主要参数：</label></td>
				<td class="width-35" colspan="3">${qylist.m5 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">功能：</label></td>
				<td class="width-35" colspan="3">${qylist.m7 }</td>
			</tr>				
			<tr>
					<td class="width-15 active"><label class="pull-right">检测日期：</label></td>
					<td><input name="M9" class="easyui-datebox" value="${qylist.m9}" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
					<td class="width-15 active"><label class="pull-right">有效期：</label></td>
					<td><input name="M10" class="easyui-datebox" value="${qylist.m10}" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
			</tr>

					<input type="hidden" name="ID" value="${qylist.ID}" />
				</tbody>
			</table>
		</form>
<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>