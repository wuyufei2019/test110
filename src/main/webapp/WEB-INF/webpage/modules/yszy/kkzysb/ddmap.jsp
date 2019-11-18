<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>轨迹回放</title>
    <meta name="decorator" content="default"/>
    <style type="text/css">
        #map_canvas label {
            max-width: none;
        }

        .anchorBL {
            display: none;
        }

        #result {
            width: 100%
        }

        .BMap_cpyCtrl {
            display: none;
        }

        .view {
            z-index: 2000;
            position: absolute;
            top: 90px;
            right: 20px;
            font-size: 13px;
            width: 58px;
            overflow-y: hidden;
            display: flex;
            flex-direction: column;
            align-items: center;
            border-radius: 7px;
            border: 1px solid rgb(142, 168, 224);
            box-shadow: 0px 0px 8px 0px rgb(173, 175, 178);
            padding: 15px 0px 0;
            background: rgb(250, 248, 245);
        }

        .truckView {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-around;
        }

        .truck {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 15px;
        }

        .truckIcon {
            width: 40px;
            height: 40px;
            border-radius: 50%;
        }

        .on {
            background: rgb(232, 232, 232);
            box-shadow: 1px 1px 5px 0 rgb(167, 167, 167);
        }

        .off {
            background: rgb(255, 255, 255);
            box-shadow: 3px 3px 4px 0 rgb(187, 187, 187);
        }
    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript"
            src="${ctx}/static/common/initmap.js?v=1.1"></script>
    <script type="text/javascript" src="${ctx}/static/common/LuShu.js"></script>
</head>

<body>
<!-- control view -->
<div class="view">
    <!-- 货车图标-->
    <div class="truckView">
        <div title="开始" class="truck">
            <img id="a1" class="truckIcon on"
                 src="${ctxStatic }/model/images/yszy/Play.png"/>
        </div>
        <div title="暂停" class="truck">
            <img id="a2" class="truckIcon off"
                 src="${ctxStatic }/model/images/yszy/Pause.png"/>
        </div>
        <div title="停止" class="truck">
            <img id="a3" class="truckIcon off"
                 src="${ctxStatic }/model/images/yszy/Stop.png"/>
        </div>
        <div title="加速" class="truck">
            <img id="a4" class="truckIcon off" onmousedown="addMouseDown()"
                 onmouseup="addMouseUp()"
                 src="${ctxStatic }/model/images/yszy/FFW.png"/>
        </div>
        <div title="减速" class="truck">
            <img id="a5" class="truckIcon off" onmousedown="reduceMouseDown()"
                 onmouseup="reduceMouseUp()"
                 src="${ctxStatic }/model/images/yszy/REW.png"/>
        </div>
    </div>
</div>

<div style="width: 500px; float: left">
    <input name="starttime" id="starttime" class="easyui-datetimebox" style="width:200px;height:
    30px;" value="${starttime}"
           data-options="prompt: '请选择开始时间',required:true, validType:['FUN[ValidateStart,\'日期不能大于结束时间\']']"/>至
    <input name="endtime" id="endtime" class="easyui-datetimebox" style="width:200px;height: 30px;"
           value="${endtime}"
           data-options="prompt: '请选择结束时间',required:true, validType:['FUN[ValidateEnd,\'日期不能小于开始时间\']']"/>
    <a id="serach" onclick="submitact()" class=" btn btn-primary">搜索</a>

</div>
<div style="height:100%;width:100%;border:#ccc solid 1px;"
     id="map_canvas"></div>
