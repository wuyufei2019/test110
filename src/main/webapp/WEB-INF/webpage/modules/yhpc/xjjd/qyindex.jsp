<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检监督与考核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 	
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/xjjd/qyindex.js?v=1.5"></script>
</head>
<body >
<div class="easyui-tabs" fit="true">
	
   <div title="巡检统计" style="height:100%;" data-options="">
   <div id="yhpc_xjjd_tb1" style="padding:5px;height:auto">
      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <form id="yhpc_xjjd_searchFrom1" style="margin-bottom: 8px;" action="" class="form-inline">
                   <input type="text" id="yhpc_xjsj_start" name="yhpc_xjsj_start" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '开始时间'" />
                   <input type="text" id="yhpc_xjsj_end" name="yhpc_xjsj_end" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '结束时间'" />
                       <input id="tjlx" name="tjlx" class="easyui-combobox" style="height: 30px;" 
                        data-options="editable:false ,panelHeight:'auto' ,data: [
                           {value:'1',text:'按人员',selected:true},
                           {value:'2',text:'按隐患点'},
                           {value:'3',text:'按风险点'},
                           {value:'4',text:'按班次'}],
                        onSelect: function(rec){
                           if(rec.value=='1'){
                              flag=1;
                              tjdatagrid(1);
                           }
                           if(rec.value=='2'){
                              flag=2;
                              tjdatagrid(2);
                           }
                           if(rec.value=='3'){
                              flag=3;
                              tjdatagrid(3);
                           }
                           if(rec.value=='4'){
                              flag=4;
                              tjdatagrid(4);
                           }
                          }" />
                       <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search1()" ><i class="fa fa-search"></i> 查询</span>
               </form>
            </div>
         </div>
      </div>
         </div> 
   <table id="yhpc_xjjd_dg1"></table> 
   </div>
      <div title="巡检路线轨迹回放" style="height:100%;" data-options="">
         <form id="xjgj_searchFrom" style="margin-bottom: 8px;" class="form-inline">
             <input type="text" id="view_time" name="view_time" class="easyui-datebox" style="height: 30px;" data-options="required: true,editable:false,prompt: '巡检时间'" />
            <input name="view_xjry" id="view_xjry" style="height: 30px;" class="easyui-combobox"
                  data-options="required: true,prompt:'巡检人员',panelHeight:'100',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" />
            <input name="view_xjbc" id="view_xjbc" style="height: 30px;" class="easyui-combobox"
                  data-options="required: true,prompt:'巡检班次',panelHeight:'100',editable:false ,valueField: 'id',textField: 'text',url:'${ctx }/yhpc/bcrw/bclist' " />
            <input name="view_speed" id="view_speed" style="height: 30px;width:80px" class="easyui-combobox"
                  data-options="prompt:'折线速度',panelHeight:'auto',editable:false ,valueField: 'value',textField: 'text',data : [{value : '1000',text : '1s'},
                  {value : '2000',text : '2s'},{value : '3000',text : '3s'},] " />
                 <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</span>
         </form>
         <tr>
            <div id="map_view" style="width:100%;height: 100%;"></div>
         </tr>    
</div>
</div>
<script type="text/javascript">
	var Gpoints = [];//原始点信息数组  
	var Gn=2;
	var Gn2=2;
    initMap("map_view", 0, 0);
    var splitarray = [];
    var Gf = true;
    var timer;
    var timer2;
    var Gsecond = 1000;
