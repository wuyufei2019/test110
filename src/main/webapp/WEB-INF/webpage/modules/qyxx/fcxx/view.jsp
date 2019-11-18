<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>粉尘信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">粉尘种类：</label></td>
					<td class="width-35" colspan="3">${fcxx.m1 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">涉粉作业人数：</label></td>
					<td class="width-35">${fcxx.m2 }</td>
					<td class="width-15 active"><label class="pull-right">涉粉单班作业人数：</label></td>
					<td class="width-35">${fcxx.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">是否有除尘器：</label></td>
					<td class="width-35" >
						<c:choose>
						<c:when test="${fcxx.m6=='1'}">
							是
						</c:when>
						<c:otherwise>
							否
						</c:otherwise>
						</c:choose>
					</td>
					<td class="width-15 active"><label class="pull-right">除尘器种类：</label></td>
					<td class="width-35" >${fcxx.m7 }</td>					
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">是否涉爆：</label></td>
					<td class="width-35" >
						<c:choose>
						<c:when test="${fcxx.m13=='1'}">
							是
						</c:when>
						<c:otherwise>
							否
						</c:otherwise>
						</c:choose>
					</td>
					<td class="width-15 active"><label class="pull-right">是否职业病危害因素：</label></td>
					<td class="width-35" >
						<c:choose>
						<c:when test="${fcxx.m14=='1'}">
							是
						</c:when>
						<c:otherwise>
							否
						</c:otherwise>
						</c:choose>
					</td>					
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">是否建立粉尘清扫制度：</label></td>
					<td class="width-35" >
						<c:choose>
						<c:when test="${fcxx.m8=='1'}">
							是
						</c:when>
						<c:otherwise>
							否
						</c:otherwise>
						</c:choose>
					</td>
					<td class="width-15 active"><label class="pull-right">是否安装监控：</label></td>
					<td class="width-35" >
						<c:choose>
						<c:when test="${fcxx.m9=='1'}">
							是
						</c:when>
						<c:otherwise>
							否
						</c:otherwise>
						</c:choose>
					</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${fcxx.m10 }
					</td>					
				</tr>
				</tbody>
			</table>

	</form>
</body>
</html>