<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>Home</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/model/css/home/styles.css?v=1.0" rel="stylesheet" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<style type="text/css">
body {background-color:rgba(0,0,0,0)}
.BMap_cpyCtrl{ display:none; }  
.anchorBL{ display:none;}   
</style>
</head>
<body>
	<div class="line1">
		<div class="percent20 pull-left">
			<div class="box">
				<div class="iconbox">
					<img src="${ctxStatic}/model/images/home/dbsx.png">
					<div class="label label-info">待办事项</div>
				</div>
				<dl class="textlist" id="icontext1">
					<dd  onclick="viewInfor('/system/message/index','dsh')">Review Pending<span class="afont" id="dsh">0</span></dd>
					<dd  onclick="viewInfor('/system/message/index','dpz')">Approval Pending<span class="afont" id="dpz">0</span></dd>
					<dd  onclick="viewInfor('/system/message/index','djc')">Check Pending<span class="afont" id="djc">0</span></dd>
				</dl>
			</div>
		</div>
		<div class="percent20 pull-left">
			<div class="box">
				<div class="iconbox">
					<img src="${ctxStatic}/model/images/home/zsdq.png">
					<div class="label label-success">证书到期</div>
				</div>
				<dl class="textlist" id="icontext2">
					<dd onclick="viewInfor('/bis/aqpxxx/index','expiration')">Safety Certificates<span class="afont" id="aqglzs">0</span></dd>
					<dd onclick="viewInfor()">Special Job<span class="afont" id="tzzy">0</span></dd>
					<dd onclick="viewInfor()">Special Equipment Working<span class="afont" id="tzsbzy">0</span></dd>
				</dl>
			</div>
		</div>
		<div class="percent20 pull-left">
			<div class="box">
				<div class="iconbox">
					<img src="${ctxStatic}/model/images/home/jcdq.png">
					<div class="label label-primary">检测到期</div>
				</div>
				<dl class="textlist" id="icontext3">
					<dd onclick="viewInfor('/bis/tzsbxx/index','expiration')">Special Equipment<span class="afont" id="tzsb">0</span></dd>
					<dd onclick="viewInfor('/bis/aqss/index','expiration')">Safety Facility<span class="afont" id="aqss">0</span></dd>
					<dd onclick="viewInfor('/bis/occupharmreport/index','expiration')">Occupational Hazardous Factors<span class="afont" id="zybwh">0</span></dd>
					<dd onclick="viewInfor()">Occupational Health Physical<span class="afont" id="tjdq">0</span></dd>
				</dl>
			</div>
		</div>
		<div class="percent20 pull-left">
			<div class="box">
				<div class="iconbox">
					<img src="${ctxStatic}/model/images/home/wjgx.png">
					<div class="label label-danger">文件更新</div>
				</div>
				<dl class="textlist" id="icontext4">
					<dd onclick="viewInfor('/zdgl/flfg/index')">Laws<span class="afont" id="fg">0</span></dd>
					<dd onclick="viewInfor('/zdgl/glzd/index')">Regulations<span class="afont" id="zd">0</span></dd>
					<dd onclick="viewInfor('/zdgl/wjfb/index')">Documentations<span class="afont" id="wj">0</span></dd>
					<dd onclick="viewInfor('/fxpg/zdfx/index')">Risk List<span class="afont" id="fxqd">0</span></dd>
				</dl>
			</div>
		</div>
		<div class="percent20 pull-left">
			<div class="box">
				<div class="iconbox">
					<img src="${ctxStatic}/model/images/home/lbyp.png">
					<div class="label label-warning">劳保用品</div>
				</div>
				<dl class="textlist" id="icontext5">
					<dd onclick="viewInfor('/lbyp/lssq/reviewindex','2')">Review Pending For Me <span class="afont" id="lssq">0</span></dd>
					<dd onclick="viewInfor('/lbyp/ffgl/list','lbyp')">PPE Distribution Reminder<span class="afont" id="lytx">0</span></dd>
				</dl>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	
	<!--第三行-->
	<div class="container line3">
		<div class="col-lg-8 col-md-8">
			<div class="box">
			<div class="title">Enterprise Satellite Map  <div class="pull-right" style="margin-right: 15px;"> <span class="btn btn-info btn-xs" onclick="viewInfor('/zxjkyj/spjk/index')"> 视频监控</span>  <span class="btn btn-info btn-xs" onclick="viewInfor('/yhpc/bcrw/syxjdindex')">巡检点</span>  </div></div>
			<div id="qymapContent" style="height: 500px;"> </div>
			</div>
		</div>
		<div class="col-lg-4 col-md-4">
				<div class="box">
					<div class="title">
						Risk Data
						<a class="pull-right" href="javascript:void(0);" onclick="viewXjdzt('${xjlist.id }')">Details</a>
					</div> 
					<dl>
						<dd class="locaction"  onclick=" ">
							Safety inspection point：<span class="f_orange">${xjlist.xjds }</span>
						</dd>
						<dd onclick=" ">
							Has Been Inspected current month：<span class="f_green">${xjlist.yxjds }</span><br>
							Has Not Been Inspected current month：<span class="f_red">${xjlist.wxjds}</span>
						</dd>
						<dd class="dangernum" onclick=" ">
							Risk total：<span class="f_red">${xjlist.yhs}</span>
						</dd>
						<dd onclick=" ">
							Has Been Rectified current month：<span class="f_green">${xjlist.zgyhs}</span><br>
							Has Not Been Rectified current month：<span class="f_red">${xjlist.yhs - xjlist.zgyhs}</span>
						</dd>
					</dl>
					<div style="text-align: center;height: 255px;">
						<div class="echart" id="main" style="width:45%;height:200px;float:left"></div>
						<div class="echart" id="main2" style="width:45%;height:200px;float:right"></div>
					</div>
				</div>
		</div>
	</div>
	
	<!--第二行-->
	<div class="container line2">
		<div class="col-lg-6 col-md-6">
			<div class="box">
				<div class="news">
					<div class="title">
						Dynamic Notification Of The Latest Company
						<a class="pull-right" onclick="viewInfor('/target/aqdt/index')">More</a>
					</div>
					<dl id="wjtz">
						 
					</dl>
				</div>
			</div>
		</div>
		<div class="col-lg-6 col-md-6">
			 <div class="box">
			 <div class="title"> Risk Early-warning Radar Map</div>
			 	<div style="height: 300px;text-align: center;">
			 	<div class="echart" id="main4" style="width:45%;height:300px;float:left"></div>
			 	<div class="echart" id="main5" style="width:45%;height:300px;float:right"></div>
			 	</div>
				
			 </div>
		</div>
	</div>
   <div id="hiddentable" style="display:none;height:100%"  >
      <table id="target_dg"></table> 
   </div>	
  <script>
  var P_HeaderType;
  $(function(){
  	  var yzg=${xjlist.zgyhs};//已整改
	  var wzg=${xjlist.yhs - xjlist.zgyhs};//未整改
	  var zgl=(yzg*100/(yzg+wzg)).toFixed(2); 
	  var wzgl=(wzg*100/(yzg+wzg)).toFixed(2); 
	  var data=[zgl,wzgl]; 
	  
	  var yxj=${xjlist.yxjds };//已巡检
	  var wxj=${xjlist.wxjds};//未巡检
	  var yxjl=(yxj*100/(yxj+wxj)).toFixed(2); 
	  var wxjl=(wxj*100/(yxj+wxj)).toFixed(2); 
	  var data2=[yxjl,wxjl];   		
  	  //加载图表
	  loadEcharts('main', 'Close rate for risk findings', 0,data);
	  loadEcharts('main2', 'Inspection rate', 1,data2);
	  
	  radar();
	  initMap("qymapContent","${company.m16}","${company.m17}",20);
	  var map=getMap();
	  var markerc = new BMap.Marker(new BMap.Point("${company.m16}","${company.m17}"));  
      map.addOverlay(markerc);  
	  markerc.setAnimation(BMAP_ANIMATION_BOUNCE); 
	  
	  
	//获取最新文件信息
	$.ajax({
		url : ctx + '/target/aqdt/topfour',
		type : "POST",
		dataType: 'json', 
		success : function(data) {
			var html="";
			for (var i = 0; i < data.length&&i<3; i++) {
				var date=new Date(data[i].S2).format("yyyy-MM-dd hh:mm:ss");
				var css=  i%2==0 ?'border1' :'border2' ;
				html+= '<dd class="'+css+'"  onclick="openwj('+data[i].ID+')">'+
				'<div><b>'+abbr(data[i].m1,80)+'</b>...</div>'+
				'<small>更新时间：'+date+'</small></dd>';
			}
			$("#wjtz").append(html);
		}
	});
	
	
	//获取集团公司和子公司集合
	$.ajax({
		url : ctx + '/bis/qyjbxx/idjson',
		type : "POST",
		dataType: 'json', 
		success : function(data) {
			var html="";
			for (var i = 0; i < data.length&&i<4; i++) {
				html+= '<dd  onclick="viewQyTab('+data[i].id+')">'+
				data[i].text+'</dd>';
			}
			$("#company_list").append(html);
		}
	});
	
	$.ajax({
		url : ctx + '/syshome/qysjzs',
		type : "POST",
		success : function(data) {
			for(var key in data){
				$("#"+key).text(data[key]);
			}
		}
	});
	
  });


