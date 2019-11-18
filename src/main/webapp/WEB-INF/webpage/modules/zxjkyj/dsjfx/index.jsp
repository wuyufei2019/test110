<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>统计分析</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body>
     <div class="easyui-panel" style="width:100%;height:100%;padding:10px;">
          <div class="easyui-layout" data-options="fit:true">

               <div data-options="region:'west'" style="width:50%;">
                    <div data-options="region:'north'" style="height:50%;">
                         <div class="ibox-content">
                              <div id="main" style="width:100%;"></div>
                    </div> 
               </div>
                    <div data-options="region:'south'" style="height:50%;">
                         <div class="ibox-content">
                              <div id="main3" style="width:100%;"></div>
                    </div>
               </div>
          </div>
               <div data-options="region:'east',split:true" style="width:50%;">
                    <div class="ibox-content">
                         <div id="main2" style="width:100%;"></div>
               </div>
          </div>
     </div>
     </div>
     </div>

     <script type="text/javascript">
						var datas;
						// 路径配置
						require.config({
							paths : {
								echarts : '${ctxStatic}/echarts'
							}
						});
						initEchartsSize();
						loadEcharts();
						loadEcharts2();
						loadEcharts3();
						function initEchartsSize(){
							var height= $(window).height();
							var width=$(window).height();
							$("#main").height(height/2-90);
							$("#main2").height(height-100);
							$("#main3").height(height/2-90);
							$("#main").width(width);
							$("#main2").width(width);
							$("#main3").width(width);
						}
						$(window).resize(function(){
							initEchartsSize();
							loadEcharts();
							loadEcharts2();
							loadEcharts3();	
						});
						function loadEcharts3() {
							require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
								var myChart = ec.init(document.getElementById('main3'));
								myChart.showLoading();
								$.ajax({
									url : ctx + "/zxjkyj/dsjfx/getpiejson",
									type : "POST",
									dataType : "json",
									success : function(data) {
										var x = [];
										var y = [];
										for (var i = 0; i < data.length; i++) {
											x.push(data[i].name);
											y.push(data[i].value.toFixed(2));
										}
										myChart.hideLoading();
										myChart.setOption({
											
											tooltip : {
												trigger : 'axis'
											},
											/*    legend:{
											   	orient : 'vertical',
												x : 'right',
											   	data:x,
											   	formatter:function(b){
											   		if(b.length>6)
											   			return b.substring(0,3)+"..."+b.substring(b.length-2,b.length);
											   		else
											   			return b;
											   	}
											   }, */
											calculable : true,
											xAxis : [ {
												  type: 'category',
												  data:x
											} ],
											yAxis : [ {
												type: 'value',
												data : y,
												'name':'m³',

											} ],
											series : [ {

												type : 'bar',
												data : y,
												itemStyle : {
													normal : {
														color : function(params) {
															// build a color map as your need.
															var colorList = [ '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B', '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD', '#D7504B',
																	'#C6E579', '#F4E001', '#F0805A', '#26C0C0' ];
															return colorList[params.dataIndex]
														},
														label : {
															show : true,
															position : 'top',
															formatter : '{c}'
														}
													},

												},

											} ]
										});

									}
								});
							});
						}

						function loadEcharts2() {
							require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
								var myChart = ec.init(document.getElementById('main2'));
								myChart.showLoading();
								$.ajax({
									url : ctx + "/zxjkyj/dsjfx/getbarjson",
									type : "POST",
									dataType : "json",
									success : function(data) {
										var x = [];
										var y = [];
										for (var i = 0; i < data.length; i++) {
											x.push(data[i].name);
											y.push(data[i].value.toFixed(2));
										}
										myChart.hideLoading();
										myChart.setOption({
											title : {
												text : '根据物料名称实时统计储罐储量',
											},
											tooltip : {
												trigger : 'axis'
											},
											  /*  legend:{
											   	orient : 'vertical',
												x : 'right',
												data:x,
											   	formatter:function(b){
											   		if(b.length>6)
											   			return b.substring(0,3)+"..."+b.substring(b.length-2,b.length);
											   		else
											   			return b;
											   	} 
											   },  */
											calculable : true,
											xAxis : [ {
												type : 'value',
												'name':'m³',
											} ],
											yAxis : [ {
												type : 'category',
												axisLabel : {
													formatter : function(b) {
														if (b.length > 6)
															return b.substring(0, 3) + "..." + b.substring(b.length - 2, b.length);
														else
															return b;
													}
												},
												data : x

											} ],
											series : [ {
												type : 'bar',
												data : y,
												itemStyle : {
													normal : {
														color : function(params) {
															// build a color map as your need.
															var colorList = [ '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B', '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD', '#D7504B',
																	'#C6E579', '#F4E001', '#F0805A', '#26C0C0' ];
															return colorList[params.dataIndex]
														},
														label : {
															show : true,
															position : 'right',
															formatter : '{c}'
														}
													},

												},

											} ]
										});

									}
								});
							});
						}

						function loadEcharts() {
							require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
								var myChart = ec.init(document.getElementById('main'));
								myChart.showLoading();
								$.ajax({
									url : ctx + "/zxjkyj/dsjfx/getpiejson",
									type : "POST",
									dataType : "json",
									success : function(data) {

										var legend = [];
										for (var i = 0; i < data.length; i++) {
											legend.push(data[i].name);
										}
										myChart.hideLoading();
										myChart.setOption({
											title : {
												text : '根据物料类别实时统计储罐储量',

											},
											tooltip : {
												trigger : 'item',
												formatter : "{a}<br>{b}<br>{c}m³ ({d}%)"
											},
											legend : {
												orient : 'horizontal',
												y : 'bottom',
												x : 'center',
												data : legend
											},

											calculable : true,
											series : [ {
												name : '储罐储量',
												type : 'pie',
												radius : '40%',
												center : [ '50%', '60%' ],
												data : data,
												itemStyle : {
													normal : {
														color : function(params) {
															// build a color map as your need.
															var colorList = [ '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B', '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD', '#D7504B',
																	'#C6E579', '#F4E001', '#F0805A', '#26C0C0' ];
															return colorList[params.dataIndex]
														},
														
													},

												},

											} ]
										});

									}
								});
							});
						}
					</script>
</body>
</html>