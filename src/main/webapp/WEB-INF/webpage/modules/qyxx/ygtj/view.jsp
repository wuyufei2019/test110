<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>员工体检信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">员工姓名：</label></td>
					<td class="width-30">${ygtjlist.m7 }</td>
					
					<td class="width-20 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-30">${ygtjlist.m1 }</td>
				<tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">接触危害因素：</label></td>
					<td class="width-85" colspan="3">${ygtjlist.m9 }</td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">体检类型：</label></td>
					<td class="width-30">${ygtjlist.m2 }</td>
					
					<td class="width-20 active"><label class="pull-right">体检医院：</label></td>
					<td class="width-30" colspan="3">${ygtjlist.m4 }</td>				
				<tr>
						
				<tr>
					<td class="width-20 active"><label class="pull-right">体检日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${ygtjlist.m3 }" /></td>
					
					<td class="width-20 active"><label class="pull-right">下次体检日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${ygtjlist.m8 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检结果：</label></td>
					<td class="width-85" colspan="3" style="height:80px">${ygtjlist.m10}</td>						
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检结论：</label></td>
					<td class="width-85" colspan="3" style="height:80px">${ygtjlist.m5}</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" >${ygtjlist.m6}</td>					
				</tr>
				</tbody>
			</table>
       </form>
</body>
</html>