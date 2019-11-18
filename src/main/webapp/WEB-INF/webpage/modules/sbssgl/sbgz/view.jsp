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
					<td class="width-80" colspan="3" style="height: 30px;">${sbgz.qyname }</td>
				</tr> 
			</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">部门名称：</label></td>
					<td class="width-30" style="height: 30px;">${sbgz.deptname }</td>
					<td class="width-20 active"><label class="pull-right">报修日期：</label></td>
					<td class="width-30" style="height: 30px;"><fmt:formatDate value="${sbgz.m1 }" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">报修单编号：</label></td>
					<td class="width-30" style="height: 30px;">${sbgz.m2 }</td>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30" style="height: 30px;">${sbgz.sbname }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备型号：</label></td>
					<td class="width-30" style="height: 30px;">${sbgz.m3}</td>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" style="height: 30px;">${sbgz.m4}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">故障现象：</label></td>
					<td class="width-80" colspan="3"style="height: 50px;">${sbgz.m4}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">原因分析：</label></td>
					<td class="width-80" colspan="3" style="height: 50px;">${sbgz.m6}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">采取对策：</label></td>
					<td class="width-80" colspan="3" style="height: 50px;">${sbgz.m7}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">维修开始时间：</label></td>
					<td class="width-30" style="height: 30px;"><fmt:formatDate value="${sbgz.m9 }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td class="width-20 active"><label class="pull-right">维修结束时间：</label></td>
					<td class="width-30" style="height: 30px;"><fmt:formatDate value="${sbgz.m10 }" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">维修需求报告：</label></td>
					<td class="width-80" colspan="3" style="height: 30px;">
						<c:if test="${not empty sbgz.m14}">
							<c:set var="url" value="${fn:split(sbgz.m14, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">维修报告：</label></td>
					<td class="width-80" colspan="3" style="height: 30px;">
						<c:if test="${not empty sbgz.m8}">
							<c:set var="url" value="${fn:split(sbgz.m8, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">维修停机时间（H）：</label></td>
					<td class="width-30" style="height: 30px;">${sbgz.m11}</td>
					<td class="width-20 active"><label class="pull-right">更换零部件记录：</label></td>
					<td class="width-30" style="height: 30px;">${sbgz.m12}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3" style="height: 80px;">${sbgz.m13}</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>