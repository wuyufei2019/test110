<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>高危工艺实时监测详细信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
			<tr>
				<td class="width-20 active"><label class="pull-right">工艺名称：</label></td>
				<td class="width-30">
					${gwgy.danger.m1 }
				</td>
				<td class="width-20 active"><label class="pull-right">工艺编码：</label></td>
				<td class="width-30">${gwgy.danger.processcode }</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">描述：</label></td>
				<td class="width-30" colspan="3">${gwgy.danger.describe }</td>
			</tr>


			<tr>
				<td class="width-20 active"><label class="pull-right">控制方式：</label></td>
				<td class="width-30" colspan="3">${gwgy.danger.ctrlmode }</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">控制参数：</label></td>
				<td class="width-30" colspan="3">${gwgy.danger.ctrlpara }</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">控制措施：</label></td>
				<td class="width-30" colspan="3">${gwgy.danger.ctrlmeasure }</td>
			</tr>

			<tr >
				<td class="width-20 active"><label class="pull-right">是否满足国家规定的控制要求：</label></td>
				<td class="width-30">
					<c:choose>
						<c:when test="${gwgy.danger.isnationdemand=='0'}">
							否
						</c:when>
						<c:otherwise>
							是
						</c:otherwise>
					</c:choose>
				</td>

				<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
				<td class="width-30">${gwgy.danger.equipcode }</td>
			</tr>
			<c:forEach items="${list}" var="ss" varStatus="count">
				<tr>
					<td colspan="4" style="font-size: 20px;text-align: center;color: red;"><strong>${ss.label}</strong></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">${ss.label}阈值上限：</label></td>
					<td class="width-30">
						<c:if test="${ss.threshold_up ne null and ss.threshold_up ne ''}">
							${ss.threshold_up } ${ss.unit }
						</c:if>
					</td>

					<td class="width-20 active"><label class="pull-right">${ss.label}阈值上上限：</label></td>
					<td class="width-30">
						<c:if test="${ss.threshold_upplus ne null and ss.threshold_upplus ne ''}">
							${ss.threshold_upplus } ${ss.unit }
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">${ss.label}阈值下限：</label></td>
					<td class="width-30">
						<c:if test="${ss.threshold_down ne null and ss.threshold_down ne ''}">
							${ss.threshold_down } ${ss.unit }
						</c:if>
					</td>

					<td class="width-20 active"><label class="pull-right">${ss.label}阈值下下限：</label></td>
					<td class="width-30">
						<c:if test="${ss.threshold_downplus ne null and ss.threshold_downplus ne ''}">
							${ss.threshold_downplus } ${ss.unit }
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">报警${ss.label}：</label></td>
					<td class="width-30">
						<c:if test="${ss.bjsj ne null and ss.bjsj ne '' }">
							<span style="color: red;"><strong>${ss.bjxx } ${ss.unit }</strong></span>
						</c:if>
					</td>

					<td class="width-20 active"><label class="pull-right">实时${ss.label}：</label></td>
					<td class="width-30">
						<c:if test="${ss.value ne null and ss.value ne ''}">
							${ss.value } ${ss.unit }
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">${ss.label}采集时间：</label></td>
					<td class="width-80" colspan="3">
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ss.cjsj }" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h2 style="text-align:center">历史波动图</h2>
	<div id="main" style="height:90%;width:100%"></div>

<script type="text/javascript">
	var y = [];//y轴
	var legend = [];//legend值
	var x = [];//x轴
	var gwgyid = '${gwgy.danger.ID}';
	var name = '';
	var dw = '';

	$(function () {
		getDataLoadChart();
	});

	function getDataLoadChart() {
		$.ajax({
			url: ctx + "/zxjkyj/lssj/getgwgylinejson/" + gwgyid,
			type: "POST",
			dataType: "json",
			success: function (data) {
				y = [];//y轴
				legend = [];//legend值
				x = [];//x轴
				x = data.date;
				for (key in data) {
					if (key != "date") {
						y.push({
							"name" : key,
							"type" : "line",
							"data" : data[key]
						});
						legend.push(key);
					}
				}
				loadEcharts();
			}
		});
	}

	// 路径配置
	require.config({
		paths: {
			echarts: '${ctxStatic}/echarts'
		}
	});

	function loadEcharts() {
		require(['echarts', 'echarts/chart/bar', 'echarts/chart/line'], function (ec) {
			var myChart = ec.init(document.getElementById('main'));
			myChart.showLoading();
			myChart.hideLoading();
			myChart.setOption({

				tooltip: {
					trigger: 'axis'
				},
				legend: {
					data: legend
				},
				toolbox: {
					show: true,
					feature: {
						magicType: {
							show: true,
							type: ['line', 'bar']
						},
						restore: {
							show: true
						},
						saveAsImage: {
							show: true
						}
					}
				},
				xAxis: [{
					type: 'category',
					boundaryGap: false,
					data: x
				}],
				yAxis: [{
					type: 'value',
					'name': dw,
				}],
				series: y
			});

		});
	}
</script>
</body>
</html>