<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>资质管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">有效期起：</label></td>
					<td class="width-35" colspan="3"><fmt:formatDate value="${zz.m4 }"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">有效期止：</label></td>
					<td class="width-35" colspan="3"><fmt:formatDate value="${zz.m5 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">资质证书名称：</label></td>
					<td class="width-35" colspan="3">${zz.m1 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">级别：</label></td>
					<td class="width-35" colspan="3">${zz.m2 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">许可内容：</label></td>
					<td class="width-35" colspan="3">${zz.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">上传文件：</label></td>
					<td class="width-35" colspan="3">
						 <c:if test="${not empty zz.m6}">
						 <c:forTokens items="${zz.m6}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="margin: 5px;"> 
				            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
				            </div>
						 </c:forTokens>
						 </c:if>
					</td>
				</tr>
				</tbody>
			</table>
		  	
       </form>
</body>
</html>