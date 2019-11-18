<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>轨迹查询</title>
	<meta name="decorator" content="default"/>
      <script type="text/javascript" src="${ctx}/static/model/js/lydw/gjcx/qy_index.js?v=1.1"></script>
    <link rel="stylesheet" href="${ctxStatic}/fengmap/css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/model/css/rydw/style.css">
    <!-- 引入自定义 css js -->
    <link rel="stylesheet" href="${ctxStatic}/fengmap/css/team36.css">
    <script type="text/javascript" src="${ctxStatic}/fengmap/js/team36.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/lydw/rydw/index.js"></script>
    <script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
    <script src="${ctxStatic}/fengmap/js/layerGroup.js"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="target_mbzp_tb" style="padding:5px;height:30px">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">
        <input name="ygid" style="width: 200px;height: 30px;" class="easyui-combogrid" data-options="prompt: '员工姓名',panelWidth:350,fitColumns : true,editable:true ,idField: 'id',textField: 'text',url:'${ctx}/lydw/rydw/yglist',
								columns:[[
										   {field:'text',title:'姓名',width:100},
										   {field:'sex',title:'性别',width:60},
										   {field:'dep',title:'部门',width:200} ]]" />
    </div>
	</form>
   <div class="pull-left" style="margin-left:10px">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm aa" onclick="searchShow()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="stopTrack()" ><i class="fa fa-stop"></i> 结束追踪</button>
   </div>
	
</div>



<div id="fengMap"></div>


<script type="text/javascript">

function reset(){
	$("#searchFrom").form("clear");
}

//获取版本号,设置title

//定义全局map变量
var map;
var fmapID = 'zalk-dlfactory';

// 人员实时追踪
var count = 0;
var trackTimer;

// 标注
var layer0 = null;
var im = null;
var markerImg = "";
var ygid;
var file;//设备id

var flag=true;

//当前的路线
var naviLines = [];

//添加导航线线坐标点
var naviResults = [{
	groupId: 1,
	points: []
}];

//配置线型、线宽、透明度等
var lineStyle = {
	//color: '#FF0000',	// lineType: fengmap.FMLineType.FMARROW 时不生效
	//设置线的宽度
	lineWidth: 6,
	//设置线的透明度
	alpha: 0.8,
	//设置线的类型为导航线
	lineType: fengmap.FMLineType.FMARROW,
	//设置线动画,false为动画
	noAnimate: true,
};

//创建转换器
var trasformer = new CoordTransformer();


//转换器初始化
trasformer.init(locOrigion,locRange,mapParas);

/**
 * WebSocket
 * */
var websocket = null;


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
$(window).unload( function() {
    closeWebSocket();
});
//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    if (innerHTML == 'WebSocket连接成功' || innerHTML == 'WebSocket连接发生错误' || innerHTML == 'WebSocket连接关闭'  ) {
        //alert(innerHTML);
    }
    else
        dealData(innerHTML);
}

//关闭WebSocket连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send() {
    var message = document.getElementById('text').value;
    websocket.send(message);
}

/**
 * websocket 请求数据 人员ImageMarker 请求数据 实时定位更新
 */
function dealData(data){
    if (data.indexOf("UD") == 0) {
        data = data.substring(3, data.length);//更新位置信息
        updatePersonMarkerInfo(data);
    }
}


