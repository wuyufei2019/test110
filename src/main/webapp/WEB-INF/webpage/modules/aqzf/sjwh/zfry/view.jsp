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
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35" colspan="3">${zfry.m1}</td>
				</tr>
				
				<tr>
				    <td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35" >${zfry.m2}</td>
					<td class="width-15 active"><label class="pull-right">证件号：</label></td>
					<td class="width-35" >${zfry.m3}</td>
				</tr>

                <tr>
				<td class="width-15 active"><label class="pull-right">职称：</label></td>
					<td class="width-35" >${zfry.m4}</td>
				<td class="width-15 active"><label class="pull-right">类别：</label></td>
						<td class="width-35" >${zfry.m7}</td>	
				</tr>
				<tr>
				<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
						<td class="width-35" >${zfry.m5}</td>
				</tr>
				</tbody>
			</table>
		  	
	 </form>
</body>
</html>