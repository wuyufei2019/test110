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
</head>
<body >
<!-- 工具栏 -->
<div id="target_mbzp_tb" style="padding:5px;height:30px">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">	
	<input id="starttime" class="easyui-datetimebox"  style="height: 30px;width:120px;" data-options="editable:false,prompt: '开始时间'" />&nbsp;～&nbsp;
	<input id="finishtime" class="easyui-datetimebox"  style="height: 30px;width:120px;" data-options="editable:false,prompt: '结束时间'" />
	<input type="text"  name="target_mbzp_tname" style="height:30px;width:100px;" class="easyui-textbox" data-options="prompt: '员工姓名'"/>
     <!--<input name="target_mbzp_quarter" type="text" class="easyui-combobox" style="height: 30px;width:100px;display:none"
           data-options="prompt:'部门',panelHeight:'auto',data:[{value:'1',text:'第1部门'},{value:'2',text:'第2部门'},{value:'3',text:'第3部门'},{value:'4',text:'第4部门'}] ">
    <input name="target_mbzp_db" type="text" class="easyui-combobox" style="height: 30px;width:100px;"
           data-options="prompt:'轨迹状态',panelHeight:'auto',data:[{value:'1',text:'正常'},{value:'0',text:'异常'}] ">-->
    </div>
	</form>
   <div class="pull-left" style="margin-left:10px">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchShow()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
   </div>

</div>



<div id="fengMap"></div>


<script type="text/javascript">



function reset(){
	$("#searchFrom").form("clear");
}
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
var markerHeight = 2;
var markerAnimation = true;

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
        //创建楼层(按钮型)，创建时请在地图加载后(loadComplete回调)创建。
        groupControl = new fengmap.scrollGroupsControl(map, ctlOpt);
        //通过楼层切换控件切换聚焦楼层时的回调函数
        //groupContro 即为楼层控件对象
        groupControl.onChange(function(groups, allLayer) {
            //groups 表示当前要切换的楼层ID数组,
            //allLayer表示当前楼层是单层状态还是多层状态。
        });
    });

}

// 添加Markers
function addMarkers(gid, X, Y, markerImg) {
    removeMarkers();
    var group = map.getFMGroup(map.groupIDs[gid]);

    //返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
    layer0 = group.getOrCreateLayer('imageMarker');

    im = new fengmap.FMImageMarker({
        x: X,
        y: Y,
        height: markerHeight,
        url: '${ctxStatic}/fengmap/image/'+markerImg+'.png',
        size: 32,
        callback: function() {
            im.alwaysShow();
        }
    });
    layer0.addMarker(im);
    console.log(X)
};


//定位坐标点
var showdata = [{
    x: 13362797.713975295,
    y: 4068952.9662368693,
    z: 0
},
    {
        x: 13362785.276655871,
        y: 4068894.7570362356,
        z: 0
    },
    {
        x: 13362786.450112814,
        y: 4068891.52772768,
        z: 0
    },
    {
        x: 13362787.580105608,
        y: 4068889.2475028164,
        z: 0
    },
    {
        x: 13362788.908409651,
        y: 4068888.4753114004,
        z: 0
    }
    ,
    {
        x: 13362790.036208961,
        y: 4068886.55699391,
        z: 0
    }
];

//定位点视野跟随
function searchShow() {
    var gid = 0, X = 13362797.713975295, Y = 4068952.9662368693, markerImg = 0;
    addMarkers(gid, X, Y, markerImg);
    //	设置定时器 循环执行，每隔0.3秒钟执行一次 300
    trackTimer = window.setInterval(toTrack(), 1000);
}

function toTrack() {
    count++;
    if (count > 6) {
        stopTrack();
        count = 0;
    }else{
        var tmp = showdata[count - 1];
        trackTo(tmp);
    }
}

//移动的方法
function trackTo(coord) {
    im.moveTo({
        //改变中心点x的位置
        x: coord.x,
        //改变中心点y的位置
        y: coord.y,
        //默认时间是0.3秒
        time: 2,
        callback: function() {
            // console.log('moveTo Complete!');
        },
        update:function(currentXY) {
            console.log("实时坐标："+currentXY.x+","+currentXY.y);
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


</script>

</body>
</html>
