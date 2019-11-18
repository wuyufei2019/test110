<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查表库信息查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查单元：</label></td>
					<td class="">${jcbk.m1 }</td>
				</tr>
				<tr style="">
					<td class="width-15 active"><label class="pull-right">检查内容：</label></td>
					<td class="">${jcbk.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查依据：</label></td>
					<td class="">${jcbk.m3 }</td>
				</tr>
				<tr style="">
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="">${jcbk.m4 }</td>
				</tr>
				</tbody>
			</table>
		  	
	 </form>
</body>
</html>