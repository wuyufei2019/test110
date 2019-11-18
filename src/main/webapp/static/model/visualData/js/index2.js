var map;
//id（地图容器id）lng，lat 经纬度 ,zoom缩放等级
function initMap(div, lng, lat, zoom,cityname,type){
    createMap(div,lng,lat,zoom,cityname,type);//创建地图
    setMapEvent();//设置地图事件
}

//创建地图函数：
function createMap(div,lng,lat,zoom,cityname,type){
    var map=(type?(new BMap.Map(div)):(new BMap.Map(div, {mapType:BMAP_NORMAL_MAP})));
    var point ;
    if(!lng&&!lat){
        //默认中心点
        if(userlng&&userlat){
            point = new BMap.Point(userlng,userlat);
        }else{
            point = new BMap.Point(120.398971,32.049625);
        }
    }else{
        point = new BMap.Point(lng,lat);//定义一个中心点坐标
    }
    if(!zoom) zoom=15;
    if(!cityname) cityname="靖江";
    map.centerAndZoom(point,zoom);//设定地图的中心点和坐标并将地图显示在地图容器中
    map.addControl(new BMap.NavigationControl());
    map.setCurrentCity(cityname);
   // map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
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


$(function(){
  // init();
  //init2();
    $("#el-dialog").addClass("hide");
  $(".close").click(function(event) {
    $("#el-dialog").addClass("hide");
  });

  var date = new Date();
     var numble = date.getDate();
     var today = getFormatMonth(new Date());
     $("#date1").html(today);
     $("#date2").html(today);
     $("#date3").html(today);
     $("#date4").html(today);


  lay('.demo-input').each(function(){
     laydate.render({
        type: 'month',
         elem: this,
         trigger: 'click',
         theme: '#95d7fb',
         calendar: true,
         showBottom: true,
         done: function () {
            console.log( $("#startDate").val())

         }
     })
 });

})


//mapChart渲染
function init(){
      var mapdata;
       $.ajax({
           type : "POST",
           url : ctx+"/bis/qyjbxx/maplist2",
           async:false,
           dataType : 'json',
           success : function(data) {
               mapdata= eval(data.data);
               mapChart = echarts.init(document.getElementById('mapChart'));
           }
       });
     //地图
      mapChart.setOption({
           bmap: {
               center: [120.398971,32.049625],
               zoom: 14,
               roam: true,

           },
           tooltip : {
               trigger: 'item',
               formatter:function(params, ticket, callback){
                   return params.value[2]
               }
           },
           series: [{
               type: 'scatter',
               coordinateSystem: 'bmap',
               data: mapdata
           }]
       });

     mapChart.on('click', function (params) {
         $("#el-dialog").removeClass('hide');
         $("#reportTitle").html(params.value[2]);
     });

  var bmap = mapChart.getModel().getComponent('bmap').getBMap()
  bmap.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP ]}));
  bmap.setMapStyle({style:'midnight'})

}


window.addEventListener("resize", function() {
	mapChart.resize();
});