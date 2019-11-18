
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
    <style type="text/css">
		ul,li{list-style: none;margin:0;padding:0;float:left;}
		html{height:100%}
		body{height:100%;margin:0px;padding:0px;font-family:"微软雅黑";}
		#container{height:100%;width:100%;}
		.BMap_cpyCtrl{ display:none; }  
    	.anchorBL{ display:none;}   
    </style>	
</head>
<body>
	<div id="container"></div>
</body>
</html>
<script type="text/javascript">
var mappoint='${mappoint}';
var map;
    $(function() {
    	$.ajax({
    		type : "POST",
    		url : "${ctx}/fmewx/sjfxx/showqylocation/",
    		dataType : 'json',
    		success : function(data) {
    			if (data != undefined) {
    			    var points =eval(data);
    			    if(!isSupportCanvas()){
    			    	alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
    			    }
    			    heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":30});
    				map.addOverlay(heatmapOverlay);
    				heatmapOverlay.setDataSet({data:points,max:100});
    			}
    		}
    	});
    	createMap();
    	// 创建地图函数：
    	function createMap() {
    		initMap("container",'','','','',1);
			    createPolygon(mappoint);
    	}
    	//创建多边形图
        function createPolygon(mappoint){
    		if(mappoint!=""){
    			var arry = mappoint.split("||");//lat，lnt
    			var maparry = [];//坐标数组
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
    				fillOpacity : 0.3
    			}); //创建多边形
    			map.addOverlay(polygon); //增加多边形
    			map.setViewport(polygon.getPath());
    		}
        }

    	//判断浏览区是否支持canvas
        function isSupportCanvas(){
            var elem = document.createElement('canvas');
            return !!(elem.getContext && elem.getContext('2d'));
        }
        function setGradient(){
         	var gradient = {};
         	var colors = document.querySelectorAll("input[type='color']");
         	colors = [].slice.call(colors,0);
         	colors.forEach(function(ele){
    			gradient[ele.getAttribute("data-key")] = ele.value; 
         	});
            heatmapOverlay.setOptions({"gradient":gradient});
        }
    });
	
</script>