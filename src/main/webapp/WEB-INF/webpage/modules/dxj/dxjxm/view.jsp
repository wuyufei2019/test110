<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备项目信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
			<tr>
				<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
				<td class="width-35" colspan="3">${sbxm.qyname }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
				<td class="width-35">${sbxm.sbm }</td>
				<td class="width-15 active"><label class="pull-right">设备项目名称：</label></td>
				<td class="width-35">${sbxm.name }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">标准：</label></td>
				<td class="width-35">${sbxm.standard }</td>
				<td class="width-15 active"><label class="pull-right">范围：</label></td>
				<td class="width-35">${sbxm.scope }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">点检方法：</label></td>
				<td class="width-35" >${sbxm.checkmethod }</td>
			</tr>
		</tbody>
	</table>
</body>
</html>