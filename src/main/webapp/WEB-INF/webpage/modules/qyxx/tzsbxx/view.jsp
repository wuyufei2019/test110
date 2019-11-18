<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>
</head>
<body>
		<form class="form-horizontal">
			<table class="table table-bordered dataTable" style="margin:auto;">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-35" colspan="3">${qylist.M1 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-35">${qylist.M2 }</td>
					<td class="width-15 active"><label class="pull-right">设备类型：</label></td>
					<td class="width-35">${qylist.label }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">规格型号：</label></td>
					<td class="width-35">${qylist.M4 }</td>
					<td class="width-15 active"><label class="pull-right">出厂年月：</label></td>
					<td><fmt:formatDate value="${qylist.m15}" pattern="yyyy-MM-dd"  /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">生产厂名：</label></td>
					<td class="width-35" >${qylist.m16 }</td>
					<td class="width-15 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-35" >${qylist.m17 }</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">注册代码：</label></td>
					<td class="width-35" >${qylist.m18 }</td>					
					<td class="width-15 active"><label class="pull-right">始用年月：</label></td>
					<td><fmt:formatDate value="${qylist.m14}" pattern="yyyy-MM-dd"  /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">上次审验：</label></td>
					<td><fmt:formatDate value="${qylist.m9}" pattern="yyyy-MM-dd"  /></td>
					<td class="width-15 active"><label class="pull-right">下次审验：</label></td>
					<td><fmt:formatDate value="${qylist.m10}" pattern="yyyy-MM-dd"  /></td>
				</tr>
				<tr>	
					<td class="width-15 active"><label class="pull-right">使用部门：</label></td>
					<td class="width-35" >${qylist.dep}</td>
					<td class="width-15 active"><label class="pull-right">使用地点：</label></td>
					<td class="width-35" >${qylist.m20 }</td>				
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">安全附件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty qylist.m12}">
					 <c:forTokens items="${qylist.m12}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${qylist.M8 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">相关信息：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${qylist.m21 }</td>
				</tr>
				</tbody>
			</table>
		</form>
</body>
</html>