$(function() {
	$.ajax({
		type : "POST",
		url : ctx + "/erm/yjdw/maplist/",
		dataType : 'json',
		success : function(data) {
			if (data.data != undefined) {
				window.searchClass = new SearchClass();
				searchClass.setData(data.data);
				addMarker(data.data);
			}
		}
	});
});

/*地图搜索部分*/
// 创建InfoWindow
function createInfoWindow(json) {
	var iw = new BMap.InfoWindow('<div style="width:280px;height:40px; cursor: pointer;"  ><p style="font-size:14px;">名称：' + json.title + '</br>地址：'+ json.address + '</br></p></div>');
	return iw;
}
// 创建一个Icon
function createIcon(json) {
	var icon = new BMap.Icon(ctx + json, new BMap.Size(40, 40));// , new
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
		alert("数据不存在!");
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
}
// 重置返回所有结果
function reset() {
	// s:{''只返回找到的结果|all返回所有的}
	 $('#keyword').textbox('setValue','');//设值
	var dd = searchClass.search({
		k : "title",
		d : "显示全部",
		s : "all"
	});
	addMarker(dd);// 向地图中添加marker
}
// 创建marker
window.addMarker = function(data) {
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