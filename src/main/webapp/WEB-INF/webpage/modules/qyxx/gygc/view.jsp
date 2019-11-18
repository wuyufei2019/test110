<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		  <tbody>
		<tr>
			<td class="width-15 active"><label class="pull-right">类别：</label></td>
			<td class="width-35">${gygclist.m1 }</td>
			<td class="width-15 active"><label class="pull-right">占地面积：</label></td>
			<td class="width-35">${gygclist.m2 }</td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">建筑结构：</label></td>
			<td class="width-35" colspan="3">${gygclist.m3 }</td>
		</tr>

		<tr>
			<td class="width-15 active"><label class="pull-right">主要危险性：</label></td>
			<td class="width-35" colspan="3">${gygclist.m4}</td>
		</tr>

		<tr>
			<td class="width-15 active"><label class="pull-right">备注：</label></td>
			<td class="width-35" colspan="3">${gygclist.m6}</td>
		</tr>

		<tr>
			<td class="width-15 active"><label class="pull-right">照片：</label></td>
			<td class="width-85" colspan="3"><c:if
					test="${not empty gygclist.m5}">
					<c:forTokens items="${gygclist.m5}" delims="," var="url"
						varStatus="urls">
						<c:set var="urlna" value="${fn:split(url, '||')}" />
						<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							<a target="_blank" href="${urlna[0]}">
							<img src="${urlna[0]}" alt="${urlna[1]}" width="300px;" /><br />
							${urlna[1]}</a>
						</div>
					</c:forTokens>
				</c:if></td>
		</tr>
		</tbody>
	</table>
	</form>
</body>
</html>