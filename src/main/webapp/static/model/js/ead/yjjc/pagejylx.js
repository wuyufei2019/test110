var yjjc_g_lng,yjjc_g_lat,yjjc_g_sr,yjjc_g_zr,yjjc_g_qr;
//救援路线
function onloadjylx(){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/route",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
        success:function(data){
        	yjjc_g_lng=data.point_lng;
        	yjjc_g_lat=data.point_lat;
        	var now_point =  new BMap.Point(data.point_lng, data.point_lat );
	        map.centerAndZoom(now_point,16);//设定地图的中心点和坐标并将地图显示在地图容器中
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
        		yjjc_g_sr=yjstrsubnumber2(data.sw);
        		yjjc_g_zr=yjstrsubnumber2(data.zs);
        		yjjc_g_qr=yjstrsubnumber2(data.qs);
    		    var title="死亡："+yjjc_g_sr+"米, 重伤："+ yjjc_g_zr+"米, 轻伤："+yjjc_g_qr+"米";
    		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
                label.setStyle({
                    borderColor:"#808080",
                    cursor:"pointer"
                });
    		    createCircle(data);
        	}else if(data.type=='7'){
    		    string_split(data.lng1,data.lat1,data.lng2,data.lat2,data.lng3,data.lat3);
        	}else if(data.type=='8'){
	        	var markerArr = eval(data.data);
	        	for(var i=0;i<markerArr.length;i++){
	        		circleDrow(markerArr[i].lng,markerArr[i].lat,markerArr[i].r);
	        	}
        	}
        	
        	var myIconpng0=ctx+"/static/model/images/map/ssfx.png";
        	var myIconpng0BMapSize=new BMap.Size(150,38);
        	var myIconpng0anchor=new BMap.Size(-150,38);
        	
        	var myIconpngfx=ctx+"/static/model/images/map/fxdf.png";
        	var myIconpngfxBMapSize=new BMap.Size(92,92);
        	var myIconpngfxanchor=new BMap.Size(-100,38);
        	if(data.fx=='北风'){
        		myIconpng0=ctx+"/static/model/images/map/ssfx0.png";
        		myIconpng0BMapSize=new BMap.Size(202,183);
            	myIconpng0anchor=new BMap.Size(101,200);

            	myIconpngfx=ctx+"/static/model/images/map/fxnf.png";
            	myIconpngfxBMapSize=new BMap.Size(92,92);
            	myIconpngfxanchor=new BMap.Size(25,-46);
        	}else if(data.fx=='东北风'){
        		myIconpng0=ctx+"/static/model/images/map/ssfx45.png";
        		myIconpng0BMapSize=new BMap.Size(202,183);
            	myIconpng0anchor=new BMap.Size(0,200);
        		
            	myIconpngfx=ctx+"/static/model/images/map/fxxnf.png";
            	myIconpngfxBMapSize=new BMap.Size(92,92);
            	myIconpngfxanchor=new BMap.Size(160,-46);
         	}else if(data.fx=='东风'){
        		myIconpng0=ctx+"/static/model/images/map/ssfx90.png";
        		myIconpng0BMapSize=new BMap.Size(202,183);
            	myIconpng0anchor=new BMap.Size(-80,90);
        		
            	myIconpngfx=ctx+"/static/model/images/map/fxxf.png";
            	myIconpngfxBMapSize=new BMap.Size(92,92);
            	myIconpngfxanchor=new BMap.Size(150,30);
        	}else if(data.fx=='东南风'){
        		myIconpng0=ctx+"/static/model/images/map/ssfx135.png";
        		myIconpng0BMapSize=new BMap.Size(202,183);
            	myIconpng0anchor=new BMap.Size(-20,-20);
       		
            	myIconpngfx=ctx+"/static/model/images/map/fxxbf.png";
            	myIconpngfxBMapSize=new BMap.Size(92,92);
            	myIconpngfxanchor=new BMap.Size(160,160);
         	}else if(data.fx=='南风'){
        		myIconpng0=ctx+"/static/model/images/map/ssfx180.png";
        		myIconpng0BMapSize=new BMap.Size(202,183);
            	myIconpng0anchor=new BMap.Size(101,-90);

            	myIconpngfx=ctx+"/static/model/images/map/fxbf.png";
            	myIconpngfxBMapSize=new BMap.Size(92,92);
            	myIconpngfxanchor=new BMap.Size(25,150);
        	}else if(data.fx=='西南风'){
        		myIconpng0=ctx+"/static/model/images/map/ssfx225.png";
        		myIconpng0BMapSize=new BMap.Size(202,183);
            	myIconpng0anchor=new BMap.Size(200,0);
        		
            	myIconpngfx=ctx+"/static/model/images/map/fxdbf.png";
            	myIconpngfxBMapSize=new BMap.Size(92,92);
            	myIconpngfxanchor=new BMap.Size(-46,138);
        	}else if(data.fx=='西风'){
        		myIconpng0=ctx+"/static/model/images/map/ssfx270.png";
        		myIconpng0BMapSize=new BMap.Size(202,183);
            	myIconpng0anchor=new BMap.Size(303,90);
       		
            	myIconpngfx=ctx+"/static/model/images/map/fxdf.png";
            	myIconpngfxBMapSize=new BMap.Size(92,92);
            	myIconpngfxanchor=new BMap.Size(-150,30);
        	}else if(data.fx=='西北风'){
        		myIconpng0=ctx+"/static/model/images/map/ssfx315.png";
        		myIconpng0BMapSize=new BMap.Size(202,183);
            	myIconpng0anchor=new BMap.Size(202,190);
        		
            	myIconpngfx=ctx+"/static/model/images/map/fxdnf.png";
            	myIconpngfxBMapSize=new BMap.Size(92,92);
            	myIconpngfxanchor=new BMap.Size(-90,-100);
        	}
        	
        	var myIcon0 = new BMap.Icon(myIconpng0, myIconpng0BMapSize,{
        		anchor: myIconpng0anchor
        	});
        	var marker2_0 = new BMap.Marker(new BMap.Point(data.point_lng, data.point_lat ),{icon:myIcon0});
        	marker2_0.setPosition(data.point_lng-0.01, data.point_lat);//设置覆盖物位置
        	map.addOverlay(marker2_0);
        	
        	var myIconfx = new BMap.Icon(myIconpngfx, myIconpngfxBMapSize,{
        		anchor: myIconpngfxanchor
        	});
        	var markerfx = new BMap.Marker(new BMap.Point(data.point_lng, data.point_lat ),{icon:myIconfx});
        	markerfx.setPosition(data.point_lng, data.point_lat);//设置覆盖物位置
        	map.addOverlay(markerfx);
        	
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
function yjstrsubnumber2(str){
	var f = parseFloat( str );//JSON.stringify(str).substring(0,(JSON.stringify(str).indexOf(".")+3)) );    
    if (isNaN(f)) {    
        return;    
    }    
	return  Math.round(f*100)/100;	
}