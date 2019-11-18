var yjjc_g_lng,yjjc_g_lat,yjjc_g_sr,yjjc_g_zr,yjjc_g_qr,yjjc_g_data,vmarkerArr,vmap;
var allOverlay=[]; //画图覆盖物保存
$(function() {
    window.point = point;
    onloadjylx();
});

//救援路线
function onloadjylx(){
	map.clearOverlays();
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/route",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
		async:false,
        success:function(data){
//        	var map = new BMap.Map("yjjc_consequence_jylx_map");//在百度地图容器中创建一个地图
        	yjjc_g_lng=data.point_lng;
        	yjjc_g_lat=data.point_lat;
        	
        	yjjc_g_data=data;
        	
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
//    			marker.setLabel(label);
    		    createCircle(data,map);
        	}else if(data.type=='7'){
    		    string_split(data.lng1,data.lat1,data.lng2,data.lat2,data.lng3,data.lat3,map);
        	}else if(data.type=='8'){
	        	var markerArr = eval(data.data);
	        	for(var i=0;i<markerArr.length;i++){
	        		circleDrow(markerArr[i].lng,markerArr[i].lat,markerArr[i].r,map);
	        	}
        	}
        	
        	var myIconpng0=ctx+"/static/model/images/map/ssfx0.png";
        	var myIconpng0BMapSize=new BMap.Size(202,183);
        	var myIconpng0anchor=new BMap.Size(101,200);
        	
        	var myIconpngfx=ctx+"/static/model/images/map/fxxnf.png";
        	var myIconpngfxBMapSize=new BMap.Size(92,92);
        	var myIconpngfxanchor=new BMap.Size(25,-46);
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
//        	marker2_0.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
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
function circleDrow(lng,lat,r,vmap){
	var circle = new BMap.Circle(new BMap.Point(lng,lat),r,{
        strokeColor: "#0092DC",
        strokeWeight: 1.5,
        fillColor: "#E2E8F1",
        fillOpacity: 0.5
    });
    vmap.addOverlay(circle);
}

//创建Polygon
function string_split(lngArray1,latArray1,lngArray2,latArray2,lngArray3,latArray3,vmap){  

	var arr_lng1=lngArray1.split("%"); //字符分割
	var arr_lat1=latArray1.split("%"); //字符分割
	var PAS1 = new Array();
	var num1 = arr_lat1.length; 
	for(var i = 0 ; i < num1;i++){ PAS1[i] =  new BMap.Point(arr_lng1[i],arr_lat1[i]);};	
		var wound1 = new BMap.Polygon(PAS1,{strokeColor:"red", strokeWeight:2,strokeOpacity:0.7,fillColor:"#FF0000",fillOpacity:0.2}); 
		vmap.addOverlay(wound1); 

	var arr_lng2=lngArray2.split("%"); //字符分割
	var arr_lat2=latArray2.split("%"); //字符分割
	var PAS2 = new Array();
	var num2 = arr_lat2.length; 
	for(var i = 0 ; i < num2;i++){	PAS2[i] =  new BMap.Point(arr_lng2[i],arr_lat2[i]); };
	var wound2 = new BMap.Polygon(PAS2,{strokeColor:"yellow", strokeWeight:2,strokeOpacity:0.7,fillColor:"#FFFF00",fillOpacity:0.2}); 
		vmap.addOverlay(wound2); 

	var arr_lng3=lngArray3.split("%"); //字符分割
	var arr_lat3=latArray3.split("%"); //字符分割
	var PAS3 = new Array();
	var num3 = arr_lat3.length; 		
	for(var i = 0 ; i < num3;i++){	PAS3[i] =  new BMap.Point(arr_lng3[i],arr_lat3[i]); };
	var wound3 = new BMap.Polygon(PAS3,{strokeColor:"#0092DC", strokeWeight:2,strokeOpacity:1,fillColor:"#BBEBFE",fillOpacity:0.2}); 
		vmap.addOverlay(wound3); 
};

//创建Circle
function createCircle(data,vmap){
	var circle1 = new BMap.Circle(new BMap.Point(data.point_lng,data.point_lat),yjjc_g_sr,{strokeColor:"red", strokeWeight:1.5,strokeOpacity:0.8,fillColor:"#FF0000",fillOpacity:0.4});      /*死亡半径*/   
	var circle2 = new BMap.Circle(new BMap.Point(data.point_lng,data.point_lat),yjjc_g_zr,{strokeColor:"yellow", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#FFFF00",fillOpacity:0.2});    /*重伤半径*/ 
	var circle3 = new BMap.Circle(new BMap.Point(data.point_lng,data.point_lat),yjjc_g_qr,{strokeColor:"#0092DC", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#BBEBFE",fillOpacity:0.2});    /*轻伤半径*/
	vmap.addOverlay(circle1);
	vmap.addOverlay(circle2);
	vmap.addOverlay(circle3);
}

var marker1 = "";//创建一个空的marker

var dg;
var d;
function onloadyjdw(){
	dg=$('#yjjc_consequence_yjdw_dg').datagrid({    
		method: "get",
	    url:ctx+'/ead/yjjc/conseque/resteam', 
	    fit : true,
		border : false,
		fitColumns : true,
		queryParams: {consequenceid:consequenceid},
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 20,
		pageList : [ 20, 50, 100, 150, 200 ],
		scrollbarSize:0,
		singleSelect:true,
	    columns:[[    
	        {field:'ID',title:'id',hidden:true,width:50,align:'center'},    
			{field:'m1',title:'队伍名称',width:100,align:'center'},    
			{field:'m2',title:'队伍类型',width:100,align:'center',
				formatter: function(value,row,index){
					if (value=='1'){
						return '消防';
					} else if (value=='2'){
						return '公安';
					} else if (value=='3'){
						return '交通';
					} else if (value=='4'){
						return '燃气';
					} else if (value=='5'){
						return '危化品';
					} else if (value=='6'){
						return '电力';
					} else if (value=='7'){
						return '供水';
					} else if (value=='8'){
						return '排水';
					} else if (value=='9'){
						return '医疗';
					} else if (value=='10'){
						return '环境';
					} else if (value=='11'){
						return '地震';
					} else if (value=='12'){
						return '安监';
					} else if (value=='13'){
						return '气象';
					} else if (value=='14'){
						return '搜救';
					} else if (value=='15'){
						return '其他';
					} else {
						return '';
					}
				}
			},
			{field:'m4',title:'主要负责人',width:100,align:'center'},
			{field:'m5',title:'应急电话',width:100,align:'center'}
	    ]],
	    onClickRow: function (index, row){
	    	newMark();
	    	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjdw.png",new BMap.Size(30,37));
	    	var new_point = new BMap.Point(vmarkerArr[index].m12,vmarkerArr[index].m13);
	    	var new_marker = new BMap.Marker(new_point,{icon:icon});
	    	allOverlay.push(new_marker);//添加覆盖物
	    	vmap.removeOverlay(new_marker);
	    	vmap.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	vmap.panTo(new_point);                //转到该点位置 
	    	
	    	var _iw = yjdwcreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjdw_tb'
		});
}

function newMark(){
	map.removeOverlay(allOverlay[allOverlay.length-1]);
 
}



function onloadyjdwmap(data){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/resteammap",
		data:data,
		dataType: 'json', 
		async:false,
        success:function(data){
        	vmap = new BMap.Map("yjjc_consequence_yjdw_image");
            var point = new BMap.Point(yjjc_g_data.point_lng,yjjc_g_data.point_lat);//yjjc_g_lng,yjjc_g_lat);//定义一个中心点坐标
            vmap.centerAndZoom(point,16);//设定地图的中心点和坐标并将地图显示在地图容器中
            vmap.addControl(new BMap.NavigationControl());
//            map.setCurrentCity("常州");          // 设置地图显示的城市 此项是必须设置的
            vmap.addControl(new BMap.MapTypeControl());   //添加地图类型控件
            vmap.enableScrollWheelZoom();//启用地图滚轮放大缩小
            vmap.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
            vmap.enableKeyboard();//启用键盘上下左右键移动地图
	    	var markerc = new BMap.Marker(point); //创建marker对象
	        vmap.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

	        //向地图中添加比例尺控件
        	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_TOP_LEFT});
        	vmap.addControl(ctrl_sca);
        	
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjdw.png",new BMap.Size(30,37));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m12, json.m13 ),{icon:icon});
            	allOverlay.push(marker);
            	vmap.addOverlay(marker);
            	
            	(function(){
    				var index = i;
    				var _iw = yjdwcreateInfoWindow(i);
    				var _marker = marker;
    				_marker.addEventListener("click",function(){
    					_marker.openInfoWindow(_iw);
    			    });
    				
    			})();
        	}
        	
        	allOverlay.push(marker1);//多建一个marker，用于删除
        	//3KM、5Km、10KM 圆虚线
        	create_3_5_10Circle(yjjc_g_data,vmap);
        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(yjjc_g_data,vmap);
        }
	});
}

function yjdw_cx(){
	var obj=$("#yjjc_consequence_yjdw_searchFrom").serializeObject();
	obj.consequenceid=consequenceid;
	dg.datagrid('load',obj); 
	onloadyjdwmap(obj);
}

function yjdw_cx_clear(){
	$("#yjjc_consequence_yjdw_searchFrom").form("clear");
	yjdw_cx();
}

function onloadyjzb(){
	dg=$('#yjjc_consequence_yjzb_dg').datagrid({    
		method: "get",
	    url:ctx+'/ead/yjjc/conseque/resinstrument', 
	    fit : true,
		border : false,
		fitColumns : true,
		queryParams: {consequenceid:consequenceid},
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 20,
		pageList : [ 20, 50, 100, 150, 200 ],
		scrollbarSize:0,
		singleSelect:true,
	    columns:[[    
	        {field:'ID',title:'id',hidden:true,width:50,align:'center'},
//			{field:'qynm',title:'企业名称',width:100,align:'center'},    
	        {field:'m1',title:'装备类别',width:100},
	        {field:'m2',title:'装备名称',width:100},
			{field:'m3',title:'规格型号',width:100,align:'center'},
			{field:'m4',title:'数量',width:60,align:'center'},
			{field:'m6',title:'自储数量',width:60,align:'center'},
			{field:'m7',title:'代储数量',width:60,align:'center'},
			{field:'m10',title:'联系人',width:100,align:'center'},
			{field:'m11',title:'应急电话',width:100,align:'center'},
			{field:'m9',title:'地址',width:300}
	    ]],
	    onClickRow: function (index, row){
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjzb.png",new BMap.Size(30,37));
	    	var new_point = new BMap.Point(vmarkerArr[index].m14,vmarkerArr[index].m15);
	    	var new_marker = new BMap.Marker(new_point,{icon:icon});
	    	newMark();
	    	allOverlay.push(new_marker);//添加覆盖物
	    	vmap.removeOverlay(new_marker);
	    	vmap.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	vmap.panTo(new_point);                //转到该点位置 
	    	var _iw = yjzbcreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjzb_tb'
		});
}

function onloadyjzbmap(data){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/resinstrumentmap",
		data:data,
		dataType: 'json', 
		async:false,
        success:function(data){
        	vmap = new BMap.Map("yjjc_consequence_yjzb_image");
            var point = new BMap.Point(yjjc_g_lng,yjjc_g_lat);//定义一个中心点坐标
            vmap.centerAndZoom(point,16);//设定地图的中心点和坐标并将地图显示在地图容器中
            vmap.addControl(new BMap.NavigationControl());
//            map.setCurrentCity("常州");          // 设置地图显示的城市 此项是必须设置的
            vmap.addControl(new BMap.MapTypeControl());   //添加地图类型控件
            vmap.enableScrollWheelZoom();//启用地图滚轮放大缩小
            vmap.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
            vmap.enableKeyboard();//启用键盘上下左右键移动地图
	    	var markerc = new BMap.Marker(point); //创建marker对象
	    	vmap.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            //向地图中添加比例尺控件
        	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_TOP_LEFT});
        	vmap.addControl(ctrl_sca);
        	
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjzb.png",new BMap.Size(30,37));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m14, json.m15 ),{icon:icon});
            	vmap.addOverlay(marker);
            	
            	(function(){
    				var index = i;
    				var _iw = yjzbcreateInfoWindow(i);
    				var _marker = marker;
    				_marker.addEventListener("click",function(){
    					_marker.openInfoWindow(_iw);
    			    });
    				
    			})();
        	}
        	allOverlay.push(marker1);//多建一个marker，用于删除
        	//3KM、5Km、10KM 圆虚线
        	create_3_5_10Circle(yjjc_g_data,vmap);
        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(yjjc_g_data,vmap);
        }
	});
}

