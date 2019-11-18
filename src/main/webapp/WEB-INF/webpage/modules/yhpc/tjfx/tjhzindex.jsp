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
   <div class="box  padtop30">
      <div class="box2">
         <div class="title">
          <h5>企业隐患排查数据总览</h5>
         </div>
         <div class="padtop30">
            <div class="col-lg-6">
               <dl class="yhpctable col-lg-6">
                  <dt>
                     企业总数 <span class="f_blue fr" onclick="sjzl(1)">${qycount}</span>
                  </dt>
                  <dd>
                     已上线企业数 <span class="f_blue fr" onclick="sjzl(2)">${azqycount}</span>
                  </dd>
                  <dt>
                     本月已巡检企业数 <span class="f_blue fr" onclick="sjzl(3)">${xjqycount}</span>
                  </dt>
                  <dd>
                     本月未巡检企业数 <span class="f_blue fr" onclick="sjzl(4)">${wxjqycount}</span>
                  </dd>
               </dl>
               <dl class="yhpctable col-lg-6">
                  <dt>
                     本月检查记录数 <span class="f_blue fr" onclick="sjzl(5)">${jccount}</span>
                  </dt>
                  <dd>
                     本月检查隐患数 <span class="f_blue fr" onclick="sjzl(6)">${yhcount}</span>
                  </dd>
                  <dt>
                     本月未整改隐患数 <span class="f_blue fr" onclick="sjzl(7)">${wzgcount}</span>
                  </dt>
                  <dd>
                     本月已整改隐患数 <span class="f_blue fr" onclick="sjzl(8)">${yzgcount}</span>
                  </dd>
               </dl>
            </div>
            <div class="col-lg-6">
               <div class="col-lg-4" style="padding:0px">
                  <div class="echart" id="main" style="width:100%;height:200px"></div>
               </div>
               <div class="col-lg-4" style="padding:0px">
                  <div class="echart" id="main2" style="width:100%;height:200px"></div>
               </div>
               <div class="col-lg-4" style="padding:0px">
                  <div class="echart" id="main3" style="width:100%;height:200px"></div>
               </div>
            </div>
         </div>
      </div>
      <div class="yhpc2">
         <div class="col-lg-6">
            <div class="box2" id="yhpc_tj">
               <div class="title">
                  <h5>统计隐患数目</h5>
               </div>
               <div class="col-lg-7">
                  <div style="float: left;padding:15px;margin-right:10px ">
                     <img style="background-color: #2894FF; width: 10px;height: 10px;" />&nbsp;&nbsp;隐患总数
                  </div>
                  <div style="float: left;padding:15px;margin-right:10px">
                     <img style="background-color: #f8692d; width: 10px;height: 10px;" />&nbsp;&nbsp;未整改隐患数
                  </div>
                  <div style="float: left;padding:15px;">
                     <img style="background-color: #71bd65; width: 10px;height: 10px;" />&nbsp;&nbsp;已整改隐患数
                  </div>
               </div>
               <div class="col-lg-5"> <div class="btn-group" style="height: 40px;margin-left: 20px;margin-top: 10px">
                     <button type="button" class="btn btn-white" onclick="getDayCount('day')"> &nbsp;天&nbsp;   </button>
                     <button type="button" class="btn btn-white" onclick="getDayCount('month')"> &nbsp;月&nbsp;   </button>
                     <button type="button" class="btn btn-white" onclick="getDayCount('year')"> &nbsp;年&nbsp;   </button>
                  </div></div>
               <div class="ibox-content" style="margin: 0 20px">
                  <div class="echart" id="main4" style="width:100%;height:400px"></div>
               </div>
            </div>
         </div>
         <div class="col-lg-6">
            <div class="box2 mrgl10">
               <div>
                  <div class="col-lg-6 tip tipcurrent" id="tip1" onclick="getDqXjCount('xjbl')">本月各地区巡检率统计</div>
                  <div class="col-lg-6 tip" id="tip2" onclick="getDqXjCount('zgbl')">本月各地区隐患整改完成率统计</div>
               </div>
               <div id="tipbox1">
                  <div class="ibox-content" id="ibox-content1" style="margin: 10px">
                  <div class="echart" id="main5" style="width:100%;height:442px"></div>
               </div>
               </div>
               <div id="tipbox2">
               <div class="ibox-content" id="ibox-content2" style="margin: 10px">
                  <div class="echart" id="main6" style="width:100%;height:442px"></div>
               </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <script>
   var daycount=eval(${daycount});
   $(function(){
   		//计算部署率
   		var bsl=((${azqycount})/(${qycount})*100).toFixed(2);
   		var wbsl=(100-bsl).toFixed(2);
   		var data=[bsl,wbsl];
   		
   		//计算巡检检查率
   		var xjl=((${xjqycount})/(${qycount})*100).toFixed(2);
   		var wxjl=(100-xjl).toFixed(2);
   		var data2=[xjl,wxjl];   	
   		
   		//计算整改率
   		var zgl=0;
   		if(${yzgcount}==0&&${wzgcount}==0){
   			zgl=100;
   		}else{
   			zgl=((${yzgcount})/(${yzgcount}+${wzgcount})*100).toFixed(2);
   		}
		var wzgl=(100-zgl).toFixed(2);
   		var data3=[zgl,wzgl]; 	
   		
		loadEcharts('main', '企业上线率', 0,data);
		loadEcharts('main2', '巡检检查率', 1,data2);
		loadEcharts('main3', '隐患整改率', 2,data3);
		getDayCount("day");
		getDqXjCount("xjbl");
		//loadEcharts2('main5');
   });
   
   function getDayCount(flag){
   			$.ajax({
				type:'get',
				url: '${ctx}/yhpc/tjfx/daycount?flag='+flag,
				success : function(data) {
				    loadEcharts2('main4',flag,JSON.parse(data));
				}
			});
   }
   
   function getDqXjCount(flag){
   			$.ajax({
				type:'get',
				url: '${ctx}/yhpc/tjfx/dqxjbl?flag='+flag,
				success : function(data) {
				data=eval(data);
				var xdata=[];
				var ydata=[];
				for(var i=0;i<data.length;i++){
				xdata.push(data[i][0]);
				ydata.push(data[i][1]);
				}
				   if(flag=='xjbl'){
				   		yhtip(1,xdata,ydata);
				   }else{
				   		yhtip(2,xdata,ydata);
				   }
				}
			});
   }
   
   				$("#tipbox2").hide();
				function yhtip(m,xdata,ydata) {
					if(m==1){
						$("#main5").width($("#yhpc_tj").width());
						loadEcharts3('main5','1',xdata,ydata);
					}
					else if(m==2){
						$("#main6").width($("#yhpc_tj").width());
						loadEcharts3('main6','2',xdata,ydata);
					}
						
					$("#tipbox1").hide();
					$("#tipbox2").hide();
					$("#tipbox" + m + "").show();
					$(".tip").removeClass("tipcurrent");
					$("#tip" + m + "").addClass("tipcurrent");
				}

				require.config({
					paths : {
						echarts : '${ctxStatic}/echarts'
					}
				});
			
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
							edata.push({name : '已上线',value : data[0],itemStyle:labelTop});
							edata.push({name : '未上线',value : data[1],itemStyle:labelBottom});
						}else if(index==1){
							edata.push({name : '已巡检',value : data[0],itemStyle:labelTop});
							edata.push({name : '未巡检',value : data[1],itemStyle:labelBottom});
						}else if(index==2){
							edata.push({name : '已整改',value : data[0],itemStyle:labelTop});
							edata.push({name : '未整改',value : data[1],itemStyle:labelBottom});
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
				
				function loadEcharts2(div,flag,daycount) {
					require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
						var myChart = ec.init(document.getElementById(div));
						var now = new Date(); 
						var data=[];
						if(flag=='day'){
						 	var day = now.getDate();
						 	now.setDate(now.getDate() - 1);
						 	var day2 = now.getDate();
						 	now.setDate(now.getDate() - 1);
						 	var day3 = now.getDate();
							data=[day3+'日',day2+'日',day+'日'];
						}else if(flag=='month'){
						 	var month = now.getMonth()+1;
						 	now.setMonth(now.getMonth() - 1);
						 	var month2 = now.getMonth()+1;
						 	now.setMonth(now.getMonth() - 1);
						 	var month3 = now.getMonth()+1; 
							data=[month3+'月',month2+'月',month+'月'];
						}else if(flag=='year'){
						 	var year = now.getFullYear();
							data=[year-2+'年',year-1+'年',year+'年'];
						}
						myChart.setOption({
							tooltip : {
								trigger : 'axis'
							},
							calculable : true,
							xAxis : [
							{
								type : 'category',
								data : data 
							} ],
							yAxis : [ {
								type : 'value'
							} ],
							series : [ {
								name : '隐患总数',
								type : 'bar',
								itemStyle : {
									normal : {
										color : '#2894FF',
										label:{
			            				show: true,//是否展示  
			            				position:'top',
			            				textStyle:{color:'#5690D2'},
			            				formatter : '{c}'
			            			},
								  },
								},
								barWidth : 10,//柱图宽度
								barCategoryGap : '70%',
								data : daycount.yhzs,
							}, {
								name : '未整改隐患数',
								type : 'bar',
								barWidth : 10,//柱图宽度
								barCategoryGap : '70%',
								itemStyle : {
									normal : {
										color : '#f8692d',
										label:{
			            				show: true,//是否展示  
			            				position:'top',
			            				textStyle:{color:'#5690D2'},
			            				formatter : '{c}'
			            			},
								  },
								},
								data : daycount.wzgs,
							},
							{
								name : '已整改隐患数',
								type : 'bar',
								barWidth : 10,//柱图宽度
								barCategoryGap : '70%',
								itemStyle : {
									normal : {
										color : '#71bd65',
										label:{
			            				show: true,//是否展示  
			            				position:'top',
			            				textStyle:{color:'#5690D2'},
			            				formatter : '{c}'
			            			},
								  },
								},
								data : daycount.yzgs,
							}
							]
						});
					});
				}
				
				function loadEcharts3(div,flag,xdata,ydata) {
					if(flag==1){
						name='巡检率(已巡检企业数/安装企业数)';
					}else{
						name='整改率(整改完成数/隐患数)';
					}
					require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
						var myChart = ec.init(document.getElementById(div));
						myChart.setOption({
							tooltip : {
								trigger : 'axis'
							},
							calculable : true,
							xAxis : [

							{
								type : 'category',
								data : xdata,
								name:'百分比',
							axisLabel:{  
                         		interval:0,//横轴信息全部显示  
                         		formatter:function(value)  
                               {  
                                   return value.split("").join("\n");
                               }    
                         		
                    		} 
							} ],
							yAxis : [ {
								type : 'value',
								name:name 
							} ],
							series : [ {
								name : '巡检率',
								type : 'bar',
							 	itemStyle : {
									normal : {
										color : '#2894FF',
										label:{
			            				show: true,//是否展示  
			            				position:'top',
			            				textStyle:{color:'#5690D2'},
			            				formatter : '{c}%'
			            			},
								  },
								}, 
								barWidth : 10,//柱图宽度
								barCategoryGap : '70%',
								data : ydata,
							}
							
							]
						});
					});
				}

//查看数据总览list
function sjzl(type){
	var name="";
	if(type==1)
		name="企业列表";
	if(type==2)
		name="已上线企业表";
	if(type==3)
		name="本月已巡检企业表";
	if(type==4)
		name="本月未巡检企业表";	
	if(type==5)
		name="本月检查记录表";
	if(type==6)
		name="本月检查隐患表";
	if(type==7)
		name="本月未整改隐患表";
	if(type==8)
		name="本月已整改隐患表";
	openDialogView(name,ctx+"/yhpc/tjfx/sjzl?type="+type,"90%", "90%","");
}
</script>
</body>
</html>