<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>台时确定</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<%-- <tr>
					<td class="width-20 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-80" colspan="3">${tsqd.qyname }</td>
				</tr> --%> 
				<tr>
					<td class="width-25 active"><label class="pull-right">使用单位：</label></td>
					<td class="width-25">${tsqd.deptname }</td>
					<td class="width-25 active"><label class="pull-right">日期：</label></td>
					<td class="width-25">${tsqd.m3 }</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">主要设备制度开动台时（H）：</label></td>
					<td class="width-25">${tsqd.m1}</td>
					<td class="width-25 active"><label class="pull-right">主要设备实际开动台时（H）：</label></td>
					<td class="width-25">${tsqd.m2}</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>