<script type="text/javascript">
    var plateNum = "${plateNum}";

    function submitact() {
        var starttime = $("#starttime").datetimebox("getValue");
        var endtime = $("#endtime").datetimebox("getValue");
        var sd = new Date(starttime);
        var ed = new Date(endtime);
        var sf = $("#starttime").datetimebox("isValid");
        var ef = $("#endtime").datetimebox("isValid");
        if ((ed - sd) / (1000 * 60 * 60) > 24) {
            layer.msg("间隔时间超过24小时，请重新输入日期");
            return false;
        }
        if (sf && ef) {
            window.location.href = ctx + "/yszy/kkzysb/historytrack/"
                + plateNum + "?starttime=" + starttime + "&endtime=" + endtime;

        }
    }

    $(function () {

        var list = JSON.parse('${gpsList}');//历史坐标点

        if (list.length > 0) {
            initMap("map_canvas", list[0].lon, list[0].lat, 16, "滨海", "1");
        } else {
            initMap("map_canvas", "", "", 16, "滨海", "1");
        }
        var point1;
        var point2;
        var index = 0;
        var lushu;
        var allArrPois = [];
        var arrPois = [];
        var botten;
        if (list.length >= 2) {
            point1 = new BMap.Point(list[index].lon, list[index].lat);
            point2 = new BMap.Point(list[index + 1].lon, list[index + 1].lat);
        } else {
            layer.msg("坐标点过少！");
        }


        // 实例化一个驾车导航用来生成路线
        var drv = new BMap.DrivingRoute(
            map, {
                onSearchComplete: function (res) {
                    if (drv.getStatus() == BMAP_STATUS_SUCCESS) {
                        index++;
                        var plan = res.getPlan(0);
                        arrPois = [];
                        for (var j = 0; j < plan.getNumRoutes(); j++) {
                            var route = plan.getRoute(j);
                            arrPois = arrPois.concat(route.getPath());
                        }
                        map.addOverlay(new BMap.Polyline(arrPois, {
                            strokeColor: '#ff0000'
                        }));
                        if (index == 1) {
                            lushu = new BMapLib.LuShu(map, arrPois,
                                {
                                    defaultContent:
                                        "<div>" + plateNum + "</div><div>" + new Date(list[0].time).format("yy-MM-dd hh:mm:ss") + "</div>",
                                    autoView: true,
                                    icon: new BMap.Icon(
                                        '${ctx}/static/model/images/car_orange.png',
                                        new BMap.Size(52, 26), {
                                            anchor: new BMap.Size(
                                                27, 13)
                                        }),
                                    speed: 500,
                                    enableRotation: true,//是否设置marker随着道路的走向进行旋转
                                    landmarkPois: []
                                });
                            lushu.start();
                        }
                        if (index == list.length) {
                            index++;
                            lushu = new BMapLib.LuShu(map,
                                allArrPois,
                                {
                                    defaultContent: '${plateNum}',//"从天安门到百度大厦"
                                    autoView: true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                                    icon: new BMap.Icon(
                                        '${ctx}/static/model/images/car_orange.png',
                                        new BMap.Size(52, 26), {
                                            anchor: new BMap.Size(
                                                27, 13)
                                        }),
                                    speed: 500,
                                    enableRotation: true,//是否设置marker随着道路的走向进行旋转
                                    landmarkPois: []
                                });
                        }
                    }
                }
            });
        drv.search(point1, point2);

        var interval = setInterval(function () {
            lushu._setOptions({
                defaultContent: "<div>" + plateNum
                    + "</div><div>" + new Date(list[index - 1].time).format("yy-MM-dd hh:mm:ss") + "</div>"
            });
            if (list.length - 2 >= index) {
                if (lushu && (lushu.getMoving() == false)) {
                    point1 = new BMap.Point(list[index].lon, list[index].lat);
                    point2 = new BMap.Point(list[index + 1].lon,
                        list[index + 1].lat);
                    if (map.getDistance(point1, point2) > 5) {
                        drv.search(point1, point2);
                        if (index != 1) {
                            lushu.goPath(arrPois);
                            //marker点转向
                            var angle = lushu.getAngle(arrPois);

                            //var angle = calcAngle(arrPois[0].lng, arrPois[0].lat,arrPois[1].lng, arrPois[1].lat);
                            var myIcon = new BMap.Icon("${ctx}/static/model/images/angle.png", new BMap.Size(25, 25));
                            var marker = new BMap.Marker(new BMap.Point(arrPois[0].lng, arrPois[0].lat), {
                                icon: myIcon,
                                rotation: angle
                            });
                            map.addOverlay(marker);
                        }
                    } else {
                        index++;
                    }
                }
            } else if (list.length - 1 == index) {
                if (lushu && (lushu.getMoving() == false)) {
                    lushu.goPath(arrPois);
                    index++;
                }
            }
            if (list.length == index) {
                if (lushu && (lushu.getMoving() == false)) {
                    botten = 1;
                    var img1 = document.getElementById('a3');
                    img1.setAttribute("class", "truckIcon on");
                    var img2 = document.getElementById('a2');
                    img2.setAttribute("class", "truckIcon off");
                    var img3 = document.getElementById('a1');
                    img3.setAttribute("class", "truckIcon off");
                    clearInterval(interval);
                }
                //lushu.goPath(allArrPois);
                return;
            }
        }, 400);
        $("a1").onclick = function () {
            var img1 = document.getElementById('a1');
            img1.setAttribute("class", "truckIcon on");
            var img2 = document.getElementById('a2');
            img2.setAttribute("class", "truckIcon off");
            var img3 = document.getElementById('a3');
            img3.setAttribute("class", "truckIcon off");
            if (botten == 1) {
                lushu.stop();
                lushu.goPath(allArrPois);
                botten = 2;
            }
            if (botten = 2) {
                lushu.start(function () {
                    var img1 = document.getElementById('a3');
                    img1.setAttribute("class", "truckIcon on");
                    var img2 = document.getElementById('a2');
                    img2.setAttribute("class", "truckIcon off");
                    var img3 = document.getElementById('a1');
                    img3.setAttribute("class", "truckIcon off");
                });
            } else {
                lushu.start();//开始/继续
            }

        }
        $("a2").onclick = function () {
            var img1 = document.getElementById('a2');
            img1.setAttribute("class", "truckIcon on");
            var img2 = document.getElementById('a1');
            img2.setAttribute("class", "truckIcon off");
            var img3 = document.getElementById('a3');
            img3.setAttribute("class", "truckIcon off");
            //暂停
            lushu.pause();
        }
        $("a3").onclick = function () {
            var img1 = document.getElementById('a3');
            img1.setAttribute("class", "truckIcon on");
            var img2 = document.getElementById('a2');
            img2.setAttribute("class", "truckIcon off");
            var img3 = document.getElementById('a1');
            img3.setAttribute("class", "truckIcon off");
            lushu.stop();//停止
            lushu.goPath(allArrPois);
            lushu.stop();//停止
        }

        function addMouseDown() {
            var div = document.getElementById('a4');
            div.setAttribute("class", "truckIcon on");
        }

        function addMouseUp() {
            var div = document.getElementById('a4');
            div.setAttribute("class", "truckIcon off");
            lushu._opts.speed = lushu._opts.speed + 500;//加速
        }

        function reduceMouseDown() {
            var div = document.getElementById('a5');
            div.setAttribute("class", "truckIcon on");
        }

        function reduceMouseUp() {
            var div = document.getElementById('a5');
            div.setAttribute("class", "truckIcon off");
            if (lushu._opts.speed != 500) {
                lushu._opts.speed = lushu._opts.speed - 500;//减速
            }
        }


        function $(element) {
            return document.getElementById(element);
        }


        //添加违规信息
        var wglist = JSON.parse('${illegalList}');//违规坐标点
        var data_info = [];
        for (var i = 0; i < wglist.length; i++) {  //循环LIST
            var con = '车牌号:' + plateNum + '<br>进入危险区域<br>时间：' + new Date(wglist[i].time).format("hh:mm:ss");
            var info = [];
            info = [wglist[i].lon, wglist[i].lat, con];
            data_info.push(info);
        }

        var opts = {
            width: 170,     // 信息窗口宽度
            height: 80,     // 信息窗口高度
            title: "信息窗口", // 信息窗口标题
            enableMessage: true//设置允许信息窗发送短息
        };
        for (var i = 0; i < data_info.length; i++) {
            var marker = new BMap.Marker(new BMap.Point(data_info[i][0], data_info[i][1]));  // 创建标注
            var content = data_info[i][2];
            map.addOverlay(marker);               // 将标注添加到地图中
            addClickHandler(content, marker);
        }

        function addClickHandler(content, marker) {
            marker.addEventListener("click", function (e) {
                    openInfo(content, e)
                }
            );
        }

        function openInfo(content, e) {
            var p = e.target;
            var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
            var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
            map.openInfoWindow(infoWindow, point); //开启信息窗口
        }


    });

    if (!'${plateNum}') {
        function ValidateStart(value) {
            var endtime = $("#endtime").datetimebox("getValue");
            if (endtime) {
                return compareDate(value, endtime);
            }
            return true;

        }

        function ValidateEnd(value) {
            var starttime = $("#starttime").datetimebox("getValue");
            if (starttime) {
                return compareDate(starttime, value);
            }
            return ture;
        }

        function compareDate(s1, s2) {
            return ((new Date(s1.replace(/-/g, "\/"))) < (new Date(s2.replace(/-/g, "\/"))));
        }

    }


</script>
</body>
</html>