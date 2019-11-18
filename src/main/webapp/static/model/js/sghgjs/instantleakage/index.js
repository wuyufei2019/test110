var count=0,sr,zr,qr,timec=10,reftime="", leadata={},goflag=1,arryjsonstr=[],circle_arr=[];
var allOverlay=[]; //画图覆盖物保存
var all=[];//记录所有请求的点
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
		instantleakage(e);
		
	});
	
}


function instantleakage(e){
	top.layer.open({
	    type: 2,  
	    area: ['650px', '350px'],
	    title: '瞬时泄漏事故计算',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/sghgjs/instantleakage/create',
	    success: function(layero, index){
	    	var body = top.layer.getChildFrame('body', index);
	    	body.find("#instantleakage_form_id_m7").val(e.point.lng);
	        body.find("#instantleakage_form_id_m8").val(e.point.lat);
	        map.clearOverlays();//清空地图覆盖物
	    },
	    btn: ['计算', '取消'],
	    yes: function(index, layero){
	    	 var body = top.layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; 
	         var inputForm = body.find('#instantleakage_form');
	         
	         var now_point =  new BMap.Point(e.point.lng, e.point.lat );
		     var marker = new BMap.Marker(now_point); //创建marker对象
			 	
			 marker.setPosition(now_point);//设置覆盖物位置
			 marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画	  
			
			
	         if(iframeWin.contentWindow.doSubmit()){
	        	 
	        	 map.clearOverlays();//清空地图覆盖物
				 map.addOverlay(marker); //在地图中添加marker
				 clearInterval(reftime);
				 timec=10;
				 goflag=1;
				 $('#aca_ts').timespinner('disable');
				 
				 
	        	//显示应急资源、疏散距离、处理技术
	     		var postdata={wzname: body.find('#instantleakage_form_id_m1_1').val(), lng:e.point.lng , lat:e.point.lat }
	     		show(postdata);
	     		
	     		//form参数遍历
	     		leadata=new Object();
	     	    $.each(inputForm.serializeArray(),function(index,param){  
	     	        if(!(param.name in obj)){  
	     	        	leadata[param.name]=param.value;  
	     	        }  
	     	    });  
	     	    
	     		
	     		//计算事故结果
	     		$.ajax({
	    			type:'post',
	    			url:ctx+"/sghgjs/instantleakage/create",
	    			data:leadata,
	    			dataType: 'json',
	    			async:false,
	    			success:function(data){
	                	
	    				showTimeWin();//弹出时间微调界面
	                	
	                	top.layer.close(index);//关闭计算窗口
	                	
	    	        	if(data.r<=1){
	    	        		layer.open({title: '提示',offset: 'rb',content: "一共用时:"+ formatSeconds(timec-10),shade: 0 ,time:3000});
	    	        		timec=0;
	    	        	}else{
	    	        		circleDrow(data.lng,data.lat,data.r);
	    					reftime=setInterval(function(){ countBtn2(e); },1000);//每隔1秒调用一次getList() 
	    	        	}
	                	
	                }
	    		});
	     	} 

		  },
		  cancel: function(index){ 
	       } 
	});
	
	
	
	

}

//计算事故后果
function countBtn(e){
	if($("#aca_instantleakage_form_mainform").form('validate')){
		
		//显示应急资源、疏散距离、处理技术
		var postdata={wzname:$('#aca_instantleakage_form_id_m1').combobox('getText'),lng:$("#aca_instantleakage_form_id_m7").val(),lat:$("#aca_instantleakage_form_id_m8").val()}
		show(postdata);
		
		leadata={"ID":$("#aca_instantleakage_form_id_ID").val(),
			  "M1":$("#aca_instantleakage_form_id_m1").combobox('getValue'),
			  "M2":$("#aca_instantleakage_form_id_m2").val(),
			  "M3":$("#aca_instantleakage_form_id_m3").val(),
			  "M4":$("#aca_instantleakage_form_id_m4").combobox('getValue'),
			  "M5":$("#aca_instantleakage_form_id_m5").combobox('getValue'),
			  "M6":$("#aca_instantleakage_form_id_m6").combobox('getValue'),
			  "M7":$("#aca_instantleakage_form_id_m7").val(),
			  "M8":$("#aca_instantleakage_form_id_m8").val(),
			  "M9":$("#aca_instantleakage_form_id_m9").val()
		};
		
		$.ajax({
			type:'post',
			url:ctx+"/aca/instantleakage/create",
			data:leadata,//$("#aca_instantleakage_form_mainform").serialize(),
			dataType: 'json',
			async:false,
			success:function(data){
	        	//显示时间微调界面
	        	$('#instantleakage_result_win').window('open'); 
	        	
	        	if(data.r<=1){
	        		$("#aca_instantleakage_form_div").window('destroy');
	        		parent.$.messager.show({ title : "提示",msg: "一共用时:"+ formatSeconds(timec-10), position: "bottomRight" });
	        		timec=0;
	        	}else{
	        		$("#aca_instantleakage_form_div").window('destroy');
	        		circleDrow(data.lng,data.lat,data.r);
					reftime=setInterval(function(){ countBtn2(e); },1000);//每隔1秒调用一次getList() 
	        	}
			    
	        }
		});
	}
}	

