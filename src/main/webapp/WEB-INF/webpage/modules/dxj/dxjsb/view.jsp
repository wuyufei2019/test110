<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
			<tr>
				<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
				<td class="width-85" colspan="3">${sb.m1 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">绑定二维码：</label></td>
				<td class="width-35">${sb.bindcontent }</td>
				<td class="width-15 active"><label class="pull-right">绑定rfid：</label></td>
				<td class="width-35">${sb.rfid }</td>
			</tr>
			<tr >
				<td class="width-15 active" ><label class="pull-right">rfid卡批次代码：</label></td>
				<td class="width-35">${sb.area }</td>				
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
				<td class="width-85" colspan="3">
					 <c:if test="${not empty sb.m2}">
						 <c:forTokens items="${sb.m2}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" height="150px;"/><br/>${urlna[1]}</a>
						 	</div>
						 </c:forTokens>
					 </c:if>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>