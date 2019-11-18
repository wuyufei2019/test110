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
					<td class="width-15 active"><label class="pull-right">违法行为：</label></td>
					<td class="width-85" >${wfxw.m1}</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">违法条款：</label></td>
					<td class="width-85" >${wfxw.m2}</td>
				</tr>
                <tr>
					<td class="width-15 active"><label class="pull-right">违反条款详情：</label></td>
					<td class="width-85" >${wfxw.m3 }</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-85" >${wfxw.m4}</td>
				</tr>
                <tr>
					<td class="width-15 active"><label class="pull-right">处罚标准：</label></td>
					<td class="width-85" >${wfxw.m5 }</td>
				</tr>
				</tbody>
			</table>
		  	
	 </form>
</body>
</html>