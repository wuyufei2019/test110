<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">受限空间名称：</label></td>
					<td class="width-35" colspan="3">${sxkj.m5 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">主要危险有害物质：</label></td>
					<td class="width-35" colspan="3">${sxkj.m6 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">数量：</label></td>
					<td class="width-35" colspan="3">${sxkj.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">位置：</label></td>
					<td class="width-35" colspan="3">${sxkj.m3 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">易导致事故类型：</label></td>
					<td class="width-35" colspan="3">${sxkj.m7 }</td>
				</tr>
				<tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">安全设施（预防/应急）：</label></td>
					<td class="width-35" colspan="3">${sxkj.m8 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${sxkj.m9}</td>					
				</tr>

				</tbody>
			</table>
       </form>
</body>
</html>