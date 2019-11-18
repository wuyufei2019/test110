<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>网格区域</title>
	<meta name="decorator" content="default"/>

<style type="text/css">
    .BMap_cpyCtrl{ display:none; }  
    .anchorBL{ display:none;}   
    #dituContent label{max-width:none;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
 <!--加载鼠标绘制工具-->  
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script> 
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>  
<script type="text/javascript">

var ctx='${ctx}';
var wgid='${wgid}';
var wglist = eval('${wglist}');
$(function() {
	$("#dituContent").height($(window).height()-$("#divhigh").outerHeight());
	initMap("dituContent",'${bro.lng}','${bro.lat}',16);//创建和初始化地图
	//创建和初始化地图函数：
    createPolygon();
    createLabel();
});



var allOverlay=[]; //画图覆盖物保存
var doneDraw = 0; // 判断图形是否绘制结束
var startDraw = 0; // 判断图形是否处于绘制状态
var polygon;//多边形
var overlay;//多边形的焦点坐标数组
var unsetWg="";//未设置中心点的网格区域
//创建多边形图
function createPolygon() {
for(var i=0;i<wglist.length;i++){
	var json=wglist[i].m1;
	var mappoint=wglist[i].mappoint;
	if(mappoint!=null&&mappoint!=""&&mappoint!="undefined"){
	var arry = mappoint.split("||");//lat，lnt
	var maparry = [];//坐标数组
	var narry=[];
	 for (var j = 0; j < arry.length; j++) {
	    narry = arry[j].split(",");
		var m = new BMap.Point(narry[0], narry[1]);
		maparry.push(m);
	}  
	 var polygon = new BMap.Polygon(maparry, {
		strokeColor : "red",
		strokeWeight : 2,
		strokeOpacity : 0.5,
		fillOpacity : 0.3
	}); //创建多边形
	
	map.addOverlay(polygon); //增加多边形
	(function() {
		var _json=json;
		var _polygon = polygon;
		_polygon.addEventListener("click", function() {
			//this.openInfoWindow(_iw);
			layer.msg(_json);
		});
	})()
	}
}
	//map.setViewport(polygon.getPath());
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
				color : "#1e2422",
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

function addmap(searchcon){	
	//var map = new BMap.Map("dituContent");
	var lat='${bro.lat}';
    var lng='${bro.lng}';
    var point;//定义一个中心点坐标
    if(lat==null||lng==null||lat==""||lng=="")
    	point=new BMap.Point(116.403119,39.919141);
    else
   		point=new BMap.Point(lng,lat);
	map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
	map.enableScrollWheelZoom();//启用滚轮放大缩小，默认禁用。
	map.centerAndZoom(point, 16);
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map}
	});
	local.search(searchcon);

	/* map.addEventListener("click", function(e){	
		console.log(e);
		locx=e.point.lng;
		locy=e.point.lat;
		var now_point =  new BMap.Point(e.point.lng, e.point.lat );
		marker.setPosition(now_point);//设置覆盖物位置
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	}); */
	
}


// 创建InfoWindow
function createInfoWindow(json) {
	var iw = new BMap.InfoWindow('<div style="width:280px;height:40px; cursor: pointer;"  ><p style="font-size:14px;">名称：' + json.m1 + '</br>地址：'+ json.address + '</br></p></div>');
	//var iw = new BMap.InfoWindow('<div style="width:280px;height:40px; cursor: pointer;" onclick="onShow('+ json.id+ ')" ><p style="font-size:14px;">名称：' + json.title + '</br>地址：'+ json.address + '</br></p></div>');
	return iw;
}
// 创建InfoWindow
function createWgInfoWindow(json) {
	var iw = new BMap.InfoWindow('<div style="width:280px;height:40px; cursor: pointer;" ><p style="font-size:14px;">'+json.m1+'</p></div>');
	//var iw = new BMap.InfoWindow('<div style="width:280px;height:40px; cursor: pointer;" onclick="onShow('+ json.id+ ')" ><p style="font-size:14px;">名称：' + json.title + '</br>地址：'+ json.address + '</br></p></div>');
	return iw;
}
// 创建一个Icon
function createIcon(json) {
	var icon = new BMap.Icon(json, new BMap.Size(24, 24));// , new
																// BMap.Size(23,25),{imageOffset:
																// new
																// BMap.Size(-46,-21),infoWindowOffset:new
																// BMap.Size(17,1),offset:new
																// BMap.Size(9,25)});
	return icon;
}
//多边形画图，保存图形
var overlaycomplete = function(e){ 
	e.overlay.enableEditing();//开启编辑
    polygon = e.overlay;
    
    polygon.addEventListener("click", function() {
    	savePolygon();
	});
    
    savePolygon();
};

//保存网格区域
function savePolygon(){
	
	layer.confirm('确定保存图形?', {icon: 3, title:'提示'}, function(index){
		
		var hs=polygon.getPath();
		var mappoint="";
		for(var i=0;i<hs.length;i++){
			if(i!=hs.length-1)
			mappoint+=hs[i].lng+","+hs[i].lat+"||";
			else
			mappoint+=hs[i].lng+","+hs[i].lat;
		}
		
		var rs;
		$.ajax({
	        type:"POST",
	        url:ctx+"/system/admin/xzqy/updmappoint",
	        dataType: 'text',
	        data:{"id":${bro.id},"mappoint":mappoint},
	        success:function(data){
	        	if(data=='error'){
	        		layer.msg("保存失败！");
	        		parent.dg.treegrid('reload');
	        	}else{
	        		parent.dg.treegrid('reload');
	        		var index1 = parent.layer.getFrameIndex(window.name);
	        		layer.msg("保存成功！");
				    setTimeout(function(){
				    	parent.layer.close(index1);
				    	},1000);//弹出结果信息后一秒钟关闭当前层；
	        	}
	        }
	    });
		});
}

//画多边形
function toPolygon(){
	mapclear();//清除已有覆盖物
	if(startDraw==0){
		var styleOptions = {  
	    	strokeColor : "blue",
			strokeWeight : 3,
			strokeOpacity : 0.4
	    }
	    //实例化鼠标绘制工具  
	    var drawingManager = new BMapLib.DrawingManager(map, {  
	        isOpen: true, //是否开启绘制模式  
	        polygonOptions: styleOptions//多边形的样式  
	    });
	drawingManager.open();
    drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);
    startDraw=1;
	}
}

function mapclear() {  //清除覆盖物
		map.removeOverlay(polygon);//移除多边形
		for(var i = 0; i < allOverlay.length; i++){  
	        map.removeOverlay(allOverlay[i]);  
	    }  
	    allOverlay.length = 0;
	doneDraw = 0;
	startDraw=0;
}  

</script>


</head>
<body>
<c:if test="${action ne 'view'}" >
<div id="divhigh" style="padding: 5px;">
<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"  onclick="toPolygon()">画区域</button>
<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"   onclick="mapclear()">重置</button>
<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
      
</div>
</c:if>

<div style="height:100%;width:100%;position:absolute;" id="dituContent"></div>
  
<div id="select_dlg" style="display:none;height:300px"  >
            <table id="areadata"></table> 
          </div>
</body>
</html>
