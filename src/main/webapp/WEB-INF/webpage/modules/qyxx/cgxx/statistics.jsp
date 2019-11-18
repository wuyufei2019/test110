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
                         <div class="echart" id="main" style="width:100%;height:380px"></div>
                    </div>
               </div>
               <div data-options="region:'center'" border="false"style="width:50%;">
                    <div class="ibox-content">
                         <div class="echart" id="main2" style="width:100%;height:380px"></div>
                    </div>
               </div>
               </div>
          </div>
          <div data-options="region:'south',split:true" border="false"style="width:100%;height:50%;">
           <div class="easyui-layout" data-options="fit:true" border="false">
            <div class="easyui-layout" data-options="fit:true" border="false">
               <div data-options="region:'west'" border="false"style="width:50%;">
                    <div class="ibox-content">
                         <div class="echart" id="main3" style="width:100%;height:380px"></div>
                    </div>
               </div>
               <div data-options="region:'center'" border="false"style="width:50%;">
                    <div class="ibox-content">
                         <div class="echart" id="main4" style="width:100%;height:380px"></div>
                    </div>
               </div>
               </div>
               </div>
          </div>
          </div>
     </div>
     <script type="text/javascript">
     var data=eval(${data});
     $(function(){
    	 var height=$(window).height();
    	 $(".echart").height(height*2/5);
    	 var edata=[];
    	 var legend=['立式圆筒形储罐','卧式圆筒形储罐','球形储罐','其他储罐'];
    	 for(var i=0;i<4;i++){
    	 edata.push({name:legend[i],value:data["cglx"+(i+1)]});
    	 }
    	 loadEcharts('main','储罐类型统计',legend,edata);
    	 edata=[];
    	 legend=['甲类','乙类','丙类','丁类','戊类'];
    	 for(var i=0;i<5;i++){
        	 edata.push({name:legend[i],value:data["hzwxdj"+(i+1)]});
        	 }
         loadEcharts('main2','火灾危险等级统计',legend,edata);
         edata=[];
         legend=['滚塑','玻璃钢','碳钢','陶瓷','橡胶','不锈钢','其他'];
         for(var i=0;i<7;i++){
        	 edata.push({name:legend[i],value:data["cz"+(i+1)]});
        	 }
         loadEcharts('main3','材质统计',legend,edata);
         edata=[];
         legend=['0~50','50~100','100~200','200~400','400以上'];
         for(var i=0;i<6;i++){
        	 edata.push({name:legend[i],value:data["rj"+(i+1)]});
        	 }
         loadEcharts('main4','容积统计',legend,edata);
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