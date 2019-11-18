<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="default" />
<link href="${ctx}/static/model/css/home/styles.css?v=1.0" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/model/js/home/jquery.liMarquee.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.2"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<style type="text/css">
body {background-color:#FAFAFA}
.BMap_cpyCtrl{ display:none; }  
.anchorBL{ display:none;}   
.txtsize{padding:20px;}
.tit {
	padding: 0 10px 0 6px;
	border-left: 3px solid #3ba2c2;
	line-height: 16px;
	font-size: 16px;
	color: #3ba2c2;
	font-weight: 600;
}
.lab,.box {
	box-shadow: 2px 2px 8px #ececec;
	border-radius: 0px;
}
.lab,.box{
	border: 1px solid #c9dbdc;
}
.line3 .title{
	border-bottom: 1px solid #c9dbdc;
}

.container {
	background: white;
}
</style>
</head>
<body>
	<!-- <div class="line1" style="margin:10px 0px 0px 20px "></div>-->
	<div class="line1">
		<!-- Cap's -->
		<div class="col-lg-12 col-md-12 col-xs-12 mgb10" style="background: white;padding: 10px 0px 0;">
			<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0 10px 2px;display: flex;align-items: center;justify-content: space-between;">
				<span class="tit">到期提醒</span><button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="selmenu()" title="自定义界面"><i class="fa fa-external-link"></i> 自定义界面</button> 
			</div>
			<div class="col-lg-6 col-md-6 col-xs-12 mgt10 mtb8" style="display: flex;padding:0px;">
				<div class="lab grad" onclick="viewInfor('/bis/aqpxxx/index','expiration')" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/lab1.png);background-position: center bottom;background-repeat: no-repeat;background-size: 100%;">
					<div class="labwds" style="">
						<p>安全证书培训&nbsp;&nbsp;</p>
						<p class="num"><span id="aqglzs"></span><span>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-long-arrow-up-xx" style="color:red"></i>&nbsp;</span></p>
					</div>
					<div class="labic">
						<i class="fa fa-file-text" style="color: #f56583;"></i>
					</div>
				</div>
				<div class="lab grad2" onclick="viewInfor('/bis/tzsbxx/index','expiration')" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/lab2.png);background-position: center bottom;background-repeat: no-repeat;background-size: 100%;">
					<div class="labwds" style="">
						<p>特种设备检测</p>
						<p class="num"><span id="tzsb"></span><span>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-long-arrow-up-xx" style="color:red"></i>&nbsp;</span></p>
					</div>
					<div class="labic" style="">
						<i class="fa fa-wrench" style="color: #fbb059;"></i>
					</div>
				</div>
				<div class="lab grad3" onclick="viewInfor('/bis/aqss/index','expiration')" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/lab3.png);background-position: center bottom;background-repeat: no-repeat;background-size: 100%;">
					<div class="labwds" style="">
						<p>安全设施检测</p>
						<p class="num"><span id="aqss"></span><span>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-long-arrow-up-xx" style="color:red"></i>&nbsp;</span></p>
					</div>
					<div class="labic">
						<i class="fa fa-tasks" style="color: #18c069;"></i>
					</div>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-xs-12 mgt10 mtb8" style="display: flex;padding:0px;">
				<div class="lab grad4" onclick="viewInfor('/bis/occupharmreport/index','expiration')" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/lab4.png);background-position: center bottom;background-repeat: no-repeat;background-size: 100%;">
					<div class="labwds" style="">
						<p>职业病危害检测</p>
						<p class="num"><span id="zybwh"></span><span>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-long-arrow-up-xx" style="color:red"></i>&nbsp;</span></p>
					</div>
					<div class="labic" style="">
						<i class="fa fa-medkit" style="color: #1faff0;"></i>
					</div>
				</div>
				<div class="lab grad5" onclick="viewInfor('/bis/ygtjxx/index','expiration')" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/lab3.png);background-position: center bottom;background-repeat: no-repeat;background-size: 100%;">
					<div class="labwds">
						<p>职业病体检</p>
						<p class="num"><span id="zybtj"></span><span>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-long-arrow-up-xx" style="color:red"></i>&nbsp;</span></p>
					</div>
					<div class="labic" style="">
						<i class="fa fa-stethoscope" style="color: #10be63;"></i>
					</div>
				</div>
				<div class="lab grad6" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/lab2.png);background-position: center bottom;background-repeat: no-repeat;background-size: 100%;">
					<div class="labwds" style="">
						<p>其它提醒</p>
						<p class="num">0<span>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-long-arrow-up-xx" style="color:red"></i>&nbsp;</span></p>
					</div>
					<div class="labic">
						<i class="fa fa-share-alt" style="color: #fba642;"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	
	<!--第三行-->
	
	<div class="container line3" >
		<!--待办-->
		<div class="col-lg-6 col-md-6" style="height:500px">
			<div class="box" style="height: 481px">
			<div class="title"><span class="tit">待办任务</span><div class="pull-right" style="margin-right: 15px;"> </div></div>
				<div style="display: flex;height: 420px;padding:0px 10px; line-height: 30px">
					<div class="dbblock" style="height: 340px;margin:40px 0px;border-right: 1px #dedede dashed;width: 25%">
						<div class="dbicon dbic1" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/dbic1.png);background-size:cover;"></div>
						<div class="dbctt">
							<p>安全巡检&nbsp;&nbsp;<span id="dxj">0</span></p>
							<p>安全检查&nbsp;&nbsp;<span id="djc">0</span></p>
							<p>隐患整改&nbsp;&nbsp;<span>0</span></p>
							<p>整改复查&nbsp;&nbsp;<span>0</span></p>
						</div>
					</div>
					<div class="dbblock" style="height: 340px;margin:40px 0px;border-right: 1px #dedede dashed;width: 25%">
						<div class="dbicon dbic1" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/dbic2.png);background-size:cover;"></div>
						<div class="dbctt">
							<p>审核任务&nbsp;&nbsp;<span id="dsh">0</span></p>
							<p>审批任务&nbsp;&nbsp;<span id="dsp">0</span></p>
							<p>评审任务&nbsp;&nbsp;<span>0</span></p>
						</div>
					</div>
					<div class="dbblock" style="height: 340px;margin:40px 0px;border-right: 1px #dedede dashed;width: 25%">
						<div class="dbicon dbic1" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/dbic3.png);background-size:cover;"></div>
						<div class="dbctt">
							<p>应急演练&nbsp;&nbsp;<span>0</span></p>
							<p>法规识别&nbsp;&nbsp;<span>0</span></p>
							<p>文件查阅&nbsp;&nbsp;<span id="xwj">0</span></p>
						</div>
					</div>
					<div class="dbblock" style="height: 340px;margin:40px 0px;width: 25%">
						<div class="dbicon dbic1" style="background-image: url(${ctxStatic}/model/images/home/syshome_qy/dbic4.png);background-size:cover;"></div>
						<div class="dbctt">
							<p>安全培训&nbsp;&nbsp;<span id="pxtz">0</span></p>
							<p>安全会议&nbsp;&nbsp;<span>0</span></p>
							<p>劳保用品&nbsp;&nbsp;<span>0</span></p>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<c:forTokens items="${userdefine.menuid}" delims="," var="menu" varStatus="menus">
			<c:if test="${menu==0}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					<div class="box" style="height: 481px">
					<div class="title"><span class="tit">安全生产十大禁令</span> <div class="pull-right" style="margin-right: 15px;"> </div></div>
						<div style="height: 420px;padding:00px 60px 0px 60px; line-height: 30px">
							<h2 style="text-align: center; margin-bottom: 15px">安全生产十大禁令</h2>
							<font size="3" ><b><font color="#417FB8" >一、</font></b>严禁装置生产区域吸烟。</font><br/>
							<font size="3"><b><font color="#417FB8" >二、</font></b>严禁登高作业不系挂安全带。</font><br/>
							<font size="3"><b><font color="#417FB8" >三、</font></b>严禁无作业许可证进行相关作业。</font><br/>
							<font size="3"><b><font color="#417FB8" >四、</font></b>严禁工作岗位违反劳动纪律睡岗。</font><br/>
							<font size="3"><b><font color="#417FB8" >五、</font></b>严禁防爆装置区使用非防爆手机。</font><br/>
							<font size="3"><b></b>如有违反者，一经发现立即下岗，进行培训教育，情节严重者，解除劳动合同 。</font><br/>
						</div>
					</div>
				</div>
			</c:if>		
			
			<c:if test="${menu==1}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					<div class="box" style="height: 481px">
					<div class="title"><span class="tit">企业卫星图</span>  <div class="pull-right" style="margin-right: 15px;"> <!-- <span class="btn btn-info btn-xs" onclick="viewInfor('/zxjkyj/spjk/index')"> 视频监控</span> -->  <span class="btn btn-info btn-xs" onclick="viewInfor('/yhpc/bcrw/syxjdindex')">巡检点</span>  </div></div>
					<div id="qymapContent" style="height: 440px;"> </div>
					</div>
				</div>
			</c:if>
			
			<c:if test="${menu==3}">
				<div class="col-lg-6 col-md-6" style="height:500px">
					<div class="box " style="height: 481px">
						<div class="news">
							<div class="title">
								<span class="tit">最新公司动态通知</span>
								<a class="pull-right" onclick="viewInfor('/target/aqdt/index')">更多</a>
							</div>
							<dl36 id="wjtz">
								 
							</dl36>
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
	  //调用天气插件
	  $.getScript('http://ip.ws.126.net/ipquery',function(){
		  var citytq=localAddress.city;
		  $.ajax({
			  type:'get',
			  url:url = "http://wthrcdn.etouch.cn/weather_mini?city=" + citytq,
			  scriptCharset: "gbk",
			  dataType:'json',
			  success: function(data){
				  var _w0 = data.data.forecast;
				  var date= new Date();
				  var html="";
				  var r = /\[.+\[(.+?)\]/;
				  for(var i=0;i<_w0.length;i++){
					  html+=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+"星期"+"日一二三四五六".charAt(date.getDay());
					  if(i==0){
						  html+=" 今日天气：";
					  }
					  else if (i==1)
						  html+= " 明天天气: ";
					  else
						  html+=" "+"天气: ";
					  html+=_w0[i].type+"; ";
					  html+=_w0[i].high+"~"+_w0[i].low+";&nbsp"+_w0[i].fengxiang+_w0[i].fengli.match(r)[1]+";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					  date.setDate(date.getDate()+1);
				  }
				  $("#weather").html(html);
				  $('.weather').liMarquee({});
			  }
		  });
	  });

	  //卫星图
	  if(arr.indexOf('1')>-1){
		  initMap("qymapContent","${company.m16}","${company.m17}",20);
		  var map=getMap();
		  var markerc = new BMap.Marker(new BMap.Point("${company.m16}","${company.m17}"));  
	      map.addOverlay(markerc);  
		  markerc.setAnimation(BMAP_ANIMATION_BOUNCE); 
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
    	    area:['90%','80%'],
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