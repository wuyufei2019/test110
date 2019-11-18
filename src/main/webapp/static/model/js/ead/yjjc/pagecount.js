var yjjc_g_sr,yjjc_g_zr,yjjc_g_qr;
//救援路线
function onloadcount(){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/route",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
        success:function(data){
        	var now_point =  new BMap.Point(data.point_lng, data.point_lat );
	        map.centerAndZoom(now_point,16);
	        
	    	var marker = new BMap.Marker(now_point); //创建marker对象
	        map.addOverlay(marker); //在地图中添加marker
			marker.setPosition(now_point);//设置覆盖物位置
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			
        	if(data.type=='1'
        		||data.type=='2'
        		||data.type=='3'
        		||data.type=='4'
        		||data.type=='5'
        		||data.type=='6'){
        		yjjc_g_sr=parseFloat(data.sw);
        		yjjc_g_zr=parseFloat(data.zs);
        		yjjc_g_qr=parseFloat(data.qs);
//            	var ssr=JSON.stringify(sr).substring(0,(JSON.stringify(sr).indexOf(".")+3));
//            	var zzr=JSON.stringify(zr).substring(0,(JSON.stringify(zr).indexOf(".")+3));
//            	var qqr=JSON.stringify(qr).substring(0,(JSON.stringify(qr).indexOf(".")+3));

//    		    var title="死亡："+yjjc_g_sr+"米, 重伤："+ yjjc_g_zr+"米, 轻伤："+yjjc_g_qr+"米";
//    		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
//                label.setStyle({
//                    borderColor:"#808080",
//                    cursor:"pointer"
//                });
//    			marker.setLabel(label);
    		    createCircle(data);
        	}else if(data.type=='7'){
    		    string_split(data.lng1,data.lat1,data.lng2,data.lat2,data.lng3,data.lat3);
        	}else if(data.type=='8'){
	        	var markerArr = eval(data.data);
	        	for(var i=0;i<markerArr.length;i++){
	        		circleDrow(markerArr[i].lng,markerArr[i].lat,markerArr[i].r);
	        	}
        	}
        	
        }
	});
}

//创建Circle
function circleDrow(lng,lat,r){
	var circle = new BMap.Circle(new BMap.Point(lng,lat),r,{
        strokeColor: "#0092DC",
        strokeWeight: 1.5,
        fillColor: "#E2E8F1",
        fillOpacity: 0.5
    });
    map.addOverlay(circle);
}

//创建Polygon
function string_split(lngArray1,latArray1,lngArray2,latArray2,lngArray3,latArray3){  

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
function createCircle(data){
	var circle1 = new BMap.Circle(new BMap.Point(data.point_lng,data.point_lat),yjjc_g_sr,{strokeColor:"red", strokeWeight:1.5,strokeOpacity:0.8,fillColor:"#FF0000",fillOpacity:0.4});      /*死亡半径*/   
	var circle2 = new BMap.Circle(new BMap.Point(data.point_lng,data.point_lat),yjjc_g_zr,{strokeColor:"yellow", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#FFFF00",fillOpacity:0.2});    /*重伤半径*/ 
	var circle3 = new BMap.Circle(new BMap.Point(data.point_lng,data.point_lat),yjjc_g_qr,{strokeColor:"#0092DC", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#BBEBFE",fillOpacity:0.2});    /*轻伤半径*/
	map.addOverlay(circle1);
	map.addOverlay(circle2);
	map.addOverlay(circle3);
}

//取小数点2位
function strsubnumber2(str){
    var f = parseFloat( JSON.stringify(str).substring(0,(JSON.stringify(str).indexOf(".")+3)) );    
    if (isNaN(f)) {    
        return;    
    }    
	return  Math.round(f*100)/100;	
}