<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备验收单</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">调出单位：</label></td>
					<td class="width-30" >${sbjf.m1 }</td>
					<td class="width-20 active"><label class="pull-right">调入单位：</label></td>
					<td class="width-30" >${sbjf.m2 }</td>
				</tr> 
				<tr >
					<td class="width-20 active"><label class="pull-right">事由：</label></td>
					<td class="width-80" colspan="3">${sbjf.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" >${sbjf.m4 }</td>
					<td class="width-20 active"><label class="pull-right">名称型号：</label></td>
					<td class="width-30" >${sbjf.m5 }</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-30" >${sbjf.m6 }</td>
					<td class="width-20 active"><label class="pull-right">账面原值（元）：</label></td>
					<td class="width-30" >${sbjf.m7 }</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">现值（元）：</label></td>
					<td class="width-30" >${sbjf.m8 }</td>
					<td class="width-20 active"><label class="pull-right">残值（元）：</label></td>
					<td class="width-30" >${sbjf.m9 }</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">折旧年限（年）：</label></td>
					<td class="width-30" >${sbjf.m10 }</td>
					<td class="width-20 active"><label class="pull-right">已提折旧（年）：</label></td>
					<td class="width-30" >${sbjf.m11 }</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">年折旧额（元/年）：</label></td>
					<td class="width-30" >${sbjf.m12 }</td>
				</tr> 
				<tr >
					<td class="width-20 active"><label class="pull-right">折旧方法：</label></td>
					<td class="width-80" colspan="3">${sbjf.m13 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">完好程度：</label></td>
					<td class="width-30" >${sbjf.m14 }</td>
					<td class="width-20 active"><label class="pull-right">启用、封存、调拨日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbjf.m15 }" /></td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">封存开始日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbjf.m16 }" /></td>
					<td class="width-20 active"><label class="pull-right">封存结束日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbjf.m17 }" /></td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">记事：</label></td>
					<td class="width-80" colspan="3">${sbjf.m18 }</td>
				</tr>
				<c:if test="${sbjf.m20 != '0'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">附件：</label></td>
						<td class="width-80" colspan="3">
							<c:if test="${not empty sbjf.m19}">
								<c:set var="url" value="${fn:split(sbjf.m19, '||')}" />
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