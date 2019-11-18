<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>数据统计</title>
<meta name="decorator" content="default" />

<link href="${ctxStatic}/model/css/yhpc/tjfxstyle.css" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body class="bggrey1">
<div class="easyui-tabs" fit="true">
     <div title="设备完好状况统计" style="height:100%;" data-options="" id="">
         <div class="col-lg-12">
            <div class="box2" style="margin-bottom: 5px" id="yhpc_tj">
               <div class="title">
                  <h5>设备完好状况统计(默认当月)</h5>
               </div>
               <div class="col-lg-2" style="width: 50%;">
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #ad23bd; width: 10px;height: 10px;" />&nbsp;&nbsp;全部设备台数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #98efe7; width: 10px;height: 10px;" />&nbsp;&nbsp;主要设备台数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #659ff3; width: 10px;height: 10px;" />&nbsp;&nbsp;主要设备完好台数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #f3cc65; width: 10px;height: 10px;" />&nbsp;&nbsp;主要设备完好率
                  </div>
               </div>
            <div class="col-lg-5" style="width: 50%;height: 30px ;margin-top: 8px;">
	            <!-- 集团 -->
	            <c:if test="${type == '2'}">
	            	<input id="dept_qyid" name="dept_qyid" class="easyui-combobox" style="height: 30px;" 
	            		data-options="prompt: '企业列表', panelHeight: '150px', idField: 'id', valueField: 'id', textField: 'text',url: '${ctx}/bis/qyjbxx/idjson',
	            			onLoadSuccess: function(){
	            				var data = $('#dept_qyid').combobox('getData');
								$('#dept_qyid').combobox('select',data[0].id);
								$('#dept_deptid').combobox('setValue', '');
								$('#dept_deptid').combobox('reload', '${ctx}/system/department/deptjson3/'+data[0].id);
								
								countByDept();
	            			},
	            			onSelect: function(row) {
								$('#dept_deptid').combobox('setValue', '');
								$('#dept_deptid').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
							}
	            		" />
	            </c:if>
	            <input name="dept_deptid" id="dept_deptid" class="easyui-combobox" style="width: 150px;height: 30px;" 
						data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url: '${ctx}/system/department/deptjson',prompt: '使用单位' "/>
               <input type="text" id="dept_starttime" name="dept_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '日期'" />
		       <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search3()" ><i class="fa fa-search"></i> 查询</span>
			   <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset3()" ><i class="fa fa-refresh"></i> 全部</span> 
			   <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="exportYdbb()" ><i class="fa fa-external-link"></i> 导出月度报表</span>           
            </div>
            <div class="ibox-content" style="margin: 0 20px">
                  <div class="echart" id="main3" style="width:100%;height:400px"></div>
               </div>
            </div>
         </div>
     </div>
     <div title="设备保养状况统计" style="height:100%;" data-options="" id="">
         <div class="col-lg-12">
            <div class="box2" style="margin-bottom: 5px" id="yhpc_tj">
               <div class="title">
                  <h5>设备保养状况统计(默认当月)</h5>
               </div>
               <div class="col-lg-2" style="width: 50%;">
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #ad23bd; width: 10px;height: 10px;" />&nbsp;&nbsp;一级保养计划台数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #98efe7; width: 10px;height: 10px;" />&nbsp;&nbsp;一级保养完成台数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #659ff3; width: 10px;height: 10px;" />&nbsp;&nbsp;二级保养计划台数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #f3cc65; width: 10px;height: 10px;" />&nbsp;&nbsp;二级保养完成台数
                  </div>
               </div>
            <div class="col-lg-5" style="width: 50%;height: 30px ;margin-top: 8px;">
	            <!-- 集团 -->
	            <c:if test="${type == '2'}">
	            	<input id="by_qyid" name="by_qyid" class="easyui-combobox" style="height: 30px;" 
	            		data-options="prompt: '企业列表', panelHeight: '150px', idField: 'id', valueField: 'id', textField: 'text',url: '${ctx}/bis/qyjbxx/idjson',
	            			onLoadSuccess: function(){
	            				var data = $('#by_qyid').combobox('getData');
								$('#by_qyid').combobox('select',data[0].id);
								$('#by_deptid').combobox('setValue', '');
								$('#by_deptid').combobox('reload', '${ctx}/system/department/deptjson3/'+data[0].id);
								
								countByBy();
	            			},
	            			onSelect: function(row) {
								$('#by_deptid').combobox('setValue', '');
								$('#by_deptid').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
							}
	            		" />
	            </c:if>
	            <input name="by_deptid" id="by_deptid" class="easyui-combobox" style="width: 150px;height: 30px;" 
						data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url: '${ctx}/system/department/deptjson',prompt: '使用单位' "/>
               <input type="text" id="by_starttime" name="by_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '日期'" />
		       <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search4()" ><i class="fa fa-search"></i> 查询</span>
			   <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset4()" ><i class="fa fa-refresh"></i> 全部</span> 
            </div>
            <div class="ibox-content" style="margin: 0 20px">
                  <div class="echart" id="main4" style="width:100%;height:400px"></div>
               </div>
            </div>
         </div>
     </div>
     <div title="其他统计" style="height:100%;" data-options="" id="">
         <div class="col-lg-12">
            <div class="box2" style="margin-bottom: 5px" id="yhpc_tj">
               <div class="title">
                  <h5>其他统计(默认当月)</h5>
               </div>
               <div class="col-lg-2" style="width: 50%;">
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #ad23bd; width: 10px;height: 10px;" />&nbsp;&nbsp;封存台数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #98efe7; width: 10px;height: 10px;" />&nbsp;&nbsp;故障次数
                  </div>
                  <div style="float: left;padding:12px;">
                     <img style="background-color: #659ff3; width: 10px;height: 10px;" />&nbsp;&nbsp;事故次数
                  </div>
               </div>
            <div class="col-lg-5" style="width: 50%;height: 30px ;margin-top: 8px;">
	            <!-- 集团 -->
	            <c:if test="${type == '2'}">
	            	<input id="qt_qyid" name="qt_qyid" class="easyui-combobox" style="height: 30px;" 
	            		data-options="prompt: '企业列表', panelHeight: '150px', idField: 'id', valueField: 'id', textField: 'text',url: '${ctx}/bis/qyjbxx/idjson',
	            			onLoadSuccess: function(){
	            				var data = $('#qt_qyid').combobox('getData');
								$('#qt_qyid').combobox('select',data[0].id);
								$('#qt_deptid').combobox('setValue', '');
								$('#qt_deptid').combobox('reload', '${ctx}/system/department/deptjson3/'+data[0].id);
								
								countByQt();
	            			},
	            			onSelect: function(row) {
								$('#qt_deptid').combobox('setValue', '');
								$('#qt_deptid').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
							}
	            		" />
	            </c:if>
	            <input name="qt_deptid" id="qt_deptid" class="easyui-combobox" style="width: 150px;height: 30px;" 
						data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url: '${ctx}/system/department/deptjson',prompt: '使用单位' "/>
               <input type="text" id="qt_starttime" name="qt_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '日期'" />
		       <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search5()" ><i class="fa fa-search"></i> 查询</span>
			   <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset5()" ><i class="fa fa-refresh"></i> 全部</span> 
            </div>
            <div class="ibox-content" style="margin: 0 20px">
                  <div class="echart" id="main5" style="width:100%;height:400px"></div>
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
   		initDateShow("dept_starttime");
   		initDateShow("by_starttime");
   		initDateShow("qt_starttime");
   });
	
