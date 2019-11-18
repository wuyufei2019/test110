<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工卡管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">标签号：</label></td>
					<td class="width-35" colspan="3">
						${pub_file.fileid }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">标签编码：</label></td>
					<td class="width-35">${pub_file.filecode }</td>
					<td class="width-15 active"><label class="pull-right">标签：</label></td>
					<td class="width-35">${pub_file.tag }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">载入时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${pub_file.intime }" pattern="YYYY-MM-dd HH:mm:ss" /></td>
					<td class="width-15 active"><label class="pull-right">更新时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${pub_file.uptime }" pattern="YYYY-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">状态：</label></td>
					<td class="width-35" >${pub_file.val1 }</td>
					<td class="width-15 active"><label class="pull-right">是否在线：</label></td>
					<td class="width-35" >
							${pub_file.online }
						</td>
				</tr>
				</tbody>
			</table>
	</form>
</body>
</html>