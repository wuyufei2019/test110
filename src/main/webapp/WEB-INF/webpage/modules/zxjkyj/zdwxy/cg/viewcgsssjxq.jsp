<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐实时监测详细信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
			<tr>
				<td class="width-20 active"><label class="pull-right">位号：</label></td>
				<td class="width-30"colspan="3">${cg.m9 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">储罐名称：</label></td>
				<td class="width-30"colspan="3">${cg.m1 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">存储物料名称：</label></td>
				<td class="width-30"colspan="3">${cg.m7 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">危化品类别：</label></td>
				<td class="width-30"colspan="3">${cg.ID3 }</td>

			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
				<td class="width-30">${cg.m10 }</td>
                
				<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
				<td class="width-30">${cg.m11 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">储罐类型：</label></td>
				<td class="width-30">
					<c:if test="${cg.m2 eq '1'}">立式圆筒形储罐</c:if>
					<c:if test="${cg.m2 eq '2'}">卧式圆筒形储罐</c:if>
					<c:if test="${cg.m2 eq '3'}">球形储罐</c:if>
					<c:if test="${cg.m2 eq '4'}">其他储罐</c:if>
				</td>
                
				<td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
				<td class="width-30">${cg.m3 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">材质：</label></td>
				<td class="width-30">
					<c:if test="${cg.m4 eq '1'}">滚塑</c:if>
					<c:if test="${cg.m4 eq '2'}">玻璃钢</c:if>
					<c:if test="${cg.m4 eq '3'}">碳钢</c:if>
					<c:if test="${cg.m4 eq '4'}">陶瓷</c:if>
					<c:if test="${cg.m4 eq '5'}">橡胶</c:if>
					<c:if test="${cg.m4 eq '6'}">其他</c:if>
					<c:if test="${cg.m4 eq '7'}">不锈钢</c:if>
				</td>
                
				<td class="width-20 active"><label class="pull-right">储罐区面积（㎥）：</label></td>
				<td class="width-30">${cg.m12 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">有无防火堤：</label></td>
				<td class="width-30">
					<c:if test="${cg.m13 eq '1'}">有</c:if>
					<c:if test="${cg.m13 eq '0'}">无</c:if>
				</td>
                
				<td class="width-20 active"><label class="pull-right">防火堤所围面积（㎥）：</label></td>
				<td class="width-30">${cg.m14 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">火灾危险性等级：</label></td>
				<td class="width-30">
					<c:if test="${cg.m6 eq '1'}">甲类</c:if>
					<c:if test="${cg.m6 eq '2'}">乙类</c:if>
					<c:if test="${cg.m6 eq '3'}">丙类</c:if>
					<c:if test="${cg.m6 eq '4'}">丁类</c:if>
					<c:if test="${cg.m6 eq '5'}">戊类</c:if>
				</td>

				<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
				<td class="width-30">${cg.m8 }</td>
			</tr>
			<c:forEach items="${list}" var="ss">
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
	var tankid = '${cg.ID}';
	var name = '';
	var dw = '';

	$(function () {
		getDataLoadChart();
	});

	function getDataLoadChart() {
		$.ajax({
			url: ctx + "/zxjkyj/lssj/getcglinejson/" + tankid,
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