function yjzb_cx(){
	var obj=$("#yjjc_consequence_yjzb_searchFrom").serializeObject();
	obj.consequenceid=consequenceid;
	dg.datagrid('load',obj); 
	onloadyjzbmap(obj);
}

function yjzb_cx_clear(){
	$("#yjjc_consequence_yjzb_searchFrom").form("clear");
	yjzb_cx();
}

function onloadyjwz(){
	dg=$('#yjjc_consequence_yjwz_dg').datagrid({    
		method: "get",
	    url:ctx+'/ead/yjjc/conseque/mate', 
	    fit : true,
		border : false,
		fitColumns : true,
		queryParams: {consequenceid:consequenceid},
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 20,
		pageList : [ 20, 50, 100, 150, 200 ],
		scrollbarSize:0,
		singleSelect:true,
	    columns:[[    
	        {field:'ID',title:'id',hidden:true,width:50,align:'center'},
//			{field:'qynm',title:'企业名称',width:100,align:'center'},
	        {field:'m1',title:'物资类别',width:50,align:'center' },
			{field:'m2',title:'物资名称',width:50,align:'center'},
			{field:'m3',title:'型号',width:50,align:'center'},
			{field:'m4',title:'数量',width:40,align:'center'},
			{field:'m6',title:'自储数量',width:40,align:'center'},
			{field:'m7',title:'代储数量',width:40,align:'center'},
			{field:'m8',title:'储存单位',width:80},
			{field:'m9',title:'储存地址',width:100},
			{field:'m12',title:'应对事故类型',width:100,align:'center',
				formatter: function(value,row,index){
					var arr = value.split(",");
					var result="";
					for(var i=0;i<arr.length;i++){
						if(i>0){
							result=result+',';
						}
						if (arr[i]=='1'){
							result=result+'物体打击';
						}else if (arr[i]=='2'){
							result=result+'车辆伤害';
						}else if (arr[i]=='3'){
							result=result+'机械伤害';
						}else if (arr[i]=='4'){
							result=result+'起重伤害';
						}else if (arr[i]=='5'){
							result=result+'触电';
						}else if (arr[i]=='6'){
							result=result+'淹溺';
						}else if (arr[i]=='7'){
							result=result+'灼烫';
						}else if (arr[i]=='8'){
							result=result+'火灾';
						}else if (arr[i]=='9'){
							result=result+'高处坠落';
						}else if (arr[i]=='10'){
							result=result+'坍塌';
						}else if (arr[i]=='11'){
							result=result+'冒顶片帮';
						}else if (arr[i]=='12'){
							result=result+'透水';
						}else if (arr[i]=='13'){
							result=result+'放炮';
						}else if (arr[i]=='14'){
							result=result+'火药爆炸';
						}else if (arr[i]=='15'){
							result=result+'瓦斯爆炸';
						}else if (arr[i]=='16'){
							result=result+'锅炉爆炸';
						}else if (arr[i]=='17'){
							result=result+'容器爆炸';
						}else if (arr[i]=='18'){
							result=result+'其它爆炸';
						}else if (arr[i]=='19'){
							result=result+'中毒和窒息';
						}else if (arr[i]=='20'){
							result=result+'其它伤害';
						}
					}
					return result;
				}
			}
	    ]],
	    onClickRow: function (index, row){
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjwz.png",new BMap.Size(30,37));
	    	var new_point = new BMap.Point(vmarkerArr[index].m14,vmarkerArr[index].m15);
	    	var new_marker = new BMap.Marker(new_point,{icon:icon});
	    	newMark();
	    	allOverlay.push(new_marker);//添加覆盖物
	    	vmap.removeOverlay(new_marker);
	    	vmap.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	vmap.panTo(new_point);                //转到该点位置 
	    	var _iw = yjwzcreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjwz_tb'
		});
}

function onloadyjwzmap(data){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/matemap",
		data:data,
		dataType: 'json', 
		async:false,
        success:function(data){
        	vmap = new BMap.Map("yjjc_consequence_yjwz_image");
            var point = new BMap.Point(yjjc_g_lng,yjjc_g_lat);//定义一个中心点坐标
            vmap.centerAndZoom(point,16);//设定地图的中心点和坐标并将地图显示在地图容器中
            vmap.addControl(new BMap.NavigationControl());
//            map.setCurrentCity("常州");          // 设置地图显示的城市 此项是必须设置的
            vmap.addControl(new BMap.MapTypeControl());   //添加地图类型控件
            vmap.enableScrollWheelZoom();//启用地图滚轮放大缩小
            vmap.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
            vmap.enableKeyboard();//启用键盘上下左右键移动地图
	    	var markerc = new BMap.Marker(point); //创建marker对象
	        vmap.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            //向地图中添加比例尺控件
        	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_TOP_LEFT});
        	vmap.addControl(ctrl_sca);
        	
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjwz.png",new BMap.Size(30,37));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m14, json.m15 ),{icon:icon});
            	vmap.addOverlay(marker);
            	
            	(function(){
    				var index = i;
    				var _iw = yjwzcreateInfoWindow(i);
    				var _marker = marker;
    				_marker.addEventListener("click",function(){
    					_marker.openInfoWindow(_iw);
    			    });
    				
    			})();
        	}
        	allOverlay.push(marker1);//多建一个marker，用于删除
        	//3KM、5Km、10KM 圆虚线
        	create_3_5_10Circle(yjjc_g_data,vmap);
        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(yjjc_g_data,vmap);
        }
	});
}

