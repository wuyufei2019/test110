//定义时间轴控件函数
 function TimeControl() {
	// 默认停靠位置和偏移量
	this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
	this.defaultOffset = new BMap.Size(10, 50);
}

function clickrefresh(data) {
	map.clearOverlays();
	createPolygon();
	 if(whpdatas[data]){
		  points=whpdatas[data];
	  }else{
		  points=whpdatas.replace;
	  }
	  loadHeatMap(points,1);
}

// 加载热力图 
function loadHeatMap(data, maxvalue) {
	if (data != undefined) {
		if (!isSupportCanvas()) {
			alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
			return;
		}
		var points = eval(data);
		heatmapOverlay = new BMapLib.HeatmapOverlay({
			"radius" : 30
		});
		map.addOverlay(heatmapOverlay);
		heatmapOverlay.setDataSet({
			data : points,
			max : maxvalue
		});
	}

}

// 海量点数据
function getDateLoadCollection(type) {
	var data=getJson("/bis/qyjbxx/maplist?type=" + type);
	pointCollection(data.data, type);
}
function getJson(url, qyid) {
	var data;
	$.ajax({
		type : "POST",
		url : ctx + url,
		async : false,
		dataType : 'json',
		data : {
			'qyid' : qyid
		},
		success : function(d) {
			data=d;
		}
	});
	return data;
}

// 判断浏览区是否支持canvas
function isSupportCanvas() {
	var elem = document.createElement('canvas');
	return !!(elem.getContext && elem.getContext('2d'));
}
// 创建海量点
function pointCollection(data, type) {
	var color;
	if (type == 1)
		color = "#FF5151";
	if (type == 2)
		color = "#FFA042";
	if (type == 3)
		color = "#F9F900";
	if (type == 4)
		color = "#2894FF";
	if (document.createElement('canvas').getContext) { // 判断当前浏览器是否支持绘制海量点
		var points = []; // 添加海量点数据
		for (var i = 0; i < data.length; i++) {
			points.push(new BMap.Point(data[i].pointx, data[i].pointy));
		}
		var options = {
			size : BMAP_POINT_SIZE_NORMAL,
			shape : BMAP_POINT_SHAPE_CIRCLE,
			color : color,
		}
		var pointCollection = new BMap.PointCollection(points, options); // 初始化PointCollection
		pointCollection.addEventListener('click', function(e) {
			var d = getJsonByPoint(e);
			var iw = createInfoWindow(d);
			map.openInfoWindow(iw, e.point);
		});
		map.addOverlay(pointCollection); // 添加Overlay
	} else {
		alert('当前浏览器不支持海量点查看，请在chrome、safari、IE8+以上浏览器查看地图');
	}
}

// 点击marker点 根据经纬度确定企业返回信息
function getJsonByPoint(e) {
	var d;
	$.ajax({
		type : "POST",
		url : ctx + "/bis/qyjbxx/findpoint?lng=" + e.point.lng + "&lat=" + e.point.lat,
		dataType : 'json',
		async : false,
		success : function(data) {
			if (data) {
				d = data;
			}
		}
	});
	return d;
}

function setGradient() {
	var gradient = {};
	var colors = document.querySelectorAll("input[type='color']");
	colors = [].slice.call(colors, 0);
	colors.forEach(function(ele) {
		gradient[ele.getAttribute("data-key")] = ele.value;
	});
	heatmapOverlay.setOptions({
		"gradient" : gradient
	});
}
// 创建多边形图
function createPolygon() {
	if (mappoint != "") {
		var arry = mappoint.split("||");// lat，lnt
		var maparry = [];// 坐标数组
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
			fillOpacity : 0.001
		});
		map.addOverlay(polygon); // 增加多边形
		map.setViewport(polygon.getPath());
	}
	return "success";
}

// 创建多边形图
function createPolygons(wglist, colorlist) {
	var len = wglist.length;
	for (var i = 0; i < len; i++) {
		var mappoint = wglist[i].mappoint;
		if (mappoint != null && mappoint != "" && mappoint != "undefined") {
			var arry = mappoint.split("||");// lat，lnt
			var maparry = [];// 坐标数组
			for (var j = 0; j < arry.length; j++) {
				var narry = arry[j].split(",");
				var m = new BMap.Point(narry[0], narry[1]);
				maparry.push(m);
			}
			var polygon = new BMap.Polygon(maparry, {
				strokeColor : colorlist[i],
				strokeWeight : 1.5,
				strokeOpacity : 0.5,
				fillOpacity : 0.5
			}); // 创建多边形
			polygon.setFillColor(colorlist[i]);
			map.addOverlay(polygon); // 增加多边形
		}
	}
}

