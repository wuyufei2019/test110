var count=0,pide,sgpide,timec=10,reftime="", leadata={},sr,zr,qr;
var iframeindex,iframelayero,global_weathertoday_fx;
var consequenceid;//事故id
var yjzy_iframeindex;//应急资源弹窗id
var allOverlay=[]; //画图覆盖物保存

var jylxdata; //事故计算救援路线结果保存


var flag=false;//地图点击事件是否有效

//重置
function reset(){
	flag=false;
	map.clearOverlays();
	layer.close(yjzy_iframeindex);
}

function onloadacacount(){
	
	if(flag){
		return ;
	}
	
	layer.open({
	    type: 2,  
	    area: ['750px', '400px'],
	    title: '事故后果计算',
        maxmin: false, 
        shift: 1,
        content: ctx+'/ead/yjjc/form',
        success: function(layero, index){
	    	var body = layer.getChildFrame('body', index);
	    	iframeindex=index;
	    	iframelayero=layero;
	    	body.find("#ead_yjjc_form_id_hid_lng").val(pide.point.lng);
	        body.find("#ead_yjjc_form_id_hid_lat").val(pide.point.lat);
	        body.find("#ead_yjjc_form_id_hid_weathertoday_fx").val(global_weathertoday_fx);
	        
	    },
	});
}


//计算火球事故后果
function fireball(){
	var body = layer.getChildFrame('body', iframeindex);
	var iframeWin = iframelayero.find('iframe')[0]; 
    var inputForm = body.find('#ead_yjjc_form_mainform');
    
    var index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
    
	if(iframeWin.contentWindow.doSubmit()){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createfireball",
			data:inputForm.serialize(),
			dataType: 'json',
			beforeSend:function(){
				 
			}, 
            success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);
    		   
    		    layer.close(iframeindex);//关闭计算窗口
    		    layer.close(index);//关闭加载层
    		    onloadjylx(data.id);//在地图上显示救援路线
    		    consequenceid=data.id;
    		    showBtnPanel();//显示按钮弹窗
    		   // top.openTab(ctx+'/ead/yjjc/consequence/'+data.id,"应急调度与处置", false);
    		    flag=true;
            },
            error:function(){
            	layer.close(index);//关闭加载层
            }
		});
	}

}	

//计算事故后果
function gasphysical(){
	var body = layer.getChildFrame('body', iframeindex);
	var iframeWin = iframelayero.find('iframe')[0]; 
    var inputForm = body.find('#ead_yjjc_form_mainform');
    
    var index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
    
	if(iframeWin.contentWindow.doSubmit()){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/creategasphysical",
			data:inputForm.serialize(),
			dataType: 'json',
			beforeSend:function(){
				 
			}, 
            success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);

                layer.close(iframeindex);//关闭计算窗口
                layer.close(index);//关闭加载层
    		    onloadjylx(data.id);//在地图上显示救援路线
    		    consequenceid=data.id;
    		    showBtnPanel();//显示按钮弹窗
    		    flag=true;
            },
            error:function(){
            	layer.close(index);//关闭加载层
            }
		});
	}
}	

//喷射火
function jetfire(){
	var body = layer.getChildFrame('body', iframeindex);
	var iframeWin = iframelayero.find('iframe')[0]; 
    var inputForm = body.find('#ead_yjjc_form_mainform');
    
    var index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
    
	if(iframeWin.contentWindow.doSubmit()){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createjetfire",
			data:inputForm.serialize(),
			dataType: 'json',
			beforeSend:function(){
				 
			}, 
           success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);

            	layer.close(iframeindex);//关闭计算窗口
            	layer.close(index);//关闭加载层
     		    onloadjylx(data.id);//在地图上显示救援路线
     		    consequenceid=data.id;
     		    showBtnPanel();//显示按钮弹窗
     		   flag=true;
           },
           error:function(){
           	layer.close(index);//关闭加载层
           }
		});
	}
}

