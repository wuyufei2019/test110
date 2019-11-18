<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车间管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">设备型号：</label></td>
					<td class="width-30">
                        <c:if test="${entity.m1 == '1'}">立式</c:if>
                        <c:if test="${entity.m1 == '2'}">卧式</c:if>
                        <c:if test="${entity.m1 == '3'}">手动</c:if>
                    </td>
                    <td class="width-20 active"><label class="pull-right">台数：</label></td>
                    <td class="width-30">${entity.m2 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业区域人数：</label></td>
					<td class="width-30">${entity.m3 }</td>
                    <td class="width-20 active"><label class="pull-right">集尘器位置：</label></td>
                    <td class="width-30">${entity.m4 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">产品材质：</label></td>
					<td class="width-30">${entity.m5 }</td>
					<td class="width-20 active"><label class="pull-right">砂丸材质：</label></td>
					<td class="width-30">${entity.m6 }</td>
				</tr>
			    <tr>
					<td class="width-20 active"><label class="pull-right">清理制度：</label></td>
					<td class="width-30">
                        <c:if test="${entity.m7 == '0'}">有</c:if>
                        <c:if test="${entity.m7 == '1'}">无</c:if>
                    </td>
					<td class="width-20 active"><label class="pull-right">清理记录：</label></td>
					<td class="width-30">
                        <c:if test="${entity.m8 == '0'}">有</c:if>
                        <c:if test="${entity.m8 == '1'}">无</c:if>
                    </td>
				</tr>

				</tbody>
			</table>
	</form>
</body>
</html>