// 将hex表示方式转换为rgb表示方式(这里返回rgb数组模式)
function colorRgb(sColor) {
	var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
	var sColor = sColor.toLowerCase();
	if (sColor && reg.test(sColor)) {
		if (sColor.length === 4) {
			var sColorNew = "#";
			for (var i = 1; i < 4; i += 1) {
				sColorNew += sColor.slice(i, i + 1).concat(sColor.slice(i, i + 1));
			}
			sColor = sColorNew;
		}
		// 处理六位的颜色值
		var sColorChange = [];
		for (var i = 1; i < 7; i += 2) {
			sColorChange.push(parseInt("0x" + sColor.slice(i, i + 2)));
		}
		return sColorChange;
	} else {
		return sColor;
	}
};

// 创建InfoWindow
function createInfoWindow1(json) {
	var iw = new BMap.InfoWindow('<div style="width:280px;height:40px; cursor: pointer;" onclick="Show' + json.type + '(' + json.id + ')" ><p style="font-size:14px;">名称：' + json.m1 + '</br>地址：' + json.m33 + '</br></p></div>');
	return iw;
}
function Showwxgy(qyid) {
	layer.open({
		type : 1,
		area : [ '800px', '450px' ],
		title : '查看危高危工艺信息',
		maxmin : false,
		content : $("#select_type"),
		btn : [ '关闭' ],
		success : function(layero, index) {
			var dg = $('#areadata').datagrid({
				method : "post",
				url : ctx + '/bis/gwgy/tablist/' + qyid,
				fit : true,
				fitColumns : true,
				selectOnCheck : false,
				border : false,
				idField : 'id',
				striped : true,
				pagination : true,
				rownumbers : true,
				pageNumber : 1,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50 ],
				singleSelect : true,
				scrollbarSize : 0,
				columns : [ [ {
					field : 'M4',
					title : '高危工艺',
					sortable : false,
					width : 100
				}, {
					field : 'M2',
					title : '套数（套）',
					sortable : false,
					width : 100,
					align : 'center'
				} ] ],
				enableHeaderClickMenu : true,
				enableRowContextMenu : false
			});
		},
		cancel : function(index) {
		}
	});
}
function Showzdjg(qyid) {
	layer.open({
		type : 1,
		area : [ '800px', '450px' ],
		title : '查看重点监管危化品信息',
		maxmin : false,
		content : $("#select_type"),
		btn : [ '关闭' ],
		success : function(layero, index) {
			var dg = $('#areadata').datagrid({
				method : "post",
				url : ctx + '/bis/wlxx/list2',
				fit : true,
				queryParams : {
					'qyid' : qyid
				},
				fitColumns : true,
				selectOnCheck : false,
				border : false,
				idField : 'id',
				striped : true,
				pagination : true,
				rownumbers : true,
				pageNumber : 1,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50 ],
				singleSelect : true,
				scrollbarSize : 0,
				columns : [ [ {
					field : 'm1',
					title : '物料名称',
					sortable : false,
					width : 120
				}, {
					field : 'm2',
					title : '年用量/年产量(t)',
					sortable : true,
					width : 70,
					align : 'center',
					formatter : function(value, row, index) {
						if (value != null && value != "") {
							return value.toFixed(2);
						}
					}
				}, {
					field : 'm3',
					title : '最大储量(t)',
					sortable : true,
					width : 70,
					align : 'center',
					formatter : function(value, row, index) {
						if (value != null && value != "") {
							return value.toFixed(2);
						}
					}
				}, {
					field : 'm5',
					title : '储存方式',
					sortable : true,
					width : 50,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == '1')
							return value = '储罐';
						if (value == '2')
							return value = '桶装';
						if (value == '3')
							return value = '袋装';
						if (value == '4')
							return value = '其他';
					}
				}, {
					field : 'm8',
					title : '危化品类别',
					sortable : false,
					width : 80,
					align : 'center'
				}, {
					field : 'm12',
					title : '重点监管',
					sortable : true,
					width : 50,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == null || value == '')
							return value = '/';
						if (value == '0')
							return value = '否';
						if (value == '1')
							return value = '是';
					}
				}, {
					field : 'm13',
					title : '剧毒',
					sortable : true,
					width : 50,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == null || value == '')
							return value = '/';
						if (value == '0')
							return value = '否';
						if (value == '1')
							return value = '是';
					}
				}, {
					field : 'm14',
					title : '易制毒',
					sortable : true,
					width : 50,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == null || value == '')
							return value = '/';
						if (value == '0')
							return value = '否';
						if (value == '1')
							return value = '是';
					}
				} ] ],
				enableHeaderClickMenu : true,
				enableRowContextMenu : false
			});
		},
		cancel : function(index) {
		}
	});
}
function Showwxy(qyid) {
	layer.open({
		type : 1,
		area : [ '800px', '450px' ],
		title : '查看危高危工艺信息',
		maxmin : false,
		content : $("#select_type"),
		btn : [ '关闭' ],
		success : function(layero, index) {
			var dg = $('#areadata').datagrid({
				method : "post",
				url : ctx + '/bis/hazardIdentity/ajlist/' + qyid,
				fit : true,
				fitColumns : true,
				selectOnCheck : false,
				border : false,
				idField : 'ID',
				striped : true,
				pagination : true,
				rownumbers : true,
				pageNumber : 1,
				pageSize : 50,
				pageList : [ 50, 100, 150, 200, 250 ],
				scrollbarSize : 0,
				singleSelect : true,
				columns : [ [ {
					field : 'ID',
					title : 'ID',
					hidden : true
				}, {
					field : 'm1',
					title : '类别',
					width : 100
				}, {
					field : 'm2',
					title : '辨识物质',
					width : 150,
					align : 'center'
				}, {
					field : 'm3',
					title : '最大储量（t）',
					width : 70,
					align : 'center'
				}, {
					field : 'm4',
					title : '临界储量（t）',
					width : 70,
					align : 'center'
				},
				// {field:'m5',title:'计算',width:50,align:'center'}
				] ]
			});
		},
		cancel : function(index) {
		}
	});
}

