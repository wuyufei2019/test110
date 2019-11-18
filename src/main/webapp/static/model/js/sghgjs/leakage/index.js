var count=0,sr,zr,qr;
$(function() {
	addMarker();//向地图中添加marker
	if(count==0){
		layer.msg("请选择事故发生点！",{time: 2000});
	}
});
 
//创建marker
function addMarker(){
	map.addEventListener("click", function(e){ 
		count=count+1;
		leakage(e);
	});
}
function leakage(e){
	top.layer.open({
	    type: 2,  
	    area: ['650px', '400px'],
	    title: '持续泄漏事故计算',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/sghgjs/leakage/create',
	    btn: ['计算', '取消'],
	    yes: function(index, layero){
	    	 var body = top.layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; 
	         var inputForm = body.find('#leakage_form');
	         
	         body.find("#leakage_form_id_m13").val(e.point.lng);
	         body.find("#leakage_form_id_m14").val(e.point.lat);
	         
	         if(iframeWin.contentWindow.doSubmit()){
	        	//显示应急资源、疏散距离、处理技术
	     		var postdata={wzname: body.find('#leakage_form_id_m1_1').val(), lng:e.point.lng , lat:e.point.lat }
	     		show(postdata);
	     		
	     		//计算事故结果
	     		$.ajax({
	    			type:'post',
	    			url:ctx+"/sghgjs/leakage/create",
	    			data:inputForm.serialize(),
	    			dataType: 'json',
	    			async:false,
	    			success:function(data){
	    				var swmax1=strsubnumber2(data.max1);
	                	var swmin1=strsubnumber2(data.min1);
	                	var zsmax2=strsubnumber2(data.max2);
	                	var zsmin2=strsubnumber2(data.min2);
	                	var qsmax3=strsubnumber2(data.max3);
	                	var qsmin3=strsubnumber2(data.min3);
	                	
	                	top.layer.close(index);
	                	
	    				map.clearOverlays();
	                	var now_point =  new BMap.Point(e.point.lng, e.point.lat );
	        	    	var marker = new BMap.Marker(now_point); //创建marker对象
	        			map.addOverlay(marker); //在地图中添加marker
	        			marker.setPosition(now_point);//设置覆盖物位置
	        			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	        			var title="边界浓度1：最大影响长度"+swmax1+"米、最大影响宽度"+swmin1+"米</br>边界浓度2：最大影响长度"+zsmax2+"米、最大影响宽度"+zsmin2+"米</br>边界浓度3：最大影响长度"+qsmax3+"米、最大影响宽度"+qsmin3+"米";
	        		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-100), position:now_point});
	                    label.setStyle({
	                        cursor:"pointer"
	                    });
	        			marker.setLabel(label);
	        		    map.addOverlay(marker);
	        		    string_split(data.lng1,data.lat1,data.lng2,data.lat2,data.lng3,data.lat3);
	                	
	                }
	    		});
	     	} 

		  },
		  cancel: function(index){ 
	       } 
	});
	
	
	 
}
 

//创建Circle
function createCircle(e){
	var circle1 = new BMap.Circle(new BMap.Point(e.point.lng,e.point.lat),sr,{strokeColor:"red", strokeWeight:2,strokeOpacity:1,fillColor:"#000CC",fillOpacity:0.2});      /*死亡半径*/   
	var circle2 = new BMap.Circle(new BMap.Point(e.point.lng,e.point.lat),zr,{strokeColor:"yellow", strokeWeight:2,strokeOpacity:0.7,fillColor:"#FF6600",fillOpacity:0.2});    /*重伤半径*/ 
	var circle3 = new BMap.Circle(new BMap.Point(e.point.lng,e.point.lat),qr,{strokeColor:"blue", strokeWeight:2,strokeOpacity:0.7,fillColor:"#ff0000",fillOpacity:0.2});    /*轻伤半径*/
	map.addOverlay(circle1);
	map.addOverlay(circle2);
	map.addOverlay(circle3);
}	

//创建InfoWindow
function createInfoWindow(){
    var iw = new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;color:red’>死亡半径：" + sr +"</p></br>" +
    		"<p style=’font-size:12px;lineheight:1.8em;color:yellow’>重伤半径：" + zr +"</p></br>" + 
    		"<p style=’font-size:12px;lineheight:1.8em;color:blue’>轻伤半径：" + qr +"</p>" );
    return iw;
}

function string_split(lngArray1,latArray1,lngArray2,latArray2,lngArray3,latArray3) 
{  

	arr_lng1=lngArray1.split("%"); //字符分割
	arr_lat1=latArray1.split("%"); //字符分割
	var PAS1 = new Array();
	var num1 = arr_lat1.length; 
	for(var i = 0 ; i < num1;i++){ PAS1[i] =  new BMap.Point(arr_lng1[i],arr_lat1[i]);};	
		var wound1 = new BMap.Polygon(PAS1,{strokeColor:"red", strokeWeight:1.5,strokeOpacity:0.8,fillColor:"#FF0000",fillOpacity:0.4}); 
		map.addOverlay(wound1); 

	arr_lng2=lngArray2.split("%"); //字符分割
	arr_lat2=latArray2.split("%"); //字符分割
	var PAS2 = new Array();
	var num2 = arr_lat2.length; 
	for(var i = 0 ; i < num2;i++){	PAS2[i] =  new BMap.Point(arr_lng2[i],arr_lat2[i]); };
	var wound2 = new BMap.Polygon(PAS2,{strokeColor:"#yellow", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#FFFF00",fillOpacity:0.2}); 
		map.addOverlay(wound2); 

	arr_lng3=lngArray3.split("%"); //字符分割
	arr_lat3=latArray3.split("%"); //字符分割
	var PAS3 = new Array();
	var num3 = arr_lat3.length; 		
	for(var i = 0 ; i < num3;i++){	PAS3[i] =  new BMap.Point(arr_lng3[i],arr_lat3[i]); };
	var wound3 = new BMap.Polygon(PAS3,{strokeColor:"#0092DC", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#BBEBFE",fillOpacity:0.2}); 
		map.addOverlay(wound3); 
};

//取小数点2位
function strsubnumber2(str){
    var f = parseFloat( JSON.stringify(str).substring(0,(JSON.stringify(str).indexOf(".")+3)) );    
    if (isNaN(f)) {    
        return;    
    }    
	return  Math.round(f*100)/100;	
}