/*     var sy = new BMap.Symbol(BMap_Symbol_SHAPE_BACKWARD_OPEN_ARROW, {
	    scale: 0.6,//图标缩放大小
	    strokeColor:'#fff',//设置矢量图标的线填充颜色
	    strokeWeight: '2',//设置线宽
	});
	var icons = new BMap.IconSequence(sy, '20', '40'); */
    $(function() {
      	var curr_time = new Date();
      	var strDate = curr_time.getFullYear() + "-";
      	strDate += curr_time.getMonth() + 1 + "-";
      	strDate += curr_time.getDate();
      	var strDate2 = curr_time.getFullYear() + "-01-01";
      	$("#yhpc_xjsj_start").datebox("setValue", strDate2);
      	$("#yhpc_xjsj_end").datebox("setValue", strDate);
      
      	$(".erm_bncs_form_id_table td").mouseover(function() {
      	    $(this).addClass("over");
      	});
      	$(".erm_bncs_form_id_table td").mouseout(function() {
      	    $(this).removeClass("over");
      	});
     
    });
    
    function reload(){
	window.clearTimeout(timer2);
	window.clearTimeout(timer);
		map.clearOverlays();
      	 Gpoints = [];//原始点信息数组  
      	 Gn=2;
      	 Gn2=2;
         splitarray = [];
         Gsecond = 1000;
    }
    
    function reset(){
		$("#xjgj_searchFrom").form("clear");
		reload();
	}
    function search(){
	var isValid = $("#xjgj_searchFrom").form('validate');
	if(!isValid) return ;
	reload();
	var s = $("#view_speed").combobox("getValue");
	if(s){
	    Gsecond = s;
	}
 	$.ajax({
      	    type : 'post',
      	    url : "${ctx}/yhpc/xjjd/xjgjhf",
      	    async : false,
      	    data : $("#xjgj_searchFrom").serializeObject(),
      	    success : function(data) {
      			if(data.length!=0){
      				Gpoints=data;
   					var point = new BMap.Point(Gpoints[0].lng, Gpoints[0].lat);
   	      			map.centerAndZoom(point, 20);  
					var iconImg = createIcon();
				    var marker = new BMap.Marker(point,{icon:iconImg});
   	      			//var marker = new BMap.Marker(point)
   	      			map.addOverlay(marker);//添加首个坐标
   	      			var ftime=new Date(Gpoints[0].createtime).format("hh:mm:ss");
   	      			var label = new BMap.Label(ftime,{offset:new BMap.Size(25,10)});
      	      		label.setStyle({
      	      		    maxWidth: "none"
      	      		});//解决和bootstrap的冲突
					marker.setLabel(label);
      				dynamicLine();
      			}
      	
      	    }
      	});
    }
    
  		//执行划线操作
		function drawPic(points){ 
			window.clearTimeout(timer);
		    Gn2 =2;
		    var linePoints = [],pointsLen = points.length,i,polyline;  
		    if(pointsLen == 0){  
		        return;  
		    }  
  		    var firstp = points[0];//第一个点
  		    var secondp = points[1];//第二个点
			splitarray = splitArray(firstp,secondp);
			test();
		}
  		
  		
  		function test (){
			addLine(splitarray.slice(Gn2-2, Gn2));
			timer = setTimeout(test, Gsecond/15); 
			if(Gn2==splitarray.length+1){
				window.clearTimeout(timer);
		    }
  		}
  		
  		//添加折线
		function addLine(points){ 
		    var polyline =new BMap.Polyline(points, {
			    //icons:[icons],
			    strokeWeight:'6',//折线的宽度，以像素为单位
			    strokeOpacity: 0.8,//折线的透明度，取值范围0 - 1
			    strokeColor:"#fc7373" //折线颜色
			 });
		    map.addOverlay(polyline);
		    Gn2++;
		   	var len = splitarray.length;
		   	if(Gn2==len+1){
  		  		var point = splitarray[len-1];
		   	  //添加第二个marker点
		   	    var iconImg = createIcon();
				var marker = new BMap.Marker(point,{icon:iconImg});
		   	  	//var marker = new BMap.Marker(point);
				map.addOverlay(marker); 
				var label = new BMap.Label(new Date(Gpoints[Gn-2].createtime).format("hh:mm:ss"),{offset:new BMap.Size(25,10)});
				marker.setLabel(label);
				label.setStyle({
	      	       maxWidth: "none"
	      	     });//解决和bootstrap的冲突
				//map.centerAndZoom(point, 18);  
		   	} 	
		}
  		
  		//细粒化坐标点，平滑曲线用
		function splitArray(firstp,secondp){  
		    var count = 10;
		    var points = [];
		    var diflng = secondp.lng - firstp.lng;
		    var diflat = secondp.lat - firstp.lat;
		    var childlng = diflng/10;
		    var childlat = diflat/10;
		    for(var i = 0; i <= count ;i++){
				var point = new BMap.Point(parseFloat(parseFloat(firstp.lng)+childlng*i), parseFloat(parseFloat(firstp.lat)+childlat*i))
				points.push(point);
		    }
		    return points;
		}
  		
		//选取两个坐标点生成折线
		function dynamicLine(){  
		    if(Gn<=Gpoints.length){
      		    newLinePoints = Gpoints.slice(Gn-2, Gn);//每次选取两个点来生成折线
      		    drawPic(newLinePoints);//增加轨迹线  
		    }
         	Gn++;
		    timer2 = setTimeout(dynamicLine, Gsecond); 
		    if(Gn==Gpoints.length+2){
				window.clearTimeout(timer2);
				window.clearTimeout(timer);
		    }
		} 
		
		 //创建一个Icon
		    function createIcon(){
		        var icon = new BMap.Icon("${ctx}/static/model/images/locat.png",new BMap.Size(24,48),{anchor : new BMap.Size(8,37)});
		        return icon;
		    }
		
</script>
</body>
</html>