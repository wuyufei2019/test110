<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐区、库区监测维护数据信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-25 active"><label class="pull-right">所属储罐区：</label></td>
					<td class="width-25" colspan="3">${cglist.cgqname }</td>
				</tr>

				<tr>
					<td class="width-25 active"><label class="pull-right">气体传感器设备编号：</label></td>
					<td class="width-25" colspan="3">${cglist.qtcgqbh }</td>
				</tr>

				<tr >
					<td class="width-25 active"><label class="pull-right">气体点位号：</label></td>
					<td class="width-25">${cglist.qtdwh }</td>

					<td class="width-25 active"><label class="pull-right">气体传感器位置：</label></td>
					<td class="width-25">${cglist.qtcgqwz }</td>
				</tr>

				<tr>
					<td class="width-25 active"><label class="pull-right">气体名称：</label></td>
					<td class="width-25">${cglist.qtmc }</td>

					<td class="width-15 active"><label class="pull-right">气体类别：</label></td>
					<td class="width-25" colspan="3">
						<c:if test="${cglist.qttype eq '0'}">有毒气体</c:if>
						<c:if test="${cglist.qttype eq '1'}">可燃气体</c:if>
					</td>
				</tr>

				<tr>
					<td class="width-25 active"><label class="pull-right">气体类别第一级<br>报警阈值(%LEL)：</label></td>
					<td class="width-25">${cglist.qtbjyz1 }</td>

					<td class="width-25 active"><label class="pull-right">气体浓度第二级<br>报警阈值(%LEL)：</label></td>
					<td class="width-25">${cglist.qtbjyz2 }</td>
				</tr>
				</tbody>
			</table>
	 </form>
<script type="text/javascript">
	
</script>
</body>
</html>