<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<%--  <script type="text/javascript" src="${ctxStatic}/model/js/qyxx/qyjbxx/qyfbmapindex.js"></script>  --%>
<%-- <script type="text/javascript" src="${ctxStatic}/model/js/home/bootstrap.min.js"></script> --%>
<script type="text/javascript" src="${ctxStatic}/model/js/home/jquery.liMarquee.js"></script>
<%--  <script type="text/javascript" src="${ctxStatic}/model/js/zxjkyj/dtfxyt/mapindex.js?v=1.2"></script> --%>
<link href="${ctxStatic}/model/css/home/styles.css" rel="stylesheet" />
<link href="${ctxStatic}/model/css/home/liMarquee.css" rel="stylesheet" />
<style type="text/css">
.BMap_cpyCtrl {
	display: none;
}

.anchorBL {
	display: none;
}

.imgdiv {
	text-align: left;
	float: right;
	width: 50%;
	font-size: 12px;
}

.imgsty {
	width: 13px;
	height: 13px;
}

.mr {
	margin-left: 20px;
}

.titlediv {
	float: left;
	margin-left: -45px;
	margin-top: -25px;
	font-size: 15px
}

.afont {
	font-size: 15px;
	font-weight: 600;
}


.w15 {
	width: 15%;
}

.w20 {
	width: 20%;
}

.tds {
	font-size: 12px;
	letter-spacing: -0.1em;;
}
.col-lg-2-4 {
      width: 20%
   }
