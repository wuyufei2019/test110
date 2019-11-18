<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>备品备件</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<c:choose>
		<c:when test="${infosize > 0 }">
			<c:forEach items="${bpbjlist }" var="bpbj">
				<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" id="data_table">
					<tbody>
						<tr>
							<td class="width-20 active"><label class="pull-right">备品备件名称：</label></td>
							<td class="width-30" style="height: 30px;">${bpbj.m2 }</td>
						   	<td class="width-20 active"><label class="pull-right">规格型号：</label></td>
							<td class="width-30" style="height: 30px;">${bpbj.m3 }</td>
						</tr>
						<tr>
						   	<td class="width-20 active"><label class="pull-right">数量：</label></td>
							<td class="width-30" style="height: 30px;">${bpbj.m6 }</td>
						   	<td class="width-20 active"><label class="pull-right">单位：</label></td>
							<td class="width-30" style="height: 30px;">${bpbj.m4 }</td>
						</tr>
						<tr>
						   	<td class="width-20 active"><label class="pull-right">最低安全库存：</label></td>
							<td class="width-30" style="height: 30px;">${bpbj.m1 }</td>
							<td class="width-20 active"><label class="pull-right">制造商：</label></td>
							<td class="width-30" style="height: 30px;">${bpbj.m7 }</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>	
		</c:when>
		<c:otherwise>
		
		</c:otherwise>
	</c:choose>
</body>
</html>