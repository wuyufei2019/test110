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
		gasphysical(e);
	});
}

function gasphysical(e){
	top.layer.open({
	    type: 2,  
	    area: ['600px', '300px'],
	    title: '压缩气体物料爆炸事故计算',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/sghgjs/gasphysical/create',
	    btn: ['计算', '取消'],
	    yes: function(index, layero){
	    	 var body = top.layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; 
	         var inputForm = body.find('#gasphysical_form');
	         
	         body.find("#gasphysical_form_id_m7").val(e.point.lng);
	         body.find("#gasphysical_form_id_m8").val(e.point.lat);
	         
	         if(iframeWin.contentWindow.doSubmit()){
	        	//显示应急资源、疏散距离、处理技术
	     		var postdata={wzname: body.find('#gasphysical_form_id_m1_1').val(), lng:e.point.lng , lat:e.point.lat }
	     		show(postdata);
	     		
	     		//计算事故结果
	     		$.ajax({
	    			type:'post',
	    			url:ctx+"/sghgjs/gasphysical/create",
	    			data:inputForm.serialize(),
	    			dataType: 'json',
	    			async:false,
	    			success:function(data){
	                	sr=strsubnumber2(data.sw);
	                	zr=strsubnumber2(data.zs);
	                	qr=strsubnumber2(data.qs);
//	                	layer.open({title: '提示',offset: 'rb',content: "死亡："+sr+"米, 重伤："+ zr+"米, 轻伤："+qr+"米",shade: 0 ,time:3000});
	                	top.layer.close(index);
	    				map.clearOverlays();
	                	var now_point =  new BMap.Point(e.point.lng, e.point.lat );
	        	    	var marker = new BMap.Marker(now_point); //创建marker对象
	        			map.addOverlay(marker); //在地图中添加marker
	        			marker.setPosition(now_point);//设置覆盖物位置
	        			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	        			 var title="死亡："+sr+"米，重伤："+ zr+"米，轻伤："+qr+"米";
	         		    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
	                     label.setStyle({
	                         borderColor:"#808080",
	                         cursor:"pointer"
	                     });
	        			marker.setLabel(label);
	        		    map.addOverlay(marker);
	                	createCircle(e);
	                	
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
	var circle1 = new BMap.Circle(new BMap.Point(e.point.lng,e.point.lat),sr,{strokeColor:"red", strokeWeight:1.5,strokeOpacity:0.8,fillColor:"#FF0000",fillOpacity:0.4});      /*死亡半径*/   
	var circle2 = new BMap.Circle(new BMap.Point(e.point.lng,e.point.lat),zr,{strokeColor:"yellow", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#FFFF00",fillOpacity:0.2});    /*重伤半径*/ 
	var circle3 = new BMap.Circle(new BMap.Point(e.point.lng,e.point.lat),qr,{strokeColor:"#0092DC", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#BBEBFE",fillOpacity:0.2});    /*轻伤半径*/
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

//取小数点2位
function strsubnumber2(str){
    var f = parseFloat( JSON.stringify(str).substring(0,(JSON.stringify(str).indexOf(".")+3)) );    
    if (isNaN(f)) {    
        return;    
    }    
	return  Math.round(f*100)/100;	
}