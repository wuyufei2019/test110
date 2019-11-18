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


     <div class="easyui-panel"  style="width:100%;height:100%;" >
          <div class="easyui-layout" data-options="fit:true" >
          <div data-options="region:'north',split:true" border="false" style="width:100%;height:50%;">
           <div class="easyui-layout" data-options="fit:true" border="false">
               <div data-options="region:'west'" border="false"style="width:50%;">
                    <div class="ibox-content">
                         <div class="echart" id="main" style="width:100%;"></div>
                    </div>
               </div>
               <div data-options="region:'center'" border="false"style="width:50%;">
                    <div class="ibox-content">
                         <div class="echart" id="main2" style="width:100%;"></div>
                    </div>
               </div>
               </div>
          </div>
          <div data-options="region:'south',split:true" border="false"style="width:100%;height:50%;">
           <div class="easyui-layout" data-options="fit:true" border="false">
            <div class="ibox-content">
                         <div class="echart" id="main3" style="width:100%;"></div>
                    </div>
               </div>
          </div>
          </div>
     </div>
     <script type="text/javascript">
     var data=eval(${mapdata});
     $(function(){
    	 var height=$(window).height();
    	 $(".echart").height(height*2/5);
    	 var edata=[];
    	 var legend=['甲类','乙类','丙类','丁类','戊类'];
    	 for(var i=0;i<5;i++){
    	 edata.push({name:legend[i],value:data["hzwxdj"+(i+1)]});
    	 }
    	 loadEcharts('main','火灾危险等级统计',legend,edata);
    	 edata=[];
    	 legend=['钢混结构','砖混','钢结构','框架','其他'];
    	 for(var i=0;i<5;i++){
        	 edata.push({name:legend[i],value:data["jzjg"+(i+1)]});
        	 }
         loadEcharts('main2','建筑结构统计',legend,edata);
         edata=[];
         legend=[];
         for(var i=0;i<15;i++){
        	 var l=(i+1)+"层";
        	 legend.push(l);
        	 edata.push(data["cs"+(i+1)]);
        	 }
         loadEcharts2('层数统计',legend,edata);
     });
		// 路径配置
 		require.config({
 			paths : {
 				echarts : '${ctxStatic}/echarts'
 			}
 		});
		function loadEcharts(div,title,legend,data) {
			require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
				var myChart = ec.init(document.getElementById(div));
						myChart.setOption({
							 title : {
							        text: title,
							        x:'center'
							    },
							    legend: {
							        orient : 'horizontal',
							        y : 'bottom',
							        x:'center',
							        data:legend
							    },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{b} : {c} ({d}%)"
						    },
						    series : [
						        {
						            type:'pie',
						            radius : ['25%', '45%'],
						            itemStyle: {
						            	  normal: {
							                    label: {
							                        show: true,
							                        formatter: '{b}({c})\n{d}%'
							                    },
							                    labelLine : {
							                          length: 5,
							                      }
							                },
						                emphasis : {
						                    label : {
						                        show : true,
						                        position : 'center',
						                        textStyle : {
						                            fontSize : '30',
						                            fontWeight : 'bold'
						                        }
						                    }
						                }
						            },
						            data:data
						        }
						    ]
						    });
					});
			}
		function loadEcharts2(title,legend,data) {
			require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
				var myChart = ec.init(document.getElementById('main3'));
						myChart.setOption({
							 title: {
							        x: 'center',
							        text: title,
							    },
							    tooltip: {
							        trigger: 'item'
							    },
							    toolbox: {
							        show: true,
							        feature: {
							            dataView: {show: true, readOnly: false},
							            restore: {show: true},
							            saveAsImage: {show: true}
							        }
							    },
							    calculable: true,
							    grid: {
							        borderWidth: 0,
							        y: 80,
							        y2: 60
							    },
							    xAxis: [
							        {
							            type: 'category',
							            show: true,
							            data: legend,
							        }
							    ],
							    yAxis: [
							        {
							            type: 'value',
							            show: true
							        }
							    ],
							    series: [
							        {
							            type: 'bar',
							            itemStyle: {
							                normal: {
							                    color: function(params) {
							                        // build a color map as your need.
							                        var colorList = [
							                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
							                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
							                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
							                        ];
							                        return colorList[(params.dataIndex)%15]
							                    },
							                    label: {
							                        show: true,
							                        position: 'top',
							                        formatter: '{b}\n{c}'
							                    }
							                }
							            },
							            data: data,
							        }
							    ]
						});
					});
			}
	</script>
</body>
</html>