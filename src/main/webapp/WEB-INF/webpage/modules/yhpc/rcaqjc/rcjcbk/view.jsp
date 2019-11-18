<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日常檢查表庫</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-80" >${rcjcbk.m1 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">单元：</label></td>
					<td class="width-80" >${rcjcbk.m2 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-80" style="height:80px">${rcjcbk.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">依据：</label></td>
					<td class="width-80" style="height:80px">${rcjcbk.m4}</td>					
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" style="height:80px">${rcjcbk.m5}</td>					
				</tr>
				</tbody>
			</table>
       </form>
 
</body>
</html>