<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>发放记录查看</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table
		class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
			<tr>
				<td class="width-15 active"><label class="pull-right">领取日期：</label></td>
				<td class="width-35"><fmt:formatDate value="${entity.time}"
						type="both" /></td>
				<td class="width-15 active"><label class="pull-right">领取人：</label></td>
				<td class="width-35">${entity.receiveperson }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">用品名称：</label></td>
				<td class="width-35">${entity.goodsname }</td>
				<td class="width-15 active"><label class="pull-right">用品数量：</label></td>
				<td class="width-35">${entity.amount }</td>
			</tr>

		</tbody>
	</table>
	</form>
	<script type="text/javascript">
		
	</script>


</body>
</html>