<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>文件接收回执</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
</head>
<body>
	<form id="inputForm" action="${ctx}/issue/wjcdjs/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
                <tr>
                <td class="width-15 active"><label class="pull-right">回执内容：</label></td>
                         <td class="width-35" style="height:50px">${ife.m4 }</td>
                </tr>
					<tr>
					<td class="width-15 active"><label class="pull-right">回执附件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty ife.url}">
					 <c:forTokens items="${ife.url}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
			<tbody>
		</table>
	</form>
	</body>
</html>