<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>卡口信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">卡口名称：</label></td>
					<td class="width-80">${kkxx.m1 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">卡口位置：</label></td>
					<td class="width-80">${kkxx.m2 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">卡口设备：</label></td>
					<td class="width-80">${kkxx.m3}</td>
				</tr>
				</tbody>
			</table>

	</form>
</body>
</html>