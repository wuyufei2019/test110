	var map;
	//id（地图容器id）lng，lat 经纬度 ,zoom缩放等级
    function initMap(div, lng, lat, zoom,cityname,type){
        createMap(div,lng,lat,zoom,cityname,type);//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
    }
    
    //创建地图函数：
    function createMap(div,lng,lat,zoom,cityname,type){
    	map=(type?(new BMap.Map(div)):(new BMap.Map(div, {mapType:BMAP_SATELLITE_MAP})));
    	 if(!lng&&!lat){
         	//默认中心点
         	var point = new BMap.Point(119.345938,34.765698);
         }else{
         	var point = new BMap.Point(lng,lat);//定义一个中心点坐标
         }
        if(!zoom) zoom=15;
        if(!cityname) cityname="连云港";
        map.centerAndZoom(point,zoom);//设定地图的中心点和坐标并将地图显示在地图容器中
        map.addControl(new BMap.NavigationControl());
        map.setCurrentCity(cityname);         
        map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
        window.map = map;//将map变量存储在全局
        window.point = point;
    }
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.disableDoubleClickZoom();
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //获取地图，方便自主设置地图函数
    function getMap(){
    	return map;
    }
    
    //地图控件添加函数：
    function addMapControl(){
                        //向地图中添加比例尺控件
	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_TOP_LEFT});
	map.addControl(ctrl_sca);
    }