//持续泄漏
function leakage(){
	var body = layer.getChildFrame('body', iframeindex);
	var iframeWin = iframelayero.find('iframe')[0]; 
    var inputForm = body.find('#ead_yjjc_form_mainform');
    
    var index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
    
	if(iframeWin.contentWindow.doSubmit()){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createleakage",
			data:inputForm.serialize(),
			dataType: 'json',
			beforeSend:function(){
			 
			}, 
           success:function(data){
            	sr=data.lng1;
            	zr=data.lng2;
            	qr=data.lng3;

    		    layer.close(iframeindex);//关闭计算窗口
    			layer.close(index);//关闭加载层
     		    onloadjylx(data.id);//在地图上显示救援路线
     		    consequenceid=data.id;
     		    showBtnPanel();//显示按钮弹窗
     		   flag=true;
            },
            error:function(){
               	layer.close(index);//关闭加载层
            }
		});
	}
}	

//物理爆炸（压力容器爆炸）
function physical(){
	var body = layer.getChildFrame('body', iframeindex);
	var iframeWin = iframelayero.find('iframe')[0]; 
    var inputForm = body.find('#ead_yjjc_form_mainform');
    
    var index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
    
	if(iframeWin.contentWindow.doSubmit()){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createphysical",
			data:inputForm.serialize(),
			dataType: 'json',
			beforeSend:function(){

			}, 
           success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);

            	layer.close(iframeindex);//关闭计算窗口
            	layer.close(index);//关闭加载层
     		    onloadjylx(data.id);//在地图上显示救援路线
     		    consequenceid=data.id;
     		    showBtnPanel();//显示按钮弹窗
     		   flag=true;
            },
            error:function(){
               	layer.close(index);//关闭加载层
            }
		});
	}
}	

//池火灾
function poolfire(){
	
	var body = layer.getChildFrame('body', iframeindex);
	var iframeWin = iframelayero.find('iframe')[0]; 
    var inputForm = body.find('#ead_yjjc_form_mainform');
    
    var index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
    
	if(iframeWin.contentWindow.doSubmit()){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createpoolfire",
			data:inputForm.serialize(),
			dataType: 'json',
			beforeSend:function(){
//				 layer.close(iframeindex);//关闭计算窗口
			}, 
           success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);

            	layer.close(iframeindex);//关闭计算窗口
            	layer.close(index);//关闭加载层
     		    onloadjylx(data.id);//在地图上显示救援路线
     		    consequenceid=data.id;
     		    showBtnPanel();//显示按钮弹窗
     		   flag=true;
            },
            error:function(){
               	layer.close(index);//关闭加载层
            }
		});
	}
}

//化学爆炸（蒸气云爆炸）
function vce(){
	var body = layer.getChildFrame('body', iframeindex);
	var iframeWin = iframelayero.find('iframe')[0]; 
    var inputForm = body.find('#ead_yjjc_form_mainform');
    
    var index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
    
	if(iframeWin.contentWindow.doSubmit()){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createvce",
			data:inputForm.serialize(),
			dataType: 'json',
			beforeSend:function(){
				 
			}, 
           success:function(data){
            	sr=strsubnumber2(data.sw);
            	zr=strsubnumber2(data.zs);
            	qr=strsubnumber2(data.qs);
            	
            	layer.close(iframeindex);//关闭计算窗口
            	layer.close(index);//关闭加载层
     		    onloadjylx(data.id);//在地图上显示救援路线
     		    consequenceid=data.id;
     		    showBtnPanel();//显示按钮弹窗
     		   flag=true;
            },
            error:function(){
               	layer.close(index);//关闭加载层
            }
		});
	}
}	