function yjwz_cx(){
	var obj=$("#yjjc_consequence_yjwz_searchFrom").serializeObject();
	obj.consequenceid=consequenceid;
	dg.datagrid('load',obj); 
	onloadyjwzmap(obj);
}

function yjwz_cx_clear(){
	$("#yjjc_consequence_yjwz_searchFrom").form("clear");
	yjwz_cx();
}

function onloadbncs(){
	dg=$('#yjjc_consequence_bncs_dg').datagrid({    
		method: "get",
	    url:ctx+'/ead/yjjc/conseque/resplace', 
	    fit : true,
		border : false,
		fitColumns : true,
		queryParams: {consequenceid:consequenceid},
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 20,
		pageList : [ 20, 50, 100, 150, 200 ],
		scrollbarSize:0,
		singleSelect:true,
	    columns:[[    
	        {field:'ID',title:'id',hidden:true,width:50,align:'center'},
	        {field:'m1',title:'场所名称',width:100 },    
	        {field:'m2',title:'场所类型',width:100,align:'center' },
	        {field:'m3',title:'详细地址',width:150},
	        {field:'m4',title:'可容纳人数',width:50,align:'center'},
	        {field:'m5',title:'联系人',width:50,align:'center'},
	        {field:'m6',title:'联系人电话',width:100,align:'center'}
	    ]],
	    onClickRow: function (index, row){
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_bncs.png",new BMap.Size(30,37));
	    	var new_point = new BMap.Point(vmarkerArr[index].m12,vmarkerArr[index].m13);
	    	var new_marker = new BMap.Marker(new_point,{icon:icon});
	    	newMark();
	    	allOverlay.push(new_marker);//添加覆盖物
	    	vmap.removeOverlay(new_marker);
	    	vmap.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	vmap.panTo(new_point);                //转到该点位置 
	    	var _iw = bncscreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_bncs_tb'
		});
}

function onloadbncsmap(data){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/resplacemap",
		data:data,
		dataType: 'json', 
		async:false,
        success:function(data){
        	vmap = new BMap.Map("yjjc_consequence_bncs_image");
            var point = new BMap.Point(yjjc_g_lng,yjjc_g_lat);//定义一个中心点坐标
            vmap.centerAndZoom(point,16);//设定地图的中心点和坐标并将地图显示在地图容器中
            vmap.addControl(new BMap.NavigationControl());
//            map.setCurrentCity("常州");          // 设置地图显示的城市 此项是必须设置的
            vmap.addControl(new BMap.MapTypeControl());   //添加地图类型控件
            vmap.enableScrollWheelZoom();//启用地图滚轮放大缩小
            vmap.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
            vmap.enableKeyboard();//启用键盘上下左右键移动地图
	    	var markerc = new BMap.Marker(point); //创建marker对象
	        vmap.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画//

	        //向地图中添加比例尺控件
        	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_TOP_LEFT});
        	vmap.addControl(ctrl_sca);
        	
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_bncs.png",new BMap.Size(30,37));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m12, json.m13 ),{icon:icon});
            	vmap.addOverlay(marker);
            	
            	(function(){
    				var index = i;
    				var _iw = bncscreateInfoWindow(i);
    				var _marker = marker;
    				_marker.addEventListener("click",function(){
    					_marker.openInfoWindow(_iw);
    			    });
    				
    			})();
        	}
        	allOverlay.push(marker1);//多建一个marker，用于删除
        	//3KM、5Km、10KM 圆虚线
        	create_3_5_10Circle(yjjc_g_data,vmap);
        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(yjjc_g_data,vmap);
        }
	});
}

