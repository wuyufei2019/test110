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
    <script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
    <script src="${ctxStatic}/fengmap/js/layerGroup.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/lydw/rydw/index.js"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="target_mbzp_tb" style="padding:5px;height:30px">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">	
	<input name="starttime" class="easyui-datetimebox"  style="height: 30px;width:120px;" data-options="editable:false,prompt: '开始时间'" />&nbsp;～&nbsp;
	<input name="endtime" class="easyui-datetimebox"  style="height: 30px;width:120px;" data-options="editable:false,prompt: '结束时间'" />
        <input name="tagid" style="width: 200px;height: 30px;" class="easyui-combogrid" data-options="prompt: '员工姓名',panelWidth:350,fitColumns : true,editable:true ,idField: 'id',textField: 'text',url:'${ctx}/lydw/rydw/yglist',
								columns:[[
										   {field:'text',title:'姓名',width:100},
										   {field:'sex',title:'性别',width:60},
										   {field:'dep',title:'部门',width:200} ]]" />
    </div>
	</form>
   <div class="pull-left" style="margin-left:10px">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm aa" onclick="searchShow()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
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
var fmapID = 'realidmap1';

// 人员实时追踪
var count = 0;
var trackTimer;

// 标注
var layer0 = null;
var im = null;
var markerImg = "";
var showdata;

var flag=true;

//当前的路线
var naviLines = [];

// 导航线数组中的groupId
var gid = 1;

