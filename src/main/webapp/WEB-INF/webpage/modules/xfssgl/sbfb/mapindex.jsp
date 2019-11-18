<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>消防设施分布图</title>
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
<%-- <script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxyt/mapindex.js?v=1.4"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script> --%>

</head>
<body>
 	<div id="r-result" style="margin:10px auto;" >
		&nbsp;&nbsp;
		<input id="keyword" type="text" class="easyui-textbox" style="width:150px;height:30px;" value="" data-options="prompt: '消防设施名称' " />
		<button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="qysearch('keyword')">
            <i class="fa fa-search"></i> 查询
         </button>
         <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="qyreset()">
            <i class="fa fa-refresh"></i> 全部
         </button>
         <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="downImg()">
            <i class="fa fa-download"></i> 保存平面分布图
         </button>
	</div>
	<div style="height:100%;width:100%;position:absolute;" id="dituContent"><img style="width:100%" id="img1"></img></div>
<script type="text/javascript">
var windowheight= $(window).height();
	var windowwidth= $(window).width()-20;
	if(windowheight>600) windowheight=600;
	if(windowwidth>1100) windowwidth=1100;
	var ctx='${ctx}';
	var type = '${type}';
	var qyid = '${qyid}'
	var flag = '0'; 
	//消防设施信息	
	var xfsslist='${xfsslist}';
	//平面图
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

function initMapQy(){
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		var xfss=JSON.parse(xfsslist);
		$.each(xfss, function(idx, obj) {
			var ys;
			var x=obj.x;
			var y=obj.y;
			var cname = obj.m9;//设施类别名称
			var m1 = obj.m1;//设施名称
			var state = obj.state;//状态
			var sccs = obj.sccs//生产厂商
			var icon = obj.icon;//设施图标
			if(x!="" && y!=""){
		        var top=y*wh2+"px";
		        var left=x*wh+"px";
		        if (icon != '' && icon != null) {
		        	$("#dituContent").append("<div class='ball' id='tubiao_"+obj.id+"' style='top:"+top+";left:"+left+";cursor: pointer;' onclick=\"viewInfo('"+cname+"','"+m1+"','"+state+"','"+sccs+"','tubiao_"+obj.id+"')\"><img width='20' height='20' src='${ctx}/static/model/images/xfssgl/"+icon+"' title='"+obj.m1+"'></div>");
		        } else {
		        	$("#dituContent").append("<div class='ball' id='tubiao_"+obj.id+"' style='top:"+top+";left:"+left+";cursor: pointer;' onclick=\"viewInfo('"+cname+"','"+m1+"','"+state+"','"+sccs+"','tubiao_"+obj.id+"')\"><img src='${ctx}/static/model/images/xfssgl/其他.png' title='"+obj.m1+"'></div>");
		        }
			}
		});
	}

function initImg(url){
		$("#img1").width($("#dituContent").width());
		$("#img1").attr("src",url);
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
	        url:ctx+"/xfssgl/sbfb/xfsslist/"+qyid,
	        data: {"keyword": keyword},
	        dataType: 'json', 
	        success:function(data){
	        	$(".ball").remove();
	        	var wh=$("#img1").width();
				var wh2=$("#img1").height();
	        	var xfsslist2=data.xfsslist;
				var xfss2=JSON.parse(xfsslist2);
				$.each(xfss2, function(idx, obj) {
					var ys;
					var x=obj.x;
					var y=obj.y;
					var cname = obj.m9;//设施类别名称
					var m1 = obj.m1;//设施名称
					var state = obj.state;//状态
					var sccs = obj.sccs//生产厂商
					var icon = obj.icon;//设施图标
					if(x!="" && y!=""){
				        var top=y*wh2+"px";
				        var left=x*wh+"px";
				        if (icon != '' && icon != null) {
				        	$("#dituContent").append("<div class='ball' id='tubiao_"+obj.id+"' style='top:"+top+";left:"+left+";cursor: pointer;' onclick=\"viewInfo('"+cname+"','"+m1+"','"+state+"','"+sccs+"','tubiao_"+obj.id+"')\"><img width='20' height='20' src='${ctx}/static/model/images/xfssgl/"+icon+"' title='"+obj.m1+"'></div>");
				        } else {
				        	$("#dituContent").append("<div class='ball' id='tubiao_"+obj.id+"' style='top:"+top+";left:"+left+";cursor: pointer;' onclick=\"viewInfo('"+cname+"','"+m1+"','"+state+"','"+sccs+"','tubiao_"+obj.id+"')\"><img src='${ctx}/static/model/images/xfssgl/其他.png' title='"+obj.m1+"'></div>");
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

function viewInfo(cname, m1, state, sccs, id) {
	layer.tips('设备类别：'+cname+'<br>设备名称：'+m1+'<br>设备状态：'+state+'<br>生产厂商：'+sccs+'', '#'+id+'', {
    	tips: 1
	});
}

function downImg(){
	window.open(ctx + "/xfssgl/sbfb/xzpmt/"+${bis.ID});
}
</script> 
</body>

</html>