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
	
	.menu_ {
	    display: flex;
	}

	.menu_ div{
		margin: 5px;
	    background: #d3e9fb;
	    padding: 4px 6px;
	    border-radius: 4px;
	    color: #000;
	    cursor: pointer;
	}
	.menu_ div.chos{
	    background: #529edc;
	    color: #fff;
	}
	
	.menus_{
	    padding: 5px 10px;
		align-items: center;
    display: flex;
    flex-wrap: wrap;
	}
	.btn{
	margin-left: 5px;
	}
	
	.imgmap{
	height:calc(100% - 100px);width:100%;position:absolute;overflow: scroll;
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
			<div title="风险点分布图" style="height:100%;" data-options="" id="fxdfbt_">
			 	<div id="r-result" class="menus_" >
				 	<div id="r-result">
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
			         
			         <!-- 将菜单拉过来 -->
					<div class="menu_">
						<div class="chos" onclick="initMapQy()">风险点分布图</div>
						<div id="fxsst">风险四色图</div>
						<div id="wxzyfbt">危险作业分布图</div>
						<div id="rywz">人员位置分布图</div>
					</div>
			         
				</div>
				<!-- 将菜单拉过来
				<div class="menu_">
					<div class="chos">风险点分布图</div>
					<div>风险四色图</div>
					<div>危险作业分布图</div>
					<div>人员位置分布图</div>
				</div> -->
				
				<div class="imgmap" style="" id="dituContent2">
					<img style="width:100%;min-width:1200px;" id="img1" title=""></img>
					
					<div id="sst" style="display: none;">
						<!-- 企业风险四色图 -->
						<div id="sst_div">
							<span class="btn btn-warning btn-sm" id="lastImg" style="height: 30px;width: 60px;position: absolute;top: 20px;right: 115px;" onclick="showLastImg(this)">上一张</span>
							<span class="btn btn-info btn-sm" id="nextImg" style="height: 30px;width: 60px;position: absolute;top: 20px;right: 50px;" onclick="showNextImg(this)">下一张</span>
							<img id="sst_img" style="width: 100%;"/>
						</div>

						<!-- 提示信息 -->
						<div id="sst_msg" style="text-align: center;display: none">
							<span style="font-size: 25px;color: red;"><strong>请在企业信息中上传企业四色图</strong></span>
						</div>
					</div>
				</div>
			</div>
			<!-- <div title="风险平面图" style="height:100%;" data-options="" id="">
				<div style="height:100%;width:100%;position:absolute;" id="dituContent2"><img style="width:100%" id="img2"></img></div>
			</div>
		</div> -->
	</c:if>
<script type="text/javascript">
$(window).ready(function(){
	$(".menu_ div").click(function(){
		$(this).addClass("chos").siblings("div").removeClass("chos");
	})
	
	$(".imgmap").height($("#fxdfbt_").height()-$(".menus_").height()-2)
})
$(window).resize(function(){
	$(".imgmap").height($("#fxdfbt_").height()-$(".menus_").height()-2)
})

var windowheight= $(window).height();
var windowwidth= $(window).width()-20;
if(windowheight>600) windowheight=600;
if(windowwidth>1100) windowwidth=1100;
var ctx='${ctx}';
var type = '${type}';
var qy_id = ${bis.ID};
var flag = '0'; 
var flag2 = '0';
var pmturl;
	
if(type=='1'){
	//风险点分布图
	var fxdlist='${fxdlist}';
	var pmtpath='${bis.m33_3}';
	if(pmtpath == '' ||pmtpath == null ||pmtpath == undefined){
		flag = '1';
		layer.msg("请在重大危险源系统-基础信息-企业信息中上传企业平面图！",{time: 5000});
	}else{
		var url=pmtpath.split('||');
		pmturl = url[0];
		//初始化平面图
		initImg(url[0]);
		$("#img1").load(function() {
			initMapQy();
		});
	}
}

// 显示风险点mark点
function initMapQy(){
	// 清除平面图图标
	$('.ball').remove();

	$('#img1').show();
	$('#sst').hide();

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
				$("#dituContent2").append('<div class="ball" style="top:'+top+';left:'+left+'" onclick="showInfo('+obj.id+')"><img src="${ctx}/static/model/images/map/bdmap_icon_h02.png" title="'+obj.m1+'"></div>');
			}else if(ys == '2'){
				$("#dituContent2").append('<div class="ball" style="top:'+top+';left:'+left+'" onclick="showInfo('+obj.id+')"><img src="${ctx}/static/model/images/map/bdmap_icon_c02.png" title="'+obj.m1+'"></div>');
			}else if(ys == '3'){
				$("#dituContent2").append('<div class="ball" style="top:'+top+';left:'+left+'" onclick="showInfo('+obj.id+')"><img src="${ctx}/static/model/images/map/bdmap_icon_y02.png" title="'+obj.m1+'"></div>');
			}else if(ys == '4'){
				$("#dituContent2").append('<div class="ball" style="top:'+top+';left:'+left+'" onclick="showInfo('+obj.id+')"><img src="${ctx}/static/model/images/map/bdmap_icon_l02.png" title="'+obj.m1+'"></div>');
			}
		}
	});
}

