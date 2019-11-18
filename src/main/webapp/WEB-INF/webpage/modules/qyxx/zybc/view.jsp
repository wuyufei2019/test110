<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>作业班次信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">班次：</label></td>
					<td class="width-35">${zybclist.m1 }</td>
					<td class="width-15 active"><label class="pull-right">人数：</label></td>
					<td class="width-35">${zybclist.m4 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">接班时间：</label></td>
					<td class="width-35">${zybclist.m2 }</td>
					<td class="width-15 active"><label class="pull-right">交班时间：</label></td>
					<td class="width-35">${zybclist.m3 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${zybclist.m5}</td>					
				</tr>
				
				</tbody>
			</table>
       </form>
</body>
</html>