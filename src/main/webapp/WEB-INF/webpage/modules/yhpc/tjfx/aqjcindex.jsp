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
<div class="easyui-tabs" fit="true">
	<div title="时间统计" style="height:100%;" data-options="" id="">
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
            	<!-- 集团 -->
	            <c:if test="${qytype != '' and qytype == 'bloc'}">
	            	<input type="text" id="combo1" name="combo1" class="easyui-combobox" style="height: 30px;" data-options="prompt: '企业列表', idField: 'qyid', valueField: 'qyid', textField: 'qyname',
	             												url: ctx+'/yhpc/tjfx/qynamejson',
	             												onSelect: function(rec){
	             													qyid = rec.qyid;
	             													if ($('#day').hasClass('active')) {
	             														countByTime(1);
	             													} else if ($('#week').hasClass('active')) {
	             														countByTime(2);
	             													} else if ($('#month').hasClass('active')) {
	             														countByTime(3);
	             													}  
	             												} " />
	            </c:if>
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
    </div>
    <div title="人员统计" style="height:100%;" data-options="" id="user_tj">
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
            	<!-- 集团 -->
	            <c:if test="${qytype != '' and qytype == 'bloc'}">
	            	<input type="text" id="combo2" name="combo2" class="easyui-combobox" style="height: 30px;" data-options="prompt: '企业列表', idField: 'qyid', valueField: 'qyid', textField: 'qyname',
	             												url: ctx+'/yhpc/tjfx/qynamejson',
	             												onSelect: function(rec){
	             													qyid = rec.qyid;
	             													countByUser();
	             													qyid = '';
	             												} " />
	            </c:if>
               <input type="text" id="member_starttime" name="member_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '开始时间'" />
               <input type="text" id="member_endtime" name="member_endtime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '结束时间'" />
		       <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
			   <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset2()"><i class="fa fa-refresh"></i> 全部</span>            
            </div>
            <div class="ibox-content" style="margin: 0 20px">
                  <div class="echart" id="main2" style="width:100%;height:400px"></div>
               </div>
            </div>
         </div>
     </div>
     <div title="重要危险源整改汇总" style="height:100%;" data-options="" id="">
         <div class="col-lg-12">
            <div class="box2" style="margin-bottom: 5px" id="yhpc_tj">
               <div class="title">
                  <h5>重要危险源整改统计(默认当月)</h5>
               </div>
               <div class="col-lg-2" style="width: 50%;">
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #ad23bd; width: 10px;height: 10px;" />&nbsp;&nbsp;本期发生数量
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #fb812a; width: 10px;height: 10px;" />&nbsp;&nbsp;上期未完成项
                  </div>
                 <div style="float: left;padding:12px;">
                     <img style="background-color: #e8c85a; width: 10px;height: 10px;" />&nbsp;&nbsp;整改
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #98efe7; width: 10px;height: 10px;" />&nbsp;&nbsp;未到期项
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #f70606; width: 10px;height: 10px;" />&nbsp;&nbsp;未完成
                  </div>
               </div>
            <div class="col-lg-5" style="width: 50%;height: 30px ;margin-top: 8px;">
	            <!-- 集团 -->
	            <c:if test="${qytype != '' and qytype == 'bloc'}">
	            	<input type="text" id="combo3" name="combo3" class="easyui-combobox" style="height: 30px;" data-options="prompt: '企业列表', idField: 'qyid', valueField: 'qyid', textField: 'qyname',
	             												url: ctx+'/yhpc/tjfx/qynamejson',
	             												onSelect: function(rec){
	             													qyid = rec.qyid;
	             													countByDept();
	             													qyid = '';
	             												} " />
	            </c:if>
               <input type="text" id="dept_starttime" name="dept_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '开始时间'" />
               <input type="text" id="dept_endtime" name="dept_endtime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '结束时间'" />
		       <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search3()" ><i class="fa fa-search"></i> 查询</span>
			   <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset3()" ><i class="fa fa-refresh"></i> 全部</span>            
            </div>
            <div class="ibox-content" style="margin: 0 20px">
                  <div class="echart" id="main3" style="width:100%;height:400px"></div>
               </div>
            </div>
         </div>
     </div>
     <div title="风险类别统计图" style="height:100%;" data-options="" id="">
         <div class="col-lg-12">
            <div class="box2" style="margin-bottom: 5px" id="yhpc_tj">
               <div class="title">
                  <h5>风险类别统计图</h5>
               </div>
               <div class="col-lg-2" style="width: 50%;">
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #fd2310; width: 10px;height: 10px;" />&nbsp;&nbsp;红色风险点
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #ecb48b; width: 10px;height: 10px;" />&nbsp;&nbsp;橙色风险点
                  </div>
                 <div style="float: left;padding:12px;">
                     <img style="background-color: #e4dc22; width: 10px;height: 10px;" />&nbsp;&nbsp;黄色风险点
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #7e98ef; width: 10px;height: 10px;" />&nbsp;&nbsp;蓝色风险点
                  </div>
                </div>
	            <div class="col-lg-5" style="width: 45%;height: 30px;margin-top: 8px;margin-bottom: 6px;">
	            	<!-- 集团 -->
		            <c:if test="${qytype != '' and qytype == 'bloc'}">
		            	<input type="text" id="combo5" name="combo5" class="easyui-combobox" style="height: 30px;" data-options="prompt: '企业列表', idField: 'qyid', valueField: 'qyid', textField: 'qyname',
		             												url: ctx+'/yhpc/tjfx/qynamejson',
		             												onSelect: function(rec){
		             													qyid = rec.qyid;
		             													countByFxlb();
		             													qyid = '';
		             												} " />
		            </c:if>
	               <input type="text" id="fxlb_name" name="fxlb_name" class="easyui-combobox" style="height: 30px;" data-options="prompt: '风险类别',
									editable:true ,panelHeight:'auto',valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/fxfl'" />
			       <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search5()" ><i class="fa fa-search"></i> 查询</span>
				   <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset5()" ><i class="fa fa-refresh"></i> 全部</span>            
	            </div>
	            <div class="ibox-content" style="margin: 0 20px">
                  <div class="echart" id="main5" style="width:100%;height:400px;margin-bottom: 60px;"></div>
               </div>
            </div>
         </div>
     </div>
   </div>
   <script>
   var type=1;
   var qyid;//企业列表下拉框中每一项的id
   var qytype = '${bloc}';//企业类型，bloc代表集团
   
   $(function(){
		countByTime(1);
		countByUser();
		//重要危险源整改 
		countByDept();
		//安全隐患类别占比
		countByYhkind();
		//风险类别统计图
		countByFxlb();
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
			url: '${ctx}/yhpc/tjfx/aqjctj',
			type:'POST',
			data:{"flag":flag,"starttime":$("#time_starttime").datebox('getValue'),"endtime":$("#time_endtime").datebox('getValue'), "qyid": qyid},  
			dataType : "json",
			success : function(data) {
				loadEcharts(data);
			}
		});	   
   }

   function countByUser(){
		$.ajax({
			url: '${ctx}/yhpc/tjfx/aqjctj2',
			type:'POST',
			data:{"starttime":$("#member_starttime").datebox('getValue'),"endtime":$("#member_endtime").datebox('getValue'), "qyid": qyid},  
			dataType : "json",
			success : function(data) {
				loadEcharts2(data);
			}
		});	
   }
   
   function countByDept(){
		$.ajax({
			url: '${ctx}/yhpc/tjfx/aqjctj3',
			type:'POST',
			data:{"starttime":$("#dept_starttime").datebox('getValue'),"endtime":$("#dept_endtime").datebox('getValue'), "qyid": qyid},  
			dataType : "json",
			success : function(data) {
				loadEcharts3(data);
			}
		});	   
   }
   
   function countByYhkind(){
		$.ajax({
			url: '${ctx}/yhpc/tjfx/aqjctj4',
			type:'POST',
			data:{"starttime":$("#yhkind_starttime").datebox('getValue'),"endtime":$("#yhkind_endtime").datebox('getValue'), "qyid": qyid},  
			dataType : "json",
			success : function(data) {
				loadEcharts4(data);
			}
		});	   
   }
   
   function countByFxlb(){
   	   $.ajax({
			url: '${ctx}/yhpc/tjfx/aqjctj5',
			type:'POST',
			data:{"fxlb_name":$("#fxlb_name").combobox('getValue'), "qyid": qyid},  
			dataType : "json",
			success : function(data) {
				loadEcharts5(data);
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
			                    	color:'#ff7f50',
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
			                        color:'#97d3f9',
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
			/* var myChart = ec.init(document.getElementById("main2")); */
			var myChart=document.getElementById("main2");
			//自适应
			myChart.style.width=window.innerWidth+'px';
			//初始化
			myChart=ec.init(myChart);
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
	
	//重要危险源
	function loadEcharts3(data) {
		require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
			/* var myChart = ec.init(document.getElementById("main3")); */
			var myChart=document.getElementById("main3");
			//自适应
			myChart.style.width=window.innerWidth+'px';
			myChart=ec.init(myChart);
			myChart.setOption({ 
			    tooltip : {
			        trigger: 'axis'
			    },
			    xAxis : [
			        {
			        	name: '部门名称',
			            type : 'category',
			            data : data.deptname,
			            axisLabel: {
				        	interval:0,
	                      	formatter: function(value){
	                      		return value.split("").join("\n");
	                      	}
				        }
			        }
			        
			    ],
			    grid: {x2: 180,y2: 100 },
			    yAxis : [
			        {
			        	name : '数量',
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'本期发生数量',
			            type:'bar',
			            itemStyle : {
							normal : {
								color: '#ad23bd',
								label:{
    								show: true,//是否展示  
    								position:'top',
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value;
    									} else {
    										return '';
    									}
    								}
    							},
						 	},
						},
						barWidth : 12,//柱图宽度
						barCategoryGap : '80%',
			            data: data.current_mon_count
			        },
			        {
			            name:'上期未完成项',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#fb812a',
         						label:{
         							show: true,//是否展示  
         							position:'top',
         							textStyle:{color:'#fb812a'},
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value;
    									} else {
    										return '';
    									}
    								}
         						},
         				 	},
         				},
      					barWidth : 12,//柱图宽度
      					barCategoryGap : '80%',
			            data: data.last_mon_count	
			        },
			        {
			            name:'整改项',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#e8c85a',
         						label:{
         							show: true,//是否展示  
         							position:'top',
         							textStyle:{color:'#e8c85a'},
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value;
    									} else {
    										return '';
    									}
    								}
         						},
         				 	},
         				},
      					barWidth : 12,//柱图宽度
      					barCategoryGap : '80%',
			            data: data.soluted_count	
			        },
			        {
			            name:'未完成项',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#f70606',
         						label:{
         							show: true,//是否展示  
         							position:'top',
         							textStyle:{color:'#f70606'},
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value;
    									} else {
    										return '';
    									}
    								}
         						},
         				 	},
         				},
      					barWidth : 12,//柱图宽度
      					barCategoryGap : '80%',
			            data: data.no_solut_count	
			        },
			        {
			            name:'未到期项',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#98efe7',
         						label:{
         							show: true,//是否展示  
         							position:'top',
         							textStyle:{color:'#98efe7'},
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value;
    									} else {
    										return '';
    									}
    								}
         						},
         				 	},
         				},
      					barWidth : 12,//柱图宽度
      					barCategoryGap : '80%',
			            data: data.no_expire_count	
			        }
			    ]});
		});
	}
	
	
	//安全隐患类别占比
	function loadEcharts4(data) {
		require([ 'echarts', 'echarts/chart/pie' ], function(ec) {
			/* var myChart = ec.init(document.getElementById("main4")); */
			var myChart=document.getElementById("main4");
			myChart.style.width=window.innerWidth+'px';
			myChart=ec.init(myChart);
			myChart.setOption({ 
			    tooltip : {
			        trigger: 'item',
                    formatter: "{a} <br/>{b} : {d}%"
			    },
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data: data.name
			    },
				toolbox : {
					show : true,
					feature : {
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				series : [ {
					name : '安全隐患类别占比',
					type : 'pie',
				    radius : '70%',  
                    center: ['40%', '50%'], 
                    itemStyle:{ 
			            normal:{ 
			                  label:{ 
			                    show: true, 
			                    formatter: '{b} : {d}%' 
			                  }, 
			                  labelLine :{show:true} 
			            } 
			        },  
				    data : data.yhcount
				}]
			});
		});
	}
	
	//风险类别
	function loadEcharts5(data) {
		require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
			var myChart=document.getElementById("main5");
			//自适应
			myChart.style.width=window.innerWidth+'px';
			myChart=ec.init(myChart);
			myChart.setOption({ 
			    tooltip : {
			        trigger: 'axis'
			    },
			     toolbox: {
			        show : true,
			        feature : {
			            /* mark : {show: true},
			            dataView : {show: true, readOnly: false}, */
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        },
			         x: 950,
       				 y:'top'
			    },
			    xAxis : [
			        {
			        	name: '风险类别名称',
			            type : 'category',
			            data : data.name,
			            axisLabel: {
				        	interval:0
				        }
			        }
			        
			    ],
			    grid: {x2: 180,y2: 100 },
			    yAxis : [
			        {
			        	name : '数量',
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'红色数量',
			            type:'bar',
			            itemStyle : {
							normal : {
								color: '#fd2310',
								label:{
    								show: true,//是否展示  
    								position:'top',
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value;
    									} else {
    										return '';
    									}
    								}
    							},
						 	},
						},
						barWidth : 12,//柱图宽度
						barCategoryGap : '40%',
			            data: data.red_count
			        },
			        {
			            name:'橙色数量',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#ecb48b',
         						label:{
         							show: true,//是否展示  
         							position:'top',
         							textStyle:{color:'#fb812a'},
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value;
    									} else {
    										return '';
    									}
    								}
         						},
         				 	},
         				},
      					barWidth : 12,//柱图宽度
      					barCategoryGap : '40%',
			            data: data.orange_count	
			        },
			        {
			            name:'黄色数量',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#e4dc22',
         						label:{
         							show: true,//是否展示  
         							position:'top',
         							textStyle:{color:'#e8c85a'},
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value;
    									} else {
    										return '';
    									}
    								}
         						},
         				 	},
         				},
      					barWidth : 12,//柱图宽度
      					barCategoryGap : '40%',
			            data: data.yellow_count	
			        },
			        {
			            name:'蓝色数量',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#7e98ef',
         						label:{
         							show: true,//是否展示  
         							position:'top',
         							textStyle:{color:'#f70606'},
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value;
    									} else {
    										return '';
    									}
    								}
         						},
         				 	},
         				},
      					barWidth : 12,//柱图宽度
      					barCategoryGap : '40%',
			            data: data.blue_count	
			        }/* ,
			        {
			            name:'总数量',
			            type:'line',
			            itemStyle : {
      					normal : {
      							color: '#ad23bd',
         				 	},
         				},
			            data: data.m2_sum_count	
			        } */
			    ]});
		});
	}

	//创建查询对象并查询
	function search1() {
		countByTime(type);
	}

	//重置
	function reset1() {
		type = 1;
		$('#time_starttime').datebox("setValue", '');
		$('#time_endtime').datebox("setValue", '');
		countByTime(type);
	}

	//创建查询对象并查询
	function search2() {
		countByUser();
	}

	//重置
	function reset2() {
		type = 1;
		$('#member_starttime').datebox("setValue", '');
		$('#member_endtime').datebox("setValue", '');
		countByUser();
	}

	//创建查询对象并查询
	function search3() {
		countByDept();
	}

	//重置
	function reset3() {
		$('#dept_starttime').datebox("setValue", '');
		$('#dept_endtime').datebox("setValue", '');
		countByDept();
	}

	//创建查询对象并查询
	function search4() {
		countByYhkind();
	}

	//重置
	function reset4() {
		$('#yhkind_starttime').datebox("setValue", '');
		$('#yhkind_endtime').datebox("setValue", '');
		countByYhkind();
	}
	
	//创建查询对象并查询
	function search5() {
		countByFxlb();
	}

	//重置
	function reset5() {
		$('#fxlb_name').combobox("setValue", '');
		countByFxlb();
	}
</script>
</body>
</html>