<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>
<body>
<form class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
					<td class="width-15 active"><label class="pull-right">类别：</label></td>
					<td class="width-35" colspan="3">${qylist.m1 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">辨识物质：</label></td>
					<td class="width-35" colspan="3">${qylist.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">最大储量(t)：</label></td>
					<td class="width-35">${qylist.m3 }</td>
					<td class="width-15 active"><label class="pull-right">临界储量(t)：</label></td>
					<td class="width-35">${qylist.m4 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">危险性分类及说明：</label></td>
					<td class="width-35" colspan="3">${qylist.m7 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3">${qylist.m6 }</td>
				</tr>
		</table>
</form>
</body>
</html>