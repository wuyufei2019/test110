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
				<tr >
					<td class="width-15 active"><label class="pull-right">制度编号：</label></td>
					<td class="width-35" colspan="3">${glzd.m2 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">制度名称：</label></td>
					<td class="width-35" colspan="3">${glzd.m1 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">版本号：</label></td>
					<td class="width-35">${glzd.m3 }</td>
					<td class="width-15 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-35" ><fmt:formatDate pattern="yyyy-MM-dd" value="${glzd.m4 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">制度正文：</label></td>
					<td class="width-35" colspan="3">${glzd.m5 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件（单文件）：</label></td>
					<td colspan="3">
						<c:if test="${not empty glzd.m6}">
						<c:set var="url" value="${fn:split(glzd.m6, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">编辑部门：</label></td>
					<td class="width-35">${glzd.bjbm }</td>
					<td class="width-15 active"><label class="pull-right">适用部门：</label></td>
					<td class="width-35">${glzd.sybm }</td>
				</tr>
                <c:if test="${not his}">
                    <tr>
                        <td class="width-15 active"><label class="pull-right">审核人：</label></td>
                        <td class="width-35">${glzd.spr }</td>
                        <td class="width-15 active"><label class="pull-right">审核日期：</label></td>
                        <td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${glzd.m11 }" /></td>
                    </tr>
                    <tr>
                        <td class="width-15 active"><label class="pull-right">审核意见：</label></td>
                        <td class="width-35">${glzd.spyj }</td>
                    </tr>
                    <tr>
                        <td class="width-15 active"><label class="pull-right">批准人：</label></td>
                        <td class="width-35">${glzd.pzr }</td>
                        <td class="width-15 active"><label class="pull-right">批准日期：</label></td>
                        <td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${glzd.m14 }" /></td>
                    </tr>
                    <tr >
                        <td class="width-15 active"><label class="pull-right">批准意见：</label></td>
                        <td class="width-35">${glzd.pzyj }</td>
                    </tr>
                </c:if>

				</tbody>
			</table>

	</form>
</body>
</html>