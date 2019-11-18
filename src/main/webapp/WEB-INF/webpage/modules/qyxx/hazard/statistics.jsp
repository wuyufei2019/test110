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
	 var legend=['一级','二级','三级','四级'];
	 var edata=[];
	 for(index in data){
		 edata.push({"name":legend[index],"value":data[index]});
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
								    text: "危险源等级统计",
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
							    calculable : true,
							    series : [
							        {
							            type:'pie',
							            radius : ['35%', '55%'],
							            center: ['60%', '55%'],
							            itemStyle: {
							            	
							            	  normal: {
							            		  color: function(params) {
								                        // build a color map as your need.
								                        var colorList = ['#C1232B','#FFA042','#FCCE10','#2894FF','#87cefa'];
								                        return colorList[(params.dataIndex)]
								                    },
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
							            data:edata
							        }
							    ]
							    
						});
					});
			}
	</script>
</body>
</html>