function openwj(id){
	openDialogView("安全动态信息查看","${ctx}/target/aqdt//view/"+id,"80%", "80%","");
}

//查看子公司档案
function viewQyTab(id){
	openDialogView("查看企业档案信息","${ctx}/bis/qyjbxx/view/"+id,"90%", "90%",""); 	
}

//查看巡检点状态
function viewXjdzt(id){
	window.wqyname="";
	layer.open({
	    type: 2, 
	    area: ["95%", "95%"],
		offset: '10px',
	    title: " ",
        maxmin: true, 
	    content: "${ctx}/yhpc/xjdzt/index2?qyid=" + id,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	}); 
}

//待办提醒数字点击查看
function viewInfor(url,type){
    if(type=='lbyp'){
   	layer.open({
    	    type: 1,  
    	    title: '信息详情',
    	    area:['700px','400px'],
    	    content: $("#hiddentable"),
    	    btn: ['关闭'],
    	    success: function(index,layero){
    	      	var dg=$("#target_dg").datagrid({    
    	    		method: "post",
    	    		url  : ctx+url,
    	    		queryParams :{/* 'uid': '1', */"expiration":1,},
             	    fit : true,
             		fitColumns : true,
             		border : false,
             		striped:true,
             		pagination:true,
             		rownumbers:true,
             		nowrap:false,
             		pageNumber:1,
             		pageSize : 50,
             		pageList : [ 50, 100, 150, 200, 250 ],
             		scrollbarSize:0,
             		singleSelect:true,
             		striped:true,
    	    		columns:[[    
                           {field:'jobtype',title:'岗位/工种名称',sortable:false,width:50},
                           {field:'ename',title:'员工姓名',sortable:false,width:50},    
                           {field:'goodsname',title:'用品名称',sortable:false,width:40},
                           {field:'amount',title:'数量',sortable:false,width:20},
                           {field:'lasttime',title:'最近领取日期',sortable:false,width:40, formatter : function (value, row){
                           	if(value) return new Date(value).format("yyyy-MM-dd");
                           	else return '';
                           }},    
                           {field:'nexttime',title:'下次领取日期',sortable:false,width:40, formatter : function (value, row){
                           	var d = getNextDate(row);
                           	if(d) return d;
                           },
                           styler: function(value, row, index){
                           	var d = getNextDate(row);
                           		var now=new Date().format("yyyy-MM-dd");
                           		if(now>=d){
                           			return 'background-color:rgb(249, 156, 140);';
                           		}
                           	}} 
    		 		    		]],
    		 		    onLoadSuccess: function(){dg.datagrid("autoMergeCells",['year','quarter','department']);}
    	    		});
    	    },
    	    cancel: function(index){}
    	}); 
    }else{
		P_HeaderType=type;
		if(url)
     		openDialogView("信息查看",'${ctx}'+url,"90%", "70%","");
    }
}
function getNextDate(row){
	if(row.lasttime) {
		var d= new Date(row.lasttime);
		if(row.cycleyear)
			d.setFullYear(d.getFullYear()+parseInt(row.cycleyear));
		if(row.cyclemonth)
			d.setMonth(d.getMonth()+parseInt(row.cyclemonth));
		return d.format("yyyy-MM-dd");
	}
	else return null;
}

 
function openPage(url,title,mflag){
	url='${ctx}'+url+"?sys=1";
	if(mflag!=null&&mflag!='')
		url+="&mflag="+mflag;
	layer.open({
	    type: 2,  
	    area: ['100%', '100%'],
	    title: title,
      maxmin: true, 
	    content: url,
	    btn: ['关闭'],
	    yes: function(index, layero){	
	    	layer.close(index);
			},
	cancel: function(index){}
	});
}

