<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>三级教育培训记录统计</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body>
<div class="easyui-panel"  style="width:100%;height:100%;" >
	 <br><br>
	 <h1 style="color: red;text-align: center;">三级教育统计</h1>
	 <br><br>
     <div class="easyui-layout" data-options="fit:true" >
	     <div data-options="region:'north',split:true" border="false" style="width:100%;height:50%;">
	       	  <div class="easyui-layout" data-options="fit:true" border="false">
		           <div data-options="region:'west'" border="false"style="width:33%;">
		                <div class="ibox-content">
		                     <div class="echart" id="main" style="width:100%;height:380px"></div>
		                </div>
		           </div>
		           <div data-options="region:'center'" border="false"style="width:33%;">
		                <div class="ibox-content">
		                     <div class="echart" id="main2" style="width:100%;height:380px"></div>
		                </div>
		           </div>
		           <div data-options="region:'east'" border="false"style="width:33%;">
		               <div class="ibox-content">
		                     <div class="echart" id="main3" style="width:100%;height:380px"></div>
		                </div>
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
    	 var legend=['合格','不合格'];
    	 edata.push({name:"合格",value:data.hg});
    	 edata.push({name:"不合格",value:data.bhg});
    	 loadEcharts('main','',legend,edata);
    	 
    	 edata=[];
    	 legend=['线上','线下'];
    	 edata.push({name:"线上",value:data.xs});
    	 edata.push({name:"线下",value:data.xx});
    	 loadEcharts('main2','',legend,edata);
    	 
    	 edata=[];
    	 legend=['已培训','未培训'];
    	 edata.push({name:"已培训",value:data.ypx});
    	 edata.push({name:"未培训",value:data.wpx});
    	 loadEcharts('main3','',legend,edata);
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
							        x:'center',
							        textStyle: {
							        	fontSize: 15,
							        }
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
						            radius : ['35%', '65%'],
						            itemStyle: {
						                normal: {
						                    color: function(params) {
						                        var colorList = ['#C1232B','#FFA042','#87cefa','#FCCE10','#2894FF','#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#03a9f4'];
						                        return colorList[Math.floor(Math.random()*10)]
						                    },
						                    label: {
						                        show: true,
						                        formatter: '{b}{d}%',
						                    },
						                    labelLine : {
						                          length: 20,
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
						            clockWise: false,
						            minAngle: 10,
						            data:data
						        }
						    ]
						    });
					});
			}
</script>
</body>
</html>