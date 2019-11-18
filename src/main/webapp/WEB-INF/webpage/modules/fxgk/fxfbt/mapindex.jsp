<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
<style type="text/css">
body {
	height: 100%;
	margin: 0px;
	padding: 0px;
	font-family: "微软雅黑";
}

body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
}

#dituContent {
	height: 100%;
	width: 100%;
}

.BMap_cpyCtrl {
	display: none;
}

.anchorBL {
	display: none;
}

#dituContent label {
	max-width: none;
}

.ball {
	width: 10px;
	height: 10px;
	border-radius: 50%;
	position: absolute;
}

.wrap {
	height: 280px;
	width: 280px;
	background: #ccc;
	position: relative;
	background: #ccc;
}

#timeline {
	width: 110px;
	height: 350px;
	overflow: hidden;
	position: relative;
	text-align: center;
}

#dates {
	width: 110px;
	height: 400px;
	padding: 0
}

#dates li {
	list-style: none;
	line-height: 50px;
	font-size: 16px;
}

#dates .selected {
	font-size: 24px;
	margin: 0 auto;
	background-color: #8ea8e0;
}

a {
	color: #fff;
	text-decoration: none;
}

a:hover,a.selected {
	color: #ffcc00;
}

.play {
	background: url('${ctx}/static/model/images/play.png') center no-repeat;
	width: 35px;
	height: 35px;
	margin: 0 auto;
}

.pause {
	background: url('${ctx}/static/model/images/pause.png') center no-repeat;
	width: 35px;
	height: 35px;
    margin: 0 auto;
}

