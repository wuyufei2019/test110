<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>区域权限管理</title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/lydw/qyqx/index.js?v=1"></script>
    <script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/fengmap/css/style.css">
</head>
<body >
<!-- 工具栏 -->
<div class="easyui-layout" style="height: 100%">

    <div data-options="region:'west',split:true" style="width: 32%">
            <div id="lydw_qyqx_tb" style="padding:5px;height:auto;"  >
                <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
                <div class="form-group">
                <input type="text" name="lydw_qyqx_cx_name" class="easyui-textbox"  style="height: 30px;width: 120px;" data-options="prompt: '区域名称'"/>
                <input type="text" name="lydw_qyqx_cx_floor" class="easyui-combobox" style="height: 30px;width: 100px" data-options="prompt: '楼层',editable:false ,panelHeight:'auto', data: [
                                                      {value:'1',text:'1层'},
                                                      {value:'2',text:'2层'},
                                                      {value:'3',text:'3层'},
                                                      {value:'4',text:'4层'},
                                                      {value:'5',text:'5层'}]" />
                <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
                <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
                <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchDzwl()" ><i class="fa fa-map-o"></i> 总览</span>
                </div>
                </form>
                <div class="row">
                <div class="col-sm-12">
                    <div class="pull-left">

                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>

                        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
                        </div>
                </div>
                </div>
            </div>
            <table id="lydw_qyqx_dg"></table>
    </div>

    <div id="cas" data-options="region:'center',split:true" style="width: 68%">
        <div id="fengMap"></div>
            <!--操作按钮-->
        <div class="operating">
            <button class="btn btn-default" onclick="removeMarker()">清除标注点</button>
            <button class="btn btn-default" onclick="cleanFence()" title="非立即删除区域信息，只是在目前操作中清除">清除区域</button>
            <button class="btn btn-default" onclick="createFence()">生成区域</button>
            <button class="btn btn-default" onclick="saveMarker()" title="保存后将重置区域信息至最新状态">保存区域状态</button>
        </div>
    </div>
</div>


<script type="text/javascript">
//获取版本号,设置title

//定义全局map变量
var map;
var fmapID = 'zalk-dlfactory';

//是否是动态标注
var isDynamicMarker = true;
var layer0 = null;
var fenceLayer = null;
var im = null;

//	新增的标注点coord 信息
var fmapCoord;

//	当前列的区域id
var wlId;

//楼层控制
var groupControl;
//当前楼层
var floor = 0;

//电子区域
var groupLayer;

// 电子区域参数
var fenceData;

// 信标集合
var readerlist = [];

// 新点位电子区域数组
var newFenceData = [];

// 判断是否已经生成了区域
var fenceState = 0;


$(function(){
    //获取信标集合
    getXBList();

	//楼层控制控件配置参数
	var ctlOpt = new fengmap.controlOptions({
		//默认在右上角
		position: fengmap.controlPositon.RIGHT_TOP,
		//默认显示楼层的个数
		showBtnCount: 7,
		//初始是否是多层显示，默认单层显示
		allLayer: false,
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

	// 多类型电子区域
	var rectangleMarker, circleMaker, polygonMarker;

	//地图加载完成回掉方法
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
/*		map.visibleGroupIDs = map.groupIDs;
		map.focusGroupID = map.groupIDs[0];*/
	});

	//地图点击事件
	map.on('mapClickNode', function(event) {
		if (event.nodeType == fengmap.FMNodeType.NONE) return;
		// 标注视野聚焦
/*		map.moveTo({
			//改变中心点x的位置
			x : event.target.x,
			//改变中心点y的位置
			y : event.target.y,
			groupID: event.target.groupID,
			//默认时间是0.3秒
			time : 0.5,
			callback : function() {
			}
		});*/
		
		// 添加标注点
		if (isDynamicMarker==true) {
			/* 选点之前重置之前的点位
			if(layer!=null){
				layer.removeMarker(im);
			}*/

			if(fenceState == 1){
				cleanFence();
				fenceState = 0;
			}

            //获取焦点层
            var currentGid = map.focusGroupID;
            if(floor != currentGid) {
                cleanFence();
            }
            floor = currentGid;

            //获取坐标信息
            var eventInfo = event.eventInfo.coord;
			if (eventInfo) { //pc端
				var coord = {
					x: event.eventInfo.coord.x,
					y: event.eventInfo.coord.y,
					z: map.getFMGroup(currentGid).groupHeight + map.layerLocalHeight
				}
			} else { //移动端
				var coord = {
					x: event.mapCoord.x,
					y: event.mapCoord.y,
					z: map.getFMGroup(currentGid).groupHeight + map.layerLocalHeight
				}
			}

			//添加Marker
			addMarker(currentGid, coord);

			// 将点位加入到 fenceData数组中
			newFenceData.push(coord);
		}
	});
});

//获取信标集合
function getXBList(){
    $.ajax({
        type:'get',
        url:ctx+"/lydw/xbgl/xblist",
        success: function(data){
            readerlist=jQuery.parseJSON(data);
        }
    });
}



