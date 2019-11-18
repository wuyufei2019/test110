<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>不安全行为管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">不安全行为类型1：</label></td>
					<td class="width-80" >${unsafe.m1}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">行为描述：</label></td>
					<td class="width-80" >${unsafe.m2}</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" style="height:80px">${unsafe.m3}</td>					
				</tr>
				
				<c:if test="${not empty unsafe.ID}">
					<input type="hidden" name="ID" value="${unsafe.ID}" />
					<input type="hidden" name="ID1" value="${unsafe.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${unsafe.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${unsafe.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
</body>
</html>