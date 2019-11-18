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
				<td class="width-30"colspan="3">${cg.M9 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">储罐名称：</label></td>
				<td class="width-30"colspan="3">${cg.M1 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">存储物料名称：</label></td>
				<td class="width-30"colspan="3">${cg.M7 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">危化品类别：</label></td>
				<td class="width-30"colspan="3">${cg.id3 }</td>

			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
				<td class="width-30">${cg.M10 }</td>
                
				<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
				<td class="width-30">${cg.M11 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">储罐类型：</label></td>
				<td class="width-30">
					<c:if test="${cg.M2 eq '1'}">立式圆筒形储罐</c:if>
					<c:if test="${cg.M2 eq '2'}">卧式圆筒形储罐</c:if>
					<c:if test="${cg.M2 eq '3'}">球形储罐</c:if>
					<c:if test="${cg.M2 eq '4'}">其他储罐</c:if>
				</td>
                
				<td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
				<td class="width-30">${cg.M3 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">材质：</label></td>
				<td class="width-30">
					<c:if test="${cg.M4 eq '1'}">滚塑</c:if>
					<c:if test="${cg.M4 eq '2'}">玻璃钢</c:if>
					<c:if test="${cg.M4 eq '3'}">碳钢</c:if>
					<c:if test="${cg.M4 eq '4'}">陶瓷</c:if>
					<c:if test="${cg.M4 eq '5'}">橡胶</c:if>
					<c:if test="${cg.M4 eq '6'}">其他</c:if>
					<c:if test="${cg.M4 eq '7'}">不锈钢</c:if>
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
					<c:if test="${cg.M6 eq '1'}">甲类</c:if>
					<c:if test="${cg.M6 eq '2'}">乙类</c:if>
					<c:if test="${cg.M6 eq '3'}">丙类</c:if>
					<c:if test="${cg.M6 eq '4'}">丁类</c:if>
					<c:if test="${cg.M6 eq '5'}">戊类</c:if>
				</td>

				<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
				<td class="width-30">${cg.M8 }</td>
			</tr>
			<tr>
				<td colspan="4" style="font-size: 20px;text-align: center;color: red;"><strong>${cg.label}</strong></td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">${cg.label}阈值上限：</label></td>
				<td class="width-30">
					<c:if test="${cg.threshold_up ne null and cg.threshold_up ne ''}">
						${cg.threshold_up } ${cg.unit }
					</c:if>
				</td>

				<td class="width-20 active"><label class="pull-right">${cg.label}阈值上上限：</label></td>
				<td class="width-30">
					<c:if test="${cg.threshold_upplus ne null and cg.threshold_upplus ne ''}">
						${cg.threshold_upplus } ${cg.unit }
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">${cg.label}阈值下限：</label></td>
				<td class="width-30">
					<c:if test="${cg.threshold_down ne null and cg.threshold_down ne ''}">
						${cg.threshold_down } ${cg.unit }
					</c:if>
				</td>

				<td class="width-20 active"><label class="pull-right">${cg.label}阈值下下限：</label></td>
				<td class="width-30">
					<c:if test="${cg.threshold_downplus ne null and cg.threshold_downplus ne ''}">
						${cg.threshold_downplus } ${cg.unit }
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">报警${cg.label}：</label></td>
				<td class="width-30">
					<c:if test="${cg.bjsj ne null and cg.bjsj ne '' }">
						<span style="color: red;"><strong>${cg.bjxx } ${cg.unit }</strong></span>
					</c:if>
				</td>

				<td class="width-20 active"><label class="pull-right">实时${cg.label}：</label></td>
				<td class="width-30">
					<c:if test="${cg.value ne null and cg.value ne ''}">
						${cg.value } ${cg.unit }
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">${cg.label}采集时间：</label></td>
				<td class="width-80" colspan="3">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${cg.cjsj }" />
				</td>
			</tr>
		</tbody>
	</table>

	<h2 style="text-align:center">历史波动图</h2>
	<div id="main" style="height:90%;width:100%"></div>

<script type="text/javascript">
	var y = [];//y轴
	var legend = [];//legend值
	var x = [];//x轴
	var pointid = '${pointid}';
	var jctype = '${jctype}';
	var name = '';
	var yz1 = '';// 阈值上限
	var yz2 = '';// 阈值上上限
	var yz3 = '';// 阈值下限
	var yz4 = '';// 阈值下下限
	var maxval = '';
	var markLineData = [];// 水平线数据
	var dw = '${cg.unit}';

	$(function () {
		getDataLoadChart();
	});

	function getDataLoadChart() {
		$.ajax({
			url: ctx + "/zxjkyj/lssj/getlinejson/" + pointid + "/" + jctype,
			type: "POST",
			dataType: "json",
			success: function (data) {
				y = [];//y轴
				legend = [];//legend值
				x = [];//x轴
				x = data.date;
				markLineData = [];
				name = (data.name + ' (' + dw + ')');
				y = data.label;
				yz1 = data.thresholdUp;
				yz2 = data.thresholdUpplus;
				yz3 = data.thresholdDown;
				yz4 = data.thresholdDownplus;

				if (y != undefined) {
					if (yz1 != null && yz1 != '') {
						markLineData.push([{
							name: '阈值上限',
							value: yz1,
							xAxis: -1,
							yAxis: yz1,
							itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值上限'}}}
						}, {
							xAxis: y.length,
							yAxis: yz1,
							itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值上限'}}}
						}]);
					}
					if (yz2 != null && yz2 != '') {
						markLineData.push([{
							name: '阈值上上限',
							value: yz2,
							xAxis: -1,
							yAxis: yz2,
							itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值上上限'}}}
						}, {
							xAxis: y.length,
							yAxis: yz2,
							itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值上上限'}}}
						}]);
					}
					if (yz3 != null && yz3 != '') {
						markLineData.push([{
							name: '阈值下限',
							value: yz3,
							xAxis: -1,
							yAxis: yz3,
							itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值下限'}}}
						}, {
							xAxis: y.length,
							yAxis: yz3,
							itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值下限'}}}
						}]);
					}
					if (yz4 != null && yz4 != '') {
						markLineData.push([{
							name: '阈值下下限',
							value: yz4,
							xAxis: -1,
							yAxis: yz4,
							itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值下下限'}}}
						}, {
							xAxis: y.length,
							yAxis: yz4,
							itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值下下限'}}}
						}]);
					}
				}
				legend.push(name);
				if (yz2 != null && yz2 != '') {
					maxval = yz2 + 2;
				} else if (yz1 != null && yz1 != '') {
					maxval = yz1 + 2;
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
					max: maxval
				}],
				series: [{
					"name": name,
					"type": "line",
					"data": y,
					markLine: {
						data: markLineData,
					},
					itemStyle: {
						normal: {lineStyle: {type: 'solid', color: '#2e8ded'}}
					}
				}]
			});

		});
	}
</script>
</body>
</html>