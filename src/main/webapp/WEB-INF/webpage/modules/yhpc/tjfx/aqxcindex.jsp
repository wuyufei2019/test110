<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>统计汇总</title>
<meta name="decorator" content="default" />

<link href="${ctxStatic}/model/css/yhpc/tjfxstyle.css" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body class="bggrey1">
      <div class="">
         <div class="col-lg-12">
            <div class="box2" id="yhpc_tj" style="margin-bottom: 5px">
               <div class="title">
                  <h5>时间统计(默认当月)</h5>
               </div>
               <div class="col-lg-2">
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #ff7f50; width: 10px;height: 10px;" />&nbsp;&nbsp;巡检数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #97d3f9; width: 10px;height: 10px;" />&nbsp;&nbsp;隐患数
                  </div>
               </div>
            <div class="col-lg-2">
               <div class="btn-group" style="height: 30px ;margin: 8px 20px 8px 10px">
                  <button type="button" id="day" class="btn btn-white active" onclick="countByTime(1)">&nbsp;天&nbsp;</button>
                  <button type="button" id="week" class="btn btn-white" onclick="countByTime(2)">&nbsp;月&nbsp;</button>
                  <button type="button" id="month" class="btn btn-white" onclick="countByTime(3)">&nbsp;年&nbsp;</button>
               </div>
            </div>
            <div class="col-lg-6" style="margin: 8px">
                <input type="text" id="time_starttime" name="time_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '开始时间'" />
                <input type="text" id="time_endtime" name="time_endtime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '结束时间'" />
		    	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search1()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset1()" ><i class="fa fa-refresh"></i> 全部</span>            
            </div>
            <div class="ibox-content" style="margin: 0 20px">
                  <div class="echart" id="main1" style="width:100%;height:400px"></div>
               </div>
            </div>
         </div>
         <div class="col-lg-12">
            <div class="box2" style="margin-bottom: 5px" id="yhpc_tj">
               <div class="title">
                  <h5>人员统计(默认当月)</h5>
               </div>
               <div class="col-lg-2">
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #ff7f50; width: 10px;height: 10px;" />&nbsp;&nbsp;巡检数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #97d3f9; width: 10px;height: 10px;" />&nbsp;&nbsp;隐患数
                  </div>
               </div>
            <div class="col-lg-5" style="height: 30px ;margin: 8px 20px 8px 10px">
               <input type="text" id="member_starttime" name="member_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '开始时间'" />
               <input type="text" id="member_endtime" name="member_endtime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '结束时间'" />
		       <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
			   <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset2()" ><i class="fa fa-refresh"></i> 全部</span>            
            </div>
            <div class="ibox-content" style="margin: 0 20px">
                  <div class="echart" id="main2" style="width:100%;height:400px"></div>
               </div>
            </div>
         </div>
      </div>
   <script>
   var type=1;
   var daycount=eval(${daycount});
   $(function(){
		countByTime(1);
		countByUser();
   });

   function countByTime(flag){
	   if(flag==1){
		 type=1;
	     $("#day").addClass("active");
	  	 $("#week").removeClass("active");
	   	 $("#month").removeClass("active");
	   }
	   if(flag==2){
		 type=2;  
	  	 $("#week").addClass("active");
	   	 $("#month").removeClass("active");
		 $("#day").removeClass("active");		   
	   }
	   if(flag==3){
		 type=3;
		 $("#month").addClass("active");
	  	 $("#week").removeClass("active");
		 $("#day").removeClass("active");		   
	   }
		$.ajax({
			url: '${ctx}/yhpc/tjfx/aqxctj',
			type:'POST',
			data:{"flag":flag,"starttime":$("#time_starttime").datebox('getValue'),"endtime":$("#time_endtime").datebox('getValue')},  
			dataType : "json",
			success : function(data) {
				loadEcharts(data);
			}
		});	   
   }

   function countByUser(){
		$.ajax({
			url: '${ctx}/yhpc/tjfx/aqxctj2',
			type:'POST',
			data:{"starttime":$("#member_starttime").datebox('getValue'),"endtime":$("#member_endtime").datebox('getValue')},  
			dataType : "json",
			success : function(data) {
				loadEcharts2(data);
			}
		});	   
   }
   
	require.config({
		paths : {
			echarts : '${ctxStatic}/echarts'
		}
	});
			
	function loadEcharts(data) {
		require([ 'echarts', 'echarts/chart/line' ], function(ec) {
			var myChart = ec.init(document.getElementById("main1"));
			myChart.setOption({
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : data.date
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'搜索引擎',
			            type:'line',
			            symbol:'emptyCircle',
			            itemStyle: {
			                normal: {
			                    label:{
									show: true,//是否展示  
									position:'top'
								},
			                    lineStyle: { 
			                        width: 2,
			                        shadowColor : 'rgba(0,0,0,0.5)',
			                        shadowBlur: 10,
			                        shadowOffsetX: 8,
			                        shadowOffsetY: 8
			                    }
			                },
			                emphasis : {
			                    label : {show: true}
			                }
			            },
			            data:data.xjcount
			        },
			        {
			            name:'搜索引擎',
			            type:'line',
			            symbol:'emptyCircle',
			            itemStyle: {
			                normal: {
			                    label:{
									show: true,//是否展示  
									position:'top'
								},
			                    lineStyle: {            // 系列级个性化折线样式，横向渐变描边
			                        width: 2,
			                        shadowColor : 'rgba(0,0,0,0.5)',
			                        shadowBlur: 10,
			                        shadowOffsetX: 8,
			                        shadowOffsetY: 8
			                    }
			                },
			                emphasis : {
			                    label : {show: true}
			                }
			            },
			            data:data.yhcount
			        }
			    ]
			});
		});
	}
				
	function loadEcharts2(data) {
		require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
			var myChart = ec.init(document.getElementById("main2"));
			myChart.setOption({ 
			    tooltip : {
			        trigger: 'axis'
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : data.name
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'巡检数',
			            type:'bar',
			            itemStyle : {
							normal : {
								label:{
    								show: true,//是否展示  
    								position:'top'
    							},
						 	},
						},
						barWidth : 12,//柱图宽度
						barCategoryGap : '60%',
			            data: data.xjcount
			        },
			        {
			            name:'隐患数',
			            type:'bar',
			            itemStyle : {
      					normal : {
         						label:{
         							show: true,//是否展示  
         							position:'top',
         							textStyle:{color:'#5690D2'},
         						},
         				 	},
         				},
      					barWidth : 12,//柱图宽度
      					barCategoryGap : '60%',
			            data: data.yhcount	
			        }
			    ]});
		});
	}

	//创建查询对象并查询
	function search1(){
		countByTime(type); 
	}

	//重置
	function reset1(){
		type=1;
		$('#time_starttime').datebox("setValue",'');
		$('#time_endtime').datebox("setValue",'');
		countByTime(type); 
	}	
	
	//创建查询对象并查询
	function search2(){
		countByUser(); 
	}

	//重置
	function reset2(){
		type=1;
		$('#member_starttime').datebox("setValue",'');
		$('#member_endtime').datebox("setValue",'');
		countByUser(); 
	}
</script>
</body>
</html>