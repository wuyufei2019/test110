<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>文件发布信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">文件编号：</label></td>
					<td class="width-35" colspan="3">${wjfb.m2 }</td>
				</tr>
			  	<tr >
					<td class="width-15 active"><label class="pull-right">文件名称：</label></td>
					<td class="width-35" colspan="3">${wjfb.m1 }</td>
				</tr>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">类别：</label></td>
					<td class="width-35">${wjfb.lb }</td>
					<td class="width-15 active"><label class="pull-right">性质：</label></td>
					<td class="width-35">${wjfb.xz }</td>
				</tr>  
				<tr>
					<td class="width-15 active"><label class="pull-right">发送部门：</label></td>
					<td class="width-35" colspan="3">${wjfb.fsbm }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">文件内容：</label></td>
					<td class="width-35" colspan="3">${wjfb.m5 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件（单文件）：</label></td>
					<td colspan="3">
						<c:if test="${not empty wjfb.m6}">
						<c:set var="url" value="${fn:split(wjfb.m6, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发布人：</label></td>
					<td class="width-35">${wjfb.fbr }</td>
					<td class="width-15 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd" value="${wjfb.s1 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核人：</label></td>
					<td class="width-35">${wjfb.spr }</td>
					<td class="width-15 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${wjfb.m9 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-35">${wjfb.spyj }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">批准人：</label></td>
					<td class="width-35">${wjfb.pzr }</td>
					<td class="width-15 active"><label class="pull-right">批准日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${wjfb.m12 }" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">批准意见：</label></td>
					<td class="width-35">${wjfb.pzyj }</td>
				</tr>
				</tbody>
			</table>
	</form>
</body>
</html>