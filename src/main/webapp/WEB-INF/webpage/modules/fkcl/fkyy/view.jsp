<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>访客预约信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">预约状态：</label></td>
					<td class="width-35">${entity.zt }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">预约时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${entity.m1}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
					<td class="width-15 active"><label class="pull-right">被预约人：</label></td>
					<td class="width-35">${entity.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">事由：</label></td>
					<td class="width-85" colspan="3">${entity.m3 }</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">预约人：</label></td>
					<td class="width-35">${entity.m4 }</td>
					<td class="width-15 active"><label class="pull-right">手机号码：</label></td>
					<td class="width-35">${entity.m5 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">预约确认人员：</label></td>
					<td class="width-35">${entity.m6 }</td>
					<td class="width-15 active"><label class="pull-right">预约确认时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${entity.m7 }" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">进厂确认人员：</label></td>
					<td class="width-35">${entity.m8 }</td>
					<td class="width-15 active"><label class="pull-right">出厂确认人员：</label></td>
					<td class="width-35">${entity.m11 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">进厂时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${entity.m9 }" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
					<td class="width-15 active"><label class="pull-right">出厂时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${entity.m12 }" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">进厂人数：</label></td>
					<td class="width-35" >${entity.m10 }</td>
					<td class="width-15 active"><label class="pull-right">出厂人数：</label></td>
					<td class="width-35" >${entity.m13 }</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>