//初始化年份-月份控件
function initDateShow(idName){
	var yjyf = $("#"+idName);
	yjyf.datebox({
	    onShowPanel: function () {//显示日期选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
	        span.trigger('click'); //触发click事件弹出月份层
	        if (p.find('div.calendar-menu').is(':hidden')) p.find('div.calendar-menu').show();
	        if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
	            tds = p.find('div.calendar-menu-month-inner td');
	            tds.click(function (e) {
	                e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
	                var year = /\d{4}/.exec(span.html())[0]//得到年份
	                , month = parseInt($(this).attr('abbr'), 10); //
	                yjyf.datebox('hidePanel')//隐藏日期对象
	                .datebox('setValue', year + '-' + month); //设置日期的值
	            });
	        }, 0);
	        yearIpt.unbind();//解绑年份输入框中任何事件
	    },
	    parser: function (s) {
	        if (!s) return new Date();
	        var arr = s.split('-');
	        return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
	    },
	    formatter: function (d) { 
	    	var dMonth = (d.getMonth() + 1);
	    	if (dMonth < 10) {
	    		dMonth = ("0"+dMonth);//1月到9月自动补0
	    	}
	    	return d.getFullYear() + '-' + dMonth; 
    	}
	});
	var p = yjyf.datebox('panel'), //日期选择对象
	    tds = false, //日期选择对象中月份
	    aToday = p.find('a.datebox-current'),
	    yearIpt = p.find('input.calendar-menu-year'),//年份输入框
	    //显示月份层的触发控件
	    span = aToday.length ? p.find('div.calendar-title span') :
	    p.find('span.calendar-text'); 
	if (aToday.length) {//重新绑定新事件设置日期框为今天，防止弹出日期选择面板
	   
	    aToday.unbind('click').click(function () {
	        var now=new Date();
	        yjyf.datebox('hidePanel').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
	    });
	}
	/* var now=new Date();
	yjyf.datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1)); */
}

   function countByDept(){
   		var qyid = "";
   		var deptid = "";
   		if ('${type}' == '2') {
   			qyid = $("#dept_qyid").combobox('getValue');
   			deptid = $("#dept_deptid").combobox('getValue');
   			console.log("deptid:"+ deptid)
   		}
		$.ajax({
			url: '${ctx}/sbssgl/tjfx/sbwhtj',
			type:'POST',
			data:{"starttime":$("#dept_starttime").datebox('getValue'), "qyid": qyid, "deptid": deptid},  
			dataType : "json",
			success : function(data) {
				loadEcharts3(data);
			}
		});	   
   }
   
   function countByBy(){
   		var qyid = "";
   		var deptid = "";
   		if ('${type}' == '2') {
   			qyid = $("#by_qyid").combobox('getValue');
   			deptid = $("#by_deptid").combobox('getValue');
   			console.log("deptid2:"+ deptid)
   		}
		$.ajax({
			url: '${ctx}/sbssgl/tjfx/sbbytj',
			type:'POST',
			data:{"starttime":$("#by_starttime").datebox('getValue'), "qyid": qyid, "deptid": deptid},  
			dataType : "json",
			success : function(data) {
				loadEcharts4(data);
			}
		});	   
   }
   
   function countByQt(){
   		var qyid = "";
   		var deptid = "";
   		if ('${type}' == '2') {
   			qyid = $("#qt_qyid").combobox('getValue');
   			deptid = $("#qt_deptid").combobox('getValue');
   			console.log("deptid3:"+ deptid)
   		}
		$.ajax({
			url: '${ctx}/sbssgl/tjfx/sbqttj',
			type:'POST',
			data:{"starttime":$("#qt_starttime").datebox('getValue'), "qyid": qyid, "deptid": deptid},  
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
			
	//设备完好状况
	function loadEcharts3(data) {
		require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
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
	                      		/* return value.split("").join("\n"); */
	                      		return value;
	                      	}	
				        }
			        }
			        
			    ],
			    grid: {x2: 180,y2: 100 },
			    yAxis : [
			        {
			        	name : '台数',
			            type : 'value'
			        },
			        {
			        	name : '百分比',
			            type : 'value',
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series : [
			        {
			            name:'全部设备台数',
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
			            data: data.sb_total_count
			        },
			        {
			            name:'主要设备台数',
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
			            data: data.mainsb_total_count	
			        },
			        {
			            name:'主要设备完好台数',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#659ff3',
         						label:{
         							show: true,//是否展示  
         							position:'top',
         							textStyle:{color:'#659ff3'},
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
			            data: data.mainsb_wh_count	
			        },
			        {
			            name:'主要设备完好率(%)',
			            type:'line',
			            itemStyle : {
      					normal : {
      							color: '#f3cc65',
         						label:{
         							show: true,//是否展示  
         							textStyle:{color:'#f3cc65'},
    								formatter: function(el){
    									if (el.value>0) {
    										return el.value+'%';
    									} else {
    										return '';
    									}
    								}
         						},
         				 	},
         				},
			            data: data.mainsb_wh_rate	
			        }
			    ]});
		});
	}
	
	//设备保养状况
	function loadEcharts4(data) {
		require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
			var myChart=document.getElementById("main4");
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
	                      		/* return value.split("").join("\n"); */
	                      		return value;
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
			            name:'一级保养计划台数',
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
			            data: data.fir_plan_count
			        },
			        {
			            name:'一级保养完成台数',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#98efe7',
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
			            data: data.fir_complete_count	
			        },
			        {
			            name:'二级保养计划台数',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#659ff3',
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
			            data: data.sec_plan_count	
			        },
			        {
			            name:'二级保养完成台数',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#f3cc65',
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
			            data: data.sec_complete_count	
			        }
			    ]});
		});
	}
	
	// 其他状况统计
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
			    xAxis : [
			        {
			        	name: '部门名称',
			            type : 'category',
			            data : data.deptname,
			            axisLabel: {
				        	interval:0,
	                      	formatter: function(value){
	                      		/* return value.split("").join("\n"); */
	                      		return value;
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
			            name:'封存台数',
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
			            data: data.fc_count
			        },
			        {
			            name:'故障次数',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#98efe7',
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
			            data: data.gz_count	
			        },
			        {
			            name:'事故次数',
			            type:'bar',
			            itemStyle : {
      					normal : {
      							color: '#659ff3',
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
			            data: data.sg_count	
			        }
			    ]});
		});
	}

	//创建查询对象并查询
	function search3() {
		countByDept();
	}

	//重置
	function reset3() {
		var data = $('#dept_qyid').combobox("getData");
		$('#dept_qyid').combobox("select", data[0].id);
		$('#dept_deptid').combobox("setValue", '');
		$('#dept_deptid').combobox("reload", '${ctx}/system/department/deptjson3/'+data[0].id);
		$('#dept_starttime').datebox("setValue", '');
		countByDept();
	}

	//创建查询对象并查询
	function search4() {
		countByBy();
	}

	//重置
	function reset4() {
		var data = $('#by_qyid').combobox("getData");
		$('#by_qyid').combobox("select", data[0].id);
		$('#by_deptid').combobox("setValue", '');
		$('#by_deptid').combobox("reload", '${ctx}/system/department/deptjson3/'+data[0].id);
		$('#by_starttime').datebox("setValue", '');
		countByBy();
	}
	
	//创建查询对象并查询
	function search5() {
		countByQt();
	}

	//重置
	function reset5() {
		var data = $('#qt_qyid').combobox("getData");
		$('#qt_qyid').combobox("select", data[0].id);
		$('#qt_deptid').combobox("setValue", '');
		$('#qt_deptid').combobox("reload", '${ctx}/system/department/deptjson3/'+data[0].id);
		$('#qt_starttime').datebox("setValue", '');
		countByQt();
	}
	
	function exportYdbb() {
		var qyid = $('#dept_qyid').combobox("getValue");
		var deptid = $('#dept_deptid').combobox("getValue");
		var starttime = $('#dept_starttime').datebox("getValue");
		
		if (qyid == null || qyid == '') {
			layer.msg('请选择企业', {time: 3000});
			return;
		}
		if (deptid == null || deptid == '') {
			layer.msg('请选择使用单位', {time: 3000});
			return;
		}
		if (starttime == null || starttime == '') {
			layer.msg('请选择日期', {time: 3000});
			return;
		}
		
		$.ajax({
			type: "POST",
			url: ctx+"/sbssgl/tjfx/export",
			data: {"qyid": qyid, "deptid": deptid, "starttime": starttime},
			success: function(data){
				window.open(ctx+data);
			}
		});
	}
</script>
</body>
</html>