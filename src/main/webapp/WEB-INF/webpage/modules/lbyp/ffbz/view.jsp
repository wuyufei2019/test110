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
					<td class="width-15 active"><label class="pull-right">工种(岗位)名称：</label></td>
					<td class="width-35" colspan="3">${entity.jobtype }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">用品名称：</label></td>
					<td class="width-35" colspan="3">${entity.goodsname }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发放数量：</label></td>
					<td class="width-35">${entity.amount }</td>
					<td class="width-15 active"><label class="pull-right">发放周期：</label></td>
					<td class="width-35">&nbsp;&nbsp;${entity.cyclemonth}&nbsp;&nbsp;月
						</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>