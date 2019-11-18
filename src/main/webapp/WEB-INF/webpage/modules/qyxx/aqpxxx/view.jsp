<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
</head>
<body>
<form class="form-horizontal">
		<div style="height:auto;padding:5px 5px;">
			<table class="table table-bordered dataTable"
				style="margin:auto;">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">培训人员姓名：</label></td>
					<td class="width-35">${qylist.m1 }</td>
					<td class="width-15 active"><label class="pull-right">安全培训人员类别：</label></td>
					<td class="width-35">${qylist.m2 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">培训人员职务：</label></td>
					<td class="width-35">${qylist.m3 }</td>
					<td class="width-15 active"><label class="pull-right">有效期：</label></td>
					<td class="width-35"><fmt:formatDate value="${qylist.m9 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">最近培训时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${qylist.m4 }" /></td>
					<td class="width-15 active"><label class="pull-right">下次培训时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${qylist.m5 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">证书名称：</label></td>
					<td class="width-35">${qylist.m10 }</td>
					<td class="width-15 active"><label class="pull-right">证书编号：</label></td>
					<td class="width-35">${qylist.m11 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发证机关：</label></td>
					<td class="width-35" colspan="3">${qylist.m6 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">培训证书文件：</label></td>
					<td class="width-85" colspan="3">
					 <c:if test="${not empty qylist.m8}">
					 <c:forTokens items="${qylist.m8}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/>${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" >
						${qylist.m7 }
					</td>
				</tr>
				</tbody>
			</table>
        </div>
        </form>
</body>
</html>