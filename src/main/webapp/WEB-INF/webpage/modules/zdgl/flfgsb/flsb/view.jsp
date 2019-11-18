<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>法律法规识别</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">大类别：</label></td>
					<td class="width-35">
						<c:if test="${flfg.m1_1 eq '1'}">法律</c:if>
						<c:if test="${flfg.m1_1 eq '2'}">法规</c:if>
						<c:if test="${flfg.m1_1 eq '3'}">规章</c:if>
						<c:if test="${flfg.m1_1 eq '4'}">地方性法规</c:if>
						<c:if test="${flfg.m1_1 eq '5'}">政府文件</c:if>
						<c:if test="${flfg.m1_1 eq '6'}">标准规范</c:if>
						<c:if test="${flfg.m1_1 eq '7'}">其他</c:if>
					</td>
					<td class="width-15 active"><label class="pull-right">小类别：</label></td>
					<td class="width-35">${flfg.lb }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">法律法规名称：</label></td>
					<td class="width-35" colspan="3">${flfg.m2 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">颁发单位：</label></td>
					<td class="width-35">${flfg.m3 }</td>
					<td class="width-15 active"><label class="pull-right">文件编号：</label></td>
					<td class="width-35">${flfg.m4 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-35" ><fmt:formatDate pattern="yyyy-MM-dd" value="${flfg.m5 }" /></td>
					<td class="width-15 active"><label class="pull-right">实施日期：</label></td>
					<td class="width-35" ><fmt:formatDate pattern="yyyy-MM-dd" value="${flfg.m6 }" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">适用条款：</label></td>
					<td class="width-35">${flsb.m1 }</td>
					<td class="width-15 active"><label class="pull-right">适用部门：</label></td>
					<td class="width-35">${flsb.sybm }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">Gap（差异）：</label></td>
					<td class="width-35" colspan="3">${flsb.m13 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3">${flsb.m3 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">识别人：</label></td>
					<td class="width-35">${flsb.sbr }</td>
					<td class="width-15 active"><label class="pull-right">识别日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${flsb.m6 }" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">识别部门：</label></td>
					<td class="width-35">${flsb.sbbm }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核人：</label></td>
					<td class="width-35">${flsb.spr }</td>
					<td class="width-15 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${flsb.m9 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-35">${flsb.spyj }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">批准人：</label></td>
					<td class="width-35">${flsb.pzr }</td>
					<td class="width-15 active"><label class="pull-right">批准日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${flsb.m12 }" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">批准意见：</label></td>
					<td class="width-35">${flsb.pzyj }</td>
				</tr>
				</tbody>
			</table>
	</form>
</body>
</html>