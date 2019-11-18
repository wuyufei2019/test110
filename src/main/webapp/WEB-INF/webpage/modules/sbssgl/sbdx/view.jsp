<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备大修项</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			<c:if test="${type eq '2'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-80" colspan="3" style="height: 30px;">${sbdx.qyname }</td>
				</tr> 
			</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">使用单位：</label></td>
					<td class="width-80" colspan="3" style="height: 30px;">${sbdx.deptname}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">工作令号：</label></td>
					<td class="width-30" style="height: 30px;">${sbdx.m1 }</td>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" style="height: 30px;">${sbdx.m2 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">型号名称：</label></td>
					<td class="width-30" style="height: 30px;">${sbdx.m3 }</td>
				   	<td class="width-20 active"><label class="pull-right">启用年月：</label></td>
					<td class="width-30" style="height: 30px;">${sbdx.m4}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">计划修理费（万元）：</label></td>
					<td class="width-30" style="height: 30px;">${sbdx.m6}</td>
					<td class="width-20 active"><label class="pull-right">计划修理时间：</label></td>
					<td class="width-30" style="height: 30px;">${sbdx.m18}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">修理类别：</label></td>
					<td class="width-30" style="height: 30px;">
						<c:if test="${sbdx.m8 eq '0'}">大修</c:if>
						<c:if test="${sbdx.m8 eq '1'}">项修</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备现状描述：</label></td>
					<td class="width-80" colspan="3" style="height: 50px;">${sbdx.m16}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检维修方案：</label></td>
					<td class="width-80" colspan="3" style="height: 50px;">${sbdx.m15}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">技术验收质量要求：</label></td>
					<td class="width-30" colspan="3" style="height: 50px;">${sbdx.m20}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检维修负责人：</label></td>
					<td class="width-30" style="height: 30px;">${sbdx.m21}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">是否完成：</label></td>
					<td class="width-30" style="height: 30px;">
						<c:if test="${sbdx.m19 eq '0'}">未完成</c:if>
						<c:if test="${sbdx.m19 eq '1'}">已完成</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">定人：</label></td>
					<td class="width-30">${sbdx.m9}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">定期：</label></td>
					<td class="width-30">${sbdx.m10}</td>
					<td class="width-20 active"><label class="pull-right">定点：</label></td>
					<td class="width-30">${sbdx.m11}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">定质：</label></td>
					<td class="width-30">${sbdx.m12}</td>
					<td class="width-20 active"><label class="pull-right">定量：</label></td>
					<td class="width-30">${sbdx.m13}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">验收报告：</label></td>
					<td class="width-80" colspan="3" style="height: 30px;">
						<c:if test="${not empty sbdx.m14}">
							<c:set var="url" value="${fn:split(sbdx.m14, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">维修合同：</label></td>
					<td class="width-80" colspan="3" style="height: 30px;">
						<c:if test="${not empty sbdx.m22}">
							<c:set var="url" value="${fn:split(sbdx.m22, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>