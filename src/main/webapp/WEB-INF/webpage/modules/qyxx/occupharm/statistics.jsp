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
             <div data-options="region:'west'" border="false"style="width:50%;">
                    <div class="ibox-content">
                         <div id="main" style="width:100%;height:350px"></div>
                    </div>
               </div>
               <div data-options="region:'center'" border="false"style="width:50%;">
                    <div class="ibox-content">
                         <div id="main2" style="width:100%;height:350px"></div>
                    </div>
               </div>
          </div>
     </div>
     <script type="text/javascript">
		// 路径配置
 		require.config({
 			paths : {
 				echarts : '${ctxStatic}/echarts'
 			}
 		});
     var data=eval(${data});
     var d1=data.whys;
     var d2=data.yzcd;
     var edata=[];
     var legend=[];
	 for(index in d1){
		 legend.push(d1[index].name);
	 }
	 loadEcharts('main','职业病危害因素统计',legend,d1);
	 legend=['轻度危险性','一般危险性','严重危险性'];
	 edata=[];
	 for(index in d2){
		 edata.push({name:legend[index],value:d2[index]});
	 }
	 loadEcharts('main2','严重程度统计',legend,edata);
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
						            radius : ['30%', '50%'],
						            itemStyle: {
						                normal: {
						                    color: function(params) {
						                        // build a color map as your need.
						                        var colorList = ['#FFA042','#FCCE10','#C1232B','#2894FF','#87cefa'];
						                        return colorList[(params.dataIndex)]
						                    },
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
						                            fontSize : '15',
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
	</script>
</body>
</html>