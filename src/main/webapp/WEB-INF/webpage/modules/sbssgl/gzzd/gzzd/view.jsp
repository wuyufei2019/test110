<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>规章制度</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<%-- <tr>
					<td class="width-20 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-80" colspan="3">${gzzd.qyname }</td>
				</tr>  --%>
				<tr>
					<td class="width-20 active"><label class="pull-right">规章制度名称：</label></td>
					<td class="width-80" style="height: 30px;" colspan="3">${gzzd.m1 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">颁布单位：</label></td>
					<td class="width-30" style="height: 30px;">${gzzd.m4 }</td>
				   	<td class="width-20 active"><label class="pull-right">文件编号：</label></td>
					<td class="width-30" style="height: 30px;">${gzzd.m5 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-30" style="height: 30px;"><fmt:formatDate value="${gzzd.m6 }" pattern="yyyy-MM-dd"/></td>
				   	<td class="width-20 active"><label class="pull-right">实施日期：</label></td>
					<td class="width-30" style="height: 30px;"><fmt:formatDate value="${gzzd.m7 }" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">摘要：</label></td>
					<td class="width-80" style="height: 80px;" colspan="3">${gzzd.m8 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">文件：</label></td>
					<td class="width-80" style="height: 30px;" colspan="3">
						<c:if test="${not empty gzzd.m3}">
							<c:set var="url" value="${fn:split(gzzd.m3, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>