//瞬时泄漏
function instantleakage(){
	var body = layer.getChildFrame('body', iframeindex);
	var iframeWin = iframelayero.find('iframe')[0]; 
    var inputForm = body.find('#ead_yjjc_form_mainform');
    
    var index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
    
	if(iframeWin.contentWindow.doSubmit()){
		$.ajax({
			type:'post',
			url:ctx+"/ead/yjjc/createinstantleakage",
			data:inputForm.serialize(), 
			dataType: 'json',
			beforeSend:function(){
				
			}, 
	        success:function(data){
	        	layer.close(index);//关闭加载层
	        	layer.close(iframeindex);//关闭计算窗口
     		    onloadjylx(data.id);//在地图上显示救援路线
     		    consequenceid=data.id;
     		    showBtnPanel();//显示按钮弹窗
     		   flag=true;
	        },
            error:function(){
               	layer.close(index);//关闭加载层
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
function createCircle(point){
	var circle1 = new BMap.Circle(new BMap.Point(point.lng,point.lat),sr,{strokeColor:"red", strokeWeight:1.5,strokeOpacity:0.8,fillColor:"#FF0000",fillOpacity:0.4});      /*死亡半径*/   
	var circle2 = new BMap.Circle(new BMap.Point(point.lng,point.lat),zr,{strokeColor:"yellow", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#FFFF00",fillOpacity:0.2});    /*重伤半径*/ 
	var circle3 = new BMap.Circle(new BMap.Point(point.lng,point.lat),qr,{strokeColor:"#0092DC", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#BBEBFE",fillOpacity:0.2});    /*轻伤半径*/
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

 

//取小数点2位
function strsubnumber2(str){
    var f = parseFloat( JSON.stringify(str).substring(0,(JSON.stringify(str).indexOf(".")+3)) );    
    if (isNaN(f)) {    
        return;    
    }    
	return  Math.round(f*100)/100;	
}

//显示按钮导航窗口
function showBtnPanel(){
	var left= $(window).width()-150;
	var height = window.parent.$("#content-main").height();
	
	layer.open({
	    type: 1,  
	    area: ['110px', '470px'],
	    offset: ['50px', left+'px'],
	    title: ' ',
	    maxmin: true,
        shift: 5,
        shade :0,
	    content: $('#yjzy_btn_panel')
	});
}

//显示资源datagrid窗口
function showDataGridPanel(divid,title){
	layer.close(yjzy_iframeindex);
	var height = $(window).height();
	
	layer.open({
	    type: 1,  
	    area: ['450px', height/2+'px'],
	    offset: ['50px','50px'],
	    title: title,
	    maxmin: true,
        shift: 5,
        shade :0,
	    content: $(divid),
	    success: function(layero, index){
	    	yjzy_iframeindex=index;
	    	if(divid=='#yjjc_consequence_yjzb'){//应急装备
	    		onloadyjzb();
	    		onloadyjzbmap();
	    	}else if(divid=='#yjjc_consequence_yjdw'){//应急队伍
	    		onloadyjdw();
		    	onloadyjdwmap();
	    	}else if(divid=='#yjjc_consequence_yjwz'){//应急物资
	    		onloadyjwz();
	    		onloadyjwzmap();
	    	}else if(divid=='#yjjc_consequence_bncs'){//避难场所
	    		onloadbncs();
	    		onloadbncsmap();
	    	}else if(divid=='#yjjc_consequence_yjzj'){//应急专家
	    		onloadyjzj();
	    		createJYLX();
	    	}else if(divid=='#yjjc_consequence_yjyy'){//应急医院
	    		onloadyjyy();
	    		onloadyjyymap();
	    	}else if(divid=='#yjjc_consequence_yjczjs'){//应急处置技术
	    		onloadyjczjsData();
	    		createJYLX();
	    	}else if(divid=='#yjjc_consequence_yjtxl'){//应急通讯录
	    		onloadyjtxl();
	    		createJYLX();
	    	} 
	    }
	});
}

//救援路线
function onloadjylx(consequenceid){
	layer.close(yjzy_iframeindex);//关闭应急资源弹窗
	map.clearOverlays();
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/route",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
		async:false,
        success:function(data){
        	sgpide={point:{lng : data.point_lng,lat : data.point_lat}};
        	sgpide.type=data.type;
        	jylxdata=data;
        	createJYLX(data);
        	
        }
	});
}

//显示救援路线
function showJYLX(){
	   layer.close(yjzy_iframeindex);
	   createJYLX();
}

//创建救援路线
function createJYLX(){
	if(jylxdata==null||jylxdata=='')
		return ;
	
	map.clearOverlays();
	var data=jylxdata;
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
		yjjc_g_sr=strsubnumber2(data.sw);
		yjjc_g_zr=strsubnumber2(data.zs);
		yjjc_g_qr=strsubnumber2(data.qs);

	    var title="死亡："+yjjc_g_sr+"米, 重伤："+ yjjc_g_zr+"米, 轻伤："+yjjc_g_qr+"米";
	    var label = new BMap.Label(title,{offset:new BMap.Size(-60,-60), position:now_point});
        label.setStyle({
            borderColor:"#808080",
            cursor:"pointer"
        });
	    createCircle(sgpide.point);
	}else if(data.type=='7'){
		sgpide.lng1=data.lng1;sgpide.lat1=data.lat1;sgpide.lng2=data.lng2;
		sgpide.lat2=data.lat2;sgpide.lng3=data.lng3,sgpide.lat3=data.lat3;
	    string_split(data.lng1,data.lat1,data.lng2,data.lat2,data.lng3,data.lat3);
	}else if(data.type=='8'){
		sgpide.data=data.data;
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
	map.addOverlay(marker2_0);
	
	var myIconfx = new BMap.Icon(myIconpngfx, myIconpngfxBMapSize,{
		anchor: myIconpngfxanchor
	});
	var markerfx = new BMap.Marker(new BMap.Point(data.point_lng, data.point_lat ),{icon:myIconfx});
	markerfx.setPosition(data.point_lng, data.point_lat);//设置覆盖物位置
	map.addOverlay(markerfx);
}



var dg;
//应急队伍datagrid加载
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
	    	map.removeOverlay(new_marker);
	    	map.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	map.panTo(new_point);                //转到该点位置 
	    	
	    	var _iw = yjdwcreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjdw_tb'
		});
}

var marker1 = "";//创建一个空的marker
function newMark(){
	map.removeOverlay(allOverlay[allOverlay.length-1]);
}

//应急队伍在地图上标注
function onloadyjdwmap(){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/resteammap",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
		async:false,
        success:function(data){
        	map.clearOverlays();
	    	var markerc = new BMap.Marker(sgpide.point); //创建marker对象
	        map.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	        
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjdw.png",new BMap.Size(30,37));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m12, json.m13 ),{icon:icon});
            	allOverlay.push(marker);
            	map.addOverlay(marker);
            	
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
        	create_3_5_10Circle(sgpide.point);
//        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(sgpide);
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


//应急装备datagrid加载
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
			{field:'qyname',title:'所属单位',width:100,align:'center',
	        	formatter : function(value) {
				if(value==null||value=='')
					return "安监局";
				else
					return value;
			}},    
	        {field:'M1',title:'装备类别',width:100},
	        {field:'M2',title:'装备名称',width:100},
//			{field:'M3',title:'规格型号',width:100,align:'center'},
			{field:'M4',title:'数量',width:60,align:'center'},
//			{field:'M6',title:'自储数量',width:60,align:'center'},
//			{field:'M7',title:'代储数量',width:60,align:'center'},
			{field:'M10',title:'联系人',width:100,align:'center'},
			{field:'M11',title:'应急电话',width:100,align:'center'},
//			{field:'M9',title:'地址',width:300}
	    ]],
	    onClickRow: function (index, row){
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjzb.png",new BMap.Size(30,37));
	    	var new_point = new BMap.Point(vmarkerArr[index].m14,vmarkerArr[index].m15);
	    	var new_marker = new BMap.Marker(new_point,{icon:icon});
	    	newMark();
	    	allOverlay.push(new_marker);//添加覆盖物
	    	map.removeOverlay(new_marker);
	    	map.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	 map.panTo(new_point);                //转到该点位置 
	    	var _iw = yjzbcreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onLoadSuccess : function() {
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		},
	    toolbar:'#yjjc_consequence_yjzb_tb'
		});
}
//应急装备在地图上标注
function onloadyjzbmap(){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/resinstrumentmap",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
		async:false,
        success:function(data){
        	map.clearOverlays();
	    	var markerc = new BMap.Marker(sgpide.point); //创建marker对象
	    	map.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        	
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjzb.png",new BMap.Size(30,37));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m14, json.m15 ),{icon:icon});
            	map.addOverlay(marker);
            	
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
        	create_3_5_10Circle(sgpide.point);
        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(sgpide);
        }
	});
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


//应急物资datagrid加载
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
			{field:'qyname',title:'企业名称',width:100,align:'center',
	        	formatter: function(value){
	        		if(value==null||value==''){
	        			return"安监局";
	        		}else{
	        			return value;
	        		}
	        	}},
	        {field:'M1',title:'物资类别',width:50,align:'center' },
			{field:'M2',title:'物资名称',width:50,align:'center'},
//			{field:'M3',title:'型号',width:50,align:'center'},
			{field:'M4',title:'数量',width:40,align:'center'},
//			{field:'M6',title:'自储数量',width:40,align:'center'},
//			{field:'M7',title:'代储数量',width:40,align:'center'},
			{field:'M8',title:'储存单位',width:80},
			{field:'M9',title:'储存地址',width:100},
			
	    ]],
	    onClickRow: function (index, row){
	    	var icon;
	    	if(row.qyid!=null&&row.qyid!=""){
	    		icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjwz_qy.png",new BMap.Size(30,37));
	    	}
	    	else{
	    		icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjwz_aj.png",new BMap.Size(30,37));	
	    	}
	    	var new_point = new BMap.Point(vmarkerArr[index].m14,vmarkerArr[index].m15);
	    	var new_marker = new BMap.Marker(new_point,{icon:icon});
	    	newMark();
	    	allOverlay.push(new_marker);//添加覆盖物
	    	map.removeOverlay(new_marker);
	    	map.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	map.panTo(new_point);                //转到该点位置 
	    	var _iw = yjwzcreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onLoadSuccess : function() {
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		},
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjwz_tb'
		});
}
//应急物资在地图上标注
function onloadyjwzmap(){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/matemap",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
		async:false,
        success:function(data){
        	map.clearOverlays();
	    	var markerc = new BMap.Marker(sgpide.point); //创建marker对象
	        map.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        	var iconaj = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjwz_aj.png",new BMap.Size(30,37));
        	var iconqy = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjwz_qy.png",new BMap.Size(30,37));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
        		var marker ;
        		if(json.qyid!=null&&json.qyid!=""){
        			marker = new BMap.Marker(new BMap.Point(json.m14, json.m15 ),{icon:iconaj});
        		}else{
        			marker= new BMap.Marker(new BMap.Point(json.m14, json.m15 ),{icon:iconqy});
        		}
            	map.addOverlay(marker);
            	
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
        	create_3_5_10Circle(sgpide.point);
        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(sgpide);
        }
	});
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


