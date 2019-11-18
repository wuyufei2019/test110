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
					<td class="width-15 active"><label class="pull-right">用品名称：</label></td>
					<td class="width-35">${entity.name }</td>
					<td class="width-15 active"><label class="pull-right">用品编号：</label></td>
					<td class="width-35">${entity.number }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">所在仓库：</label></td>
					<td class="width-35"><input  class="easyui-combobox" value="${entity.ID2 }"
						style="width: 100%;height: 30px;"
						data-options="readonly: true,valueField: 'id',textField: 'text',url:'${ctx}/lbyp/ckxx/idjson'" /></td>
					<td class="width-15 active"><label class="pull-right">库存量：</label></td>
					<td class="width-35">${entity.storagerate }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">计量单位：</label></td>
					<td class="width-35">${entity.unit }</td>
					<td class="width-15 active"><label class="pull-right">型号/规格：</label></td>
					<td class="width-35">${entity.specifications }</td>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>