//在点击的位置添加图片标注
function addMarker(gid, coord) {
	fmapCoord = {
			x: coord.x,
			y: coord.y,
			z: gid
		}

	var group = map.getFMGroup(gid);

	//返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
	layer0 = group.getOrCreateLayer('imageMarker');

	im = new fengmap.FMImageMarker({
		x: coord.x,
		y: coord.y,
		height: 2,
		url: '${ctxStatic}/fengmap/image/blueImageMarker.png',
		size: 24,
		callback: function() {
			im.alwaysShow();
		}
	});
	layer0.addMarker(im);
};

//删除点标注
function removeMarker(){
	if(layer0){
		layer0.removeAll();
	}
	newFenceData = [];
}

//判断点是否在多边形里,参数:（点,多边形）
function PointInPoly(pt, poly) {
    for (var c = false, i = -1, l = poly.length, j = l - 1; ++i < l; j = i)
        ((poly[i].x <= pt.x && pt.x < poly[j].x) || (poly[j].x <= pt.x && pt.x < poly[i].x)) && (pt.y < (poly[j].y - poly[i].y) * (pt.x - poly[i].x) / (poly[j].x - poly[i].x) + poly[i].y) && (c = !c);
    return c;
}
//保存电子区域标注
function saveMarker(){
    if(fenceState == 1) {
        var mappoint;
        for (var i = 0; i < newFenceData.length; i++) {
            var point = newFenceData[i].x + ',' + newFenceData[i].y + ',' + floor;
            if(i == 0) {
                mappoint = point;
            }else {
                mappoint = mappoint + '||' + point;
            }
        }
        if(wlId == null || wlId == '') {
            layer.msg("请先选择电子区域！",{time: 1000});
            return;
        }

        var xbids="0";
        for(var i = 0; i < readerlist.length; i++){
            var flag=   PointInPoly(readerlist[i],newFenceData);
            //fengmap.MapUtil.ptInPolygon2d(newFenceData, readerlist[i], newFenceData.length-1);
            if(flag)
                xbids+=","+readerlist[i].id;
        }

        layer.confirm('确认保存该电子区域？', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type:'get',
                url:ctx+"/lydw/qyqx/addqyqx",
                data: {"wlid":wlId,"mappoint":mappoint,"floor":floor,"xbids":xbids},
                success: function(data){
                    layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
                    layer.close(index);
                    dg.datagrid('reload');
                    dg.datagrid('clearChecked');
                    dg.datagrid('clearSelections');
                }
            });
        });
    }
}

/**
 * 多类型电子区域
 */
//创建矩形标注
function createRectangleMaker(fenceData) {
	rectangleMarker = new fengmap.FMPolygonMarker({
		//设置颜色
		color: '#9F35FF',
		//设置透明度
		alpha: .3,
		//设置边框线的宽度
		lineWidth: 1,
		//设置高度
		height: 6,
		points: {
			//设置为矩形
			type: 'rectangle',
			//设置此形状的中心坐标
			center: {
				x: 1.2961583E7,
				y: 4861865.0
			},
			//设置矩形的宽度
			width: 30,
			//设置矩形的高度
			height: 30
		}
	});
};

//创建圆形标注
function createCircleMaker(fenceData) {
	circleMaker = new fengmap.FMPolygonMarker({
		//设置颜色
		color: '#3CF9DF',
		//设置透明度
		alpha: .3,
		//设置边框线的宽度
		lineWidth: 3,
		//设置高度
		height: 6,
		points: {
			//设置为圆形
			type: 'circle',
			//设置此形状的中心坐标
			center: {
				x: 1.2961644E7,
				y: 4861874.0
			},
			//设置半径
			radius: 30,
			//设置段数，默认为40段
			segments: 40,
		}
	});
};

//创建自定义形状标注
function createPolygonMaker(fenceData) {
	polygonMarker = new fengmap.FMPolygonMarker({
		//设置透明度
		alpha: .5,
		//设置边框线的宽度
		lineWidth: 1,
		//设置高度
		height: 2,
		//设置多边形坐标点
		points: fenceData
	});
}

// 真正想地图中添加电子区域的方法
function addPmMarker(fenceData,floor) {
	var group = map.getFMGroup(map.groupIDs[floor-1]);

	//返回当前层中第一个polygonMarker,如果没有，则自动创建
	fenceLayer = group.getOrCreateLayer('polygonMarker');

	createPolygonMaker(fenceData);
	fenceLayer.addMarker(polygonMarker);

	fenceState = 1;
}

// 非在数据库删掉之前区域信息，保存时才重置数据库
function cleanFence(){
	if(fenceLayer){
        for (var i = 0; i < 5; i++) {
            var group = map.getFMGroup(i+1);
            fenceLayer = group.getOrCreateLayer('polygonMarker');
            fenceLayer.removeAll();
        }
	}
	// 重置新区域点位数组
	removeMarker();
}

//	生成区域样式
function createFence(){
	if(newFenceData.length>2){
		addPmMarker(newFenceData,floor);
	}else{
		alert("请在同一楼层设置且选择至少三个点位！")
	}
}

//非在数据库删掉之前区域信息，保存时才重置数据库
function saveFence(){

}



</script>
</body>
</html>