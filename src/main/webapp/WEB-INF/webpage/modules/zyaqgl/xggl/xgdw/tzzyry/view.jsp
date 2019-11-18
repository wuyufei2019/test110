<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>特种作业人员管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35" >${tz.m1 }</td>
										<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35" >
						<c:if test="${tz.m2 eq '0'}">
							男
						</c:if>
						<c:if test="${tz.m2 eq '1'}">
							女
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">类别：</label></td>
					<td class="width-35" >${tz.m8 }</td>				
					<td class="width-15 active"><label class="pull-right">操作证号：</label></td>
					<td class="width-35">${tz.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35">${tz.m4 }</td>
					<td class="width-15 active"><label class="pull-right">作业类型：</label></td>
					<td class="width-35">${tz.m5 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">到期日期：</label></td>
					<td class="width-35"><fmt:formatDate value="${tz.m6 }" pattern="yyyy-MM-dd " /></td>				
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" height="80" >
					${tz.m7 }
					</td>
				</tr>
				</tbody>
			</table>

       </form>
 
</body>
</html>