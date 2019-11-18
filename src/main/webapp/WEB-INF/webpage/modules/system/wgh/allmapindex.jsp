<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>网格区域</title>
<meta name="decorator" content="default" />

<style type="text/css">
.BMap_cpyCtrl {
	display: none;
}

.anchorBL {
	display: none;
}

#dituContent label {
	max-width: none;
}

.box1 {
	background-color: white;
	border: 1px
}

.box2 {
	background-color: #ff6666;
}

.box3 {
	background-color: #666699;
}

.icon {
	float: left;
	width: 50px;
	height: 50px;
	margin: 10px;
}

.boxx2 .list-inline {
	margin-top: 10px;
	float: left
}

.boxx2 .list-inline li {
	width: 47%;
	height: 40px;
	line-height: 40px;
	margin-left: 9px;
	margin-bottom: 10px;
	padding-left: 0px;
	overflow: hidden;
	white-space: nowrap;
	margin-bottom: 10px;
}

.boxx2 .list-inline li a {
	text-decoration: none;
	height: 45px;
	line-height: 45px;
	padding-left: 5px;
	display: inline-block;
}

.boxx2 .list-inline li  i {
	display: inline-block;
	width: 44px;
	height: 44px;
	font-size: 24px;
	padding-left: 12px;
	line-height: 45px;
	color: #fff;
	margin: auto 0;
}

.boxx2 .list-inline li .iblue {
	background-color: #69B6EA;
	float: left;
}

.boxx2 .list-inline li .igreen {
	background-color: #91D33E;
	float: left;
}

.boxx2 .list-inline .blue {
	background-color: #D2E8F6;
}

.boxx2 .list-inline .green {
	background-color: #DFEECD;
}

.boxx2 span {
	font: 14px/16px "微软雅黑";
}

.titlediv {
	margin-top: 10px;
	float: left;
}

.count {
	font-size: 18px;
	color: #00568e;
	font-weight: bold;
	margin-top: 10px;
	float: left;
	margin-left: 5px
}

.mgr10 {
	margin-right: 10px
}

.fotl {
	float: left
}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css" />
<link href="${ctxStatic}/model/css/home/styles.css" rel="stylesheet" />
<script type="text/javascript">
	var ctx = '${ctx}';
	var wglist = eval('${wglist}');
	console.log(wglist);
	var barrio = eval(${barrio});
	var zoom;//全局变量zoom，获取地图缩放级别，判断地图放大还是缩小。
	var flg=true;//滚轮事件只监听一次flg。
	$(function() {
		getQyByXzqy();
		getTjJsonByXzqy(null,wglist[0].m1);
		initMap("dituContent",barrio.lng, barrio.lat,14);//创建和初始化地图
		createPolygon();
		zoom=map.getZoom();
		//监听地图缩放事件
		map.addEventListener("zoomend", function(){  
			if(map.getZoom()<zoom&&flg){
				scrollZoomOut(wglist[0].code);
				flg=false;
			}
	    });
		//地图加载完成监听事件
		map.addEventListener("tilesloaded", function(){  
			flg=true;
	    });
	});
	//获取企业列表
	function getQyByXzqy(xzqy) {
		$.ajax({
			url : "${ctx}/system/admin/xzqy/qyjson",
			type : "POST",
			data : {
				"xzqy" : xzqy
			},
			dataType : "json",
			success : function(data) {
				var qylist = eval(data);
				var html = "";
				for (var i = 0; i < qylist.length; i++) {
					html += "<li class=\"blue\"><i class=\"iblue fa fa-home\"></i> <a href=\"javascript:viewQY("+qylist[i].id+")\" title=\""+qylist[i].text+"\">&nbsp;" + qylist[i].text + " </a> </li>";
				}
				$('#list-inline').html(html);
			}
		});
	}
	//获取统计数据
	function getTjJsonByXzqy(xzqy,_json) {
		if(_json!=null)
		$("#ajtitle").text(_json+"列统企业");
		$.ajax({
			url : "${ctx}/system/admin/xzqy/tjjson",
			type : "GET",
			data : {
				"xzqy" : xzqy
			},
			dataType : "json",
			success : function(data) {
				var count=$(".count");
				count[0].innerText=data.qycount;
				count[1].innerText=data.childcount;
				count[2].innerText=data.yhpccount;
			}
		});
	}
	//点击多边形事件
	function clickPolygon(xzqy) {
		$.ajax({
			url : "${ctx}/system/admin/xzqy/twolevjson/"+xzqy,
			type : "POST",
			dataType : "json",
			success : function(data) {
				wglist=data;
				createPolygon();
				zoom=map.getZoom();
			}
		});
	}
	//滚轮返回上级网格区域
	function scrollZoomOut(xzqy) {
		$.ajax({
			url : "${ctx}/system/admin/xzqy/parentjson/"+xzqy,
			type : "POST",
			dataType : "json",
			success : function(data) {
				wglist=data;
				var parentb=wglist[0];
				getQyByXzqy(parentb.code);
				getTjJsonByXzqy(parentb.code,parentb.m1);
				createPolygon();
				zoom=map.getZoom();
			}
		});
	}

	//创建多边形图
	function createPolygon() {
		map.clearOverlays();
		createLabel();
		for (var i = 0; i < wglist.length; i++) {
			var mappoint = wglist[i].mappoint;
			var json = wglist[i].m1;
			var xzqy = wglist[i].code;
			if (mappoint != null && mappoint != "" && mappoint != "undefined") {
				var arry = mappoint.split("||");//lat，lnt
				var maparry = [];//坐标数组
				for (var j = 0; j < arry.length; j++) {
					var narry = arry[j].split(",");
					var m = new BMap.Point(narry[0], narry[1]);
					maparry.push(m);
				}
				var polygon = new BMap.Polygon(maparry, {
					strokeColor : getColor(i % 15),
					strokeWeight : 1.5,
					strokeOpacity : 0.5,
					fillOpacity : 0.5
				}); //创建多边形
				polygon.setFillColor(getColor(i % 15));
				map.addOverlay(polygon); //增加多边形
				if(i==0){
					map.setViewport(polygon.getPath());
				}
				(function() {
					var _json = json;
					var _polygon = polygon;
					var _xzqy = xzqy;
					_polygon.addEventListener("click", function() {
						//this.openInfoWindow(_iw);
						layer.msg(_json);
						getQyByXzqy(_xzqy);
						clickPolygon(_xzqy);
						getTjJsonByXzqy(_xzqy,_json);
					});
				})()
			}
		}
	}

	//创建多边形的中心点的标注
	function createLabel() {
		for (var i = 0; i < wglist.length; i++) {
			if (wglist[i].lat != null && wglist[i].lng != null) {
				var point = new BMap.Point(wglist[i].lng, wglist[i].lat);
				var iconImg = new BMap.Icon("${ctx}/static/model/images/map/bdmap_icon_l01.png", new BMap.Size(23, 25), {
					imageOffset : new BMap.Size(0, 25)
				// 设置图片偏移  
				});
				var marker1 = new BMap.Marker(point, {
					icon : iconImg
				});
				var label1 = new BMap.Label(wglist[i].m1, {
					offset : new BMap.Size(0, 0)
				});
				label1.setStyle({
					color : "#eee",
					fontSize : "9px",
					backgroundColor : "0.05",
					border : "0",
					fontWeight : "bold"
				});
				marker1.setLabel(label1);
				map.addOverlay(marker1);
			}
		}
	}

	function getColor(index) {
		var colorList = [ '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#03a9f4', '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD', '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0' ];
		return colorList[index];
	}

	
	function viewQY(id){
		//openDialogView("查看一企一档","${ctx}/bis/qyjbxx/view/"+id,"100%", "100%");
		layer.open({
		    type: 2,  
		    shift: 1,
		    area: ['100%', '100%'],
		    title: '查看一企一档',
	        maxmin: true, 
		    content: "${ctx}/bis/qyjbxx/view/"+id ,
		}); 	
	}