//添加导航线线坐标点
var naviResults = [{
	groupId: gid,
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

//popmarker 对象
var popMarker = null;

//创建转换器
var trasformer = new CoordTransformer();

//转换器初始化
trasformer.init(locOrigion,locRange,mapParas);


window.onload = function() {
	//楼层控制控件配置参数
	var ctlOpt = new fengmap.controlOptions({
		//默认在右上角
		position: fengmap.controlPositon.RIGHT_TOP,
		//默认显示楼层的个数
		showBtnCount: 3,
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
    	defaultThemeName: 'realidmap1',
        // 默认比例尺级别设置为20级
        defaultMapScaleLevel: 19,
        //不支持单击模型高亮，true为单击时模型高亮
        modelSelectedEffect: false,
		//是否对不可见图层启用透明设置 默认为true
		focusAlphaMode: false,
		//对不聚焦图层启用透明设置，当focusAlphaMode = true时有效
		focusAlpha: 0.1,
		
        //开发者申请应用下web服务的key
        key: 'b501e571307da75ab6c89e960e5f6148',
        //开发者申请应用名称
        appName: '苏州兴业',
    });

    //打开Fengmap服务器的地图数据和主题
    map.openMapById(fmapID);
    
   	/*
   	 *	地图加载完成回调	===	提示搜索方法区
   	 */
	map.on('loadComplete', function() {
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


//查询历史轨迹
function searchShow(){
    var obj=$("#searchFrom").serializeObject();
    if(obj.starttime==""||obj.endtime==""||obj.tagid==""){
        layer.msg("请输入查询条件！",{time: 2000});
        return;
    }
    
    if(flag){
      	// 删除之前线标注
        clearNaviLines();
      	// 清空线路点位数组
        naviResults[0].points = [];
      	
        $.ajax({
            type:'get',
            url:ctx+"/lydw/rydw/hisgjlistman",
            data:obj,
            success: function(data){
                showdata=jQuery.parseJSON(data).path;

                console.info(showdata);
                for (var i = 0; i <showdata.length ; i++) {
                    var loc = {'x': showdata[i][0],'y': showdata[i][2]};
                    var mapCoord = trasformer.transform(loc);
                    showdata[i][0] = mapCoord.x;
                    showdata[i][2] = mapCoord.y;
                }
                if(showdata.length>0){
                    var initCoord = {
                    		x: showdata[0][0],
                    		y: showdata[0][2],
                    		z: showdata[0][1]
                    }
                    addMarkers(initCoord, 0);
                    
                    // 向 线路数组中事先添加站位元素
                    naviResults[0].groupId = showdata[0][2];
                    naviResults[0].points.push(initCoord);
                    naviResults[0].points.push(initCoord);
                    
                    flag=false;
                	
                    //	设置定时器 循环执行，每隔0.3秒钟执行一次 300
                    trackTimer = window.setInterval(toTrack, 1000);
                }else{
                    layer.msg("没有历史轨迹数据！",{time: 2000});
                }
            }
        });
    }else{
        layer.msg("请不要频繁查询，请稍等！",{time: 2000});
    }

}


// 添加Markers
function addMarkers(initCoord, markerImg) {
	removeMarkers();
	var group = map.getFMGroup(map.groupIDs[initCoord.z]);

	//返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
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
		x: initCoord.x,
		y: initCoord.y,
		height: 2,
		url: '${ctxStatic}/fengmap/image/'+markerImg+'.png',
		size: 24,
		callback: function() {
			im.alwaysShow();
		}
	});
	layer0.addMarker(im);
};


function toTrack() {
	count++;
	if (count > showdata.length) {
		stopTrack();
		count = 0;
        flag=true;
	}else{
		var tmp = showdata[count - 1];
		trackTo(tmp);
	}
};

   
//移动的方法
function trackTo(coord) {
	im.moveTo({
		//改变中心点x的位置
		x: coord[0],
		//改变中心点y的位置
		y: coord[2],
		//默认时间是0.3秒
		time: 1,
		callback: function() {
		},
		update:function(thisMarker) {
			var thisCoord = {
					x: thisMarker.x,
					y: thisMarker.y,
					z: coord[1]
			}
			// 向线路坐标数组中追加下一个点位
			naviResults[0].groupId = thisCoord.z;
			naviResults[0].points.push(thisCoord);
			// 删除之前一个旧点位
			naviResults[0].points.shift();
			
			// 绘制线路
        	drawLines(naviResults, lineStyle);
        	
			// 生成pop信息窗
			addPopMarker(thisCoord,coord.uptime);
		}
	});
	
	// 标注视野聚焦
	map.moveTo({
		//改变中心点x的位置
		x : coord.x,
		//改变中心点y的位置
		y : coord.y,
		groupID: coord.z,
		//默认时间是0.3秒
		time : 0.3,
		callback : function() {
			//console.log('moveTo Complete!');
		}
	});
};

// 清空时事追踪定时器，“追踪”按钮开放可点击
function stopTrack(){
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

/**
 *  生成pop信息窗
 */
function addPopMarker(thisCoord,popmarkerInfo){
	var popmarkerInfoArr = popmarkerInfo.split(" ");
	// 生成popmarker 信息框控件大小配置
	if(popMarker != null){
		popMarker.close();
	}
	var ctlOpt2 = new fengmap.controlOptions({
		mapCoord: {
			//设置弹框的x轴
			x: thisCoord.x,
			//设置弹框的y轴
			y: thisCoord.y,
			height: 6, //控制信息窗的高度
			//设置弹框位于的楼层
			groupID: thisCoord.z
		},
		//设置弹框的宽度
		width: 130,
		//设置弹框的高度
		height: 50,
		marginTop: 10,
		marginLeft:  0,
		//设置弹框的内容
		content: '<div class="popMarkerDiv"><div style="height:25px;">'+popmarkerInfoArr[1]+'</div><div style="height:15px;font-size:12px">'+popmarkerInfoArr[0]+'</div></div>',
		closeCallBack: function() {
			//信息窗点击关闭操作
			// alert('信息窗关闭了！');
		}
	});

	//添加弹框到地图上
	popMarker = new fengmap.FMPopInfoWindow(map, ctlOpt2);
	$(".fm-control-popmarker .popMarkerDiv").parent("div").css('height','55px');
}
</script>
</body>
</html>