//避难场所datagrid加载
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
	    	map.removeOverlay(new_marker);
	    	map.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	map.panTo(new_point);                //转到该点位置 
	    	var _iw = bncscreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_bncs_tb'
		});
}
//避难场所在地图上进行标注
function onloadbncsmap(){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/resplacemap",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
		async:false,
        success:function(data){
        	map.clearOverlays();
	    	var markerc = new BMap.Marker(sgpide.point); //创建marker对象
	        map.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画//
        	
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_bncs.png",new BMap.Size(30,37));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m12, json.m13 ),{icon:icon});
            	map.addOverlay(marker);
            	
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
        	create_3_5_10Circle(sgpide.point);
        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(sgpide);
        }
	});
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

//应急专家datagrid加载
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


//应急医院datagrid加载
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
	    	map.removeOverlay(new_marker);
	    	map.addOverlay(new_marker);
	    	new_marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    	map.panTo(new_point);                //转到该点位置 
	    	var _iw = yjyycreateInfoWindow(index);
	    	new_marker.openInfoWindow(_iw);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:'#yjjc_consequence_yjyy_tb'
		});
}

function onloadyjyymap( ){
	$.ajax({
		type:'post',
		url:ctx+"/ead/yjjc/conseque/hospitalmap",
		data:{"consequenceid":consequenceid},
		dataType: 'json', 
		async:false,
        success:function(data){
        	map.clearOverlays();
	    	var markerc = new BMap.Marker(sgpide.point); //创建marker对象
	        map.addOverlay(markerc); //在地图中添加marker
			markerc.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			
        	var icon = new BMap.Icon(ctx+"/static/model/images/ead/yjjc/i_yjyy.png",new BMap.Size(30,43));
        	vmarkerArr = eval(data.data);
        	
        	for(var i=0;i<vmarkerArr.length;i++){
        		var json = vmarkerArr[i];
            	var marker = new BMap.Marker(new BMap.Point(json.m11, json.m12 ),{icon:icon});
            	map.addOverlay(marker);
            	
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
        	create_3_5_10Circle(sgpide.point);
        	//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
        	createCirclePolygonDrow(sgpide);
        }
	});
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


//应急处置技术数据加载
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

//应急通讯录datagrid加载
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


//生成文书
function yjfzword_word(){
	var index;
	$.ajax({
		type:"post",
        url:ctx+'/ead/yjjc/conseque/word',		
        data:{"consequenceid":consequenceid},
		dataType: 'json', 
		timeout:60000,
		beforeSend:function(){
			index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
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



//创建Circle
function create_3_5_10Circle(data){
	var circle1 = new BMap.Circle(new BMap.Point(data.lng,data.lat),3000,{strokeStyle:"dashed",strokeColor:"#2A3AFF", strokeWeight:1.5,strokeOpacity:0.8,fillColor:"#0000FF",fillOpacity:0.01});      /*3KM*/   
	var circle2 = new BMap.Circle(new BMap.Point(data.lng,data.lat),5000,{strokeStyle:"dashed",strokeColor:"#0092DC", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#0000FF",fillOpacity:0.01});    /*5KM*/ 
	var circle3 = new BMap.Circle(new BMap.Point(data.lng,data.lat),10000,{strokeStyle:"dashed",strokeColor:"#62CFEB", strokeWeight:1.5,strokeOpacity:0.5,fillColor:"#0000FF",fillOpacity:0.01});    /*10KM*/

	
	var icon1 = new BMap.Icon(ctx+"/static/model/images/map/km_3.png",new BMap.Size(30,43));
	var new_point1 = new BMap.Point(data.lng,eval(data.lat-3/111));
	var new_circle1 = new BMap.Marker(new_point1,{icon:icon1});
	map.addOverlay(new_circle1);
	var icon2 = new BMap.Icon(ctx+"/static/model/images/map/km_5.png",new BMap.Size(30,43));
	var new_point2 = new BMap.Point(data.lng,eval(data.lat-5/111));
	var new_circle2 = new BMap.Marker(new_point2,{icon:icon2});
	map.addOverlay(new_circle2);
	var icon3 = new BMap.Icon(ctx+"/static/model/images/map/km_10.png",new BMap.Size(30,43));
	var new_point3 = new BMap.Point(data.lng,eval(data.lat-10/111));
	var new_circle3 = new BMap.Marker(new_point3,{icon:icon3});
	map.addOverlay(new_circle3);
	map.addOverlay(circle1);
	map.addOverlay(circle2);
	map.addOverlay(circle3);
}

//池火灾、喷射火、火球、物理爆炸、化学爆炸、持续泄漏、瞬时泄漏
function createCirclePolygonDrow(data){
	if(data.type=='1'
		||data.type=='2'
		||data.type=='3'
		||data.type=='4'
		||data.type=='5'
		||data.type=='6'){
	    createCircle(data.point);
	}else if(data.type=='7'){
	    string_split(data.lng1,data.lat1,data.lng2,data.lat2,data.lng3,data.lat3);
	}else if(data.type=='8'){
    	var markerArr = eval(data.data);
    	for(var i=0;i<markerArr.length;i++){
    		circleDrow(markerArr[i].lng,markerArr[i].lat,markerArr[i].r);
    	}
	}
}