function bncs_cx(){
	var obj=$("#yjjc_consequence_bncs_searchFrom").serializeObject();
	obj.consequenceid=consequenceid;
	dg.datagrid('load',obj); 
	onloadbncsmap(obj);
}

function bncs_cx_clear(){
	$("#yjjc_consequence_bncs_searchFrom").form("clear");
	bncs_cx();
}

function onloadyjzj(){
	dg=$('#yjjc_consequence_yjzj_dg').datagrid({    
		method: "get",
	    url:ctx+'/ead/yjjc/conseque/resexpert', 
	    fit : true,
		border : false,
		fitColumns : true,
		queryParams: {consequenceid:consequenceid},
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 20,
		pageList : [ 20, 50, 100, 150, 200 ],
		scrollbarSize:0,
		singleSelect:true,
	    columns:[[    
	        {field:'ID',title:'id',hidden:true,width:50,align:'center'},
//			{field:'qynm',title:'企业名称',width:100,align:'center'},    
	        {field:'m1',title:'姓名',width:100,align:'center'},    
			{field:'m7',title:'单位',width:100,align:'center',},
			{field:'m11',title:'联系电话',width:50,align:'center'},
			{field:'m12',title:'手机',width:50,align:'center'},
			{field:'m15',title:'专业',width:100,align:'center'}
	    ]],
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['m7']);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjzj_tb'
		});
}


