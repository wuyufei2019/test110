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
</style>
<script type="text/javascript"
     src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
<script type="text/javascript">
	var ctx = '${ctx}';
	var wgid = '${wgid}';
	var qylist=eval('${qylist}'); 
	var polygon;//多边形（网格区域）
	
	$(function() {
		var html="";
		for (var i = 0; i < qylist.length; i++) {
			html += "<div style=\"margin-bottom: 10px; margin-left:10px\">"
					+ "<a onclick=\"searchqy('"+qylist[i].m1+"')\"style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\">"
					+ qylist[i].m1
					+ "</a>"
					+ "</div>";
			
		}
		$('#qylist').html(html);

		initMap("dituContent");//创建和初始化地图
		addMarker(qylist);//向地图中添加marker
		createPolygon('${mappoint}');

	});


	//创建多边形图
		function createPolygon(mappoint) {
		if(mappoint!=""){
			var arry = mappoint.split("||");//lat，lnt
			var maparry = [];//坐标数组
			var narry;
			var m;
			for (var i = 0; i < arry.length; i++) {
				narry = arry[i].split(",");
				m = new BMap.Point(narry[0], narry[1]);
				maparry.push(m);
			}
			 polygon = new BMap.Polygon(maparry, {
				strokeColor : "red",
				strokeWeight : 2,
				strokeOpacity : 0.5,
				fillOpacity : 0.3
			}); //创建多边形
			map.addOverlay(polygon); //增加多边形
			map.setViewport(polygon.getPath());
		}
	}
	    
		function createInfoWindow(json) {
			var iw = new BMap.InfoWindow('<div style="width:280px;height:40px; cursor: pointer;"  ><p style="font-size:14px;">名称：' + json.m1 + '</br>地址：'+ json.address + '</br></p></div>');
			//var iw = new BMap.InfoWindow('<div style="width:280px;height:40px; cursor: pointer;" onclick="onShow('+ json.id+ ')" ><p style="font-size:14px;">名称：' + json.title + '</br>地址：'+ json.address + '</br></p></div>');
			return iw;
		}
	
 
	// 创建一个Icon
	function createIcon(json) {
		var icon = new BMap.Icon(json, new BMap.Size(24, 24)); 
		return icon;
	}
 
	// 创建marker
	function addMarker(data) {
		for (var i = 0; i < data.length; i++) {
			var json = data[i];// 整数据
			var p0 = json.lng;// 经度
			var p1 = json.lat;// 纬度
			var point = new BMap.Point(p0, p1);
			var iconImg = createIcon("${ctx}/static/model/images/map/bdmap_icon_l01.png");
			var marker = new BMap.Marker(point, {
				icon : iconImg
			});
			var iw = createInfoWindow(i);
			var label = new BMap.Label(json.m1, {
				"offset" : new BMap.Size(22, 22)
			});
			map.addOverlay(marker);
			(function() {
				var _json = json;
				var _iw = createInfoWindow(_json);
				var _marker = marker;
				_marker.addEventListener("onclick", function() {
					this.openInfoWindow(_iw);
				});
			})()
		}
	}
</script>
</head>
<body>

     <div class="easyui-panel" style="width:100%;height:100%;">
          <div class="easyui-layout" data-options="fit:true">
               <div collapsible="true"title="绑定企业" data-options="region:'west'" style="width:300px;height:100%">
                    <div id="qylist"></div></div>
               <div data-options="region:'center'" style="width:100%;height:100%">
                    <div style="height:100%;width:100%;position:absolute;" id="dituContent"></div>

               </div>
          </div>
     </div>
     </div>

</body>
</html>