window.onload = function() {
	//楼层控制控件配置参数
	var ctlOpt = new fengmap.controlOptions({
		//默认在右上角
		position: fengmap.controlPositon.RIGHT_TOP,
		//默认显示楼层的个数
		showBtnCount: 7,
		//初始是否是多层显示，默认单层显示
		allLayer: true,
		//位置x,y的偏移量
		offset: {
			x: 20,
			y: 10
		},
		imgURL: '${ctxStatic}/fengmap/image/',
	});
	
    map = new fengmap.FMMap({
        //渲染dom
        container: document.getElementById('fengMap'),
        //地图数据位置
        mapServerURL: '${ctxStatic}/fengmap/data/' + fmapID,

        //主题数据位置
        mapThemeURL: '${ctxStatic}/fengmap/data/theme',
        //设置主题
    	defaultThemeName: 'zalk-dlfactory',
        // 默认比例尺级别设置为20级
        defaultMapScaleLevel: 20,
        //不支持单击模型高亮，true为单击时模型高亮
        modelSelectedEffect: false,
		//是否对不可见图层启用透明设置 默认为true
		focusAlphaMode: false,
		//对不聚焦图层启用透明设置，当focusAlphaMode = true时有效
		focusAlpha: 0.1,
		
        //开发者申请应用下web服务的key
        key: '0c5907c2c43a800d7b007941d72126b5',
        //开发者申请应用名称
        appName: 'bh_dlkj',
    });

    //打开Fengmap服务器的地图数据和主题
    map.openMapById(fmapID);
    
   	/*
   	 *	地图加载完成回调	===	提示搜索方法区
   	 */
	map.on('loadComplete', function() {
        //判断当前浏览器是否支持WebSocket，并建立连接
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://"+document.location.host+"/${ctx}/lydwWebsocket");
        }
        else {
            alert('当前浏览器 Not support websocket')
        }
        websocket.onerror = function () {
            setMessageInnerHTML("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        websocket.onopen = function () {
            setMessageInnerHTML("WebSocket连接成功");
        }
        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            setMessageInnerHTML(event.data);
        }


        //连接关闭的回调方法
        websocket.onclose = function () {
            setMessageInnerHTML("WebSocket连接关闭");
        }
		//创建楼层(按钮型)，创建时请在地图加载后(loadComplete回调)创建。
		groupControl = new fengmap.scrollGroupsControl(map, ctlOpt);
		//通过楼层切换控件切换聚焦楼层时的回调函数
		//groupContro 即为楼层控件对象
		groupControl.onChange(function(groups, allLayer) {
			//groups 表示当前要切换的楼层ID数组,
			//allLayer表示当前楼层是单层状态还是多层状态。
		});
		
		// 默认楼层加载完后不显示，需自定义设置要显示的楼层和聚焦层
		map.visibleGroupIDs = map.groupIDs;
		map.focusGroupID = map.groupIDs[0];
	});
}   


//定位点视野跟随
function searchShow(){

    var obj=$("#searchFrom").serializeObject();
    if(obj.ygid==""){
        layer.msg("请输入查询条件！",{time: 2000});
        return;
    }

    ygid = obj.ygid;

    if(flag){
        $.ajax({
            type : 'post',
            url : "${ctx}/lydw/rydw/realtimezb",
            data:{"ygid": ygid},
            success : function(data) {
                if (data != null) {
                    var nextCoords = jQuery.parseJSON(data);
                    if (nextCoords.length > 0) {
                        var loc = {'x': nextCoords[0].x,'y': nextCoords[0].y};
                        var mapCoord = trasformer.transform(loc);
                        nextCoords[0].x = mapCoord.x;
                        nextCoords[0].y = mapCoord.y;
                        flag=false;
                        file = nextCoords[0].file;
                        var nextCoord = {
                            x : nextCoords[0].x,
                            y : nextCoords[0].y,
                            z : nextCoords[0].z,
                        }
                        addMarkers(nextCoord, 0);
                    }
                }
            }
        });


	   	// 删除之前线标注
	     clearNaviLines();
	   	// 清空线路点位数组
	     naviResults[0].points = [];
	}else{
		layer.msg("请不要频繁查询，请稍等！",{time: 2000});
	}
}

function updatePersonMarkerInfo(data){
    if (data != null) {
        // 定时请求得到的新人员点位数组
        var nextCoord = jQuery.parseJSON(data);
        var newfile = nextCoord.file;//设备id
        if (file == newfile ) {
            var loc = {'x': nextCoord.x,'y': nextCoord.y};
            var mapCoord = trasformer.transform(loc);
            nextCoord.x = mapCoord.x;
            nextCoord.y = mapCoord.y;
            trackTo(nextCoord);
        }
    }
};

