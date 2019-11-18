<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>法律法规库</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">法律法规名称：</label></td>
					<td class="width-35" colspan="3">
						${flfg.m2 }
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">颁布单位：</label></td>
					<td class="width-35">
						${flfg.m3 }
					</td>
					<td class="width-15 active"><label class="pull-right">文件编号：</label></td>
					<td class="width-35">${flfg.m4 }</td>
				</tr>
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
                    <c:if test="${flfg.id1 != null && flfg.id1 != ''}">
					<td class="width-15 active"><label class="pull-right">小类别：</label></td>
					<td class="width-35">${flfg.lb }</td>
                    </c:if>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-35" ><fmt:formatDate pattern="yyyy-MM-dd" value="${flfg.m5 }" /></td>
					<td class="width-15 active"><label class="pull-right">实施日期：</label></td>
					<td class="width-35" ><fmt:formatDate pattern="yyyy-MM-dd" value="${flfg.m6 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">摘要：</label></td>
					<td class="width-35" colspan="3">${flfg.m7 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件（单文件）：</label></td>
					<td colspan="3">
						<c:if test="${not empty flfg.m8}">
						<c:set var="url" value="${fn:split(flfg.m8, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">录入人员：</label></td>
					<td class="width-35">${flfg.lrname }</td>
					<td class="width-15 active"><label class="pull-right">录入时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${flfg.s1 }" /></td>
				</tr>
				</tbody>
			</table>
	</form>
</body>
</html>