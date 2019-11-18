<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>信标管理</title>
	<meta name="decorator" content="default"/>

	<link rel="stylesheet" href="${ctxStatic}/fengmap/css/style.css">
</head>
<body >
<!-- 工具栏 -->
<div class="easyui-layout" style="height: 100%">


<div data-options="region:'west',split:true" style="width: 30%">


	<div id="lydw_rygl_tb" style="padding:5px;height:auto;"  >
		<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
		<div class="form-group">
		<input type="text" id="lydw_xbgl_readerid" name="lydw_xbgl_readerid" class="easyui-textbox"  style="height: 30px;width: 120px;" data-options="prompt: '信标号'"/>
		<input type="text" id="lydw_xbgl_readercode" name="lydw_xbgl_readercode" class="easyui-textbox"  style="height: 30px;width: 100px;" data-options="prompt: '信标MAC地址'"/>
		<input type="text" id="lydw_xbgl_online" name="lydw_xbgl_online" class="easyui-combobox" style="height: 30px;width: 100px" data-options="prompt: '在线状态',editable:false ,panelHeight:'auto', data: [
											  {value:'0',text:'关闭'},
											  {value:'1',text:'在线'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchXb()" ><i class="fa fa-map-o"></i> 总览</span>
		</div>
		</form>
		<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">

				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
				</div>
		</div>
		</div>
	</div>
	<table id="lydw_rygl_dg"></table>
</div>

<div id="cas" data-options="region:'center',split:true" style="width: 68%">
	<div id="fengMap"></div>
		<!--操作按钮-->
	<div class="operating">
		<button class="btn btn-default" onclick="removeAllMarker()">清除标注点</button>
		<button class="btn btn-default" onclick="removeMarker()">删除标注点</button>
		<button class="btn btn-default" onclick="saveMarker()">保存信标点</button>
	</div>
</div>
</div>

<script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>

<script type="text/javascript">
//获取版本号,设置title

//定义全局map变量
var map;
var fmapID = 'zalk-dlfactory';

//是否是动态标注
var isDynamicMarker = true;
var layer0 = null;
var im = null;

//	新增的标注点coord 信息
var fmapCoord;

//	当前列的信标id
var beaconId;
//坐标id
var zbid;

//楼层控制
var groupControl;
//楼层
var floor = 0;

window.onload = function() {
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
		
		/*// 默认楼层加载完后不显示，需自定义设置要显示的楼层和聚焦层
		map.visibleGroupIDs = map.groupIDs;
		map.focusGroupID = map.groupIDs[0];*/
	});
    
	//地图点击事件
	map.on('mapClickNode', function(event) {
		if (event.nodeType == fengmap.FMNodeType.NONE) return;
		/*// 标注视野聚焦
		map.moveTo({
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
			// 选点之前重置之前的点位
			if(layer0!=null){
                removeAllMarker();
			}
			
			//获取坐标信息
			var eventInfo = event.eventInfo.coord;
			//获取焦点层
			var currentGid = map.focusGroupID;
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
	console.log(coord.x+"--"+coord.y+"--"+gid)
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
	im.setPosition(im.x,im.y,gid,60);
};

//删除点标注
function removeMarker(){
    if(fmapCoord != null && zbid == null) {
        layer0.removeAll();
        fmapCoord=null;
    }else {
        if(zbid == null || zbid == '') {
            layer.msg("未找到标注点！",{time: 1000});
            return;
        }

        layer.confirm('确认是否删除该点标注？', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type:'get',
                url:ctx+"/lydw/xbgl/deletezb/"+zbid,
                success: function(data){
                    layer0.removeMarker(im);
                    fmapCoord=null;
                    zbid=null;
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

function removeAllMarker(){
    if(layer0){
        for (var i = 0; i < 5; i++) {
            var group = map.getFMGroup(i+1);
            layer0 = group.getOrCreateLayer('imageMarker');
            layer0.removeAll();
        }
    }
    fmapCoord = null;
}

//保存信标点标注
function saveMarker(){
    console.log(fmapCoord);
    if(beaconId == null || beaconId == '') {
        layer.msg("请先选择信标点！",{time: 1000});
        return;
    }
    if(fmapCoord == null || fmapCoord == '') {
        layer.msg("请在地图上选择坐标位置！",{time: 1000});
        return;
    }
    layer.confirm('确认是否在该点添加标注？', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type:'get',
            url:ctx+"/lydw/xbgl/addzb",
            data: {"zbid":zbid,"xbid":beaconId,"X":fmapCoord.x,"Y":fmapCoord.y,"Z":fmapCoord.z},
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

/**
 * 左侧信息列表
 */
var dg;
var d;
$(function(){
    dg=$('#lydw_rygl_dg').datagrid({
        method: "post",
        url:ctx+'/lydw/xbgl/listjson',
        fit : true,
        fitColumns : true,
        border : false,
        idField : 'id',
        striped:true,
        pagination:true,
        rownumbers:true,
        nowrap:false,
        pageNumber:1,
        pageSize : 50,
        pageList : [ 50, 100, 150, 200, 250 ],
        scrollbarSize:5,
        singleSelect:true,
        striped:true,
        columns:[[
            {field:'readerid',title:'信标号',sortable:false,width:30,align:'center'},
            {field:'readercode',title:'信标MAC地址',sortable:false,width:80,align:'center'},
            //{field:'tcpip',title:'保留',sortable:false,width:40,align:'center'},
            {field:'online',title:'在线状态',sortable:false,width:50,align:'center',
	            formatter : function(value, row, index) {
                    if(value == 0) {
                        return '关闭';
                    }else {
                        return '启用';
                    }
            }},
            {field:'battery',title:'信标电量',sortable:false,width:80,align:'center',
                formatter:function (value,row) {
                    if(!value){  return '/'; }else{ return  value; }
            }},
            {field:'xbid',title:'是否绑定坐标',sortable:false,width:80,align:'center',
                formatter:function (value,row) {
                    if(!value) {
                        return '否';
                    }else {
                        return '是';
                    }
                }
            }
        ]],
        onClickRow: function() {
            var row = dg.datagrid('getSelected');
            if(layer0!=null){
                removeAllMarker();
                fmapCoord=null;
            }
            beaconId = row.readerid;
            zbid="";
            if(row.id != null && row.id != '') {
                zbid = row.id;
                floor = row.z;
                groupControl.changeFocusGroup(floor);
            }
            if(row.xbid != null && row.xbid != ''){
                // 点击相应列，若有点标注则携带过来 显示在地图上
                coord = {x:row.x,y:row.y}
                map.moveTo({
                    x: coord.x,
                    y: coord.y,
                    time: 1,
                    callback: function() {
                        // console.log('moveTo Complete!');
                    }
                });
                addMarker(floor,coord);
            }else {
                removeAllMarker();
                fmapCoord=null;
            }

        },
        //	双点击相应列，若有点标注则携带过来 
        onDblClickRow: function (rowdata, rowindex, rowDomElement){
        },
        checkOnSelect:false,
        selectOnCheck:false,
        toolbar:'#lydw_rygl_tb'
    });
});

//创建查询对象并查询
function search(){
    var obj=$("#searchFrom").serializeObject();
    dg.datagrid('load',obj);
}

function reset(){
    $("#searchFrom").form("clear");
    var obj=$("#searchFrom").serializeObject();
    dg.datagrid('load',obj);
}

//信标总览
function searchXb() {
    $.ajax({
        type:'post',
        url:ctx+"/lydw/xbgl/xblist",
        success: function(data){
            removeAllMarker();
            var data = $.parseJSON(data);
            for (var i = 0; i < data.length; i++) {
                var gid = data[i].z;
                coord = {x:data[i].x,y:data[i].y};
                addMarker(gid,coord);
            }
            fmapCoord=null;
        }
    });
}
</script>
</body>
</html>