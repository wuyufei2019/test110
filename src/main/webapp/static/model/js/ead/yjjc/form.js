var count=0,pide,timec=10,reftime="", leadata={},sr,zr,qr;
$(function() {
	initMap();//创建和初始化地图
	
	//创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        addMarker();//向地图中添加marker
        //addRemark();//向地图中添加文字标注
    	if(count==0){
    		layer.msg("请选择事故发生点！",{time: 2000});
    	}
    }
    
    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
        var point = new BMap.Point(116.403119,39.919141);//(120.034376,31.946553);//定义一个中心点坐标
        map.centerAndZoom(point,15);//设定地图的中心点和坐标并将地图显示在地图容器中
        map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
        map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
        map.addControl(new BMap.NavigationControl());
        window.map = map;//将map变量存储在全局
        window.point = point;
       
    }
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //地图控件添加函数：
    function addMapControl(){
         //向地图中添加比例尺控件
		var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_TOP_LEFT});
		map.addControl(ctrl_sca);
    }
    
    //创建marker
    function addMarker(){
		
		map.addEventListener("click", function(e){ 
			count=count+1;
			map.clearOverlays();
			var now_point =  new BMap.Point(e.point.lng, e.point.lat );
	    	var marker = new BMap.Marker(now_point); //创建marker对象
			map.addOverlay(marker); //在地图中添加marker
			marker.enableDragging();
			marker.setPosition(now_point);//设置覆盖物位置
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		    map.addOverlay(marker);
		    pide=e;
		    window.pide=pide;
		    onloadacacount();
		});
		
    }
    
});

function onloadacacount(){
 
	layer.open({
	    type: 2,  
	    area: ['750px', '400px'],
	    title: '事故后果计算',
        maxmin: false, 
        shift: 1,
        content: ctx+'/ead/yjjc/form',
        success: function(layero, index){
	    	var body = top.layer.getChildFrame('body', index);
	    	body.find("#ead_yjjc_form_id_hid_lng").val(pide.point.lng);
	        body.find("#ead_yjjc_form_id_hid_lat").val(pide.point.lat);
	        body.find("#ead_yjjc_form_id_hid_weathertoday_fx").val(parent.global_weathertoday_fx);
	        
	    },
	});
	 
	
}


//计算事故后果
function fireball(){
	if($("#ead_yjjc_form_mainform").form('validate')){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createfireball",
			data:$("#ead_yjjc_form_mainform").serialize(),
			dataType: 'json',
			async:false,
			beforeSend:function(){
				parent.$.messager.show({ title : "提示",msg: "数据载入中,请稍候...", position: "bottomRight" });
			}, 
            success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);
//            	var ssr=JSON.stringify(sr).substring(0,(JSON.stringify(sr).indexOf(".")+3));
//            	var zzr=JSON.stringify(zr).substring(0,(JSON.stringify(zr).indexOf(".")+3));
//            	var qqr=JSON.stringify(qr).substring(0,(JSON.stringify(qr).indexOf(".")+3));
            	parent.$.messager.show({ title : "提示",msg: "死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米", position: "bottomRight" });

//				$("#ead_yjjc_add_div").window('destroy');
				map.clearOverlays();
            	var now_point =  new BMap.Point(pide.point.lng, pide.point.lat );
    	    	var marker = new BMap.Marker(now_point); //创建marker对象
    			map.addOverlay(marker); //在地图中添加marker
    			marker.setPosition(now_point);//设置覆盖物位置
    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    		    var title="死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米";
    		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
                label.setStyle({
                    borderColor:"#808080",
                    cursor:"pointer"
                });
    			marker.setLabel(label);
    		    map.addOverlay(marker);
    		    createCircle(pide);
    		    
    		    parent.mainpage.mainTabs.addModule('应急调度与处置',ctx+'/ead/yjjc/consequence/'+data.id,'icon-standard-book'); 
            }
		});
	}
//    parent.mainpage.mainTabs.addModule('事故后果',ctx+'/ead/yjjc/consequence/'+56,'icon-standard-book'); 

}	