/**
 * 获取该点位coord
 */
function getNextCoord( ) {
    $.ajax({
        type : 'post',
        url : "${ctx}/lydw/rydw/realtimezb",
        data:{"ygid": ygid},
        success : function(data) {
            if (data != null) {
                var nextCoords = jQuery.parseJSON(data);
                if (nextCoords.length > 0) {
                    var loc = {'x': nextCoords[0].x,'y': nextCoords[0].y};
                    var mapCoord = trasformer.transform(loc);
                    nextCoords[0].x = mapCoord.x;
                    nextCoords[0].y = mapCoord.y;
                    var nextCoord = {
                        x : nextCoords[0].x,
                        y : nextCoords[0].y,
                        z : nextCoords[0].z ,
                    };
                    trackTo(nextCoord);
                }
            }
        },
        error :function(){

        }
    });
}


// 添加Markers
function addMarkers(personCoord, markerImg) {
	removeMarkers();
	var group = map.getFMGroup(map.groupIDs[personCoord.z]);

	//返回当前层中第一个imageMarkerLayer,如果没有，则自动创建						$(".aa").css('pointer-events','unset');
	layer0 = group.getOrCreateLayer('imageMarker');
	
	if(markerImg==0){
		markerImg = "bluePersonMarker";
	}
	else if(markerImg==1){
		markerImg = "redPersonMarker";
	}
	else{
		markerImg = "yellowPersonMarker";
	}
	
	im = new fengmap.FMImageMarker({
		x: personCoord.x,
		y: personCoord.y,
		height: 2,
		url: '${ctxStatic}/fengmap/image/'+markerImg+'.png',
		size: 24,
		callback: function() {
			im.alwaysShow();
		}
	});
	layer0.addMarker(im);
};

   
//移动的方法
function trackTo(nextCoord) {
	im.moveTo({
		//改变中心点x的位置
		x: nextCoord.x,
		//改变中心点y的位置
		y: nextCoord.y,
		//默认时间是0.3秒
		time: 1,
		callback: function() {
			// console.log('moveTo Complete!');
		},
		update:function(thisMarker) {
			var thisCoord = {
					x: thisMarker.x,
					y: thisMarker.y,
					z: nextCoord.z
			}
			// 向线路坐标数组中追加下一个点位
			naviResults[0].points.push(thisCoord);
			// 删除之前一个旧点位
			naviResults[0].points.shift();
			
			//绘制线路
        	drawLines(naviResults, lineStyle);
		}
	});
	
	// 标注视野聚焦
	map.moveTo({
		//改变中心点x的位置
		x : nextCoord.x,
		//改变中心点y的位置
		y : nextCoord.y,
		groupID: nextCoord.z,
		//默认时间是0.3秒
		time : 0.3,
		callback : function() {
			//console.log('moveTo Complete!');
		}
	});
};


// 清空时事追踪定时器，"追踪"按钮开放可点击
function stopTrack(){
    flag=true;
    window.clearInterval(trackTimer);
}
  	

//删除Marker
function removeMarkers() {
	//获取多楼层Marker
	map.callAllLayersByAlias('imageMarker', function(layer0) {
		layer0.removeAll();
	});
};


//绘制线图层
function drawLines(results, lineStyle) {
	//绘制部分
	var line = new fengmap.FMLineMarker();
	for (var i = 0; i < results.length; i++) {
	  var result = results[i];
	  var gid = result.groupId;
	  var points = result.points;
	  var seg = new fengmap.FMSegment();
	  seg.groupId = gid;
	  seg.points = points;
	  line.addSegment(seg);
	  var lineObject = map.drawLineMark(line, lineStyle);
	  naviLines.push(lineObject);
	}
};

//清除路线
var clearNaviLines = function() {
	//方法一：清除所有路径线
	map.clearLineMark();
};

</script>
</body>
</html>
