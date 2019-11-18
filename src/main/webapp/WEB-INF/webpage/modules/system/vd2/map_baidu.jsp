<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>

<!doctype html>
<head>
    <meta charset="utf-8">
    <title>百度地图</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>
</head>
<body>

        <%-- 使用ThingJS --%>
        <%--<iframe id="thingJsMapiframe" name="thingJsMapiframe" style="width:100%;height:100%;border-width: 0;" src="https://www.thingjs.com/s/294e21f70715634e5600f07f"></iframe>--%>

        <%-- 使用百度地图 --%>
        <div id="bis_enterprise_dlg_map" style="width:100%;height: 100%;"></div>

    <script type="text/javascript">
        var qyid = '${qyid}';
        var cgLngLatList = eval('${cgLngLatList}');// 储罐经纬度集合
        var markers = [];

        $(function(){
            initMap("bis_enterprise_dlg_map", '${qylng}', '${qylat}', 17);
            showMarkerPoints(cgLngLatList);
        })

        // 在地图上展示marker点
        function showMarkerPoints(list) {
            if (list != null && list.length > 0) {
                $.each(list, function (index, el) {
                    var point = new BMap.Point(el.lng, el.lat);
                    var marker = new BMap.Marker(point);
                    markers.push(marker);
                    map.addOverlay(marker);
                    addClickHandler(el.id, marker);
                })
            }
        }

        var opts = {
            width: 170,     // 信息窗口宽度
            height: 140,     // 信息窗口高度
            title: "信息窗口", // 信息窗口标题
            enableMessage: true//设置允许信息窗发送短息
        };

        function addClickHandler(tankid, marker) {
            marker.addEventListener("click", function (e) {
                    openInfo(tankid, e)
                }
            );
        }

        function openInfo(tankid, e) {
            var content = showCgInfo(tankid);
            console.log(content);
            var p = e.target;
            var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
            var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
            map.openInfoWindow(infoWindow, point); //开启信息窗口
        }

        // 点击储罐marker点显示储罐实时数据相关信息
        function showCgInfo(tankid) {
            var html = '';
            $.ajax({
                type: 'POST',
                async: false,
                url: '${ctx}/zxjkyj/zdwxycg/getcgsssj/'+tankid,
                success: function (data) {
                    var cgxx = JSON.parse(data)[0];
                    console.log(cgxx);
                    // 储罐温度
                    var wdHtml = '';
                    if (cgxx.bjwd == '' || cgxx.bjwd == null || cgxx.bjwd == 0) {
                        wdHtml = '<div>温度：'+cgxx.wd+'℃</div>';
                    } else {
                        wdHtml = '<div style="color: red">温度：'+cgxx.wd+'℃</div>';
                    }
                    // 储罐压力
                    var ylHtml = '';
                    if (cgxx.bjyl != 0 || cgxx.bjyl == null || cgxx.bjyl == 0) {
                        ylHtml = '<div>压力：'+cgxx.yl+'kPa</div>';
                    } else {
                        ylHtml = '<div style="color: red">压力：'+cgxx.yl+'kPa</div>';
                    }
                    // 储罐液位
                    var ywHtml = '';
                    if (cgxx.bjyw != 0 || cgxx.bjyw == null || cgxx.bjyw == 0) {
                        ywHtml = '<div>液位：'+cgxx.yw+'m</div>';
                    } else {
                        ywHtml = '<div style="color: red">液位：'+cgxx.yw+'m</div>';
                    }

                    html = '<div>储罐位号：'+cgxx.wh+'</div>'+
                           '<div>储罐名称：'+cgxx.cgname+'</div>'+
                           '<div>物料名称：'+cgxx.wl+'</div>'+
                            wdHtml+
                            ylHtml+
                            ywHtml;
                }
            })
            return html;
        }

    </script>
</body>
</html>