//计算事故后果
function gasphysical(){
	if($("#ead_yjjc_form_mainform").form('validate')){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/creategasphysical",
			data:$("#ead_yjjc_form_mainform").serialize(),
			dataType: 'json',
			async:false,
			beforeSend:function(){
				parent.$.messager.show({ title : "提示",msg: "数据载入中,请稍候...", position: "bottomRight" });
			}, 
            success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);
//            	var ssr=JSON.stringify(sr).substring(0,(JSON.stringify(sr).indexOf(".")+3));
//            	var zzr=JSON.stringify(zr).substring(0,(JSON.stringify(zr).indexOf(".")+3));
//            	var qqr=JSON.stringify(qr).substring(0,(JSON.stringify(qr).indexOf(".")+3));
            	parent.$.messager.show({ title : "提示",msg: "死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米", position: "bottomRight" });

//				$("#ead_yjjc_add_div").window('destroy');
				map.clearOverlays();
            	var now_point =  new BMap.Point(pide.point.lng, pide.point.lat );
    	    	var marker = new BMap.Marker(now_point); //创建marker对象
    			map.addOverlay(marker); //在地图中添加marker
    			marker.setPosition(now_point);//设置覆盖物位置
    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    		    var title="死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米";
    		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
                label.setStyle({
                    borderColor:"#808080",
                    cursor:"pointer"
                });
    			marker.setLabel(label);
    		    map.addOverlay(marker);
    		    createCircle(pide);

    		    parent.mainpage.mainTabs.addModule('应急调度与处置',ctx+'/ead/yjjc/consequence/'+data.id,'icon-standard-book'); 
            }
		});
	}
}	

//喷射火
function jetfire(){
	if($("#ead_yjjc_form_mainform").form('validate')){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createjetfire",
			data:$("#ead_yjjc_form_mainform").serialize(),
			dataType: 'json',
			async:false,
			beforeSend:function(){
				parent.$.messager.show({ title : "提示",msg: "数据载入中,请稍候...", position: "bottomRight" });
			}, 
           success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);
//            	var ssr=JSON.stringify(sr).substring(0,(JSON.stringify(sr).indexOf(".")+3));
//            	var zzr=JSON.stringify(zr).substring(0,(JSON.stringify(zr).indexOf(".")+3));
//            	var qqr=JSON.stringify(qr).substring(0,(JSON.stringify(qr).indexOf(".")+3));
            	parent.$.messager.show({ title : "提示",msg: "死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米", position: "bottomRight" });

//				$("#ead_yjjc_add_div").window('destroy');
				map.clearOverlays();
            	var now_point =  new BMap.Point(pide.point.lng, pide.point.lat );
    	    	var marker = new BMap.Marker(now_point); //创建marker对象
    			map.addOverlay(marker); //在地图中添加marker
    			marker.setPosition(now_point);//设置覆盖物位置
    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    		    var title="死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米";
    		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
                label.setStyle({
                    borderColor:"#808080",
                    cursor:"pointer"
                });
    			marker.setLabel(label);
    		    map.addOverlay(marker);
    		    createCircle(pide);

    		    parent.mainpage.mainTabs.addModule('应急调度与处置',ctx+'/ead/yjjc/consequence/'+data.id,'icon-standard-book'); 
           }
		});
	}
}

//持续泄漏
function leakage(){
	if($("#ead_yjjc_form_mainform").form('validate')){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createleakage",
			data:$("#ead_yjjc_form_mainform").serialize(),
			dataType: 'json',
			async:false,
			beforeSend:function(){
				layer.load(1, {  shade: [0.1,'#fff'] //0.1透明度的白色背景
					});
//				parent.$.messager.show({ title : "提示",msg: "数据载入中,请稍候...", position: "bottomRight" });
			}, 
           success:function(data){
            	parent.$.messager.show({ title : "提示",msg: "计算完成！", position: "bottomRight" });
            	sr=data.lng1;
            	zr=data.lng2;
            	qr=data.lng3;

//				$("#ead_yjjc_add_div").window('destroy');
				map.clearOverlays();
            	var now_point =  new BMap.Point(pide.point.lng, pide.point.lat );
    	    	var marker = new BMap.Marker(now_point); //创建marker对象
    			map.addOverlay(marker); //在地图中添加marker
    			marker.setPosition(now_point);//设置覆盖物位置
    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

    		    map.addOverlay(marker);
    		    string_split(data.lng1,data.lat1,data.lng2,data.lat2,data.lng3,data.lat3);
            	
    		    parent.mainpage.mainTabs.addModule('应急调度与处置',ctx+'/ead/yjjc/consequence/'+data.id,'icon-standard-book');
            }
		});
	}
}	