// 创建InfoWindow
function createInfoWindow(json) {
	var param = json.id;
	var arry = json.M33_3.split("||");
	var data = getJson("/fxgk/fxjg/pmtjson", param);
	var wrap = "";
	for (var i = 0; i < data.length; i++) {
		var x = data[i].locx;
		var y = data[i].locy;
		var color = data[i].color;
		if (x != "" && y != "") {
			var top = y * 280 + "px";
			var left = x * 280 + "px";
			if (color == "1") {
				wrap += '<div class="ball" style="top:' + top + ';left:' + left + ';background:red"></div>';
			} else if (color == "2") {
				wrap += '<div class="ball" style="top:' + top + ';left:' + left + ';background:orange"></div>';
			} else if (color == "3") {
				wrap += '<div class="ball" style="top:' + top + ';left:' + left + ';background:yellow"></div>';
			} else if (color == "4") {
				wrap += '<div class="ball" style="top:' + top + ';left:' + left + ';background:blue"></div>';
			}
			wrap += '<div class="ball" style="top:' + top + ';left:' + left + '"></div>';
		}
	}
	var html = "<div style='width:280px;height:40px; cursor: pointer;'><p style='font-size:14px;'>名称：" + json.title + "</br>地址：" + json.address + "</br></p></div><div class=\"wrap\">" + wrap + " <img id=\"pmt1\" src=\"" + arry[0] + "\" style=\"width:280px; height: 280px\"></div>";
	var iw = new BMap.InfoWindow(html);
	return iw;
}

// 创建一个Icon
function createIcon(json) {
	var icon = new BMap.Icon(ctx + json, new BMap.Size(28, 47));
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
// 回车键事件
document.onkeydown = function(event) {
	var e = event || window.event || arguments.callee.caller.arguments[0];
	// if(e && e.keyCode==27){ // 按 Esc
	// //要做的事情
	// }
	// if(e && e.keyCode==113){ // 按 F2
	// //要做的事情
	// }
	if (e && e.keyCode == 13) { // enter 键
		// 要做的事情
		search('keyword');
	}
};
// ///
// 搜索方法 param{searchTypeRadio_name：搜索radio的名字,keyword_name:搜索文本框的id}
window.search = function(keyword_name) {
	// 获取页面dom
	var keyword = document.getElementById(keyword_name).value;
	if (keyword && keyword != null) {
		// 获取dom的值
		var dd = searchClass.search({
			k : "title",
			d : keyword,
			s : ""
		});
		addMarker(dd);// 向地图中添加marker
	} else {
		reset();
	}
}
// 重置返回所有结果
function reset() {
	// s:{''只返回找到的结果|all返回所有的}
	$("#keyword").textbox('setValue', '');
	var dd = searchClass.search({
		k : "title",
		d : "显示全部",
		s : "all"
	});
	addMarker(dd);// 向地图中添加marker
}
// 创建marker
window.addMarker = function(data) {
	for (var i = 0; i < data.length; i++) {
		var json = data[i];// 整数据
		var p0 = json.m16;// 经度
		var p1 = json.m17;// 纬度
		var point = new BMap.Point(p0, p1);
		var iconImg = createIcon(json.icon);
		var marker = new BMap.Marker(point, {
			icon : iconImg
		});
		map.addOverlay(marker);
		(function() {
			var _json = json;
			var _iw = createInfoWindow1(_json);
			var _marker = marker;
			_marker.addEventListener("click", function() {
				this.openInfoWindow(_iw);
			});
		})()
	}
}