function yjzj_cx(){
	var obj=$("#yjjc_consequence_yjzj_searchFrom").serializeObject();
	obj.consequenceid=consequenceid;
	dg.datagrid('load',obj); 
}

function yjzj_cx_clear(){
	$("#yjjc_consequence_yjzj_searchFrom").form("clear");
	yjzj_cx();
}

function onloadyjyy(){
	dg=$('#yjjc_consequence_yjyy_dg').datagrid({    
		method: "get",
	    url:ctx+'/ead/yjjc/conseque/hospital', 
	    fit : true,
		border : false,
		fitColumns : true,
		queryParams: {consequenceid:consequenceid},
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 20,
		pageList : [ 20, 50, 100, 150, 200 ],
		scrollbarSize:0,
		singleSelect:true,
	    columns:[[    
	        {field:'ID',title:'id',hidden:true,width:50,align:'center'},
	        {field:'m1',title:'医院名称',width:100},    
			{field:'m2',title:'等级',width:100,align:'center',
				formatter: function(value,row,index){
					if (value=='1'){
						return '一级甲等';
					}else if (value=='2'){
						return '一级乙等';
					}else if (value=='3'){
						return '一级丙等';
					}else if (value=='4'){
						return '二级甲等';
					}else if (value=='5'){
						return '二级乙等';
					}else if (value=='6'){
						return '二级丙等';
					}else if (value=='7'){
						return '三级特等';
					}else if (value=='8'){
						return '三级甲等';
					}else if (value=='9'){
						return '三级乙等';
					}else if (value=='10'){
						return '三级丙等';
					}else {
						return '';
					}
				}
			},
			{field:'m3',title:'地址',width:100},
			{field:'m4',title:'主要负责人',width:100,align:'center'},
			{field:'m5',title:'主要负责人电话',width:100,align:'center'},
			{field:'m6',title:'联系人',width:100,align:'center'},
			{field:'m7',title:'联系人电话',width:100,align:'center'}
	    ]],
	    onClickRow: function (index, row){
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjyy.png",new BMap.Size(30,43));
	    	var new_point = new BMap.Point(vmarkerArr[index].m11,vmarkerArr[index].m12);
	    	var new_marker = new BMap.Marker(new_point,{icon:icon});
	    	newMark();
	    	allOverlay.push(new_marker);//添加覆盖物
	    	vmap.removeOverlay(new_marker);
	    	vmap.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	vmap.panTo(new_point);                //转到该点位置 
	    	var _iw = yjyycreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjyy_tb'
		});
}

function onloadyjyymap(data){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/hospitalmap",
		data:data,
		dataType: 'json', 
		async:false,
        success:function(data){
        	
        	vmap = new BMap.Map("yjjc_consequence_yjyy_image");
            var point = new BMap.Point(yjjc_g_lng,yjjc_g_lat);//定义一个中心点坐标
            vmap.centerAndZoom(point,16);//设定地图的中心点和坐标并将地图显示在地图容器中
            vmap.addControl(new BMap.NavigationControl());
            vmap.setCurrentCity("常州");          // 设置地图显示的城市 此项是必须设置的
            vmap.addControl(new BMap.MapTypeControl());   //添加地图类型控件
            vmap.enableScrollWheelZoom();//启用地图滚轮放大缩小
            vmap.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
            vmap.enableKeyboard();//启用键盘上下左右键移动地图
	    	var markerc = new BMap.Marker(point); //创建marker对象
	        vmap.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			//向地图中添加比例尺控件
        	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_TOP_LEFT});
        	vmap.addControl(ctrl_sca);
        	
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjyy.png",new BMap.Size(30,43));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m11, json.m12 ),{icon:icon});
            	vmap.addOverlay(marker);
            	
            	(function(){
    				var index = i;
    				var _iw = yjyycreateInfoWindow(i);
    				var _marker = marker;
    				_marker.addEventListener("click",function(){
    					_marker.openInfoWindow(_iw);
    			    });
    				
    			})();
        	}
        	allOverlay.push(marker1);//多建一个marker，用于删除
        	//3KM、5Km、10KM 圆虚线
        	create_3_5_10Circle(yjjc_g_data,vmap);
        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(yjjc_g_data,vmap);
        }
	});
}

