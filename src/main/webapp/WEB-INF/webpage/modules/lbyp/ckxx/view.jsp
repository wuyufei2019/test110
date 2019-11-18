<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>劳保用品仓库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">仓库名称：</label></td>
					<td class="width-35">${entity.name }</td>
					<td class="width-15 active"><label class="pull-right">仓库编号：</label></td>
					<td class="width-35">${entity.number }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">负责人：</label></td>
					<td class="width-35">${entity.principle }</td>
					<td class="width-15 active"><label class="pull-right">负责人电话：</label></td>
					<td class="width-35">${entity.phone}</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35" colspan="3">${entity.address }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3">${entity.note }</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>