//计算事故后果
function countBtn2(e){
	if(!goflag)
		return;
	timec=timec+10;//使用时长
	leadata.M9=timec;
	$.ajax({
		type:'post',
		url:ctx+"/sghgjs/instantleakage/create",
		data:leadata,
		dataType: 'json',
        success:function(data){
        	if(data.r<=1){
        		for(var i=1;i<=timec/10;i++){
        			countBtn3(e,i*10);
        		}
        		goflag=0;
        		layer.open({title: '提示',offset: 'rb',content: "一共用时:"+ formatSeconds(timec-10),shade: 0 ,time:3000});
        		timec=0;
        	}else{
	        	map.removeOverlay(circle);
	        	map.removeOverlay(label);
			    circleDrow(data.lng,data.lat,data.r);
        	}
        }
	});
}	

function countBtn3(e,timec1){
	leadata.M9=timec1;
	$.ajax({
		type:'post',
		url:ctx+"/sghgjs/instantleakage/create",
		data:leadata,
		dataType: 'json',
        success:function(data){
        	if(data.r<=1){
        		map.removeOverlay(label);
        	}else{
        		map.removeOverlay(label);
			    circleDrow(data.lng,data.lat,data.r);
        		map.removeOverlay(label);
        	}
        }
	});
}


//创建Circle
function circleDrow(lng,lat,r){
	var point = new BMap.Point(lng,lat);
	
    circle = new BMap.Circle(point,r,{
        strokeColor: "#0092DC",
        strokeWeight: 1.5,
        fillColor: "#E2E8F1",
        fillOpacity: 0.5
    });
    var opts = {
    	position : point,    // 指定文本标注所在的地理位置
    	offset   : new BMap.Size(30, -30)    //设置文本偏移量
    }
	label = new BMap.Label("扩散范围模拟时间："+formatTime(timec), opts);  // 创建文本标注对象
		label.setStyle({
			 //color : "red",
			 fontSize : "12px",
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑"
		 });
		
	map.addOverlay(label);      
	$("#aca_ts").timespinner('setValue',formatTime(timec));
    map.addOverlay(circle);
//    if( timec==10 )	circle_arr.push( circle );
}

//创建Circle
function circleDrowNoLabel(lng,lat,r){
	circle = new BMap.Circle(new BMap.Point(lng,lat),r,{
        strokeColor: "#0092DC",
        strokeWeight: 1,
        fillColor: "#E2E8F1",
        fillOpacity: 0.2
    });
    map.addOverlay( circle );
    circle_arr.push( circle );
}

//将秒转换成时分秒 hh:mm:ss
function formatTime(t){
	var ss;
	h=parseInt(t/3600);
	m=parseInt((t%3600)/60);
	s=t%60;
	if(h<10)
		h="0"+h;
	if(m<10)
		m="0"+m;
	if(s<10)
		s="0"+s;
	return h+":"+m+":"+s;
}

//将时分秒 hh:mm:ss转换成秒
function formatTime2(t){	
	var arry=t.split(':');
	h=parseInt(arry[0])*3600;
	m=parseInt(arry[1])*60;
	s=parseInt(arry[2]);
	return h+m+s;
}

//创建InfoWindow
function createInfoWindow(){
    var iw = new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;color:red’>死亡半径：" + sr +"</p></br>" +
    		"<p style=’font-size:12px;lineheight:1.8em;color:yellow’>重伤半径：" + zr +"</p></br>" + 
    		"<p style=’font-size:12px;lineheight:1.8em;color:blue’>轻伤半径：" + qr +"</p>" );
    return iw;
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


//
function timeStop(){
	$('#aca_ts').timespinner('enable');
	goflag=0;
}

function timeGo(){
	goflag=1;
//	map.removeOverlay(circle);
//	map.removeOverlay(label);
	for(var j=0;j<circle_arr.length;j++){
		map.removeOverlay(circle_arr[j]);
	}
	$('#aca_ts').timespinner('disable');
	h=$('#aca_ts').timespinner('getHours');
	m=$('#aca_ts').timespinner('getMinutes');
	s=$('#aca_ts').timespinner('getSeconds');

	if(!isNaN(h)&&!isNaN(m)&&!isNaN(s))
		timec=parseInt(h)*3600+parseInt(m)*60+parseInt(s);
}