</style>
</head>
<body id="win">
   <div class="container-fluid">
      <div class="toptips mgt10">
         <div class="weather" style="height:30px;">
            <a id="weather" href="javascript:" style="display:block;margin-top:5px;font-size:15px;"> </a>
         </div>
      </div>
      <div class="col-lg-12 col-md-12 col-xs-12  bs-example mgt10 mgl10">
         <div class="ajtitle">
            统计信息
            <a class="pull-right" href="#"></a>
         </div>
         <div class="col-lg-2-4  col-md-4 col-xs-6 ">
            <div class="tjbox1">
               <div class="tjicon"></div>
               <div class="tjtext" style="margin-top:-35px">
                  <div class="titlediv">企业概况</div>
                  <br>
                  <a href="#" style="color:white;" onclick="openPage('/bis/qyjbxx/index','一企一档')">企业总数：<span class="afont" id="qysum"></span></a><br>
                  <a href="#" style="color:white;" onclick="openPage2('/bis/qyjbxx/index?jglx=2','化工企业')">化工企业：<span class="afont" id="hgsum"></span></a><br>
                  <a href="#" style="color:white;" onclick="openPage2('/bis/qyjbxx/index?jglx=1','工贸企业')">工贸企业：<span class="afont" id="gmsum"></span></a><br>
                  <a href="#" style="color:white;" onclick="openPage2('/bis/qyjbxx/index?jglx=9','其他企业')">其他企业：<span class="afont" id="qtsum"></span></a>
               </div>
            </div>
         </div>
         <div class="col-lg-2-4  col-md-4 col-xs-6">
            <div class="tjbox2">
               <div class="tjicon"></div>
               <div class="tjtext" style="margin-top:-20px">
                  <div class="titlediv">两重点一重大</div>
                  <span style="font-size:12px;letter-spacing:0.1em;height:4px"> <br>
                  <a href="#" style="color:white" onclick="openPage('/bis/hazard/index','重大危险源')">重大危险源：<span class="afont" id="wxysum"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/bis/wlxx/index2','重点监管化学品')">重点监管化学品：<span class="afont" id="hxpsum"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/bis/gwgy/index','高危工艺')">高危工艺：<span class="afont" id="gwsum"></span></a></span>

               </div>
            </div>
         </div>
         <div class="col-lg-2-4  col-md-4 col-xs-6">
            <div class="tjbox4">
               <div class="tjicon"></div>
               <div class="tjtext" style="margin-top:-20px">
                  <div class="titlediv">安全监督</div>
                  <br> 
                  <a href="#" style="color:white"onclick="openDialogView('计划检查数','${ctx }/aqjd/jcjh/index','100%', '100%','');">计划检查数：<span class="afont" id="jcssum"></span></a><br>
                  <a href="#" style="color:white"onclick="openDialogView('执行数','${ctx }/aqjd/jcjl/index','100%', '100%','');">执行数：<span class="afont" id="zxsum"></span></a><br>
                  <a href="#" style="color:white"onclick="openDialogView('待复查数','${ctx }/aqjd/jcjl/index','100%', '100%','');">待复查数：<span class="afont" id="fcsum"></span></a>
               </div>
            </div>
         </div>

         <div class="col-lg-2-4  col-md-4 col-xs-6">
            <div class="tjbox5">
              <div class="tjicon"></div>
               <div class="tjtext" style="margin-top:-20px">
                  <div class="titlediv">安全执法</div>
                  <span style="font-size:12px;letter-spacing:0.1em;height:4px">
                  <br>
                  <a href="#" style=" color:white" onclick="openPage('/xzcf/ybcf/cfjd/index','处罚数')">处罚数：<span class="afont" id="cfsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/xzcf/ybcf/ajclcp/index','案件呈批数')">案件呈批数：<span class="afont" id="cpsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/xzcf/ybcf/jasp/index','结案数')">结案数：<span class="afont" id="jasum"></span></a>
                  </span>
               </div>
            </div>
         </div>

         <div class="col-lg-2-4  col-md-4 col-xs-6">
            <div class="tjbox6">
               <div class="tjicon"></div>
               <div class="tjtext" style="margin-top:-20px">
                  <div class="titlediv">安全许可</div>
                  <span style="font-size:12px;letter-spacing:0.1em;height:4px">
                  <br>
                  <a href="#" style=" color:white" onclick="openPage('/aqjg/aqba/index','已发证')">已发证：<span class="afont" id="yfsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/aqjg/aqba/index','已过期')">已过期：<span class="afont" id="gqsum"></span></a><br>
                  </span>
               </div>
            </div>
         </div>

         <div class="col-lg-2-4  col-md-4 col-xs-6">
            <div class="tjbox6">
               <div class="tjicon"></div>
               <div class="tjtext" style="margin-top:-40px">
                  <div class="titlediv">安全许可</div>
                  <span style="font-size:12px;letter-spacing:0.1em;height:4px">
                  <br>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjdw/index','应急队伍')">应急队伍：<span class="afont" id="dwsum"></span></a>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjwz/index','应急物资')">应急物资：<span class="afont" id="wzsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjzb/index','应急装备')">应急装备：<span class="afont" id="zbsum"></span></a>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjzj/index','应急专家')">应急专家：<span class="afont" id="zjsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjyagl/index','应急预案')">应急预案：<span class="afont" id="yasum"></span></a>
                  </span>
               </div>
            </div>
         </div>

         <div class="col-lg-2-4  col-md-4 col-xs-6">
            <div class="tjbox3">
               <div class="tjicon"></div>
               <div class="tjtext" style="margin-top:-10px">
                  <div class="titlediv">网格管理</div>
                  <span style="font-size:12px;letter-spacing:-0.1em;height:4px">
                  <a href="#" style="color:white" onclick="openDialogView('网格数','${ctx }/system/admin/xzqy','100%', '100%','');">网格数：<span class="afont" id="wgsum"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/wghgl/xjjl/index','网格排查率')">网格排查率：<span class="afont" id="pcsum"></span></a><br>
                  </span>
                  
               </div>
            </div>
         </div>

         <div class="col-lg-2-4  col-md-4 col-xs-6">
            <div class="tjbox3">
               <div class="tjicon"></div>
               <div class="tjtext" style="margin-top:-15px">
                  <div class="titlediv">文件发布</div>
                  <span style="font-size:12px;letter-spacing:-0.1em;height:4px">
                  <a href="#" style=" color:white" onclick="openDialogView('文件查看','${ctx }/issue/aqwjfb/index','100%', '100%','')">发文总数：<span class="afont" id="fwsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openIssuePage('1')">接收总数：<span class="afont" id="jssum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openIssuePage('2')">已读：<span class="afont" id="ydsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openIssuePage('3')">未读：<span class="afont" id="wdsum"></span></a><br>
                  </span>

               </div>
            </div>
         </div>

        <div class="col-lg-2-4 col-md-4 col-xs-6">
         <div class="tjbox10">
            <div class="tjicon"></div>
            <div class="tjtext">
               <div class="titlediv">实时监控</div>
               <a href="#" style="color:white" onclick="openPage('/fmew/bj/cgindex','储罐报警')">储罐报警：<span class="afont" id="cgsum"></span></a><br>
               <a href="#" style="color:white" onclick="openPage('/fmew/bj/qtindex','浓度报警')">浓度报警：<span class="afont" id="ndsum"></span></a><br>
               <a href="#" style="color:white" onclick="openPage('/fmew/bj/gyindex','高危工艺报警')">高危工艺报警：<span class="afont" id="gwbjsum"></span></a></span>
            </div>
         </div>
         </div>
         </div>
      
      <div class="col-lg-6 col-md-6 col-xs-12 bs-example mgt10">
         <div class="ajtitle">
            最新文件
            <a class="pull-right" onclick='openPage("/issue/aqwjfb/index/","安全文件管理")' href="#"> 更多+&nbsp;&nbsp;</a>
         </div>
         <div class="listnews">
            <ul class="clearpd" id="xdt" style="height:300px">
            </ul>
         </div>
      </div>
      <div class="col-lg-6 col-md-6 col-xs-12">
         <div class="bs-example mgt10">
            <div class="ajtitle">
               最新动态
               <a class="pull-right" onclick='openPage("/issue/aqscdt/index","安全生产动态")' href="#"> 更多+&nbsp;&nbsp;</a>
            </div>
            <div class="listnews">
               <ul class="clearpd" id="zxdt" style="height:300px">
               </ul>
            </div>
         </div>
      </div>

      <div class="col-lg-12 col-md-12 col-xs-12 bs-example mgt10">
         <div class="bs-example mgt10">
            <div class="ajtitle">
               地图分布
               <a class="pull-right" href="#"></a>
            </div>
            <div class="mgt10" style="height:550px">
               <iframe frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no src="${ctx}/bis/qyjbxx/onemap?type=1"></iframe>
            </div>
         </div>
      </div>
      <div class="col-lg-12 col-xs-12 bs-example mgt10">
         <div class="ajtitle">
            物料储量历史图
            <a class="pull-right" href="#"></a>
         </div>
         <div class="mgt10" style="height:400px">

            <div class="ibox-content" style="height:400px">
               <div id="main" style="height:400px"></div>
            </div>
         </div>
      </div>
   </div>
   <script>
  var mappoint='${mappoint}';
  var forbidscrollflg='${forbidScroll}';
  var Gsys = true ;
  var IssueType;
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
  });
 $.ajax({
		url : ctx + '/syshome/zxdt',
		type : "POST",
		success : function(data) {
			//$("#xdt").append(data);
			var html="";
			for (var i = 0; i < data.length; i++) {
				var date=new Date(data[i].s1);
				html+= "<li><a onclick=\"opendt("+data[i].id+")\" href=\"#\" >"+(data[i].m1.length>25?data[i].m1.substring(0,30)+"...":data[i].m1) 
					 + "</a><span class=\"pull-right\">"+(date.getFullYear()+'-'
							 +((date.getMonth() + 1) < 10 ? ('0' + (date.getMonth()+ 1)) :(date.getMonth()+ 1))
								+'-' + (date.getDate() < 10 ? ('0' + date.getDate()) : date.getDate()))+"</span></li>";
			}
			$("#zxdt").append(html);
		}
	});
