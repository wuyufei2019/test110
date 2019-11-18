<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/model/css/home/styles.css?v=1.0" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/model/js/home/jquery.liMarquee.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<style type="text/css">
body {background-color:rgba(0,0,0,0)}
.BMap_cpyCtrl{ display:none; }  
.anchorBL{ display:none;}   
.txtsize{padding:20px;}
</style>
</head>
<body>
	<div class="line1" style="margin:10px 0px 0px 20px ">
		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="selmenu()" title="自定义界面"><i class="fa fa-external-link"></i> 自定义界面</button> 
	</div>
	<div class="line1">
		<div class="percent20 pull-left">
			<div class="box">
				<div class="iconbox">
					<img src="${ctxStatic}/model/images/home/dbsx.png">
					<div class="label label-info">待办事项</div>
				</div>
				<dl class="textlist" id="icontext3">
					<dd  onclick="viewInfor('/system/message/index?msgtype=dsh','dsh')">待审核 <span class="afont" id="dsh">0</span></dd>
					<dd  onclick="viewInfor('/system/message/index','djc')">待检查 <span class="afont" id="djc">0</span></dd>
					<dd>安全培训任务 <span class="afont" >${study }</span></dd>
					<dd onclick="viewInfor('/system/message/index?msgtype=dzg','dzg')">隐患处理  <span class="afont" id="dzg">0</span></dd>
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
					<dd onclick="viewInfor('/bis/aqpxxx/index','expiration')">安全管理证书 <span class="afont" id="aqglzs">0</span></dd>
					<dd onclick="viewInfor()">特种作业 <span class="afont" id="tzzy">0</span></dd>
					<dd onclick="viewInfor()">特种设备作业 <span class="afont" id="tzsbzy">0</span></dd>
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
					<dd onclick="viewInfor('/bis/tzsbxx/index','expiration')">特种设备 <span class="afont" id="tzsb">0</span></dd>
					<dd onclick="viewInfor('/bis/aqss/index','expiration')">安全设施 <span class="afont" id="aqss">0</span></dd>
					<dd onclick="viewInfor('/bis/occupharmreport/index','expiration')">职业病危害因素 <span class="afont" id="zybwh">0</span></dd>
					<dd onclick="viewInfor()">体检到期 <span class="afont" id="tjdq">0</span></dd>
				</dl>
			</div>
		</div>
		<%-- <div class="percent20 pull-left">
			<div class="box">
				<div class="iconbox">
					<img src="${ctxStatic}/model/images/home/wjgx.png">
					<div class="label label-danger">文件更新</div>
				</div>
				<dl class="textlist" id="icontext4">
					<dd onclick="viewInfor('/zdgl/flfg/index')">法规 <span class="afont" id="fg">0</span></dd>
					<dd onclick="viewInfor('/zdgl/glzd/index')">制度 <span class="afont" id="zd">0</span></dd>
					<dd onclick="viewInfor('/zdgl/wjfb/index')">文件 <span class="afont" id="wj">0</span></dd>
					<dd onclick="viewInfor('/fxpg/zdfx/index')">风险清单 <span class="afont" id="fxqd">0</span></dd>
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
					<dd onclick="viewInfor('/lbyp/lssq/reviewindex','2')">待我审核 <span class="afont" id="lssq">0</span></dd>
					<dd onclick="viewInfor('/lbyp/ffgl/list','lbyp')">领用提醒 <span class="afont" id="lytx">0</span></dd>
				</dl>
			</div>
		</div> --%>
		<div class="clearfix"></div>
	</div>
	
	<!--第三行-->
	
	<div class="container line3" >
		<c:forTokens items="${userdefine.menuid}" delims="," var="menu" varStatus="menus">
			<c:if test="${menu==0}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					<div class="box" style="height: 481px">
					<div class="title">安全生产禁令 <div class="pull-right" style="margin-right: 15px;"> </div></div>
						<div style="height: 420px;padding:00px 60px 0px 60px; line-height: 30px">
							<h2 style="text-align: center; margin-bottom: 15px">安全生产禁令</h2>
							<font size="3" ><b><font color="#417FB8" >一、</font></b>严禁装置生产区域吸烟。</font><br/>
							<font size="3"><b><font color="#417FB8" >二、</font></b>严禁登高作业不系挂安全带。</font><br/>
							<font size="3"><b><font color="#417FB8" >三、</font></b>严禁无作业许可证进行相关作业。</font><br/>
							<font size="3"><b><font color="#417FB8" >四、</font></b>严禁工作岗位违反劳动纪律睡岗。</font><br/>
							<font size="3"><b><font color="#417FB8" >五、</font></b>严禁防爆装置区使用非防爆手机。</font><br/>
						</div>
					</div>
				</div>
			</c:if>		
			
			<c:if test="${menu==1}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					<div class="box" style="height: 481px">
					<div class="title">企业卫星图  <div class="pull-right" style="margin-right: 15px;"> <span class="btn btn-info btn-xs" onclick="viewInfor('/zxjkyj/spjk/index')"> 视频监控</span>  <span class="btn btn-info btn-xs" onclick="viewInfor('/yhpc/bcrw/syxjdindex')">巡检点</span>  </div></div>
					<div id="qymapContent" style="height: 420px;"> </div>
					</div>
				</div>
			</c:if>
			
			<c:if test="${menu==2}">
				<div class="col-lg-6 col-md-6" style="height:500px">
						<div class="box" style="height: 481px">
							<div class="title">
								隐患排查数据
								<a class="pull-right" href="javascript:void(0);" onclick="viewXjdzt('${xjlist.id }')">详情</a>
							</div> 
							<dl>
								<dd class="locaction"  onclick=" ">
									巡检点数：<span class="f_orange">${xjlist.xjds }</span>
								</dd>
								<dd onclick=" ">
									本月已巡检：<span class="f_green">${xjlist.yxjds }</span>&nbsp;&nbsp;
									本月未巡检：<span class="f_red">${xjlist.wxjds}</span>
								</dd>
								<dd class="dangernum" onclick=" ">
									隐患总数：<span class="f_red">${xjlist.yhs}</span>
								</dd>
								<dd onclick=" ">
									本月已整改：<span class="f_green">${xjlist.zgyhs}</span>&nbsp;&nbsp;
									本月未整改：<span class="f_red">${xjlist.yhs - xjlist.zgyhs}</span>
								</dd>
							</dl>
							<div style="text-align: center;height: 196px;">
								<div class="echart" id="main" style="width:45%;height:190px;float:left"></div>
								<div class="echart" id="main2" style="width:45%;height:190px;float:right"></div>
							</div>
						</div>
				</div>			
			</c:if>
			
			<c:if test="${menu==3}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					<div class="box " style="height: 481px">
						<div class="news">
							<div class="title">
								最新公司动态通知
								<a class="pull-right" onclick="viewInfor('/target/aqdt/index')">更多</a>
							</div>
							<dl36 id="wjtz">
								 
							</dl36>
						</div>
					</div>
				</div>
			</c:if>
			
			<c:if test="${menu==4}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					 <div class="box">
					 <div class="title"> 风险预警雷达图 </div>
					 	<div style="height: 440px;text-align: center;">
					 	<div class="echart" id="main4" style="width:50%;height:300px;float:left"></div>
					 	<div class="echart" id="main5" style="width:50%;height:300px;float:right"></div>
					 	</div>
						
					 </div>
				</div>
			</c:if>
			
			<c:if test="${menu==5}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					 <div class="box">
					 <div class="title"> 安全积分 </div>
						 <div style="height: 440px;text-align: center;">
							<table id="target_jfgl_dg"></table>					 
						 </div>	
					 </div>
				</div>
			   <div id="select_dg" style="display:none;height:100%">
			      <table id="target_dg"></table>
			   </div>				
			</c:if>	
			
			<c:if test="${menu==6}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					 <div class="box">
					 <div class="title"> 安全动态 </div>
						 <div style="height: 440px;text-align: center;">
							<table id="target_aqdt_dg"></table>			 
						 </div>	
					 </div>
				</div>
			</c:if>	
			
			<c:if test="${menu==7}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					 <div class="box">
					 <div class="title"> 消防应急  </div>
			        <div class="dowebok bgw" style="height: 440px;">
			            <ul class="clearpd" id="bjxx">
			            </ul>
			        </div>
					 </div>
				</div>			
			</c:if>			
			
			<c:if test="${menu==8}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					 <div class="box">
					 <div class="title"> 危险作业 </div>
				         <div style="height: 440px;text-align: center;">
				            <table id="dw_zysp_dg"></table> 
				        </div>
					 </div>
				</div>
			</c:if>												
		</c:forTokens>
		
	</div>
	
   <div id="hiddentable" style="display:none;height:100%"  >
	   <table id="target_jfgl_dg"></table>
	   <div id="select_dg" style="display:none;height:100%">
	      <table id="dw_zysp_dg"></table> 
	   </div>
   </div>	
  <script>
  var dg3;
  var selmenuid='${userdefine.menuid}';
  var arr=selmenuid.split(",");
  var P_HeaderType;
  var usertype='${usertype}';
  
  $(function(){
	  //卫星图
	  if(arr.indexOf('1')>-1){
		  initMap("qymapContent","${company.m16}","${company.m17}",20);
		  var map=getMap();
		  var markerc = new BMap.Marker(new BMap.Point("${company.m16}","${company.m17}"));  
	      map.addOverlay(markerc);  
		  markerc.setAnimation(BMAP_ANIMATION_BOUNCE); 
	  }
	  
	  //隐患排查数据
	  if(arr.indexOf('2')>-1){
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
		  loadEcharts('main', '隐患整改率', 0,data);
		  loadEcharts('main2', '巡检检查率', 1,data2);
	  }

	  //最新公司动态通知
	  if(arr.indexOf('3')>-1){
		  $.ajax({
			  url : ctx + '/target/aqdt/topfour',
			  type : "POST",
			  dataType: 'json', 
			  success : function(data) {
				  var html="";
				  // for (var i = 0; i < data.length&&i<3; i++) {
					//  var date=new Date(data[i].S2).format("yyyy-MM-dd hh:mm:ss");
					//  var css=  i%2==0 ?'border1' :'border2' ;
					//  html+= '<dd class="'+css+'"  onclick="openwj('+data[i].ID+')">'+
					//  '<div><b>'+abbr(data[i].m1,80)+'</b>...</div>'+
					//  '<small>更新时间：'+date+'</small></dd>'
// Captain - 36
                  for (var i = 0; i < data.length&&i<4; i++) {
                      var date=new Date(data[i].S2).format("yyyy-MM-dd hh:mm:ss");
                      var css=  i%2==0 ?'border1' :'border2' ;
                      html+= '<dd class="'+css+'" style="cursor:pointer" onclick="openwj('+data[i].ID+')">'+
                          '<div style="height: 40px"><b>'+abbr(data[i].m1,60)+'</b>...</div>'+
                          '<small style="font-weight: normal">'+date+'</small></dd>';

				  }
				  $("#wjtz").append(html);

				  // <dd class = 'css' onclick='openwj（ID）'>
				  //	<div><b>abbr(ml,80)</B>...<div>
				  //	<small>更新时间: date <small><dd>
			  }
		  });
	  }
		
	  //风险预警雷达图
	  if(arr.indexOf('4')>-1){
	  	radar();
	  }
	  
	  //安全积分
	  if(arr.indexOf('5')>-1){
			var dg;
			dg = $('#target_jfgl_dg').datagrid({
				method : "post",
				url : ctx + '/target/jfgl/listoverview',
				fit : true,
				fitColumns : true,
				border : false,
				idField : 'id',
				striped : true,
				rownumbers : true,
				nowrap : false,
				pageSize : 10,
				scrollbarSize : 5,
				singleSelect:true,
				striped : true,
				columns : [ [{
					field : 'qyname',
					title : '企业名称',
					hidden : true,
					width : 200,
					align : 'center'
				}, {
					field : 'year',
					title : '年份',
					sortable : false,
					width : 50,
					align : 'center'
				}, {
					field : 'name',
					title : '用户姓名',
					sortable : false,
					width : 80,
					align : 'center'
				}, {
					field : 'yhpc',
					title : '隐患排查',
					sortable : false,
					width : 50,
					align : 'center'
				}, {
					field : 'ssp',
					title : '随手拍',
					sortable : false,
					width : 50,
					align : 'center'
				},
				 {
					field : 'aqpx',
					title : '安全培训',
					sortable : false,
					width : 50,
					align : 'center'
				},
				 {
					field : 'jyxc',
					title : '建言献策',
					sortable : false,
					width : 50,
					align : 'center'
				},{
					field : 'qt',
					title : '其他',
					sortable : false,
					width : 50,
					align : 'center'
				},{
					field : 'zjf',
					title : '总积分',
					sortable : false,
					width : 50,
					align : 'center'
				},{
					field : 'caozuo',
					title : '查看详情',
					sortable : false,
					width : 50,
					align : 'center',formatter(value,row){
						return "<a class='btn btn-info btn-xs' onclick='viewjf("+JSON.stringify(row)+")'>查看详情</a>";
					}
				}] ],
				onLoadSuccess: function(){
					$(this).datagrid("autoMergeCells",['year']);
					if(usertype=='isbloc'){
			    		$(this).datagrid("showColumn","qyname");
			    		$(this).datagrid("autoMergeCells",['qyname']);
			    	}
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#target_jfgl_tb'
			});
	  }

	  //安全动态
	  if(arr.indexOf('6')>-1){
		  var dg2;
		  	dg2 = $('#target_aqdt_dg').datagrid(
		  			{
		  				method : "get",
		  				url : ctx + '/target/aqdt/list',
						fit : true,
						fitColumns : true,
						border : false,
						idField : 'id',
						striped : true,
						rownumbers : true,
						singleSelect:true,
						nowrap : false,
						pageSize : 10,
						scrollbarSize : 5,
						striped : true,
		  				columns : [ [
		  						{
		  							field : 'qyname',
		  							title : '企业名称',
		  							hidden : true,
		  							width : 200 
		  						},
		  						{
		  							field : 'm1',
		  							title : '主题',
		  							sortable : false,
		  							width : 100,
		  							formatter: function(value,row,index){
		  								return "<a style='color:blue' onclick='viewdt("+row.id+")' >"+row.m1+"</a>";
		  							}
		  						},
		  						{
		  							field : 's1',
		  							title : '发布时间',
		  							sortable : false,
		  							width : 60,
		  							align : 'center',
		  							formatter : function(value) {
		  								if (value != null && value != '') {
		  									var dt = new Date(value); 
		  						        	return dt.format("yyyy-MM-dd"); 
		  								} else {
		  									return '/';
		  								}
		  							}
		  						},
		  						{
		  							field : 'm5',
		  							title : '文件类型',
		  							sortable : false,
		  							width : 40,
		  							align : 'center'
		  						}
		  				] ],
		  				onLoadSuccess : function() {
		  					if(usertype=='isbloc'){
		  			    		$(this).datagrid("showColumn","qyname");
		  			    		$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		  					}
		  				},
		  			    onDblClickRow: function (rowdata, rowindex, rowDomElement){
		  			        viewdt(11);
		  			    },
		  				checkOnSelect : false,
		  				selectOnCheck : false
		  			});
	  }

	  //消防应急
	  if(arr.indexOf('7')>-1){
		  $.ajax({
		  	url : ctx + '/syshome/bjxx',
		  	type : "POST",
		  	success : function(data) {
		  		//$("#xdt").append(data);
		  		var html="";
		  		for(var i=0;i<data.length;i++){
		  			var arry=data[i].situation.split(";");
		  			var bjname=arry[arry.length-1];
		  		   if(data[i].type=='1')
		  			 html+="<li><span class=\"label label-info\">储罐</span><a onclick='viewbj(\"${ctx}\/fmew\/bj\/cgindex\",\"储罐报警数据\")\' href=\"javascript:\">";
		  			else if(data[i].type=='2')
		  			 html+="<li><span class=\"label label-warning\">浓度</span><a onclick='viewbj(\"${ctx}\/fmew\/bj\/qtindex\",\"浓度报警数据\")\' href=\"javascript:\">";
		  			 else
		  			 html+="<li><span class=\"label label-danger\">高危工艺</span><a onclick='viewbj(\"${ctx}\/fmew\/bj\/gyindex\",\"高危工艺报警数据\")\' href=\"javascript:\">";
		  			 html+=(new Date(data[i].colltime)).format("yyyy-MM-dd hh:mm")+"  "+data[i].qyname+":"+bjname+"</a></li>";
		  		}
	  		    $("#bjxx").append(html);
	  		    $('.dowebok').liMarquee({direction: 'up',scrollamount: 40});
		  	}
		  });
	  }

	  //危险作业
	  if(arr.indexOf('8')>-1){
		  $(function(){
		  	dg3=$('#dw_zysp_dg').datagrid({    
		  	method: "post",
		      url:ctx+'/dw/zysp/list', 
			  fit : true,
			  fitColumns : true,
			  border : false,
			  idField : 'id',
			  striped : true,
			  rownumbers : true,
			  nowrap : false,
			  pageSize : 10,
			  scrollbarSize : 5,
			  striped : true,
			  singleSelect:true,
		      columns:[[    
		  		        {field:'id',title:'id',checkbox:true,width:50,align:'center'},
		  		        {field:'qyname',title:'企业名称',width:100},  
		  		        {field:'m20',title:'类型',width:60,align:'center',
		  					formatter: function(value,row,index){
		  						if (value == "1"){
		  							return "特种作业";
		  						} else if (value == "2"){
		  							return "一般作业";
		  						} 
		  					}},		        
		  		        {field:'m1',title:'方式',width:60,align:'center',
		  					formatter: function(value,row,index){
		  						if(value!=null&&value!=""){
		  							var zyfs = "";
		  							var arr = value.split(",");
		  							arr.forEach(function(e) {
		  								var fs;
		  								if (e == "1"){
		  									fs = "动火作业";
		  								} else if (e == "2"){
		  									fs = "受限空间作业";
		  								} else if (e == "3"){
		  									fs = "管道拆卸作业";
		  								} else if (e == "4"){
		  									fs = "盲板抽堵作业";
		  								} else if (e == "5"){
		  									fs = "高处安全作业";
		  								} else if (e == "6"){
		  									fs = "吊装安全作业";
		  								} else if (e == "7"){
		  									fs = "临时用电安全作业";
		  								} else if (e == "8"){
		  									fs = "动土安全作业";
		  								} else if (e == "9"){
		  									fs = "断路安全作业";
		  								} else if (e == "10"){
		  									fs = "其他安全作业";
		  								}
		  								zyfs = "," + fs + zyfs;
		  						    })
		  						    return zyfs.replace(",","");
		  						}else{
		  							return "";
		  						}
		  					}},		        
		  		        {field:'m2',title:'级别',width:50,align:'center',
		  						formatter: function(value,row,index){
		  							if (value == "1"){
		  								return "特级";
		  							} else if (value == "2"){
		  								return "一级";
		  							} else if (value == "3"){
		  								return "二级";
		  							} else if (value == "4"){
		  								return "其他";
		  							}
		  						}},
		  		        {field:'m3',title:'地点',width:100,align:'center'},
		  		        {field:'m17',title:'队伍',width:50,align:'center',
		  		          	styler: function(value, row, index){
		  		          		if (value == "1"){
		  							return 'background-color:rgb(255, 228, 141);';
		  						} else if (value == "2"){
		  							return 'background-color:rgb(249, 156, 140);';
		  						} 
		  		          	},
		  					formatter: function(value,row,index){
		  						if (value == "1"){
		  							return "外包施工队";
		  						} else if (value == "2"){
		  							return "本厂人员";
		  						} 
		  					}
		  		        },
		  		        {field:'m4',title:'负责人',width:50,align:'center'},
		  				{field:'m5',title:'人员',width:50,align:'center'},
		  				{field:'m7',title:'开始时间',width:80,align:'center',
		  			      	  formatter : function(value, row, index) {
		  			            	if(value!='') {
		  			            		var datetime=new Date(value);
		  			            		return datetime.format('yyyy-MM-dd hh:mm:ss');
		  			            	}
		  			        	} 	
		  				},
		  				{field:'m8',title:'结束时间',width:80,align:'center',
		  			      	  formatter : function(value, row, index) {
		  			            	if(value!='') {
		  			            		var datetime=new Date(value);
		  			            		return datetime.format('yyyy-MM-dd hh:mm:ss');
		  			            	}
		  			        	} 	
		  				},	
		      ]],
		      onDblClickRow: function (rowdata, rowindex, rowDomElement){
		    	  	viewWxzy();
		      },
		      onLoadSuccess:function(){
		    	  if(usertype=="1"){
		    		  $(this).datagrid("hideColumn",['qyname']);
		    	  }else{
		    		  $(this).datagrid("showColumn",['qyname']);
		    	  }
		    	  $(this).datagrid("autoMergeCells",['qyname']);
		      },
		  	checkOnSelect:false,
		  	selectOnCheck:false,
		      toolbar:'#dw_zysp_tb'
		  	});
		  	
		  });
	  }
	  
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
     		openDialogView("信息查看",'${ctx}'+url,"90%", "90%","");
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

//查看动态详细信息
function viewdt(id) {
	  openDialogView("安全动态信息查看","${ctx}/target/aqdt/view/"+id,"80%", "80%","");
}

//查看报警信息
function viewbj(url,name) {
	  openDialogView(name,url,"80%", "80%","");
}

//查看安全积分详细信息
function viewjf(row) {
      	layer.open({
      	    type: 1,  
      	    title: '信息详情',
      	    area:['700px','400px'],
      	    content: $("#select_dg"),
      	    btn: ['关闭'],
      	    success: function(index,layero){
      	      	var d=$('#target_dg').datagrid({    
      	    		method: "post",
      	    		url  : ctx+'/target/jfgl/qylist',
      	    		queryParams :{'year':row.year,'uid':row.id2},
      	    		fit : true,
      				fitColumns : true,
      				border : false,
      				idField : 'id',
      				striped : true,
      				pagination : true,
      				rownumbers : true,
      				nowrap : false,
      				pageNumber : 1,
      				pageSize : 50,
      				pageList : [ 50, 100, 150, 200, 250 ],
      				scrollbarSize : 5,
      				singleSelect : true,
      				striped : true,
      	    		columns:[[    
			    	              {field:'name',title:'用户姓名',sortable: false,width:60,align:'center'},
			    	              {field:'m1',title:'类别',sortable: false,width:60,align:'center',formatter : function(value,row){
			    	            	  var html="";
			    	            	  if(value==1) html="隐患排查";
			    	            	  else if(value==2) html="随手拍";
			    	            	  else if(value==3) html="安全培训";
			    	            	  else if(value==4) html="建言献策";
			    	            	  else if(value==5) html="其他";
			    	            	 return html;
			    	              }}, 
			    	              {field:'m2',title:'积分值',sortable: false,width:50,align:'center'},
			    	              {field:'m3',title:'积分说明',sortable: false,width:150,align:'center'},
			    	              {field:'m4',title:'评定日期',sortable: false,width:80,align:'center',
			    	            	  formatter:function(value){
			    	            		  if(value) return new Date(value).format('yyyy-MM-dd');
			    	            		  else return ;
			    	            	  }},      
  		 		    		]],
  		 		    onLoadSuccess: function(){dg.datagrid("autoMergeCells",['year','quarter','department']);}
      	    		});
      	    },
      	    cancel: function(index){}
      	}); 

}

//查看危险作业
function viewWxzy(){
	var row = dg3.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("作业报备详细页面",ctx+"/dw/zysp/view/"+row.id,"800px", "400px","");
	
}

//自定义菜单
function selmenu(){
	window.secid='${userdefine.ID}';
	window.userid='${userdefine.userid}';
	window.menuid='${userdefine.menuid}';
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '选择菜单',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/system/user/selindex',
	    btn: ['确定'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doSubmit();
		  }
	}); 		
}
  </script>
 </body>
</html>