#gb {
	background-color: rgba(255, 255, 255, 0.2); border-style : solid;
	border-width: 1px;
	border-color: rgb(142, 168, 224);
	border-radius: 10px;
	box-shadow: 0px 5px 13px 0px rgba(0, 0, 0, 0.9);
	width: 110px;
	height: 400px;
	z-index: 20;
	border-style: solid;
}
</style>
<script type="text/javascript">
	var ctx = '${ctx}';
	var mappoint = '${mappoint}'
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/fxgk/fxfbt/mapindex.js?v=1.1"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/fxgk/fxfbt/timelinr.js"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>
</head>
<body>
   <div id="r-result" style="margin:10px auto;">
      &nbsp;&nbsp;
      <!-- <input id="keyword" type="text" class="easyui-textbox" style="width:150px;" value="" data-options="prompt: '企业名称' " />
          <button type="button" class="btn btn-info btn-xs" onclick="search('keyword')" style="width:50px" >搜索</button> -->
      <button type="button" class="btn btn-info btn-xs" id="fxyt" style="width:85px">固有风险云图</button>
      <button type="button" class="btn btn-info btn-xs" id="whp" style="width:120px">危化品动态风险云图</button>
      <button type="button" class="btn btn-info btn-xs" id="fxfj" style="width:120px">企业风险分级分布图</button>
      <button type="button" class="btn btn-info btn-xs" id="zdzd" style="width:120px">两重点一重大分布图</button>
      <button type="button" class="btn btn-info btn-xs" id="wgfx" style="width:100px">网格风险分布图</button>
      <!-- <button type="button" class="btn btn-info btn-xs" onclick="reset()" style="width:70px">全部数据</button> -->
   </div>
   <div id="dituContent"></div>
   <div id="select_type" style="display:none;height:350px">
      <table id="areadata"></table>
   </div>
   <script type="text/javascript">
				var ctx = '${ctx}';
				var mappoint = '${mappoint}';
				initMap("dituContent");
				var myTimeCtrl;//自定义控件
				var whpdatas;//危化品数据
				var interval;
				$(function() {
					//进入页面加载热力图
					createPolygon();
					loadHeatMap(getJson("/fxgk/fxfb/showfxyt"),900);
					//风险分级图
					$("#fxfj").click(function() {
						$.jBox.tip("请稍等...", 'loading', {
							opacity : 0
						});
						reloadMap();
						//蓝黄橙红依次加载
						getDateLoadCollection(4);
						getDateLoadCollection(3);
						getDateLoadCollection(2);
						getDateLoadCollection(1);
						$.jBox.closeTip();
					});
					//两重点一重大分布图
					$("#zdzd").click(function() {
						$.jBox.tip("请稍等...", 'loading', {
							opacity : 0
						});
						var data = getJson("/fxgk/fxfb/showzdzd");
						if (data != undefined) {
							reloadMap();
							addMarker(data);//加载marker点
						}
						$.jBox.closeTip();
					});
					$("#whp").click(function() {
						$.jBox.tip("请稍等...", 'loading', {
							opacity : 0
						});
						reloadMap();
         				TimeControl.prototype = new BMap.Control();
         				TimeControl.prototype.initialize = function(map) {
         					// 创建一个DOM元素
         				var mydate = new Date();
                     	mydate.setDate(mydate.getDate() - 6);
                     	
                     	var cont="";//时间轴内容
                     	for (var i = 0; i < 7; i++) {
                     		var newdate = mydate.format("yyyy-MM-dd");
                     		var newdate2 = mydate.format("MM-dd");
                     		cont += "<li><a title='" + newdate + "'>" + newdate2 + "</a></li>";
                     		mydate.setDate(mydate.getDate() + 1);
                     	}
         					var html = "<div id='gb'><div id='timeline'><ul id='dates'>"+cont+"</ul></div><div style='width:100%;'><div id='timeline_Pause' class='pause' onclick='clickPause()'></div></div></div>";
         					var div = document.createElement("div");
         					div.innerHTML = html;
         					// 添加DOM元素到地图中
         					map.getContainer().appendChild(div);
         					// 将DOM元素返回
         					return div;
         				}
         				// 创建控件
         				myTimeCtrl = new TimeControl();
         				// 添加到地图当中
         				map.addControl(myTimeCtrl); 
         				interval=setInterval("autoPlay()", 1500);
         				var data;
         				$.ajax({
         					type : "get",
         					url : ctx + "/fmew/sjfx/heatmapjson",
         					async : false,
         					dataType : 'json',
         					success : function(d) {
         						whpdatas=d;
         						settimelinr();
         					}
         				});
						$.jBox.closeTip();
					});

					$("#fxyt").click(function() {
						$.jBox.tip("请稍等...", 'loading', {
							opacity : 0
						});
						reloadMap();
						loadHeatMap(getJson("/fxgk/fxfb/showfxyt"),900);
						$.jBox.closeTip();
					});
					$("#wgfx").click(function() {
						$.jBox.tip("请稍等...", 'loading', {
							opacity : 0
						});
						map.removeControl(myTimeCtrl);
						map.clearOverlays();
						var data=getJson("/fxgk/fxfb/barrocolor");
						var colorlist=[];
						var len=data.length;
						var max=data[len-1].count;
						var min=data[0].count;
						var dif=max-min;
						var dif2=dif/2;
						var everyd=255/dif2;
						var startRGB = colorRgb('#00ff00');//绿色,先变前两位00
						  startR = startRGB[0];
						  startG = startRGB[1];
						  startB = startRGB[2];
						var endRGB = colorRgb('#ff0000');
						  endR = endRGB[0];
						  endG = endRGB[1];
						  endB = endRGB[2];
						colorlist.push('#00ff00');
						for(var i=1;i<len-1;i++){
							var count=data[i].count;
							if((count-min)<=dif2){
								startR=parseInt(everyd*(count-min));
								colorlist.push("rgb("+startR+","+startG+","+startB+")");
							}else{
								endG=parseInt(everyd*(max-count));
								colorlist.push("rgb("+endR+","+endG+","+endB+")");
							}
						}
						colorlist.push('#ff0000');
						createPolygons(data,colorlist);
						$.jBox.closeTip();
					});

				});
				//设置时间轴控件的相关属性
				function settimelinr() {
					$().timelinr({
						autoPlay : 'true',
						autoPlayDirection : 'forward',
						startAt : 1,
						orientation : 'vertical'
					})
				};
				// 去除所有图层并加载多边形图层
				function reloadMap() {
					map.clearOverlays();
					if(interval)
						clearInterval(interval);
					map.removeControl(myTimeCtrl);
					createPolygon();
				}
			</script>
</body>
</html>