require.config({
	paths : {
		echarts : '${ctxStatic}/echarts'
	}
});

//图表加载
function loadEcharts(div, title, index,data) {
	require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
		var myChart = ec.init(document.getElementById(div));
		var lemphasis = {//悬浮样式
			label : {
				show : true,
				position : 'center',
				formatter : '{d}%',
				textStyle : {
					color : 'black',
					fontSize : '15',
				}
			}
		};
		var labelTop = {//上层样式（“是”）样式
			normal : {
				color : function() {
					// build a color map as your need.
					var colorList = [ '#e069bc', '#fe7751', '#02c994' ];
					return colorList[index]
				},
				label : {
					show : true,
					position : 'outer',
					textStyle : {
						color : 'black'
					},
					formatter : '{b}\n{c}%'
				},
				labelLine : {
					show : true,
					length : '1',
					lineStyle : {
						color : '#cdcdcd'
					}
				},
			},
			emphasis : lemphasis
		};
		var labelBottom = {//底层样式（"否"）样式
			normal : {
				color : '#cccccc',
				label : {
					show : true,
					textStyle : {
						color : 'black'
					},
					formatter : '{b}\n{c}%'
				},
				labelLine : {
					show : true,
					length : '1',
					lineStyle : {
						color : '#cccccc'
					}
				},
			},
			emphasis : lemphasis

		};
		var edata=[];
		if(index==0){
			edata.push({name : '已整改',value : data[0],itemStyle:labelTop});
			edata.push({name : '未整改',value : data[1],itemStyle:labelBottom});
		}else if(index==1){
			edata.push({name : '已巡检',value : data[0],itemStyle:labelTop});
			edata.push({name : '未巡检',value : data[1],itemStyle:labelBottom});
		}
		myChart.setOption({
			title : {
				text : title,
				y : 'bottom',
				x : 'center',
				textStyle : {
					fontSize : 17,
					fontFamily : 'Arial',
					fontWeight : 100,
				},
			},
			series : [ {
				type : 'pie',
				radius : [ '35%', '45%' ],
				center : [ '50%', '35%' ],
				data : edata
			} ]
		});
	});
}