//物理爆炸（压力容器爆炸）
function physical(){
	if($("#ead_yjjc_form_mainform").form('validate')){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createphysical",
			data:$("#ead_yjjc_form_mainform").serialize(),
			dataType: 'json',
			async:false,
			beforeSend:function(){
				parent.$.messager.show({ title : "提示",msg: "数据载入中,请稍候...", position: "bottomRight" });
			}, 
           success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);
//            	var ssr=JSON.stringify(sr).substring(0,(JSON.stringify(sr).indexOf(".")+3));
//            	var zzr=JSON.stringify(zr).substring(0,(JSON.stringify(zr).indexOf(".")+3));
//            	var qqr=JSON.stringify(qr).substring(0,(JSON.stringify(qr).indexOf(".")+3));
            	parent.$.messager.show({ title : "提示",msg: "死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米", position: "bottomRight" });

//				$("#ead_yjjc_add_div").window('destroy');
				map.clearOverlays();
            	var now_point =  new BMap.Point(pide.point.lng, pide.point.lat );
    	    	var marker = new BMap.Marker(now_point); //创建marker对象
    			map.addOverlay(marker); //在地图中添加marker
    			marker.setPosition(now_point);//设置覆盖物位置
    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    		    var title="死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米";
    		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
                label.setStyle({
                    borderColor:"#808080",
                    cursor:"pointer"
                });
    			marker.setLabel(label);
    		    map.addOverlay(marker);
            	createCircle(pide);
            	
            	parent.mainpage.mainTabs.addModule('应急调度与处置',ctx+'/ead/yjjc/consequence/'+data.id,'icon-standard-book');
            }
		});
	}
}	

//池火灾
function poolfire(){
	 
	if($("#ead_yjjc_form_mainform").form('validate')){
		
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createpoolfire",
			data:$("#ead_yjjc_form_mainform").serialize(),
			dataType: 'json',
			async:false,
			beforeSend:function(){
				 $.messager.show({ title : "提示",msg: "数据载入中,请稍候...", position: "bottomRight" });
			}, 
           success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);
//            	var ssr=JSON.stringify(sr).substring(0,(JSON.stringify(sr).indexOf(".")+3));
//            	var zzr=JSON.stringify(zr).substring(0,(JSON.stringify(zr).indexOf(".")+3));
//            	var qqr=JSON.stringify(qr).substring(0,(JSON.stringify(qr).indexOf(".")+3));
//            	parent.$.messager.show({ title : "提示",msg: "死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米", position: "bottomRight" });

//				$("#ead_yjjc_add_div").window('destroy');
				map.clearOverlays();
            	var now_point =  new BMap.Point(pide.point.lng, pide.point.lat );
    	    	var marker = new BMap.Marker(now_point); //创建marker对象
    			map.addOverlay(marker); //在地图中添加marker
    			marker.setPosition(now_point);//设置覆盖物位置
    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    		    var title="死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米";
    		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
                label.setStyle({
                    borderColor:"#808080",
                    cursor:"pointer"
                });
    			marker.setLabel(label);
    		    map.addOverlay(marker);
            	createCircle(pide);
            	
            	parent.mainpage.mainTabs.addModule('应急调度与处置',ctx+'/ead/yjjc/consequence/'+data.id,'icon-standard-book');
            }
		});
	}
}

