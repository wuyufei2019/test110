<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>冶金信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">承包商名称：</label></td>
					<td class="width-35" colspan="3">${cbslist.m1 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">承包项目：</label></td>
					<td class="width-35" colspan="3">${cbslist.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-35" colspan="3">${cbslist.m3 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">作业人数：</label></td>
					<td class="width-35" >
						${cbslist.m4 }
					</td>
					<td class="width-15 active"><label class="pull-right">承包商负责人 ：</label></td>
					<td class="width-35" >
						${cbslist.m8 }
					</td>
					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">联系方式：</label></td>
					<td class="width-35" colspan="3">${cbslist.m9 }</td>
				</tr>

			<tr>
					<td class="width-15 active"><label class="pull-right">资质情况：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty cbslist.m5}">
					 <c:forTokens items="${cbslist.m5}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
			</tr>
			
			<tr>
					<td class="width-15 active"><label class="pull-right">承包协议：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty cbslist.m6}">
					 <c:forTokens items="${cbslist.m6}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
			</tr>
			
			<tr>
					<td class="width-15 active"><label class="pull-right">安全工管协议：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty cbslist.m7}">
					 <c:forTokens items="${cbslist.m7}" delims="," var="url" varStatus="urls">
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