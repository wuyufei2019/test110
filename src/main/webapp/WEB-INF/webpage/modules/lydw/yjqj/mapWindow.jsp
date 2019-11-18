<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工卡管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div id="fengMap" style="height:100%"></div>
<script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
<script src="${ctxStatic}/fengmap/js/createBubble.js"></script>
<script src="${ctxStatic}/fengmap/js/layerGroup.js"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/lydw/rydw/index.js"></script>
<script type="text/javascript">
var map;
var fmapID = 'zalk-dlfactory';
var im;
var jump;
var flag = true;
var warningMarkerInfo;
var layer0;
// 警报点楼层
var gid;

var groupId = 1;

//创建转换器
var trasformer = new CoordTransformer();


//转换器初始化
trasformer.init(locOrigion,locRange,mapParas);
window.onload = function() {

	
	//楼层控制控件配置参数
	var ctlOpt = new fengmap.controlOptions({
		//默认在右上角
		position : fengmap.controlPositon.RIGHT_BOTTOM,
		//默认显示楼层的个数
		showBtnCount : 7,
		//初始是否是多层显示，默认单层显示
		allLayer : false,
		//位置x,y的偏移量
		offset : {
			x : 5,
			y : 55
		},
		imgURL : '${ctxStatic}/fengmap/image/',
	});

	map = new fengmap.FMMap({
		//渲染dom
		container : document.getElementById('fengMap'),
		//地图数据位置
		mapServerURL : '${ctxStatic}/fengmap/data/' + fmapID,

		//主题数据位置
		mapThemeURL : '${ctxStatic}/fengmap/data/theme',
		//设置主题
		defaultThemeName : 'zalk-dlfactory',
		// 默认比例尺级别设置为20级
		defaultMapScaleLevel : 20,
		//不支持单击模型高亮，true为单击时模型高亮
		modelSelectedEffect : false,
		
		// 默认聚焦楼层
		defaultFocusGroup: gid,
		
		//是否对不可见图层启用透明设置 默认为true
		focusAlphaMode : false,
		//对不聚焦图层启用透明设置，当focusAlphaMode = true时有效
		focusAlpha : 0.1,

		//开发者申请应用下web服务的key
		key : '0c5907c2c43a800d7b007941d72126b5',
		//开发者申请应用名称
		appName : 'bh_dlkj',
	});

	//打开Fengmap服务器的地图数据和主题
	map.openMapById(fmapID);

	//禁用浏览器右键事件
	$('body').on("contextmenu", function(event) {
		return false;
	})

	
	/*
	 *	地图加载完成回调	
	 */
	map.on('loadComplete', function() {
		//创建楼层(按钮型)，创建时请在地图加载后(loadComplete回调)创建。
		groupControl = new fengmap.scrollGroupsControl(map, ctlOpt);
		//通过楼层切换控件切换聚焦楼层时的回调函数
		//groupContro 即为楼层控件对象
		groupControl.onChange(function(groups, allLayer) {

		});
        // 默认楼层加载完后不显示，需自定义设置要显示的楼层和聚焦层
        map.visibleGroupIDs = map.groupIDs;
        map.focusGroupID = map.groupIDs[0];
        // 获取警报点信息
        getWarningCoord(${ygid });

	});


	/**
	 *	基础点击弹出组件信息tip 方法区
	 */
	map.on('mapClickNode', function(event) {
		if (event.nodeType == fengmap.FMNodeType.IMAGE_MARKER) {
			var popmarkerInfo = event.target.name;
			var popmarkerInfoArr = popmarkerInfo.split("_");
			
			if(popMarker != null){
				popMarker.close();
			}
			
			//信息框控件大小配置
			var ctlOpt1 = new fengmap.controlOptions({
				mapCoord: {
					//设置弹框的x轴
					x: event.target.x,
					//设置弹框的y轴
					y: event.target.y,
					height: 0, //控制信息窗的高度
					//设置弹框位于的楼层
					groupID: event.target.groupID
				},
				//设置弹框的宽度
				width: 170,
				//设置弹框的高度
				height: 80,
				marginTop: 0,
				//设置弹框的内容
				content: '<div class="popMarkerDiv"><div style="height:30px;">'+popmarkerInfoArr[0]+'</div><div style="height:20px;font-size:12px">'+popmarkerInfoArr[1]+' - '+popmarkerInfoArr[2]+'</div><div style="height:20px;font-size:12px">'+popmarkerInfoArr[3]+'</div></div>',
				closeCallBack: function() {
					//信息窗点击关闭操作
					// alert('信息窗关闭了！');
				}
			});

			//添加弹框到地图上
			var popMarker = new fengmap.FMPopInfoWindow(map, ctlOpt1);
			//popMarker.close();
			$(".fm-control-popmarker .popMarkerDiv").parent("div").css('height','70px');
		}
	});
};


// 获取警报点信息
function getWarningCoord(ygid){
    $.ajax({
        type:'get',
        url:ctx+"/lydw/rydw/realtimezb",
        data:{"ygid":ygid},
        success: function(data){
            showdata=jQuery.parseJSON(data);
            if(showdata.length>0){
                var loc = {'x': showdata[0].x,'y': showdata[0].y};
                var mapCoord = trasformer.transform(loc);
            	gid = showdata[0].z;
            	var popmarkerInfo = showdata[0].name+"_"+showdata[0].bm+"_"+showdata[0].gw+"_"+showdata[0].uptime;
                warningMarkerInfo = {
                		x: mapCoord.x,
                		y: mapCoord.y,
                		z: showdata[0].z,
                		popmarkerInfo: popmarkerInfo
                }
                addMarkers(warningMarkerInfo, 1);
            }else{
                layer.msg("没有警报数据！",{time: 2000});
            }
        }
    });
}

//原生搜索 table添加Markers
function addMarkers(warningMarkerInfo, markerImg) {
	removeMarkers();
	var group = map.getFMGroup(map.groupIDs[warningMarkerInfo.z]);

	//返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
	layer0 = group.getOrCreateLayer('imageMarker');

	if (markerImg == 0) {
		markerImg = "bluePersonMarker";
	} else if (markerImg == 1) {
		markerImg = "redPersonMarker";
	} else {
		markerImg = "yellowPersonMarker";
	}

	im = new fengmap.FMImageMarker({
		x : warningMarkerInfo.x,
		y : warningMarkerInfo.y,
		name: warningMarkerInfo.popmarkerInfo,
		height : 2,
		url : '${ctxStatic}/fengmap/image/' + markerImg + '.png',
		size : 24,
		callback : function() {
			im.alwaysShow();
			jump = im.jump({
				times : 0,
				duration : 1,
				delay : 0.5,
				height : 10
			});
			
		}
	});
	layer0.addMarker(im);
};


//删除Marker
function removeMarkers() {
	//获取多楼层Marker
	map.callAllLayersByAlias('imageMarker', function(layer) {
		layer.removeAll();
	});
};

</script>
</body>
</html>