function yjyy_cx(){
	var obj=$("#yjjc_consequence_yjyy_searchFrom").serializeObject();
	obj.consequenceid=consequenceid;
	dg.datagrid('load',obj); 
	onloadyjyymap(obj);
}

function yjyy_cx_clear(){
	$("#yjjc_consequence_yjyy_searchFrom").form("clear");
	yjyy_cx();
}

function onloadyjczjsData(){
	$.ajax({
	      method : 'get',
	      url : ctx+'/ead/yjjc/conseque/disptechnology',
	      dataType : 'json',
	      data:{consequenceid:consequenceid},
	      success : function(data) {
	    	  var row=eval(data.rows);
	    	  var mm="";
	    	  if(row[0].m1!=undefined){
	    		  $('#yjjc_consequence_yjczjs_dg_t1').html(row[0].m1);
	    	  }
	    	  if(row[0].m10!=undefined){
	    		  mm=mm+"健康危害:&nbsp;&nbsp;&nbsp;&nbsp;"+row[0].m10+"<br/>";
	    	  }
	    	  if(row[0].m11!=undefined){
	    		  mm=mm+"环境危害:&nbsp;&nbsp;&nbsp;&nbsp;"+row[0].m11+"<br/>";
	    	  }
	    	  if(row[0].m12!=undefined){
	    		  mm=mm+"爆燃危害:&nbsp;&nbsp;&nbsp;&nbsp;"+row[0].m12+"<br/>";
	    	  }
	    	  $('#yjjc_consequence_yjczjs_dg_t2').html(mm);
	    	  if(row[0].m1!=undefined){
	    		  $('#yjjc_consequence_yjczjs_dg_t3').html(row[0].m20);
	    	  }
	      }
	});
}

function onloadyjczjs(){
	dg=$('#yjjc_consequence_yjczjs_dg').datagrid({    
		method: "get",
	    url:ctx+'/ead/yjjc/conseque/disptechnology', 
	    fit : true,
		border : false,
		fitColumns : true,
		queryParams: {consequenceid:consequenceid},
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 20,
		pageList : [ 20, 50, 100, 150, 200 ],
		scrollbarSize:0,
		singleSelect:true,
	    columns:[[    
	        {field:'ID',title:'id',hidden:true,width:50,align:'center'},
	        {field:'m1',title:'化学品名称',width:50,align:'center'},    
	        {field:'m',title:'主要危险性',width:100,align:'center',
				formatter: function(value,row,index){
					var ret="";
					if(row.m10!=""&&row.m10!=null) ret=ret+"健康危害:"+row.m10+"&nbsp;&nbsp;&nbsp;&nbsp;";
					if(row.m11!=""&&row.m11!=null) ret=ret+"环境危害:"+row.m11+"&nbsp;&nbsp;&nbsp;&nbsp;";
					if(row.m12!=""&&row.m12!=null) ret=ret+"爆燃危害:"+row.m12+"&nbsp;&nbsp;&nbsp;&nbsp;";
					return ret;
				}
			},
	        {field:'m20',title:'事故应急处置技术',width:100,align:'center'}
	    ]],
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['m7']);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjczjs_tb'
		});
}

function yjczjs_cx(){
	var obj=$("#yjjc_consequence_yjczjs_searchFrom").serializeObject();
	obj.consequenceid=consequenceid;
	dg.datagrid('load',obj); 
}

function yjczjs_cx_clear(){
	$("#yjjc_consequence_yjczjs_searchFrom").form("clear");
	yjczjs_cx();
}