//雷达图数据
function radar(){
	$.ajax({
		url : ctx + '/fxgk/tjfx/FXDjSg2',
		type : "POST",
		dataType: 'json', 
		success : function(data) {
			require([ 'echarts', 'echarts/chart/radar' ], function(ec) {
				var myChart = ec.init(document.getElementById("main4"));
				myChart.setOption({
				tooltip : {
	        		trigger: 'axis'
	    		},
	    		calculable : true,
			    polar : [
			        {
			            indicator : data.shigu,
			            radius : 95
			        }
			    ],
			    series : [
			        {
			            type: 'radar',
			            itemStyle: {
			                normal: {
			                    areaStyle: {
			                        type: 'default'
			                    }
			                }
			            },
			            data : [
			                {
			                    value : data.num,
			                  	name:'风险次数'
			                }
			            ]
			        }
			    ]
				});
			});
		}
	})
	
	require([ 'echarts', 'echarts/chart/radar' ], function(ec) {
		var myChart = ec.init(document.getElementById("main5"));
		myChart.setOption({
			tooltip : {
        		trigger: 'axis'
    		},
    		calculable : true,
		    polar : [
		        {
		            indicator : [
                        { text: '车间', max: 30},
                        { text: '物料', max: 30},
                        { text: '仓库', max: 30},
                        { text: '危险作业', max: 30},
                        { text: '生产作业', max: 30},
                        { text: '外来作业', max: 30},
                        { text: '安全管理', max: 30},
                        { text: '职业病危害', max: 30}
		            ],
		            radius : 95
		        }
		    ],
		    series : [
		        {
		            type: 'radar',
		            itemStyle: {
		                normal: {
		                    areaStyle: {
		                        type: 'default'
		                    }
		                }
		            },
		            data : [
		                {
		                	value : [10, 15, 15, 8, 15, 17,20,30],
		                  	name:'风险次数'
		                }
		            ]
		        }
		    ]
		});
	});	
}

  </script>
 </body>
</html>