// 初始化企业平面图
function initImg(url){
	$("#img1").width($("#dituContent").width());
	$("#img1").attr("src",url);
}	

// 显示风险点两单三卡信息
function showInfo(id) {
	parent.layer.open({
		type: 2,
		shift: 1,
		area: ["90%", "90%"],
		title: "风险点两单三卡信息",
		maxmin: false,
		content: ctx+"/fxgk/fxfb/view/"+id+"?type=A3" ,
		btn: ['确定', '关闭'],
		yes: function(index, layero){
			var body = layer.getChildFrame('body', index);
			var iframeWin = layero.find('iframe')[0];
			var inputForm = body.find('#inputForm');
			iframeWin.contentWindow.doSubmit();
		},
		cancel: function(index){
		}
	});

}
	
function downImg(){
	window.open(ctx + "/fxgk/fxjg/xzpmt/"+${bis.ID});
}

// 风险四色图
$("#fxsst").click(function() {
	// 清除平面图图标
	$('.ball').remove();

	$('#sst').show();
	$('#img1').hide();

	var fxsst = '${bis.m33_4}';
	if (fxsst) {// 如果企业风险四色图存在
		fxssts = fxsst.split(',');
		var firstImgUrl = fxssts[0].split('||')[0];
		$('#sst_img').prop('src', firstImgUrl);// 显示第一张图片
		$('#sst_img').prop('name', 0);// 把img标签的name属性值设置为第一张图片在fxssts中的下标
		$('#lastImg').hide();// 隐藏上一张按钮
	} else {// 如果不存在，则隐藏按钮，显示提示信息
		$('#sst_div').hide();
		$('#sst_msg').show();
	}
});

// 显示上一张图片
function showLastImg(obj) {
	var curIndex = parseInt($(obj).next().next().prop('name'));// 获取当前图片在fxssts中的下标
	var lastIndex = parseInt(curIndex - 1);// 获取上一张图片在fxssts中的下标
	if (fxssts[lastIndex] != undefined && fxssts[lastIndex] != null){// 如果上一张图片存在
		var lastImgUrl = fxssts[lastIndex].split('||')[0];
		$('#sst_img').prop('src', lastImgUrl);// 把img标签的src属性设置为上一张图片url
		$('#sst_img').prop('name', lastIndex);// 把img标签的name属性设置为上一张图片的下标
		if (lastIndex == 0) {// 如果显示的是第一张图片，则隐藏'上一张'按钮
			$('#lastImg').hide();
		}
		$('#nextImg').show();
	}
}

// 显示下一张图片
function showNextImg(obj) {
	var curIndex = parseInt($(obj).next().prop('name'));// 获取当前图片在fxssts中的下标
	var nextIndex = parseInt(curIndex + 1);// 获取下一张图片在fxssts中的下标
	if (fxssts[nextIndex] != undefined && fxssts[nextIndex] != null){// 如果下一张图片存在
		var nextImgUrl = fxssts[nextIndex].split('||')[0];
		$('#sst_img').prop('src', nextImgUrl);// 把img标签的src属性设置为下一张图片url
		$('#sst_img').prop('name', nextIndex);// 把img标签的name属性设置为下一张图片的下标
		if ((nextIndex + 1) == fxssts.length) {// 如果显示的是最后一张图片，则隐藏'下一张'按钮
			$('#nextImg').hide();
		}
		$('#lastImg').show();// 显示'上一张'按钮
	}
}

// 危险作业分布图
$("#wxzyfbt").click(function() {
	// 清除平面图图标
	$('.ball').remove();

	$('#img1').show();
	$('#sst').hide();

	if(pmturl == '' ||pmturl == null ||pmturl == undefined){
		flag = '1';
		layer.msg("请在重大危险源系统-基础信息-企业信息中上传企业平面图！",{time: 5000});
	} else {
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		var fxds=JSON.parse(fxdlist);
		$.each(fxds, function(idx, obj) {
			var x=obj.m19;
			var y=obj.m20;
			if(x!="" && y!=""){
				var top=y*wh2+"px";
				var left=x*wh+"px";
				$("#dituContent2").append('<div class="ball" style="top:'+top+';left:'+left+'"><img src="${ctx}/static/model/images/fxgk/wxzy.png" title="'+obj.m1+'"></div>');
			}
		});
	}
});

// 人员位置分布图
$("#rywz").click(function() {
	// 清除平面图图标
	$('.ball').remove();

	$('#img1').show();
	$('#sst').hide();

	if(pmturl == '' ||pmturl == null ||pmturl == undefined){
		flag = '1';
		layer.msg("请在重大危险源系统-基础信息-企业信息中上传企业平面图！",{time: 5000});
	} else {
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		var fxds=JSON.parse(fxdlist);
		$.each(fxds, function(idx, obj) {
			var x=obj.m19;
			var y=obj.m20;
			if(x!="" && y!=""){
				var top=y*wh2+"px";
				var left=x*wh+"px";
				$("#dituContent2").append('<div class="ball" style="top:'+top+';left:'+left+'"><img src="${ctx}/static/model/images/fxgk/ryjk.png" title="'+obj.m1+'"></div>');
			}
		});
	}
});
</script>
</body>

</html>