function onloadyjtxl(){
	dg=$('#yjjc_consequence_yjtxl_dg').datagrid({    
		method: "get",
	    url:ctx+'/ead/yjjc/conseque/contacts', 
	    fit : true,
		border : false,
		fitColumns : true,
		queryParams: {consequenceid:consequenceid},
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 20,
		pageList : [ 20, 50, 100, 150, 200 ],
		scrollbarSize:0,
		singleSelect:true,
	    columns:[[    
	        {field:'ID',title:'id',hidden:true,width:50,align:'center'},
//	        {field:'qynm',title:'企业名称',width:100,align:'center'},    
			{field:'m1',title:'姓名',width:100,align:'center'},    
			{field:'m2',title:'单位',width:100,align:'center'},
			{field:'m3',title:'职务',width:100,align:'center'},
			{field:'m4',title:'电话',width:100,align:'center'},
			{field:'m5',title:'手机',width:100,align:'center'}
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjtxl_tb'
		});
}

function yjtxl_cx(){
	var obj=$("#yjjc_consequence_yjtxl_searchFrom").serializeObject();
	obj.consequenceid=consequenceid;
	dg.datagrid('load',obj); 
}

function yjtxl_cx_clear(){
	$("#yjjc_consequence_yjtxl_searchFrom").form("clear");
	yjtxl_cx();
}


function yjfzword_word(){
	var index;
	$.ajax({
		type:"post",
        url:ctx+'/ead/yjjc/conseque/word',		
        data:{"consequenceid":consequenceid},
		dataType: 'json', 
		timeout:30000,
		beforeSend:function(){
			index = layer.load(1, { shade: [0.2,'#fff']  });//弹出加载层
		}, 
		success: function(data){
			layer.close(index);//关闭加载层
			
			if(data!=undefined){
				var dat=eval(data);
				layer.open({title: '提示',offset: 'rb',content: "生成成功！",shade: 0 ,time:3000});
				window.open(ctx+dat.data);
			}
		},
        error:function(){
        	layer.close(index);//关闭加载层
        } 
	});
}

//取小数点2位
function yjstrsubnumber2(str){
    var f = parseFloat( str );//JSON.stringify(str).substring(0,(JSON.stringify(str).indexOf(".")+3)) );    
    if (isNaN(f)) {    
        return;    
    }    
	return  Math.round(f*100)/100;	
}

//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
function createCirclePolygonDrow(data,vmap){
	if(data.type=='1'
		||data.type=='2'
		||data.type=='3'
		||data.type=='4'
		||data.type=='5'
		||data.type=='6'){
	    createCircle(data,vmap);
	}else if(data.type=='7'){
	    string_split(data.lng1,data.lat1,data.lng2,data.lat2,data.lng3,data.lat3,vmap);
	}else if(data.type=='8'){
    	var markerArr = eval(data.data);
    	for(var i=0;i<markerArr.length;i++){
    		circleDrow(markerArr[i].lng,markerArr[i].lat,markerArr[i].r,vmap);
    	}
	}
}

//创建Circle
function create_3_5_10Circle(data,vmap){
	var circle1 = new BMap.Circle(new BMap.Point(data.point_lng,data.point_lat),3000,{strokeStyle:"dashed",strokeColor:"#2A3AFF", strokeWeight:1.5,strokeOpacity:0.8,fillColor:"#0000FF",fillOpacity:0.01});      /*3KM*/   
	var circle2 = new BMap.Circle(new BMap.Point(data.point_lng,data.point_lat),5000,{strokeStyle:"dashed",strokeColor:"#0092DC", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#0000FF",fillOpacity:0.01});    /*5KM*/ 
	var circle3 = new BMap.Circle(new BMap.Point(data.point_lng,data.point_lat),10000,{strokeStyle:"dashed",strokeColor:"#62CFEB", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#0000FF",fillOpacity:0.01});    /*10KM*/

	
	//alert(eval(data.point_lat-3/111));
	
	var icon1 = new BMap.Icon(ctx+"/static/model/images/map/km_3.png",new BMap.Size(30,43));
	var new_point1 = new BMap.Point(data.point_lng,eval(data.point_lat-3/111));
	var new_circle1 = new BMap.Marker(new_point1,{icon:icon1});
	vmap.addOverlay(new_circle1);
	var icon2 = new BMap.Icon(ctx+"/static/model/images/map/km_5.png",new BMap.Size(30,43));
	var new_point2 = new BMap.Point(data.point_lng,eval(data.point_lat-5/111));
	var new_circle2 = new BMap.Marker(new_point2,{icon:icon2});
	vmap.addOverlay(new_circle2);
	var icon3 = new BMap.Icon(ctx+"/static/model/images/map/km_10.png",new BMap.Size(30,43));
	var new_point3 = new BMap.Point(data.point_lng,eval(data.point_lat-10/111));
	var new_circle3 = new BMap.Marker(new_point3,{icon:icon3});
	vmap.addOverlay(new_circle3);
	vmap.addOverlay(circle1);
	vmap.addOverlay(circle2);
	vmap.addOverlay(circle3);
}


//创建应急队伍InfoWindow
function yjdwcreateInfoWindow(i){
    var json = vmarkerArr[i];
    var m2="";
    var html = [];
    switch(json.m2){
    	case '1': m2="消防"; break;
    	case '2': m2="公安"; break;
    	case '3': m2="交通"; break;
    	case '4': m2="燃气"; break;
    	case '5': m2="危化品"; break;
    	case '6': m2="电力"; break;
    	case '7': m2="供水"; break;
    	case '8': m2="排水"; break;
    	case '9': m2="医疗"; break;
    	case '10': m2="环境"; break;
    	case '11': m2="地震"; break;
    	case '12': m2="安监"; break;
    	case '13': m2="气象"; break;
    	case '14': m2="搜救"; break;
    	case '15': m2="其他"; break;
		default: m2=""; break;
    }
    
	html.push('<div style="width:280px;height:40px;" ');
	html.push('<p style="font-size:14px;">队伍名称：'+ json.m1 +'</br>队伍类型：'+m2+'</br>主要负责人：'+json.m4+'</br>应急电话：'+json.m5+'</p>');
	html.push('</div>');
	return new window.BMap.InfoWindow(html.join(""), {offset: new BMap.Size(0, -10)});
}

//创建应急装备InfoWindow
function yjzbcreateInfoWindow(i){
    var json = vmarkerArr[i];
    var html = [];
	html.push('<div style="width:280px;height:40px;" ');
	html.push('<p style="font-size:14px;">装备名称：'+ json.m2 +'</br>规格型号：'+json.m3+'</br>数量：'+json.m4+'</br>自储数量：'+json.m6+'</br>代储数量：'+json.m7+'</br>联系人：'+json.m10+'</br>应急电话：'+json.m11+'</br>地址：'+json.m9+'</br></p>');
	html.push('</div>');
	return new window.BMap.InfoWindow(html.join(""), {offset: new BMap.Size(0, -10)});
}

//创建应急物资InfoWindow
function yjwzcreateInfoWindow(i){
    var json = vmarkerArr[i];
    var m1="";
    var m12="";
    var html = [];
    switch(json.m1){
    	case '1': m1="防护用品"; break;
    	case '2': m1="生命救助"; break;
    	case '3': m1="生命支持"; break;
    	case '4': m1="临时食宿"; break;
    	case '5': m1="通讯广播"; break;
    	case '6': m1="污染清理"; break;
    	case '7': m1="动力燃料"; break;
    	case '8': m1="器材工具"; break;
    	case '9': m1="其他"; break;
		default: m1=""; break;
    }
    
    var arr = (json.m12).split(",");
	for(var i=0;i<arr.length;i++){
		if(i>0){
			m12=m12+',';
		}
    
	    switch(arr[i]){
			case '1': m12=m12+'物体打击'; break;
			case '2': m12=m12+'车辆伤害'; break;
			case '3': m12=m12+'机械伤害'; break;
			case '4': m12=m12+'起重伤害'; break;
			case '5': m12=m12+'触电'; break;
			case '6': m12=m12+'淹溺'; break;
			case '7': m12=m12+'灼烫'; break;
			case '8': m12=m12+'火灾'; break;
			case '9': m12=m12+'高处坠落'; break;
			case '10': m12=m12+'坍塌'; break;
			case '11': m12=m12+'冒顶片帮'; break;
			case '12': m12=m12+'透水'; break;
			case '13': m12=m12+'放炮'; break;
			case '14': m12=m12+'火药爆炸'; break;
			case '15': m12=m12+'瓦斯爆炸'; break;
			case '16': m12=m12+'锅炉爆炸'; break;
			case '17': m12=m12+'容器爆炸'; break;
			case '18': m12=m12+'其它爆炸'; break;
			case '19': m12=m12+'中毒和窒息'; break;
			case '20': m12=m12+'其它伤害'; break;
			default: m12=""; break;
		}
	}
    
	html.push('<div style="width:280px;height:40px;" ');
	html.push('<p style="font-size:14px;">物资类别：'+ m1 +'</br>物资名称：'+json.m2+'</br>型号：'+json.m3+'</br>数量：'+json.m4+'</br>自储数量：'+json.m6+'</br>代储数量：'+json.m7+'</br>储存单位：'+json.m8+'</br>储存地址：'+json.m9+'</br>应对事故类型：'+m12+'</br></p>');
	html.push('</div>');
	return new window.BMap.InfoWindow(html.join(""), {offset: new BMap.Size(0, -10)});

}

//创建避难场所InfoWindow
function bncscreateInfoWindow(i){
    var json = vmarkerArr[i];
    var m2="";
    var html = [];
    switch(json.m2){
    	case '1': m2="室外避难场所"; break;
    	case '2': m2="室内避难场所"; break;
    	case '3': m2="临时避难场所"; break;
    	case '4': m2="长期避难场所"; break;
    	case '5': m2="紧急避难场所"; break;
    	case '6': m2="中心避难场所"; break;
    	case '7': m2="固定避难场所"; break;
    	case '8': m2="其他避难场所"; break;
		default: m2=""; break;
    }
 
	html.push('<div style="width:280px;height:40px;" ');
	html.push('<p style="font-size:14px;">场所名称：'+ json.m1 +'</br>场所类型：'+m2+'</br>详细地址：'+json.m3+'</br>可容纳人数：'+json.m4+'</br>联系人：'+json.m5+'</br>联系人电话：'+json.m6+'</br></p>');
	html.push('</div>');
	return new window.BMap.InfoWindow(html.join(""), {offset: new BMap.Size(0, -10)});

}

//创建应急医院InfoWindow
function yjyycreateInfoWindow(i){
    var json = vmarkerArr[i];
    var m2="";
    var html = [];
    switch(json.m2){
    	case '1': m2="一级甲等"; break;
    	case '2': m2="一级乙等"; break;
    	case '3': m2="一级丙等"; break;
    	case '4': m2="二级甲等"; break;
    	case '5': m2="二级乙等"; break;
    	case '6': m2="二级丙等"; break;
    	case '7': m2="三级特等"; break;
    	case '8': m2="三级甲等"; break;
    	case '9': m2="三级乙等"; break;
    	case '10': m2="三级丙等"; break;
		default: m2=""; break;
    }
 
	html.push('<div style="width:280px;height:40px;" ');
	html.push('<p style="font-size:14px;">医院名称：'+ json.m1 +'</br>等级：'+m2+'</br>地址：'+json.m3+'</br>主要负责人：'+json.m4+'</br>主要负责人电话：'+json.m5+'</br>联系人：'+json.m6+'</br>联系人电话：'+json.m7+'</br></p>');
	html.push('</div>');
	return new window.BMap.InfoWindow(html.join(""), {offset: new BMap.Size(0, -10)});

}