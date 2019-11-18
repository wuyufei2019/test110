<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全生产执法字典查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">标签：</label></td>
					<td class="width-30" colspan="3">
						${dict.m1}
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30" colspan="3">
						${dict.m2}
					</td>
				</tr>
				
				<tr>
				    <td class="width-20 active"><label class="pull-right">类别编码：</label></td>
					<td class="width-30" >
						${dict.m3}
					</td>
					<td class="width-20 active"><label class="pull-right">排序：</label></td>
					<td class="width-30" >
						${dict.m4}
					</td>
				</tr>
				</tbody>
			</table>
		  	
	 </form>
</body>
</html>