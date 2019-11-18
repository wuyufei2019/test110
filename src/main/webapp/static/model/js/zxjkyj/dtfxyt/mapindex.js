var markerArr;

$(function() {
	$.ajax({
		type : "POST",
		url : ctx + "/fmewx/sjfxx/showqylocation/",
		dataType : 'json',
		success : function(data) {
			if (data != undefined) {
				initMap(); // 允许滚轮缩放
			    var points =eval(data);
			    if(!isSupportCanvas()){
			    	alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
			    }
			    heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":30});
				map.addOverlay(heatmapOverlay);
				heatmapOverlay.setDataSet({data:points,max:100});
			}
		}
		
	});
	
	//判断浏览区是否支持canvas
    function isSupportCanvas(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    }
    function setGradient(){
     	var gradient = {};
     	var colors = document.querySelectorAll("input[type='color']");
     	colors = [].slice.call(colors,0);
     	colors.forEach(function(ele){
			gradient[ele.getAttribute("data-key")] = ele.value; 
     	});
        heatmapOverlay.setOptions({"gradient":gradient});
    }


	// 创建和初始化地图函数：
	function initMap(v) {
		createMap();// 创建地图
		setMapEvent();// 设置地图事件
		addMapControl();// 向地图添加控件
//		 addMarker();//向地图中添加marker
//		 addRemark();//向地图中添加文字标注
		window.searchClass = new SearchClass();
		searchClass.setData(v);
		reset();// 重置
	}

	// 创建地图函数：
	function createMap() {
		var map = new BMap.Map("container", {mapType:BMAP_SATELLITE_MAP});//在百度地图容器中创建一个地图
//		var map = new BMap.Map("container");//在百度地图容器中创建一个地图
		var point = new BMap.Point(116.403119,39.919141);// 定义一个中心点坐标
		map.centerAndZoom(point, 15);// 设定地图的中心点和坐标并将地图显示在地图容器中
		map.addControl(new BMap.NavigationControl());
		map.setCurrentCity("北京"); // 设置地图显示的城市 此项是必须设置的
		map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
		window.map = map;// 将map变量存储在全局
	}

	// 地图事件设置函数：
	function setMapEvent() {
		map.enableDragging();// 启用地图拖拽事件，默认启用(可不写)
		//map.enableScrollWheelZoom();// 启用地图滚轮放大缩小
		// map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
		map.disableDoubleClickZoom();
		map.enableKeyboard();// 启用键盘上下左右键移动地图
	}

	// 地图控件添加函数：
	function addMapControl() {
		// 向地图中添加比例尺控件
		var ctrl_sca = new BMap.ScaleControl({
			anchor : BMAP_ANCHOR_TOP_LEFT
		});
		map.addControl(ctrl_sca);
	}

 });



