<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车辆信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		    <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">车牌号码：</label></td>
					<td class="width-30">${clxx.m1 }</td>

					<td class="width-20 active"><label class="pull-right">车型：</label></td>
					<td class="width-30">${clxx.m2 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">驾驶员姓名：</label></td>
					<td class="width-30">${clxx.m3 }</td>

					<td class="width-20 active"><label class="pull-right">驾驶员手机号码：</label></td>
					<td class="width-30">${clxx.m4 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">押送员姓名：</label></td>
					<td class="width-30">${clxx.m5}</td>

					<td class="width-20 active"><label class="pull-right">押送员手机号码：</label></td>
					<td class="width-30">${clxx.m6}</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">允许行驶的线路：</label></td>
					<td class="width-30" colspan="3">${clxx.m7 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">允许停泊的位置：</label></td>
					<td class="width-30">${clxx.m8}</td>

					<td class="width-20 active"><label class="pull-right">允许停泊的时长（H）：</label></td>
					<td class="width-30">${clxx.m9}</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3" style="height:80px">${clxx.m10}</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>