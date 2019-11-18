<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>区域人员清点</title>
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
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
<!--加载鼠标绘制工具-->
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>

<script type="text/javascript">
	var ctx = '${ctx}';

	var markerArr;
	var map;
	$(function() {

		$.ajax({
			type : "POST",
			url : ctx + "/zxjkyj/edmjc/maplist",
			dataType : 'json',
			async:false,
			success : function(data) {
				if (data != undefined) {
					markerArr = eval(data).data;
					initMap("dituContent");//创建和初始化地图
				}
			}
		});
		addMarker();//向地图中添加marker

		//创建marker
		function addMarker() {
			for (var i = 0; i < markerArr.length; i++) {
				var json = markerArr[i];
				var p0 = json.lng;
				var p1 = json.lat;
				var point = new BMap.Point(p0, p1);

				var iconImg = createIcon('/static/model/images/map/edm.png');
				var marker = new BMap.Marker(point, {
					icon : iconImg
				});
				map.addOverlay(marker);

				var iw = createInfoWindow(i);

				(function() {
					var index = i;
					var _iw = createInfoWindow(i);
					var _ih = hoverInfoWindow(i);
					var _marker = marker;

					_marker.addEventListener("click", function() {
						this.openInfoWindow(_iw);
					});
					_marker.addEventListener("onmouseover", function() {
						this.openInfoWindow(_ih);
					});

					if (!!json.isOpen) {
						_marker.openInfoWindow(_iw);
					}
				})()

			}
		}
		//创建InfoWindow
		function createInfoWindow(i) {
			var json = markerArr[i];
			var iw = new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;’>企业：" + json.qyname + "</br>部门：<br>" + json.html.replace(/,/g, '<br>') + "<br><span style='color:red'>总和:" + json.total + "</span></br></p>");
			return iw;
		}
		//创建悬浮InfoWindow
		function hoverInfoWindow(i) {
			var json = markerArr[i];
			var iw = new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;’>企业：" + json.qyname + "<br>总和:" + json.total + "</p>");
			return iw;
		}
		//创建一个Icon
		function createIcon(json) {
			var icon = new BMap.Icon(ctx + json, new BMap.Size(100, 100));
			return icon;
		}

		//坐标转换完之后的回调函数
		translateCallback = function(point) {
			var iconImg = createIcon('/static/model/images/map/people_icon.png');
			var marker = new BMap.Marker(point, {
				icon : iconImg
			});
			map.addOverlay(marker);
			//alert("转化为百度坐标为："+point.lng + "," + point.lat);
		}

	});

	var p = [];// 用来存储区域的点
	var allOverlay = []; //画图覆盖物保存
	var doneDraw = 0; // 判断图形是否绘制结束
	var startDraw = 0; // 判断图形是否处于绘制状态
	var circle; //圆形
	var type; //1多边形，2圆形

	var overlay;

	var overlaycomplete = function(e) {
		allOverlay.push(e.overlay);
		for (var i = 0; i < allOverlay.length; i++) {
			overlay = allOverlay[i].getPath();
		}

		polygon = new BMap.Polygon(overlay, {
			strokeColor : "blue",
			strokeWeight : 0,
			strokeOpacity : 0
		}); //创建多边形
		map.addOverlay(polygon);
		polygon.addEventListener("click", function(e) {
			polygonQD();
		});
	};

	function polygonQD() {
		ids = "";//人员id
		var rydata = [];
		//统计有多少坐标在区域内
		var sum = 0;

		for (var i = 0; i < markerArr.length; i++) {
			var json = markerArr[i];
			var point = new BMap.Point(json.lng, json.lat);
			var c = PointInPoly(point, overlay);
			if (c) {
				sum++;
				rydata.push(json);
				ids += json.id + ",";
			}
		}

		showdlg("区域内一共有 " + sum + " 人!", ids, rydata);
	}

	//圆形人数清点
	function circleQD() {
		ids = "";
		var sum = 0;
		var rydata = [];
		for (var i = 0; i < markerArr.length; i++) {
			var json = markerArr[i];
			var point = new BMap.Point(json.lng, json.lat);
			var c = isPointInCircle(point, circle);
			if (c) {
				sum++;
				rydata.push(json);
				ids += json.id + ",";
			}
		}
		showdlg("区域内一共有 " + sum + " 人!", ids, rydata);
	}

	//绘制圆鼠标事件 
	function MapAddCircle(e) {
		startDraw = 1;
		if (doneDraw == 0) {
			var pt = new BMap.Point(e.point.lng, e.point.lat);
			p.push(pt);
			circle = new BMap.Circle(pt, 1);
			map.addOverlay(circle); //增加圆
			circle.enableEditing(); //  开启圆编辑功能
			circle.setStrokeOpacity(0.4); //设置圆形的边线透明度
			circle.setStrokeWeight(3); //设置圆形边线的宽度
			circle.setStrokeColor("blue");
			circle.addEventListener("click", function(e) {
				circleQD();
			}); //添加圆点击事件
			doneDraw = 1;
		}
	}

	//计算一个点是否在多边形里,参数:（点,多边形数组）
	function PointInPoly(pt, poly) {
		for (var c = false, i = -1, l = poly.length, j = l - 1; ++i < l; j = i)
			((poly[i].lat <= pt.lat && pt.lat < poly[j].lat) || (poly[j].lat <= pt.lat && pt.lat < poly[i].lat)) && (pt.lng < (poly[j].lng - poly[i].lng) * (pt.lat - poly[i].lat) / (poly[j].lat - poly[i].lat) + poly[i].lng) && (c = !c);
		return c;
	}

	//计算一个点是否在多边形里
	function IsPtInPoly(ALon, ALat, APoints) {
		var iSum = 0, iCount;
		var dLon1, dLon2, dLat1, dLat2, dLon;
		if (APoints.length < 3)
			return false;
		iCount = APoints.length;
		for (var i = 0; i < iCount; i++) {
			if (i == iCount - 1) {
				dLon1 = APoints[i].lng;
				dLat1 = APoints[i].lat;
				dLon2 = APoints[0].lng;
				dLat2 = APoints[0].lat;
			} else {
				dLon1 = APoints[i].lng;
				dLat1 = APoints[i].lat;
				dLon2 = APoints[i + 1].lng;
				dLat2 = APoints[i + 1].lat;
			}
			//以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上  
			if (((ALat >= dLat1) && (ALat < dLat2)) || ((ALat >= dLat2) && (ALat < dLat1))) {
				if (Math.abs(dLat1 - dLat2) > 0) {
					//得到 A点向左射线与边的交点的x坐标：  
					dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - ALat)) / (dLat1 - dLat2);
					if (dLon < ALon)
						iSum++;
				}
			}
		}
		if (iSum % 2 != 0)
			return true;
		return false;
	}

	//判断一个点是否在圆内
	function isPointInCircle(point, circle) {

		//point与圆心距离小于圆形半径，则点在圆内，否则在圆外
		var c = circle.getCenter();
		var r = circle.getRadius();
		var dis = map.getDistance(point, c);
		if (dis <= r) {
			return true;
		} else {
			return false;
		}
	}

	//画多边形
	function toPolygon() {
		map.removeEventListener("click", MapAddCircle); //移除画圆事件
		mapclear();//清除已有覆盖物
		if (startDraw == 0) {
			var styleOptions = {
				strokeColor : "blue",
				strokeWeight : 3,
				strokeOpacity : 0.4
			}
			//实例化鼠标绘制工具  
			var drawingManager = new BMapLib.DrawingManager(map, {
				isOpen : false, //是否开启绘制模式  
				drawingToolOptions : {
					anchor : BMAP_ANCHOR_TOP_RIGHT, //位置  
					offset : new BMap.Size(5, 5)
				//偏离值  
				},
				polygonOptions : styleOptions
			//多边形的样式  
			});
			drawingManager.open();
			drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
			drawingManager.addEventListener('overlaycomplete', overlaycomplete);
			startDraw = 1;
			type = 1;
		}
	}

	//画圆形
	function toCircle() {
		mapclear();
		if (startDraw == 0) {
			map.setDefaultCursor("crosshair");//将鼠标变成十字架
			map.addEventListener("click", MapAddCircle);//向地图中画多边形	
			startDraw = 1;
			type = 2;
		}
	}

	function mapclear() { //清除覆盖物
		if (type == 1) {
			map.removeOverlay(polygon);//移除多边形
			for (var i = 0; i < allOverlay.length; i++) {
				map.removeOverlay(allOverlay[i]);
			}
			allOverlay.length = 0;
		} else if (type == 2) {
			map.removeOverlay(circle);//移除圆形
		}
		p = [];// 清空存放坐标的数组
		doneDraw = 0;
		startDraw = 0;
	}

	//清点人数
	function qingdain() {
		if (startDraw == 1) {
			if (type == 1) {
				polygonQD();

			} else if (type == 2) {
				circleQD();
			}
		} else {
			$.messager.alert("提示", "请先画区域！");
		}
	}

	//人员导出
	function exportRY(ids) {
		if (ids == null || ids == "") {
			$.messager.alert("提示", "该区域无人员！");
		} else {
			ids = ids.substring(0, ids.length - 1);
			//$.post(ctx+'/ead/ryqd/export',{ids:ids});
			url = ctx + "/ead/ryqd/export/" + ids;
			window.location.href = url;
		}
	}

	//消息对话框，content提示内容，ids人员id集合,data被清点的人员信息
	function showdlg(content, ids, rydata) {
		if (rydata.length == 0) {
			layer.msg("该区域无人员！", {
				time : 1000
			});
			return;
		}
		var dg;
		layer.open({
			type : 1,
			title : '区域信息',
			area : [ '600px', '400px' ],
			content : $("#select_dlg"),
			btn : [ '关闭' ],
			success : function(layero, index) {
				dg = $('#areadata').datagrid({
					fit : true,
					fitColumns : true,
					border : false,
					idField : 'ID',
					striped : true,
					pagination : false,
					rownumbers : true,
					nowrap : false,
					pageNumber : 1,
					scrollbarSize : 0,
					singleSelect : true,
					striped : true,
					columns : [ [ {
						field : 'qyname',
						title : '企业名称',
						sortable : false,
						width : 100,
						align : 'center'
					}, {
						field : 'total',
						title : '总数',
						sortable : false,
						width : 50,
						align : 'center'
					}, ] ],
					onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
					},
					checkOnSelect : true,
					selectOnCheck : false,
				});

				dg.datagrid('loadData', {
					"rows" : rydata
				});//datagrid加载数据
			},

			cancel : function(index) {
			}
		});
	}
</script>


</head>
<body>
   <div style="padding: 5px;">
      <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="toPolygon()">多边形</button>
      <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="toCircle()">圆形</button>
      <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="qingdain()">人数统计</button>
      <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="mapclear()">全部</button>
   </div>


   <div style="height:100%;width:100%;position:absolute;" id="dituContent"></div>

   <div id="select_dlg" style="display:none;height:300px">
      <table id="areadata"></table>
   </div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$("#dituContent").height($(this).window.height() - 36);
	});
</script>