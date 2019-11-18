function onloadyjyy(){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/hospitalmap",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
        success:function(data){
            var point = new BMap.Point( data.yjjclng, data.yjjclat );//(120.034376,31.946553);//定义一个中心点坐标
            map.centerAndZoom(point,13);//设定地图的中心点和坐标并将地图显示在地图容器中
	    	var markerc = new BMap.Marker(point); //创建marker对象
	        map.addOverlay(markerc); //在地图中添加marker
        	
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjyy.png",new BMap.Size(30,43));
        	var markerArr = eval(data.data);
        	
        	for(var i=0;i<markerArr.length;i++){
        		var json = markerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m11, json.m12 ),{icon:icon});
            	map.addOverlay(marker);
        	}
        	//3KM、5Km、10KM 圆虚线
        	create_3_5_10Circle(data,map);
		    
        }
	});
}

//创建Circle
function create_3_5_10Circle(data,vmap){
	var circle1 = new BMap.Circle(new BMap.Point(data.yjjclng,data.yjjclat),3000,{strokeStyle:"dashed",strokeColor:"#2A3AFF", strokeWeight:1.5,strokeOpacity:0.8,fillColor:"#0000FF",fillOpacity:0.01});      /*3KM*/   
	var circle2 = new BMap.Circle(new BMap.Point(data.yjjclng,data.yjjclat),5000,{strokeStyle:"dashed",strokeColor:"#0092DC", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#0000FF",fillOpacity:0.01});    /*5KM*/ 
	var circle3 = new BMap.Circle(new BMap.Point(data.yjjclng,data.yjjclat),10000,{strokeStyle:"dashed",strokeColor:"#62CFEB", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#0000FF",fillOpacity:0.01});    /*10KM*/
	
	var icon1 = new BMap.Icon(ctx+"/static/model/images/map/km_3.png",new BMap.Size(30,43));
	var new_point1 = new BMap.Point(data.yjjclng,eval(data.yjjclat-3/111));
	var new_circle1 = new BMap.Marker(new_point1,{icon:icon1});
	vmap.addOverlay(new_circle1);
	var icon2 = new BMap.Icon(ctx+"/static/model/images/map/km_5.png",new BMap.Size(30,43));
	var new_point2 = new BMap.Point(data.yjjclng,eval(data.yjjclat-5/111));
	var new_circle2 = new BMap.Marker(new_point2,{icon:icon2});
	vmap.addOverlay(new_circle2);
	var icon3 = new BMap.Icon(ctx+"/static/model/images/map/km_10.png",new BMap.Size(30,43));
	var new_point3 = new BMap.Point(data.yjjclng,eval(data.yjjclat-10/111));
	var new_circle3 = new BMap.Marker(new_point3,{icon:icon3});
	vmap.addOverlay(new_circle3);
	
	vmap.addOverlay(circle1);
	vmap.addOverlay(circle2);
	vmap.addOverlay(circle3);
}