$.ajax({
	url : ctx + '/syshome/issue',
	type : "POST",
	success : function(data) {
		//$("#xdt").append(data);
		var html="";
		for (var i = 0; i < data.length; i++) {
			var date=new Date(data[i].S1);
			html+= "<li><a onclick=\"openwj("+data[i].ID+")\" href=\"#\" >"+ (data[i].M1.length>25?data[i].M1.substring(0,30)+"...":data[i].M1) 
				 + "</a><span  class=\"pull-right\">"+(date.getFullYear()+'-'
						 +((date.getMonth() + 1) < 10 ? ('0' + (date.getMonth()+ 1)) :(date.getMonth()+ 1))
							+'-' + (date.getDate() < 10 ? ('0' + date.getDate()) : date.getDate()))+"</span></li>";
		}
		$("#xdt").append(html);
	}
});
//报警信息
/* $.ajax({
	url : ctx + '/syshome/bjxx',
	type : "POST",
	success : function(data) {
		//$("#xdt").append(data);
		var html="";
		for(var i=0;i<data.length;i++){
			var arry=data[i].situation.split(";");
			var bjname=arry[arry.length-1];
		   if(data[i].type=='1')
			 html+="<li><span class=\"label label-info\">储罐</span><a onclick='openPage(\"\/fmew\/bj\/cgindex\",\"储罐报警数据\")\'>";
			else if(data[i].type=='2')
			 html+="<li><span class=\"label label-warning\">浓度</span><a onclick='openPage(\"\/fmew\/bj\/qtindex\",\"浓度报警数据\")\'>";
			 else
			 html+="<li><span class=\"label label-danger\">高危工艺</span><a onclick='openPage(\"\/fmew\/bj\/gyindex\",\"高危工艺报警数据\")\'>";
			 html+=(new Date(data[i].colltime)).format("yyyy-MM-dd hh:mm")+"  "+data[i].qyname+":"+bjname+"</a></li>";
		}
		   $("#bjxx").append(html);
		   $('.dowebok').liMarquee({direction: 'up',scrollamount: 40});
	}
}); */
$.ajax({
	url : ctx + '/syshome/sjzs',
	type : "POST",
	success : function(data) {
       var tjdata=eval(data);
       console.log(tjdata);
       for (var key in tjdata) {
          for ( var i in tjdata[key] ){
             $("#"+i).text(tjdata[key][i]);
          }
       }
/*		var sj=eval(data);
		var afont= $(".afont");
		for(var i=0;i<afont.length;i++){
			afont[i].innerText=sj[i];
		}*/
	}
});