//化学爆炸（蒸气云爆炸）
function vce(){
	if($("#ead_yjjc_form_mainform").form('validate')){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createvce",
			data:$("#ead_yjjc_form_mainform").serialize(),
			dataType: 'json',
			async:false,
			beforeSend:function(){
				parent.$.messager.show({ title : "提示",msg: "数据载入中,请稍候...", position: "bottomRight" });
			}, 
           success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);
//            	var ssr=JSON.stringify(sr).substring(0,(JSON.stringify(sr).indexOf(".")+3));
//            	var zzr=JSON.stringify(zr).substring(0,(JSON.stringify(zr).indexOf(".")+3));
//            	var qqr=JSON.stringify(qr).substring(0,(JSON.stringify(qr).indexOf(".")+3));
            	parent.$.messager.show({ title : "提示",msg: "死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米", position: "bottomRight" });

//				$("#ead_yjjc_add_div").window('destroy');
				map.clearOverlays();
            	var now_point =  new BMap.Point(pide.point.lng, pide.point.lat );
    	    	var marker = new BMap.Marker(now_point); //创建marker对象
    			map.addOverlay(marker); //在地图中添加marker
    			marker.setPosition(now_point);//设置覆盖物位置
    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    		    var title="死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米";
    		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
                label.setStyle({
                    borderColor:"#808080",
                    cursor:"pointer"
                });
    			marker.setLabel(label);
    		    map.addOverlay(marker);
            	createCircle(pide);
            	
            	parent.mainpage.mainTabs.addModule('应急调度与处置',ctx+'/ead/yjjc/consequence/'+data.id,'icon-standard-book');
            }
		});
	}
}	

//瞬时泄漏
function instantleakage(){
	if($("#ead_yjjc_form_mainform").form('validate')){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createinstantleakage",
			data:$("#ead_yjjc_form_mainform").serialize(),//$("#aca_instantleakage_form_mainform").serialize(),
			dataType: 'json',
			async:false,
			beforeSend:function(){
				parent.$.messager.show({ title : "提示",msg: "数据载入中,请稍候...", position: "bottomRight" });
			}, 
	        success:function(data){
	        	map.clearOverlays();
            	var now_point =  new BMap.Point(pide.point.lng, pide.point.lat );
    	    	var marker = new BMap.Marker(now_point); //创建marker对象
    			map.addOverlay(marker); //在地图中添加marker
    			marker.setPosition(now_point);//设置覆盖物位置
    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	        	
	        	var markerArr = eval(data.data);
	        	for(var i=0;i<markerArr.length;i++){
	        		circleDrow(markerArr[i].lng,markerArr[i].lat,markerArr[i].r);
	        	}
//        		$("#ead_yjjc_add_div").window('destroy');
        		parent.$.messager.show({ title : "提示",msg: "一共用时:"+ formatSeconds(data.time), position: "bottomRight" });

            	parent.mainpage.mainTabs.addModule('应急调度与处置',ctx+'/ead/yjjc/consequence/'+data.id,'icon-standard-book');
	        
	        }
		});
	}
}	

//瞬时泄漏
function instantleakage2(){
	timec=timec+10;//使用时长
	leadata.M9=timec;
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/createinstantleakage",
		data:leadata,
		dataType: 'json',
        success:function(data){
        	if(data.r<=1){
        		clearInterval(reftime);
        		parent.$.messager.show({ title : "提示",msg: "一共用时："+ formatSeconds(timec-10), position: "bottomRight" });
        		timec=0;
        	}
//        	map.removeOverlay(circle);
		    circleDrow(data.lng,data.lat,data.r);
        }
	});
}	


//创建Circle
function circleDrow(lng,lat,r){
//  circle = new BMap.Circle(new BMap.Point(lng,lat),r);
	var circle = new BMap.Circle(new BMap.Point(lng,lat),r,{
        strokeColor: "#0092DC",
        strokeWeight: 1.5,
        fillColor: "#E2E8F1",
        fillOpacity: 0.5
    });
    map.addOverlay(circle);
}

