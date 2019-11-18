<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>风险点分布地图</title>
<meta name="decorator" content="default"/>
<style type="text/css">
    html,body{margin:0;padding:0;}
    .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
    .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
    .ball {
    width: 24px;
    height: 24px;
    position: absolute;
	} 
</style>
<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxyt/mapindex.js?v=1.4"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>

</head>
<body>
	<c:if test="${type != '1'}">
		<div class="easyui-tabs" fit="true">
			<div title="风险点分布图" style="height:100%;" data-options="" id="">
			 	<div id="r-result" style="margin:10px auto;" >
					&nbsp;&nbsp;
					<input id="keyword" type="text" class="easyui-textbox" style="width:150px;height: 30px;" value="" data-options="prompt: '企业名称' " />
					<!-- <input type="button" class="easyui-linkbutton" value="搜索" onclick="search('keyword')" style="width: 50px;height: 22px;background-color:transparent;border:0;color:gray " />
					<input type="button"class="easyui-linkbutton" onclick="reset()" value="全部数据" style="width: 100px;height: 22px;background-color:transparent;border:0;color:gray " /> -->
					<button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search('keyword')">
			            <i class="fa fa-search"></i> 查询
			         </button>
			         <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()">
			            <i class="fa fa-refresh"></i> 全部数据
			         </button>
				</div>
				<div style="height:100%;width:100%;position:absolute;" id="dituContent"></div>
			</div>
			<div title="风险平面图" style="height:100%;" data-options="" id="">
				<div class="easyui-layout" style="height:100%; ">
					<div data-options="region:'west',split:true,border:false,title:'企业列表'"  style="width: 250px">
						<table id="qynameListDg"></table>
				    </div>  
				    <div data-options="region:'center',split:true,border:false,title:'风险平面图'">
				    	<!-- 风险平面图 -->
				    	<img id="fxpmt" style="width: 100%"/>
				    </div>   
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${type == '1'}">
		<!-- <div class="easyui-tabs" fit="true"> -->
			<div title="风险点分布图" style="height:100%;" data-options="" id="">
			 	<div id="r-result" style="padding: 10px;" >
					&nbsp;&nbsp;
					<input id="keyword" type="text" class="easyui-textbox" style="width:150px;height: 30px;" value="" data-options="prompt: '风险点名称' " />
					<button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="qysearch('keyword')">
			            <i class="fa fa-search"></i> 查询
			         </button>
			         <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="qyreset()">
			            <i class="fa fa-refresh"></i> 全部
			         </button>
			         <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="downImg()">
			            <i class="fa fa-download"></i> 保存风险点分布图
			         </button>
				</div>
				<div style="height:100%;width:100%;position:absolute;" id="dituContent"><img style="width:100%" id="img1" title=""></img></div>
			</div>
			<!-- <div title="风险平面图" style="height:100%;" data-options="" id="">
				<div style="height:100%;width:100%;position:absolute;" id="dituContent2"><img style="width:100%" id="img2"></img></div>
			</div>
		</div> -->
	</c:if>
<script type="text/javascript">
var windowheight= $(window).height();
var windowwidth= $(window).width()-20;
if(windowheight>600) windowheight=600;
if(windowwidth>1100) windowwidth=1100;
var ctx='${ctx}';
var type = '${type}';
var qy_id = ${bis.ID};
var flag = '0'; 
var flag2 = '0';
	
if(type=='1'){
	//风险点分布图
	var fxdlist='${fxdlist}';
	var pmtpath='${bis.m33_3}';
	if(pmtpath == '' ||pmtpath == null ||pmtpath == undefined){
		flag = '1';
		layer.msg("请在安全基础档案-一企一档-基本信息中上传企业平面图！",{time: 5000});
	}else{
		var url=pmtpath.split('||');
		//初始化平面图
		initImg(url[0]);
		$("#img1").load(function() {
			initMapQy();
		});
	}
	//风险平面图
	var fxpmtpath='${bis.m33_4}';
	if(fxpmtpath == '' ||fxpmtpath == null ||fxpmtpath == undefined){
		flag2 = '1';
		layer.msg("请在安全基础档案-一企一档-基本信息中上传企业风险平面图！",{time: 5000});
	}else{
		var url2=fxpmtpath.split('||');
		//初始化平面图
		initImg2(url2[0]);
	}
}else{
	var markerArr;
	var barrio;
   	var url = ctx+"/fxgk/fxyt/maplist";
   	$.ajax({
        type:"POST",
        url:url,
        dataType: 'json', 
        success:function(data){
        if(data.data!=undefined&&data.barrio!=undefined){
            markerArr = eval(data.data);
            barrio = data.barrio;
            initMap("dituContent",barrio.lng,barrio.lat,9,barrio.m1);//创建和初始化地图
            addMarker();
            createPolygon();
         }
        }
    });
}

