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
					<td class="width-15 active" ><label class="pull-right">发生单位：</label></td>
					<td class="width-30">${aie.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发生日期：</label></td>
					<td class="width-35"><fmt:formatDate value="${aie.m1 }"/></td>
					<td class="width-15 active"><label class="pull-right">发生地点：</label></td>
					<td class="width-35" >${aie.m3 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">事故类型：</label></td>
					<td class="width-35">${aie.m4}</td>
					
					<td class="width-15 active"><label class="pull-right">事故等级：</label></td>
					<td class="width-35">${aie.m5 }</td>
				</tr>
				<tr>
				<td class="width-15 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-15" >${aie.m6 }</td>
					<td class="width-15 active"><label class="pull-right">重伤人数：</label></td>
					<td class="width-35" >${aie.m7 }</td>
				</tr>
				<tr><td class="width-15 active"><label class="pull-right">轻伤人数：</label></td>
					<td class="width-85" >${aie.m8 }</td></tr>
				<tr>
				<td class="width-15 active"><label class="pull-right">直接经济损失（万元）：</label></td>
					<td class="width-35" >${aie.m9 }</td>
					<td class="width-15 active"><label class="pull-right">间接经济损失（万元）：</label></td>
					<td class="width-35" >${aie.m10 }</td>
				</tr>

                <tr>
					<td class="width-15 active"><label class="pull-right">事故描述：</label></td>
					<td class="width-85" colspan="3">
					${aie.m11 }
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">事故处理：</label></td>
					<td class="width-85" colspan="3">
					${aie.m12 }
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">事故预防对策：</label></td>
					<td class="width-85" colspan="3">
					${aie.m13 }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					${aie.m14 }
					</td>
				</tr>
				</tbody>
			</table>
	</form>
</body>
</html>