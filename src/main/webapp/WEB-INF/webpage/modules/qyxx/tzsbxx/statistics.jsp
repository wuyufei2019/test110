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
            <div class="ibox-content">
                 <div id="main" style="width:100%;height:380px"></div>
            </div>
          </div>
     </div>
     <script type="text/javascript">
     var data=eval(${data});
	 var legend=[];
	 for(index in data){
		 legend.push(data[index].name);
	 }
		// 路径配置
 		require.config({
 			paths : {
 				echarts : '${ctxStatic}/echarts'
 			}
 		});
    	 loadEcharts();
		function loadEcharts() {
			require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
				var myChart = ec.init(document.getElementById('main'));
						myChart.setOption({
							  title : {
								    text: "设备类型统计",
							        x:'left'
							    },
							    legend: {
							        orient : 'vertical', 
							        y : 'bottom',
							        x:'left',
							        data:legend
							    },
							    tooltip : {
							        trigger: 'item',
							        formatter: "{b} : {c} ({d}%)"
							    },
							    series : [
							        {
							            type:'pie',
							            radius : ['35%', '55%'],
							            center: ['60%', '55%'],
							            itemStyle: {
							            	
							            	  normal: {
								                    label: {
								                        show: true,
								                        formatter: '{b}({c})\n{d}%'
								                    }
								                },
							                emphasis : {
							                    label : {
							                        show : true,
							                        position : 'center',
							                        textStyle : {
							                            fontSize : '25',
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