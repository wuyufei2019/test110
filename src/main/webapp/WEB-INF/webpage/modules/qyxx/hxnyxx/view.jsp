<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>化学能源信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">能源名称：</label></td>
					<td class="width-30">${hxnyxx.m1 }</td>
					<td class="width-20 active"><label class="pull-right">年用量（t/m3）：</label></td>
					<td class="width-30">${hxnyxx.m2 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">最大储存量（t/m3）：</label></td>
					<td class="width-30">${hxnyxx.m3 }</td>
					<td class="width-20 active"><label class="pull-right">涉及工艺：</label></td>
					<td class="width-30">${hxnyxx.m4 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">是否进行安全评价：</label></td>
					<td class="width-30" >
						<c:if test="${hxnyxx.m5 eq '1'}">是</c:if>
						<c:if test="${hxnyxx.m5 eq '0'}">否</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">是否设置监控设施：</label></td>
					<td class="width-30" >
						<c:if test="${hxnyxx.m5 eq '1'}">是</c:if>
						<c:if test="${hxnyxx.m5 eq '0'}">否</c:if>
					</td>
					
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">设置监控设施内容：</label></td>
					<td class="width-30" colspan="3">${hxnyxx.m8 }</td>			
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${hxnyxx.m7}
					</td>					
				</tr>
				</tbody>
			</table>

	</form>
</body>
</html>