function initMapQy(){
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		var fxds=JSON.parse(fxdlist);
		$.each(fxds, function(idx, obj) {
			var x=obj.m19;
			var y=obj.m20;
			if(x!="" && y!=""){
		        var top=y*wh2+"px";
		        var left=x*wh+"px";
		        var ys = obj.m9;
		        if(ys == '1'){
		        	$("#dituContent").append('<div class="ball" style="top:'+top+';left:'+left+'" onclick="showInfo('+obj.id+')"><img src="${ctx}/static/model/images/map/bdmap_icon_h02.png" title="'+obj.m1+'"></div>');
		        }else if(ys == '2'){
		        	$("#dituContent").append('<div class="ball" style="top:'+top+';left:'+left+'" onclick="showInfo('+obj.id+')"><img src="${ctx}/static/model/images/map/bdmap_icon_c02.png" title="'+obj.m1+'"></div>');
		        }else if(ys == '3'){
		        	$("#dituContent").append('<div class="ball" style="top:'+top+';left:'+left+'" onclick="showInfo('+obj.id+')"><img src="${ctx}/static/model/images/map/bdmap_icon_y02.png" title="'+obj.m1+'"></div>');
		        }else if(ys == '4'){
		        	$("#dituContent").append('<div class="ball" style="top:'+top+';left:'+left+'" onclick="showInfo('+obj.id+')"><img src="${ctx}/static/model/images/map/bdmap_icon_l02.png" title="'+obj.m1+'"></div>');
		        }
			}
		});
	}

function initImg(url){
		$("#img1").width($("#dituContent").width());
		$("#img1").attr("src",url);
}	

function initImg2(url){
		$("#img2").width($("#dituContent2").width());
		$("#img2").attr("src",url);
}

function showInfo(id) {
	openDialogView("风险点两单三卡信息",ctx+"/fxgk/fxfb/view/"+id+"?type=A3","90%", "90%","");
}
	
//搜索方法 
function qysearch(keyword_name) {
	if(flag == '1'){
		layer.msg("请在安全基础档案-一企一档-基本信息中上传企业平面图！",{time: 5000});
	}else{
		// 获取页面dom
		var keyword = document.getElementById(keyword_name).value;
		$.ajax({
	        type:"POST",
	        url:ctx+"/fxgk/fxfb/fxdslist?keyword="+keyword,
	        dataType: 'json', 
	        success:function(data){
	        	$(".ball").remove();
	        	var wh=$("#img1").width();
				var wh2=$("#img1").height();
	        	var fxdlist2=data.fxdlist;
				var fxds2=JSON.parse(fxdlist2);
				$.each(fxds2, function(idx, obj) {
					var x=obj.m19;
					var y=obj.m20;
					if(x!="" && y!=""){
				        var top=y*wh2+"px";
				        var left=x*wh+"px";
				        var ys = obj.m9;
				        if(ys == '1'){
				        	$("#dituContent").append('<div class="ball" style="top:'+top+';left:'+left+'"><img src="${ctx}/static/model/images/map/bdmap_icon_h02.png" title="'+obj.m1+'"></div>');
				        }else if(ys == '2'){
				        	$("#dituContent").append('<div class="ball" style="top:'+top+';left:'+left+'"><img src="${ctx}/static/model/images/map/bdmap_icon_c02.png" title="'+obj.m1+'"></div>');
				        }else if(ys == '3'){
				        	$("#dituContent").append('<div class="ball" style="top:'+top+';left:'+left+'"><img src="${ctx}/static/model/images/map/bdmap_icon_y02.png" title="'+obj.m1+'"></div>');
				        }else if(ys == '4'){
				        	$("#dituContent").append('<div class="ball" style="top:'+top+';left:'+left+'"><img src="${ctx}/static/model/images/map/bdmap_icon_l02.png" title="'+obj.m1+'"></div>');
				        }
					}
				});
	        }
	    });
	}
}

// 重置返回所有结果
function qyreset() {
	if(flag == '1'){
		layer.msg("请在安全基础档案-一企一档-基本信息中上传企业平面图！",{time: 5000});
	}else{
		$("#keyword").textbox('setValue','');
		qysearch("keyword");
	}
}


function downImg(){
	window.open(ctx + "/fxgk/fxjg/xzpmt/"+${bis.ID});
}
</script>
</body>

</html>