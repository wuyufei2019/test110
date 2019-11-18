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
					<td class="width-80" colspan="3" style="height: 30px;">${sbgx.qyname }</td>
				</tr> 
			</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">工作令号：</label></td>
					<td class="width-30" style="height: 30px;">${sbgx.m1 }</td>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30" style="height: 30px;">${sbgx.m2 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">规格型号：</label></td>
					<td class="width-30" style="height: 30px;">${sbgx.m3 }</td>
				   	<td class="width-20 active"><label class="pull-right">使用单位：</label></td>
					<td class="width-30" style="height: 30px;">${sbgx.deptname}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">单价（万元）：</label></td>
					<td class="width-30" style="height: 30px;">${sbgx.m5}</td>
					<td class="width-20 active"><label class="pull-right">数量（台）：</label></td>
					<td class="width-30" style="height: 30px;">${sbgx.m6}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">合计（万元）：</label></td>
					<td class="width-30" style="height: 30px;">${sbgx.m7}</td>
					<td class="width-20 active"><label class="pull-right">设备更新类别：</label></td>
					<td class="width-30" style="height: 30px;">
						<c:if test="${sbgx.m8 eq '0'}">替换</c:if>
						<c:if test="${sbgx.m8 eq '1'}">新增</c:if>
					</td>
				</tr>
			<c:if test="${sbgx.m8 eq '0'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">设备替换原因描述：</label></td>
					<td class="width-80" colspan="3" style="height: 50px;">${sbgx.m9}</td>
				</tr>
			</c:if>
			<c:if test="${sbgx.m8 eq '1'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">设备新增原因描述：</label></td>
					<td class="width-80" colspan="3" style="height: 50px;">${sbgx.m10}</td>
				</tr>
			</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备更新后作用描述：</label></td>
					<td class="width-30" colspan="3" style="height: 50px;">${sbgx.m11}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">是否完成：</label></td>
					<td class="width-30" style="height: 30px;">
						<c:if test="${sbgx.m12 eq '0'}">未完成</c:if>
						<c:if test="${sbgx.m12 eq '1'}">已完成</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">年度：</label></td>
					<td class="width-30">${sbgx.m14}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">验收报告：</label></td>
					<td class="width-80" colspan="3" style="height: 30px;">
						<c:if test="${not empty sbgx.m13}">
							<c:set var="url" value="${fn:split(sbgx.m13, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">合同附件：</label></td>
					<td class="width-80" colspan="3" style="height: 30px;">
						<c:if test="${not empty sbgx.m15}">
							<c:set var="url" value="${fn:split(sbgx.m15, '||')}" />
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