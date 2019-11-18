<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>高危工艺信息查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">位号：</label></td>
					<td class="width-30" colspan="3">
						${gwgylist.m5 }
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">高危工艺名称：</label></td>
					<td class="width-30" colspan="3">
						${gwgylist.m1 }
					</td>
				</tr>
		
	            <tr>
					<td class="width-20 active"><label class="pull-right">物料名称：</label></td>
					<td class="width-30">${gwgylist.m2 }</td>
					<td class="width-20 active"><label class="pull-right">类型：</label></td>
					<td class="width-30">${a}</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
					<td class="width-30">${gwgylist.m6 }</td>
					<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
					<td class="width-30">${gwgylist.m7 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
					<td class="width-30" colspan="3">${gwgylist.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">高液位预警值1（m）：</label></td>
					<td class="width-30" >${gwgylist.level1 }</td>
					<td class="width-20 active"><label class="pull-right">高液位预警值2（m）：</label></td>
					<td class="width-30" >
							${gwgylist.level2 }
						</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">釜内高温度预警1（℃）：</label></td>
					<td class="width-30" >${gwgylist.temperature1 }</td>
					<td class="width-20 active"><label class="pull-right">釜内高温度预警2（℃）：</label></td>
					<td class="width-30" >
							${gwgylist.temperature2 }
						</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">夹套高温度预警1（℃）：</label></td>
					<td class="width-30" >${gwgylist.temperature3 }</td>
					<td class="width-20 active"><label class="pull-right">夹套高温度预警2（℃）：</label></td>
					<td class="width-30" >
							${gwgylist.temperature4 }
						</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">高压力预警值1（MPa）：</label></td>
					<td class="width-30" >${gwgylist.pressure1 }</td>
					<td class="width-20 active"><label class="pull-right">高压力预警值2（MPa）：</label></td>
					<td class="width-30" >
							${gwgylist.pressure2 }
						</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">高流量预警1（m³/h）：</label></td>
					<td class="width-30" >${gwgylist.flux1 }</td>
					<td class="width-20 active"><label class="pull-right">高流量预警2（m³/h）：</label></td>
					<td class="width-30" >
							${gwgylist.flux2 }
						</td>
				</tr>
				<c:if test="${usertype eq '9'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">液位点号 ：</label></td>
					<td class="width-30" >${gwgylist.r1 }</td>
					<td class="width-20 active"><label class="pull-right">温度点号 ：</label></td>
					<td class="width-30" >
							${gwgylist.r2 }
						</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">压力点号 ：</label></td>
					<td class="width-30" >${gwgylist.r3 }</td>
					<td class="width-20 active"><label class="pull-right">流量点号 ：</label></td>
					<td class="width-30" >
							${gwgylist.r4 }
						</td>
				</tr>
				</c:if>
				</tbody>
			</table>
	 </form>
<script type="text/javascript">
	
</script>
</body>
</html>