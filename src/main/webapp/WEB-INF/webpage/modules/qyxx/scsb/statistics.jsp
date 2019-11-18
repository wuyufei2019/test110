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
             <div data-options="region:'west'" border="false"style="width:50%;height:100%">
                    <div class="ibox-content">
                         <div class="echart" id="main" style="width:100%;height:350px"></div>
                    </div>
               </div>
               <div data-options="region:'center'" border="false"style="width:50%;height:100%">
                    <div class="ibox-content">
                         <div class="echart" id="main2" style="width:100%;height:350px"></div>
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
	$(".echart").height($(window).height()-100);	
     var data=eval(${data});
     var d1=data.tyrq;
     var d2=data.sblb;
     var edata=[];
     var legend=['3年内','3-5年内','5-8年内','8年以上'];
	 for(index in d1){
		 edata.push({name:legend[index],value:d1[index]});
	 }
	 loadEcharts('main','投产时间段统计',legend,edata);
	 legend=[];
	 edata=[];
	 for(index in d2){
		 //legend.push(d2[index].name);
		 edata.push({name:d2[index].name,value:d2[index].value});
	 }
	 loadEcharts('main2','设备类别统计',legend,edata);
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
							                    label: {
							                        show: true,
							                        formatter: '{b}({c}){d}%'
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