//创建Polygon
function string_split(lngArray1,latArray1,lngArray2,latArray2,lngArray3,latArray3) 
{  

	var arr_lng1=lngArray1.split("%"); //字符分割
	var arr_lat1=latArray1.split("%"); //字符分割
	var PAS1 = new Array();
	var num1 = arr_lat1.length; 
	for(var i = 0 ; i < num1;i++){ PAS1[i] =  new BMap.Point(arr_lng1[i],arr_lat1[i]);};	
		var wound1 = new BMap.Polygon(PAS1,{strokeColor:"red", strokeWeight:2,strokeOpacity:0.7,fillColor:"#FF0000",fillOpacity:0.2}); 
		map.addOverlay(wound1); 

	var arr_lng2=lngArray2.split("%"); //字符分割
	var arr_lat2=latArray2.split("%"); //字符分割
	var PAS2 = new Array();
	var num2 = arr_lat2.length; 
	for(var i = 0 ; i < num2;i++){	PAS2[i] =  new BMap.Point(arr_lng2[i],arr_lat2[i]); };
	var wound2 = new BMap.Polygon(PAS2,{strokeColor:"yellow", strokeWeight:2,strokeOpacity:0.7,fillColor:"#FFFF00",fillOpacity:0.2}); 
		map.addOverlay(wound2); 

	var arr_lng3=lngArray3.split("%"); //字符分割
	var arr_lat3=latArray3.split("%"); //字符分割
	var PAS3 = new Array();
	var num3 = arr_lat3.length; 		
	for(var i = 0 ; i < num3;i++){	PAS3[i] =  new BMap.Point(arr_lng3[i],arr_lat3[i]); };
	var wound3 = new BMap.Polygon(PAS3,{strokeColor:"#0092DC", strokeWeight:2,strokeOpacity:1,fillColor:"#BBEBFE",fillOpacity:0.2}); 
		map.addOverlay(wound3); 
};

//创建Circle
function createCircle(e){
	var circle1 = new BMap.Circle(new BMap.Point(e.point.lng,e.point.lat),sr,{strokeColor:"red", strokeWeight:1.5,strokeOpacity:0.8,fillColor:"#FF0000",fillOpacity:0.4});      /*死亡半径*/   
	var circle2 = new BMap.Circle(new BMap.Point(e.point.lng,e.point.lat),zr,{strokeColor:"yellow", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#FFFF00",fillOpacity:0.2});    /*重伤半径*/ 
	var circle3 = new BMap.Circle(new BMap.Point(e.point.lng,e.point.lat),qr,{strokeColor:"#0092DC", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#BBEBFE",fillOpacity:0.2});    /*轻伤半径*/
	map.addOverlay(circle1);
	map.addOverlay(circle2);
	map.addOverlay(circle3);
}

function formatSeconds(value) {
	var theTime = parseInt(value);// 秒
	var theTime1 = 0;// 分
	var theTime2 = 0;// 小时

	if(theTime > 60) {
	theTime1 = parseInt(theTime/60);
	theTime = parseInt(theTime%60);

	if(theTime1 > 60) {
	theTime2 = parseInt(theTime1/60);
	theTime1 = parseInt(theTime1%60);
	}
	}
	var result = ""+parseInt(theTime)+"秒";
	if(theTime1 > 0) {
	result = ""+parseInt(theTime1)+"分"+result;
	}
	if(theTime2 > 0) {
	result = ""+parseInt(theTime2)+"小时"+result;
	}
	return result;
} 

function cutImage(){
//    preventDefault();  
    html2canvas(document.body, {  
    allowTaint: true,  
    taintTest: false,  
    onrendered: function(canvas) {  
    	var canvas1 = document.getElementById("canvas");
//        canvas.id = "mycanvas";  
//        document.body.appendChild(canvas);  
        //生成base64图片数据  
        var dataUrl = canvas1.toDataURL();  
//        alert(dataUrl);
//        var newImg = document.createElement("img");  
//        newImg.setAttribute('crossOrigin', 'anonymous');
//        newImg.crossOrigin = "*";
//        newImg.src =  dataUrl;  
//        document.body.appendChild(newImg); 
    }
    });  
}

//取小数点2位
function strsubnumber2(str){
    var f = parseFloat( JSON.stringify(str).substring(0,(JSON.stringify(str).indexOf(".")+3)) );    
    if (isNaN(f)) {    
        return;    
    }    
	return  Math.round(f*100)/100;	
}