</script>
</head>
<body>
   <div class="container-fluid bggrey">
      <div class="toptips mgt5 mgb10"></div>
      <div class="col-lg-8 bs-example mgt10 " style="margin-right:10px;box-shadow: 1px 1px 5px #ddd;">
         <div class="ajtitle">
           网格总览
            <a class="pull-right" href="#"></a>
         </div>
         <div class="col-lg-12">
            <div class="bs-example mgt10" style="height: 660px" id="dituContent"></div>
         </div>
      </div>
      <div class="col-lg-1  bs-example mgt10" style="width:10%;box-shadow: 1px 1px 5px #ddd;">
         <div>
            <div class="icon" style="background-image:url('${ctx}/static/model/images/wgh/qycount.png');"></div>
            <div class="fotl">
               <div class="titlediv">企业概况</div>
               <br>
               <div class="count">123</div>
            </div>
         </div>
      </div>
      <div class="col-lg-1  bs-example mgt10" style="margin-left:1.2%;margin-right:1.2%;width:10%;box-shadow:1px 1px 5px #ddd;">
         <div>
            <div class="icon" style="background-image:url('${ctx}/static/model/images/wgh/zwg.png');"></div>
            <div class="fotl">
               <div class="titlediv">子网格数量</div>
               <br>
               <div class="count">123</div>
            </div>
         </div>
      </div>
      <div class="col-lg-1  bs-example mgt10" style="width:10%;box-shadow:1px 1px 5px #ddd;">
         <div>
            <div class="icon" style="background-image:url('${ctx}/static/model/images/wgh/xjwgd.png');"></div>
            <div class="fotl">
               <div class="titlediv">巡检网格点</div>
               <br>
               <div class="count">123</div>
            </div>
         </div>
      </div>
      <div class="boxx2 col-lg-4  bs-example mgt10" style="width: 32.5%;box-shadow:1px 1px 5px #ddd;">
         <div id="ajtitle"class="ajtitle">
            斜桥镇列统企业
         </div>
         <ul id="list-inline" class="list-inline" style="width:100%;height:547px;overflow-y: scroll">
         </ul>
      </div>
   </div>
</body>
</html>