function openwj(id){
	window.open(ctx + "/issue/aqwjfb/view/" +id, "信息查看");
}
function opendt(id){
	window.open(ctx+"/issue/aqscdt/view/"+id,"生产动态信息查看");
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

  function openPage2(url,title,mflag){
     url='${ctx}'+url+"&sys=1";
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
function openIssuePage(type){
    IssueType = type;
	layer.open({
	    type: 2,  
	    area: ['100%', '100%'],
	    title: '文件查看',
        maxmin: true, 
	    content: '${ctx}/issue/zfwjfb/index',
	    btn: ['关闭'],
	    yes: function(index, layero){	
	    	layer.close(index);
			},
	cancel: function(index){}
	});
}

// 路径配置
require.config({
	paths : {
		echarts : '${ctxStatic}/echarts'
	}
});


var y = [];//y轴
var legend = [];//legend值
var x = [];//x轴
$(function(){
	$.ajax({
		url : ctx + "/zxjkyj/dsjfx/getlinejson",
		type : "POST",
		dataType : "json",
		success : function(data) {
			y = [];//y轴
			legend = [];//legend值
			x = [];//x轴
		    x = data.date;
			for (key in data) {
				if (key != "date") {
					y.push({
						"name" : key,
						"type" : "line",
						"data" : data[key]
					});
					legend.push(key);
				}
			}
			loadEcharts();
		}
});
});
 
function loadEcharts() {
	require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
		var myChart = ec.init(document.getElementById('main'));
		myChart.showLoading();
				myChart.hideLoading();
				myChart.setOption({

					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : legend
					},
					toolbox : {
						show : true,
						feature : {
							magicType : {
								show : true,
								type : [ 'line', 'bar' ]
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					xAxis : [ {
						type : 'category',
						boundaryGap : false,
						data : x
					} ],
					yAxis : [ {
						type : 'value',
						'name':'m³',
					} ],
					series : y
				});

			});
	}
  </script>
</body>
</html>