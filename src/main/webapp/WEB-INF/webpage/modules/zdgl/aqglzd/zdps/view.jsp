<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全管理制度</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">选择制度：</label></td>
					<td class="width-35" colspan="3">${zd }</td>
				</tr>
			 	<tr >
					<td class="width-15 active"><label class="pull-right">评审主题：</label></td>
					<td class="width-35" colspan="3">${aqps.m2 }</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">主持人：</label></td>
					<td class="width-35" >${aqps.m5 }</td>
					<td class="width-15 active"><label class="pull-right">评审日期：</label></td>
					<td class="width-35" ><fmt:formatDate pattern="yyyy-MM-dd" value="${aqps.m3 }" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">地点：</label></td>
					<td class="width-35" colspan="3">${aqps.m4 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">评审依据：</label></td>
					<td class="width-35" colspan="3">${aqps.m6 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">评审范围：</label></td>
					<td class="width-35" colspan="3">${aqps.m7 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">评审人员：</label></td>
					<td class="width-35" colspan="3">${aqps.m8 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评审纪要：</label></td>
					<td class="width-35" colspan="3">${aqps.m9 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">评审结论：</label></td>
					<td class="width-35" colspan="3">${aqps.m10 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">录入人：</label></td>
					<td class="width-35" colspan="3">${aqps.lrr }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核人：</label></td>
					<td class="width-35">${aqps.spr }</td>
					<td class="width-15 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${aqps.m14 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-35">${aqps.spyj }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">批准人：</label></td>
					<td class="width-35">${aqps.pzr }</td>
					<td class="width-15 active"><label class="pull-right">批准日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${aqps.m17 }" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">批准意见：</label></td>
					<td class="width-35">${aqps.pzyj }</td>
				</tr>
			  </tbody>
			</table>

	</form>
</body>
</html>