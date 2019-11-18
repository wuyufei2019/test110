var qynameListDg;
var dg;
$(function(){
	//集团用户及其他非企业用户登录,显示企业列表
	if (type == '2') {
		qynameListDg=$('#qynameListDg').datagrid({  
			method: "get",
			url:ctx+'/fxgk/fxfb/qynamelist', 
		    fit : true,
			fitColumns : true,
			border : true,
			idField : 'id',
			animate : true, 
			rownumbers : true,
			singleSelect : true,
			scrollbarSize:0,
			striped:true,
		    columns:[[    
		        {field:'id',title:'id',hidden:true},    
		        {field:'m1',title:'企业名称',width:100}
		    ]],
		    enableHeaderClickMenu: false,
		    enableHeaderContextMenu: false,
		    enableRowContextMenu: false,
		    dataPlain: true,
		    onClickRow:function(rowIndex, rowData){
		    	var qyid =rowData.id;
		    	$.ajax({
		    		type: 'post',
		    		url: ctx+'/fxgk/fxfb/fxpmt/'+qyid,
		    		success: function(data){
		    			if (data == null || data == '') {//如果风险平面图为空
		    				if (qy_id == qyid) {
		    					layer.msg("请在安全基础档案-一企一档-基本信息中上传企业风险平面图",{time: 5000});
		    				} else {
		    					layer.msg("该企业还未上传企业风险平面图！",{time: 5000});
		    				}
		    			} else {
		    				var arr_fxpmt = data.split("||");
		    				$("#fxpmt").prop("src", arr_fxpmt[0]);
		    				$("#fxpmt").prop("alt", arr_fxpmt[1]);
		    			}
		    		}
		    	});
		    }
		});
		
		//默认加载该用户的风险平面图
		$.ajax({
    		type: 'post',
    		url: ctx+'/fxgk/fxfb/fxpmt/'+qy_id,
    		success: function(data){
    			if (data == null || data == '') {//如果风险平面图为空
					layer.msg("请在安全基础档案-一企一档-基本信息中上传企业风险平面图",{time: 5000});
    			} else {
    				var arr_fxpmt = data.split("||");
    				$("#fxpmt").prop("src", arr_fxpmt[0]);
    				$("#fxpmt").prop("alt", arr_fxpmt[1]);
    			}
    		}
    	});
	}
})
  

//创建多边形图
    function createPolygon(){
    	if(barrio.mappoint){
    	        var points=new Array();
    	        var arr1=barrio.mappoint.split("||");
    	        for (var i = 0; i < arr1.length; i++) {
    	        	var arr2 = arr1[i].split(",");
    	        	points[i] = new BMap.Point(arr2[0],arr2[1]);
    	        }
    			var polygon = new BMap.Polygon(points, {strokeColor:"red", strokeWeight:2, strokeOpacity:0.5,fillOpacity: 0.1});  //创建多边形
            	map.addOverlay(polygon);   //增加多边形
            	map.setViewport(polygon.getPath());
    	}
            	
    	 	
    }
    
    //创建marker
    function addMarker(){
    	map.clearOverlays();
        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];
            var p0 = json.pointx;
            var p1 = json.pointy;
            var point = new BMap.Point(p0,p1);
			var iconImg = createIcon(json.icon);
            var marker = new BMap.Marker(point,{icon:iconImg});
//			var iw = createInfoWindow(i);
//			var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
//			var label = new BMap.Label("",{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
//			marker.setLabel(label);
            map.addOverlay(marker);
//            label.setStyle({
//                        borderColor:"#808080",
//                        color:"#333",
//                        cursor:"pointer"
//            });
			
			(function(){
				var index = i;
				var _iw = createInfoWindow(i);
				var _marker = marker;
				_marker.addEventListener("click",function(){
					_marker.openInfoWindow(_iw);
			    });
			})()
        }
    }
    //创建InfoWindow
    function createInfoWindow(i){
        var json = markerArr[i];
        var html = [];
        var qyname = json.title;
    	html.push("<div style='width:280px;height:40px; cursor: pointer;' onclick='openDialogView(\"风险点辨识\",\""+ctx+"/fxgk/fxxx/index2?qyname=" + qyname + "\",\"100%\",\"100%\",\"\")' >");
    	html.push('<p style="font-size:14px;">名称：'+ json.title +'</br>地址：'+ json.address +'</br><img style="width: 14px;height: 14px;background-color: #FF5151;margin-right: 10px;" />'+ json.Rhong +'<img style="width: 14px;height: 14px;background-color: #FFA042;margin-right: 10px;margin-left: 25px;" />'+ json.Rcheng +'<img style="width: 14px;height: 14px;background-color: #F9F900;margin-right: 10px;margin-left: 25px;" />'+ json.Rhuang +'<img style="width: 14px;height: 14px;background-color: #2894FF;margin-right: 10px;margin-left: 25px;" />'+ json.Rlan +'</br></p>');   	
    	html.push('</div>');
    	return new window.BMap.InfoWindow(html.join(""), {offset: new BMap.Size(0, -10)});//window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;’>名称：" + json.title +"</br>地址：" + json.address + "</br></p>");
    }
    //创建一个Icon
    function createIcon(json){
//        var icon = new BMap.Icon(ctx+json, new BMap.Size(23,25),{imageOffset: new BMap.Size(-46,-21),infoWindowOffset:new BMap.Size(17,1),offset:new BMap.Size(9,25)});
//        return icon;
        var icon = new BMap.Icon(ctx+json,new BMap.Size(24,24));//, new BMap.Size(23,25),{imageOffset: new BMap.Size(-46,-21),infoWindowOffset:new BMap.Size(17,1),offset:new BMap.Size(9,25)});
        return icon;
    }
    


//搜索方法 
function search(keyword_name) {
	// 获取页面dom
	var keyword = document.getElementById(keyword_name).value;
	if(keyword&&keyword!=null){
		$.ajax({
	        type:"POST",
	        url:ctx+"/fxgk/fxyt/searchlist/"+keyword,
	        dataType: 'json', 
	        success:function(data){
	        	if(data.data!=undefined){
	        		markerArr = eval(data.data);
	        		addMarker();// 向地图中添加marker
	        		createPolygon();
	        	}
	        }
	    });
	}else{
		reset();
	}
}
// 重置返回所有结果
function reset() {
	$("#keyword").textbox('setValue','');
	$.ajax({
        type:"POST",
        url:ctx+"/fxgk/fxyt/maplist",
        dataType: 'json', 
        success:function(data){
        	if(data.data!=undefined){
        		markerArr = eval(data.data);
        		addMarker();// 向地图中添加marker
        		createPolygon();
        	}
        }
    });
}
