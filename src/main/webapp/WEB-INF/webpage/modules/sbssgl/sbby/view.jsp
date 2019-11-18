<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备保养</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">保养计划标题：</label></td>
					<td class="width-80" colspan="3">${sbby.jhbt }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-80" colspan="3">${sbby.qyname }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">操作人：</label></td>
					<td class="width-30" >${sbby.czr }</td>
					<td class="width-20 active"><label class="pull-right">保养计划年份：</label></td>
					<td class="width-30" >${sbby.jhnf }年</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">保养计划类型：</label></td>
					<td class="width-30" >
						<c:choose>
							<c:when test="${sbby.jhlx == '1'}">
								月度
							</c:when>
							<c:when test="${sbby.jhlx == '2'}">
								季度
							</c:when>
							<c:when test="${sbby.jhlx == '3'}">
								半年度
							</c:when>
							<c:otherwise>
								年度
							</c:otherwise>
						</c:choose>
					</td>
					<td class="width-20 active"><label class="pull-right">执行保养期限：</label></td>
					<td class="width-30" >
						<c:choose>
							<c:when test="${sbby.jhlx == '1'}">
								${sbby.m1 }月
							</c:when>
							<c:when test="${sbby.jhlx == '2'}">
								第${sbby.m1 }季度
							</c:when>
							<c:when test="${sbby.jhlx == '3'}">
								<c:if test="${sbby.m1 == '1'}">
									上半年度
								</c:if>
								<c:if test="${sbby.m1 == '2'}">
									下半年度
								</c:if>
							</c:when>
							<c:otherwise>
								全年
							</c:otherwise>
						</c:choose>
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" >${sbby.sbbh }</td>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30" >${sbby.sbname }</td>
				</tr> 
				<c:if test="${sbby.m2 == '1'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">附件：</label></td>
						<td class="width-80" colspan="3">
							<c:if test="${not empty sbby.m3}">
								<c:set var="url" value="${fn:split(sbby.m3, '||')}" />
								<div  style="margin-bottom: 10px;">
								<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
								</div>
							</c:if>
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</form>
</body>
</html>