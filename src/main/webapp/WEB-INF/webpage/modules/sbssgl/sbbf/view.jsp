<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备报废</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-80" colspan="3">${sbbf.qyname }</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">使用部门：</label></td>
					<td class="width-30" style="height: 30px;">${sbbf.deptname }</td>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30" style="height: 30px;">${sbbf.m2 }</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" style="height: 30px;">${sbbf.m1 }</td>
					<td class="width-20 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-30" style="height: 30px;">${sbbf.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-30" style="height: 30px;">${sbbf.m4 }</td>
				   	<td class="width-20 active"><label class="pull-right">设备制造商：</label></td>
					<td class="width-30" style="height: 30px;">${sbbf.m5 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">购入时间：</label></td>
					<td class="width-30" style="height: 30px;"><fmt:formatDate value="${sbbf.m6 }" pattern="yyyy-MM-dd"/></td>
				   	<td class="width-20 active"><label class="pull-right">资产原/净值：</label></td>
					<td class="width-30" style="height: 30px;">${sbbf.zcy }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">规定使用年限：</label></td>
					<td class="width-30" style="height: 30px;">${sbbf.gdsynx }</td>
				   	<td class="width-20 active"><label class="pull-right">实际使用年限：</label></td>
					<td class="width-30" style="height: 30px;">${sbbf.sjsynx }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">报废原因：</label></td>
					<td class="width-80" colspan="3" style="height: 80px;">${sbbf.reason }</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>