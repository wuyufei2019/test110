<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>危化品安全信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">品名：</label></td>
					<td class="width-35" colspan="3" >
						${res.m2 }
					</td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">别名：</label></td>
					<td class="width-35" colspan="3" >
						${res.m3 }
					</td>
				</tr>
								
				<tr >
					<td class="width-15 active"><label class="pull-right">英文名：</label></td>
					<td class="width-35" colspan="3" >
						${res.m4 }
					</td>
				</tr>
				
				<tr >
					<td class="width-15 active"><label class="pull-right">CAS：</label></td>
					<td class="width-35" colspan="3" >
						${res.m5 }
					</td>
				</tr>
				
				<tr >
					<td class="width-15 active"><label class="pull-right">危险性类别：</label></td>
					<td class="width-35" colspan="3" >
						${res.m6 }
					</td>
				</tr>
				
				<tr >
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" >
						${res.m7 }
					</td>
				</tr>
				</tbody>
			</table>

       </form>
</body>
</html>