<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>培训计划信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >  
					<td class="width-15 active"><label class="pull-right">培训计划名称：</label></td>
					<td class="width-85" colspan="3" >
					${aqpxjh.m1 }
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">培训课程：</label></td>
					<td class="width-30">${kclist }</td>
					<td class="width-20 active"><label class="pull-right">培训部门：</label></td>
					<td class="width-30">${bmlist }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">培训日期起：</label></td>
					<td class="width-30">${aqpxjh.m5 }</td>
					<td class="width-20 active"><label class="pull-right">培训日期止：</label></td>
					<td class="width-30">${aqpxjh.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">培训学时(h)：</label></td>
					<td class="width-30">${aqpxjh.m3 }</td>
					<td class="width-20 active"><label class="pull-right">培训类别：</label></td>
					<td class="width-30">${aqpxjh.m2 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">${aqpxjh.m7 }</td>	
				</tr>
				
			</table>

		  	</tbody>
	 </form>
</body>
</html>