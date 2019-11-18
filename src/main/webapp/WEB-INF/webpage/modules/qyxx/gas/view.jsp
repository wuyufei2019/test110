<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车间管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">燃气类别：</label></td>
					<td class="width-30">${entity.m1 }</td>
                    <td class="width-20 active"><label class="pull-right">储存类型：</label></td>
                    <td class="width-30">${entity.m2 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">储罐数量：</label></td>
					<td class="width-30">${entity.m3 }</td>
                    <td class="width-20 active"><label class="pull-right">储罐容积（m³）：</label></td>
                    <td class="width-30">${entity.m4 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">瓶组数量：</label></td>
					<td class="width-30">${entity.m5 }</td>
					<td class="width-20 active"><label class="pull-right">瓶组体积（KG）：</label></td>
					<td class="width-30">${entity.m6 }</td>
				</tr>
			    <tr>
					<td class="width-20 active"><label class="pull-right">管道用量（m³/月）：</label></td>
					<td class="width-30">${entity.m7 }</td>
				</tr>

				</tbody>
			</table>
	</form>
</body>
</html>