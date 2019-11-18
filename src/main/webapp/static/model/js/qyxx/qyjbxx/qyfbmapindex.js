function getJson(type){
	var d;
	$.ajax({
		type : "POST",
		url : ctx+"/bis/qyjbxx/maplist?type="+type,
		async:false,
		dataType : 'json',
		success : function(data) {
			d=data.data;
			if (d) {
				pointCollection(data.data,type);//向地图中添加marker
			}
		}
	});
	return d;
}
function getJsonByPoint(e){
	var d;
	$.ajax({
		type : "POST",
		url : ctx+"/bis/qyjbxx/findpoint?lng="+e.point.lng+"&lat="+e.point.lat,
		dataType : 'json',
		async:false,
		success : function(data) {
			if (data) {
				d=data;
			}
		}
	});
	return d;
}

	//创建多边形图
    function createPolygon(){
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
    


function onShow(i) {
	top.layer.open({
	    type: 2,  
	    area: ['900px', '480px'],
	    title: '查看企业基本信息',
        maxmin: true, 
	    content: ctx+"/bis/qyjbxx/view/"+i,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	}); 	
}
function showSp(i) {
	top.layer.open({
		type: 2,  
		area: ['1100px', '450px'],
		title: '查看企业视频信息',
		maxmin: true, 
		content: ctx+"/zxjkyj/spjk/showqysp/"+i,
		btn: ['关闭'],
		cancel: function(index){ 
		}
	}); 	
}

/*地图搜索部分*/
// 创建InfoWindow
function createInfoWindow(json) {
	var iw = new BMap.InfoWindow('<div style="width:280px;height:40px; cursor: pointer;" onclick="onShow('+json.id+')" ><p style="font-size:14px;">名称：' + json.title + '</br>地址：'+ json.address + '</br></p></div>');
	return iw;
}
// 创建一个Icon
function createIcon(json) {
	var icon = new BMap.Icon(ctx + json, new BMap.Size(31, 42));// , new
																// BMap.Size(23,25),{imageOffset:
																// new
																// BMap.Size(-46,-21),infoWindowOffset:new
																// BMap.Size(17,1),offset:new
																// BMap.Size(9,25)});
	return icon;
}
function SearchClass(data) {
	this.datas = data;
}

SearchClass.prototype.search = function(rule) {
	if (this.datas == null) {
		return false;
	}

	var reval = [];
	var datas = this.datas;
	var len = datas.length;
	var me = this;
	var ruleReg = new RegExp(this.trim(rule.d));
	var hasOpen = false;

	var addData = function(data, isOpen) {
		// 第一条数据打开信息窗口
		if (isOpen && !hasOpen) {
			hasOpen = true;
			data.isOpen = 1;
		} else {
			data.isOpen = 0;
		}
		reval.push(data);
	}
	var getData = function(data, key) {
		var ks = me.trim(key).split(/\./);
		var i = null, s = "data";
		if (ks.length == 0) {
			return data;
		} else {
			for (var i = 0; i < ks.length; i++) {
				s += '["' + ks[i] + '"]';
			}
			return eval(s);
		}
	}
	for (var cnt = 0; cnt < len; cnt++) {
		var data = datas[cnt];
		var d = getData(data, rule.k);
		if (rule.t == "single" && rule.d == d) {
			addData(data, true);
		} else if (rule.t != "single" && ruleReg.test(d)) {
			addData(data, true);
		} else if (rule.s == "all") {
			addData(data, false);
		}
	}
	return reval;
}
SearchClass.prototype.setData = function(data) {
	this.datas = data;
}
SearchClass.prototype.trim = function(str) {
	if (str == null) {
		str = "";
	} else {
		str = str.toString();
	}
	return str.replace(/(^[\s\t\xa0\u3000]+)|([\u3000\xa0\s\t]+$)/g, "");
}
//回车键事件
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
//    if(e && e.keyCode==27){ // 按 Esc 
//        //要做的事情
//      }
//    if(e && e.keyCode==113){ // 按 F2 
//         //要做的事情
//       }            
     if(e && e.keyCode==13){ // enter 键
         //要做的事情
    	 search('keyword');
    }
}; 
// ///
// 搜索方法 param{searchTypeRadio_name：搜索radio的名字,keyword_name:搜索文本框的id}
window.search = function(keyword_name) {
	// 获取页面dom
	var keyword = document.getElementById(keyword_name).value;
	if(keyword&&keyword!=null){
		// 获取dom的值
		var dd = searchClass.search({
			k : "title",
			d : keyword,
			s : ""
		});
		saddMarker(dd);// 向地图中添加marker
	}else{
		reset();
	}
	createPolygon();
}
// 重置返回所有结果
function reset() {
	// s:{''只返回找到的结果|all返回所有的}
	$("#keyword").textbox('setValue','');
	var dd = searchClass.search({
		k : "title",
		d : "显示全部",
		s : "all"
	});
	map.clearOverlays();
	getJson(4);
	getJson(3);
	getJson(2);
	getJson(1);
	createPolygon();
}
// 创建marker
window.addMarker = function(data,type) {
	map.clearOverlays();
	for (var i = 0; i < data.length; i++) {
		var json = data[i];// 整数据
		var p0 = json.pointx;// 经度
		var p1 = json.pointy;// 纬度
		var point = new BMap.Point(p0, p1);
		var iconImg = createIcon(json.icon);
		var marker = new BMap.Marker(point, {
			icon : iconImg
		});
		var iw = createInfoWindow(i);
		var label = new BMap.Label(json.title, {
			"offset" : new BMap.Size(22, 22)
		});
		map.addOverlay(marker);
		(function() {
			var _json = json;
			var _iw = createInfoWindow(_json);
			var _marker = marker;
			_marker.addEventListener("mouseover", function() {
				this.openInfoWindow(_iw);
			});
		})()
	}
}
// 创建所有颜色的海量点
function allPointCollection() {
	var color;
	var data;
	var points = []; 
	var options = {
			size: BMAP_POINT_SIZE_NORMAL,
			shape: BMAP_POINT_SHAPE_CIRCLE,
			color:'',
	}
	var pointCollection;
	if (document.createElement('canvas').getContext) {  // 判断当前浏览器是否支持绘制海量点
		data=getJson(4);
		options.color="#2894FF";
		for (var i = 0; i < data.length; i++) {
			points.push(new BMap.Point(data[i].pointx, data[i].pointy));
		}
		pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
		
		map.addOverlay(pointCollection);  // 添加Overlay
		
		data=getJson(3);
		options.color="#F9F900";
		points = [];  // 添加海量点数据
		for (var i = 0; i < data.length; i++) {
			points.push(new BMap.Point(data[i].pointx, data[i].pointy));
		}
		pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
		map.addOverlay(pointCollection); 
		
		data=getJson(2);
		options.color="#FFA042";
		points = [];  
		for (var i = 0; i < data.length; i++) {
			points.push(new BMap.Point(data[i].pointx, data[i].pointy));
		}
		pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
		map.addOverlay(pointCollection); 
		
		data=getJson(1);
		options.color="#FF5151";
		points = [];// 添加海量点数据
		for (var i = 0; i < data.length; i++) {
			points.push(new BMap.Point(data[i].pointx, data[i].pointy));
		}
		pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
		map.addOverlay(pointCollection); 
		
		pointCollection.addEventListener('click', function (e) {
			var d=getJsonByPoint(e);
			var iw=createInfoWindow(d);
			map.openInfoWindow(iw,e.point);
		});
//		pointCollection.addEventListener('click', function (e) {
//			alert(11);
//			var d=getJsonByPoint(e);
//			onShow(d.id);
//		});
		return pointCollection;
	} else {
		alert('当前浏览器不支持海量点查看，请在chrome、safari、IE8+以上浏览器查看地图');
	} 
}
// 创建海量点
 function pointCollection(data,type) {
	var color;
	if(type==1) color="#FF5151";
	if(type==2) color="#FFA042";
	if(type==3) color="#F9F900";
	if(type==4) color="#2894FF";
    if (document.createElement('canvas').getContext) {  // 判断当前浏览器是否支持绘制海量点
        var points = [];  // 添加海量点数据
        for (var i = 0; i < data.length; i++) {
          points.push(new BMap.Point(data[i].pointx, data[i].pointy));
        }
        var options = {
            size: BMAP_POINT_SIZE_NORMAL,
            shape: BMAP_POINT_SHAPE_CIRCLE,
            color: color,
        }
        var pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
        pointCollection.addEventListener('click', function (e) {
        	var d=getJsonByPoint(e);
			var iw=createInfoWindow(d);
			map.openInfoWindow(iw,e.point);
        });
        map.addOverlay(pointCollection);  // 添加Overlay
    } else {
        alert('当前浏览器不支持海量点查看，请在chrome、safari、IE8+以上浏览器查看地图');
    } 
}
//创建marker(编辑部分)
window.saddMarker = function(data) {
	map.clearOverlays();
	for (var i = 0; i < data.length; i++) {
		var json = data[i];// 整数据
		var p0 = json.pointx;// 经度
		var p1 = json.pointy;// 纬度
		var point = new BMap.Point(p0, p1);
		var iconImg = createIcon(json.icon);
		var marker = new BMap.Marker(point, {
			icon : iconImg
		});
		var iw = createInfoWindow(i);
		var label = new BMap.Label(json.title, {
			"offset" : new BMap.Size(22, 22)
		});
		marker.setLabel(label);
		map.addOverlay(marker);
		label.setStyle({
			borderColor : "#808080",
			color : "#333",
			cursor : "pointer"
		});

		(function() {
			var _json = json;
			var _iw = createInfoWindow(_json);
			var _marker = marker;
				_marker.addEventListener("click", function() {
					this.openInfoWindow(_iw);
				});
				
			_iw.addEventListener("open", function() {
				_marker.getLabel().hide();
			})
			_iw.addEventListener("close", function() {
				_marker.getLabel().show();
			})
			label.addEventListener("click", function() {
				_marker.openInfoWindow(_iw);
			})
			if (!!json.isOpen) {
				label.hide();
				_marker.openInfoWindow(_iw);
			}
		})()
	}
}