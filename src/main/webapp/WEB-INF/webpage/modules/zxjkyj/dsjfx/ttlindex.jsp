<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>统计分析</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/tjfx/view.js?v=1.1"></script> --%>
</head>
<body>

     <h2 style="text-align:center">吞吐量大数据分析图</h2>
     <div style="margin:20px 0 10px 0;"></div>
     <div id="searchFrom" style="text-align: center"><c:if test="${empty usertype}">
               <input type="text" id="check_company_name" name="check_company_name"
                    class="easyui-combobox" style="height: 30px;"
                    data-options="editable:true ,valueField: 'm1',textField: 'm1',url:'${ctx}/zxjkyj/cgssjc/qyjson',prompt: '企业名称'" />
          </c:if> <input type="text" name="starttime" id="starttime" class="easyui-datebox"
               style="height: 30px;"
               data-options="panelHeight:'auto' ,editable:false , prompt: '开始日期' " /> <input
               type="text" id="endtime" name="endtime" class="easyui-datebox" style="height: 30px;"
               data-options="panelHeight:'auto' ,editable:false , prompt: '结束日期' " />
          <button class="btn btn-info btn-sm" onclick="search()">
               <i class="fa fa-search"></i> 查询
          </button>
          <button class="btn btn-danger btn-sm" onclick="reset()">
               <i class="fa fa-refresh"></i> 全部
          </button></div>

     <div id="tt" class="easyui-tabs" style="width:100%;height:43%;">
          <div title="按物料类别统计" style="display:none;">
               <div class="ibox-content" style="height:95%;width:100%;padding:1px;margin-top:15px;margin:0 auto">
                    <div id="maint" style="height:100%;width:100%"></div>
          </div>

     </div>
          <div title="按物料名称统计" style="overflow:auto;display:none;">
               <div class="ibox-content" style="height:95%;width:100%;padding:1px;margin-top:15px;margin:0 auto">
                    <div id="mainn" style="height:100%;width:100%"></div>
          </div>
     </div>
     </div>
     <div id="tt2" class="easyui-tabs" style="width:100%;height:43%;">
          <div title="每日吞吐量分析" style="display:none;">
               <div class="ibox-content" style="height:100%;width:100%;padding:1px">
                    <div id="maind" style="height:100%;width:100%"></div>
          </div>
     </div>
          <div title="每月吞吐量分析">
               <div class="ibox-content" style="height:100%;width:100%;padding:1px">
                    <div id="mainm" style="height:100%;width:100%"></div>
          </div>
     </div> <script type="text/javascript">
						var param;//传参
						var param2;//传参
						var y1 = [];//y轴
						var x1 = [];//增加量
						var y2 = [];//y轴
						var x2 = [];//增加量
						var z = [];//减少
						var m;//差量 1图
						var date = [];//日期
						$(function() {
							initParam();
							getDataLoadChart(param);
							initParam2();
							getDataLoadChart2(param2);
						 	$('#tt').tabs({
								onSelect : function(title) {
									initParam();
									getDataLoadChart(param);
								}
							});
							$('#tt2').tabs({
								onSelect : function(title) {
									initParam2();
									getDataLoadChart2(param2);
								}
							}); 
						});
						function getDataLoadChart(param) {
							$.ajax({
								url : ctx + "/zxjkyj/dsjfx/getTtljson",
								type : "POST",
								data : param,
								dataType : "json",
								success : function(data) {
									x1 = [];
									y1 = [];
									z = [];
									m = [];
									for (var i = 0; i < data.length; i++) {
										y1.push(data[i].label);
										x1.push(data[i].count1.toFixed(2));//增加量
										z.push(data[i].count2.toFixed(2));//减少
										m.push((data[i].count1 + data[i].count2).toFixed(2));//差量
									}
									if ($('#tt').tabs('getSelected').panel('options').tab[0].textContent == "按物料类别统计")
									loadEcharts("type");
									else
									loadEcharts("name");
								}
							});
						}
						function getDataLoadChart2(param2) {
							$.ajax({
								url : ctx + "/zxjkyj/dsjfx/getTtlbytime",
								type : "POST",
								data : param2,
								dataType : "json",
								success : function(data) {
									date = data.date;
									x2 = data.xdata;
									y2 = data.ydata;
									if ($('#tt2').tabs('getSelected').panel('options').tab[0].textContent == "每日吞吐量分析")
									loadEcharts2("day");
									else
									loadEcharts2("month");
								}
							});
						}
						function initParam() {
							param = {
								"starttime" : $("#starttime").datebox('getValue'),
								"endtime" : $("#endtime").datebox('getValue')
							};
							if ('${usertype}' != '1') {
								param.qyid = $("#check_company_name").combobox('getText');
							}
							if ($('#tt').tabs('getSelected').panel('options').tab[0].textContent == "按物料类别统计")
								param.type = "type";
							else
								param.type = "name";

						}
						function initParam2() {
							param2 = {
								"starttime" : $("#starttime").datebox('getValue'),
								"endtime" : $("#endtime").datebox('getValue')
							};
							if ('${usertype}' != '1') {
								param2.qyid = $("#check_company_name").combobox('getText');
							}
							if ($('#tt2').tabs('getSelected').panel('options').tab[0].textContent == "每日吞吐量分析")
								param2.type = "day";
							else
								param2.type = "month";
						}

						function search() {
							var d1 = $("#starttime").datebox('getValue');
							var d2 = $("#endtime").datebox('getValue');
							if ((d2 == "" && d1 == "") || (d1 != "" && d2 != "")) {
								var title = $('#tt2').tabs('getSelected').panel('options').tab[0].textContent;
								initParam();
								getDataLoadChart(param);
								initParam2();
								getDataLoadChart2(param2);
							} else {
								layer.msg("请选择日期！", {
									time : 1000
								});
								return;
							}

						}
						function reset() {
							$("#searchFrom").form("clear");
							param.qyid="";
							param.starttime="";
							param.endtime="";
							getDataLoadChart(param);
							param2.qyid="";
							param2.starttime="";
							param2.endtime="";
							getDataLoadChart2(param2);
						}
						// 路径配置
						require.config({
							paths : {
								echarts : '${ctxStatic}/echarts'
							}
						});
					
						function loadEcharts(type) {
							require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
								var myChart;
								if (type == 'name')
									myChart = ec.init(document.getElementById('mainn'));
								else
									myChart = ec.init(document.getElementById('maint'));
								myChart.showLoading();
								myChart.setOption({
									tooltip : {
										trigger : 'axis',
										axisPointer : { // 坐标轴指示器，坐标轴触发有效
											type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
										}
									},
									toolbox : {
										show : true,
										feature : {
											magicType : {
												show : true,
												type : [ 'line', 'bar' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									legend : {
										data : [ '增加量', '减少量', ]
									},
									calculable : true,
									yAxis : [ {
										type : 'value',
										'name':'m³',
									} ],
									/* grid : {
										x : '15%',
										y : '10%',
									}, */
									xAxis : [ {
										type : 'category',
										axisTick : {
											show : false
										},
										axisLabel : {
											formatter : function(b) {
												if (b.length > 20)
													return b.substring(0, 16) + "..." + b.substring(b.length - 2, b.length);
												else
													return b;
											}
										},
										data : y1,
									/*  axisLabel : {
									 	rotate:
									}, */
									} ],
									series : [ {
										name : '增加量',
										type : 'bar',
										stack : '总量',
										barCategoryGap : '70%',
										barWidth : 50,//柱图宽度
										itemStyle : {
											normal : {
												label : {
													show : true
												}
											}
										},
										data : x1
									}, {
										name : '减少量',
										type : 'bar',
										stack : '总量',
										barCategoryGap : '70%',
										barWidth : 40,//柱图宽度
										itemStyle : {
											normal : {
												label : {
													show : true,
													position : 'left'
												}
											}
										},
										data : z
									} ]
								});
								myChart.hideLoading();

							});
						}
						function loadEcharts2(type) {
							require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
								var myChart;
								if (type == 'day')
									myChart = ec.init(document.getElementById('maind'));
								else
									myChart = ec.init(document.getElementById('mainm'));
								//myChart.showLoading();

								//	myChart.hideLoading();
								myChart.setOption({
									tooltip : {
										trigger : 'axis',
										formatter : function(params) {
											return params[0].name + '<br/>' + params[0].seriesName + ' : ' + params[0].value + ' <br/>' + params[1].seriesName + ' : ' + -params[1].value;
										}
									},
									legend : {
										data : [ '增加量', '减少量' ],
										x : 'center'
									},
									toolbox : {
										show : true,
										feature : {
											magicType : {
												show : true,
												type : [ 'line', 'bar' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									dataZoom : {
										show : true,
										realtime : true,
										start : 0,
										end : 100
									},
									xAxis : [ {
										type : 'category',

										data : date
									} ],
									yAxis : [ {
										name : '增加量',
										type : 'value',
										'name':'m³',

									}, {
										name : '减少量',
										type : 'value',
										'name':'m³',

									} ],
									series : [ {
										name : '增加量',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : x2
									}, {
										name : '减少量',
										type : 'line',
										yAxisIndex : 0,
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : (function() {
											var oriData = y2;
											if (oriData != null) {
												var len = oriData.length;
												while (len--) {
													oriData[len] *= -1;
												}
											}
											return oriData;
										})()
									} ]
								});
							});

						}
					</script>
</html>