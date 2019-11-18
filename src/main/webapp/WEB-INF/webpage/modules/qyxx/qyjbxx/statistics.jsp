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
          <div data-options="region:'south',split:true" border="false"style="width:100%;height:50%;">
           <div class="easyui-layout" data-options="fit:true" border="false">
               <div data-options="region:'west'" border="false"style="width:33%;">
                     <div class="ibox-content">
                         <div class="echart" id="main4" style="width:100%;height:380px"></div>
                    </div>
               </div>
               <div data-options="region:'center'" border="false"style="width:33%;">
                  <div class="ibox-content">
                         <div class="echart" id="main5" style="width:100%;height:380px"></div>
                    </div>
               </div>
               <div data-options="region:'east'" border="false"style="width:33%;">
                  <div class="ibox-content">
                         <div class="echart" id="main6" style="width:100%;height:380px"></div>
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
    	 var legend=['是','否'];
    	 edata.push({name:"是",value:data.iswxyy});
    	 edata.push({name:"否",value:data.iswxyn});
    	 loadEcharts2('main','重大危险源统计',legend,edata);
    	 edata=[];
    	 edata.push({name:"是",value:data.iszdjgy});
    	 edata.push({name:"否",value:data.iszdjgn});
    	 loadEcharts2('main2','是否为重点监管统计',legend,edata);
    	 edata=[];
    	 edata.push({name:"是",value:data.isgwgyy});
    	 edata.push({name:"否",value:data.isgwgyn});
    	 loadEcharts2('main3','是否为高危工艺统计',legend,edata);
    	 legend=['规上','规下'];
    	 edata=[];
    	 edata.push({name:"规上",value:data.qygm1});
    	 edata.push({name:"规下",value:data.qygm2});
    	 loadEcharts2('main4','企业规模情况统计',legend,edata);
    	 legend=['红','橙','黄','蓝'];
    	 edata=[];
    	 edata.push({name:"红",value:data.fxdj1});
    	 edata.push({name:"橙",value:data.fxdj2});
    	 edata.push({name:"黄",value:data.fxdj3});
    	 edata.push({name:"蓝",value:data.fxdj4});
    	 loadEcharts2('main5','企业风险等级统计',legend,edata);
    	 legend=['一级','二级','三级','四级'];
    	 edata=[];
    	 edata.push({name:"一级",value:data.wxydj1});
    	 edata.push({name:"二级",value:data.wxydj2});
    	 edata.push({name:"三级",value:data.wxydj3});
    	 edata.push({name:"四级",value:data.wxydj4});
    	 loadEcharts2('main6','重大危险源等级统计',legend,edata);
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
							    tooltip : {
							        trigger: 'item',
							        formatter: "{b} : {c} ({d}%)"
							    },
							    legend: {
							        orient : 'horizontal',
							        y : 'bottom',
							        x:'center',
							        data:legend
							    },
							    series : [
							        {
							        	  itemStyle : {
							                  normal : {
							                      label : {
							                          formatter : '{b}({c})\n{d}%'
							                      },
							                      labelLine : {
							                          length: 10,
							                      }
							                  },
							        	  },
							            type:'pie',
							            radius : '40%',
							            center: ['50%', '50%'],
							            data:data
							        }
							        ]
						});
					});
			}
		
		function loadEcharts2(div,title,legend,data) {
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
						            radius : ['25%', '45%'],
						            itemStyle: {
						                normal: {
						                    color: function(params) {
						                        // build a color map as your need.
						                        var colorList = ['#C1232B','#FFA042','#FCCE10','#2894FF','#87cefa'];
						                        return colorList[(params.dataIndex)]
						                    },
						                    label: {
						                        show: true,
						                        formatter: '{b}({c}){d}%',
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